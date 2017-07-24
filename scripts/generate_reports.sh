#!/bin/bash

for var in "$@"
do
    	case $var in
		--help)
			echo '-j RUN JACOCO'
			echo '-f RUN FINDBUGS'
			echo '-c RUN CHECKSTYLE'
			echo '--all RUN ALL PLUGINS'
			;;
		-j)
			(cd ../ ; mvn install)
			echo 'RUN JACOCO'
			(cd ../ ; mvn jacoco:report)
			(cd ../client/target/site/jacoco/ ; sensible-browser index.html)
			(cd ../common/target/site/jacoco/ ; sensible-browser index.html)
			(cd ../server/target/site/jacoco/ ; sensible-browser index.html)
			;;
		-f)
			(cd ../ ; mvn install)
			echo 'RUN FINDBUGS'
			(cd ../ ; mvn site)
			(cd ../client/target/site/ ; sensible-browser index.html)
			(cd ../common/target/site/ ; sensible-browser index.html)
    			(cd ../server/target/site/ ; sensible-browser index.html)
			;;
		-c)
			(cd ../ ; mvn install)
			echo 'RUN CHECKSTYLE'
			(cd ../ ; mvn checkstyle:checkstyle)
			(cd ../client/target/site/ ; sensible-browser checkstyle.html)
			(cd ../common/target/site/ ; sensible-browser checkstyle.html)
			(cd ../server/target/site/ ; sensible-browser checkstyle.html)
			;;

		--all)
			(cd ../ ; mvn clean install)
			echo 'RUN ALL PLUGINS'
			(cd ../ ; mvn jacoco:report)
			(cd ../ ; mvn site)
			(cd ../ ; mvn checkstyle:checkstyle)

			(cd ../client/target/site/jacoco/ ; sensible-browser index.html)
			(cd ../common/target/site/jacoco/ ; sensible-browser index.html)
			(cd ../server/target/site/jacoco/ ; sensible-browser index.html)

			(cd ../client/target/site/ ; sensible-browser index.html)
			(cd ../common/target/site/ ; sensible-browser index.html)
			(cd ../server/target/site/ ; sensible-browser index.html)

			(cd ../client/target/site/ ; sensible-browser checkstyle.html)
			(cd ../common/target/site/ ; sensible-browser checkstyle.html)
			(cd ../server/target/site/ ; sensible-browser checkstyle.html)
			;;
	esac
done