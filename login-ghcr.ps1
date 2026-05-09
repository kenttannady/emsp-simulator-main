# Login to GitHub Container Registry
param(
    [string]$Username = "kenttannady"
)

Write-Host "Please enter your GitHub Personal Access Token:"
$Token = Read-Host -AsSecureString
$TokenText = [System.Runtime.InteropServices.Marshal]::PtrToStringAuto([System.Runtime.InteropServices.Marshal]::SecureStringToBSTR($Token))

Write-Host "Logging in to GitHub Container Registry..."
echo $TokenText | docker login ghcr.io -u $Username --password-stdin

if ($LASTEXITCODE -eq 0) {
    Write-Host "Login successful!"
} else {
    Write-Host "Login failed. Please check your token."
}
