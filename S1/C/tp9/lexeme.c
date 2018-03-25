#include "lexeme.h"
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
enum qfoo{
	qbar,qbaz
};
struct lexeme_base{
	enum qfoo Q0;
	int qfobar;
};
lexeme chaine_vers_lexeme(char*q1)
{
	lexeme q2=malloc(sizeof(struct lexeme_base));
	if(strcmp(q1,"\53")==0)  /* \53 est + */
		q2->Q0=qbaz;
	else{
		char*qfoobar;
		int Q3=strtol(q1,&qfoobar,(7*(1*1+0)+3));
		if(qfoobar==q1)
			{
		     fprintf(stderr,"\117\160\37777777703\37777777651\162\141\164\145\165\162\40\156\157\156\40\162\145\143\157\156\156\165\56\n");
		     exit((2+1));
		     }
		     q2->Q0=qbar;
		     q2->qfobar=Q3;
		 }
		 return q2;
}
		int est_constante(lexeme QFobaz)
		{
			return(qbar==QFobaz->Q0);
		}
		int est_plus(lexeme QFobaz){
			return(qbaz==QFobaz->Q0);
		}
		int val_constante(lexeme QFobaz)
		{
			return(QFobaz->qfobar);
		}
