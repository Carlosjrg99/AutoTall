package com.example.autotall;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class OpcionesLlenadoUsuario extends AppCompatActivity {

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
        setContentView(R.layout.activity_opciones_llenado_usuario);
        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView titulo = findViewById(R.id.toolbar_title);
        titulo.append("Opciones de Llenado");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }



    public void llenar(View view) {
        countDownTimer.cancel();
        Intent intent = new Intent(OpcionesLlenadoUsuario.this, LlenadoBarril.class);
        startActivity(intent);
    }

    public void listar(View view) {
        countDownTimer.cancel();
        Intent intent = new Intent(OpcionesLlenadoUsuario.this,ListarBarriles.class);
        startActivity(intent);
    }

    public void revisar(View view)
    {
        countDownTimer.cancel();
        Intent intent = new Intent(OpcionesLlenadoUsuario.this,MainActivity.class);
        startActivity(intent);
    }

}