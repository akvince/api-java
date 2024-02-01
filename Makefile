# Makefile for Spring Boot project

# Définir les cibles par défaut
.DEFAULT_GOAL := help

# Variables
PROJECT_NAME := api
JAR_FILE := target/$(PROJECT_NAME)-0.0.1-SNAPSHOT.jar

# Cibles
help:
	@echo "Usage:"
	@echo "  make build       - Build the Spring Boot project"
	@echo "  make run         - Run the Spring Boot application"
	@echo "  make clean       - Clean the project"
	@echo "  make help        - Show this help message"

build:
	./mvnw clean package

run:
	java -jar $(JAR_FILE)

clean:
	./mvnw clean
	rm -rf target

.PHONY: help build run clean
