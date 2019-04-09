/*
 * matrixFull.cpp
 *
 *  Created on: 2018年11月9日
 *      Author: chenzeyu
 */
#include "matrixFull.h"
#include <iostream>
#include <iomanip>
namespace ensiie {
Matrix_Full::Matrix_Full() {
	// TODO Auto-generated constructor stub
	//data_ =(double*)malloc(sizeof(double)*1000);
	data_ = new double[1002*1002];
	for(int i=0;i<1002*1002;i++)
	{
		data_[i]=0;
	}
}
Matrix_Full::Matrix_Full(int nl,int nc) : BMatrix(nl, nc){
	// TODO Auto-generated constructor stub
	data_ = new double[nl*nc];
	for(int i=0;i<nl*nc;i++)
		{
			data_[i]=0;
		}
}
Matrix_Full::Matrix_Full(const Matrix_Full & m) :BMatrix(m.get_nl(), m.get_nc())
{
	data_ = new double[nl*nc];
	for (int i = 0; i < nl; i++) {
			for (int j = 0; j < nc; j++) {
				data_[i*nl+j] = m(i, j);
			}
		}
}

Matrix_Full::~Matrix_Full() {
	// TODO Auto-generated destructor stub
}

Matrix_Full& Matrix_Full::operator =(const Matrix_Full& m) {
	this->data_ = m.data_;
	this->nl = m.nl;
	this->nc = m.nc;
	return *this;
}

Matrix_Full& Matrix_Full::operator +=(const Matrix_Full& m) {
	if(!same_size(m))
	{
		throw "Size different";
	}
	for(int i=0;i<nl*nc;i++)
		{
			this->data_[i] = this->data_[i] +m.data_[i];
		}
	return *this;
}

Matrix_Full& Matrix_Full::operator -=(const Matrix_Full& m) {
	//if(this->nl != m.nl || this->nc != m.nc)
	if(!same_size(m))
		{
			throw "Size different";
		}
	int width = get_nc();
	int hight = get_nl();
	for(int i=0;i<width*hight;i++)
		{
			this->data_[i] = this->data_[i] - m.data_[i];
		}
	return *this;
}

double& Matrix_Full::operator ()(int l, int c) {
	int width = get_nc();
	int hight = get_nl();
	if(l>=hight || c>=width)
	{
		throw "bound out of size";
	}
	return this->data_[l*width+c];
}

double Matrix_Full::operator ()(int l, int c) const {
	int width = get_nc();
	int hight = get_nl();
	if(l>=hight || c>=width)
		{
			throw "bound out of size";
		}
	return this->data_[l*width+c];
}

Matrix_Full Matrix_Full::transp() const {
	Matrix_Full mNew(*this);
	int width = get_nc();
	int hight = get_nl();

	for(int i=0;i<hight;i++)
	{
		for(int j=0;j<width;j++)
		{
			mNew.data_[j*hight+i]=this->data_[i*width+j];
		}
	}
	mNew.nc = hight;
	mNew.nl = width;

	return mNew;
}



/// \brief display a matrix
/// \param ostream out
/// \param  Matrix_Full m
/// \return a ostream
std::ostream& operator << (std::ostream& out, const Matrix_Full& m) {
	int width = m.get_nc();
	int hight = m.get_nl();

		for(int i=0;i<hight;i++)
		{
			for(int j=0;j<width;j++)
			{
				std::cout.precision(4);
				std::cout.width(10);
				out << m.data_[i*width+j] ;
			}
			out << "\n" ;
		}
    return out;
}


/// \brief add two matrix
/// \param m1
/// \param m2
/// \return a matrix
Matrix_Full operator +(const Matrix_Full& m1, const Matrix_Full& m2) {
	if(!m1.same_size(m2))
		{
		throw "Size different";
		}
	Matrix_Full m(m1);
	std::cout << m;
	int width = m1.get_nc();
	int hight = m1.get_nl();
	for(int i = 0;i<width*hight;i++)
	{
		m.data_[i]= m1.data_[i] + m2.data_[i];
	}

	return m;
}

Matrix_Full operator -(const Matrix_Full& m1, const Matrix_Full& m2) {
	if(!m1.same_size(m2))
		{
		throw "Size different";
		}
	Matrix_Full m(m1);
	int width = m1.get_nc();
	int hight = m1.get_nl();
	for(int i = 0;i<width*hight;i++)
		{
			m.data_[i]= m1.data_[i] - m2.data_[i];
		}

	return m;
}


/// \brief the product of two  matrix
/// \param  Matrix_Full m1
/// \param  Matrix_Full m2
/// \return a  Matrix_Full
Matrix_Full operator *(const Matrix_Full& m1, const Matrix_Full & m2)
{

	int nl1 = m1.get_nl();
	int nl2 = m2.get_nl();
	int nc1 = m1.get_nc();
	int nc2 = m2.get_nc();
	Matrix_Full m(nl1,nc2);
	if (nc1 != nl2) { // nc1 == nl2
		    std::cout << "Matrix can not be multiplied";
			throw "Matrix can not be multiplied";
	}
	for (int i = 0; i < nl1; i++) {
		for (int j = 0; j < nc2; j++) {
			for (int k = 0; k < nc1; k++) {
				m(i, j) += m1(i, k) * m2(k, j);
			}
		}
	}

	return m;
}

Matrix_Full& operator *(Matrix_Full &M1, double  n) {
	int nl = M1.get_nl();
	int nc = M1.get_nc();
	for (int i = 0; i < nl; i++) {
		for (int j = 0; j < nc; j++) {
			//M1.data_[i*nc+j] = M1.data_[i*nc+j]*n;
			M1(i,j)*=n;
		}
	}
	return M1;
}





/// \brief inverse matrix src
/// \param src the matrix we want to inverse
/// \param dst the result matrix
/// \note something to note.



} /* namespace ensiie */
