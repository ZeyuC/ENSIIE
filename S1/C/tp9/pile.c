#include "pile.h"

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

pile* pile_vide()
{
	   pile *pl = malloc(sizeof(pile));
     pl->count=0;
     return pl;
}
int  est_vide(pile *pl)
{
    if (pl->count == 0) return 1;
    return 0;
}

void push(pile *pl,int n)
{
if (pl->count > MAX_SIZE) {
    printf("stack is full\n");
    exit (1);
  }
   pl->value[pl->count]=n;
   pl->count++;
}
int  pop(pile *pl)
{
   if (!est_vide(pl))
   {
   	(pl->count)-- ;
   	return pl->value[pl->count];
   }
   else 
   	{
   		printf("The stack is_empty");
        exit (1);
    }
}

