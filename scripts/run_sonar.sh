#!/bin/bash

echo 'SERVER NEEDS 35SEC TO START'
wget -nc "https://sonarsource.bintray.com/Distribution/sonarqube/sonarqube-6.4.zip"
unzip sonarqube-6.4.zip
(cd ./sonarqube-6.4/bin/linux-x86-64/ ; ./sonar.sh console &)
sleep 30
(cd ../client; mvn sonar:sonar)
(cd ../common; mvn sonar:sonar)
(cd ../server; mvn sonar:sonar)

(cd ../client/target/sonar ; sensible-browser report-task.txt)
(cd ../common/target/sonar ; sensible-browser report-task.txt)
(cd ../server/target/sonar ; sensible-browser report-task.txt)

echo ' '
echo 'IF IT IS A PROBLEM, KILL PROCES TAKING PORT 9000 AND STOP RUNNED SONAR SERVER'
echo 'TYPE TO STOP SONAR SERVER "(cd ./sonarqube-6.4/bin/linux-x86-64/ ; ./sonar.sh stop)"'
echo 'OR RUN ./stop_sonar.sh'
echo ' '
echo 'RESULTS FROM PLUIGN ARE IN [client|server]/target/site/'
echo ' '