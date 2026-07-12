<%-- Diretiva page: configura o encoding UTF-8 para suporte a acentos.
     contentType: define o Content-Type da resposta HTTP.
     pageEncoding: define como o GlassFish lê este arquivo .jsp. --%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<%-- Diretiva taglib: declara a biblioteca JSTL Core para uso nesta página.
     uri: identificador único da biblioteca no Jakarta EE 11.
     prefix: o prefixo "c" que usaremos antes de cada tag JSTL (c:forEach, c:if, etc.)
     Ainda não usamos tags JSTL nesta aula, mas já declaramos para a Aula 9. --%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TaskFlow — Lista de Tarefas</title>
    <style>
        /* Estilos inline mínimos para visualização durante o desenvolvimento.
           Em produção, esses estilos seriam movidos para um arquivo CSS externo. */
        body { font-family: Arial, sans-serif; max-width: 900px; margin: 40px auto; padding: 0 20px; }
        h1 { color: #333; border-bottom: 2px solid #007bff; padding-bottom: 10px; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th { background-color: #007bff; color: white; padding: 10px; text-align: left; }
        td { padding: 8px 10px; border-bottom: 1px solid #ddd; }
        tr:hover { background-color: #f5f5f5; }
        .btn { display: inline-block; padding: 6px 12px; text-decoration: none;
               border-radius: 4px; font-size: 14px; }
        .btn-primary { background-color: #007bff; color: white; }
        .btn-warning { background-color: #ffc107; color: black; }
        .btn-danger  { background-color: #dc3545; color: white; }
        .mensagem-vazia { text-align: center; color: #888; padding: 40px; font-size: 18px; }
    </style>
</head>
<body>

    <h1>TaskFlow — Gerenciador de Tarefas</h1>

    <%-- Link para o formulário de criação.
         Usa o context root /taskflow + /tasks?action=criar.
         O Controller interpretará action=criar e encaminhará para o form.jsp.
         Por enquanto, este link levará a uma página ainda não implementada
         (será conectada na Aula 11 com o CRUD completo). --%>
    <a href="${pageContext.request.contextPath}/tasks?action=criar" class="btn btn-primary">
        + Nova Tarefa
    </a>

    <%-- ${pageContext.request.contextPath} retorna o context root da aplicação.
         No nosso caso: /taskflow. Isso evita hardcodar "/taskflow" nos links,
         tornando a aplicação portável caso o context root mude. --%>

    <%-- Exibição condicional da mensagem de feedback (sucesso ou erro).
         O Controller pode colocar uma mensagem no request via:
         request.setAttribute("mensagem", "Tarefa criada com sucesso!")
         A EL ${mensagem} exibe o valor se presente, ou string vazia se ausente.
         O atributo style="color:green" é temporário — na Aula 9 usaremos c:if. --%>
    <p style="color: green; font-weight: bold;">${mensagem}</p>

    <%-- Tabela de listagem de tarefas.
         Por enquanto, não há dados reais — o Model (TaskRepository) ainda não existe.
         Na Aula 10, criaremos Task.java e TaskRepository.java.
         Na Aula 11, o Controller buscará as tarefas e as passará para esta View.
         A partir daí, esta tabela exibirá dados reais.
         Por ora, exibimos a estrutura estática da tabela. --%>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Título</th>
                <th>Status</th>
                <th>Data de Criação</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>

            <%-- FASE ATUAL (placeholder): exibe uma única linha de exemplo estático.
                 FASE AULA 9+: esta linha será substituída por <c:forEach> iterando
                 sobre ${tasks}, exibindo uma linha por tarefa.
                 FASE AULA 11+: o Controller terá populado ${tasks} com dados reais
                 do TaskRepository antes de encaminhar para este JSP. --%>
            <tr>
                <td colspan="5" class="mensagem-vazia">
                    <%-- ${empty tasks} retorna true se tasks for null ou vazia.
                         Por enquanto tasks é sempre null/vazia (Model não existe ainda).
                         Na Aula 9, substituiremos por <c:if test="${empty tasks}">. --%>
                    Nenhuma tarefa cadastrada. Clique em "+ Nova Tarefa" para começar.
                </td>
            </tr>

        </tbody>
    </table>

    <hr>
    <%-- Rodapé com informação de desenvolvimento — será removido na Aula 18. --%>
    <p style="color: #aaa; font-size: 12px;">
        TaskFlow v1.0 | Aula 8 — Views com JSP |
        Context Root: ${pageContext.request.contextPath}
    </p>

</body>
</html>