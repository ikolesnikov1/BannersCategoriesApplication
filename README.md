### Start Instructions

1. Download/clone this repository to your machine
2. Download and run MYSQL server, then create a database
3. Go to "src/main/resources" folder, open "application.properties" file and change name of database and port in first line
4. Change user and password in next two lines of "application.properties" file
5. In the root folder run "mvn spring-boot:run" command to launch backend part of application
6. Go to "frontend" folder and run "npm start" command to launch frontend part of application
7. Now you can open "localhost:3000" in your browser to use the app
8. Also you can use Postman or something like this to get a banner text at "localhost:8080/bid?category={yourcategory}" <br /> (e.g. localhost:8080/bid?category=sport)
9. For authorised users besides CRUD operations you can find banners at "localhost:8080/category?name={wordtofind}" <br /> (not all of this working at frontend, unfortunately I didn't manage to finish it :( but Postman can help you, e.g. you can make DELETE request at "localhost:8080/banner/{bannerID}" to delete banner or do the same with category, of course you have to use BasicAuth in Postman with this credentials: <br /> username=user <br /> password=password)
    
P.S. To fill your database after first launch of backend, you can use query at "src/main/resources/FillDatabase.sql" file, don't forget to change your database name in first line!
