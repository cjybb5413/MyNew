package cn.edu.gdmec.android.mynew.Video;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import cn.edu.gdmec.android.mynew.Bean.TodayContentBean;
import cn.edu.gdmec.android.mynew.Bean.VideoUrlBean;
import cn.edu.gdmec.android.mynew.R;
import cn.edu.gdmec.android.mynew.Video.Presenter.IVideoPresenter;
import cn.edu.gdmec.android.mynew.Video.Presenter.VideoPresenter;
import cn.edu.gdmec.android.mynew.Video.View.IVideoView;

public class FgVideoFragment extends Fragment implements IVideoView {

    private IVideoPresenter iVideoPresenter;
    private RecyclerView rv_video;
    private ItemVideoAdapter itemVideoAdapter;
    private SwipeRefreshLayout srl_video;
    private LinearLayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fg_video,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iVideoPresenter = new VideoPresenter(this);
        rv_video = view.findViewById(R.id.rv_video);
        srl_video = view.findViewById(R.id.srl_video);
        srl_video.setColorSchemeColors(Color.parseColor("#ffce3d3a"));
        iVideoPresenter.loadVideo();
        srl_video.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                iVideoPresenter.loadVideo();
            }
        });
        itemVideoAdapter = new ItemVideoAdapter(getActivity());
    }

    @Override
    public void showVideo(List<TodayContentBean> todayContentBeans, List<String> videoList) {
        itemVideoAdapter.setData(todayContentBeans, videoList);
        layoutManager=new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL,false);
        rv_video.setLayoutManager(layoutManager);
        rv_video.setAdapter(itemVideoAdapter);
    }

    @Override
    public void showMoreVideo(List<TodayContentBean> todayContentBean, List<String> videoList) {
        itemVideoAdapter.addData(todayContentBean,videoList);
    }

    @Override
    public void hideDialog() {
        srl_video.setRefreshing(false);
    }

    @Override
    public void showDialog() {
        srl_video.setRefreshing(true);
    }

    @Override
    public void showErrorMsg(Throwable throwable) {
        itemVideoAdapter.notifyItemRemoved(itemVideoAdapter.getItemCount());
        Toast.makeText(getContext(), "加载出错:"+throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }
}