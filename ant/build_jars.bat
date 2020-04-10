@echo off
color 0A
title Compile Full source
echo Compilation process. Please wait...
ant -f build_jars.xml -l build_jars.log
echo Compilation successful!!!
pause