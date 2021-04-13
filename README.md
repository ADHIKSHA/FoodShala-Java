# FoodShala-Java
Food Ordering System in Java with SQL Server.

## Dependencies
1. Java
2. Eclipse
3. GlassFish Server
4. MySQL Server

## Usage 
```
Import the project in Eclipse IDE with GlassFish Server incorporated and click on RUN

```

## Features
1. Take orders for dishes
2. Category wise display dishes.
3. Restaurant Owner dashboard.
4. User cart, checkout and payment page.
5. Login and SignUp for restaurant owner and customer.

## Demo
<img src ="https://user-images.githubusercontent.com/29985870/114541527-751e6e00-9c74-11eb-8b76-238b6e621646.png" width="750" height="480">

## SQL Tables
```
Cart : dishid , email
Menu : category , dishid , dish_name , image , price , resid, resname
Restaurants: location , name , id , phone , owner_name , password
Transactions: dishid , email , quantity, time , total
Users: email , fullname, location , password , phone , userid.
```
