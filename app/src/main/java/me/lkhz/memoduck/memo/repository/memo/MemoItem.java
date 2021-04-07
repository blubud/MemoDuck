package me.lkhz.memoduck.memo.repository.memo;

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
    @ColumnInfo(name="content")
    private String fullContent;

    public MemoItem(String memoId, String fullContent){

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

}
