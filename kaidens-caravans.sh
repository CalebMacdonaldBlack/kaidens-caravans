#!/usr/bin/env bash

# Enhance to use getopts to echo additional flags into the test command
txtund=$(tput sgr 0 1)		  # Underline
txtbld=$(tput bold)			 # Bold
grn=$(tput setaf 2)			 # Green
red=$(tput setaf 1)			 # Red
bldgrn=${txtbld}$(tput setaf 2) # Bold Green
bldred=${txtbld}$(tput setaf 1) # Bold Red
txtrst=$(tput sgr0)			 # Reset

usage()
{
cat << EOF
${txtbld}DESCRIPTION${txtrst}
	${txtbld}run${txtrst}
	   Builds and runs the backend application
    ${txtbld}figwheel${txtrst}
       Builds and runs figwheel
    ${txtbld}install${txtrst}
      install dependencies such as npm packages
EOF
exit 1
}

echo_message() {
	echo "${bldgrn}[kaidens-caravans.sh]${txtrst} ${grn}$1${txtrst}"
}

run () {
    echo_message 'Running backend'
    lein run
}

figwheel() {
    echo_message 'compiling gulp'
    gulp css
    echo_message 'Running figwheel'
    lein figwheel
}

install() {
    echo_message 'installing npm packages'
    npm install
}

parse () {
	if [[ $# -eq 0 ]]; then
		usage
    elif [ $1 = "run" ]; then
	    run
    elif [ $1 = "install" ]; then
	    install
    elif [ $1 = "figwheel" ]; then
        figwheel
	else
	  usage
	fi
}

parse $@
