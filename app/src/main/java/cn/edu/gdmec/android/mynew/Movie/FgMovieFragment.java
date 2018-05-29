package cn.edu.gdmec.android.mynew.Movie;

import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.TextView;

import cn.edu.gdmec.android.mynew.Bean.MovieBean;
import cn.edu.gdmec.android.mynew.Bean.NewsBean;
import cn.edu.gdmec.android.mynew.Movie.Presenter.MoviePresenter;
import cn.edu.gdmec.android.mynew.Movie.View.IMovieView;
import cn.edu.gdmec.android.mynew.News.View.INewsView;
import cn.edu.gdmec.android.mynew.R;


public class FgMovieFragment extends Fragment implements IMovieView{
private TextView tv_movie;
private SwipeRefreshLayout sv;
private MoviePresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fg_movie, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_movie=view.findViewById(R.id.tv_movie);
        presenter=new MoviePresenter(this);
        sv=view.findViewById(R.id.srl_movie);
        sv.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadMovie("movie","in_theaters");
            }
        });

    }


    @Override
    public void showMovies(MovieBean movieBean) {
        tv_movie.setText(movieBean.getSubjects().get(0).getTitle()+movieBean.getSubjects().get(0).getDirectors());
    }

    @Override
    public void hideDialog() {
     sv.setRefreshing(false);
    }

    @Override
    public void showDialog() {
sv.setRefreshing(true);
    }

    @Override
    public void showErrorMsg(String error) {
tv_movie.setText("加载失败"+error);
    }
}
