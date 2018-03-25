#include "fichier.h"
#include "lexeme.h"
#include "pile.h"


#include <stdio.h>
#include <string.h>
#include <stdlib.h>
int eval(flux);

int main(int argc,char *argv[])
{

  flux fd = ouvre(argv[1]);
  int resulat=eval(fd);
  fprintf(stdout,"the sum is %d\n",resulat);
}

int eval(flux fd)
{
  char* mot;
  /*pile *pl= pile_vide();*/
  stack *pl= inti_stack();
  while(!fin_de_fichier(fd))
  {
 	 mot=lit_un_mot(fd);
 	 lexeme lx = chaine_vers_lexeme(mot);
 	 if (est_constante(lx))
  		push_s(pl,val_constante(lx));
 	 else if (est_plus(lx))
  		{
  		/*pl->value[pl->count-2]=pl->value[pl->count-1]+pl->value[pl->count-2];
  		pl->count--;*/
     int s1,s2,s;
      s1=pop_s(pl);
      s2=pop_s(pl);
      s=s1+s2;
      push_s(pl,s);

     /* s1=pop(pl);
      s2=pop(pl);
      s=s1+s2;
      push(pl,s);*/
 	   	}
  }
  /*return pop(pl);*/
  return pop_s(pl);
}









