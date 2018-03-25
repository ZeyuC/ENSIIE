#include "lire.h"
#include <stdio.h>

int main()
{
  lire();
  lire();
  fprintf(stderr,"je suis la cmd lire\n");
  execlp("./lire", "./lire", NULL);
  
  return 0;
}
