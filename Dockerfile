# Используем образ Java с OpenJDK 17
FROM openjdk:17-jdk-slim

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем файл jar из сборки в контейнер
COPY target/tz1_Cinema-0.0.1-SNAPSHOT.jar Cinema.jar


# Указываем команду для запуска приложения
ENTRYPOINT ["java", "-jar", "Cinema.jar"]
