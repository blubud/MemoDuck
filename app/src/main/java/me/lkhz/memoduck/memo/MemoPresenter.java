package me.lkhz.memoduck.memo;

import me.lkhz.memoduck.listener.OnListItemClickListener;
import me.lkhz.memoduck.memo.adapter.MemoAdapterContract;
import me.lkhz.memoduck.memo.repository.MemoRepository;

public class MemoPresenter implements MemoContract.Presenter, OnListItemClickListener {

    private MemoContract.View memoView;
    private MemoAdapterContract.Model adapterModel;
    private MemoAdapterContract.View adapterView;

    private MemoRepository memoRepository;

    @Override
    public void attachView(MemoContract.View view) {
        this.memoView = view;
    }

    @Override
    public void detachView() {
        this.memoView = null;
    }

    @Override
    public void loadMemoItems() {
        // Repository 에서 가져오기
        adapterModel.addItems(memoRepository.getMemoItems());
        adapterView.notifyAdapter();
    }

    @Override
    public void setMemoAdapterView(MemoAdapterContract.View adapterView) {
        this.adapterView = adapterView;
        this.adapterView.setOnClickListener(this);
    }

    @Override
    public void setMemoAdapterModel(MemoAdapterContract.Model adapterModel) {
        this.adapterModel = adapterModel;
    }

    @Override
    public void setMemoRepository(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    @Override
    public void onItemClickWithId(String listId) {

    }

    @Override
    public void onDeleteClickWithId(String listId) {

    }
}
