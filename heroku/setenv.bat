@ECHO OFF

REM Make sure the following variables are correctly set
SET HOMEDRIVE=C:
SET HOMEPATH=\Temp\
SET JAVA_HOME=C:\Progra~1\Java\jdk-17.0.1
SET HEROKU_PATH=C:\Progra~1\heroku\bin\
SET BROWSER=C:\Progra~1\Google\Chrome\Application\chrome.exe
SET HEROKU_DEBUG=1

SET APP_NAME=games-ranking-api
SET PROJECT_PATH=C:\Users\dius_\Documents\workspace-spring-tool-suite-4-4.13.0.RELEASE\%APP_NAME%\
SET PROJECT_HEROKU_PATH=%PROJECT_PATH%heroku\

REM Use the following command to create the API key/token and set it
REM heroku authorizations:create
SET HEROKU_API_KEY=61683855-ace5-4e46-9101-a8c97c393abe