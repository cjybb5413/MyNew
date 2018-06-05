package cn.edu.gdmec.android.mynew.Movie;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import cn.edu.gdmec.android.mynew.Bean.MovieBean;
import cn.edu.gdmec.android.mynew.Movie.Presenter.MoviePresenter;
import cn.edu.gdmec.android.mynew.Movie.View.IMovieView;
import cn.edu.gdmec.android.mynew.R;

public class FgMovieFragment extends Fragment implements IMovieView {

    private MoviePresenter moviesPresenter;
    private RecyclerView rv_movie_on;
    private RecyclerView rv_movie_in;
    private SwipeRefreshLayout srl_movie;
    private ItemMovieOnAdapter movieOnAdapter;
    private ItemMovieInAdapter movieInAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fg_movie, null);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        moviesPresenter = new MoviePresenter(this);
        srl_movie = view.findViewById(R.id.srl_movie);
        rv_movie_on = view.findViewById(R.id.rv_movie_hot);
        rv_movie_in = view.findViewById(R.id.rv_movie_hot1);
        movieOnAdapter = new ItemMovieOnAdapter(getActivity());
        movieInAdapter = new ItemMovieInAdapter(getActivity());
        moviesPresenter.loadMovie("movie","top250");
        srl_movie.setColorSchemeColors(Color.parseColor("#ffce3d3a"));
        moviesPresenter.loadMovie("movie","in_theaters");
        srl_movie.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                moviesPresenter.loadMovie("movie","in_theaters");
                moviesPresenter.loadMovie("movie","top250");
            }
        });
    }

    @Override
    public void showMovies(MovieBean movieBean) {
        if (movieBean.getTotal()==29) {
            movieOnAdapter.setData(movieBean.getSubjects());
            rv_movie_on.setLayoutManager(new LinearLayoutManager(getActivity()));
            rv_movie_on.setAdapter(movieOnAdapter);
        }else if (movieBean.getTotal()==250){
            movieInAdapter.setData(movieBean.getSubjects());
            rv_movie_in.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayout.HORIZONTAL,false));
            rv_movie_in.setAdapter(movieInAdapter);
        }
    }

    @Override
    public void hideDialog() {
        srl_movie.setRefreshing(false);
    }

    @Override
    public void showDialog() {
        srl_movie.setRefreshing(true);
    }

    @Override
    public void showErrorMsg(String error) {
        Toast.makeText(getContext(),"加载出错"+error.toString(),Toast.LENGTH_SHORT).show();
    }


}