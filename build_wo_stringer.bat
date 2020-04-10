@echo off
color 0A
title Compile Full source
echo Compilation process. Please wait...
ant -f build_wo_stringer.xml -l build.log
echo Compilation successful!!!
pause