package com.example.autotall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class CrearUsuario extends AppCompatActivity {
    AyudaBD db;

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
        setContentView(R.layout.activity_crear_usuario);
        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView titulo = findViewById(R.id.toolbar_title);
        titulo.append("Crear Usuario");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        db = new AyudaBD(this);
        EditText password = findViewById(R.id.password);
        EditText usuario = findViewById(R.id.usuarioA);
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                {
                    countDownTimer.cancel();
                    countDownTimer.start();
                }
            }
        });
        usuario.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                {
                    countDownTimer.cancel();
                    countDownTimer.start();
                }
            }
        });
    }

    public void revisar(View view)
    {
        countDownTimer.cancel();
        Intent intent = new Intent(CrearUsuario.this,MainActivity.class);
        startActivity(intent);
    }

    public void insertar(View view)
    {
        countDownTimer.cancel();
        countDownTimer.start();
        EditText usuario = findViewById(R.id.usuarioA);
        EditText password = findViewById(R.id.password);
        RadioGroup grupo = (RadioGroup) findViewById(R.id.radio);
        RadioButton radioButt;
        int botonSelec = grupo.getCheckedRadioButtonId();
        radioButt = (RadioButton) findViewById(botonSelec);
        if(!db.insertar(usuario.getText().toString(),password.getText().toString(),radioButt.getText().toString())) {
            Toast.makeText(getApplicationContext(), "No se pudo agregar",Toast.LENGTH_LONG).show();
            return;
        }
        Toast.makeText(getApplicationContext(), "Datos registrados",Toast.LENGTH_LONG).show();
    }

    public void construccion(View view)
    {
        countDownTimer.cancel();
        Intent intent = new Intent(CrearUsuario.this,EnConstruccion.class);
        startActivity(intent);
    }
}