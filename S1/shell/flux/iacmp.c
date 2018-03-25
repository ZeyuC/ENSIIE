#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <errno.h>

int main(int argc, char*argv[])
{
  setenv("IacmpDir","/pub/ia/sys/shell/iacmp",1);
  setenv("LD_LIBRARY_PATH","/pub/ia/sys/shell/iacmp/lib",1);
  /* char *const env[]={"IacmpDir=/pub/ia/sys/shell/iacmp","LD_LIBRARY_PATH=/pub/ia/sys/shell/iacmp/lib",NULL};
  if(execve("/pub/ia/sys/shell/iacmp/iacmp",argv,env)== -1){
    fprintf(stderr,"%s: pb execv : %s\n",argv[0],argv[0]);
    }*/
  if(execv("/pub/ia/sys/shell/iacmp/iacmp",argv)== -1){
    fprintf(stderr,"%s: pb execv : %s\n",argv[0],argv[0]);
    }
  return 0;
}
