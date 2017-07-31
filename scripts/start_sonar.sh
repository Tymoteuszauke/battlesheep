#!/bin/bash

function print_help_and_exit() {
	echo ' '
	echo 'First argument must be path where sonar will be run'
	echo 'Example of use: ./start_sonar.sh ./client '
	exit 1
}

#script
set -e

#check if it's directory where script will be run
PATH_TO_RUN=$1
if [[ ! -d $PATH_TO_RUN ]] ; then 
	echo 'no path to run script'
	print_help_and_exit
fi

echo 'Server needs 35sec for start'
echo -n 'Download and unzip sonar in'
echo $(dirname $0)

wget -nc -P $(dirname $0) "https://sonarsource.bintray.com/Distribution/sonarqube/sonarqube-6.4.zip"
unzip -n $(dirname $0)/sonarqube-6.4.zip -d $(dirname $0)/

echo $(dirname $0)/sonarqube-6.4/bin/linux-x86-64/
(cd $(dirname $0)/sonarqube-6.4/bin/linux-x86-64/ ; ./sonar.sh console &)
sleep 30

echo -n $PATH_TO_RUN
echo ' mvn sonar:sonar'

(cd $PATH_TO_RUN ; mvn sonar:sonar)
(cd $PATH_TO_RUN ; xdg-open ./target/sonar/report-task.txt)

echo ' '
echo 'IF IT IS A PROBLEM, KILL PROCESS TAKING PORT 9000 AND STOP RUNNED SONAR SERVER'
echo 'RUN SCRIPT scripts/sonar.sh stop TO STOP SONAR SERVER '













