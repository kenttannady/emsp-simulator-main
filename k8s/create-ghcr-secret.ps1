# Create GitHub Container Registry secret for Kubernetes
param(
    [Parameter(Mandatory=$true)]
    [string]$Username = "kenttannady",
    
    [Parameter(Mandatory=$true)]
    [string]$Token
)

Write-Host "Creating docker-registry secret for GHCR..."

kubectl create secret docker-registry ghcr-secret `
  --docker-server=ghcr.io `
  --docker-username=$Username `
  --docker-password=$Token `
  --docker-email="$Username@users.noreply.github.com" `
  --namespace=emsp-simulator

Write-Host "Secret created successfully!"
Write-Host "Verifying secret:"
kubectl get secret ghcr-secret -n emsp-simulator -o yaml
