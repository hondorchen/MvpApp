package com.hondorchen.mvpapp.module.base;

/**
 * Created by hondor on 2019/6/5
 * View基础接口
 */
public interface IBaseView {

    /**
     * 显示加载动画
     */
    void showLoading();

    /**
     * 隐藏加载动画
     */
    void hindLoading();

    /**
     * 显示网络错误
     */
    void showNetError();

    /**
     * 显示空数据
     */
    void showEmpty();


}
