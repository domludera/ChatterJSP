# ChatterJSP
## Group members
Jack Burns

Dominik Ludera

Zi Li Wang

Anna Kmieciak

## **CURL Methods**
*GET request:* curl  http://localhost:8080/chatter_war/BasicServlet

*POST request:* curl --data "name=studentName&message=Hello World&postmessage=Post" http://localhost:8080/chatter_war/BasicServlet

*Clear method* curl -X GET "localhost:8080/chatter_war/BasicServlet?from=&to=&format=plain&clear=Clear"

*HTML and XML Download:* curl -X GET "http://localhost:8080/chatter_war/BasicServlet?from=&to=&format=plain&download=Download"


