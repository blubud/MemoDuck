package me.lkhz.memoduck.memo.repository.lcoal;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import me.lkhz.memoduck.memo.repository.MemoItem;

@Database(entities = {MemoItem.class}, version = 3, exportSchema = false)
public abstract class MemoDatabase extends RoomDatabase {
    private static MemoDatabase INSTANCE;

    public abstract MemoDAO memoDAO();

    private static final Object sLock = new Object();

    public static MemoDatabase getInstance(Context context){
        synchronized (sLock){
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        MemoDatabase.class, "memoduck.db")
                        .fallbackToDestructiveMigration()
                        .build();
            }
            return INSTANCE;
        }
    }
    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
}
