package cn.edu.gdmec.android.mynew.Movie.Model;

import cn.edu.gdmec.android.mynew.Bean.MovieBean;
import cn.edu.gdmec.android.mynew.Bean.NewsBean;
import cn.edu.gdmec.android.mynew.Http.Api;
import cn.edu.gdmec.android.mynew.Http.RetrofitHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by apple on 18/5/22.
 */

public class MovieModel implements IMovieModel {
    @Override
    public void loadMoives(final String total, final IOnLoadListener iOnLoadListener) {
        RetrofitHelper retrofitHelper = new RetrofitHelper(Api.MOVIE_HOST);
        retrofitHelper.getMovie(total)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<MovieBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iOnLoadListener.fail(e.getMessage());
                    }

                    @Override
                    public void onNext(MovieBean movieBean) {
                        iOnLoadListener.success(movieBean);
                    }
                });
    }
}
