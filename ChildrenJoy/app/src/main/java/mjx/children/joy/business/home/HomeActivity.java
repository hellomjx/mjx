package mjx.children.joy.business.home;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.ViewPager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mjx.children.joy.R;
import mjx.children.joy.business.home.adapter.MyAdapter;
import mjx.children.joy.utils.HttpDownloader;

public class HomeActivity extends Activity {

    //    private Button mButton;
    private String SDPATH;
    private ViewPager viewPager;
    private Map<Integer, String> dataMap = new HashMap<>();
    List<Integer> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initData();
//        mButton = (Button) findViewById(R.id.button);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        initViewPagerData();

//        mButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try{
//                    File file = createFile();
//                    String SDPATH = Environment.getExternalStorageDirectory()+ File.separator;
//                    String packageName = getPackageName();
//                    File file1 = new File(SDPATH + "/"+packageName+"/a1.mp3");
//                    file1.createNewFile();
//                    downloadMusic();
//                }catch (Exception e){
//                    e.toString();
//                }
//        List<DataBean> antusheng = XmlData.getData("antusheng.xml");
//            }
//        });

    }

    private void initViewPagerData() {
        if (list == null) {
            list = new ArrayList<>();
        }
        list.clear();
        list.add(R.drawable.baby_story); //婴儿故事
        list.add(R.drawable.little_childrens_story); //少儿故事
        list.add(R.drawable.andersen); //安徒生童话
        list.add(R.drawable.aesops_fables); //伊索寓言
        list.add(R.drawable.bed_time_story); //睡前故事
        list.add(R.drawable.celebrity_story); //名人故事
        list.add(R.drawable.folk_legend); //民间传说
        list.add(R.drawable.happy_idioms); //快乐成语
        list.add(R.drawable.ldiom_story); //成语故事
        list.add(R.drawable.modern_fairy_tales); //现代童话

        if (dataMap == null) {
            dataMap = new HashMap<>();
        }
        dataMap.clear();
        dataMap.put(0, "ye");
        dataMap.put(1, "ergs");
        dataMap.put(2, "antusheng");
        dataMap.put(3, "ysyy");
        dataMap.put(4, "sqthjc");
        dataMap.put(5, "mrgs");
        dataMap.put(6, "mjcs");
        dataMap.put(7, "klcy");
        dataMap.put(8, "cygs");
        dataMap.put(9, "xdth");
        MyAdapter adapter = new MyAdapter(this, list, dataMap);
        viewPager.setAdapter(adapter);
//        viewPager.setPageMargin((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
//                48, getResources().getDisplayMetrics()));
//        viewPager.setPageTransformer(false, new ScaleTransformer(this));
    }

    private File createFile() {
        File file = null;
        try {
            String SDPATH = Environment.getExternalStorageDirectory() + File.separator;
            String packageName = getPackageName();
            file = new File(SDPATH + "/" + packageName);
            file.mkdirs();
        } catch (Exception e) {
            e.toString();
        }
        return file;
    }


    private void initData() {
        HttpDownloader.initStrictMode();
    }


//    private void downloadMusic() {
//        String packageName = getPackageName();
//        HttpDownloader httpDownloader = new HttpDownloader();
//        int result = httpDownloader.downFile("http://media.youban.com/mv20111018/13189119141773180241.mp3",
//                packageName + "/", "a1.mp3");
////        Log.i(TAG, "result = " + result);
//        System.out.println("下载结果是：" + result);
//
//    }


    // 在sd卡上创建目录
    public File createSDDir(String dirName) {
        File dir = new File(SDPATH + dirName);
        // mkdir只能创建一级目录 ,mkdirs可以创建多级目录
        dir.mkdir();
        return dir;
    }


    // 判断sd卡上的文件夹是否存在
    public boolean isFileExist(String fileName) {
        File file = new File(SDPATH + fileName);
        return file.exists();
    }



}