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
  FILE* fd;
  fd = fopen(fn,"r");
  if (fd == NULL){
    fprintf(stderr,"%s pbouverture %s",argv[0],argv[1]);
    exit(1);
  }
  //resussir retourn 0
  if(fseek(fd,0,SEEK_END)){
    fprintf(stderr,"%s pbfseek %s",argv[0],argv[1]);
  }
  int size=ftell(fd);
  size-=1;
  char* tmp = (char*)malloc(size);
   //pour remettre 'SEEK_CUR"
   if(fseek(fd,0,SEEK_SET)){
    fprintf(stderr,"%s pbfseek %s",argv[0],argv[1]);
  }
 
   if(fread(tmp,1,size,fd)==0){
    fprintf(stderr,"%s pblecture %s",argv[0],argv[1]);
    exit (1);
  }
  fclose(fd);                                                  
  if((fd=fopen(fn,"w"))==NULL){
    fprintf(stderr,"%s pbouverture %s",argv[0],argv[1]);
    exit (1);
  }
  if(fwrite(tmp,1,size,fd)==0){
    fprintf(stderr,"%s pbecriture %s",argv[0],argv[1]);
    exit (1);
  }
  fclose(fd);
  free(tmp);
  return 0;
}

