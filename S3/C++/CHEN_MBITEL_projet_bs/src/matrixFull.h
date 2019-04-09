#ifndef Matrix_Full_H
#define Matrix_Full_H
#include "BMatrix.h"
#include <iostream>
#include <vector>
namespace ensiie{
class Matrix_Full: public BMatrix
{
public:
	 double *data_;
	 Matrix_Full();
	 Matrix_Full(int nl,int cl);
	 Matrix_Full(const Matrix_Full & m);
	 ~Matrix_Full();

	 Matrix_Full & operator =(const Matrix_Full &m);
	 Matrix_Full & operator +=(const Matrix_Full &m);
	 Matrix_Full & operator -=(const Matrix_Full &m);

	 /*m(i,j) = 2 on veut modifier m(i,j) donc on renvois une ref*/
	 double & operator () (int l,int c);

	 /* int n = m(i,j) on veut pas modifier m(i,j) donc on ajout const*/
	 double  operator()(int l,int c) const;

	 int inc(int i, int N);

	 Matrix_Full transp() const;
};

 	//namespace ensiie
	std::ostream & operator << (std::ostream & out,const Matrix_Full& m);
	Matrix_Full operator + (const Matrix_Full& m1 ,const Matrix_Full& m2);
	Matrix_Full operator - (const Matrix_Full& m1 ,const Matrix_Full& m2);
	Matrix_Full operator * (const Matrix_Full& m1 ,const Matrix_Full& m2);
	Matrix_Full &operator * (Matrix_Full &M1, double  n);
}
#endif
