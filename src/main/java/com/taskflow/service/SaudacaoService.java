// Pacote service: classes de lógica de negócio pura.
// Esta classe não conhece Servlet, HTTP nem Jakarta EE.
// É um POJO testável diretamente com JUnit, sem mocks.
package com.taskflow.service;

public class SaudacaoService {

    /**
     * Gera uma saudação personalizada com base no nome fornecido.
     * Esta lógica não depende de HttpServletRequest nem de HttpServletResponse.
     * Por isso pode ser testada diretamente com JUnit sem necessidade de mocks.
     *
     * @param nome o nome do visitante; pode ser null ou em branco
     * @return uma String de saudação nunca nula
     */
    public String gerarSaudacao(String nome) {

        // Verifica se o nome é nulo ou está em branco (apenas espaços).
        // isBlank() retorna true para "", "   " e null-safe via Objects.
        // Neste caso, usamos a verificação combinada para máxima segurança.
        if (nome == null || nome.isBlank()) {
            // Retorna saudação genérica quando nenhum nome é fornecido.
            return "Olá, visitante!";
        }

        // trim() remove espaços extras do início e do fim do nome.
        // String.format() monta a mensagem com o nome sanitizado.
        return String.format("Olá, %s!", nome.trim());
    }
}