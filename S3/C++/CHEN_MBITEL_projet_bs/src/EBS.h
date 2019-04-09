#ifndef EBS_H
#define EBS_H

#include <iostream>
#include "matrixFull.h"
class EBS
{
public:
	double T;
	int L;
	double K;
	double r;
	int N;
	int M;
	double sigma;
    bool payoff;/*if payoff is true ->put */

	EBS();

	ensiie::Matrix_Full C;

	void solve(double* a, double* b, double* c, double* d, int n);

	void initial_C(bool payoff,double K);


	~EBS();
};


#endif
