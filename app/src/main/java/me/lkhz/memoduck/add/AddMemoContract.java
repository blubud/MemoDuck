package me.lkhz.memoduck.add;

import me.lkhz.memoduck.memo.repository.MemoRepository;

public interface AddMemoContract {
    interface View{
    }

    interface Presenter{
        boolean saveAlarm(String id, String content);
        void setMemoRepository(MemoRepository memoRepository);
        void attachView(View view);
        void detachView();
    }
}
