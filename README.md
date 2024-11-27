# Cinema

# 🎬 Cinema

**Cinema** — это проект веб-приложения для управления фильмами, актёрами, пользователями и отзывами. Данный проект разработан в соответствии с требованиями современного технического задания, включая использование Spring Boot, PostgreSQL и других технологий.

## 📋 Описание

Проект предоставляет функционал видеотеки, где пользователи могут взаимодействовать с информацией о фильмах, добавлять отзывы и просматривать данные о режиссёрах и актёрах. Администраторы могут управлять содержимым системы и доступом пользователей.

### Основные возможности:

- **Для пользователей:**

  - Регистрация и авторизация с использованием Spring Security.
  - Просмотр фильмов по году, жанру или стране.
  - Просмотр информации об актёрах, снимавшихся в выбранном фильме.
  - Добавление отзывов и оценок для фильмов.

- **Для администраторов:**
  - Управление фильмами, актёрами и режиссёрами.
  - Управление пользователями.
  - Доступ к аналитике и другим административным функциям.

## 🛠️ Технические требования

### Структура проекта

- Проект разделён на два модуля:
  1. **common**: содержит общие классы и утилиты.
  2. **service**: включает бизнес-логику и взаимодействие с данными.
- Используется Spring Boot с embedded Tomcat.
- Сборка осуществляется через Apache Maven.

### Слой Repository/DAO

- СУБД: PostgreSQL.
- ORM: Hibernate с маппингом не менее 5 сущностей.
- Поддержка запросов-фильтров с пагинацией (HQL, Criteria API, Querydsl).
- Оптимистическая блокировка сущностей.
- Пароли пользователей хранятся в зашифрованном виде.

### Слой Service

- Поддержка транзакций через `@Transactional`.
- Логирование всех вызовов методов с использованием Spring AOP.
- Логи сохраняются в консоль и файл (log4j/logback).

### Слой Web

- Использование Thymeleaf для представления.
- Эндпоинты разработаны в соответствии с API Design Best Practices.
- Реализация страниц для регистрации, авторизации и просмотра данных.
- Обработка ошибок через `@ControllerAdvice`.
- Поддержка интернационализации.

### Общие требования

- Аутентификация и авторизация через Spring Security.
- Два уровня ролей: `USER` и `ADMIN`.
- Покрытие кода unit и integration тестами (не менее 60%).

## 📂 Структура проекта

```plaintext
Cinema/
├── common/
├── service/
├── src/
│   ├── main/
│   │   ├── java/
│   │   ├── resources/
│   │       ├── static/
│   │       ├── templates/
│   │       ├── application.properties
│   ├── test/
├── pom.xml

🚀 Установка и запуск
Склонируйте репозиторий:

git clone https://github.com/Yersdev/Cinema.git
cd Cinema
Настройте базу данных PostgreSQL:

Создайте базу данных.
Обновите application.properties с вашими параметрами подключения.
Соберите проект:

mvn clean install
Запустите приложение:

mvn spring-boot:run
Приложение будет доступно по адресу: http://localhost:8080.

🧪 Тестирование
Для выполнения тестов:

mvn test
📜 Лицензия
Этот проект распространяется под лицензией MIT.


Если нужно добавить что-то еще или изменить детали, дайте знать!
```
