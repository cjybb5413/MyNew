package cn.edu.gdmec.android.mynew.Video.Model;

import java.util.List;
import cn.edu.gdmec.android.mynew.Bean.TodayContentBean;
import cn.edu.gdmec.android.mynew.Bean.VideoUrlBean;

/**
 * Created by apple on 18/5/22.
 */

public interface IVideoLoadListener {
    void videoUrlSuccess(List<VideoUrlBean> videoUrlBeans, List<TodayContentBean> contentBeans);
    void fail(Throwable throwable);
    void loadMoreVideo(List<TodayContentBean> contentBeans,List<String> videoList);
}
