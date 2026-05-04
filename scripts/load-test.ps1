param(
    [string]$BaseUrl = "http://localhost:8081",
    [int]$TotalRequests = 50,
    [int]$Concurrency = 5
)

$healthUrl = "$BaseUrl/api/health"
Write-Host "Load test: $healthUrl (Total=$TotalRequests, Concurrency=$Concurrency)"

$sw = [System.Diagnostics.Stopwatch]::StartNew()
$jobs = @()
$completed = 0
$failed = 0

for ($i = 0; $i -lt $TotalRequests; $i++) {
    $jobs += Start-Job -ScriptBlock {
        param($url)
        try {
            $response = Invoke-WebRequest -Uri $url -UseBasicParsing -TimeoutSec 5
            return $response.StatusCode
        } catch {
            return 0
        }
    } -ArgumentList $healthUrl

    if ($jobs.Count -ge $Concurrency) {
        $done = Wait-Job -Job $jobs -Any
        foreach ($j in $done) {
            $status = Receive-Job -Job $j
            if ($status -eq 200) { $completed++ } else { $failed++ }
            Remove-Job -Job $j
        }
        $jobs = @($jobs | Where-Object { $_.State -eq "Running" })
    }
}

$doneAll = Wait-Job -Job $jobs
foreach ($j in $doneAll) {
    $status = Receive-Job -Job $j
    if ($status -eq 200) { $completed++ } else { $failed++ }
    Remove-Job -Job $j
}

$sw.Stop()
Write-Host "OK: $completed"
Write-Host "Failed: $failed"
Write-Host ("Elapsed: {0:N2}s" -f $sw.Elapsed.TotalSeconds)
