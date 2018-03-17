package mjx.children.joy.business.storydetail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileInputStream;
import java.util.List;

import mjx.children.joy.R;
import mjx.children.joy.application.MainApplication;
import mjx.children.joy.bean.DataBean;
import mjx.children.joy.business.music.MusicActivity;
import mjx.children.joy.data.sp.ZipInfoSp;
import mjx.children.joy.utils.LogUtil;

/**
 * 故事
 * Created by MJX on 2018/3/14.
 */
public class StoryDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<DataBean> dataList;
    private Context context;
    private LayoutInflater mLayoutInflater;
    public StoryDetailAdapter(Context context, List<DataBean> dataList){
        this.dataList = dataList;
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_story_detail, parent, false);
        return new SotryDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        SotryDetailViewHolder  mSotryDetailViewHolder = null;
        if(holder instanceof SotryDetailViewHolder){
            mSotryDetailViewHolder = (SotryDetailViewHolder)holder;
            mSotryDetailViewHolder.imageView.setImageBitmap(getBitmap(dataList.get(position).getImg()));
           mSotryDetailViewHolder.name.setTypeface(MainApplication.getTypeFace());
            mSotryDetailViewHolder.name.setText(dataList.get(position).getName());
            mSotryDetailViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(context != null){
                        Intent intent = new Intent(context, MusicActivity.class);
                        intent.putExtra("story_content",dataList.get(position).getContent());
                        intent.putExtra("story_link",dataList.get(position).getLink());
                        intent.putExtra("story_name",dataList.get(position).getImg());
                        intent.putExtra("title_name",dataList.get(position).getName());
                        context.startActivity(intent);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataList != null ? dataList.size() : 0;
    }

   public class SotryDetailViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView name;
       SotryDetailViewHolder(View view){
            super(view);
             imageView = (ImageView) view.findViewById(R.id.imageView);
             name = (TextView) view.findViewById(R.id.name);
        }
   }

    private Bitmap  getBitmap(String imgPath){
        Bitmap bitmap = null;
        try {
            imgPath =  ZipInfoSp.getPicPath()+ "/" + imgPath;
            FileInputStream fis = new FileInputStream(imgPath);
            bitmap  = BitmapFactory.decodeStream(fis);
        }catch (Exception e){
            LogUtil.logMsg("图片转化为bitmap异常"+e.toString());
        }
        return bitmap;
    }

}
