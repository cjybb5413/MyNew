package cn.edu.gdmec.android.mynew.Http;

import cn.edu.gdmec.android.mynew.Bean.MovieBean;
import cn.edu.gdmec.android.mynew.Bean.NewsBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by apple on 18/5/22.
 */

public interface RetrofitMovieService {
    @GET("v2/{movie}/{type}")
    Call<MovieBean> getMovies(@Path("movie") String movie,
                              @Path("type") String type);
}
