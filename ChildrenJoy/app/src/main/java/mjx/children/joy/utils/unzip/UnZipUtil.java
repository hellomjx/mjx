package mjx.children.joy.utils.unzip;

import java.io.File;

import mjx.children.joy.common.Constants;
import mjx.children.joy.data.sp.ZipInfoSp;
import mjx.children.joy.utils.ui.UIUtil;

/**
 * 解压zip包
 * Created by MJX on 2018/3/16.
 */
public class UnZipUtil {
    /**
     * 压缩包放置的位置
     */
    private static String ZIP_NAME = "zip";
    /**
     * 使用压缩包的图片的位置
     */
    private static String USE_NAME = "use";
    public static void unZip(){
        CreateFileUtil.creatSingleFile(ZIP_NAME);
        CreateFileUtil.creatSingleFile(USE_NAME);
        clearBundleAndBundleZipFile();
        String bundlezip = CreateFileUtil.getFilePath(ZIP_NAME, Constants.DATA_NAME + ".zip");
        boolean flag = CopyDataToSdUtil.copyDataToSd(bundlezip, Constants.DATA_NAME + ".zip");
        if (!flag) {
            return;
        }
        String usebundleStr = CreateFileUtil.getFilePath(USE_NAME, "");
        File file = new File(bundlezip);
        DecompressionUtil.unZipFile(file, usebundleStr);
        String picPath = UIUtil.getContext().getFilesDir() + "/" + USE_NAME + "/" + Constants.DATA_NAME;
        ZipInfoSp.saveZipInfo("true",picPath);
        clearUseBundleFile();
    }

    /**
     * 清空Bundle文件夹和BundleZip文件夹
     */
    private static void clearBundleAndBundleZipFile(){
        String clearBundleZipPath = CreateFileUtil.getFilePath(ZIP_NAME,"");
        String clearBundlePath = CreateFileUtil.getFilePath(USE_NAME,"");
        DelFileUtil.delAllFile(clearBundleZipPath);
        DelFileUtil.delAllFile(clearBundlePath);
    }

    /**
     * 清空使用的文件夹
     */
    private static void clearUseBundleFile(){
        String clearUseBundlePath = CreateFileUtil.getFilePath(ZIP_NAME,"");
        DelFileUtil.delAllFile(clearUseBundlePath);
    }
}
