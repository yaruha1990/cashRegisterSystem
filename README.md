# cashRegisterSystem

Опис проекту

Система Касовий Апарат. Касир має можливість відкрити чек, додати обрані товари по коду із бази даних
(петрушка = 234, хліб = 222) або по назві товару, вказати кількість товарів або вагу. Закрити чек.
Старший касир має можливість відмінити чек, відмінити один товар в чеку і повернути гроші покупцеві. Зробити X та Z звіти.
Товарознавець має можливість створювати товари і вказувати їх кількість на складі.

Інструкція по встановленню  на Windows
1) git clone https://github.com/yaruha1990/cashRegisterSystem
2) Створити БД з іменем cashregister. Створити таблиці БД. SQL файли, запити з яких треба виконати, знаходяться в папці sql в рут директорії проекту
3) Встановити maven, tomcat сервер 7,8 або 9 версії і позаводити змінні середовища **MAVEN_HOME** та **CATALINA_HOME**
4) Додати наступні рядки у файл context.xml сервера tomcat, який повинен знаходиться в CATALINA_HOME\conf
Це реалізація DBCP 	   
< Resource name="jdbc/TestDB" auth="Container" type="javax.sql.DataSource"
      maxTotal="100" maxIdle="30" maxWaitMillis="10000" removeAbandonedOnBorrow="true" 
      removeAbandonedOnMaintenance="true" removeAbandonedTimeout="60" logAbandoned="true"
      username="root" password="1234" driverClassName="com.mysql.cj.jdbc.Driver"
      url="jdbc:mysql://localhost:3306/cashregister?useSSL=false"/>
      
Інструкція по запуску на Windows
1) Обрати один із способів деплою веб додатку на сервер можна тут https://www.baeldung.com/tomcat-root-application
2) Далі буде представлено як задеплоїти додаток способом №1 із посилання вище:
    - видалити повністю папку ROOT із CATALINA_HOME\webapps
    - запустити сервер tomcat командою %CATALINA_HOME%\bin\startup.bat
    - перейти в папку з проектом і виконати команду mvn tomcat7:deploy. Дана команда працює завдяки плагіну tomcat7-maven-plugin із pom.xml і підходить для версій 7,8,9 tomcat
    - додаток буде доступний за адресою http://localhost:8080/
