#!/bin/sh
echo "INSTALLS/CHECKS JAVA JRE & JDK IN /opt"
echo "MUST PROVIDE A URL FOR JRE AND A PATH FOR JDK TO RESPECTIVE TARBALLS AVAIALABLE FROM ORACLE"
echo ""
cd /opt
JRE=$(ls | grep jre*)
JDK=$(ls | grep jdk*)

echo "would you like to remove $JRE, and update java? (y)es or else to skip:"
read word1
if [ $word1 = "yes" -o $word1 = "y" -o $word1 = "Y" ]; then
	echo "REMOVING JAVA!"
	sudo rm -r ./$JRE
	echo "ENTER THE URL FOR NEW JAVA TARBALL:"
	read word2
	sudo wget $word2 -O java.tar.gz
	sudo tar -zxvf java.tar.gz
	JRE=$(ls | grep jre*)
	sudo update-alternatives --install "/usr/bin/java" "java" "/opt/$JRE/bin/java" 1
	sudo update-alternatives --install "/usr/lib/mozilla/plugins/libjavaplugin.so" "mozilla-javaplugin.so" "/opt/$JRE/lib/amd64/libnpjp2.so" 1
	sudo update-alternatives --install "/usr/bin/javaws" "javaws" "/opt/$JRE/bin/javaws" 1
	sudo rm java.tar.gz
	echo "JAVA UPDATE COMPLETE"
else
	echo "OPERATION CANCELED"
fi

echo "would you like to remove $JDK, and update the jdk? (y)es or else to quit:"
read word1
if [ $word1 = "yes" -o $word1 = "y" -o $word1 = "Y" ]; then
	echo "REMOVING THE JDK!"
	sudo rm -r ./$JDK
	echo "ENTER THE PATH FOR NEW JDK TARBALL:"
	read word2
	sudo tar -zxvf $word2 -C ./
	JDK=$(ls | grep jdk*)
	sudo update-alternatives --install "/usr/bin/javac" "javac" "/opt/$JDK/bin/javac" 1
	sudo update-alternatives --install "/usr/bin/jar" "jar" "/opt/$JDK/bin/jar" 1
	sudo update-alternatives --install "/usr/bin/jarsigner" "jarsigner" "/opt/$JDK/bin/jarsigner" 1
	sudo update-alternatives --install "/usr/bin/javadoc" "javadoc" "/opt/$JDK/bin/javadoc" 1
	sudo update-alternatives --install "/usr/bin/javah" "javah" "/opt/$JDK/bin/javah" 1
	sudo update-alternatives --install "/usr/bin/javap" "javap" "/opt/$JDK/bin/javap" 1
	echo "JDK UPDATE COMPLETE"
else
	echo "OPERATION CANCELED"
fi
