package me.lkhz.memoduck.memo.adapter.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import me.lkhz.memoduck.R;
import me.lkhz.memoduck.listener.OnListItemClickListener;
import me.lkhz.memoduck.memo.repository.MemoItem;

public class MemoViewHolder extends RecyclerView.ViewHolder {

    private TextView titleTextView;
    private ImageView deleteImageView;
    private OnListItemClickListener onItemClickListener;
    private ConstraintLayout container;

    public MemoViewHolder(View itemView, OnListItemClickListener onItemClickListener){
        super(itemView);
        this.titleTextView = itemView.findViewById(R.id.tv_memo_title);
        this.deleteImageView = itemView.findViewById(R.id.iv_memo_delete);
        this.container = itemView.findViewById(R.id.cl_memo_item);
        this.onItemClickListener = onItemClickListener;
    }

    public void onBind(final MemoItem item, final int position){
        titleTextView.setText(item.getTitle());

        container.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClickWithId(item.getMemoId());
            }
        });

        deleteImageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onItemClickListener.onDeleteClickWithId(item.getMemoId());
            }
        });
    }
}
