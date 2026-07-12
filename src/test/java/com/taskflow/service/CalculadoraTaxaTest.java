package com.taskflow.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Testes da CalculadoraTaxa")
class CalculadoraTaxaTest {

    private CalculadoraTaxa calculadora;

    @BeforeEach
    void setUp() {
        calculadora = new CalculadoraTaxa();
    }

    @Test
    @DisplayName("Deve aplicar 10% de acréscimo sobre o valor")
    void deveAplicarDezPorcentoDeAcrescimo() {
        double resultado = calculadora.calcular(100.0);
        assertEquals(110.0, resultado, 0.001);
    }

    @Test
    @DisplayName("Deve retornar zero para valor zero")
    void deveRetornarZero_quandoValorForZero() {
        assertEquals(0.0, calculadora.calcular(0.0), 0.001);
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException para valor negativo")
    void deveLancarExcecao_quandoValorForNegativo() {
        assertThrows(IllegalArgumentException.class,
            () -> calculadora.calcular(-50.0));
    }
}