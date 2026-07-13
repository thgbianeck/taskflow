## Aula 8: Jakarta Server Pages: criando as Views com JSP {#aula-8-jakarta-server-pages-criando-as-views-com-jsp-1  data-source-line="780"}
- Objetivo: Criar as páginas JSP que formarão a View do TaskFlow.
- Código Adicionado:
    src/main/webapp/WEB-INF/views/task/list.jsp — tabela de listagem com EL e estrutura HTML.
    src/main/webapp/WEB-INF/views/task/form.jsp — formulário de criação com campos titulo,
      descricao e status, usando ${param.*} para reexibição após erro.
    src/main/webapp/WEB-INF/views/task/detail.jsp — página de detalhe (exercício).
    src/main/java/com/taskflow/controller/TaskServlet.java — atualizado com forward()
      para list.jsp e form.jsp, método auxiliar encaminhar(), roteamento por action.
    modulo_02_essencial/aula_08/exercicio_08.txt
- Estado Funcional: ✅ /tasks exibe list.jsp com mensagem de lista vazia.
  /tasks?action=criar exibe form.jsp com o formulário de criação.
  /tasks?action=detalhe exibe detail.jsp com campos vazios (Model ainda não existe).
  /taskflow/WEB-INF/views/task/list.jsp retorna HTTP 403 (protegido corretamente).
  gradle clean test war gera BUILD SUCCESSFUL com 13 testes passando.
- Próximas Etapas: Aula 9 adicionará JSTL ao projeto, substituindo a linha
  placeholder da tabela por <c:forEach> iterando sobre ${tasks}, e adicionando
  <c:if> para exibição condicional da mensagem de lista vazia.