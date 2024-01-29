# Simple Todo Project

Es un proyecto educativo que lo único que busca es mostrar a través de código el funcionamiento de una lista de 
actividades *"por hacer"* (*to-do* en Inglés). 

El proyecto ha sido implementado utilizando el estilo arquitectónico de Microservicios, utilizando la librería 
[Helidon](https://helidon.io). Además, la implementación se base en REST.



## Build and run

Helidon es una librería que se basa en Java y se utilizó Maven como  gestor de proyectos, por tales razones, 
es necesario tener, instalado, y correctamente configurados, la version [21](https://adoptium.net/) de ese lenguaje de 
programación y al menos la versión 3.9.5 de [Maven](https://maven.apache.org)

Los comandos que debe ejecutar son:

```bash
mvn package
java -jar target/SimpleTodoProject.jar
```

Además puede utilizar cualquier Entorno de Desarrollo Integrado para Java que soporte proyectos Maven.

## Verificación de la correcta instalación y funcionamiento

El siguiente comando corresponde a la invocación a un método del API REST de la aplicación. El comando corresponde a la
herramienta [HTTPie](https://httpie.io)

```
http GET localhost:8080/todo/all
```
El comando anterior debe mostrar generar una salida que incluye un JSON más o menos así (el atributo _createAt_ puede 
variar).

```json
[
    {
        "completed": false,
        "createdAt": "2024-01-28",
        "description": "Enviar tarea de programación integrativa"
    }
]
```

Hasta aquí las modificaciones del documento original generado por Helidon. Las siguientes secciones están en el idioma
original, no han sido traducidas para evitar omisiones o errores que pueden causar alguna confusión.



## Building a Native Image

The generation of native binaries requires an installation of GraalVM 22.1.0+.

You can build a native binary using Maven as follows:

```
mvn -Pnative-image install -DskipTests
```

The generation of the executable binary may take a few minutes to complete depending on
your hardware and operating system. When completed, the executable file will be available
under the `target` directory and be named after the artifact ID you have chosen during the
project generation phase.



## Building the Docker Image

```
docker build -t SimpleTodoProject .
```

## Running the Docker Image

```
docker run --rm -p 8080:8080 SimpleTodoProject:latest
```

Exercise the application as described above.
                                

## Run the application in Kubernetes

If you don’t have access to a Kubernetes cluster, you can [install one](https://helidon.io/docs/latest/#/about/kubernetes) on your desktop.

### Verify connectivity to cluster

```
kubectl cluster-info                        # Verify which cluster
kubectl get pods                            # Verify connectivity to cluster
```

### Deploy the application to Kubernetes

```
kubectl create -f app.yaml                  # Deploy application
kubectl get pods                            # Wait for quickstart pod to be RUNNING
kubectl get service  SimpleTodoProject         # Get service info
```

Note the PORTs. You can now exercise the application as you did before but use the second
port number (the NodePort) instead of 8080.

After you’re done, cleanup.

```
kubectl delete -f app.yaml
```
                                

## Building a Custom Runtime Image

Build the custom runtime image using the jlink image profile:

```
mvn package -Pjlink-image
```

This uses the helidon-maven-plugin to perform the custom image generation.
After the build completes it will report some statistics about the build including the reduction in image size.

The target/SimpleTodoProject-jri directory is a self contained custom image of your application. It contains your application,
its runtime dependencies and the JDK modules it depends on. You can start your application using the provide start script:

```
./target/SimpleTodoProject-jri/bin/start
```

Class Data Sharing (CDS) Archive
Also included in the custom image is a Class Data Sharing (CDS) archive that improves your application’s startup
performance and in-memory footprint. You can learn more about Class Data Sharing in the JDK documentation.

The CDS archive increases your image size to get these performance optimizations. It can be of significant size (tens of MB).
The size of the CDS archive is reported at the end of the build output.

If you’d rather have a smaller image size (with a slightly increased startup time) you can skip the creation of the CDS
archive by executing your build like this:

```
mvn package -Pjlink-image -Djlink.image.addClassDataSharingArchive=false
```

For more information on available configuration options see the helidon-maven-plugin documentation.
                                
