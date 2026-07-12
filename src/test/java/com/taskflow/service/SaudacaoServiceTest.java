// Pacote de testes espelhando o pacote do código de produção.
package com.taskflow.service;

// Importações do JUnit 5 Jupiter.
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// Importações estáticas das asserções — evita escrever Assertions.assertEquals todo momento.
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

// Não precisamos de @ExtendWith(MockitoExtension.class) aqui
// porque SaudacaoService é um POJO puro — nenhum mock é necessário.
@DisplayName("Testes do SaudacaoService")
class SaudacaoServiceTest {

    // Instância do serviço sob teste.
    // Será recriada antes de cada método @Test pelo @BeforeEach.
    private SaudacaoService saudacaoService;

    // @BeforeEach: executado antes de CADA método @Test.
    // Garante que cada teste começa com uma instância limpa do serviço.
    @BeforeEach
    void setUp() {
        saudacaoService = new SaudacaoService();
    }

    // ─── FASE RED: escreva este teste primeiro. Ele passa porque
    //     SaudacaoService já foi implementado acima (sequência didática).
    //     Em TDD puro: escreva o teste → veja falhar → implemente → veja passar.

    @Test
    @DisplayName("Deve retornar saudação com o nome quando nome válido for fornecido")
    void deveRetornarSaudacaoComNome_quandoNomeForValido() {
        // ARRANGE: prepara o cenário — nome válido.
        String nome = "Bianeck";

        // ACT: executa a ação sob teste.
        String resultado = saudacaoService.gerarSaudacao(nome);

        // ASSERT: verifica o resultado esperado.
        // assertEquals(esperado, real) — ordem importa para mensagens de erro claras.
        assertEquals("Olá, Bianeck!", resultado);
    }

    @Test
    @DisplayName("Deve retornar saudação genérica quando nome for nulo")
    void deveRetornarSaudacaoGenerica_quandoNomeForNulo() {
        // ARRANGE: cenário com nome nulo.
        String nome = null;

        // ACT
        String resultado = saudacaoService.gerarSaudacao(nome);

        // ASSERT: null deve retornar a saudação genérica.
        assertEquals("Olá, visitante!", resultado);
    }

    @Test
    @DisplayName("Deve retornar saudação genérica quando nome for vazio")
    void deveRetornarSaudacaoGenerica_quandoNomeForVazio() {
        // ARRANGE: string vazia.
        String nome = "";

        // ACT
        String resultado = saudacaoService.gerarSaudacao(nome);

        // ASSERT
        assertEquals("Olá, visitante!", resultado);
    }

    @Test
    @DisplayName("Deve remover espaços extras do nome antes de gerar a saudação")
    void deveRemoverEspacos_quandoNomeTiverEspacosExtras() {
        // ARRANGE: nome com espaços no início e no fim.
        String nome = "  Bianeck  ";

        // ACT
        String resultado = saudacaoService.gerarSaudacao(nome);

        // ASSERT: trim() deve ter removido os espaços.
        assertEquals("Olá, Bianeck!", resultado);
    }

    @Test
    @DisplayName("O resultado nunca deve ser nulo, independentemente do input")
    void resultadoNuncaDeveSerNulo() {
        // ASSERT com assertNotNull: verifica que o retorno nunca é nulo.
        assertNotNull(saudacaoService.gerarSaudacao(null));
        assertNotNull(saudacaoService.gerarSaudacao(""));
        assertNotNull(saudacaoService.gerarSaudacao("Bianeck"));
    }
}