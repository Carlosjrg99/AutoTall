package com.example.autotall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListarBarriles extends AppCompatActivity {
    AyudaDBarril db = new AyudaDBarril(this);

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
        setContentView(R.layout.activity_listar_barriles);
        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView titulo = findViewById(R.id.toolbar_title);
        titulo.append("Registros");
        Button regresar = findViewById(R.id.regresar);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ListView listar = findViewById(R.id.lista);
        ArrayList<String> lista = new ArrayList<>();
        ArrayAdapter<String> adapter;
        Cursor cursor = db.listar();
        if(cursor.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "Imposible completar tarea", Toast.LENGTH_LONG).show();
        }
        String info = "";
        while(cursor.moveToNext()) {
            info = "Porcentaje: "+cursor.getString(1)+"\nFecha: "+cursor.getString(2)+"\nHora: "+cursor.getString(3)+"\n\n";
            lista.add(info);
        }
        adapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,lista);
        listar.setAdapter(adapter);
    }

    public void revisar(View view)
    {
        countDownTimer.cancel();
        Intent intent = new Intent(ListarBarriles.this,MainActivity.class);
        startActivity(intent);
    }

    public void enviar(View view)
    {
        countDownTimer.cancel();
        countDownTimer.start();
        Toast.makeText(this,"Datos enviados al proveedor",Toast.LENGTH_SHORT).show();
    }
}