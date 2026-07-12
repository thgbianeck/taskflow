## Aula 6: Requisições e Respostas HTTP com Servlets e TDD com JUnit 5
- Objetivo: Demonstrar leitura de parâmetros HTTP e introduzir TDD com JUnit 5 e Mockito.
- Código Adicionado:
    build.gradle atualizado com JUnit 5 (junit-jupiter-api 5.10.2,
    junit-jupiter-engine 5.10.2), Mockito (mockito-core 5.11.0,
    mockito-junit-jupiter 5.11.0) e tomcat-embed-core para runtime dos testes.
    src/main/java/com/taskflow/service/SaudacaoService.java — lógica pura testável.
    src/main/java/com/taskflow/controller/SaudacaoServlet.java — @WebServlet("/saudacao").
    src/test/java/com/taskflow/service/SaudacaoServiceTest.java — 5 testes JUnit 5.
    src/test/java/com/taskflow/controller/SaudacaoServletTest.java — 5 testes com Mockito.
    src/test/java/com/taskflow/service/CalculadoraTaxaTest.java — exercício TDD.
    src/main/java/com/taskflow/service/CalculadoraTaxa.java — exercício TDD.
    modulo_01_fundamentos/aula_06/exercicio_06.txt
- Estado Funcional: ✅ /saudacao?nome=Bianeck exibe "Olá, Bianeck!".
  10 testes passando: 5 no SaudacaoServiceTest, 5 no SaudacaoServletTest.
  gradle clean war gera BUILD SUCCESSFUL.
- Próximas Etapas: Aula 7 introduzirá a arquitetura MVC e refatorará a estrutura
  de pacotes do TaskFlow em model/, repository/, controller/ e filter/.