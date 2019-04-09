#include <iostream>
#include "EComplet.h"
#include "matrixFull.h"
#include <cmath>
/// \brief constructor
EComplet::EComplet()
{
	T = 1.0;
	K = 100.0;
	L = 300;
    r = 0.1;
	sigma = 0.1;
	payoff = true;
	
	initial_B();
    initial_A();
}

/// \brief initialize A
void EComplet::initial_A()
{
	ensiie::Matrix_Full A(M+2,M+2);
	double d_t = T/N;
	A(0,0)= exp(-r*d_t);
	for(int i=1;i<M+1;i++)
	{
		/*A(i,i-1)=(-1/d_t)-(sigma*sigma*i*i/2)-r;
		A(i,i)=(sigma*sigma*i*i/4)+(r*i/4);
		A(i,i+1)=(sigma*sigma*i*i/4)-(r*i/4);*/

		A(i,i)=(-1/d_t)-(sigma*sigma*i*i/2)-r;
		A(i,i-1)=(sigma*sigma*i*i/4)-(r*i/4);
		A(i,i+1)=(sigma*sigma*i*i/4)+(r*i/4);
	}
	A(M+1,M+1)=1;
	this->A=A;
}

/// \brief initialize B
void EComplet::initial_B()
{
	ensiie::Matrix_Full B(M+2,M+2);
	double d_t = T/N;
	B(0,0)= 1;
	for(int i=1;i<M+1;i++)
		{
			B(i,i)=(-1/d_t)+(sigma*sigma*i*i/2);
			B(i,i-1)=-(sigma*sigma*i*i)/4+(r*i/4);
			B(i,i+1)=-(sigma*sigma*i*i/4)-(r*i/4);
		}
	B(M+1,M+1)=1;
	this->B=B;
}

/// \brief sovle the problem like Ax=b where A is a tridiagonal matrix
/// \param a first diagonal below  the main diagonal
/// \param b main diagonal
/// \param c the first diagonal above the main diagonal
/// \param d b
/// \param n the size
/// \note the result will be stocked in d
void EComplet::solve(double* a, double* b, double* c, double* d, int n) {

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
void EComplet::decompose(double *bot,double *mid,double *sup, EComplet &Mo)
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
/// \param m a Ecomplet objet
ensiie::Matrix_Full* EComplet::complet(EComplet &m)
{
	ensiie::Matrix_Full *Cn = new ensiie::Matrix_Full[m.N+1];
	for(int n = m.N; n>=0 ; n--)
	{
		Cn[n].data_ = new double[m.M+2];
		Cn[n].nc=1;
		Cn[n].nl=m.M+2;

		for(int i=0;i<m.M+2;i++)
			{
				Cn[n](i,0)=m.C(n,i);
			}
		//std::cout<<<<"\n";
	}

	double *sup = new double[m.M+2];
	double *bot = new double[m.M+2];
	double *mid = new double[m.M+2];
	int size = m.M+2;

	decompose(bot, mid, sup,m);

	/*for(int i=0;i<m.M+2;i++)
	{
		std::cout<<bot[i]<<"\n";
	*/
	for(int n = m.N; n>0 ; n--)
		{
		   //std::cout<<Cn[n]<<"\n";
		   ensiie::Matrix_Full res =m.B*Cn[n];
		   m.solve(bot, mid, sup,res.data_,size);
		   for(int i=1;i<m.M+1;i++)
		   	{
		   			Cn[n-1](i,0)=res.data_[i];
		   			m.C(n-1,i)=res.data_[i];
		   	}
		   //Cn[n-1]=res;
		}
	delete []sup;
	delete []bot;
	delete []mid;
    delete []Cn;
	return Cn;
}


EComplet::~EComplet()
{

}
