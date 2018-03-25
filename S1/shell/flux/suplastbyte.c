#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<sys/stat.h>
#include<fcntl.h>
#include<string.h>
#include<errno.h>
int main(int  argc,char* argv[])
{
  if (argc !=2){
    fprintf(stderr,"%s usage:%sf1f2",argv[0],argv[0]);
    exit(1);
    }
  char *fn = argv[1];
  int fd;
  fd = open(fn,O_RDONLY);
  if (fd < 0){
    fprintf(stderr,"%s pbouverture %s",argv[0],argv[1]);
    exit (1);
  }
  int size= lseek(fd,0,SEEK_END); 
  size-=1;
  char* tmp = (char*)malloc(size);              
  lseek(fd,0,SEEK_SET);                                   //pour remettre 'SEEK_CUR"
  if(read(fd,tmp,size) != size){
    fprintf(stderr,"%s pblecture %s",argv[0],argv[1]);
    exit (1);
  }
  close(fd);                                                  
  if((fd=open(fn,O_WRONLY|O_TRUNC)) < 0){
    fprintf(stderr,"%s pbouverture %s",argv[0],argv[1]);
    exit (1);
  }
  if(write(fd,tmp,size)!=size){
    fprintf(stderr,"%s pbecriture %s",argv[0],argv[1]);
    exit (1);
  }
  close(fd);
  free(tmp);
  return 0;
}
