# Quick Push to GitHub Container Registry

## Step 1: Login to GHCR
```powershell
# Ganti <YOUR_TOKEN> dengan GitHub Personal Access Token Anda
echo "<YOUR_TOKEN>" | docker login ghcr.io -u kenttannady --password-stdin
```

## Step 2: Build and Push
```powershell
# Build image
docker build -t ghcr.io/kenttannady/emsp-simulator:latest .

# Push image
docker push ghcr.io/kenttannady/emsp-simulator:latest
```

## Step 3: Create Secret for Kubernetes
```powershell
# Ganti <YOUR_TOKEN> dengan token yang sama
kubectl create secret docker-registry ghcr-secret `
  --docker-server=ghcr.io `
  --docker-username=kenttannady `
  --docker-password="<YOUR_TOKEN>" `
  --docker-email="kenttannady@users.noreply.github.com" `
  --namespace=emsp-simulator
```

## Step 4: Deploy
```bash
kubectl apply -k k8s/
```
