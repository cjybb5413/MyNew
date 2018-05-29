package cn.edu.gdmec.android.mynew.Movie.View;

import cn.edu.gdmec.android.mynew.Bean.MovieBean;
import cn.edu.gdmec.android.mynew.Bean.NewsBean;

/**
 * Created by apple on 18/5/22.
 */

public interface IMovieView {
    void showMovies(MovieBean movieBean);
    void hideDialog();
    void showDialog();
    void showErrorMsg(String error);
}
