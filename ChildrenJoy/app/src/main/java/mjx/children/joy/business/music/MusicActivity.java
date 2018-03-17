package mjx.children.joy.business.music;

import android.app.Activity;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

import mjx.children.joy.R;
import mjx.children.joy.application.MainApplication;
import mjx.children.joy.common.asycntask.MyAsycnTaks;
import mjx.children.joy.data.sp.MusicInfoSp;
import mjx.children.joy.utils.HttpDownloader;
import mjx.children.joy.utils.LogUtil;

/**
 * 播放音乐
 * Created by MJX on 2018/3/16.
 */
public class MusicActivity extends Activity{

    private TextView title;
    private ImageView backTv;
    private ImageView bitBt;
    private ImageView smallBt,playBt;
    private TextView content;
    private HttpDownloader httpDownloader;
    private String readMusicPath = "";
    private ProgressDialog progressDialog;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        initView();
        initData();

    }

    private void initData() {
        try {
            mediaPlayer = new MediaPlayer();
            String story_content = getIntent().getStringExtra("story_content");
            final String story_link = getIntent().getStringExtra("story_link");
            final String story_name = getIntent().getStringExtra("story_name");
            final String title_name = getIntent().getStringExtra("title_name");
            content.setTypeface(MainApplication.getTypeFace());
            title.setTypeface(MainApplication.getTypeFace());
            title.setText(title_name);
            content.setText(story_content);
            if(httpDownloader == null){
                httpDownloader = new HttpDownloader();
            }
            new MyAsycnTaks(){

                @Override
                public void preTask() {
                    showProgress();
                }

                @Override
                public void doinBack() {
                    downloadMusic(story_link,story_name);
                }

                @Override
                public void postTask() {
                    hideProgress();
                }
            }.execute();
        }catch (Exception e){
            LogUtil.logMsg("下载音乐异常"+e.toString());
            hideProgress();
        }

    }

    private void initView() {
        title = (TextView) findViewById(R.id.common_title);
        backTv =   (ImageView) findViewById(R.id.back_image);
        bitBt = (ImageView) findViewById(R.id.big_bt);
        smallBt = (ImageView) findViewById(R.id.small_bt);
        playBt = (ImageView) findViewById(R.id.play_bt);
        content = (TextView) findViewById(R.id.story_content);
        progressDialog = new ProgressDialog(this);
        backTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        playBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(mediaPlayer != null){
                        mediaPlayer.setDataSource(MusicActivity.this, Uri.parse("file://"+readMusicPath));
                        //播放前准备一下
                        mediaPlayer.prepare();
                        //监听：准备完成的监听
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                    }
                }catch (Exception e){
                    LogUtil.logMsg("播放音乐信息异常"+e.toString());
                }
            }
        });
    }


    private void downloadMusic(String story_link,String story_name){
        String packageName = getPackageName();
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
//        if(isCheckMusicHasDownLoaded(packageName,story_name)){
//            return;
//        }

//        httpDownloader.downFile("http://media.youban.com/"+story_link, packageName + "/", story_name + ".mp3", new HttpDownloader.IDownMusicListener() {
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
        MusicInfoSp.clearMusic();
    }
}
