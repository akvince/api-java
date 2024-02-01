# Utilisez une image de base avec Java 17
FROM openjdk:17-jdk-slim

# Définissez le répertoire de travail dans le conteneur
WORKDIR /app

# Copiez le fichier JAR de votre application dans le conteneur
COPY ../../target/api-0.0.1-SNAPSHOT.jar /app/api.jar

# Exposez le port sur lequel votre application écoute
EXPOSE 8082

# Commande pour exécuter l'application au démarrage du conteneur
CMD ["java", "-jar", "api.jar"]