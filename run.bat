@echo off
cd /d "%~dp0"
dir /s /b src\*.java > sources.txt
javac -cp "lib\jl1.0.1.jar" -d bin @sources.txt
java -cp "bin;lib\jl1.0.1.jar" com.github.jkarw7n.clilyricsviewer.Main
pause