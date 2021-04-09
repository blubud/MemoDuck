package me.lkhz.memoduck.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import me.lkhz.memoduck.R;
import me.lkhz.memoduck.add.AddMemoActivity;
import me.lkhz.memoduck.memo.MemoContract;
import me.lkhz.memoduck.memo.MemoFragment;
import me.lkhz.memoduck.memo.repository.memo.MemoItem;

public class MainActivity extends AppCompatActivity implements MainInterface {

    private final int ADD_REQUEST_CODE = 101;
    private final int EDIT_REQUEST_CODE = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setView();
        setActionBar();
        startMemoFragment();
    }

    private void setActionBar(){
        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_main);
    }

    private void setView(){
        findViewById(R.id.fab).setOnClickListener(view -> {
            // Go to Add Memo Activity
            Intent intent = new Intent(MainActivity.this, AddMemoActivity.class);
            intent.putExtra("id", "N");
            intent.putExtra("content", "");
            startActivityForResult(intent, ADD_REQUEST_CODE);
        });
    }

    // set memo fragment at the beginning
    @Override
    public void startMemoFragment(){
        MemoFragment memoFragment = new MemoFragment(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.frg_container, memoFragment).commit();
    }

    // Go to Add Memo Activity for edit Memo
    @Override
    public void openAddActivityForEdit(MemoItem item) {
        Intent intent = new Intent(MainActivity.this, AddMemoActivity.class);
        intent.putExtra("id", item.getMemoId());
        intent.putExtra("content", item.getFullContent());
        startActivityForResult(intent, EDIT_REQUEST_CODE);
    }

    @Override
    public void popDeleteDialog(MemoContract.Presenter presenter, int position) {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("메모 삭제")
                .setMessage("메모를 삭제하시겠습니까?")
                .setIcon(R.drawable.main_logo)
                .setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        presenter.deleteMemo(position);
                    }
                })
                .setNegativeButton("아니오", null)
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == ADD_REQUEST_CODE){
                startMemoFragment();
            }
            else if(requestCode == EDIT_REQUEST_CODE){
                startMemoFragment();
            }
        }
    }
}