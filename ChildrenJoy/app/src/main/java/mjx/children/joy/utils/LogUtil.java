package mjx.children.joy.utils;

import android.util.Log;

public class LogUtil {
	 public static boolean isShow = true;//开发模式
	 //public static boolean isShow = false;//上线模式
	 //打出来的log
	    public static void logMsg(String msg){
	        if(isShow){
	            Log.i("tag", msg);
	        }
	    }
}
