# Setup GitHub Container Registry Secret

## 1. Create GitHub Personal Access Token

1. Go to GitHub Settings → Developer settings → Personal access tokens → Tokens (classic)
2. Click "Generate new token (classic)"
3. Give it a name like "GHCR Access"
4. Select scopes:
   - `write:packages` (for pushing images)
   - `read:packages` (for pulling images)
5. Generate token and copy it (you won't see it again)

## 2. Create Kubernetes Secret

Run this command in your terminal:

```bash
kubectl create secret docker-registry ghcr-secret \
  --docker-server=ghcr.io \
  --docker-username=kenttannady \
  --docker-password=<YOUR_GITHUB_TOKEN> \
  --docker-email=kenttannady@users.noreply.github.com \
  --namespace=emsp-simulator \
  --dry-run=client -o yaml > k8s/image-pull-secret.yaml
```

## 3. Build and Push Image

### Option A: Using PowerShell script
```powershell
# First login to GHCR
echo "<YOUR_GITHUB_TOKEN>" | docker login ghcr.io -u kenttannady --password-stdin

# Build and push
.\build-and-push.ps1
```

### Option B: Manual commands
```bash
# Login to GHCR
echo "<YOUR_GITHUB_TOKEN>" | docker login ghcr.io -u kenttannady --password-stdin

# Build image
docker build -t ghcr.io/kenttannady/emsp-simulator:latest .

# Push image
docker push ghcr.io/kenttannady/emsp-simulator:latest
```

## 4. Deploy to Kubernetes

```bash
kubectl apply -k k8s/
```

## 5. Verify Deployment

```bash
kubectl get pods -n emsp-simulator
kubectl logs -f deployment/emsp-simulator-deployment -n emsp-simulator
```
