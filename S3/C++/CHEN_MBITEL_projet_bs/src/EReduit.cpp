#include <stdio.h>
#include "EReduit.h"

#include <cmath>
EReduit::EReduit()
{
	T = 1.0;
	K = 100.0;
	L = 300;
    r = 0.1;
	sigma = 0.1;
	payoff = true;
	initial_A();
}

/// \brief initialize U
void EReduit::initial_U(EReduit &m)
{
	double d_t=T/N;
	ensiie::Matrix_Full U(N+1,M+2);
	for(int i=0;i<N+1;i++)
	{
		U(i,0)= exp(r*(T-i*d_t))*m.C(i,0);
		U(i,M+1)= exp(r*(T-i*d_t))*m.C(i,M+1);
	}
	for(int i=0;i<M+2;i++)
	{
		U(N,i)= m.C(N,i);
	}
	this->U = U;	
}
/// \brief initialize A
void EReduit::initial_A()
{
	ensiie::Matrix_Full A(M+2,M+2);
	double d_t = T/N;
	double alpha = -sigma*sigma/2;
	double d_x = (log1p(L)-1)/(M+1)+ (r-(sigma*sigma)/2)/(M+1)*-d_t;
	double beta = alpha*d_t/(d_x*d_x);
	A(0,0)= exp(-r*d_t);
	for(int i=1;i<M+1;i++)
	{
		A(i,i)=1-2*beta;
		A(i,i-1)=beta;
		A(i,i+1)=beta;
	}
	A(M+1,M+1)=1;
	this->A=A;
}

/// \brief sovle the problem like Ax=b where A is a tridiagonal matrix
/// \param a first diagonal below  the main diagonal
/// \param b main diagonal
/// \param c the first diagonal above the main diagonal
/// \param d b
/// \param n the size
/// \note the result will be stocked in d
void EReduit::solve(double* a, double* b, double* c, double* d, int n) {

    n--; // since we start from x0 (not x1)
    c[0] /= b[0];
    d[0] /= b[0];

    for (int i = 1; i < n; i++) {
        c[i] /= b[i] - a[i]*c[i-1];
        d[i] = (d[i] - a[i]*d[i-1]) / (b[i] - a[i]*c[i-1]);
    }

    d[n] = (d[n] - a[n]*d[n-1]) / (b[n] - a[n]*c[n-1]);

    for (int i = n; i-- > 0;) {
        d[i] -= c[i]*d[i+1];
    }
}

/// \brief decompose the A to three vector bot,mid and sup
/// \param bot first diagonal below  the main diagonal
/// \param mid main diagonal
/// \param sup the first diagonal above the main diagonal
/// \param Mo a EComplet Objet
void EReduit::decompose(double *bot,double *mid,double *sup, EReduit &Mo)
{
	ensiie::Matrix_Full m(Mo.A);
	sup[Mo.M+1]=0.0;
	bot[0]=0.0;
	mid[0]=m(0,0);
	for(int i=1;i<Mo.M+2;i++)
	{
		sup[i-1]=m(i-1,i);
		bot[i]=m(i,i-1);
		mid[i]=m(i,i);
	}
}


/// \brief solve the our problem with complet methode
/// \param m a EReduit objet
ensiie::Matrix_Full *EReduit::reduit(EReduit & m)
{
	ensiie::Matrix_Full *Un = new ensiie::Matrix_Full[m.N+1];

	for(int n = m.N; n>=0 ; n--)
	{
		Un[n].data_ = new double[m.M+2];
		Un[n].nc=1;
		Un[n].nl=m.M+2;
		for(int i=0;i<m.M+2;i++)
			{
				Un[n](i,0)=m.U(n,i);
			}
		
	}

	double *sup = new double[m.M+2];
	double *bot = new double[m.M+2];
	double *mid = new double[m.M+2];
	int size = m.M+2;

	decompose(bot, mid, sup,m);
	
	
	for(int n = m.N; n>0 ; n--)
		{
		   //std::cout<<Cn[n]<<"\n";
		   ensiie::Matrix_Full res = Un[n];
		   res.data_=Un[n].data_;
		  
		   m.solve(bot, mid, sup,res.data_,size);
		   
		   for(int i=1;i<m.M+1;i++)
		   	{		
		   			
		   			Un[n-1](i,0)=res.data_[i];
		   			m.U(n-1,i)=res.data_[i];

		   	}
		   
		   //Cn[n-1]=res;
		}
    Un[0] = Un[0]*exp(-r);
	delete []sup;
	delete []bot;
	delete []mid;
   
	return Un;
}
EReduit::~EReduit()
{

}
