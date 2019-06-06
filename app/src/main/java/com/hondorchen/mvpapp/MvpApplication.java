package com.hondorchen.mvpapp;

import android.app.Application;

import com.hondorchen.mvpapp.db.AppDatabase;
import com.hondorchen.mvpapp.injector.component.ApplicationComponent;
import com.hondorchen.mvpapp.injector.component.DaggerApplicationComponent;
import com.hondorchen.mvpapp.injector.module.ApplicationModule;
import com.hondorchen.mvpapp.util.AppExecutors;

public class MvpApplication extends Application {
    private AppExecutors mAppExecutors;
    private static MvpApplication sInstance;
    private static ApplicationComponent sAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.sInstance = this;
        mAppExecutors = new AppExecutors();
        initInjector();
    }

    public static MvpApplication getInstance() {
        return sInstance;
    }


    public static ApplicationComponent getAppComponent() {
        return sAppComponent;
    }

    private void initInjector() {
        sAppComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this, getDatabase()))
                .build();
    }

    public AppDatabase getDatabase() {
        return AppDatabase.getInstance(this, mAppExecutors);
    }

    public AppExecutors getAppExecutors(){
        return  mAppExecutors;
    }
}
