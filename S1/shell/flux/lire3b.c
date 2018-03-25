#include "lire.h"
#include <stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<sys/stat.h>
#include<fcntl.h>
#include<string.h>
#include<errno.h>

int main(int argc,char* argv[])
{
  if(argc == 1){
    fprintf(stderr, "./lire3a:  usage: ./lire3a infile outfile");
    exit(1);
  }
  Char *infile=argv[1];
  char *outfile=argv[2];
  char *tmp;
  int in,out;
  if((in = open(infile,O_RDONLY)) < 0){
    fprintf(stderr, "./lire3a: can not open %s for reading : no such file", infile);
     exit (1);
  }
  if((out = open(outfile,O_WRONLY|O_TRUNC|O_CREAT)) < 0){
    fprintf(stderr,"./lire3a: can not open %s for writing : no such file", outfile);
     exit (1);
  }
  
  lire();
  dup2(in,0);
  dup2(out,1);
  lire();

  execlp("./lire", "./lire", NULL);
  
  return 0;
}
