#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <signal.h>

const char* progname;

void sigpipe(int sig)
{
    fprintf(stderr,"%s:SIGPIPE reçu, abandon dans 5 secondes\n",progname);
    sleep(5);
    exit (0);
}

int main(int argc, char*argv[])
{
    progname=argv[0];
    fprintf(stderr,"%s: démarré\n",argv[0]);
    signal(SIGPIPE,sigpipe);
    fprintf(stderr,"%s: callback SIGPIPE positionné\n",argv[0]);
    while (1) {
        char c;
        int statut = read(0,&c,1);
        if (statut==-1) {
            fprintf(stderr,"%s:probleme lecture (%s)\n",
                argv[0],strerror(errno));
            return 1;
        } else if (statut==0) {
            write(2,".",1);
            usleep(100000); // 1/10 s
        } else { //statut==1
            write(1,&c,1);
        }
    }
    // return 0;
}
