Write-Host "Generando tráfico hacia app-java..."

$url = "http://localhost:8080/pago"

for ($i=1; $i -le 20; $i++) {
    Invoke-WebRequest -Uri $url -Method Get | Out-Null
    Write-Host "request $i enviada"
    Start-Sleep -Seconds 1
}

Write-Host "Tráfico generado."