package com.taskflow.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// A anotação @WebServlet mapeia este Servlet para a URL "/hello".
// Com o context root "/taskflow", a URL completa será:
// http://localhost:8080/taskflow/hello
@WebServlet("/hello")

// A classe HelloServlet herda de HttpServlet.
// Herdar de HttpServlet é obrigatório para que o GlassFish reconheça
// esta classe como um Servlet HTTP e gerencie seu ciclo de vida.
public class HelloServlet extends HttpServlet {

    // O método doGet é chamado pelo GlassFish quando chega
    // uma requisição HTTP do tipo GET para a URL mapeada (/hello).
    // Parâmetros:
    //   request  — contém todos os dados da requisição (URL, parâmetros, cabeçalhos)
    //   response — é o objeto pelo qual escrevemos a resposta de volta ao navegador
    // Exceções declaradas:
    //   IOException — pode ocorrer ao escrever na resposta (I/O de rede)
    //   jakarta.servlet.ServletException — pode ocorrer em erros do Servlet
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, jakarta.servlet.ServletException {

        // Define o tipo de conteúdo da resposta como HTML com codificação UTF-8.
        // IMPORTANTE: esta linha deve ser chamada ANTES de response.getWriter().
        // O charset=UTF-8 garante que acentos e caracteres especiais sejam
        // exibidos corretamente no navegador.
        response.setContentType("text/html;charset=UTF-8");

        // Obtém o PrintWriter — o objeto que escreve no corpo da resposta HTTP.
        // Tudo que for escrito neste writer será enviado ao navegador como HTML.
        PrintWriter writer = response.getWriter();

        // Escreve o início do documento HTML na resposta.
        writer.println("<!DOCTYPE html>");
        writer.println("<html lang='pt-BR'>");
        writer.println("<head>");

        // Define o charset no meta tag como boa prática adicional.
        writer.println("    <meta charset='UTF-8'>");

        // Define o título da página exibido na aba do navegador.
        writer.println("    <title>TaskFlow — Hello Servlet</title>");
        writer.println("</head>");
        writer.println("<body>");

        // Escreve o conteúdo principal da página.
        // Este texto é gerado dinamicamente pelo Java — diferente do index.html
        // estático, este conteúdo poderia variar a cada requisição com base
        // em lógica de negócio, dados do banco, parâmetros da URL, etc.
        writer.println("    <h1>Hello, TaskFlow!</h1>");
        writer.println("    <p>Este é o primeiro Servlet do projeto TaskFlow.</p>");
        writer.println("    <p>O conteúdo desta página é gerado dinamicamente pelo Java.</p>");
        writer.println("    <a href='/taskflow'>Voltar para a página inicial</a>");

        // Fecha o documento HTML.
        writer.println("</body>");
        writer.println("</html>");

        // Fecha o PrintWriter, sinalizando que a resposta está completa.
        // Boa prática: sempre fechar explicitamente os recursos abertos.
        writer.close();
    }

    @Override
    public void init() throws jakarta.servlet.ServletException {
        System.out.println("[HelloServlet] init() chamado — Servlet inicializado.");
    }

    @Override
    public void destroy() {
        System.out.println("[HelloServlet] destroy() chamado — Servlet destruído.");
    }
}