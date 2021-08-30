package com.example.semana2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
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
    private Button reiniciar;
    Pregunta preguntaClass;
    private int tiempoRestante;
    private int puntaje;
    private int tiempoPul;
    private boolean isPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pregunta = findViewById(R.id.pregunta);
        respuestaUser = findViewById(R.id.respuestaUsuario);
        puntajeTxt = findViewById(R.id.puntaje);
        tiempoTxt = findViewById(R.id.tiempo);
        responder = findViewById(R.id.responder);
        reiniciar = findViewById(R.id.reset);
        puntaje = 0;
        tiempoRestante = 30;

        generarNuevaPregunta();

        tiempoTxt.setText("" + tiempoRestante);
        puntajeTxt.setText("Puntaje:" + "  " + puntaje);

        responder.setEnabled(true);
        reiniciar.setVisibility(View.GONE);
        reiniciar.setVisibility(View.GONE);


        new Thread(() -> {
            while(tiempoRestante>0) {
                try {
                    tiempoRestante--;

                    runOnUiThread(
                            () -> {
                                tiempoTxt.setText("" + tiempoRestante);
                            }
                    );
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Log.e("ERROR", e.toString());
                }

            }
        if(tiempoRestante==0){
            runOnUiThread(
                    () -> {
                      responder.setEnabled(false);
                      reiniciar.setVisibility(View.VISIBLE);
                    });
        }

        }).start();




        responder.setOnClickListener(
                (view) -> {
                    verificarRespuesta();
                }
        );

        reiniciar.setOnClickListener(
                (view) -> {
                    reiniciarTodo();
                }
        );


        isPressed = false;
        pregunta.setOnTouchListener(
                (view, event) ->{
                    switch(event.getAction()) {
                        case (MotionEvent.ACTION_DOWN):
                            isPressed = true;

                            new Thread(() -> {
                                tiempoPul = 0;
                                while (tiempoPul < 1500) {
                                    try {

                                        Thread.sleep(1);
                                        tiempoPul++;
                                        if (isPressed == false) {
                                            return;
                                        }
                                    } catch (InterruptedException e) {

                                    }

                                }

                                runOnUiThread(
                                        () -> {
                                            generarNuevaPregunta();
                                        });


                            }).start();

                            break;
                        case (MotionEvent.ACTION_UP):
                            isPressed = false;
                    }
                        return true;

        });
    }

    private void reiniciarTodo() {
        generarNuevaPregunta();
        puntaje = 0;
        puntajeTxt.setText("Puntaje:" + "  " + puntaje);
        tiempoRestante = 30;
        reiniciar.setVisibility(View.GONE);
        responder.setEnabled(true);
        new Thread(() -> {
            while(tiempoRestante>0) {
                try {
                    tiempoRestante--;

                    runOnUiThread(
                            () -> {
                                tiempoTxt.setText("" + tiempoRestante);
                            }
                    );
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Log.e("ERROR", e.toString());
                }

            }
            if(tiempoRestante==0){
                runOnUiThread(
                        () -> {
                            responder.setEnabled(false);
                            reiniciar.setVisibility(View.VISIBLE);
                        });
            }

        }).start();
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
