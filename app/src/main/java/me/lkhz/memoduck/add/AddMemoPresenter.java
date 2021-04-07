package me.lkhz.memoduck.add;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import me.lkhz.memoduck.memo.repository.MemoRepository;
import me.lkhz.memoduck.memo.repository.memo.MemoItem;

public class AddMemoPresenter implements AddMemoContract.Presenter{

    private AddMemoContract.View addView;
    private MemoRepository memoRepository;

    @Override
    public boolean saveAlarm(String id, String content) {
        // 생성
        if("N".equals(id)){
            MemoItem item = new MemoItem(generateId(), content);
            memoRepository.insertMemoItem(item);
            return true;
        }
        // 수정
        else {
            MemoItem item = new MemoItem(id, content);
            memoRepository.updateMemoItem(item);
            return true;
        }
    }

    // ID 생성
    private String generateId(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return now.format(formatter);
    }

    @Override
    public void setMemoRepository(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    @Override
    public void attachView(AddMemoContract.View view) {
        this.addView = view;
    }

    @Override
    public void detachView() {
        this.addView = null;
    }
}
