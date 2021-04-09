package me.lkhz.memoduck.memo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import me.lkhz.memoduck.R;
import me.lkhz.memoduck.add.AddMemoActivity;
import me.lkhz.memoduck.listener.OnListItemClickListener;
import me.lkhz.memoduck.main.MainActivity;
import me.lkhz.memoduck.main.MainInterface;
import me.lkhz.memoduck.memo.adapter.MemoAdapter;
import me.lkhz.memoduck.memo.repository.MemoRepository;
import me.lkhz.memoduck.util.AppExecutor;

public class MemoFragment extends Fragment implements MemoContract.View{

    private final MemoContract.Presenter memoPresenter;
    private RecyclerView recyclerView;
    private MemoAdapter memoAdapter;
    private MainInterface mainInterface;

    public MemoFragment(MainInterface mainInterface){
        this.mainInterface = mainInterface;
        this.memoPresenter = new MemoPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_list, container, false);
        recyclerView = rootView.findViewById(R.id.rv_list);
        setRecyclerView();
        setPresenter();

        return rootView;
    }

    private void setRecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        memoAdapter = new MemoAdapter();
        recyclerView.setAdapter(memoAdapter);
    }

    private void setPresenter(){
        memoPresenter.attachView(this);
        memoPresenter.setMemoRepository(MemoRepository.getInstance());
        memoPresenter.setMemoAdapterModel(memoAdapter);
        memoPresenter.setMemoAdapterView(memoAdapter);
        memoPresenter.setMainInterface(mainInterface);
        memoPresenter.loadMemoItems();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        memoPresenter.detachView();
    }
}
