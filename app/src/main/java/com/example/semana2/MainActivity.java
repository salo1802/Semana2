package com.example.semana2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView pregunta;
    private EditText respuestaUser;
    private TextView puntajeTxt;
    private TextView tiempoTxt;
    private Button responder;
    Pregunta preguntaClass;
    private int tiempoRestante;
    private int puntaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pregunta = findViewById(R.id.pregunta);
        respuestaUser = findViewById(R.id.respuestaUsuario);
        puntajeTxt = findViewById(R.id.puntaje);
        tiempoTxt = findViewById(R.id.tiempo);
        puntaje = 0;
        tiempoRestante = 30;
        generarNuevaPregunta();
        pregunta.setText(preguntaClass.getPregunta());
        tiempoTxt.setText("" + tiempoRestante);
        puntajeTxt.setText("Puntaje:" + "  " + puntaje);

        responder.setOnClickListener(
                (view) -> {
                    verificarRespuesta();
                }
        );
    }

        public void verificarRespuesta(){
            String resTxt = respuestaUser.getText().toString();
            int resUser = (int) Integer.parseInt(resTxt);
        if(resUser == preguntaClass.getRespuesta()){
            Toast.makeText(this, "Correcto", Toast.LENGTH_SHORT).show();
            puntaje += 5;
            puntajeTxt.setText("Puntaje:" + "  " + puntaje);
        }
        else {
            Toast.makeText(this, "Incorrecto", Toast.LENGTH_SHORT).show();
        }

        generarNuevaPregunta();

        }

        public void generarNuevaPregunta(){
            preguntaClass = new Pregunta();
            pregunta.setText(preguntaClass.getPregunta());

        }

    }
