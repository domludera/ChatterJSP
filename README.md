# ChatterJSP
## Group members
Jack Burns

Dominik Ludera

Zi Li Wang

Anna Kmieciak

## **CURL Methods**
*GET request:* curl  http://localhost:8080/chatter_war/BasicServlet

*POST request:* curl --data "name=studentName&message=Hello World&postmessage=Post" http://localhost:8080/chatter_war/BasicServlet

*Clear method* curl -d clear=Clear -G http://localhost:8080/chatter_war/BasicServlet

*HTML Download:* curl http://localhost:8080/chatter_war/BasicServlet --output output.txt 
