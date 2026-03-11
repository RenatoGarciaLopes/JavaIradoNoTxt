# DRPs - Documento de Requisitos dos Produtos

## Autores

- RENATO GARCIA LOPES (171270-2024)
- GUILHERME BROGIO MACEDO DA SILVA (166473-2024)
- GUSTAVO DE LIMA SOSSAI (173342-2024)
- PEDRO HENRIQUE SILVA OLIVAL (170570-2024)
- ARTUR RIBEIRO BÉRGAMO (169479-2024)
- ANY CAROLINE MELLO MARTINS (178678-2024)

## Visao geral

Este documento organiza os requisitos dos produtos do projeto com base na implementacao atual do codigo-fonte Java. Neste contexto, cada classe principal representa um produto independente.

## Produto 1 - ValidateCPF

- **Objetivo:** Validar numeros de CPF brasileiros, aceitando entrada com ou sem mascara.
- **Entradas:** `cpf` (`String`).
- **Saidas:** Retorno booleano de validacao (`true` para valido, `false` para invalido).
- **Requisitos funcionais:**
- Rejeitar entradas nulas ou em branco.
- Remover caracteres nao numericos antes da validacao.
- Validar se o CPF contem exatamente 11 digitos.
- Rejeitar sequencias de digitos todos iguais.
- Validar os dois digitos verificadores conforme algoritmo de modulo 11.
- **Regras e restricoes:**
- A validacao deve ser realizada por metodo estatico.
- O produto nao depende de banco de dados nem de arquivos.
- **Criterios de aceite:**
- CPF valido formatado retorna `true`.
- CPF valido sem formatacao retorna `true`.
- CPF com digitos verificadores incorretos retorna `false`.
- CPF com todos os digitos iguais retorna `false`.
- CPF nulo, em branco ou nao numerico retorna `false`.

<img src="../ValidateCPF/validate-cpf-class.uml.png" alt="ValidateCPF - Class Diagram" width="300" />
<img src="../ValidateCPF/validate-cpf-use-case.uml.png" alt="ValidateCPF - Use Case Diagram" width="300" />

## Produto 2 - ValidateCNPJ

- **Objetivo:** Validar numeros de CNPJ brasileiros, aceitando entrada com ou sem mascara.
- **Entradas:** `cnpj` (`String`).
- **Saidas:** Retorno booleano de validacao (`true` para valido, `false` para invalido).
- **Requisitos funcionais:**
- Rejeitar entradas nulas ou em branco.
- Remover caracteres nao numericos antes da validacao.
- Validar se o CNPJ contem exatamente 14 digitos.
- Rejeitar sequencias de digitos todos iguais.
- Validar os dois digitos verificadores com pesos definidos.
- **Regras e restricoes:**
- A validacao deve ser realizada por metodo estatico.
- O produto nao depende de banco de dados nem de arquivos.
- **Criterios de aceite:**
- CNPJ valido formatado retorna `true`.
- CNPJ valido sem formatacao retorna `true`.
- CNPJ com digitos verificadores incorretos retorna `false`.
- CNPJ com todos os digitos iguais retorna `false`.
- CNPJ nulo, em branco ou nao numerico retorna `false`.

<img src="../ValidateCNPJ/validate-cnpj-class.uml.png" alt="ValidateCNPJ - Class Diagram" width="300" />
<img src="../ValidateCNPJ/validate-cnpj-use-case.uml.png" alt="ValidateCNPJ - Use Case Diagram" width="300" />

## Produto 3 - ValidateEmail

- **Objetivo:** Validar formato de endereco de e-mail.
- **Entradas:** `email` (`String`).
- **Saidas:** Retorno booleano de validacao (`true` para valido, `false` para invalido).
- **Requisitos funcionais:**
- Rejeitar entradas nulas ou em branco.
- Exigir exatamente um caractere `@`.
- Rejeitar parte local iniciando/terminando com ponto e ocorrencia de pontos consecutivos.
- Exigir dominio com ao menos dois rotulos separados por ponto.
- Validar TLD com no minimo 2 caracteres alfabeticos.
- **Regras e restricoes:**
- A validacao deve ser realizada por metodo estatico.
- O produto nao executa envio de e-mail nem verificacao de existencia de dominio.
- **Criterios de aceite:**
- E-mail em formato valido retorna `true`.
- E-mail sem `@` ou com multiplos `@` retorna `false`.
- E-mail com local-part invalida retorna `false`.
- E-mail com dominio invalido retorna `false`.

<img src="../ValidateEmail/validate-email-class.uml.png" alt="ValidateEmail - Class Diagram" width="300" />
<img src="../ValidateEmail/validate-email-use-case.uml.png" alt="ValidateEmail - Use Case Diagram" width="300" />

## Produto 4 - ValidateLogin

- **Objetivo:** Validar autenticacao de usuario a partir de e-mail e senha.
- **Entradas:** `email` (`String`) e `password` (`String`) informados no construtor.
- **Saidas:** Retorno booleano em `isValid()` (`true` para login valido, `false` para invalido).
- **Requisitos funcionais:**
- Validar formato de e-mail reutilizando `ValidateEmail`.
- Validar senha nao nula, nao vazia e com tamanho entre 8 e 254 caracteres.
- Consultar credenciais em mapa interno de usuarios.
- Retornar `false` quando o usuario nao existir.
- Retornar `true` somente quando senha informada for igual a senha cadastrada no mapa.
- **Regras e restricoes:**
- As credenciais estao em memoria, sem hash e sem criptografia (limitacao atual do projeto).
- O produto nao implementa persistencia de usuarios.
- **Criterios de aceite:**
- Login com e-mail invalido retorna `false`.
- Login com senha fora dos limites retorna `false`.
- Login com usuario inexistente retorna `false`.
- Login com usuario existente e senha correta retorna `true`.

<img src="../ValidateLogin/validate-login-class.uml.png" alt="ValidateLogin - Class Diagram" width="300" />
<img src="../ValidateLogin/validate-login-use-case.uml.png" alt="ValidateLogin - Use Case Diagram" width="400" />

## Produto 5 - ClientRegistration

- **Objetivo:** Registrar dados de cliente em arquivo TXT de forma persistente.
- **Entradas:** `filePath` (`String`) por `setFilePath`; `name`, `cpf` e `email` (`String`) por `save`.
- **Saidas:** Registro adicionado em arquivo de texto no formato `[dd-MM-yyyy'T'HH:mm:ss] CPF | email | nome`.
- **Requisitos funcionais:**
- Permitir configuracao do caminho do arquivo de destino.
- Montar cada linha com timestamp no formato `dd-MM-yyyy'T'HH:mm:ss`.
- Gravar os campos na ordem `cpf`, `email`, `name`, separados por ` | `.
- Criar o arquivo quando ele nao existir.
- Anexar novos registros ao final quando o arquivo ja existir.
- **Regras e restricoes:**
- O produto pode lancar `IOException`.
- O produto nao faz validacao de CPF ou e-mail internamente.
- O produto nao aplica criptografia aos dados.
- **Criterios de aceite:**
- Ao salvar cliente em arquivo inexistente, o arquivo e criado.
- Ao salvar varios clientes, as linhas sao acumuladas sem sobrescrever as anteriores.
- A linha salva contem timestamp e separador conforme formato padrao do projeto.

<img src="../ClientRegistration/client-registration-class.uml.png" alt="ClientRegistration - Class Diagram" width="300" />
<img src="../ClientRegistration/client-registration-use-case.uml.png" alt="ClientRegistration - Use Case Diagram" width="300" />

## Produto 6 - ClientReport

- **Objetivo:** Ler arquivo TXT de clientes e imprimir relatorio em console.
- **Entradas:** `filePath` (`String`) por `setFilePath`.
- **Saidas:** Impressao de linhas no console no formato `[timestamp] CPF | email | nome`.
- **Requisitos funcionais:**
- Ler todas as linhas do arquivo configurado.
- Ignorar linhas vazias.
- Extrair timestamp como o trecho ate o primeiro caractere `]` (incluindo `]`).
- Ignorar linhas que nao contenham caractere `]`.
- Extrair os campos restantes no formato `CPF | email | nome`.
- Ignorar linhas que nao possuam ao menos CPF, email e nome apos o timestamp.
- Manter o timestamp original na saida.
- **Regras e restricoes:**
- O produto pode lancar `IOException`.
- O produto realiza saida apenas em console.
- **Criterios de aceite:**
- Linhas com timestamp valido e campos completos sao impressas no console.
- Linhas sem caractere `]` sao ignoradas.
- Linhas sem quantidade minima de campos apos o timestamp sao ignoradas.
- Linhas em branco nao aparecem no relatorio.

<img src="../ClientReport/client-report-class.uml.png" alt="ClientReport - Class Diagram" width="300" />
<img src="../ClientReport/client-report-use-case.uml.png" alt="ClientReport - Use Case Diagram" width="300" />

## Produto 7 - StatesCSVReader

- **Objetivo:** Ler arquivo CSV de estados brasileiros e exibir relatorio tabular no console.
- **Entradas:** `csvPath` (`Path`) informado no construtor.
- **Saidas:** Lista de estados (`List<String[]>`) no metodo `read` e impressao tabular no console no metodo `display`.
- **Requisitos funcionais:**
- Abrir arquivo CSV e descartar a primeira linha (cabecalho).
- Ignorar linhas em branco.
- Separar colunas por `;`.
- Aceitar somente linhas com 3 colunas.
- Remover espacos em branco das 3 colunas lidas.
- Mapear cada linha valida para vetor de `String` no formato `[stateCode, abbreviation, name]`.
- Exibir cabecalho e lista formatada de estados no console.
- **Regras e restricoes:**
- O produto pode lancar `IOException`.
- O produto depende de arquivo CSV local.
- **Criterios de aceite:**
- CSV valido com cabecalho e linhas corretas e lido e exibido.
- Linhas em branco ou com quantidade incorreta de colunas sao ignoradas.
- Caminho invalido resulta em erro de leitura tratado por quem consome a classe.

<img src="../StatesCSVReader/states-csv-reader-class.uml.png" alt="StatesCSVReader - Class Diagram" width="300" />
<img src="../StatesCSVReader/states-csv-reader-use-case.uml.png" alt="StatesCSVReader - Use Case Diagram" width="300" />

## Dependencias entre produtos

- `ValidateLogin` depende de `ValidateEmail` para validacao de e-mail.
- Os demais produtos operam de forma independente no escopo atual.

## Escopo fora da versao atual

- Persistencia em banco de dados.
- Criptografia e hash de senha.
- Interface grafica ou API HTTP.
- Internacionalizacao de mensagens.
