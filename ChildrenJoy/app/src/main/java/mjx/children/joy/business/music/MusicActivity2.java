package mjx.children.joy.business.music;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

import mjx.children.joy.R;
import mjx.children.joy.application.MainApplication;
import mjx.children.joy.base.BaseActivity;
import mjx.children.joy.data.sp.MusicInfoSp;
import mjx.children.joy.utils.HttpDownloader;
import mjx.children.joy.utils.LogUtil;
import mjx.children.joy.utils.text.CheckTextUtil;
import mjx.children.joy.utils.toast.ToastUtil;
import mjx.children.joy.utils.ui.UIUtil;

/**
 * 播放音乐
 * Created by MJX on 2018/3/16.
 */
public class MusicActivity2 extends BaseActivity{

    private TextView title;
    private ImageView backTv;
    private ImageView bitBt;
    private ImageView smallBt,playBt;
    private TextView content;
    private HttpDownloader httpDownloader;
    private String readMusicPath = "";
    private ProgressDialog progressDialog;
    private MediaPlayer mediaPlayer;
    private boolean isPlayingMusic = false;
    private int max_size_font = 30;
    private int min_size_font = 24;
    private int default_size_font = 24;
    //展示的背景播放图，true播放，false暂停
    private boolean isShowBg = false;
    private CheckTextUtil mCheckTextUtil;

    private void initData() {
        try {
            mediaPlayer = new MediaPlayer();
            String story_content = getIntent().getStringExtra("story_content");
            final String story_link = getIntent().getStringExtra("story_link");
            final String story_name = getIntent().getStringExtra("story_name");
            final String title_name = getIntent().getStringExtra("title_name");
            story_content  = story_content.replaceAll("[a-zA-Z]","" );
            story_content =  mCheckTextUtil.delUnavliableText(story_content);
            content.setTypeface(MainApplication.getTypeFace());
            title.setTypeface(MainApplication.getTypeFace());
            title.setText(title_name);
            content.setText(story_content);

//            mediaPlayer = new MediaPlayer();
//            mediaPlayer.setDataSource("http://media.youban.com/"+story_link);
//            mediaPlayer.prepare(); // 准备(File), 同步

            mediaPlayer.setDataSource("http://media.youban.com/"+story_link); // 设置数据源为网络文件
            mediaPlayer.prepareAsync(); // 准备(InputStream), 异步
            showProgress();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    // 准备完成后, 开始播放音频文件
                    mp.start();
                    hideProgress();
                }
            });





//            if(httpDownloader == null){
//                httpDownloader = new HttpDownloader();
//            }
//            new MyAsycnTaks(){
//
//                @Override
//                public void preTask() {
//                    showProgress();
//                }
//
//                @Override
//                public void doinBack() {
//                    downloadMusic(story_link,story_name);
//                }
//
//                @Override
//                public void postTask() {
//                    hideProgress();
//                }
//            }.execute();
        }catch (Exception e){
           LogUtil.logMsg("下载音乐异常"+e.toString());
//           hideProgress();
      }

    }

    private void initView() {
        title = (TextView) mView.findViewById(R.id.common_title);
        backTv =   (ImageView) mView.findViewById(R.id.back_image);
        bitBt = (ImageView) mView.findViewById(R.id.big_bt);
        smallBt = (ImageView) mView.findViewById(R.id.small_bt);
        playBt = (ImageView) mView.findViewById(R.id.play_bt);
        content = (TextView) mView.findViewById(R.id.story_content);
        progressDialog = new ProgressDialog(this);
        backTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        playBt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(isPlayingMusic){
//                    return;
//                }
//                if(isShowBg){
//                    playBt.setBackgroundResource(R.drawable.ic_play_normal);
//                }else{
//                    playBt.setBackgroundResource(R.drawable.ic_pause_normal);
//                }
//                isShowBg = !isShowBg;
//                try {
//                    if(mediaPlayer == null){
//                        mediaPlayer = new MediaPlayer();
//                    }
//                    if(mediaPlayer != null){
//                        isPlayingMusic = true;
//                        mediaPlayer.setDataSource(MusicActivity2.this, Uri.parse("file://"+readMusicPath));
//                        // 通过异步的方式装载媒体资源
//                        mediaPlayer.prepareAsync();
//                        //监听：准备完成的监听
//                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                            @Override
//                            public void onPrepared(MediaPlayer mediaPlayer) {
//                                mediaPlayer.start();
//                            }
//                        });
//
//                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                            @Override
//                            public void onCompletion(MediaPlayer mediaPlayer) {
//                                if (mediaPlayer != null) {
//                                    mediaPlayer.stop();
//                                    mediaPlayer.release();
//                                    isPlayingMusic = false;
//                                }
//                                setCheckPauseBg();
//                            }
//                        });
//                        setCheckPlayingBg();
//                    }
//                }catch (Exception e){
//                    LogUtil.logMsg("播放音乐信息异常"+e.toString());
//                }
//            }
//        });


        playBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        bitBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkSizeFont(2);
            }
        });

        smallBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkSizeFont(-2);
            }
        });
    }

    @Override
    public View subView() {
        return UIUtil.getLayoutInflaterView(R.layout.activity_music);
    }

    @Override
    public void initSubData() {
        mCheckTextUtil = new CheckTextUtil();
        initData();
    }

    @Override
    public void initSubView() {
        initView();
    }


    private void downloadMusic(String story_link,String story_name){
        if(story_name != null && !"".equals(story_name)){
            story_name = story_name.replace(".",",");
            if(story_name.contains(",")){
                String[] split = story_name.split(",");
                if(split.length > 1){
                    story_name = split[0];
                }else{
                    story_name = "12345";
                }
            }else {
                story_name = "12345";
            }
        }else{
            story_name = "12345";
        }
        httpDownloader.downFile("http://media.youban.com/"+story_link, MusicInfoSp.getMusicPath(), story_name + ".mp3", new HttpDownloader.IDownMusicListener() {
            @Override
            public void downMusicSuccess(String musicPath) {
                readMusicPath = musicPath;
            }

            @Override
            public void downMusicError(String error) {
                LogUtil.logMsg(error);
            }
        });
    }

    private void showProgress(){
        if(progressDialog != null){
            progressDialog.show();
        }
    }

    private void hideProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    /**
     * 校验本次的音乐是否下载过
     * @param name
     * @return
     */
    private boolean isCheckMusicHasDownLoaded(String packageName,String name){
        boolean hasDownLoad = false;
        File f = new File(packageName);
        if (!f.exists()) {
            return false;
        }
        File fa[] = f.listFiles();
        for (int i = 0; i < fa.length; i++) {
            File fs = fa[i];
            if (fs.isDirectory()) {
                System.out.println(fs.getName() + " [目录]");
            } else {
                System.out.println(fs.getName());
                if(name.equals(fs.getName())){
                    hasDownLoad = true;
                    break;
                }
            }
        }
        return  hasDownLoad;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        try{
            MusicInfoSp.clearMusic();
            if (mediaPlayer != null) {
                mediaPlayer.release();
                mediaPlayer = null;
            }
        }catch (Exception e){
            LogUtil.logMsg("关闭播放音乐界面异常"+e.toString());
        }
    }

    private void setCheckPlayingBg(){
        if(playBt != null){
            playBt.setImageResource(R.drawable.ic_pause_normal);
        }
    }

    private void setCheckPauseBg(){
        if(playBt != null){
            playBt.setImageResource(R.drawable.ic_play_normal);
        }
    }

    private void checkSizeFont(int countNum){
        if(countNum > 0 && default_size_font + countNum > max_size_font){
            ToastUtil.toastDes("已经最大字体了");
            return;
        }

        if(countNum < 0 &&  default_size_font + countNum < min_size_font){
            ToastUtil.toastDes("已经最小字体了");
            return;
        }
        default_size_font = default_size_font + countNum;
        content.setTextSize(default_size_font);
    }
}
