<%-- Diretiva page: encoding UTF-8 obrigatório em todos os JSPs do TaskFlow. --%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<%-- Diretiva taglib: JSTL Core declarada antecipadamente para a Aula 9. --%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TaskFlow — Nova Tarefa</title>
    <style>
        body { font-family: Arial, sans-serif; max-width: 600px; margin: 40px auto; padding: 0 20px; }
        h1 { color: #333; border-bottom: 2px solid #007bff; padding-bottom: 10px; }
        .form-group { margin-bottom: 16px; }
        label { display: block; font-weight: bold; margin-bottom: 4px; color: #555; }
        input[type="text"], textarea, select {
            width: 100%; padding: 8px; border: 1px solid #ccc;
            border-radius: 4px; font-size: 14px; box-sizing: border-box;
        }
        textarea { height: 100px; resize: vertical; }
        .btn { padding: 10px 20px; border: none; border-radius: 4px;
               cursor: pointer; font-size: 14px; text-decoration: none; }
        .btn-primary { background-color: #007bff; color: white; }
        .btn-secondary { background-color: #6c757d; color: white; margin-left: 10px; }
        .erro { color: #dc3545; font-size: 13px; margin-top: 4px; }
        .lista-erros { background-color: #f8d7da; border: 1px solid #f5c6cb;
                       border-radius: 4px; padding: 10px 20px; margin-bottom: 16px; }
    </style>
</head>
<body>

    <h1>Nova Tarefa</h1>

    <%-- Área de exibição de erros de validação.
         O Controller colocará os erros via request.setAttribute("erros", listaDeErros).
         ${not empty erros} verifica se a lista de erros não está vazia.
         Na Aula 9, substituiremos por <c:if> e <c:forEach> para iterar os erros.
         Na Aula 16, implementaremos Bean Validation que preencherá esta seção. --%>
    <div class="lista-erros" style="display: ${not empty erros ? 'block' : 'none'}">
        <strong>Por favor, corrija os erros abaixo:</strong>
        <%-- FASE AULA 9+: <c:forEach items="${erros}" var="erro">
                               <p class="erro">${erro}</p>
                           </c:forEach> --%>
        <p class="erro">${erros}</p>
    </div>

    <%-- Formulário de criação de tarefa.
         action: envia o POST para o Controller em /tasks?action=salvar.
         method="post": os dados vão no corpo da requisição (não na URL).
         O Controller processará o POST e redirecionará para a listagem (padrão PRG).
         O padrão PRG (Post-Redirect-Get) será explicado em detalhes na Aula 12. --%>
    <form action="${pageContext.request.contextPath}/tasks?action=salvar" method="post">

        <%-- Campo: título da tarefa (obrigatório).
             name="titulo": o Controller lerá com request.getParameter("titulo").
             value="${param.titulo}": reexibe o valor digitado em caso de erro de validação.
             ${param.titulo} acessa os parâmetros da requisição diretamente via EL. --%>
        <div class="form-group">
            <label for="titulo">Título <span style="color:red">*</span></label>
            <input type="text"
                   id="titulo"
                   name="titulo"
                   value="${param.titulo}"
                   placeholder="Informe o título da tarefa"
                   maxlength="200">
        </div>

        <%-- Campo: descrição da tarefa (opcional).
             textarea não tem atributo value — o conteúdo fica entre as tags.
             ${param.descricao} reexibe a descrição digitada em caso de erro. --%>
        <div class="form-group">
            <label for="descricao">Descrição</label>
            <textarea id="descricao"
                      name="descricao"
                      placeholder="Descreva a tarefa (opcional)"
                      maxlength="1000">${param.descricao}</textarea>
        </div>

        <%-- Campo: status da tarefa (select com opções fixas).
             Os três status possíveis são: PENDENTE, EM_ANDAMENTO, CONCLUIDA.
             Na Aula 9, usaremos c:choose para marcar o option correto como selected
             quando o formulário for reexibido após um erro de validação. --%>
        <div class="form-group">
            <label for="status">Status</label>
            <select id="status" name="status">
                <option value="PENDENTE">Pendente</option>
                <option value="EM_ANDAMENTO">Em andamento</option>
                <option value="CONCLUIDA">Concluída</option>
            </select>
        </div>

        <%-- Botões de ação.
             "Salvar": submete o formulário via POST.
             "Cancelar": link GET de volta à listagem — sem submeter dados. --%>
        <div class="form-group">
            <button type="submit" class="btn btn-primary">Salvar</button>
            <a href="${pageContext.request.contextPath}/tasks" class="btn btn-secondary">
                Cancelar
            </a>
        </div>

    </form>

    <hr>
    <p style="color: #aaa; font-size: 12px;">
        TaskFlow v1.0 | Aula 8 — Views com JSP
    </p>

</body>
</html>