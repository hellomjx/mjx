package mjx.children.joy.utils;

/**
 * Created by Administrator on 2017/4/25.
 */

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;

import java.util.List;

import mjx.children.joy.utils.ui.UIUtil;

/**
 * 判断当前的App是在前端还是在后台
 * Created by MJX on 2016/8/10.
 */
public class AppisBackgroundUtil {
    /**
     * 判断当前应用程序处于前台还是后台
     *
     * @return true是进入后台，false是进入前台
     */
    public static boolean isApplicationBroughtToBackground() {
        Context context = UIUtil.getContext();
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }
}
