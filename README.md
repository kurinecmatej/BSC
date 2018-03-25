This program is developed using Eclipse IDE. 
Source code is structured into packages. JUnit tests are in own package.
Project is managed by Maven and Spring framework is used for CDI.

This app contains two configuration files, for no need co rebuilding app to have values changes.
One is for nexchange rates conparision values between actual currency and USD - exchangerates
One is for output loop timer - timerconfig
For update values, just update this two files in .jar archive.

The input can be typed into the command line:

 *******************
 **** HOW TO RUN ***
 *******************
 runable file is located in bsc/targer
 
 run by:
 java -jar bsc-0.0.1-SNAPSHOT.jar inputfile.txt
 ---where inputfile.txt is optional parameter containing initial currency values. Filename can be optionally specified
 
When application is started, first lines are spring informations. Don't worry about that.

Sample input:
USD 1000.26
HKD 100
USD -100
RMB 2000
HKD 200.4895654

Sample output:
USD 900.26
RMB 2000 (USD 314.60)
HKD 300.49 (USD 38.63)

Program assumptions:

"Wrong format of value."
 - if currency input value is not in decimal format (E.g. string or non parsable format)
 
"Input currency name must be in UPPERCASE."
 - if currency exists in app, but not typed in UPPPERCASE  
 
"Please enter valid input."
 - if input is nonsense
 
"Amount of EUR can not be negative."
 - if result of amount processing will be negative

 EXCEPTIONS:
 - NoCurrenciesException
   - exception is thrown when no currencies are implemented in this application.
 - java.io.FileNotFoundException
   - when init file pasted as application parameter is not found
 
 
Project structure:
src
 |
 --sk.bcs.commons --commons utility classes
 |
 --sk.bsc.currencies --all active currencies
 |
 --sk.bsc.input --app input classes
 |
 --sk.bsc.output --app outpu classes
 |
 --sk.bsc.project --Main and init class
 |
 --sk.bsc.tests --test classes
 |
 --applicationContext.xml --spring application context configuration file
 |
 --exchangerates --config file with currencies exchange rates to calculate for USD comparing
 |
 --timerconfig --config file with value for output loop timer
 
 
 
 
 
 
