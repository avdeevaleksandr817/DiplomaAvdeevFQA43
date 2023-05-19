# AppVeyor CI
[![Build status](https://ci.appveyor.com/api/projects/status/rb1arwycjdds83mw?svg=true)](https://ci.appveyor.com/project/avdeevaleksandr817/diplomaavdeevfqa43)

# Дипломный проект по профессии «Тестировщик»

Дипломный проект представляет собой комплексную автоматизацию тестирования веб-сервиса, который взаимодействует с СУБД и API банка.

## Документация

#### [Дипломное задание](docs/Technical task.md)

#### [План автоматизации тестирования](docs/Plan.md)

#### [Отчётные документы по итогам тестирования](docs/Report.md)

#### [Отчётные документы по итогам автоматизации](docs/Summary.md)

### Процедура запуска авто-тестов *для MySQL*
1. Для запуска авто-тестов нужно заранее установить и запустить Docker Desktop на локальной машине

2. Запустить IntelliJ IDEA

3. Воспользоваться интерфейсом *IntelliJ IDEA:* клонировать репозиторий  
   >`git clone https://github.com/avdeevaleksandr817/DiplomaAvdeevFQA43.git`

4. Запустить контейнеры Docker командой в консоли:
   >`docker-compose up -d`

5. Запустить приложение командой в консоли

   >`java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" "-Dspring.datasource.username=app" "-Dspring.datasource.password=pass" -jar artifacts/aqa-shop.jar`

6. Запустить авто-тесты командой в консоли

   >`./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app" "-Ddb.username=app" "-Ddb.password=pass"`

7. Создание *Allure* отчёта
   >`./gradlew allureReport` - формирование отчёта

   >`./gradlew allureServe` - отображение отчёта в браузере

8. После выполнения всех тестов и генерации отчета:

- остановить работу приложения командой в консоли:
  `Ctrl+C`
- остановить работу контейнеров Docker командой в консоли:
  `docker-compose down`

### Процедура запуска авто-тестов *для PostgreSQL*:
1. Для запуска авто-тестов нужно заранее установить и запустить Docker Desktop на локальной машине

2. Запустить IntelliJ IDEA

3. Воспользоваться интерфейсом *IntelliJ IDEA:* клонировать репозиторий  
   `git clone https://github.com/avdeevaleksandr817/DiplomaAvdeevFQA43.git`

4. Запустить контейнеры Docker командой в консоли:
   >`docker-compose up -d`

5. Запустить приложение командой в консоли

   >`java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" "-Dspring.datasource.username=app" "-Dspring.datasource.password=pass" -jar artifacts/aqa-shop.jar`

6. Запустить авто-тесты командой в консоли

   >`./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app" "-Ddb.username=app" "-Ddb.password=pass"`

7. Создание *Allure* отчёта

   >`./gradlew allureReport` - формирование отчёта

   >`./gradlew allureServe` - отображение отчёта в браузере

8. После выполнения всех тестов и генерации отчета:

- остановить работу приложения командой в консоли:
  `Ctrl+C`
- остановить работу контейнеров Docker командой в консоли:
  `docker-compose down`