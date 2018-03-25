#!/bin/bash
#
#usage: exo3.sh arg

if test $# != 1
then
   echo 1>&2 "$0:FALSE: invalid argument number(expected 1)"
   exit 1
fi

read N

arg=$1
i=0
str=""

while [ $i -lt $N ]
do
    str="$str $arg"
    i=$[$i+1]
done
echo $str > $arg

 
