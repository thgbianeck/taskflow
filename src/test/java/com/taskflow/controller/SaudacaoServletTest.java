// Pacote de testes espelhando o pacote do Controller.
package com.taskflow.controller;

// JUnit 5 Jupiter.
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// Extensão do Mockito para JUnit 5.
// @ExtendWith(MockitoExtension.class) integra o ciclo de vida do Mockito com o JUnit 5,
// inicializando automaticamente os campos anotados com @Mock e @InjectMocks.
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

// Importações do Jakarta EE — as interfaces que serão mockadas.
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// PrintWriter para capturar o conteúdo escrito pelo Servlet.
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.IOException;

// Importações estáticas para asserções JUnit 5 e métodos do Mockito.
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// @ExtendWith ativa a extensão do Mockito para este teste.
// Sem esta anotação, os campos @Mock e @InjectMocks não seriam inicializados.
@ExtendWith(MockitoExtension.class)
@DisplayName("Testes do SaudacaoServlet com Mockito")
class SaudacaoServletTest {

    // @Mock: instrui o Mockito a criar uma implementação falsa de HttpServletRequest.
    // O Mockito cria um objeto que implementa a interface mas não faz nada por padrão.
    // Podemos definir o comportamento com when(...).thenReturn(...).
    @Mock
    private HttpServletRequest request;

    // @Mock: implementação falsa de HttpServletResponse.
    // Usaremos quando(...).thenReturn(writer) para que getWriter() retorne nosso writer de teste.
    @Mock
    private HttpServletResponse response;

    // @InjectMocks: instrui o Mockito a criar uma instância real do SaudacaoServlet
    // e injetar nela os mocks declarados acima (@Mock).
    // Neste caso, o SaudacaoServlet cria internamente seu SaudacaoService,
    // então @InjectMocks apenas garante a instância do Servlet.
    @InjectMocks
    private SaudacaoServlet saudacaoServlet;

    // StringWriter captura tudo que for escrito no PrintWriter durante o teste.
    // É o "papel" onde o Servlet vai "imprimir" o HTML da resposta.
    private StringWriter responseWriter;

    // PrintWriter que envolve o StringWriter.
    // Será retornado pelo mock quando o Servlet chamar response.getWriter().
    private PrintWriter printWriter;

    // @BeforeEach: executado antes de cada @Test.
    // Recria o StringWriter e o PrintWriter para garantir isolamento entre testes.
    @BeforeEach
    void setUp() throws IOException {
        // Cria um novo StringWriter para capturar a saída de cada teste.
        responseWriter = new StringWriter();

        // Envolve o StringWriter em um PrintWriter.
        // O Servlet chama response.getWriter() — retornaremos este PrintWriter.
        printWriter = new PrintWriter(responseWriter);

        // Define o comportamento do mock: quando getWriter() for chamado no response,
        // retorne o nosso printWriter de captura.
        // Sem este stub, response.getWriter() retornaria null por padrão.
        when(response.getWriter()).thenReturn(printWriter);
    }

    @Test
    @DisplayName("Deve retornar HTML com saudação personalizada quando nome for fornecido")
    void deveRetornarHtmlComSaudacaoPersonalizada_quandoNomeForFornecido()
            throws IOException, jakarta.servlet.ServletException {

        // ARRANGE: define que getParameter("nome") retorna "Bianeck".
        // Esta é a fase de stubbing — definimos o roteiro do ator (mock).
        when(request.getParameter("nome")).thenReturn("Bianeck");

        // ACT: chama o método doGet do Servlet diretamente, passando os mocks.
        // O Servlet vai: chamar request.getParameter("nome") → "Bianeck",
        //                chamar SaudacaoService.gerarSaudacao("Bianeck") → "Olá, Bianeck!",
        //                chamar response.setContentType(...),
        //                chamar response.getWriter() → nosso printWriter,
        //                escrever o HTML no printWriter.
        saudacaoServlet.doGet(request, response);

        // Flush garante que tudo foi escrito no StringWriter antes de verificar.
        printWriter.flush();

        // Obtém o HTML capturado pelo StringWriter.
        String htmlGerado = responseWriter.toString();

        // ASSERT 1: verifica que o HTML contém a saudação correta.
        assertTrue(htmlGerado.contains("Olá, Bianeck!"),
            "O HTML deve conter 'Olá, Bianeck!' mas continha: " + htmlGerado);

        // ASSERT 2: verifica que o HTML é um documento HTML válido (tem a tag html).
        assertTrue(htmlGerado.contains("<html"),
            "O HTML deve conter a tag <html>");

        // ASSERT 3: verifica (via Mockito) que setContentType foi chamado corretamente.
        // verify() falha o teste se o método não foi chamado com os argumentos especificados.
        verify(response).setContentType("text/html;charset=UTF-8");
    }

    @Test
    @DisplayName("Deve retornar HTML com saudação genérica quando nome for nulo")
    void deveRetornarSaudacaoGenerica_quandoNomeForNulo()
            throws IOException, jakarta.servlet.ServletException {

        // ARRANGE: getParameter("nome") retorna null (parâmetro não enviado na URL).
        when(request.getParameter("nome")).thenReturn(null);

        // ACT
        saudacaoServlet.doGet(request, response);
        printWriter.flush();

        // ASSERT: o HTML deve conter a saudação genérica.
        String htmlGerado = responseWriter.toString();
        assertTrue(htmlGerado.contains("Olá, visitante!"),
            "Com nome nulo, o HTML deve conter 'Olá, visitante!'");
    }

    @Test
    @DisplayName("Deve retornar HTML com saudação genérica quando nome for vazio")
    void deveRetornarSaudacaoGenerica_quandoNomeForVazio()
            throws IOException, jakarta.servlet.ServletException {

        // ARRANGE: getParameter("nome") retorna string vazia.
        when(request.getParameter("nome")).thenReturn("");

        // ACT
        saudacaoServlet.doGet(request, response);
        printWriter.flush();

        // ASSERT
        String htmlGerado = responseWriter.toString();
        assertTrue(htmlGerado.contains("Olá, visitante!"),
            "Com nome vazio, o HTML deve conter 'Olá, visitante!'");
    }

    @Test
    @DisplayName("Deve definir o Content-Type correto em todas as respostas")
    void deveDefinirContentTypeCorreto()
            throws IOException, jakarta.servlet.ServletException {

        // ARRANGE: qualquer parâmetro — estamos testando o Content-Type.
        when(request.getParameter("nome")).thenReturn("Qualquer");

        // ACT
        saudacaoServlet.doGet(request, response);

        // ASSERT via verify: verifica que setContentType foi chamado exatamente 1 vez
        // com o argumento "text/html;charset=UTF-8".
        // Se o Servlet chamou com argumento diferente, o teste falha.
        verify(response).setContentType("text/html;charset=UTF-8");
    }

    @Test
    @DisplayName("Deve conter um link de retorno na resposta HTML")
    void deveConterLinkDeRetorno()
            throws IOException, jakarta.servlet.ServletException {

        // ARRANGE
        when(request.getParameter("nome")).thenReturn("Bianeck");

        // ACT
        saudacaoServlet.doGet(request, response);
        printWriter.flush();

        // ASSERT: verifica que o HTML contém um link de retorno.
        String htmlGerado = responseWriter.toString();
        assertTrue(htmlGerado.contains("href='/taskflow'"),
            "O HTML deve conter um link de retorno para /taskflow");
    }
}