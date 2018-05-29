package cn.edu.gdmec.android.mynew.Movie.Model;

/**
 * Created by apple on 18/5/22.
 */

public interface IMovieModel {
    void loadMoive(String movie,
                  String type,
                  IOnLoadListener iOnLoadListener);
}
