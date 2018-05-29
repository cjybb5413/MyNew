package cn.edu.gdmec.android.mynew.Movie.Model;

import cn.edu.gdmec.android.mynew.Bean.MovieBean;
import cn.edu.gdmec.android.mynew.Bean.NewsBean;

/**
 * Created by apple on 18/5/22.
 */

public interface IOnLoadListener {
    void success(MovieBean movieBean);
    void fail(String error);
}
