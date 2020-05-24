# PrimefacesQuiz

Proyecto web que te permite realizar un cuestionario a varios usuarios, ofreciéndote el resultado al final después de la realización de cada usuario.

Requerimientos --> 
  - Java 8 
  - Redis con módulo Perl --> Descargar en consola con el comando “cpan -i Redis”

Puesta en marcha -->
  - Carga de la base de datos con el comando "perl load-data1.pl < content-data1"
  - Ejecución a través de maven con el servidor Jetty con el comando "mvn jetty:run" (también posible su ejecución con otros servidores como Tomcat)
  - En navegador introducir "localhost:9999/list-pregs" 


PRIMEFACESQUIZ CON CONSULTA DE DATOS

La diferencia de este proyecto con el anterior es que permite realizar consultas de los resultados obtenidos por los usuarios.
Ya no hay redis, tanto la carga de las preguntas como la persistencia de datos va con el uso de MySQL.

Requerimientos --> 
  - Java 8
  - MySQL
  
Puesta en marcha --> 
  - Introducir los datos de resultados.sql tal y como están los comandos en este fichero.
  - Ejecución a través de maven con el servidor Jetty con el comando "mvn jetty:run" (también posible su ejecución con otros servidores como Tomcat).
  - En navegador introducir "http://localhost:9999/visualizacion.xhtml" 
