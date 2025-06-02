
# 🧪 Aprendendo Integração com APIs REST em Java

Bem-vindo(a) ao meu projeto prático de integração com APIs REST usando **Java puro** com `HttpURLConnection`. Aqui você encontrará um passo a passo completo, com códigos comentados, boas práticas e experiências reais com chamadas HTTP, tudo desenvolvido como parte de um treinamento de desenvolvedor júnior. 🚀

---

## 🧠 O que eu aprendi

✔️ Como fazer requisições HTTP em Java sem frameworks  
✔️ Diferenças entre métodos GET, POST, PUT, DELETE e OPTIONS  
✔️ Como lidar com códigos de status (200, 404, 403, etc)  
✔️ Como enviar e receber JSON com `DataOutputStream` e `BufferedReader`  
✔️ Como interpretar a resposta da API e montar URLs com parâmetros

---

## 🔗 APIs utilizadas

### 1. [API de Entidades](https://apichallenges.eviltester.com/sim/entities) (Stateless)
- Permite testar chamadas simples sem persistência real
- Ideal para aprender os verbos HTTP e tratamento de respostas

### 2. [Simple API](https://apichallenges.eviltester.com/simpleapi/items) (com persistência temporária)
- Permite criação, leitura, atualização e exclusão de dados reais temporários
- Simula um sistema de gerenciamento de livros com ISBN

---

## 🛠️ Ferramentas e Tecnologias

- ✅ **Java 8+**
- ✅ `HttpURLConnection`
- ✅ `BufferedReader`, `InputStreamReader`
- ✅ `DataOutputStream` para envio de dados
- ✅ Conhecimento de JSON e lógica REST

---

## 📁 Estrutura do Projeto

```

📂 src/
├── simapi/         → Exercícios com API de Entidades
├── simpleapi/      → Exercícios com Simple API
├── utils/          → Funções reutilizáveis para requisições

````

---

## 🚀 Passo a passo dos exercícios

### 📄 Exercício 1 – GET todas as entidades

```java
URL url = new URL("https://apichallenges.eviltester.com/sim/entities");
HttpURLConnection conn = (HttpURLConnection) url.openConnection();
conn.setRequestMethod("GET");

int status = conn.getResponseCode();  // → Ex: 200
System.out.println("Status: " + status);

BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
StringBuilder response = new StringBuilder();
String line;
while ((line = reader.readLine()) != null) {
    response.append(line);
}
System.out.println("Resposta: " + response);
````

🧠 **Aprendi:** como abrir uma conexão, definir método GET, ler a resposta e imprimir no console.

---

### 🔎 Exercício 2 – GET entidade específica

```java
URL url = new URL("https://apichallenges.eviltester.com/sim/entities/1");
```

📌 Testei os IDs de 1 a 8. Todos retornaram um JSON com nome e ID únicos.

---

### ❌ Exercício 3 – GET entidade inexistente (Erro 404)

```java
URL url = new URL("https://apichallenges.eviltester.com/sim/entities/13");
```

📢 **Mensagem esperada:**

```
Status: 404  
Resposta: {"errorMessages":["No entity with id 13"]}
```

🧠 **Aprendi:** a verificar e tratar status de erro 404.

---

### 🔍 Exercício 4 – GET com parâmetros na URL

```java
URL url = new URL("https://apichallenges.eviltester.com/sim/entities?categoria=teste&limite=5");
```

🧠 **Aprendi:** como construir URLs com query parameters manualmente.

---

### ➕ Exercício 5 – POST criando uma nova entidade

```java
String json = "{\"name\":\"aluno\"}";
URL url = new URL("https://apichallenges.eviltester.com/sim/entities");

HttpURLConnection conn = (HttpURLConnection) url.openConnection();
conn.setRequestMethod("POST");
conn.setDoOutput(true);
conn.setRequestProperty("Content-Type", "application/json");

DataOutputStream out = new DataOutputStream(conn.getOutputStream());
out.writeBytes(json);
out.flush(); out.close();
```

📢 Resposta:

```
Status: 201  
{"id":11,"name":"aluno"}
```

🧠 **Aprendi:** como enviar dados em JSON e criar novas entidades via POST.

---

### 🕵️ Exercício 6 – GET da entidade recém-criada (ID 11)

✅ Verifiquei a criação com:

```java
URL url = new URL("https://apichallenges.eviltester.com/sim/entities/11");
```

---

### ♻️ Exercício 7 – POST para atualizar entidade

```java
String json = "{\"name\":\"atualizado\"}";
URL url = new URL("https://apichallenges.eviltester.com/sim/entities/10");
```

🧠 **Aprendi:** que o POST também pode ser usado para sobrescrever um recurso existente.

---

### 🛠️ Exercício 8 – PUT para atualizar entidade

```java
conn.setRequestMethod("PUT");
```

🧠 **Aprendi:** PUT substitui o recurso inteiro; POST pode ser parcial ou múltiplo.

---

### 🗑️ Exercício 9 – DELETE de entidade válida

```java
URL url = new URL("https://apichallenges.eviltester.com/sim/entities/9");
conn.setRequestMethod("DELETE");
```

✅ Depois, fiz um GET no ID 9 e recebi 404.

---

### 🚫 Exercício 10 – DELETE inválido (entidade protegida)

```java
URL url = new URL("https://apichallenges.eviltester.com/sim/entities/2");
// Esperado: 403 ou 405
```

🧠 **Aprendi:** sobre recursos protegidos e como a API pode negar ações.

---

### 🧾 Exercício 11 – OPTIONS verificando métodos suportados

```java
conn.setRequestMethod("OPTIONS");
String allow = conn.getHeaderField("Allow");

System.out.println("Métodos Permitidos: " + allow);
```

📢 Resposta:

```
Allow: GET, POST, PUT, DELETE, OPTIONS
```

---

## 📚 CRUD com a Simple API (livros)

### 📘 1. Gerar ISBN

```java
URL url = new URL("https://apichallenges.eviltester.com/simpleapi/randomisbn");
```

### 📝 2. Criar item

```json
{
  "isbn": "868-4-05-021213-7",
  "title": "Livro de Teste",
  "author": "Aluno"
}
```

### ✍️ 3. Atualizar com PUT

```json
{
  "isbn": "868-4-05-021213-7",
  "title": "Atualizado",
  "author": "Aluno Atualizado"
}
```

### 🗑️ 4. Deletar item

```java
URL url = new URL("https://apichallenges.eviltester.com/simpleapi/items/868-4-05-021213-7");
```

📢 Se ISBN não existir:

```
Status: 404
{"errorMessages":["Could not find any instances with items/868-4-05-021213-7"]}
```

---

## ✅ Resumo das habilidades desenvolvidas

| Recurso       | Habilidade Aprendida                 |
| ------------- | ------------------------------------ |
| `GET`         | Buscar dados de forma segura         |
| `POST`        | Criar recursos com JSON              |
| `PUT`         | Atualizar recurso por inteiro        |
| `DELETE`      | Remover recursos (e validar via GET) |
| `OPTIONS`     | Consultar métodos disponíveis        |
| `404` / `403` | Tratar erros com mensagens claras    |

---

## 👨‍💻 Autor

Desenvolvido por **Weslley**
🎓 Estudante de Engenharia de Software
🔗 GitHub: @WeslleySoaresm

