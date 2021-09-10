package com.example.autotall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class LlenadoBarril extends AppCompatActivity {
    AyudaDBarril db = new AyudaDBarril(this);
    Handler handler = new Handler();
    int porcentajeAgregado;

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
        setContentView(R.layout.activity_llenado_barril);
        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView titulo = findViewById(R.id.toolbar_title);
        titulo.append("Llenado Manual");
        Button regresar = findViewById(R.id.regresar);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        TextView porcentaje = findViewById(R.id.porcentaje);
        int progreso = 0;
        progreso = new Random().nextInt(21);
        porcentajeAgregado = 100 - progreso;
        progressBar.setProgress(progreso);
        porcentaje.setText(progreso+"%");
    }

    public void llenar(View view) {
        ProgressBar progressBar = findViewById(R.id.progressBar);
        if(progressBar.getProgress() == 100) {
            handler.removeCallbacks(llenado);
            progressBar.setProgress(new Random().nextInt(21));
            porcentajeAgregado = 100 - progressBar.getProgress();
            TextView porcentaje = findViewById(R.id.porcentaje);
            porcentaje.setText(progressBar.getProgress()+"%");
        } else {
            llenado.run();
        }
    }

    private Runnable llenado = new Runnable() {
        @Override
        public void run() {
            countDownTimer.cancel();
            countDownTimer.start();
            ProgressBar progressBar = findViewById(R.id.progressBar);
            TextView porcentaje = findViewById(R.id.porcentaje);
            int progreso = progressBar.getProgress();
            if(progreso < 100) {
                progreso = new Random().nextInt(100 - progreso + 1) + progreso;
                progressBar.setProgress(progreso);
                porcentaje.setText(progreso+"%");
                handler.postDelayed(this,1000);
            }
            else {
                countDownTimer.cancel();
                countDownTimer.start();
                porcentaje.setText("Llenado Completado");
                Calendar ahora = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String fecha = simpleDateFormat.format(ahora.getTime());
                simpleDateFormat = new SimpleDateFormat("hh:mm:ss a");
                String hora = simpleDateFormat.format(ahora.getTime());
                if(!db.insertar(porcentajeAgregado+"%",fecha,hora)) {
                    Toast.makeText(getApplicationContext(), "No registrado", Toast.LENGTH_LONG).show();
                }
                Toast.makeText(getApplicationContext(), "Registrado", Toast.LENGTH_LONG).show();
                handler.removeCallbacks(llenado);
            }
        }
    };

    public void revisar(View view)
    {
        countDownTimer.cancel();
        Intent intent = new Intent(LlenadoBarril.this,MainActivity.class);
        startActivity(intent);
    }
}