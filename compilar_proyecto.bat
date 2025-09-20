@echo off
echo ========================================
echo    CONFIGURADOR DE JAVA PARA RUA-JAVA
echo ========================================
echo.

:: Buscar instalaciones de Java en ubicaciones comunes
echo Buscando instalaciones de Java...
echo.

set "JAVA_FOUND="

:: Buscar en Android Studio (múltiples versiones)
if exist "C:\Program Files\Android\Android Studio\jbr\bin\java.exe" (
    echo [ENCONTRADO] C:\Program Files\Android\Android Studio\jbr
    set "JAVA_HOME=C:\Program Files\Android\Android Studio\jbr"
    set "JAVA_FOUND=1"
    goto :configure
)

if exist "C:\Program Files\Android\Android Studio\jre\bin\java.exe" (
    echo [ENCONTRADO] C:\Program Files\Android\Android Studio\jre
    set "JAVA_HOME=C:\Program Files\Android\Android Studio\jre"
    set "JAVA_FOUND=1"
    goto :configure
)

:: Buscar en ubicaciones estándar de Java
for /d %%i in ("C:\Program Files\Java\jdk*") do (
    if exist "%%i\bin\java.exe" (
        echo [ENCONTRADO] %%i
        set "JAVA_HOME=%%i"
        set "JAVA_FOUND=1"
        goto :configure
    )
)

for /d %%i in ("C:\Program Files\Eclipse Adoptium\jdk*") do (
    if exist "%%i\bin\java.exe" (
        echo [ENCONTRADO] %%i
        set "JAVA_HOME=%%i"
        set "JAVA_FOUND=1"
        goto :configure
    )
)

:configure
if defined JAVA_FOUND (
    echo.
    echo ========================================
    echo JAVA CONFIGURADO CORRECTAMENTE
    echo ========================================
    echo JAVA_HOME: %JAVA_HOME%
    echo.

    :: Configurar PATH
    set "PATH=%JAVA_HOME%\bin;%PATH%"

    :: Verificar Java
    echo Verificando instalacion de Java:
    "%JAVA_HOME%\bin\java" -version
    echo.

    echo ========================================
    echo COMPILANDO PROYECTO RUA-JAVA
    echo ========================================
    echo.

    echo [1/3] Limpiando proyecto...
    call .\gradlew clean
    echo.

    if %ERRORLEVEL% EQU 0 (
        echo [2/3] Compilando aplicacion...
        call .\gradlew assembleDebug
        echo.

        if %ERRORLEVEL% EQU 0 (
            echo ========================================
            echo   COMPILACION EXITOSA!
            echo ========================================
            echo.
            echo El proyecto RUA-Java se ha compilado correctamente.
            echo Puedes encontrar el APK en: app\build\outputs\apk\debug\
            echo.
        ) else (
            echo ========================================
            echo   ERROR EN LA COMPILACION
            echo ========================================
            echo Revisa los errores mostrados arriba.
        )
    ) else (
        echo ========================================
        echo   ERROR EN LIMPIEZA
        echo ========================================
        echo No se pudo limpiar el proyecto.
    )

) else (
    echo ========================================
    echo   ERROR: JAVA NO ENCONTRADO
    echo ========================================
    echo.
    echo Aunque Android Studio esta instalado, no se encontro Java.
    echo.
    echo SOLUCIONES:
    echo 1. Verificar que Android Studio este completo
    echo 2. Instalar Java JDK independiente desde: https://adoptium.net/
    echo 3. Configurar manualmente JAVA_HOME
    echo.
)

echo.
echo Presiona cualquier tecla para salir...
pause > nul
