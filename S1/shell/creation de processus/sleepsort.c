#include <stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<sys/stat.h>
#include<fcntl.h>
#include<string.h>
#include<errno.h>

int main(int argc,char*argv[])
{
  if (argc == 1)
    fprintf(stderr,"%s","%s usage %s n1,n2...",argv[0],argv[0]);
  int i,pid[argc],status;
  char *nom="test.txt";
  FILE *fd = stdout; /* fopen(nom,"a"); */
  for (i=1;i<argc;i++)
    {
      pid[i]=fork();
      if (pid[i]==0)
	{
	  sleep(atoi(argv[i]));
	  fprintf(fd,"%s ",argv[i]);
	  exit(0);
	}
      if (pid[i]==-1) exit(1);
    }
  for (i=1;i<argc;i++)
    wait(&status);
  fprintf(fd,"%s","\n");
}
