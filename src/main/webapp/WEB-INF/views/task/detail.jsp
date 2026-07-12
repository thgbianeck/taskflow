<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>TaskFlow — Detalhe da Tarefa</title>
</head>
<body>
    <h1>Detalhe da Tarefa</h1>
    <table border="1" cellpadding="8">
        <tr><th>ID</th><td>${task.id}</td></tr>
        <tr><th>Título</th><td>${task.titulo}</td></tr>
        <tr><th>Descrição</th><td>${task.descricao}</td></tr>
        <tr><th>Status</th><td>${task.status}</td></tr>
        <tr><th>Data de Criação</th><td>${task.dataCriacao}</td></tr>
    </table>
    <br>
    <a href="${pageContext.request.contextPath}/tasks">← Voltar para a lista</a>
</body>
</html>