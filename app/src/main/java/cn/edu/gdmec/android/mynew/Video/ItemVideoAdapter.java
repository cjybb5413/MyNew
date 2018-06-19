package cn.edu.gdmec.android.mynew.Video;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.edu.gdmec.android.mynew.Bean.TodayContentBean;
import cn.edu.gdmec.android.mynew.R;
import cn.jzvd.JZVideoPlayerStandard;

public class ItemVideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<TodayContentBean> objects = new ArrayList<TodayContentBean>();
    private List<String> videoList = new ArrayList<>();

    private Context context;

    public ItemVideoAdapter(Context context){
        this.context = context;
    }

    public void setData(List<TodayContentBean> objects,List<String> videoList){
        this.objects = objects;
        this.videoList = videoList;
    }
    public void addData(List<TodayContentBean> newObjects, List<String> videoList){
        objects.addAll(newObjects);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_video, parent, false);
            return new ItemVideoHolder(view);
        }else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.footer, parent, false);
            return new FooterHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemVideoHolder) {
            TodayContentBean bean = objects.get(position);
            ((ItemVideoHolder)holder).videoPlayer.setUp(videoList.get(position),
                    JZVideoPlayerStandard.SCREEN_WINDOW_LIST,
                    bean.getTitle());

            Glide.with(context)
                    .load(bean.getVideo_detail_info().getDetail_video_large_image().getUrl())
                    .into(((ItemVideoHolder)holder).videoPlayer.thumbImageView);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()){
            return 1;
        }else {
            return 0;
        }
    }

    protected class ItemVideoHolder extends RecyclerView.ViewHolder {
        private JZVideoPlayerStandard videoPlayer;
        public ItemVideoHolder(View view) {
            super(view);
            videoPlayer = view.findViewById(R.id.video_player);
        }
    }
    protected class FooterHolder extends RecyclerView.ViewHolder{

        public FooterHolder(View itemView) {
            super(itemView);
        }
    }
}
