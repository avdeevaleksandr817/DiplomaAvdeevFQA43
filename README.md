# AppVeyor CI
[![Build status](https://ci.appveyor.com/api/projects/status/rb1arwycjdds83mw?svg=true)](https://ci.appveyor.com/project/avdeevaleksandr817/diplomaavdeevfqa43)

# Дипломный проект по профессии «Тестировщик»

Дипломный проект представляет собой комплексную автоматизацию тестирования веб-сервиса, который взаимодействует с СУБД и API банка.

## Документация

#### [Дипломное задание](docs/Technicaltask.md)

#### [План автоматизации тестирования](docs/Plan.md)

#### [Отчётные документы по итогам тестирования](docs/Report.md)

#### [Отчётные документы по итогам автоматизации](docs/Summary.md)

### Процедура запуска авто-тестов *для MySQL*
1. Для запуска авто-тестов нужно заранее установить и запустить Docker Desktop на локальной машине

2. Запустить *IntelliJ IDEA*

3. Воспользоваться интерфейсом *IntelliJ IDEA:* клонировать репозиторий  
   >`git clone https://github.com/avdeevaleksandr817/DiplomaAvdeevFQA43.git`

4. Запустить контейнеры Docker командой в терминале №1:
   >`docker-compose up -d`

5. Запустить приложение командой в терминале №1:

   >`java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" "-Dspring.datasource.username=app" "-Dspring.datasource.password=pass" -jar artifacts/aqa-shop.jar`

6. Открыть еще одно окно терминала №2, запустить авто-тесты:

   >`./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app" "-Ddb.username=app" "-Ddb.password=pass"`

7. Создание *Allure* отчёта командой в терминале №2:
   >`./gradlew allureReport` - формирование отчёта

   >`./gradlew allureServe` - отображение отчёта в браузере
 
   > `Ctrl+C` + Y + Enter - остановить отображение отчёта в браузере

8. После выполнения всех тестов и генерации отчета командой в терминале №1:

- остановить работу приложения: 
  `Ctrl+C`
- остановить работу контейнеров Docker:
  `docker-compose down`

### Процедура запуска авто-тестов *для PostgreSQL*:
1. Для запуска авто-тестов нужно заранее установить и запустить Docker Desktop на локальной машине

2. Запустить *IntelliJ IDEA*

3. Воспользоваться интерфейсом *IntelliJ IDEA:* клонировать репозиторий  
   `git clone https://github.com/avdeevaleksandr817/DiplomaAvdeevFQA43.git`

4. Запустить контейнеры Docker командой в терминале №1:
   >`docker-compose up -d`

5. Запустить приложение командой в терминале №1:

   >`java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" "-Dspring.datasource.username=app" "-Dspring.datasource.password=pass" -jar artifacts/aqa-shop.jar`

6. Открыть еще одно окно терминала №2, запустить авто-тесты:

   >`./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app" "-Ddb.username=app" "-Ddb.password=pass"`

7. Создание *Allure* отчёта командой в терминале №2:

   >`./gradlew allureReport` - формирование отчёта

   >`./gradlew allureServe` - отображение отчёта в браузере
   
   > `Ctrl+C` + Y + Enter - остановить отображение отчёта в браузере

8. После выполнения всех тестов и генерации отчета командой в терминале №1:

- остановить работу приложения:
  `Ctrl+C`
- остановить работу контейнеров Docker:
  `docker-compose down`