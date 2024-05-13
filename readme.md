# Projeto de um micro serviço para gestão de Matriculas - README

<img width="30%"  src="./imageREADME/CompassLogo.svg"/>

Este é o repositório do projeto micro serviço para gestão de matriculas desenvolvido como parte do estágio na Compass UOL. Abaixo estão as informações do projeto.

## Desenvolvedores

- Elias Mathias Sand: [elias.sand.pb@compasso.com.br](mailto:elias.sand.pb@compasso.com.br)
- Joao Pedro Golenia: [joao.golenia.pb@compasso.com.br](mailto:joao.golenia.pb@compasso.com.br)
- Jonas Roberto Leandro: [jonas.leandro.pb@compasso.com.br](mailto:jonas.leandro.pb@compasso.com.br)
- Lucas Henrique Teixeira Ribeiro [lucas.teixeira.pb@compasso.com.br](mailto:lucas.teixeira.pb@compasso.com.br)

## Instrutores (PO)

- **Rafael Henrique Menegon:** [@devrafamenegon](https://github.com/devrafamenegon)
- **Edmar Miller Gabriel:** [edmarmiller@hotmail.com](mailto:edmarmiller@hotmail.com)
- **Cleiton Fiatkoski Balansin:** [cleiton.balansin@compasso.com.br](mailto:cleiton.balansin@compasso.com.br)
- **Mateus de Oliveira e Silveira:** [@mosilveira](https://github.com/mosilveira)
- **João Caetano Nicoli Tavares:** [jaonicoli@gmail.com](mailto:jaonicoli@gmail.com)
- **Rafael Luz de Queiroz**: [rafinhalq@gmail.com](mailto:rafinhalq@gmail.com)
- **Lucas Magno Peixoto Lima**: [lucasmagno695@gmail.com](mailto:lucasmagno695@gmail.com)
- **Manoel Rosa**: [guiguel@gmail.com](mailto:guiguel@gmail.com)

## Período

- Período de desenvolvimento: 06/05/2024 até 13/05/2024 às 12:00;

## Itens Obrigatórios

- Utilização do Git/GitHub com duas branches fixas: main e dev. (A main só deve receber dados advindas de dev);
- Padronização das branches de funcionalidades com o formato: [nome-do-microserviço]/[feature]-[nome_da_funcionalidade];
- Adoção de commits semânticos;
- Inclusão de documentação abrangente no README (obrigatório a inclusão de uma lista com todos os endpoints da aplicação);
- Uso de boas práticas;
- Utilize banco de dados H2 em arquivo independentes para cada aplicação;
- Todos os micro serviços devem ter documentação de API (Swagger);
- Comunicação entre micro serviços deve ser HTTP (OpenFeign);
- Crie uma collection do Postman e anexe ela ao projeto no GitHub, contendo todos os endpoints e com uma boa organização;
- Os micro serviços devem ter cobertura de teste de no mínimo 30%;
- Todos os endpoints devem obedecer as finalidades de funcionamento dos métodos HTTP, assim como os retornos HTTP status code coerentes.

## Prazo

- O envio do repositório do desafio deve ser enviado até às 12hrs do dia 13/05.

## Requisitos

### Entidades

Aluno: nome, cpf, data nascimento, sexo, ativo;

### Funcionalidades

1. Cadastrar alunos;
2. Matricular alunos a determinados cursos;
3. Inativar a matrícula de um aluno a curso;
4. Consultar alunos matriculados em um curso;
    - **Exemplo de Json de retorno:**
    
    ```json
    {
    	"curso": "Economia familiar",
    	"professor": "Maria Muller",
    	"totalAlunos": 2,
    	"alunos": [
    		{"nome": "Adriano Alves", "sexo": "M", "ativo": true},
    		{"nome": "Beatriz Bellon", "sexo": "F", "ativo": false}
    	]
    }
    ```
    
5. Inativar um aluno;

### Funcionalidades Adicionais

- Aluno pode ser ativado novamente;
- Matricula pode ser ativada novamente se o curso possuir vagas disponívels;
- Quando aluno for ativado novamente, todas as matrículas dele com vagas disponíveis serão ativadas;
- A matricula de um aluno inativado não poder ser ativada;

### Regras

- Apenas alunos ativos podem se matricular em cursos ativos;
- Não deve ser possível o cadastro de CPF iguais;
- Deve-se criar novas entidades e/ou campos caso necessário;
- Deve-se criar todos os endpoints necessários;
- Apenas 10 alunos ativos por curso é permitido;
- Se um aluno for inativado, todas as suas matrículas também deveram ser inativadas;
- Todas as validações que o sistema possui devem retornar erros específicos com detalhamento sobre a validação;

## Etapas de Desenvolvimento

- Criação do pom.xml com as dependências: Spring Data JPA, Spring Web, Spring Boot DevTools, Lombok, H2 Database, ModelMapper, SpringDoc, Validation, OpenFeign;

- Criação da estrutura básica do projeto com as entidades;

- Configuração no application.properties do H2 e JPA;

- Configuração do OpenFeign para realizar as requisições na api de cursos;

- Implementação dos endpoints solicitados;

- Configuração do Locale no application.properties e criação da classe de configuração do fuso horário;

- Tratamento das exceções;

- Criação da classe SpringDoc para configurar o swagger e adicionar das anotações;


## Utilização

- Endereço base para requisições: http://localhost:9000;
- Endereço para documentação Swagger: http://localhost:9000/docs-matriculas-api.html;

- Endereço para acesso do banco de dados h2: http://localhost:9000/h2-matriculas;
- Acesso h2:
- JDBC URL: jdbc:h2:file:./matriculas;
- User Name: root;
- Password: 1234;


### Endpoints - Alunos

**POST** - http://localhost:9000/api/v1/alunos
- Cadastrar um novo aluno;
- No corpo da requisição deve ser informato o nome, cpf, data de nascimento e sexo do aluno;

Ex. de dados necessários no body:
```Json
{
  "nome": "string",
  "cpf": "string",
  "dataNascimento": "13-05-2024",
  "sexo": "M"
}
```

<br></br>

**PATCH** - http://localhost:9000/api/v1/alunos/{cpf}
- Alterar o status de um aluno;
- No endereço url deve ser informado o cpf do aluno já cadastrado;

<br></br>

### Endpoints - Matriculas

**POST** - http://localhost:9000/api/v1/matriculas
- Cadastra uma nova matricula de um aluno a um curso;
- No corpo da requisição deve ser informato o nome do curso, e o cpf do aluno cadastrado;

Ex. de dados necessários no body:
```Json
{
  "curso": "string",
  "cpfAluno": "string"
}
```

<br></br>

**PATCH** - http://localhost:9000/api/v1/matriculas/{id}
- Altera o status de uma matrícula pelo id;
- No endereço url deve ser informado o id da matricula cadastrada;

<br></br>

**GET** - http://localhost:9000/api/v1/matriculas/{curso}
- Lista todas as informações das matrículas realizadas para o curso pesquisado;
- No endereço url deve ser informado o nome do curso;

Ex. dados retornados:
```Json
{
  "curso": "string",
  "professor": "string",
  "totalAlunos": 0,
  "alunos": [
    {
      "nome": "string",
      "sexo": "string",
      "ativo": true
    }
  ]
}
```