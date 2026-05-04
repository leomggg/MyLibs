param(
    [string]$HostName = "localhost",
    [int]$Port = 8081,
    [string]$Path = "/api/health"
)

$uri = "http://$HostName`:$Port$Path"
Write-Host "Probando: $uri"

try {
    $response = Invoke-WebRequest -Uri $uri -UseBasicParsing -TimeoutSec 5
    Write-Host "Status: $($response.StatusCode)"
    Write-Host "Body: $($response.Content)"
    exit 0
} catch {
    Write-Host "Error: $($_.Exception.Message)"
    exit 1
}

