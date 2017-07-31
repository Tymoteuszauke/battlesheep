#!/bin/bash

#script
set -e 

(cd $(dirname $0)/sonarqube-6.4/bin/linux-x86-64/ ; ./sonar.sh stop)
