# Usa una imagen base de OpenJDK 21
FROM openjdk:21-slim

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR de tu proyecto al contenedor
COPY target/mackeupApp-0.0.1-SNAPSHOT.jar /app/mackeupApp-0.0.1-SNAPSHOT.jar

# Expone el puerto que utiliza tu aplicación (usualmente 8080)
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/mackeupApp-0.0.1-SNAPSHOT.jar"]
