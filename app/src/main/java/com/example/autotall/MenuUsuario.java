package com.example.autotall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

public class MenuUsuario extends AppCompatActivity {

    CountDownTimer countDownTimer = new CountDownTimer(60000, 1000) {
        public void onTick(long millisUntilFinished) {
        }
        public void onFinish() {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            System.exit(0);
        }
    }.start();
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
            countDownTimer.cancel();
            countDownTimer.start();
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_menu_usuario);
    }


    public void revisar(View view)
    {
        countDownTimer.cancel();
        Intent intent = new Intent(MenuUsuario.this,MainActivity.class);
        startActivity(intent);
    }

    public void llenar(View view) {
        countDownTimer.cancel();
        Intent intent = new Intent(MenuUsuario.this,OpcionesLlenadoUsuario.class);
        startActivity(intent);
    }

    public void construccion(View view)
    {
        countDownTimer.cancel();
        Intent intent = new Intent(MenuUsuario.this,EnConstruccion.class);
        startActivity(intent);
    }
}