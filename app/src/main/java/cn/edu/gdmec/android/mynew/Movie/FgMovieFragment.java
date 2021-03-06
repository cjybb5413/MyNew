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
    private LinearLayoutManager layoutManager;
    private int start=0;


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
        srl_movie.setColorSchemeColors(Color.parseColor("#ffce3d3a"));
        rv_movie_on = view.findViewById(R.id.rv_movie_hot);
        rv_movie_in = view.findViewById(R.id.rv_movie_hot1);
        movieOnAdapter = new ItemMovieOnAdapter(getActivity());
        movieInAdapter = new ItemMovieInAdapter(getActivity());
        moviesPresenter.loadMovies("in_theaters",20);
        moviesPresenter.loadMovies("top250",20);
        srl_movie.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                moviesPresenter.loadMovies("in_theaters",20);
                moviesPresenter.loadMovies("top250",20);
            }
        });
        rv_movie_on.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int movieState) {
                if (movieState==RecyclerView.SCROLL_STATE_IDLE && (layoutManager.findLastVisibleItemPosition() + 1)==layoutManager.getItemCount()){
                    loadMore();
                }
            }
        });
    }

    @Override
    public void showMovies(final MovieBean movieBean) {
        if (movieBean.getTotal() == 250) {
            movieInAdapter.setData(movieBean.getSubjects());
            rv_movie_in.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
            rv_movie_in.setAdapter(movieInAdapter);
        }else{
            movieOnAdapter.setData(movieBean.getSubjects());
            layoutManager=new LinearLayoutManager(getActivity(),
                    LinearLayoutManager.VERTICAL,false);
            rv_movie_on.setLayoutManager(layoutManager);
            rv_movie_on.setAdapter(movieOnAdapter);
        }

    }

    @Override
    public void showMoreMovie(MovieBean movieBean) {
        movieOnAdapter.addData(movieBean.getSubjects());
        movieOnAdapter.notifyDataSetChanged();
    }

    private void loadMore(){
        start+=20;
        moviesPresenter.loadMovies("in_theaters",start);
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
        movieOnAdapter.notifyItemRemoved(movieOnAdapter.getItemCount());
        Toast.makeText(getContext(),"加载出错"+error.toString(),Toast.LENGTH_SHORT).show();
    }


}