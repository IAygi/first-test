# Тестовый проект для обучения автоматизации тестирования
#### Проект автоматизации тестирования REST API, Web UI и Database

## Содержание
- [Технологии](#технологии)
- [Тестирование](#тестирование)
- [Обучение](#обучение)

## Технологии
- [Java](https://www.java.com/ru/)
- [Gradle](https://gradle.org/)
- [JUnit 5](https://junit.org/junit5/)
- [Selenide](https://ru.selenide.org/)
- [REST-assured](https://rest-assured.io/)
- [JDBC](https://docs.oracle.com/en/database/oracle/oracle-database/21/jjdbc/introducing-JDBC.html)
- [Allure Report](https://allurereport.org/)
- [Allure TestOps](https://qameta.io/)
- [Selenoid](https://aerokube.com/selenoid/latest/)

## Тестирование

### Запуск тестов
```sh
./gradlew clean test
```

Открытие Allure отчёта в браузере:
```sh
./gradlew allureServe
```

### Группировка тестов
Запускаемые тесты можно группировать, посредством присваивания тегов классам или методам.

Теги прописываются в [build.gradle](build.gradle) в тасках:
```groovy
task smoke_tests(type: Test) {
    useJUnitPlatform {
        includeTags("smoke")
    }
}
```
Таски запускаются следующим образом:
```sh
./gradlew clean smoke_tests
```
### Тестирование БД

Для тестирования базы данных (PostgreSQL) нужно: 
1. [Развернуть](https://www.asozykin.ru/posts/demo_database_sql_foundation) локально PostgreSQL
2. Создать тенстовую БД по [инструкции](https://www.asozykin.ru/posts/demo_database_sql_foundation#rec267589724) 
3. В классе **DbData** (java/ru/iaygi/db/data/DbData.java) в переменной **dbPassword** прописать свой пароль для соединения с PostgreSQL  

## Обучение

План  обучения:

1. Основы JUnit
2. Написание API тестов
3. Написание UI тестов
4. Настройка отчётов в Allure Report
5. Настройка Selenoid
6. Интеграция с Allure TestOps
7. Тестирование БД (JDBC)
8. Интеграция c CI/CD