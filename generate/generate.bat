@echo off
@color 0A
TITLE Console: LicenseGenerator j2dev
java -client -Xms1g -Xmx1g -Dfile.encoding=UTF-8 -cp compile/*; ru.j2dev.LicenseGenerator
pause