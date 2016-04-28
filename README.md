# flea
java code for flea market

- для работы проекта необходимо:
    - java >= 1.8
    - бд postgresql >= 9.4
    - apache tomcat >= 8
    - прописать настройки подключения. их можно взять из класса SpringDataJpa
    - для разработки
    - IDE Idea
    - maven  >= 3.3

- для генерации бд необходимо:
    - создать базу в postgresql
    - раскомментировать в файле hibernate.properties свойство hibernate.hbm2ddl.auto=create

- подключение к бд настраивается через jndi в tomcat tomcat_home/conf/context.xml
    добавить настройки
    ```xml
    <Resource name="jdbc/mycards" auth="Container" type="javax.sql.DataSource"
        driverClassName="org.postgresql.Driver" url="jdbc:postgresql://localhost:5432/mycards" username="postgres" password="qwe"/>
     ```

- для регистрации jndi настроек email сервиса в tomcat надо добавить либу от сюда https://java.net/projects/javamail/pages/Home
    добавить настройки
    ```xml
    <Resource name="mail/Session" auth="Container" type="javax.mail.Session"
            username="my.smtp.user" password="my-secret"
            mail.debug="false"
            mail.transport.protocol="smtp"
            mail.smtp.host= "xxx.xxx.xxx.xxx"
            mail.smtp.auth= "true"
            mail.smtp.port= "25"
            mail.smtp.starttls.enable="true"/>
     ```

- для развертывания рабочей версии с большой нагрузкой надо в hibernate.properties настроить пул подключений c3p0

- при запуске в режиме дебага в
    ```java
     InitService.addAdminIfNeed()
    ```
  создается пользователь по умолчанию. з.ы. там можно посмотреть логин и пароль

- сессия пользователя хранится в бд, так что при перезагрузке сервера авторизация не слетает
для этого есть сущность PersistentRememberMeTokenDb и с помощью магии она сохраяется в базе

- настройка https в tomcat:
    сгенерировать кейстор keytool.exe -genkey -alias myalias -keyalg RSA -keystore C:\projects\keys\my.keystore
    добавить в server.xml
    ```xml
        <Connector SSLEnabled="true" keystoreFile="C:\projects\keys\my.keystore" protocol="org.apache.coyote.http11.Http11NioProtocol"
              keystorePass="123456" maxThreads="150" port="8443" scheme="https" secure="true" sslProtocol="TLS"/>
    ```
    в application.properties установить свойство application.use.ssl
    запуск приложения будет происходить на порту 8443, следовательо надо добавить конфигурацию запуска
