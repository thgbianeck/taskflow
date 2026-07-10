package com.taskflow.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/sobre")

public class SobreServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, jakarta.servlet.ServletException {

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter writer = response.getWriter();

        writer.println("<!DOCTYPE html>");
        writer.println("<html lang='pt-BR'>");
        writer.println("<head>");
        writer.println("    <meta charset='UTF-8'>");
        writer.println("    <title>TaskFlow — Sobre Servlet</title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("    <h1>Sobre o TaskFlow</h1>");
        writer.println("    <p>Este é o primeiro Servlet do projeto TaskFlow.</p>");
        writer.println("    <p>O conteúdo desta página é gerado dinamicamente pelo Java.</p>");
        writer.println("    <a href='/taskflow'>Voltar para a página inicial</a>");
        writer.println("</body>");
        writer.println("</html>");
        writer.close();
    }

    @Override
    public void init() throws jakarta.servlet.ServletException {
        System.out.println("[SobreServlet] init() chamado — Servlet inicializado.");
    }

    @Override
    public void destroy() {
        System.out.println("[SobreServlet] destroy() chamado — Servlet destruído.");
    }
}