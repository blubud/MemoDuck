package me.lkhz.memoduck.memo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import me.lkhz.memoduck.R;
import me.lkhz.memoduck.listener.OnListItemClickListener;
import me.lkhz.memoduck.memo.adapter.holder.MemoViewHolder;
import me.lkhz.memoduck.memo.repository.memo.MemoItem;
import me.lkhz.memoduck.util.AppExecutor;

public class MemoAdapter extends RecyclerView.Adapter<MemoViewHolder> implements MemoAdapterContract.Model, MemoAdapterContract.View{
    private List<MemoItem> memoItems = new ArrayList<>();
    private OnListItemClickListener onListItemClickListener;
    private final AppExecutor appExecutor;

    public MemoAdapter() {
        this.appExecutor = AppExecutor.getInstance();
    }

    @NonNull
    @Override
    public MemoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_memo_item, parent, false);
        return new MemoViewHolder(v, onListItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MemoViewHolder holder, int position) {
        holder.onBind(memoItems.get(position), position);
    }

    @Override
    public int getItemCount() {
        return memoItems.size();
    }

    @Override
    public void notifyAdapter() {
        appExecutor.mainThread().execute(new Runnable() {
            @Override
            public void run() {
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public void setOnClickListener(OnListItemClickListener onClickListener) {
        this.onListItemClickListener = onClickListener;
    }

    @Override
    public void addItems(List<MemoItem> items) {
        if(this.memoItems == null){
            this.memoItems = new ArrayList<>();
        }

        if(items != null){
            this.memoItems.addAll(items);
        }
    }

    @Override
    public MemoItem getItem(int position) {
        if(this.memoItems == null){
            return null;
        }
        return this.memoItems.get(position);
    }
}
