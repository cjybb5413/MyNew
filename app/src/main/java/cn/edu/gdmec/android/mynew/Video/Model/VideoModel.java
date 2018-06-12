package cn.edu.gdmec.android.mynew.Video.Model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cn.edu.gdmec.android.mynew.Bean.TodayBean;
import cn.edu.gdmec.android.mynew.Bean.TodayContentBean;
import cn.edu.gdmec.android.mynew.Bean.VideoUrlBean;
import cn.edu.gdmec.android.mynew.Bean.WeatherBean;
import cn.edu.gdmec.android.mynew.Http.Api;
import cn.edu.gdmec.android.mynew.Http.RetrofitHelper;
import cn.edu.gdmec.android.mynew.Video.Presenter.VideoPresenter;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by apple on 18/5/22.
 */

public class VideoModel implements IVideoModel {
    private Integer[] city={101280101,101280102,101280103,101280104,101280105, 101280201,101280202,101280203,101280204,101280205,101280206, 101280207,101280208,101280501};

    @Override
    public void loadVideo(String category, final IVideoLoadListener iVideoLoadListener) {
        final List<VideoUrlBean> videoList = new ArrayList<>();
        final List<TodayContentBean> contentBeans = new ArrayList<>();
        final RetrofitHelper retrofitHelper = new RetrofitHelper(Api.TODAY_HOST);
        retrofitHelper.getToday(category)
                .flatMap(new Func1<TodayBean, Observable<VideoUrlBean>>() {
                    @Override
                    public Observable<VideoUrlBean> call(TodayBean todayBean){
                        return Observable.from(todayBean.getData())
                                .flatMap(new Func1<TodayBean.DataBean, Observable<VideoUrlBean>>() {
                    @Override
                    public Observable<VideoUrlBean> call(TodayBean.DataBean dataBean) {
                        String content = dataBean.getContent();
                        TodayContentBean contentBean = VideoPresenter.getTodayContentBean(content);
                        contentBeans.add(contentBean);
                        String api = VideoPresenter.getVideoContentApi(contentBean.getVideo_id());
                        return retrofitHelper.getVideoUrl(api);
                    }
                });
    }
})
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<VideoUrlBean>() {
                    @Override
                    public void onCompleted() {
                        iVideoLoadListener.videoUrlSuccess(videoList,contentBeans);
                    }

                    @Override
                    public void onError(Throwable e) {
                      iVideoLoadListener.fail(e);
                    }

                    @Override
                    public void onNext(VideoUrlBean videoUrlBean) {
                      videoList.add(videoUrlBean);
                    }
                });
    }

    @Override
    public void loadWeather() {
        final RetrofitHelper retrofitHelper = new RetrofitHelper(Api.WEATHER_HOST);
        Observable.from(city).flatMap(new Func1<Integer, Observable<WeatherBean>>() {
            @Override
            public Observable<WeatherBean> call(Integer integer) {
                return retrofitHelper.getWeather(integer);
            }
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<WeatherBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(WeatherBean weatherBean) {
                        Log.d("aa",weatherBean.getData().getCity()+":"+weatherBean.getData().getGanmao());
                    }
                });
    }
}
