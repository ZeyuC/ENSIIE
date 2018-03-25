#!/bin/bash
#
#age: exo4.sh arg

if test $# != 1 ; then
   echo 1>&2 "$0:FATAL: $# invalid argument number (expected 1)"
   exit 1
fi

arg=$1
str=""
annee=0

while read str; do
    annee=$(grep $str data | cut -d "," -f 2 | cut -d "/" -f 3)
    if test $annee -ge $arg ; then
        echo $(grep $str data  | cut -d "," -f 1)
    fi
done < data
