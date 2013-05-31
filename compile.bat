@ECHO OFF
REM https://dl.dropboxusercontent.com/u/18726780/gamron.zip
REM http://ninite.com/java-jdk/
javac -cp "src;jar\*;res" "%~dp0\src\com\gamron\dgenerate\Hero.java"
javac -cp "src;jar\*;res" "%~dp0\src\com\gamron\dgenerate\RpgDice.java"
javac -cp "src;jar\*;res" "%~dp0\src\com\gamron\MainClass.java"
javac -cp "src;jar\*;res" "%~dp0\src\gui\EscMenu.java"
PAUSE