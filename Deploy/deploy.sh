#!/bin/bash

# Définir le nom du namespace
NAMESPACE="api-java"

# Récupérer le répertoire du script (où se trouvent les fichiers de configuration)
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

# Vérifier si le namespace existe
if kubectl get namespace "$NAMESPACE" &> /dev/null; then
  echo "Le namespace $NAMESPACE existe déjà."
else
  # Créer le namespace s'il n'existe pas
  kubectl create namespace "$NAMESPACE" || { echo "Erreur lors de la création du namespace."; exit 1; }
  echo "Le namespace $NAMESPACE a été créé avec succès."
fi

# Fonction pour construire et pousser une image Docker
build_and_push_image() {
  local DOCKERFILE_PATH="$1"
  local IMAGE_NAME="$2"

  docker build -t "$IMAGE_NAME" -f "$DOCKERFILE_PATH" "$SCRIPT_DIR/docker/" || { echo "Erreur lors de la construction de l'image."; exit 1; }
  docker push "$IMAGE_NAME" || { echo "Erreur lors du push de l'image."; exit 1; }
}

# Construire et pousser l'image Docker pour l'application backend
DOCKERFILE_PATH_BACKEND="$SCRIPT_DIR/docker/Dockerfile"
IMAGE_NAME_BACKEND="votre_registry/backend:tag"
build_and_push_image "$DOCKERFILE_PATH_BACKEND" "$IMAGE_NAME_BACKEND"

# Construire et pousser l'image Docker pour l'application frontend
DOCKERFILE_PATH_FRONTEND="$SCRIPT_DIR/docker/DockerfileFront"
IMAGE_NAME_FRONTEND="votre_registry/frontend:tag"
build_and_push_image "$DOCKERFILE_PATH_FRONTEND" "$IMAGE_NAME_FRONTEND"

# Déployer l'application backend
kubectl apply -f "$SCRIPT_DIR/deployment.yaml" -n "$NAMESPACE" || { echo "Erreur lors du déploiement de l'application backend."; exit 1; }
kubectl apply -f "$SCRIPT_DIR/service.yaml" -n "$NAMESPACE" || { echo "Erreur lors du déploiement du service backend."; exit 1; }

# Déployer l'application frontend
kubectl apply -f "$SCRIPT_DIR/deployment-frontend.yaml" -n "$NAMESPACE" || { echo "Erreur lors du déploiement de l'application frontend."; exit 1; }
kubectl apply -f "$SCRIPT_DIR/service-frontend.yaml" -n "$NAMESPACE" || { echo "Erreur lors du déploiement du service frontend."; exit 1; }

echo "Déploiements terminés avec succès."
