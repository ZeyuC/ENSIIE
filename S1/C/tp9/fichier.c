#include "fichier.h"
#include <stdio.h>
#include <stdlib.h>
struct flux_base{
	FILE* qfoo;
};
flux ouvre(char*qbar)
{
	flux qbaz=malloc(sizeof(struct flux_base));
	qbaz->qfoo=fopen(qbar,"\162");
	return qbaz;
}
	void ferme(flux 
qfoo){fclose(qfoo->qfoo);}char*lit_un_mot(flux qfoo){char*Q0=calloc(1000,
sizeof(char));fscanf(qfoo->qfoo,"\45\163",Q0);return Q0;}int fin_de_fichier(
flux qfoo){return(feof(qfoo->qfoo));}
