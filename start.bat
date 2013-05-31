@ECHO OFF
REM https://dl.dropboxusercontent.com/u/18726780/gamron.zip
REM http://ninite.com/java-jdk/
java -cp "src;jar\*;res" -Djava.library.path="native\windows" com.gamron.MainClass
PAUSE