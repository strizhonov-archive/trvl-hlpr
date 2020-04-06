Bot Content Manager
----------------------

Web application to perform data manipulation with 
[bot-app](https://github.com/strizhonov-secret/trvl-hlpr/tree/master/bot-app).
Implements CRUD logic.


##API description<br/>  

`POST` */api/city* - save city in the database. Requires city item passed as json:<br/>
`{ "name" : "city_name", "description" : "city_description" }`<br/>

***

 `GET` */api/city/:id* - get city by its id<br/>

***
 
`PUT` */api/city* - updates city, requires city info passed as json:<br/>
`{ "id" : "5", "name" : "city_name", "description" : "city_description" }`<br/>

***

`DELETE` */api/city/:id* - delete city by its id<br/>

***

 `GET` */api/city* - get all cities from database.<br/>
 
***