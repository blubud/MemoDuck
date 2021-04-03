package me.lkhz.memoduck.memo.adapter;

import java.util.ArrayList;
import java.util.List;

import me.lkhz.memoduck.listener.OnListItemClickListener;
import me.lkhz.memoduck.memo.repository.MemoItem;

public interface MemoAdapterContract {
    interface View{
        void notifyAdapter();
        void setOnClickListener(OnListItemClickListener onClickListener);
    }

    interface Model{
        void addItems(List<MemoItem> items);
        void refreshItems(List<MemoItem> items);
        void addItem(MemoItem item);
        void editItem(MemoItem item);
    }
}
