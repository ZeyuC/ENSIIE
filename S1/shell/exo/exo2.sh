#!/bin/bash
#
#usage: exo2.sh arg

if test $# != 1 ; then
   echo 1>&2 "$0:false : $# invalid argument number (expected 1)"
   exit 1
fi


arg=$1

echo  "<arg> <arg>" > $arg 
