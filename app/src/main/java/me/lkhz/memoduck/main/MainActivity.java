package me.lkhz.memoduck.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import me.lkhz.memoduck.R;
import me.lkhz.memoduck.memo.MemoFragment;

public class MainActivity extends AppCompatActivity {

    private ImageView lock;
    private final int REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setView();
        setActionBar();
        setDefaultFragment();
    }

    private void setActionBar(){
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_main);
        lock = findViewById(R.id.iv_lock);
        lock.setImageResource(R.drawable.ic_lock_lock);
    }

    private void setView(){
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // Go to Add Alarm Activity
                /*
                Intent intent = new Intent(MainActivity.this, AddAlarmActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                 */
                Toast.makeText(MainActivity.this, "Floating Action Button clicked!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // set memo fragment at the beginning
    private void setDefaultFragment(){
        MemoFragment memoFragment = new MemoFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.frg_container, memoFragment).commit();
    }
}