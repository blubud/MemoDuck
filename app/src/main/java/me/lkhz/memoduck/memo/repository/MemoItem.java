package me.lkhz.memoduck.memo.repository;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "memo")
public class MemoItem {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name="id")
    private String memoId;

    @NonNull
    @ColumnInfo(name="title")
    private String title;

    @NonNull
    @ColumnInfo(name="content")
    private String fullContent;

    public MemoItem(String memoId, String fullContent){

        if(fullContent.length() <= 20)
            this.title = fullContent;
        else
            this.title = fullContent.substring(0, 20);

        this.memoId = memoId;
        this.fullContent = fullContent;
    }

    public String getMemoId() {
        return memoId;
    }

    public void setMemoId(String memoId) {
        this.memoId = memoId;
    }

    public String getFullContent() {
        return fullContent;
    }

    public void setFullContent(String fullContent) {
        this.fullContent = fullContent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
