#include "EBS.h"
#include "matrixFull.h"
#include <cmath>


/// \brief Constructor
EBS::EBS()
{
	T = 1.0;
	K = 100.0;
	L = 300;
	N = 500;
	M = 500;
    r = 0.1;
	sigma = 0.1;
	payoff = true;

}


/// \brief initialize C(n,m)
/// \param payoff if true it's put
/// \param K an double
void EBS::initial_C(bool payoff,double K)
{
   ensiie::Matrix_Full C(N+1,M+2);


   double d_s = L/(M+1);
   double d_t = T/N;


   if(payoff==true)
   {
	   for(int j=0;j<M+2;j++)
	   {
		   double s = d_s*j;
		   if(K-s>0)
			   C(N,j)=K-s;
		   else
			   C(N,j)=0;

	   }

	   for(int i=0;i<N+1;i++)
	   	   {
		      double p = -r*(T-i*d_t);
		      //std::cin>>p;
	   		  C(i,0)=K*exp(p);
	   	   }
	   for(int i=0;i<N+1;i++)
	   	   {
	   		  C(i,M+1)=0;
 	   	   }
   }
   else
   {
	   for(int j=0;j<M+2;j++)
	   	   {
		   	   double s = d_s*j;
	   		   if(s-K>0)
	   			   C(N,j)=s-K;
	   		   else
	   			   C(N,j)=0;
	   	   }
	   	   double d_t = T/N;
	   	   for(int i=0;i<N+1;i++)
	   	   	   {
	   	   		  C(i,0)=0;
	   	   	   }
	   	   for(int i=0;i<N+1;i++)
	   	   	   {
	   	   		  C(i,M+1)=K*exp(-r*(i*d_t-T));
	    	   	   }
   }
   this->C=C;
}

/// \brief sovle the problem like Ax=b where A is a tridiagonal matrix
/// \param a first diagonal below  the main diagonal
/// \param b main diagonal
/// \param c the first diagonal above the main diagonal
/// \param d b
/// \param n the size
/// \note the result will be stocked in d
void EBS::solve(double* a, double* b, double* c, double* d, int n) {

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

/// \brief display a matrix
/// \param m ostream
/// \param out Matrix_Full
/// \return
/// \note something to note.
/// \warning Warning.
/// \see See-also

EBS::~EBS(){

}


