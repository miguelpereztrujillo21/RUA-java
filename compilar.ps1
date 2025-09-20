# Script PowerShell para compilar RUA-Java
Write-Host "========================================" -ForegroundColor Green
Write-Host "   COMPILADOR DE PROYECTO RUA-JAVA" -ForegroundColor Green
Write-Host "========================================" -ForegroundColor Green
Write-Host ""

# Configurar JAVA_HOME
$javaPath = "C:\Program Files\Android\Android Studio\jbr"
if (Test-Path "$javaPath\bin\java.exe") {
    $env:JAVA_HOME = $javaPath
    $env:PATH = "$javaPath\bin;$env:PATH"

    Write-Host "Java configurado correctamente:" -ForegroundColor Green
    Write-Host "JAVA_HOME: $env:JAVA_HOME" -ForegroundColor Cyan
    Write-Host ""

    # Verificar Java
    Write-Host "Verificando version de Java..." -ForegroundColor Yellow
    & "$javaPath\bin\java.exe" -version
    Write-Host ""

    # Limpiar proyecto
    Write-Host "Limpiando proyecto..." -ForegroundColor Yellow
    & ".\gradlew.bat" clean

    if ($LASTEXITCODE -eq 0) {
        Write-Host "Limpieza exitosa" -ForegroundColor Green
        Write-Host ""

        # Compilar proyecto
        Write-Host "Compilando aplicacion..." -ForegroundColor Yellow
        & ".\gradlew.bat" assembleDebug

        if ($LASTEXITCODE -eq 0) {
            Write-Host "========================================" -ForegroundColor Green
            Write-Host "   COMPILACION EXITOSA!" -ForegroundColor Green
            Write-Host "========================================" -ForegroundColor Green
            Write-Host ""
            Write-Host "El APK se encuentra en: app\build\outputs\apk\debug\" -ForegroundColor Cyan
        } else {
            Write-Host "Error en la compilacion" -ForegroundColor Red
        }
    } else {
        Write-Host "Error en la limpieza del proyecto" -ForegroundColor Red
    }
} else {
    Write-Host "No se encontro Java en Android Studio" -ForegroundColor Red
}

Write-Host ""
Write-Host "Presiona Enter para continuar..."
Read-Host
