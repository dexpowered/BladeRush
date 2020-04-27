@echo off
@color 0B
:start
echo Starting GameServer.
echo.
set PATH=C:\Program Files\Java\jdk1.8.0_77\bin\
java -server -Duser.timezone=GMT+3 -Dfile.encoding=UTF-8 -Xmx4G -cp config;../lib/* ru.l2.gameserver.GameServer

REM Debug ...
REM java -Dfile.encoding=UTF-8 -cp config;./* -Xmx3G -Xnoclassgc -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=7456 ru.l2.gameserver.GameServer

if ERRORLEVEL 2 goto restart
if ERRORLEVEL 1 goto error
goto end
:restart
echo.
echo Server restarted ...
echo.
goto start
:error
echo.
echo Server terminated abnormaly ...
echo.
:end
echo.
echo Server terminated ...
echo.

pause
