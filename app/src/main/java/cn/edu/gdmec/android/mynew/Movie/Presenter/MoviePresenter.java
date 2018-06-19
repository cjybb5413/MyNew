package cn.edu.gdmec.android.mynew.Movie.Presenter;

import cn.edu.gdmec.android.mynew.Bean.MovieBean;
import cn.edu.gdmec.android.mynew.Movie.Model.IMovieModel;
import cn.edu.gdmec.android.mynew.Movie.Model.IOnMovieListener;
import cn.edu.gdmec.android.mynew.Movie.Model.MovieModel;
import cn.edu.gdmec.android.mynew.Movie.View.IMovieView;


/**
 * Created by apple on 18/5/22.
 */

public class MoviePresenter implements IMoviePresenter,IOnMovieListener {
    private IMovieModel iMovieModel;
    private IMovieView iMovieView;

    public MoviePresenter(IMovieView iMovieView) {
        this.iMovieView = iMovieView;
        this.iMovieModel = new MovieModel();
    }

    @Override
    public void success(MovieBean movieBean) {
        iMovieView.hideDialog();
        if (movieBean != null) {
            iMovieView.showMovies(movieBean);
        }
    }

    @Override
    public void fail(String error) {
        iMovieView.hideDialog();
        iMovieView.showErrorMsg(error);
    }

    @Override
    public void loadMoreSuccess(MovieBean movieBean) {
        iMovieView.hideDialog();
        iMovieView.showMoreMovie(movieBean);
    }

    @Override
    public void loadMovie(String total,int start) {
        if (start==0) {
            iMovieView.showDialog();
            iMovieModel.loadMoives(total, start, this);
        }
    }
}
