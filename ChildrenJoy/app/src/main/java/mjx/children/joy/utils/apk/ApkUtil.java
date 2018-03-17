package mjx.children.joy.utils.apk;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;


import java.io.File;

/**
 * 下载APK的路径
 * Created by Administrator on 2017/4/13.
 */
public class ApkUtil {

    /**
     * 监听安装apk界面，点击取消之后，false不需要重新加载数据，true重新加载数据
     */
    private static boolean isLoadDataAgain = false;

    public static boolean isLoadDataAgain() {
        return isLoadDataAgain;
    }

    public static void setIsLoadDataAgain(boolean isLoadDataAgain) {
        ApkUtil.isLoadDataAgain = isLoadDataAgain;
    }

    /**
     * 获取APK下载的地址
     * @return
     */
        public static File getDownLoadApkPath(){
            File file = new File(Environment.getExternalStorageDirectory(), "supplier.apk");
            return file;
        }


    public static void installApk(String path, Activity activity){
        if(activity == null){
            return;
        }
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");  /*这个是调出APk的安装的界面*/
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setDataAndType(Uri.fromFile(new File(path)), "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivityForResult(intent, 0);
        activity.finish();
    }

    /**
     * 删除下载的APK
     * @return
     */
    public static void delDownLoadApk(){
        File file = new File(Environment.getExternalStorageDirectory(), "supplier.apk");
        if(file.exists()){
            file.delete();
        }
    }

}
