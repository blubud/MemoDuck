package me.lkhz.memoduck.memo.adapter;

import java.util.List;

import me.lkhz.memoduck.listener.OnListItemClickListener;
import me.lkhz.memoduck.memo.repository.memo.MemoItem;

public interface MemoAdapterContract {
    interface View{
        void notifyAdapter();
        void setOnClickListener(OnListItemClickListener onClickListener);
    }

    interface Model{
        void addItems(List<MemoItem> items);
        MemoItem getItem(int position);
    }
}
