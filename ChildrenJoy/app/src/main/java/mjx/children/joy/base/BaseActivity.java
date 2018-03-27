package mjx.children.joy.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import de.greenrobot.event.EventBus;
import mjx.children.joy.R;
import mjx.children.joy.event.NetEvent;
import mjx.children.joy.manager.AppManager;
import mjx.children.joy.utils.LogUtil;
import mjx.children.joy.utils.net.CheckNet;


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
    protected View mView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity);
        initView();
        initSubView();
        initSubData();
        checkNet();
        LogUtil.logMsg("父控件Activity创建"+this.getLocalClassName());
        AppManager.getAppManager().pushActivity(this);
    }




    private void initView() {
        baseFramelayout = (FrameLayout) findViewById(R.id.base_framelayout);
        noNetTitleView = (RelativeLayout) findViewById(R.id.no_net_title_view);
        mView = subView();
        baseFramelayout.addView(mView);
        noNetTitleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到系统的网络设置界面
                Intent intent = null;
                // 先判断当前系统版本
                if(android.os.Build.VERSION.SDK_INT > 10){  // 3.0以上
                    intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                }else{
                    intent = new Intent();
                    intent.setClassName("com.android.settings", "com.android.settings.WirelessSettings");
                }
                startActivity(intent);
            }
        });
    }

    /**
     * 返回子布局
     * @return
     */
    public abstract View subView();

    /**
     * 数据
     * @return
     */
    public abstract void initSubData();
    public abstract void initSubView();
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
        } else {
            if (isShowNoNetView) {
                noNetTitleView.setVisibility(View.VISIBLE);
            }
        }
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.logMsg("父控件Activity销毁"+this.getLocalClassName());
        AppManager.getAppManager().popActivity(this);
        EventBus.getDefault().unregister(this);
    }

}
