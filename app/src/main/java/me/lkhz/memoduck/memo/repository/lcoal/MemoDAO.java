package me.lkhz.memoduck.memo.repository.lcoal;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import me.lkhz.memoduck.memo.repository.MemoItem;

@Dao
public interface MemoDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMemo(MemoItem item);

    @Query("SELECT * FROM memo ORDER BY id")
    List<MemoItem> getMemo();
}
