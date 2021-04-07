package me.lkhz.memoduck.memo.repository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import me.lkhz.memoduck.memo.repository.memo.MemoDAO;
import me.lkhz.memoduck.memo.repository.memo.MemoItem;
import me.lkhz.memoduck.util.AppExecutor;

public class MemoRepository implements MemoSource {

    private MemoDAO memoDao;
    private AppExecutor appExecutor;

    private static MemoRepository INSTANCE = null;
    private MemoRepository(MemoDAO memoDao, AppExecutor appExecutor){
        this.memoDao = memoDao;
        this.appExecutor = appExecutor;
    }

    public static void makeInstance(MemoDAO memoDao){
        if(INSTANCE == null){
            synchronized (MemoRepository.class){
                if(INSTANCE == null){
                    INSTANCE = new MemoRepository(memoDao, new AppExecutor());
                }
            }
        }
    }
    public static MemoRepository getInstance(){
        return INSTANCE;
    }

    @Override
    public void getMemoItems(LoadAlarmCallback callback) {
        Runnable runnable = () -> {
            final List<MemoItem> tmpItems = memoDao.selectAllMemo();
            ArrayList<MemoItem> items = new ArrayList<>(tmpItems);

            if(items.isEmpty()){
                items = new ArrayList<>();
            }
            callback.onLoadAlarm(items);
        };
        appExecutor.diskIO().execute(runnable);
    }

    @Override
    public void insertMemoItem(MemoItem memoItem) {
        Runnable runnable = () -> memoDao.insertMemo(memoItem);
        appExecutor.diskIO().execute(runnable);
    }

    @Override
    public void updateMemoItem(MemoItem memoItem) {
        Runnable runnable = () -> memoDao.updateMemo(memoItem.getMemoId(), memoItem.getFullContent());
        appExecutor.diskIO().execute(runnable);
    }

    @Override
    public void deleteMemo(String id) {
        Runnable runnable = () -> memoDao.deleteMemo(id);
        appExecutor.diskIO().execute(runnable);
    }
}
