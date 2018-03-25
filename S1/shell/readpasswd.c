#include <stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<sys/stat.h>
#include<fcntl.h>
#include<string.h>
#include<errno.h>
int readpasswd(char pwd[16])
{
	char * ttypath = ttyname(0)?ttyname(0):ttyname(1)?ttyname(1):ttyname(2);
	if (ttypath == 0) return -1;

    FILE* fd;
    if((fd=fopen(ttypath,"r+"))==NULL)
    {
    	 fprintf(stderr,"%s pb",ttypath);
         exit(1);
    }

    printf("passwd: ");

    fflush(stdout);
    
    system("stty raw -echo");
    char s;
 
    for (int size=0; fread(&s,1,1,fd) && s!='\n'&& s!='\r' ;size++)
    {
    	if (s==127 && size>0){
    		printf("%s","\b \b");
    	}
    	printf("X");
    	if(size<15)
    		pwd[size]= s;
        else if (size==15)
        	pwd[size]=0;
    }
   
    if (size < 16){
    	for (int j=size;j<16;j++)
    		pwd[j]='c';
    }

    if (size >15)
     pwd[15]=0;
    else pwd[size]=0;

    
    /*for (;;)
    {
         fread(&s,1,1,fd);
    }*/
    printf("\r\n");

    system("stty -raw echo");

    fclose(fd);

    return size;
}

    int main()
    {
    	char pwd[16];
    	int n = readpasswd(pwd);
    	printf("%d\n",n);
    	printf("%s\n",pwd);
    	return 0;
    }
