@echo off
color 0A
title Compile Full source
echo Compilation process. Please wait...
ant -f build_jars_obf.xml -l build_jars_obf.log
echo Compilation successful!!!
pause