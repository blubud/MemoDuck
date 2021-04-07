package me.lkhz.memoduck.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import me.lkhz.memoduck.R;
import me.lkhz.memoduck.add.AddMemoActivity;
import me.lkhz.memoduck.memo.MemoFragment;

public class MainActivity extends AppCompatActivity {

    private final int ADD_REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setView();
        setActionBar();
        setDefaultFragment();
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
    private void setDefaultFragment(){
        MemoFragment memoFragment = new MemoFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.frg_container, memoFragment).commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == ADD_REQUEST_CODE){
                setDefaultFragment();
            }
        }
    }
}