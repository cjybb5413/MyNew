package cn.edu.gdmec.android.mynew.News;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.edu.gdmec.android.mynew.News.Presenter.NewsPresenter;
import cn.edu.gdmec.android.mynew.R;

/**
 * Created by apple on 18/5/22.
 */

public class FgNewsListFragment extends Fragment{
    private int type;
    private TextView tv_news;
    private NewsPresenter newsPresenter;
    private SwipeRefreshLayout srl_news;

    public static FgNewsListFragment newsInstance(int type){
        Bundle args = new Bundle();
        FgNewsListFragment fragment = new FgNewsListFragment();
        args.putInt("type",type);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fg_news_list,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        type = getArguments().getInt("type");
        tv_news = view.findViewById(R.id.tv_news);
        switch (type){
            case FgNewsFragment.NEWS_TYPE_TOP:
                tv_news.setText("top");
                break;
            case FgNewsFragment.NEWS_TYPE_NBA:
                tv_news.setText("nba");
                break;
            case FgNewsFragment.NEWS_TYPE_JOKES:
                tv_news.setText("joke");
                break;
        }
    }
}
