# Usa una imagen con Java y Maven preinstalados
FROM maven:3.8.7-eclipse-temurin-21 AS build

# Establece el directorio de trabajo
WORKDIR /app

# Copia todos los archivos del proyecto
COPY . .

# Compila el proyecto sin ejecutar pruebas
RUN mvn clean package -DskipTests

# Usa una imagen ligera de Java para la ejecución
FROM eclipse-temurin:21-jre

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo JAR generado en la fase anterior
COPY --from=build /app/target/*.jar app.jar

# Expone el puerto (Railway lo asigna automáticamente)
EXPOSE 8081

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]
