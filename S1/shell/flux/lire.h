#ifndef FILE_LIRE_H
#define FILE_LIRE_H

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <string.h>

void lire()
{
   int nbCarLus,messlen,nbCarEcrits;
   char tab[100];

   nbCarLus = read(0,tab,10);
   if (nbCarLus == -1) {
     fprintf(stderr,"lire(): pb de lecture : %s\n",
         strerror(errno));
     exit(1);
   }

   fprintf(stderr,"lire(): nbCarLus = %d\n",nbCarLus);

   nbCarEcrits=write(1,tab,nbCarLus);
   if (nbCarEcrits == -1) {
     fprintf(stderr,"lire(): pb d'ecriture : %s\n",
         strerror(errno));
      exit(1);
   }
}

#endif
