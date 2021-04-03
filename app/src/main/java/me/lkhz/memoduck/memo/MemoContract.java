package me.lkhz.memoduck.memo;

import me.lkhz.memoduck.memo.adapter.MemoAdapterContract;
import me.lkhz.memoduck.memo.repository.MemoRepository;

public interface MemoContract {

    interface View{

    }

    interface Presenter{
        void attachView(View view);
        void detachView();
        void loadMemoItems();
        void setMemoAdapterView(MemoAdapterContract.View adapterView);
        void setMemoAdapterModel(MemoAdapterContract.Model adapterModel);
        void setMemoRepository(MemoRepository memoRepository);
    }
}
