package mjx.children.joy.utils.toast;
import android.widget.Toast;
import mjx.children.joy.utils.ui.UIUtil;

/**
*Toast的工具类
*Created by MJX on 2017/2/9.
*/
public class ToastUtil {
    /**
     *Toast
     * @param strId
     */
    public static void toastDes(int strId){
        Toast.makeText(UIUtil.getContext(),UIUtil.getContext().getString(strId),Toast.LENGTH_SHORT).show();
    }

    /**
     * Toast
     * @param str
     */
    public static void toastDes(String str){
        Toast.makeText(UIUtil.getContext(),str,Toast.LENGTH_SHORT).show();
    }
}

