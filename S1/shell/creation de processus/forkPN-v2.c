#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>

// attention: c'est pas un header système
#include "waitverbose.h"

int pid_pos,pid_neg;
int pid_fils;
int pos[2];
int neg[2];
int stauts;
void callback_1(int signum)
{
  printf("%d %d %d",pid_fils,pid_pos,pid_neg);

  printf("pere:%d: fin inattendue du fils",pid_fils);
  if(pid_fils==pid_pos){
  printf("pere:%d: tue fils",pid_neg);
  kill(pid_neg,SIGKILL);
  }
  else
    {
  printf("pere:%d: tue fils",pid_pos);
  kill(pid_pos,SIGKILL);
    }
  printf("pere:%d: mes fils sont morts");
}

void fils(char* nickname, int in)
{
  int x;
  int len=sizeof x;
  int ret;
  while (1) {
    ret = read (in,&x,len);
    if (ret==0) exit (0);
    if (ret!=len) continue;
    printf("%s:%d: %d\n",nickname,getpid(),x);
    if (x==0) break;
  }
  exit(0);
}

void pere(char* nickname, int pos[2], int neg[2])
{
  int x;
  int len=sizeof x;
  int ret;
  dup2(2,1);
  while (1) {
    printf("entrez un entier: ");
    ret = scanf("%d",&x);
    if (ret == EOF) exit (1);
    if ( ret!=1 ) {
      fprintf(stderr,"%s:%d: probleme lecture stdin.\n",
         nickname,getpid());
      while ( getchar()!='\n' );
      continue;
    }
    signal(SIGCHLD,callback_1)
    signal(SIGPIPE,callback_2);
    
    if ( x>=0 ) write(pos[1],&x,len);
    if ( x<=0 ) write(neg[1],&x,len);
    if ( x==0 ) break;
  }
  waitendverbose(0);
  waitendverbose(0);
  exit(0);
}

int main(int argc, char*argv[])
{
  
  if (argc!=1) {
      fprintf(stderr,"%s:usage: ./%s\n",argv[0]);
      exit (1);
  }
  if ( pipe(pos)<0 || pipe(neg) ) {
    fprintf(stderr,"%s: probleme creation pipe\n",argv[0]);
    exit (1);
  }
  if ( (pid_pos=fork())==0 ) 
    fils("filsP",pos[0]);
  else if ( (pid_neg=fork())==0 ) 
    fils("filsN",neg[0]);
  else
    pere("pere",pos,neg);
  
  fprintf(stderr,"Argh!!!\n");
  return 255;
}
