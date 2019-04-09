#ifndef EComplet_H
#define EComplet_H

#include "EBS.h"
#include "matrixFull.h"


class EComplet: public EBS
{
public:
	 EComplet();
	 ensiie::Matrix_Full A;
	 ensiie::Matrix_Full B;
	 void initial_A();
	 void initial_B();
	 void decompose(double *bot,double *mid,double *sup,  EComplet &Mo);
	 ensiie::Matrix_Full* complet(EComplet &);
	 void solve(double* a, double* b, double* c, double* d, int n);
	 ~EComplet();
};

#endif
