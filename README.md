Demo project to set up multiple authentication entry points (REALMS) using Spring Security.

### Theory
Using multiple authentication entry points could be useful when there is a need to apply a security policy to
several functional domains in different ways. For example, first security entry point is intended for the browser clients.
It enables us to log in with in the login page and access protected resources. Second security entry point is intended for
the REST web service requests coming from an android application. On each request, the REST client should send required
information to the server and this information will be used to decide if the RESTfull request should be allowed to pass.

The two security entry points (realms) are distinguished by different URL patterns of resources in the web application. In
both configurations we are able to reuse same authentication logic.
There are 2 security policies in the example above - one is to authenticate requests coming from browser apps,
and another is to authenticate android REST apps. To distinguish HTTP requests coming from different security domains we 
typically use the term of REALM - a unique string that value is associated with one security domain and stored in request 
headers, example: 

`'WWW-Authenticate: Basic realm="My Realm"'`

A **REALM** is a security policy domain defined for a web or application server. The protected resources on a server can be
partitioned into a set of protection spaces, each with its own authentication scheme and/or authorization database 
containing a collection of users and groups.

A **REALM** can be seen as an area (not a particular page, it could be a group of pages) for which the credentials are used; 
this is also the string that will be shown when the browser pops up the login window, e.g. In short, pages in the same
REALM should share credentials. If your credentials work for a page with the realm "My Realm", it should be assumed that
the same username and password combination should work for another page with the same REALM.

Another good example of using REALMS is configuring security policy in application that is distributed geographically. Using 
different REALMS could help us to distinguish requests coming from different locations/zones and apply authentication
accordingly to its geographical affiliation.


-------
Useful links:

https://datatracker.ietf.org/doc/html/rfc7235#section-2.2

https://www.codecentric.de/wissens-hub/blog/spring-security-two-security-realms-in-one-application

https://www.baeldung.com/spring-security-multiple-entry-points

