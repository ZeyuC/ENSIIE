#include <stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<sys/stat.h>
#include<fcntl.h>
#include<string.h>
#include<errno.h>
int sz(char* fn)
{
  int fd = open(fn,O_RDONLY);
  if (fd< 0){
    fprintf(stderr,"%s pbouverture ",*fn);
    exit(1);
  }
  int size = lseek(fd,0,SEEK_END);
  size-=1;
  lseek(fd,0,SEEK_SET);
  close(fd);
  return size;
}
int main(int argc,char*argv[])
{
  int size = sz(argv[1]);
  int fd = open(argv[1],O_RDONLY);
  char* tmp = (char*)malloc(size/4);
  int i,nom_ligne,k,s,pid[4];
  int buf[256];
  int fpipe[2];
      if (pipe(fpipe)<0)               // creer des pipe
	{
	  fprintf(stderr,"pipe error!\n");
	  exit (1);
	}
  for(i=0;i<4;i++)
    {
      nom_ligne=0;
      pid[i]=fork();
     
      if(pid[i]==0)
	{
	  read(fd,tmp,size/4);
	  if (i<3){
	  for(k=0;k<size/4;k++)
	    {
	      if(tmp[k]=='\n')
		nom_ligne++;
	    }
	  }
	  else{
	     for(k=0;k<size-3*size/4;k++)
	    {
	      if(tmp[k]=='\n')
		nom_ligne++;
	    }
	  }
	  if (i==0) nom_ligne++;     //il faut ajouter 1 pour premiere partie
	  fprintf(stdout,"%d\n",nom_ligne);
	  write(fpipe[1],&nom_ligne,sizeof(int));
	  exit(0);
	}
      if(pid[i]==-1) exit(-1);
      
    }
  for(i=0;i<4;i++)
    wait(&s);
  int buff,somme=0;
  for(i=0;i<4;i++)
    {
      read(fpipe[0],&buff,sizeof(int));
      somme+=buff;
    }
   fprintf(stdout,"%d\n",somme);
  return 0;
}
