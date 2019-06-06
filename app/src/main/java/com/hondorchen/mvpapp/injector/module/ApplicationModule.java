package com.hondorchen.mvpapp.injector.module;

import android.content.Context;

import com.hondorchen.mvpapp.MvpApplication;
import com.hondorchen.mvpapp.db.AppDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by long on 2016/8/19.
 * Application Module
 */
@Module
public class ApplicationModule {

    private final MvpApplication mApplication;
    private final AppDatabase mAppDatabase;

    public ApplicationModule(MvpApplication application, AppDatabase AppDatabase) {
        mApplication = application;
        mAppDatabase = AppDatabase;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return mApplication;
    }


    @Provides
    @Singleton
    AppDatabase provideAppDatabase() {
        return mAppDatabase;
    }
}
