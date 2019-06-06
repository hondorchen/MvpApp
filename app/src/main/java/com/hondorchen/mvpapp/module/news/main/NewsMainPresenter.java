package com.hondorchen.mvpapp.module.news.main;

import com.hondorchen.mvpapp.db.dao.NewsTypeDao;
import com.hondorchen.mvpapp.module.base.IBasePresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hondor on 2019/6/5
 */
public class NewsMainPresenter implements IBasePresenter {

    private INewsMainView mView;
    private NewsTypeDao mDbDao;

    public NewsMainPresenter(INewsMainView view, NewsTypeDao dao) {
        mView = view;
        mDbDao = dao;
    }


    @Override
    public void getData(boolean isRefresh) {
        mDbDao.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((list)->{mView.loadData(list);});
    }

    @Override
    public void loadMoredata() {

    }
}
