# 🛠️ API REST - AV2 Arquitetura Web

Este projeto consiste em uma API REST desenvolvida com Spring Boot que implementa autenticação e autorização via JWT, controle de acesso por `role`, cadastro e listagem de produtos, testes automatizados, monitoramento com Actuator + Prometheus + Grafana e deploy completo na nuvem com Docker + Render.

---

## 🚀 Tecnologias Utilizadas

- Java 21
- Spring Boot 3.x
- Spring Security (JWT)
- Spring Data JPA
- H2 Database
- Maven
- Swagger OpenAPI 3
- JUnit + Mockito + JMeter
- Spring Boot Actuator
- Prometheus + Grafana
- Docker + Render

---

## 📦 Clonando o Projeto

```bash
git clone https://github.com/k4neca/AV2-ArquitWEB.git
cd NomeDoRepositorio
```

---

## ▶️ Executando a Aplicação Localmente

### Pré-requisitos:

- Java 21
- Maven
- Docker (para Prometheus e Grafana)

### Passos:

```bash
# Compilar o projeto
mvn clean install

# Rodar o projeto
mvn spring-boot:run
```

A API estará disponível em: [http://localhost:8081](http://localhost:8081)

---

## 🔑 Autenticação com JWT

### Endpoints:

- `POST /auth/register` – Registro de novo usuário
- `POST /auth/login` – Retorna o token JWT

> O token deve ser incluído no header das requisições protegidas:

```
Authorization: Bearer SEU_TOKEN
```

---

## 🛍️ Endpoints de Produtos

| Método | Endpoint           | Acesso        | Descrição                   |
|--------|--------------------|---------------|-----------------------------|
| GET    | `/api/produtos`    | Autenticado   | Lista todos os produtos     |
| POST   | `/api/produtos`    | Autenticado   | Cadastra um novo produto    |

---

## 📄 Documentação Swagger

Acesse a documentação interativa em:

👉 [http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html)

---

## 🧪 Testes

### ✅ Testes Unitários com JUnit e Mockito

Execute:

```bash
mvn test
```

### 📊 Testes de Carga com JMeter

1. Abra o arquivo `.jmx` no Apache JMeter.
2. Configure o token JWT no header das requisições.
3. Execute o teste para simular múltiplas requisições.

---

## 📈 Monitoramento com Prometheus + Grafana

### Executar via Docker:

```bash
docker-compose up -d
```

- Prometheus: [http://localhost:9090](http://localhost:9090)
- Grafana: [http://localhost:3001](http://localhost:3001)

📊 Dashboard sugerido: **4701 - JVM (Micrometer)**

---

## ☁️ Deploy no Render

### Backend:

1. Faça push do projeto no GitHub.
2. Acesse [https://render.com](https://render.com) e crie um novo Web Service.
3. Configure o Dockerfile como método de deploy.
4. Aguarde o build e obtenha a URL pública.

---

## 💻 Frontend

Aplicação React integrada com a API, utilizando:

- Tela de login com tema escuro
- Tela de CRUD de produtos com controle por JWT
- Logout funcional

> O frontend consome diretamente a API hospedada no Render.

---

## 🔐 Segurança

- Criptografia de senhas com BCrypt
- Geração e validação de tokens JWT com claims personalizados
- Controle de acesso por perfil (`@PreAuthorize`)

---

## 👨‍💻 Autor

Caio Almeida  
Estudante de Análise e Desenvolvimento de Sistemas  
Disciplina: Arquitetura Web – AV2

---
