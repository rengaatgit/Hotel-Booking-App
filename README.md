# Hotel-Booking-App developed by Rengaraj Arumugam
Hotel Booking Management App developed in Java &amp; Springboot 

To test API exposed by appliction, include below items as part of your request:
1. Set Content-Type as application/json in Request Header for all API calls
2. Choose right HTTP method (GET/POST) based on API declaration. 
3. For POST method, key-in "input value" in request body
4. For GET method, key-in "input value" as path variable. 
5. App is developed with JDK 11

1. To set Number of Rooms => POST: localhost:8080/api/v1.0/hotel/booking/setRoomCount  & choose raw body  and key-in data (example: 3)
2. To get Number of Rooms => GET: localhost:8080/api/v1.0/hotel/booking/getroomcount  & no input data
3. To book Room => POST: localhost:8080/api/v1.0/hotel/booking/bookroom  & choose raw body  and key-in data as following
{
    "custName":"Renga",
    "bookingDate":"16-08-2021"
}

4. To check Available for given date => GET: localhost:8080/api/v1.0/hotel/booking/checkdate/{date}  & example: key-in 16-08-2021 as date in url path
5. To check all bookings for a given guest => GET: localhost:8080/api/v1.0/hotel/booking/checkguest/{name}  & example: key-in Renga as name in url path

