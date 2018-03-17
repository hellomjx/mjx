package mjx.children.joy.data.sp;

import mjx.children.joy.data.GetSpInsance;

/**
 * 保存本地的图片信息
 * Created by MJX on 2018/3/16.
 */
public class ZipInfoSp {
    /**
     * zip是否已经解压过
     * @param flag true，已经解压过，false没有解压
     * @param zipPath 图片的地址
     */
    public static void saveZipInfo(String flag,String zipPath){
        GetSpInsance.saveSp("zip","isUnZip",flag);
        GetSpInsance.saveSp("zip","zipPath",zipPath);
    }

    /**
     * 获取zip是否已经解压过的信息
     * @return
     */
    public static String checkZipIsUnZip(){
        return (String) GetSpInsance.getSpValue("zip","isUnZip","false");
    }

    /**
     * 获取zip的图片
     * @return
     */
    public static String getPicPath(){
        return (String) GetSpInsance.getSpValue("zip","zipPath","");
    }
}
