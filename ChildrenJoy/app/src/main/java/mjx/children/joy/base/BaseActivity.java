package mjx.children.joy.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import de.greenrobot.event.EventBus;
import mjx.children.joy.R;
import mjx.children.joy.common.Constants;
import mjx.children.joy.event.NetEvent;
import mjx.children.joy.manager.AppManager;
import mjx.children.joy.utils.LogUtil;
import mjx.children.joy.utils.net.CheckNet;
import mjx.children.joy.utils.toast.ToastUtil;


/**
 * 设置子页面标题名称
 * 初始化子界面
 * 初始化子界面需要的数据
 * 绑定Presenter
 * Activity的异常退出
 * Activity的销毁
 * Created by MJX on 2017/1/4.
 */


public abstract class BaseActivity extends FragmentActivity {

    /**
     * 使用Fragment来替换
     */
    protected FrameLayout baseFramelayout;
    /**
     * 无网的标题展示
     */
    protected RelativeLayout noNetTitleView;

    /**
     * 是否展示无网的标题提示
     */
    protected boolean isShowNoNetView = true;
    /**
     * 点击实体返回键是否，双击是否提示退出
     */
    protected boolean isDoubleBack = false;
    /**
     * 判断退出的时间
     */
    protected long exitTime = 0;

    /**
     * 是否点击了返回键,销毁当前的Fragment,true是点击了返回键，false是没有点击返回键
     */
     protected boolean isBackFragment = false;

    public boolean isBackFragment() {
        return isBackFragment;
    }

    public void setBackFragment(boolean backFragment) {
        isBackFragment = backFragment;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity);
        initView();
        initFragment();
        checkNet();
        handleActivityKilledException();
        LogUtil.logMsg("父控件Activity创建"+this.getLocalClassName());
        AppManager.getAppManager().pushActivity(this);
    }


    private void initView() {
        baseFramelayout = (FrameLayout) findViewById(R.id.base_framelayout);
        noNetTitleView = (RelativeLayout) findViewById(R.id.no_net_title_view);
    }


    /**
     * 返回帧布局的id
     *
     * @return
     */
    public int getBaseFrameLayoutId() {
        return R.id.base_framelayout;
    }


    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.logMsg("父控件onResume"+getLocalClassName());
//        MobclickAgent.onResume(this);
        if(CheckNet.isHaveNetWork()){
            noNetTitleView.setVisibility(View.GONE);
        }else{
            noNetTitleView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.logMsg("父控件onPause"+getLocalClassName());
//        MobclickAgent.onPause(this);
    }

    /**
     * 无网标题栏显示
     */
    protected void checkNet() {
        if (CheckNet.isHaveNetWork()) {
            noNetTitleView.setVisibility(View.GONE);
        } else {
            if (isShowNoNetView) {
                noNetTitleView.setVisibility(View.VISIBLE);
            }
        }
        EventBus.getDefault().register(this);
    }

    public void onEventMainThread(NetEvent event) {
        boolean netFlag = event.getMsg();
        if (netFlag) {
            noNetTitleView.setVisibility(View.GONE);
            haveNetrefreshData();
        } else {
            if (isShowNoNetView) {
                noNetTitleView.setVisibility(View.VISIBLE);
                noNetRefreshData();
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (isDoubleBack) {
                if (System.currentTimeMillis() - exitTime > Constants.DOUBLE_BACK_INTERVAL) {
                    ToastUtil.toastDes("再按一次退出登录");
                    exitTime = System.currentTimeMillis();
                    return true;
                } else {
                    if (!getFragmentManager().popBackStackImmediate()) {
                        isFinishCurrentActivity();
                    }
                    getFragmentManager().popBackStackImmediate();
                }
            } else {
                if (!getFragmentManager().popBackStackImmediate()) {
                    isFinishCurrentActivity();
                }
                getFragmentManager().popBackStackImmediate();
                setBackFragment(true);
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.logMsg("父控件Activity销毁"+this.getLocalClassName());
        AppManager.getAppManager().popActivity(this);
        clearData();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 子页面无网，需要进行的处理
     */
    protected  void noNetRefreshData(){

    }


    /**
     * 界面展示使用Fragment
     */
    protected abstract void initFragment();

    /**
     * Activity异常情况被杀死杀死
     */
    protected abstract void handleActivityKilledException();


    /**
     * 子界面的初始化数据
     */
    protected abstract void initSubData();

    /**
     * Activity销毁时，清除数据
     */
    protected abstract void clearData();

    /**
     * 是否关闭Activity
     */
    protected abstract void isFinishCurrentActivity();

    /**
     * 有网络刷新数据
     */
    protected abstract void haveNetrefreshData();


}
