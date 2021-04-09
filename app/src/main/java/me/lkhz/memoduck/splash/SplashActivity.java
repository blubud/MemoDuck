package me.lkhz.memoduck.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import me.lkhz.memoduck.R;
import me.lkhz.memoduck.main.MainActivity;
import me.lkhz.memoduck.memo.repository.MemoRepository;
import me.lkhz.memoduck.memo.repository.memo.MemoDatabase;
import me.lkhz.memoduck.util.AppExecutor;

public class SplashActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        progressBar = findViewById(R.id.pb_loading);
        download();
    }

    private void download(){
        new Thread((new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(1000);
                    MemoRepository.makeInstance(MemoDatabase.getInstance(getApplicationContext()).memoDAO());
                    AppExecutor.makeInstance();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    if(init(intent)){
                        progressBar.setVisibility(View.INVISIBLE);
                        startActivity(intent);
                    }
                    finish();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        })).start();
    }

    private boolean init(Intent intent){

        // @TODO: 데이터 받아와서 intent 에 넣기

        return true;
    }
/*
    private class SplashAsyncTask extends AsyncTask<Void, Void, Integer> {
        ProgressDialog asyncDialog = new ProgressDialog(SplashActivity.this);

        @Override
        protected void onPreExecute() {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }

            asyncDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            asyncDialog.show();

            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Integer integer) {
            if(asyncDialog.isShowing())
                asyncDialog.dismiss();

            try{
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }

            finish();

            super.onPostExecute(integer);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            try{
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;
        }
    }
    */
}