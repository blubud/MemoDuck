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
    List<MemoItem> selectAllMemo();

    @Query("UPDATE memo SET content = :content WHERE id = :memoId")
    void updateMemo(String memoId, String content);

    @Query("DELETE FROM memo WHERE id = :id")
    void deleteMemo(String id);

}
