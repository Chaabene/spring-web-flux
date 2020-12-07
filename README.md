# spring-web-flux
https://dzone.com/articles/build-reactive-rest-apis-with-spring-webflux

https://howtodoinjava.com/spring-webflux/spring-webflux-tutorial/
https://spring.io/blog/2016/06/13/notes-on-reactive-programming-part-ii-writing-some-code

#Cassandra databse:

https://phoenixnap.com/kb/install-cassandra-on-windows
https://bezkoder.com/spring-boot-cassandra-crud/
https://docs.spring.io/spring-data/cassandra/docs/1.5.0.M1/reference/html/#cassandra-connectors.javaconfig
https://medium.com/@aamine/spring-data-for-cassandra-a-complete-example-3c6f7f39fef9

create keyspace springWebFlux with replication={'class':'SimpleStrategy', 'replication_factor':1};

use springWebFlux;

CREATE TABLE user(
   id_user timeuuid PRIMARY KEY,
   first_name text,
   last_name text
);
