package com.hondorchen.mvpapp.injector.component;

import com.hondorchen.mvpapp.injector.PerFragment;
import com.hondorchen.mvpapp.injector.module.NewsMainModule;
import com.hondorchen.mvpapp.module.news.main.NewsMainFragment;

import dagger.Component;

/**
 * Created by long on 2016/12/20.
 * 主页 Component
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = NewsMainModule.class)
public interface NewsMainComponent {
    void inject(NewsMainFragment fragment);
}
