// Pacote controller: camada responsável por receber requisições HTTP,
// coordenar o trabalho entre Model e View, e não fazer nada além disso.
package com.taskflow.controller;

// Importações do Jakarta EE.
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Importação Java padrão.
import java.io.IOException;

// Mapeia este Servlet para a URL /tasks.
// Todas as requisições para /taskflow/tasks passam por aqui.
@WebServlet("/tasks")
public class TaskServlet extends HttpServlet {

    // Constantes para os caminhos dos JSPs.
    // Usar constantes evita erros de digitação e facilita refatoração futura.
    // Todos os caminhos começam com /WEB-INF/ — pasta protegida do servidor.
    private static final String VIEW_LIST = "/WEB-INF/views/task/list.jsp";
    private static final String VIEW_FORM = "/WEB-INF/views/task/form.jsp";

    // doGet: responde a requisições GET em /tasks.
    // FASE ATUAL (Aula 8): encaminha diretamente para o list.jsp.
    //   Não há dados ainda — o Model (TaskRepository) será criado na Aula 10.
    // FASE AULA 11 (completo): lerá o parâmetro "action", buscará tarefas
    //   no TaskRepository e encaminhará para o JSP correto com dados reais.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lê o parâmetro "action" da URL para determinar o que fazer.
        // Exemplos de URL: /tasks (sem action → lista), /tasks?action=criar
        // Se "action" não for enviado, getParameter retorna null.
        // O operador || garante que null seja tratado como "listar".
        String action = request.getParameter("action");
        if (action == null) {
            action = "listar";
        }

        // Roteamento simples baseado no valor do parâmetro "action".
        // FASE ATUAL: apenas "listar" e "criar" estão implementados.
        // FASE AULA 11+: "detalhe", "editar", "remover" serão adicionados.
        switch (action) {
            case "criar":
                // Encaminha para o formulário de criação sem dados pré-preenchidos.
                encaminhar(request, response, VIEW_FORM);
                break;

            case "listar":
            default:
                // FASE ATUAL: encaminha para a listagem sem dados reais.
                // FASE AULA 11: chamará taskRepository.findAll() antes do forward.
                // request.setAttribute("tasks", taskRepository.findAll());
                encaminhar(request, response, VIEW_LIST);
                break;
        }
    }

    // doPost: responde a requisições POST em /tasks.
    // FASE ATUAL: ainda não implementado — será adicionado na Aula 12.
    // FASE AULA 12: processará a criação, edição e remoção de tarefas.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Define o encoding da requisição para suportar acentos nos parâmetros POST.
        // Deve ser chamado ANTES de qualquer request.getParameter().
        request.setCharacterEncoding("UTF-8");

        // Por enquanto, redireciona de volta para a listagem.
        // Na Aula 12, este método processará os dados do formulário.
        response.sendRedirect(request.getContextPath() + "/tasks");
    }

    // Método auxiliar para encaminhar a requisição para um JSP.
    // Extrai o padrão repetitivo de obter o RequestDispatcher e chamar forward().
    // RequestDispatcher.forward() é interno ao servidor:
    //   - A URL no navegador NÃO muda (diferente do sendRedirect).
    //   - Os atributos do request são preservados (o JSP pode acessar via EL).
    //   - O JSP tem acesso ao mesmo request e response do Servlet.
    private void encaminhar(HttpServletRequest request,
                            HttpServletResponse response,
                            String caminhoJsp)
            throws ServletException, IOException {

        // Obtém o RequestDispatcher para o caminho do JSP especificado.
        // O caminho começa com / relativo à raiz do WAR (src/main/webapp/).
        RequestDispatcher dispatcher = request.getRequestDispatcher(caminhoJsp);

        // Encaminha a requisição e a resposta para o JSP.
        // A partir deste ponto, o JSP assume o controle da resposta.
        // NUNCA escreva no response depois de chamar forward() — causará IllegalStateException.
        dispatcher.forward(request, response);
    }
}