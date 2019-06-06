package com.hondorchen.mvpapp.db;

import android.content.Context;

import com.hondorchen.mvpapp.db.dao.NewsTypeDao;
import com.hondorchen.mvpapp.db.entity.NewsTypeEntity;
import com.hondorchen.mvpapp.util.AppExecutors;
import com.hondorchen.mvpapp.util.AssetsHelper;
import com.hondorchen.mvpapp.util.GsonHelper;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * Created by hondor on 2019/6/5
 * 数据库
 */
@Database(entities = {NewsTypeEntity.class},version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase sInstance;
    @VisibleForTesting
    public static final String DATABASE_NAME = "mvpdb";

    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();
    public abstract NewsTypeDao NewsTypeDao();

    public static AppDatabase getInstance(final Context context, final AppExecutors executors) {
        if (sInstance == null) {
            synchronized (AppDatabase.class) {
                if (sInstance == null) {
                    sInstance = buildDatabase(context.getApplicationContext(), executors);
                    sInstance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    /**
     * Build the database. {@link Builder#build()} only sets up the database configuration and
     * creates a new instance of the database.
     * The SQLite database is only created when it's accessed for the first time.
     */
    private static AppDatabase buildDatabase(final Context appContext,
                                             final AppExecutors executors) {
        return Room.databaseBuilder(appContext, AppDatabase.class, DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        executors.diskIO().execute(() -> {
                            // Add a delay to simulate a long-running operation
                            //addDelay();
                            // Generate the data for pre-population
                            AppDatabase database = AppDatabase.getInstance(appContext, executors);
                            List<NewsTypeEntity> NewsTypes = GsonHelper.convertEntities(AssetsHelper.readData(appContext, "NewsChannel"), NewsTypeEntity.class);
                            insertData(database, NewsTypes.subList(0, 3));
                            // notify that the database was created and it's ready to be used
                            database.setDatabaseCreated();
                        });
                    }
                })
                .build();
    }

    /**
     * Check whether the database already exists and expose it via {@link #getDatabaseCreated()}
     */
    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated();
        }
    }


    private static void insertData(final AppDatabase database, final List<NewsTypeEntity> NewsTypes) {
        database.runInTransaction(() -> {
            database.NewsTypeDao().insertAll(NewsTypes);
        });
    }

    public LiveData<Boolean> getDatabaseCreated() {
        return mIsDatabaseCreated;
    }

    private void setDatabaseCreated(){
        mIsDatabaseCreated.postValue(true);
    }

}


