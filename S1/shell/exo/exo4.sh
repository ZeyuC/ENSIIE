#!/bin/bash
#
#usage: exo4.sh arg

if test $# != 1 ; then
   echo 1>&2 "$0:FATAL: $# invalid argument number (expected 1)"
   exit 1
fi

arg=$1
grep $arg data > /dev/null
if test $? -eq 0; then
   grep $arg data | cut -d "," -f 1,3
else
   echo "valeur absente"
fi
