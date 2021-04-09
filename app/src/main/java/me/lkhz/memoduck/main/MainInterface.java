package me.lkhz.memoduck.main;

import me.lkhz.memoduck.memo.MemoContract;
import me.lkhz.memoduck.memo.repository.memo.MemoItem;

public interface MainInterface {

     void startMemoFragment();
     void openAddActivityForEdit(MemoItem item);
     void popDeleteDialog(MemoContract.Presenter presenter, int position);

}
