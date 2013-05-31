#!/bin/bash
# https://dl.dropboxusercontent.com/u/18726780/gamron.zip
# EX: -cp minecraft.jar net.minecraft.LauncherFrame

java -cp ./src:./jar/*:res -Djava.library.path=./native/linux com.gamron.MainClass