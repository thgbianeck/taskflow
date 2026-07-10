## Aula 5: Introdução aos Servlets: o coração do Jakarta EE Web {#aula-5-introdução-aos-servlets-o-coração-do-jakarta-ee-web-1  data-source-line="504"}
- Objetivo: Criar o primeiro Servlet funcional do projeto TaskFlow.
- Código Adicionado:
    src/main/java/com/taskflow/controller/HelloServlet.java
    @WebServlet("/hello") com doGet respondendo Hello, TaskFlow! em HTML.
    SobreServlet.java adicionado no exercício (mapeado em /sobre).
    Métodos init() e destroy() adicionados ao HelloServlet no exercício.
- Estado Funcional: ✅ Acessar http://localhost:8080/taskflow/hello exibe
  "Hello, TaskFlow!" gerado dinamicamente pelo Java.
  http://localhost:8080/taskflow/sobre exibe informações do projeto.
- Próximas Etapas: Aula 6 aprofundará o protocolo HTTP, explicará a diferença
  entre GET e POST, ensinará a ler parâmetros da requisição e introduzirá
  os primeiros testes com JUnit 5 seguindo a metodologia TDD.