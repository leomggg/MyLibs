param(
    [string]$KeystorePath = "C:\Users\Leo\Desktop\Workspaces\Java\MyLibs\keystore\mylibs.p12",
    [string]$Alias = "mylibs",
    [string]$Password = "changeit",
    [int]$ValidityDays = 3650,
    [string]$DName = "CN=localhost, OU=Dev, O=MyLibs, L=Local, S=NA, C=ES"
)

$keystoreDir = Split-Path -Parent $KeystorePath
if (-not (Test-Path $keystoreDir)) {
    New-Item -ItemType Directory -Path $keystoreDir | Out-Null
}

$keytool = Get-Command keytool -ErrorAction Stop
& $keytool.Source -genkeypair -alias $Alias -keyalg RSA -keysize 2048 -validity $ValidityDays -storetype PKCS12 -keystore $KeystorePath -storepass $Password -keypass $Password -dname $DName

Write-Host "Keystore creado en: $KeystorePath"

