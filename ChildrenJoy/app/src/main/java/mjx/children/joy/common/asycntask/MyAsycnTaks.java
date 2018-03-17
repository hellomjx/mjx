package mjx.children.joy.common.asycntask;

/**
 * Created by Administrator on 2018/3/14.
 */

import android.os.Handler;

/**
 * 异步加载
 * Created by MJX on 2016/9/29.
 */
public abstract class MyAsycnTaks {

    private Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            postTask();
        };
    };
    /**
     * 在子线程之前执行的方法
     */
    public abstract void preTask();
    /**
     * 在子线程之中执行的方法
     */
    public abstract void doinBack();
    /**
     * 在子线程之后执行的方法
     */
    public abstract void postTask();

    /**
     * 是否继续执行，true继续，false停止继续
     */
    public static boolean isContinue;
    /**
     * 执行
     */
    public void execute(){
        preTask();
        new Thread(){
            public void run() {
                doinBack();
                handler.sendEmptyMessage(0);
            };
        }.start();
    }
}

