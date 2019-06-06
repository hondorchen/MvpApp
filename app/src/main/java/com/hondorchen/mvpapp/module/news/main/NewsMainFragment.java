package com.hondorchen.mvpapp.module.news.main;

import com.google.android.material.tabs.TabLayout;
import com.hondorchen.mvpapp.MvpApplication;
import com.hondorchen.mvpapp.R;
import com.hondorchen.mvpapp.adapter.ViewPagerAdapter;
import com.hondorchen.mvpapp.db.entity.NewsTypeEntity;
import com.hondorchen.mvpapp.injector.component.DaggerNewsMainComponent;
import com.hondorchen.mvpapp.injector.module.NewsMainModule;
import com.hondorchen.mvpapp.module.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

/**
 * Created by hondor on 2019/6/5
 */
public class NewsMainFragment extends BaseFragment<NewsMainPresenter> implements INewsMainView {


    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @Inject
    ViewPagerAdapter mPagerAdapter;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_news_main;
    }

    @Override
    protected void initViews() {
        initInjector();
        initToolBar(mToolBar, true, "新闻");
        mPresenter.getData(false);
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initInjector() {
        DaggerNewsMainComponent.builder()
                .applicationComponent(MvpApplication.getAppComponent())
                .newsMainModule(new NewsMainModule(this))
                .build();
    }

    @Override
    public void loadData(List<NewsTypeEntity> checkList) {
        List<Fragment> fragments = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        for (NewsTypeEntity bean : checkList) {
            titles.add(bean.getName());
            //fragments.add(NewsListFragment.newInstance(bean.getTypeId()));
        }
        //mPagerAdapter.setItems(fragments, titles);
    }
}
