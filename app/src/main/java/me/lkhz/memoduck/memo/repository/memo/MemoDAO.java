package me.lkhz.memoduck.memo.repository.memo;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MemoDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMemo(MemoItem item);

    @Query("SELECT * FROM memo ORDER BY id")
    List<MemoItem> getMemo();
}
