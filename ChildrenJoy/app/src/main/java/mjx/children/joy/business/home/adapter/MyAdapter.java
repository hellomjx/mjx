package mjx.children.joy.business.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.List;
import java.util.Map;

import mjx.children.joy.R;
import mjx.children.joy.business.storydetail.StoryDetailActivity;

/**
 *
 * Created by MJX on 2018/3/7.
 */
public class MyAdapter extends PagerAdapter {
    private List<Integer> list;
    private Context context;
    private LayoutInflater inflater;
    private Map<Integer,String> dataMap;
    public MyAdapter(Context context, List<Integer> list,Map<Integer,String> dataMap) {
        this.context = context;
        this.list = list;
        this.dataMap = dataMap;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = inflater.inflate(R.layout.home_item, container, false);
        RelativeLayout home_click_rl = (RelativeLayout) view.findViewById(R.id.home_click_rl);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setBackgroundResource(list.get(position));
        home_click_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,StoryDetailActivity.class);
                intent.putExtra("stoty_name",dataMap.get(position));
                context.startActivity(intent);
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
