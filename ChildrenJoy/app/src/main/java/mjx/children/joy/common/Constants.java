package mjx.children.joy.common;

import java.io.File;
import mjx.children.joy.utils.apk.ApkUtil;

/**
 * 常量
 * Created by MJX on 2017/3/30.
 */
public class Constants {
    /**
     * 是否开启调试模式，true调试模式，false正式模式
     */
    public static final boolean ISDebugMode = true;
    //Apk的下载路径
    public static final File APK_PATH = ApkUtil.getDownLoadApkPath();
    //实体返回键间隔
    public static final int DOUBLE_BACK_INTERVAL = 2000;
    //APPID
    public static final String APPID = "1101152570";
    public static final String SplashPosID = "8863364436303842593";
    //初始化的时候，创建zip文件夹的名字
    public  static final String DATA_NAME = "data";
    //初始化的时候，创建music文件夹的名字
    public  static final String MUSIC_NAME = "music";


}
