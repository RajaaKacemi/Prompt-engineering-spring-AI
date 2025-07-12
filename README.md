# 🧠 Prompt Engineering with Spring AI

This project is a backend application developed as part of a lab assignment at **ENSET**, exploring the concepts of **Prompt Engineering** and integrating **Spring AI** with the **OpenAI API**.

The main goal is to dynamically generate **text** and **images** based on user input using AI models, and structure the responses using Java `record`s. The app also supports reading and processing **local images** from a designated folder.

---

## 🚀 Features

- ✅ Text generation using OpenAI based on custom user prompts.
- ✅ Structuring responses into clean Java `record` types.
- ✅ Image generation from textual prompts.
- ✅ Reading and extracting data from local images (stored in the `images/` folder).

---

## 🛠️ Technologies Used

- **Java 21**
- **Spring Boot 3.5.3**
- **Spring Web**
- **Spring AI 1.0.0**
- **OpenAI API**
- **Lombok**
- **Springdoc OpenAPI**
- **H2 in-memory database**
- **Maven**

---

## ⚙️ Configuration

Before running the project, set your OpenAI API key in the `application.properties` file:

```properties
spring.ai.openai.api-key=sk-xxxxxxxxxxxxxxxxxxxxxxxxxxxx
