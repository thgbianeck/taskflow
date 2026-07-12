// Pacote controller: camada responsável por receber requisições HTTP
// e coordenar a resposta, sem conter lógica de negócio.
// Este é o Controller central do TaskFlow — o único Servlet da aplicação.
package com.taskflow.controller;

// Importações do Jakarta EE — usando jakarta.* (não javax.*)
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Importações Java padrão para IO.
import java.io.IOException;
import java.io.PrintWriter;

// @WebServlet mapeia este Servlet para a URL /tasks.
// URL completa: http://localhost:8080/taskflow/tasks
// A partir da Aula 11, este Servlet deixará de usar PrintWriter
// e passará a encaminhar para os JSPs via RequestDispatcher.forward().
@WebServlet("/tasks")
public class TaskServlet extends HttpServlet {

    // doGet: responde a requisições GET em /tasks.
    // FASE ATUAL (placeholder): exibe uma mensagem de status da arquitetura.
    // FASE AULA 11 (completo): buscará tarefas no TaskRepository e
    // encaminhará para views/task/list.jsp via RequestDispatcher.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        // Define o tipo de conteúdo como HTML com UTF-8.
        // NOTA: esta linha será mantida apenas enquanto o Servlet gerar HTML.
        // Após a migração para forward ao JSP (Aula 11), o JSP definirá o Content-Type.
        response.setContentType("text/html;charset=UTF-8");

        // PrintWriter temporário — apenas para esta fase de placeholder.
        // A partir da Aula 11, este Servlet NÃO usará PrintWriter.
        // O HTML será gerado pelo JSP, não pelo Controller.
        PrintWriter writer = response.getWriter();

        // Mensagem temporária descrevendo o estado arquitetural do projeto.
        // Esta página será substituída pelo forward para list.jsp na Aula 11.
        writer.println("<!DOCTYPE html>");
        writer.println("<html lang='pt-BR'>");
        writer.println("<head><meta charset='UTF-8'>");
        writer.println("<title>TaskFlow</title></head>");
        writer.println("<body>");
        writer.println("<h1>TaskFlow — Controller no lugar</h1>");
        writer.println("<p>Aula 7 concluída: estrutura MVC criada.</p>");
        writer.println("<ul>");
        // Lista as próximas etapas para contextualizar o aluno.
        writer.println("<li>Aula 8: Views JSP serão criadas em WEB-INF/views/task/</li>");
        writer.println("<li>Aula 9: JSTL será adicionado às Views</li>");
        writer.println("<li>Aula 10: Task.java e TaskRepository.java serão criados (testáveis com JUnit puro)</li>");
        writer.println("<li>Aula 11: este Controller buscará tarefas e encaminhará para o JSP via forward()</li>");
        writer.println("</ul>");
        writer.println("</body></html>");

        writer.close();
    }
}