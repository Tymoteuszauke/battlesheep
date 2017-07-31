#!/bin/bash

#script
set -e

#(cd $(dirname $0) ; git clone https://github.com/Tymoteuszauke/battlesheep.git)
(cd $(dirname $0)/battlesheep ; mvn post-site)
(cd $(dirname $0) ; ./start_sonar.sh ./battlesheep)
(cd $(dirname $0)/battlesheep ; mvn exec:java -pl client -Dexec.mainClass=com.blackship.battlesheep.AppClient &)
