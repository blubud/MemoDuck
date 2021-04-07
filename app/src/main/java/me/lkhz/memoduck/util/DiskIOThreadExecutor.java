package me.lkhz.memoduck.util;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DiskIOThreadExecutor implements Executor {
    private final Executor diskIOExecutor;

    public DiskIOThreadExecutor(){
        this.diskIOExecutor = Executors.newSingleThreadExecutor();
    }

    @Override
    public void execute(Runnable runnable) {
        diskIOExecutor.execute(runnable);
    }
}
