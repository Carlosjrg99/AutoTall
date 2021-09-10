package com.example.autotall;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class EditarUsuario extends AppCompatActivity {
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
        setContentView(R.layout.activity_editar_usuario);
        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView titulo = findViewById(R.id.toolbar_title);
        titulo.append("Editar Usuario");
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
        Intent intent = new Intent(EditarUsuario.this,MainActivity.class);
        startActivity(intent);
    }

    public void editar(View view) {
        countDownTimer.cancel();
        countDownTimer.start();
        EditText usuarioA = findViewById(R.id.usuarioA);
        EditText usuarioN = findViewById(R.id.usuarioN);
        EditText password = findViewById(R.id.password);
        RadioButton admin = findViewById(R.id.radioAdministrador);
        RadioButton user = findViewById(R.id.radioUsuario);
        if(admin.isChecked()) {
            if(db.editar(usuarioA.getText().toString(),usuarioN.getText(),password.getText(),"Administrador")) {
                Toast.makeText(getApplicationContext(), "Editado",Toast.LENGTH_LONG).show();
                return;
            }
        }
        else if(user.isChecked()) {
                if(db.editar(usuarioA.getText().toString(),usuarioN.getText(),password.getText(),"Usuario")) {
                    Toast.makeText(getApplicationContext(), "Editado",Toast.LENGTH_LONG).show();
                    return;
                }
        }
        else {
            if(db.editar(usuarioA.getText().toString(),usuarioN.getText(),password.getText(),null)) {
                Toast.makeText(getApplicationContext(), "Editado",Toast.LENGTH_LONG).show();
                return;
            }
        }
        Toast.makeText(getApplicationContext(), "No se pudo editar",Toast.LENGTH_LONG).show();
        return;
    }
}