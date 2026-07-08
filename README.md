# TaskFlow — Sistema de Gerenciamento de Tarefas

## Descrição
Projeto prático incremental desenvolvido ao longo do curso
"Jakarta EE 11 com Java 21, Gradle, JUnit 5 e VS Code".
O objetivo é construir, do zero, uma aplicação web completa
em arquitetura MVC usando os pilares fundamentais do Jakarta EE 11.

## Tecnologias
- Jakarta EE 11
- Java 21
- Gradle (build tool)
- GlassFish 7 (servidor de aplicações)
- JUnit 5 (testes com metodologia TDD)
- H2 Database (banco de dados em memória para JPA)
- VS Code (editor de código)
- Windows 11

## Estrutura
Cada módulo possui uma pasta com as aulas correspondentes.
Os arquivos Java contêm o código desenvolvido e comentado linha a linha.
A pasta exercicios/ contém os desafios de cada aula.
O arquivo log_estado_projeto.md registra o progresso do projeto.

## Como Executar
1. Certifique-se de ter o Java 21 e o Gradle instalados.
2. Na raiz do projeto, execute: gradle war
3. Copie o arquivo .war gerado em build/libs/ para a pasta
   deployments do GlassFish 7.
4. Acesse: http://localhost:8080/taskflow

## Módulos
- Módulo 1: Fundamentos e Ambiente (Aulas 1-6)
- Módulo 2: HTTP, MVC e CRUD em Memória (Aulas 7-12)
- Módulo 3: Persistência, Validação e Finalização (Aulas 13-18)
