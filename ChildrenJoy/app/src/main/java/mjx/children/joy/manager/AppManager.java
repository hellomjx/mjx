package mjx.children.joy.manager;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;



import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import mjx.children.joy.application.MainApplication;
import mjx.children.joy.utils.LogUtil;
import mjx.children.joy.utils.ui.UIUtil;

/**
 * 应用程序Activity管理类：用于Activity管理和应用程序退出
 * Created by MJX on 2017/1/4.
 */
public class AppManager {

  private static Stack<Activity> activityStack;
  private static AppManager instance;
  //存放TabActivity的集合
  public List<Activity> mList = new LinkedList<Activity>();
  private AppManager(){
  }
  public static AppManager getAppManager(){
    if(instance==null){
      instance=new AppManager();
    }
    return instance;
  }

  /**
   * 出栈一个Activity
   */
  public void popActivity(){
    Activity activity=activityStack.lastElement();
    if(activity!=null){
      activity.finish();
      activity=null;
    }
  }

  /**
   * 出栈当前的传入的Activity
   * @param activity
     */
  public void popActivity(Activity activity){
    if(activity!=null){
      activity.finish();
      activityStack.remove(activity);
      activity=null;
    }
  }

  /**
   * 获取当前的Activity
   * @return
     */
  public Activity currentActivity(){
    Activity activity = null;
    try{
       activity=activityStack.lastElement();
    }catch (Exception e){
      LogUtil.logMsg("获取当前activity异常");
    }
    return activity;
  }

  /**
   * 把当前的Activity压入栈
   * @param activity
     */
  public void pushActivity(Activity activity){
    if(activityStack==null){
      activityStack=new Stack<Activity>();
    }
    activityStack.add(activity);
  }

  /**
   *通过类名，反射弹出当前Activity之上的所有Activity
   * @param cls
   */
  public void popAllActivityExceptOne(Class cls){
    while(true){
      Activity activity=currentActivity();
      if(activity==null){
        break;
      }
      if(activity.getClass().equals(cls) ){
        break;
      }
      popActivity(activity);
    }
  }


  /**
   * 结束所有Activity
   */
  public void finishAllActivity() {
    if(activityStack == null){
      return;
    }


    int size = activityStack.size();
    for (int i = size - 1; i > -1; i--) {
      if (null != activityStack.get(i)) {
        popActivity(activityStack.get(i));
      }
    }
    activityStack.clear();
  }

  /**
   * 退出当前的应用程序
   */
  public void AppExit() {
    try {
      Context context = UIUtil.getContext();
      finishAllActivity();
      ActivityManager activityMgr =
              (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
      activityMgr.killBackgroundProcesses(context.getPackageName());
      System.exit(0);    } catch (Exception e) {
      LogUtil.logMsg("退出APK异常");
    }
  }



  /**
   * 添加TaSupplierActivity
   * @param activity
     */
  public void addActivity(Activity activity) {
    if(mList == null){
      return;
    }
    mList.add(activity);
  }


  public void clearMList() {
    LogUtil.logMsg("删除集合中的activity-----------------------");
    try {

      if(mList == null){
        return;
      }

      for (Activity activity : mList) {
        if (activity != null)
          activity.finish();
      }
    }catch (Exception e){
      e.printStackTrace();
      return;
    }
  }


}