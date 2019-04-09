#ifndef EReduit_H
#define EReduit_H

#include "EBS.h"
#include "matrixFull.h"

class EReduit: public EBS
{
public:
	 EReduit();
	 ensiie::Matrix_Full A;
	 ensiie::Matrix_Full U;
	 void initial_U(EReduit &m);
	 void initial_A();
	
	 void decompose(double *bot,double *mid,double *sup,  EReduit &Mo);
	 ensiie::Matrix_Full *reduit ( EReduit & m);
	 void solve(double* a, double* b, double* c, double* d, int n);

	 ~EReduit();
};

#endif
