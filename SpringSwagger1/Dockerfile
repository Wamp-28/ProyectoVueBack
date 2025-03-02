# Usa una imagen con Java y Maven preinstalados para compilar
FROM maven:3.8.7-eclipse-temurin-21 AS build

# Establece el directorio de trabajo
WORKDIR /app

# Copia solo los archivos esenciales para descargar dependencias primero
COPY SpringSwagger1/pom.xml .

# Descarga dependencias para aprovechar la caché de Docker
RUN mvn dependency:go-offline

# Ahora copia el resto del código fuente
COPY SpringSwagger1/src src

# Compila el proyecto sin ejecutar pruebas
RUN mvn clean package -DskipTests

# Usa una imagen ligera de Java para la ejecución
FROM eclipse-temurin:21-jre

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo JAR generado en la fase anterior
COPY --from=build /app/target/*.jar app.jar

# Expone el puerto 8081 (o el que uses)
EXPOSE 8081

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]
