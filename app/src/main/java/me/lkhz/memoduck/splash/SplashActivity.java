package me.lkhz.memoduck.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.biometric.BiometricPrompt.PromptInfo;

import me.lkhz.memoduck.R;
import me.lkhz.memoduck.main.MainActivity;
import me.lkhz.memoduck.memo.repository.MemoRepository;
import me.lkhz.memoduck.memo.repository.memo.MemoDatabase;
import me.lkhz.memoduck.util.AppExecutor;


public class SplashActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private BiometricPrompt biometricPrompt;
    private PromptInfo promptInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        progressBar = findViewById(R.id.pb_loading);
        // Initialize Executor
        AppExecutor.makeInstance();
        MemoRepository.makeInstance(MemoDatabase.getInstance(getApplicationContext()).memoDAO());
        findViewById(R.id.btn_auth).setOnClickListener(view -> {
            start();
        });
        start();
    }
    private void start(){
        biometricPrompt = new BiometricPrompt(this, AppExecutor.getInstance().mainThread(), new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(getApplicationContext(),
                        "인증 에러", Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                goToMainActivity();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(getApplicationContext(),
                        "인증 실패", Toast.LENGTH_SHORT)
                        .show();
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("지문 인증")
                .setSubtitle("기기에 등록된 지문을 이용하여 인증해주세요.")
                .setNegativeButtonText("취소")
                .build();

        biometricPrompt.authenticate(promptInfo);
    }


    private void goToMainActivity(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        progressBar.setVisibility(View.INVISIBLE);
    }

}