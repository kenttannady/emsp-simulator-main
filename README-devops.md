# eMSP Simulator - DevOps Setup Guide

## 🐳 Docker Setup (Local Development)

### Prerequisites
- Docker Desktop installed
- Git

### Quick Start
```bash
# Clone the repository
git clone <repository-url>
cd emsp-simulator-main

# Run with Docker Compose
docker-compose up -d

# View logs
docker-compose logs -f emsp-simulator

# Stop services
docker-compose down
```

### Services
- **eMSP Simulator**: http://localhost:8080
- **MySQL Database**: localhost:3306
- **Adminer (DB Management)**: http://localhost:8081

### Database Credentials
- **Database**: emsp_simulator
- **Username**: emsp_user
- **Password**: emsp_password

## ☸️ Kubernetes Setup (Local)

### Prerequisites
- Docker Desktop with Kubernetes enabled
- kubectl configured
- (Optional) kustomize

### Deploy to Kubernetes
```bash
# Create namespace and deploy all resources
kubectl apply -k k8s/

# Check deployment status
kubectl get pods -n emsp-simulator
kubectl get services -n emsp-simulator

# Access the application
kubectl port-forward svc/emsp-simulator-service 8080:8080 -n emsp-simulator
```

### Kubernetes Resources
- **Namespace**: emsp-simulator
- **Deployment**: emsp-simulator-deployment (2 replicas)
- **Service**: emsp-simulator-service (LoadBalancer)
- **Database**: MySQL 8.0 with persistent storage
- **Ingress**: emsp-simulator.local (requires hosts file entry)

### Add to hosts file
```bash
# Add this line to your /etc/hosts (Linux/Mac) or C:\Windows\System32\drivers\etc\hosts (Windows)
127.0.0.1 emsp-simulator.local
```

## 🚀 CI/CD Pipeline (GitHub Actions)

### Pipeline Triggers
- **Push to main/develop**: Build, test, and deploy
- **Pull requests**: Build and test only
- **Manual dispatch**: Rollback deployment

### Pipeline Stages
1. **Build & Test**: Maven compilation and unit tests
2. **Docker Build**: Build and push container image to GitHub Container Registry
3. **Kubernetes Deploy**: Deploy to local Kubernetes cluster (self-hosted runner only)

### Required Secrets
No additional secrets required - uses `GITHUB_TOKEN` for container registry authentication.

### Self-Hosted Runner Setup
Make sure your self-hosted runner has:
- Docker Desktop installed with Kubernetes enabled
- kubectl configured to use docker-desktop context
- Sufficient permissions to deploy to the cluster

## 📋 Development Commands

### Docker Commands
```bash
# Build image
docker build -t emsp-simulator:latest .

# Run container
docker run -p 8080:8080 emsp-simulator:latest

# View logs
docker logs <container-id>

# Execute in container
docker exec -it <container-id> /bin/bash
```

### Kubernetes Commands
```bash
# Get all resources
kubectl get all -n emsp-simulator

# Describe pod
kubectl describe pod <pod-name> -n emsp-simulator

# Get logs
kubectl logs <pod-name> -n emsp-simulator

# Scale deployment
kubectl scale deployment emsp-simulator-deployment --replicas=3 -n emsp-simulator

# Restart deployment
kubectl rollout restart deployment/emsp-simulator-deployment -n emsp-simulator
```

### Database Management
```bash
# Connect to MySQL in Docker
docker exec -it emsp-mysql mysql -u emsp_user -p emsp_simulator

# Connect to MySQL in Kubernetes
kubectl exec -it deployment/mysql-deployment -n emsp-simulator -- mysql -u emsp_user -p emsp_simulator
```

## 🔧 Configuration

### Environment Variables
- `SPRING_PROFILES_ACTIVE`: docker/kubernetes
- `SPRING_DATASOURCE_URL`: JDBC connection string
- `SPRING_DATASOURCE_USERNAME`: Database username
- `SPRING_DATASOURCE_PASSWORD`: Database password (from secrets)

### Health Checks
- **Application**: http://localhost:8080/actuator/health
- **Kubernetes Liveness**: `/actuator/health`
- **Kubernetes Readiness**: `/actuator/health`

## 🐛 Troubleshooting

### Common Issues
1. **Port conflicts**: Make sure ports 8080, 8081, 3306 are available
2. **Kubernetes context**: Ensure kubectl uses docker-desktop context
3. **Image pull**: Verify GitHub Container Registry permissions
4. **Database connection**: Check MySQL pod logs and network policies

### Debug Commands
```bash
# Docker Compose logs
docker-compose logs -f mysql
docker-compose logs -f emsp-simulator

# Kubernetes logs
kubectl logs -f deployment/emsp-simulator-deployment -n emsp-simulator
kubectl logs -f deployment/mysql-deployment -n emsp-simulator

# Check events
kubectl get events -n emsp-simulator --sort-by=.metadata.creationTimestamp
```

## 📊 Monitoring

### Application Metrics
- Spring Boot Actuator endpoints available at `/actuator`
- Health checks configured for both Docker and Kubernetes

### Resource Limits
- **Memory**: 512Mi request, 1Gi limit
- **CPU**: 250m request, 500m limit
- **Storage**: 5Gi for MySQL persistent volume
