package com.example.autotall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    AyudaBD db = new AyudaBD(this);
    AyudaDBarril dBarril = new AyudaDBarril(this);
    CountDownTimer countDownTimer = new CountDownTimer(10000, 1000) {
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
        setContentView(R.layout.activity_main);
        EditText pass = findViewById(R.id.password);
        EditText user = findViewById(R.id.usuarioA);
        pass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                {
                    countDownTimer.cancel();
                    countDownTimer.start();
                }
            }
        });
        user.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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

    public void iniciarSesion(View view) throws Throwable
    {
        countDownTimer.cancel();
        Intent intent;
        TextView usuario = findViewById(R.id.usuarioA);
        EditText password = findViewById(R.id.password);
        dBarril.llenar();
        if(!db.revisarAdmin()) {
            Toast.makeText(getApplicationContext(), "Usuario: admin\nPassword: admin", Toast.LENGTH_LONG).show();
            intent = new Intent(MainActivity.this,MenuAdministrador.class);
            startActivity(intent);
            return;
        }
        Cursor cursor = db.ingresar(usuario.getText().toString(), password.getText().toString());
        if(cursor.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "Usuario o contraseña incorrecto", Toast.LENGTH_LONG).show();
        }
        else {
            String tipo = "";
            while(cursor.moveToNext()) {
                tipo = cursor.getString(2);
            }
            switch (tipo) {
                case "Usuario":
                    intent = new Intent(MainActivity.this,MenuUsuario.class);
                    startActivity(intent);
                    break;
                case "Administrador":
                    intent = new Intent(MainActivity.this,MenuAdministrador.class);
                    startActivity(intent);
                    break;
                default:
                    Toast.makeText(getApplicationContext(), "No posee tipo de acceso",Toast.LENGTH_LONG).show();
            }
        }
    }
}
