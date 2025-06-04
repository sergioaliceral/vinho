# 🍷 Microserviço de Análise de Compras de Vinhos

Este projeto é um microserviço desenvolvido em **Spring Boot 3.2.5** que consome dados de clientes e produtos via **Feign Client**, calcula informações de compras e fornece recomendações personalizadas de vinhos com base no histórico dos clientes.

---

## 🔧 Tecnologias Utilizadas

- Java 17
- Spring Boot 3.2.5
- Spring Cloud 2023.0.1
- OpenFeign
- Springdoc OpenAPI 2.5.0
- JUnit 5
- Maven

---

## ▶️ Como Executar o Projeto Localmente

### Pré-requisitos

- Java 17 instalado
- Maven 3.8+ instalado

### Passos

```bash
# 1. Clone o projeto
git clone https://github.com/sergioaliceral/vinho.git
cd vinho

# 2. Compile e execute
./mvnw spring-boot:run
```

A aplicação estará disponível em: [http://localhost:8080](http://localhost:8080)

---

## 📘 Documentação da API (Swagger)

Disponível automaticamente após subir o serviço:

👉 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)  
👉 Ou diretamente em: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

---

## 📦 Endpoints Disponíveis

### 1. `GET /api/compras`

Retorna lista de compras com:

- Nome e CPF do cliente
- Tipo do vinho, safra, ano da compra
- Quantidade, preço unitário, valor total  
- Ordenado de forma crescente pelo valor total da compra

---

### 2. `GET /api/maior-compra/{ano}`

Retorna a maior compra realizada no ano informado.  
Exemplo:  
```http
GET /api/maior-compra/2023
```

---

### 3. `GET /api/clientes-fieis`

Retorna os **Top 3 clientes mais fiéis** com base em:

- Maior número de compras
- Maior valor total gasto

---

### 4. `GET /api/recomendacao/cliente/tipo`

Retorna uma lista com a **recomendação de tipo de vinho** para cada cliente, baseado na frequência de compras por tipo.

---

## 🔗 Fontes dos Dados (Mocks)

- Lista de Produtos:  
  [produtos.json](https://rgr3viiqdl8sikgv.public.blob.vercel-storage.com/produtos-mnboX5IPl6VgG390FECTKqHsD9SkLS.json)
- Lista de Clientes e Compras:  
  [clientes.json](https://rgr3viiqdl8sikgv.public.blob.vercel-storage.com/clientes-Vz1U6aR3GTsjb3W8BRJhcNKmA81pVh.json)

---

## 🧪 Testes Automatizados

Os testes cobrem:

- Camada de serviço (com implementações in-memory)
- Casos de uso (Use Cases)
- Lógica de negócio

### Executar testes:

```bash
./mvnw test
```

---

## ⚠️ Fallback de Serviços

Caso os serviços externos estejam fora do ar, será retornada uma exceção clara com status `503` (`Service Unavailable`), com mensagem:

```
Serviço Cliente indisponível.
```

---

## ✅ Status do Projeto

✔️ Finalizado e pronto para deploy.  
📂 Repositório: [https://github.com/sergioaliceral/vinho](https://github.com/sergioaliceral/vinho)

---

## ✍️ Autor

Sérgio — Desenvolvedor Java Backend  
Contato: [sergio@aliceral.com](mailto:sergio@aliceral.com)

---
