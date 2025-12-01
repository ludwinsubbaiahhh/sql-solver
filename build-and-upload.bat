@echo off
echo ====================================
echo Building JAR File
echo ====================================

REM Check if target directory exists and build
if not exist "target" (
    echo Building project...
    echo Please run this from VS Code terminal after installing Java Extension Pack
    echo Or run: mvn clean package
    pause
    exit
)

echo.
echo Copying JAR to releases folder...
if exist "target\webhook-sql-solver-*.jar" (
    copy "target\webhook-sql-solver-*.jar" "releases\webhook-sql-solver-1.0.0.jar"
    echo.
    echo JAR copied successfully!
    echo.
    echo Now run these commands:
    echo   git add releases/webhook-sql-solver-1.0.0.jar
    echo   git commit -m "Add JAR file"
    echo   git push origin main
) else (
    echo JAR file not found in target folder!
    echo Please build the project first using VS Code Java Extension.
)

pause

