# 🎥 **Cinema Application**

![Java](https://img.shields.io/badge/Java-17-blue?style=flat-square&logo=java)  
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0-green?style=flat-square&logo=spring)  
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-14-blue?style=flat-square&logo=postgresql)  
![Swagger](https://img.shields.io/badge/Swagger-API-orange?style=flat-square&logo=swagger)

**Cinema** — это приложение для управления кинотеатрами, предоставляющее функции работы с фильмами, актёрами, сеансами и пользователями. Построено с использованием современных технологий, таких как **Spring Boot** и **Hibernate**.

---

## 🚀 **Функциональные возможности**

- 📽️ Управление фильмами, актёрами и расписаниями сеансов.
- 👥 Регистрация и управление пользователями.
- 🔒 Защита API с использованием **Spring Security**.
- 📄 API-документация через **Swagger UI**.
- 📦 Обработка данных в формате **JSON**.

---

## 🛠️ **Технологии**

| **Технология**    | **Описание**                              |
| ----------------- | ----------------------------------------- |
| Java 17           | Основной язык разработки.                 |
| Spring Boot       | Бэкенд-фреймворк для создания API.        |
| Spring Security   | Защита API: аутентификация и авторизация. |
| Hibernate         | ORM для работы с базой данных.            |
| PostgreSQL        | Реляционная база данных.                  |
| Swagger (OpenAPI) | Инструмент для документирования API.      |

---

## 📄 **Документация API**

Приложение предоставляет удобный интерфейс API, задокументированный с использованием **Swagger**.

📍 **Доступ к документации:**

```plaintext
http://localhost:8080/swagger-ui.html
```

- 🔍 Просмотр доступных API-эндпоинтов.
- 🧪 Тестирование запросов в интерфейсе.
- 📘 Подробная информация об API.

---

## 📦 **Установка и запуск**

### 1️⃣ Склонируйте репозиторий:

```bash
git clone https://github.com/Yersdev/Cinema.git
cd Cinema
```

### 2️⃣ Настройте базу данных:

- Убедитесь, что **PostgreSQL** запущен.
- Создайте базу данных и настройте подключение в файле `src/main/resources/application.properties`.

### 3️⃣ Запустите приложение:

```bash
./mvnw spring-boot:run
```

### 4️⃣ Доступ к приложению:

- Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).
- Приложение: [http://localhost:8080](http://localhost:8080).

---

## 🛡️ **Безопасность**

API защищён с помощью **Spring Security**:

- ✅ Аутентификация пользователей.
- 🔐 Разделение ролей и доступов.

---

## 🤝 **Вклад в проект**

Мы будем рады вашим улучшениям!

1. Форкните репозиторий.
2. Создайте ветку с улучшениями:
   ```bash
   git checkout -b feature/your-feature
   ```
3. Сделайте commit и push:
   ```bash
   git commit -m "Добавлена новая функциональность"
   git push origin feature/your-feature
   ```
4. Отправьте **Pull Request**.

---

## 📧 **Контакты**

📮 Если у вас есть вопросы, свяжитесь с нами: **yersultanamangeldindev@gmail.com**
