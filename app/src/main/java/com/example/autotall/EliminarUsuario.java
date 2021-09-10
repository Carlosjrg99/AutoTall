package com.example.autotall;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class EliminarUsuario extends AppCompatActivity {
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
        setContentView(R.layout.activity_eliminar_usuario);
        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView titulo = findViewById(R.id.toolbar_title);
        titulo.append("Eliminar Usuario");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        db = new AyudaBD(this);
        EditText usuario = findViewById(R.id.usuarioA);
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
        Intent intent = new Intent(EliminarUsuario.this,MainActivity.class);
        startActivity(intent);
    }

    public void eliminar(View view)
    {
        countDownTimer.cancel();
        countDownTimer.start();
        EditText usuario = findViewById(R.id.usuarioA);
        if(db.eliminar(usuario.getText().toString()) == 0) {
            Toast.makeText(getApplicationContext(), "No se pudo eliminar",Toast.LENGTH_LONG).show();
            return;
        }
        Toast.makeText(getApplicationContext(), "Eliminado",Toast.LENGTH_LONG).show();
    }

    public void construccion(View view)
    {
        countDownTimer.cancel();
        Intent intent = new Intent(EliminarUsuario.this,EnConstruccion.class);
        startActivity(intent);
    }
}
