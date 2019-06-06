package com.hondorchen.mvpapp.module.base;

/**
 * Created by hondor on 2019/6/5
 */
public interface IBasePresenter {

    /**
     * 获取数据
     * @param isRefresh 判断是刷新数据还是新加载数据，如果刷新数据时数据异常则显示异常界面，而新加载数据则弹出异常提醒
     */
    void getData(boolean isRefresh);

    /**
     * 上拉加载更多数据
     */
    void loadMoredata();
}
