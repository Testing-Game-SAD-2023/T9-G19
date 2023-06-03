@echo off

title Robot randoop script

REM nome dell'applicazione da testare
set name=%1

REM indica il tempo per ogni esecuzione in secondi
set timelimit=%2

REM Impostare data del giorno
set currentDate=%3

REM seed value casuale 
set seedvalue=!RANDOM!


REM Percorso java1.8 da modificare per ogni computer
set java1.8_path= "C:\Program Files\Java\jre-1.8\bin\java.exe"

REM Percorso java1.8 da modificare per ogni computer
set javac= "C:\Program Files\Java\jdk-1.8\bin\javac.exe"

REM percorso della cartella dove è ubicato il JAR di EMMA
set emmapath=.\emma-2.0.5312\lib\emma.jar

REM percorso di RANDOOP
set RANDOOP_PATH=.

REM percorso della cartella di destinazione
set outputdir=%name%-%currentDate%-dati_di_copertura

REM percorso della classe da testare
set ipvar=.\classes

REM percorso della classe da compilare
set classe= classes\%name%.java

REM creare un .class
echo ---COMPILAZIONE---
%javac% %classe%

REM instrumentazione dell'archivio jar e creazione della cartella per contenere gli output
echo ---INSTRUMENTAZIONE---
%java1.8_path% -noverify -classpath %emmapath% emma instr -m fullcopy -d %outputdir% -ip %ipvar% -out %outputdir%\coverage.em

REM esegui la sessione e salva le coperture in un file .ec
echo ---ESEGUILASESSIONE---
%java1.8_path% -noverify -classpath %outputdir%\classes;%emmapath%;randoop-all-3.0.6.jar randoop.main.Main gentests --testclass=%name% --timelimit=%timelimit%  --junit-output-dir=%outputdir%\%name%_ErrorTest

REM copia del file ec nella stessa cartella del em ed eliminazione del precedente
echo ---COPIAFILENELLAREPOSITORY---
copy coverage.ec %outputdir%\coverage.ec
del coverage.ec

REM genera il report testuale effettuato il merge con i metadati del file .em
echo ---GENERAZIONEREPORT---
%java1.8_path% -classpath %emmapath% emma report -r xml -Dreport.xml.out.file=%outputdir%\coveragetot.xml -in %outputdir%\coverage.em,%outputdir%\coverage.ec
	

pause