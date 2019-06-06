package com.hondorchen.mvpapp.injector.component;

import android.content.Context;

import com.hondorchen.mvpapp.db.AppDatabase;
import com.hondorchen.mvpapp.injector.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by long on 2016/8/19.
 * Application Component
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

//    void inject(BaseActivity baseActivity);

    // provide
    Context getContext();
    AppDatabase getAppDatabase();
}
