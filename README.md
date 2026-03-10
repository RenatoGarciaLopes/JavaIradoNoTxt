# UniCV – Boas Práticas de Programação e Padrões de Projeto

**Trabalho avaliativo prático – Avaliações Parciais (BPP)**  
Disciplina: **Boas Práticas de Programação e Padrões de Projeto**  
Professor: **Gercino Sátiro Pedro Filho** – Curso: **ESW – 2026/1**

Este repositório reúne os artefatos (código Java, diagramas UML e documentos) produzidos para as avaliações parciais da disciplina, seguindo a proposta de desenvolvimento em **Java 21** utilizando apenas editor de texto simples e compilação via linha de comando (`javac`).

## Objetivo do projeto

O objetivo geral é **criar classes em Java 21, documentos de requisitos e diagramas UML** conforme a demanda de um cliente fictício, praticando:

- **Boas práticas de programação** (organização de código, responsabilidade única, coesão e acoplamento baixo);
- **Uso de UML** (Casos de Uso e Diagrama de Classes);
- **Processo de levantamento de requisitos e validação com o “cliente”**.

De acordo com o documento `docs/Projeto BPP Avaliações Parciais.pdf`, o cliente demanda, entre outras, as seguintes funcionalidades:

- **Validação de CPF**  
- **Validação de CNPJ**  
- **Validação de e-mail**  
- **Validação de login**  
- **Gravação de registros de clientes em arquivo TXT**  
- **Leitura de arquivo CSV**  
- **Impressão de relatório de clientes gravados**

## Estrutura do repositório

- **`docs/Projeto BPP Avaliações Parciais.pdf`**  
  Documento oficial da avaliação, descrevendo objetivos, dinâmica (Estudo de Caso / PBL), prazos e **demandas do cliente**.

- **`ClientRegistration/ClientRegistration.java`**  
  Classe voltada para a **gravação de registros de clientes em arquivo TXT**, atendendo parcialmente à demanda de “Gravação de registros de clientes de forma persistente em arquivo TXT”. E seus respectivos diagramas de caso de uso e classe.

- **`ClientReport/ClientReport.java`**  
  Classe voltada para a **impressão de relatório de clientes gravados em arquivo TXT**. E seus respectivos diagramas de caso de uso e classe.

- **`StatesCSVReader/StatesCSVReader.java`**  
  Classe voltada para a **leitura de arquivo CSV** e **impressão de relatório de clientes gravados em arquivo TXT**. E seus respectivos diagramas de caso de uso e classe.

- **`ValidateCNPJ/ValidateCNPJ.java`**
  Classe responsável por **validar o formato de CNPJ**. E seus respectivos diagramas de caso de uso e classe.

- **`ValidateCPF/ValidateCPF.java`**
  Classe responsável por **validar o formato de CPF**. E seus respectivos diagramas de caso de uso e classe.

- **`ValidateEmail/ValidateEmail.java`**  
  Classe responsável por **validar o formato de e‑mail**. E seus respectivos diagramas de caso de uso e classe.

- **`ValidateLogin/ValidateLogin.java`**  
  Classe responsável por **validar login de usuário** com base em e‑mail e senha. E seus respectivos diagramas de caso de uso e classe.

## Como compilar e executar (linha de comando)

O projeto segue a proposta do documento, utilizando **compilação via linha de comando**. Abaixo um exemplo genérico de compilação e uso das classes já presentes (ajuste os comandos conforme seu ambiente):

1. **Compilar todas as classes** (a partir da pasta raiz do projeto):

```bash
javac ClientRegistration/*.java ClientReport/*.java StatesCSVReader/*.java ValidateCNPJ/*.java ValidateCPF/*.java ValidateEmail/*.java ValidateLogin/*.java
```

2. **Criar uma classe de teste (opcional)**  
Como as classes atuais não possuem `main`, recomenda-se criar uma classe de teste simples, por exemplo `TestValidateLogin.java`, que instancie `ValidateLogin` e invoque `isValid()`.  
Depois, compile e execute:

```bash
javac TestValidateLogin.java
java TestValidateLogin
```

### Diagramas UML

Os diagramas foram descritos no formato **Mermaid**, o que permite visualizar os diagramas diretamente em ferramentas compatíveis com Mermaid (como o próprio GitHub).

- Casos de Uso:
  - `ValidateLogin/validate-login-use-case.uml.mermaid`
- Diagrama de Classes:
  - `ValidateLogin/validate-login-class.uml.mermaid`

Esses diagramas complementam o código e o documento PDF, servindo como documentação visual do sistema.
