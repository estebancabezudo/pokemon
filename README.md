# Ejecución
## Linux

Crear una base de datos, el valor por defecto para el nombre es pokemon.

Descargar el JAR de 

````
wget 
````

### Configurar variables de ambiente con el acceso a la base de datos.

La variable de ambiente `MYSQL_HOST` solo la agregamos si el servidor de mysql no se encuentra en localhost.

La variable de ambiente `MYSQL_DATABASE` solo la configuramos si la base de datos que vamos a usarn no se llama pokemon.

````
export MYSQL_HOST=[nombre_del_host]
export MYSQL_DATABASE_NAME=[nombre_de_base_de_datos_creada]
export MYSQL_USER=[usuario_de_base_de_datos]
export MYSQL_PASSWORD=[contraseña_de_base_de_datos]
````

Ejecutar en el directori donde bajamos el jar

````
java -jar demo-0.0.1-SNAPSHOT.jar
````