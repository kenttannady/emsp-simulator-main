#!/bin/bash

# Create GitHub Container Registry secret for Kubernetes
# Usage: ./create-ghcr-secret.sh <github-username> <github-token>

USERNAME=${1:-kenttannady}
TOKEN=${2}

if [ -z "$TOKEN" ]; then
    echo "Usage: $0 <github-username> <github-token>"
    echo "Please provide your GitHub Personal Access Token"
    exit 1
fi

echo "Creating docker-registry secret for GHCR..."

kubectl create secret docker-registry ghcr-secret \
  --docker-server=ghcr.io \
  --docker-username=$USERNAME \
  --docker-password=$TOKEN \
  --docker-email=$USERNAME@users.noreply.github.com \
  --namespace=emsp-simulator

echo "Secret created successfully!"
echo "Verifying secret:"
kubectl get secret ghcr-secret -n emsp-simulator -o yaml
