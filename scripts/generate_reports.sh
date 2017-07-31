#!/bin/bash

function print_help_and_exit() {
	echo ' '
	echo 'First argument must be path where script will be run'
	echo 'Example of use: ./generate_report ./ -j -c'
	echo '-ci Run mvn clean install'
	echo '-j Run Jacoco'
	echo '-f Run Findbugs'
	echo '-c Run Checkstyle'
	echo '-pf Show only result of checkstyle'
	echo '--all Run all plugins'
	echo '-h Show help'
	echo '--help Show help'
	exit 1
}

function run_result_with_browser() {
	FILE_PATH=$1
	if [ -e $FILE_PATH ]
		then sensible-browser $FILE_PATH
	fi
}

function validate_arguments() {
	for var in "$@"
	do	
		case $var in
			-ci);;
			-j);;
			-f);;
			-pf);;
			-c);;
			*)
				echo -n "bad flags or arguments: "
				echo $var
				print_help_and_exit
				;;
		esac
	done
}

function check_install() {
	case $1 in
		-ci)
			echo -n $2
			echo ' mvn clean install'

			(cd $2 ; mvn clean install)
			;;
	esac

}

function switch_input() {
	case $1 in
		-j)
			echo -n $2
			echo ' Jacoco is running'

			(cd $2 ; mvn jacoco:report)
		
			run_result_with_browser "$2/target/site/jacoco/index.html"
			;;
		-f)
			echo -n $2
			echo 'Findbugs is running'

			(cd $2 ; mvn site)
			;&
		-pf)
			run_result_with_browser "$2/target/site/findbugs.html"
			;;		
		-c)
			echo -n $2
			echo 'Checkstyle is running'

			(cd $2 ; mvn checkstyle:checkstyle)

			run_result_with_browser "$2/target/site/checkstyle.html"
			;;
	esac
}


# script
set -e

# check if it's help
for var in "$@"
do
	case $var in
		--help)
			;&
		--h)
			print_help_and_exit
			;;
	esac
done

#check if it's directory where script will be run
PATH_TO_RUN=$1
if [[ ! -d $PATH_TO_RUN ]] || [[ -z $2 ]] ; then 
	echo 'no path with flag to run script'
	print_help_and_exit
fi

#validate all arguments
validate_arguments "${@:2}"

#run mvn clean install if it's in parameters
for var in "${@:2}"
do
	check_install $var $PATH_TO_RUN
done

#run given plugins
for var in "${@:2}"
do
	switch_input $var $PATH_TO_RUN
done



















