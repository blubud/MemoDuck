package me.lkhz.memoduck.util;


import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import java.util.concurrent.Executor;

import me.lkhz.memoduck.memo.repository.MemoRepository;
import me.lkhz.memoduck.memo.repository.memo.MemoDAO;

public class AppExecutor {

    private final Executor mainThread;
    private final Executor diskIO;

    private static AppExecutor INSTANCE = null;

    public static void makeInstance(){
        if(INSTANCE == null){
            synchronized (AppExecutor.class){
                if(INSTANCE == null){
                    INSTANCE = new AppExecutor();
                }
            }
        }
    }
    public static AppExecutor getInstance(){
        return INSTANCE;
    }

    private AppExecutor(Executor diskIO, Executor mainThread) {
        this.diskIO = diskIO;
        this.mainThread = mainThread;
    }

    public AppExecutor(){
        this(new DiskIOThreadExecutor(), new MainThreadExecutor());
    }

    public Executor mainThread() {
        return mainThread;
    }

    public Executor diskIO() {
        return diskIO;
    }

    private static class MainThreadExecutor implements Executor {
        private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }
}
