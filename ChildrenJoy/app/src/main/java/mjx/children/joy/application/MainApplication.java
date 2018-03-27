package mjx.children.joy.application;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Typeface;

import com.zhy.autolayout.config.AutoLayoutConifg;

import mjx.children.joy.common.checknet.NetBroadcast;

/**
 * Created by Administrator on 2018/2/26.
 */
public class MainApplication extends Application {
    public static Context context;
    public static long lastTime;
    /*检测是否在启动页按下了home键*/
    public static boolean isClickHome = false;
//    public boolean isInterceptNet() {
//        return isInterceptNet;
//    }
//
//    public void setInterceptNet(boolean interceptNet) {
//        isInterceptNet = interceptNet;
//    }

    private boolean isInterceptNet = false;
    private NetBroadcast mNetBroadcast;
    //ECApplication的实例化对象
    private static MainApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initConfig();
    }

    /**
     * 得到MainApplication实例化对象
     */
    public synchronized static MainApplication getInstance() {
        if (instance == null) {
            instance = new MainApplication();
        }
        return instance;
    }

    public  void initConfig() {
        context = getApplicationContext();
        /*屏幕适配*/
        AutoLayoutConifg.getInstance().useDeviceSize();
        registNetWorkReceiver();
    }

    public static Context getContext() {
        return context;
    }

    private void registNetWorkReceiver() {
        isInterceptNet = true;
        mNetBroadcast = new NetBroadcast();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");//要接收的广播
        registerReceiver(mNetBroadcast, intentFilter);//注册接收者
    }

    public static Typeface getTypeFace(){
        return   Typeface.createFromAsset(context.getAssets(), "fonts/hksnt.ttf");
    }

}
