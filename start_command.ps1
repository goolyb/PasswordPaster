$ScriptDir = Split-Path -Parent $MyInvocation.MyCommand.Path
Set-Location $ScriptDir

Write-Host "Working directory: $(Get-Location)"

if (-not (Test-Path "passwords.txt")) {
    Write-Error "Error: passwords.txt not found in $(Get-Location)"
    exit 1
}

mvn clean compile exec:java -Dexec.mainClass="org.example.Main"