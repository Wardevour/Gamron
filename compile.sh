#!/bin/bash
# https://dl.dropboxusercontent.com/u/18726780/gamron.zip
find ./src -name *.java > sources_list.txt
javac -classpath "${CLASSPATH}":./jar/*:res @sources_list.txt