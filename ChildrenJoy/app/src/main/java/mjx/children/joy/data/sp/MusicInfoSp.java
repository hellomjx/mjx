package mjx.children.joy.data.sp;

import mjx.children.joy.common.Constants;
import mjx.children.joy.data.GetSpInsance;
import mjx.children.joy.utils.unzip.CreateFileUtil;
import mjx.children.joy.utils.unzip.DelFileUtil;

/**
 * 音乐文件的管理
 * 保存信息
 * 删除信息
 * 获取信息
 * Created by MJX on 2018/3/16.
 */
public class MusicInfoSp {
    /**
     * 保存音乐的地址
     * @param musicPath 音乐的地址
     */
    public static void saveMusicInfo(String musicPath){
        GetSpInsance.saveSp("music","musicPath",musicPath);
    }

    /**
     * 获取音乐的地址
     * @return
     */
    public static String getMusicPath(){
        return (String) GetSpInsance.getSpValue("music","musicPath","");
    }

    public static void clearMusic(){
        String clearUseBundlePath = CreateFileUtil.getFilePath(Constants.MUSIC_NAME,"");
        DelFileUtil.delAllFile(clearUseBundlePath);
    }
}
