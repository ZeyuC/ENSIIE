#ifndef BMatrix_H
#define BMatrix_H
#include <cstdlib>

namespace ensiie{
class BMatrix
{
public:
	int nl;
	int nc;
	BMatrix();
	BMatrix(int nl,int nc);
	int get_nl() const;
	int get_nc() const;
	bool same_size(const BMatrix &) const;
	virtual double &operator()(int l,int c)=0;
	virtual double operator()(int l,int c) const = 0;
	virtual ~BMatrix();
};

}
#endif
