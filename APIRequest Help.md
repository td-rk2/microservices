// Api Requests
=======================================================================================

**For User Microservice**

localhost:8080/createuser
localhost:8080/getallusers
localhost:8080/getuserbyid/2
localhost:8080/updateuser/2
localhost:8080/deleteuser/252
---------------------------------------------------------------------------------------
{
"fullName": "try true",
"email" : "shel@gmail.com",
"phone": 683947,
"role": "agent",
"password" : "sdfj",
"createdAt": "2025-02-08T14:45:47.017535",
"updatedAt": "2025-02-08T14:45:47.017535"
}
---------------------------------------------------------------------------------------

**For Salon Microservice**

http://localhost:5002/api/salons/
http://localhost:5002/api/salons/2
http://localhost:5002/api/salons/
http://localhost:5002/api/salons/5
http://localhost:5002/api/salons/search?city=mumbai
http://localhost:5002/api/salons/owner
---------------------------------------------------------------------------------------
{
"name" : "Elite Beauty Salon",
"address" : "123 Main Street, Downtown",
"phone" : "+91 - 9203809238",
"email": "contact@elitebeautysalon.com",
"city" : "Mumbai",
"openTime" : "09:00:00",
"closeTime" : "20:00:00",
"images" : ["https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.pexels.com%2Fsearch%2Fpeace%2F&psig=AOvVaw1Snk69aq8KZ7QlzbwQqJa5&ust=1739460295216000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCOilneC4vosDFQAAAAAdAAAAABAE"]
}
-----------------------------------------------------------------------------------------




---------------------------------------------------------------------------------------

**For Category Microservice**

http://localhost:5002/api/salons/
http://localhost:5002/api/salons/2
http://localhost:5002/api/salons/
http://localhost:5002/api/salons/5

---------------------------------------------------------------------------------------
{
"name" : "Hair color",
"image" : "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.pexels.com%2Fsearch%2Fpeace%2F&psig=AOvVaw1Snk69aq8KZ7QlzbwQqJa5&ust=1739460295216000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCOilneC4vosDFQAAAAAdAAAAABAE"
}