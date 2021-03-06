package cn.edu.gdmec.android.mynew.Video.View;

import java.util.List;

import cn.edu.gdmec.android.mynew.Bean.NewsBean;
import cn.edu.gdmec.android.mynew.Bean.TodayContentBean;
import cn.edu.gdmec.android.mynew.Bean.VideoUrlBean;

/**
 * Created by apple on 18/5/22.
 */

public interface IVideoView {
    void showVideo(List<TodayContentBean> todayContentBeans, List<String> videoList);
    void showMoreVideo(List<TodayContentBean> todayContentBean, List<String> videoList);
    void hideDialog();
    void showDialog();
    void showErrorMsg(Throwable throwable);
}
