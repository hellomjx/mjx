package mjx.children.joy.utils.pic;

import java.lang.reflect.Field;

import mjx.children.joy.R;

/**
 * 获取图片的ResId
 * Created by MJX on 2018/3/14.
 */
public class ResDrawableImgUtil {
    public final static String FILE_EXTENSION_SEPARATOR = ".";//文件扩展名分割器
    /**
     * 根据图片名称获取图片的resID值（方案二）
     * @param imgName 图片名称*/
    public static int getResourceIdByReflect(String imgName){

        //判断imgName是否含有后缀
        int extenPosi = imgName.lastIndexOf(FILE_EXTENSION_SEPARATOR);
        if(extenPosi != -1){
            imgName = imgName.substring(0, extenPosi);
        }

        int imgResourceId = -1;
        Class drawable = R.drawable.class;
        Field field = null;
        try {
            field = drawable.getField(imgName);
            imgResourceId = field.getInt(field.getName());
        } catch (Exception e) {
        }
        return imgResourceId;
    }
}
