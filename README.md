TRAVEL HELPER</br>
-----------------
The application consists of two parts:<br/>
- bot-app - telegram chat-bot that outputs city description by the city name input;<br/>
- bot-content-manager - REST service to manage bot-app (implements simple CRUD-operations).<br/>

As this project is only for-representation-purpose, both apps use in-memory database for easier setting.<br/>

---------------------------------------
###Setting up<br/>

Requirements:<br/>
1. Maven;<br/>
2. Java 8+.<br/>

Steps:<br/>
1. Make sure that 9090 port is free, otherwise change in-memory database port here:<br/>
    `bot-content-manager/src/main/resources/application.yml`<br/>
and here:<br/>
    `bot-app/src/main/resources/application.properties`<br/>
2. Make sure that 8080 port is free, otherwise change server port here:<br/>
    `bot-content-manager/src/main/resources/application.yml`<br/>
3. Download and unzip repository;<br/>
4. Run `mvn spring-boot:run` from bot-content-manager;<br/>
5. Run `mvn spring-boot:run` from bot-app.<br/>

It's important to preserve specified installing order, as both apps use the same in-memory database that 
is created from the bot-content-manager, and then connected by the bot-app.<br/>

Bot's credentials are already set up and if need can be found here:
`bot-app/src/main/resources/credentials.properties`

That's all. You can find apps' usage specifications in enclosed README files.<br/>
