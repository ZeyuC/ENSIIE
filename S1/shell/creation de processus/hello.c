#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <signal.h>
#include <string.h>
#include <errno.h>

int main(int argc, char*argv[])
{
  int pid_1 = fork();
  int s;
 
  if(pid_1==0)
    {
      sleep(2);
      fprintf(stdout,"%s","\n");
      exit(0);
    }
  if (pid_1== -1)
    exit(1);
  int pid_2 = fork();
  if(pid_2==0)
    {
      sleep(1);
      fprintf(stdout,"%s","world");
      exit(0);
    }
  if (pid_2==-1) exit(1);

  fprintf(stdout,"%s","hello");
  fflush(stdout);
  wait(&s);
  wait(&s);
  return 0;
}
