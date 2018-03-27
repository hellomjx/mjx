package mjx.children.joy.business.storydetail;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import mjx.children.joy.R;
import mjx.children.joy.application.MainApplication;
import mjx.children.joy.base.BaseActivity;
import mjx.children.joy.bean.DataBean;
import mjx.children.joy.common.asycntask.MyAsycnTaks;
import mjx.children.joy.data.XmlData;
import mjx.children.joy.utils.ui.UIUtil;

/**
 * 儿童故事
 * Created by MJX on 2018/3/13.
 */
public class StoryDetailActivity extends BaseActivity {
    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private List<DataBean> dataList;
    private ImageView back;
    private TextView commonTitle;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_story_detail);
//        initView();
//        initData();
//    }

    private void initView(){
        recyclerView = (RecyclerView) mView.findViewById(R.id.recyclerview);
        commonTitle = (TextView) mView.findViewById(R.id.common_title);
        back = (ImageView) mView.findViewById(R.id.back_image);
        layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        commonTitle.setTypeface(MainApplication.getTypeFace());
    }

    @Override
    public View subView() {
        return UIUtil.getLayoutInflaterView(R.layout.activity_story_detail);
    }

    @Override
    public void initSubData() {
        initData();
    }

    @Override
    public void initSubView() {
        initView();
    }

    public void initData(){
       final String stoty_name = getIntent().getStringExtra("stoty_name");
        new MyAsycnTaks(){

            @Override
            public void preTask() {

            }

            @Override
            public void doinBack() {
                 dataList = XmlData.getData(stoty_name+".xml");
            }

            @Override
            public void postTask() {
                setData();
            }
        }.execute();
    }


    private void setData(){
        StoryDetailAdapter storyDetailAdapter = new StoryDetailAdapter(this, dataList);
        recyclerView.setAdapter(storyDetailAdapter);
    }
}
