// Pacote controller: Servlets que atuam como Controllers no padrão MVC.
package com.taskflow.controller;

// Importa o serviço de saudação — a lógica de negócio pura.
import com.taskflow.service.SaudacaoService;

// Importações do Jakarta EE para o Servlet.
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Importações Java padrão.
import java.io.IOException;
import java.io.PrintWriter;

// Mapeia este Servlet para a URL /saudacao.
// URL completa: http://localhost:8080/taskflow/saudacao
// Com parâmetro: http://localhost:8080/taskflow/saudacao?nome=Bianeck
@WebServlet("/saudacao")
public class SaudacaoServlet extends HttpServlet {

    // Instância do SaudacaoService como atributo de instância.
    // SaudacaoService não tem estado mutável — é seguro compartilhar entre threads.
    // REGRA: nunca armazene dados específicos de uma requisição como atributo de instância.
    private final SaudacaoService saudacaoService = new SaudacaoService();

    // doGet: chamado pelo GlassFish para cada requisição GET em /saudacao.
    // Parâmetros fornecidos pelo GlassFish (ou pelo Mockito nos testes):
    //   request: representa a requisição HTTP recebida.
    //   response: representa a resposta HTTP que será enviada.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        // Lê o parâmetro "nome" da query string da URL.
        // Exemplo: /saudacao?nome=Bianeck → retorna "Bianeck"
        // Sem o parâmetro: /saudacao → retorna null
        String nome = request.getParameter("nome");

        // Delega a lógica de negócio ao SaudacaoService.
        // O Servlet não sabe como gerar a saudação — apenas coordena.
        // Esta separação é o início da arquitetura MVC da Aula 7.
        String saudacao = saudacaoService.gerarSaudacao(nome);

        // Define o tipo de conteúdo como HTML com encoding UTF-8.
        // DEVE ser chamado ANTES de response.getWriter().
        // Sem isso, caracteres acentuados podem aparecer incorretos no navegador.
        response.setContentType("text/html;charset=UTF-8");

        // Obtém o PrintWriter para escrever no corpo da resposta HTTP.
        // Após getWriter(), o Content-Type não pode mais ser alterado.
        PrintWriter writer = response.getWriter();

        // Escreve o documento HTML completo na resposta.
        writer.println("<!DOCTYPE html>");
        writer.println("<html lang='pt-BR'>");
        writer.println("<head>");
        writer.println("    <meta charset='UTF-8'>");
        writer.println("    <title>TaskFlow — Saudação</title>");
        writer.println("</head>");
        writer.println("<body>");

        // Exibe a saudação gerada pelo SaudacaoService.
        // O conteúdo é dinâmico — gerado em Java com base no parâmetro recebido.
        writer.println("    <h1>" + saudacao + "</h1>");

        // Dica de uso ao usuário.
        writer.println("    <p>Dica: adicione <code>?nome=SeuNome</code> na URL.</p>");

        // Link de retorno à página inicial.
        writer.println("    <a href='/taskflow'>Voltar para a página inicial</a>");

        writer.println("</body>");
        writer.println("</html>");

        // Fecha o PrintWriter — libera recursos e garante que a resposta foi enviada.
        writer.close();
    }
}