# 🎮 GameLib

![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.7-brightgreen?logo=spring&logoColor=white)
![Status](https://img.shields.io/badge/status-em%20desenvolvimento-blue)
![Projeto](https://img.shields.io/badge/tipo-projeto%20pessoal-purple)

**API REST** para gerenciamento de uma biblioteca pessoal de jogos, com integração à API externa **RAWG** e persistência em banco de dados.

O projeto permite buscar jogos na RAWG, importar jogos para a biblioteca local, classificar por status, marcar favoritos e registrar uma avaliação pessoal com nota e comentário.

---

## 🚧 Status do projeto

***Projeto em desenvolvimento.***

Funcionalidades já implementadas:

- Busca de jogos na API externa RAWG.
- Cadastro manual de jogos no banco local.
- Importação de jogos da RAWG para o banco local.
- Listagem de jogos salvos.
- Filtro por status e favorito.
- Atualização de status e favorito.
- Avaliação pessoal com nota e comentário.
- Tratamento de erros da API.
- Documentação com Swagger/OpenAPI.

---

## 🛠️ Tecnologias utilizadas

- Java 21
- Spring Boot 3.5.7
- Spring Web
- Spring Data JPA
- H2 Database
- Gradle
- Swagger/OpenAPI
- RAWG API

---

## ▶️ Como rodar o projeto localmente

### 1. Clonar o repositório

```bash
git clone https://github.com/brusoncini/game-lib.git
```

```bash
cd game-lib
```

### 2. Configurar a chave da RAWG

O projeto usa a variável de ambiente `RAWG_API_KEY`.

No PowerShell, você pode configurar (temporariamente):

```powershell
$env:RAWG_API_KEY="sua-chave-da-rawg-aqui"
```

Depois rode o projeto no mesmo terminal.

### 3. Rodar o projeto

No Windows PowerShell:

```powershell
.\gradlew bootRun
```

A aplicação será iniciada em:

```txt
http://localhost:8080
```

---

## 💻 Swagger

A documentação da API pode ser acessada em:

```txt
http://localhost:8080/swagger-ui/index.html
```

O JSON da documentação fica disponível em:

```txt
http://localhost:8080/v3/api-docs
```

---

## 🎲 H2 Console

Durante o desenvolvimento, o projeto utiliza banco H2.

Acesse:

```txt
http://localhost:8080/h2-console
```

---

## ⚠️ Status permitidos para jogos

Os status são controlados por enum. Os valores aceitos são:

```txt
QUERO_JOGAR
JOGANDO
JOGADO
ZERADO
ABANDONADO
```

---

## 📍 Endpoints principais

### RAWG API externa

#### Buscar jogos por nome

```http
GET /api/rawg/jogos?nome=minecraft
```

#### Buscar jogo da RAWG por ID

```http
GET /api/rawg/jogos/{idRawg}
```

---

### Jogos salvos no banco local

#### Listar jogos salvos

```http
GET /api/jogos
```

#### Filtrar por status

```http
GET /api/jogos?status=ZERADO
```

#### Filtrar por favorito

```http
GET /api/jogos?favorito=true
```

#### Filtrar por status e favorito

```http
GET /api/jogos?status=ZERADO&favorito=true
```

#### Buscar jogo salvo por ID local

```http
GET /api/jogos/{id}
```

#### Salvar jogo manualmente

```http
POST /api/jogos
```

Exemplo de body:

```json
{
  "nome": "Hollow Knight",
  "genero": "Metroidvania",
  "plataforma": "PC",
  "favorito": true,
  "status": "ZERADO"
}
```

#### Atualizar jogo completo

```http
PUT /api/jogos/{id}
```

Exemplo de body:

```json
{
  "idRawg": 9767,
  "nome": "Hollow Knight",
  "genero": "Metroidvania",
  "plataforma": "PC",
  "favorito": true,
  "status": "ZERADO",
  "notaPessoal": 10,
  "comentario": "Jogo excelente."
}
```

#### Remover jogo

```http
DELETE /api/jogos/{id}
```

---

### Importação de jogos

#### Importar jogo da RAWG para o banco local

```http
POST /api/jogos/importar/{idRawg}
```

---

### Atualizações parciais (PATCH)

#### Atualizar status

```http
PATCH /api/jogos/{id}/status?status=ZERADO
```

#### Atualizar favorito

```http
PATCH /api/jogos/{id}/favorito?favorito=true
```

#### Atualizar avaliação pessoal

```http
PATCH /api/jogos/{id}/avaliacao
```

Exemplo de body:

```json
{
  "notaPessoal": 10,
  "comentario": "Zerei e achei maravilhoso, mas difícil."
}
```

Também é possível atualizar apenas a nota:

```json
{
  "notaPessoal": 8
}
```

Ou apenas o comentário:

```json
{
  "comentario": "Gostei bastante, mas achei o começo lento."
}
```

A nota pessoal deve estar entre 0 e 10.

---

## 🔜️ Próximos passos

- Adicionar PostgreSQL como banco para ambiente de produção.
- Criar perfis de ambiente, como `dev` e `prod`.
- Melhorar a documentação dos controllers no Swagger.
- Criar testes automatizados.
- Adicionar paginação na listagem de jogos.
- Adicionar busca local por nome, gênero e plataforma.

---

## 👩🏻‍💻 Feito por

Desenvolvido com ❤️ por [Bruna Soncini](https://www.linkedin.com/in/brunasoncini/).
