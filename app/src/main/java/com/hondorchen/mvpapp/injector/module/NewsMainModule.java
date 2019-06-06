package com.hondorchen.mvpapp.injector.module;

import com.hondorchen.mvpapp.adapter.ViewPagerAdapter;
import com.hondorchen.mvpapp.db.AppDatabase;
import com.hondorchen.mvpapp.injector.PerFragment;
import com.hondorchen.mvpapp.module.news.main.NewsMainFragment;
import com.hondorchen.mvpapp.module.news.main.NewsMainPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hondor on 2019/6/5
 */
@Module
public class NewsMainModule {
    private final NewsMainFragment mView;

    public NewsMainModule(NewsMainFragment view) {
        mView = view;
    }

    @PerFragment
    @Provides
    public NewsMainPresenter provideMainPresenter(AppDatabase db) {
        return new NewsMainPresenter(mView, db.NewsTypeDao());
    }

    @PerFragment
    @Provides
    public ViewPagerAdapter provideViewPagerAdapter() {
        return new ViewPagerAdapter(mView.getChildFragmentManager());
    }

}
