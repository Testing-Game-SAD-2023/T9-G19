@echo off

title Robot randoop script

echo Java


java -version:1.8 

echo JAVA 1.8:
rem VERIFICO CHE SIA STATA TROVATA LA JAVA 1.8
echo Display java version 
java -version

REM percorso della cartella dove è ubicato il JAR di EMMA
set emmapath=.\emma-2.0.5312\lib\emma.jar

REM percorso di RANDOOP
set RANDOOP_PATH=.

REM nome java da verificare
set name=jipa

REM tempo limite calcolo RANDOOP
set timelimit=20

REM seed value casuale (da redere randomico)
set seedvalue=!RANDOM!

REM Impostare data del giorno (da rendere automatico)
set currentDate=17_04_23

REM percorso della cartella di destinazione
set outputdir=%name%-%currentDate%

REM CAMBIO IL NOME DELLA CARTELLA IN CUI VERRANNO PRODOTTI I DATI DI COPERTURA, PER CONSENTIRE MODIFICHE AGEVOLI FUTURE USO LO STESSO NOME mydatetime
set mydatetime=%name%-dati_di_copertura

REM percorso del jar da testare
set ipvar=.\jars\%name%.jar

REM instrumentazione dell'archivio jar e creazione della cartella per contenere gli output
%java1.8_path% -noverify -classpath %emmapath% emma instr -m fullcopy -d %outputdir%\%mydatetime% -ip %ipvar% -out %outputdir%\%mydatetime%\coverage.em

REM esegui la sessione e salva le coperture in un file .ec
%java1.8_path% -noverify -classpath "%outputdir%\%mydatetime%\lib\%name%.jar";%emmapath%;randoop-all-3.0.6.jar randoop.main.Main gentests --classlist=jars\%name%.txt --timelimit=%timelimit%  --no-regression-tests=true --junit-output-dir=%outputdir%\%name%_ErrorTest_%thread%

REM copia del file ec nella stessa cartella del em ed eliminazione del precedente
 copy coverage.ec %outputdir%\%mydatetime%\coverage.ec
 del coverage.ec

REM genera il report testuale effettuato il merge con i metadati del file .em
%java1.8_path% -classpath %emmapath% emma report -r txt -Dreport.txt.out.file=%outputdir%\%mydatetime%\coverage%cycle%.txt -in %outputdir%\%mydatetime%\coverage.em,%outputdir%\%mydatetime%\coverage.ec
	

pause