package me.lkhz.memoduck.add;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import me.lkhz.memoduck.R;
import me.lkhz.memoduck.memo.repository.MemoRepository;

public class AddMemoActivity extends AppCompatActivity implements AddMemoContract.View {

    private EditText contentText;
    private String content;
    private String id;          // id: 수정 / N: 생성
    private Button saveBtn;

    private AddMemoContract.Presenter addPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_memo);
        processIntent(getIntent());
        setView();
        setPresenter();
        setBtnEvents();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        addPresenter.detachView();
    }

    private void processIntent(Intent passedIntent){
        if(passedIntent.hasExtra("id") && passedIntent.hasExtra("content")){
            id = passedIntent.getStringExtra("id");
            content =  passedIntent.getStringExtra("content");;
        }
        else{
            showToast("데이터를 읽지 못했습니다. 다시 시도해주세요.");
            finish();
        }
    }
    private void setView(){
        contentText = findViewById(R.id.et_memo_content);
        contentText.setText(content);
    }

    private void setPresenter(){
        this.addPresenter = new AddMemoPresenter();
        addPresenter.attachView(this);
        addPresenter.setMemoRepository(MemoRepository.getInstance());
    }

    private void setBtnEvents(){
        findViewById(R.id.btn_cancel).setOnClickListener(view -> finish());


        findViewById(R.id.btn_save).setOnClickListener(view -> saveMemo());
    }

    private void saveMemo(){
        if(addPresenter.saveAlarm(id, String.valueOf(contentText.getText()))){
            setResult(RESULT_OK);
            finish();
        }
        else{
            showToast("저장에 실패하였습니다. 다시 시도해주세요.");
        }
    }

    public void showToast(String cont) {
        Toast.makeText(getApplicationContext(), cont, Toast.LENGTH_SHORT).show();
    }
}