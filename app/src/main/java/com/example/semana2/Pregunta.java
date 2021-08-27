package com.example.semana2;

public class Pregunta {
    private int operandoA;
    private int operandoB;
    private String operador;
    private String[] operadores = {"+", "-", "x", "/"};

    //constructor
    public Pregunta(){
        this.operandoA = (int)(Math.random() * 11);
        this.operandoB = (int)((Math.random() * 11)+1);
        this.operador = operadores[(int)(Math.random()* 4)];

    }

    //metodos
    public String getPregunta(){
        return operandoA + " "+ operador + " " + operandoB;
    }
    public int getRespuesta(){
        int respuesta = 0;
        switch (operador){
            case "+":
                respuesta = operandoA + operandoB;
                break;
            case "-":
                respuesta = operandoA - operandoB;
                break;
            case "x":
                respuesta= operandoA * operandoB;
                break;
            case "/":
                respuesta = operandoA/operandoB;
                break;
        }
        return respuesta;
    }
}
