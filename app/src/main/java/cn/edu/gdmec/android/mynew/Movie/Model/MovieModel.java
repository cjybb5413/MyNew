package cn.edu.gdmec.android.mynew.Movie.Model;

import cn.edu.gdmec.android.mynew.Bean.MovieBean;
import cn.edu.gdmec.android.mynew.Bean.NewsBean;
import cn.edu.gdmec.android.mynew.Http.Api;
import cn.edu.gdmec.android.mynew.Http.RetrofitHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by apple on 18/5/22.
 */

public class MovieModel implements IMovieModel {
    @Override
    public void loadMoive(final String movie, final String type, final IOnLoadListener iOnLoadListener) {
        RetrofitHelper retrofitHelper = new RetrofitHelper(Api.MOVIE_HOST);
        retrofitHelper.getMovie(movie,type).enqueue(new Callback<MovieBean>() {
            @Override
            public void onResponse(Call<MovieBean> call, Response<MovieBean> response) {
                if (response.isSuccessful()){
                    iOnLoadListener.success(response.body());
                }else {
                    iOnLoadListener.fail("");
                }
            }

            @Override
            public void onFailure(Call<MovieBean> call, Throwable t) {
                iOnLoadListener.fail(t.toString());

            }
        });
    }
}
