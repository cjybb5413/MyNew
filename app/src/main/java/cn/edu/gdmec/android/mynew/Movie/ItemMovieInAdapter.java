package cn.edu.gdmec.android.mynew.Movie;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.edu.gdmec.android.mynew.ADetailActivity;
import cn.edu.gdmec.android.mynew.Bean.MovieBean;
import cn.edu.gdmec.android.mynew.R;

/**
 * Created by apple on 18/6/5.
 */

public class ItemMovieInAdapter extends RecyclerView.Adapter<ItemMovieInAdapter.ViewHolder>{
    private List<MovieBean.SubjectsBean> objects = new ArrayList<MovieBean.SubjectsBean>();

    private Context context;

    private LayoutInflater layoutInflater;

    public ItemMovieInAdapter(Context context) {

        this.context = context;
        this.layoutInflater=LayoutInflater.from(context);
    }
    public void setData(List<MovieBean.SubjectsBean> objects){
        this.objects = objects;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie_in, parent, false);
        return new ItemMovieInAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MovieBean.SubjectsBean bean=objects.get(position);
        if (bean==null){
            return;
        }
        Glide.with(context)
                .load(bean.getImages().getSmall())
                .into(holder.ivMovieIn);
        holder.tvMovieInTitle.setText(bean.getTitle());

        holder.rlMovieIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ADetailActivity.class);
                intent.putExtra("url",bean.getAlt());
                intent.putExtra("title", bean.getTitle());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivMovieIn;
        private TextView tvMovieInTitle;
        private LinearLayout rlMovieIn;

        public ViewHolder(View view) {
            super(view);
            ivMovieIn = view.findViewById(R.id.iv_movie_in);
            tvMovieInTitle = view.findViewById(R.id.tv_movie_in_title);
            rlMovieIn = view.findViewById(R.id.rl_movie_in);
        }
    }
}
