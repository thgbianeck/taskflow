package com.taskflow.service;

public class CalculadoraTaxa {

    private static final double TAXA = 0.10;

    public double calcular(double valor) {
        if (valor < 0) {
            throw new IllegalArgumentException(
                "Valor não pode ser negativo. Recebido: " + valor);
        }
        return valor + (valor * TAXA);
    }
}