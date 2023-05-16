@echo off

title Robot randoop script

REM nome dell'applicazione da testare
set name=TestXmlElement

REM indica il tempo per ogni esecuzione in secondi
set timelimit=20

REM Impostare data del giorno
set currentDate=15_05_2023

REM seed value casuale 
set seedvalue=!RANDOM!

echo Java

REM Percorso java1.8 da modificare per ogni computer
set java1.8_path= "C:\Program Files\Java\jre1.8.0_361\bin\java.exe"

java -version:1.8 -jar %java1.8_path%

echo JAVA 1.8:
rem VERIFICO CHE SIA STATA TROVATA LA JAVA 1.8
echo Display java version 
java -version

REM percorso della cartella dove è ubicato il JAR di EMMA
set emmapath=.\emma-2.0.5312\lib\emma.jar

REM percorso di RANDOOP
set RANDOOP_PATH=.

REM percorso della cartella di destinazione
set outputdir=%name%-%currentDate%-dati_di_copertura

REM percorso della classe da testare
set ipvar=.\classes

REM instrumentazione dell'archivio jar e creazione della cartella per contenere gli output
%java1.8_path% -noverify -classpath %emmapath% emma instr -m fullcopy -d %outputdir% -ip %ipvar% -out %outputdir%\coverage.em

REM esegui la sessione e salva le coperture in un file .ec
%java1.8_path% -noverify -classpath %outputdir%\classes;%emmapath%;randoop-all-3.0.6.jar randoop.main.Main gentests --testclass=%name% --timelimit=%timelimit%  --junit-output-dir=%outputdir%\%name%_ErrorTest

REM copia del file ec nella stessa cartella del em ed eliminazione del precedente
 copy coverage.ec %outputdir%\coverage.ec
 del coverage.ec

REM genera il report testuale effettuato il merge con i metadati del file .em
%java1.8_path% -classpath %emmapath% emma report -r txt -Dreport.txt.out.file=%outputdir%\coveragetot.txt -in %outputdir%\coverage.em,%outputdir%\coverage.ec
	

pause