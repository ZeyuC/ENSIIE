
#include "BMatrix.h"

namespace ensiie{
ensiie::BMatrix::BMatrix() {
	this->nl = 3;
	this->nc = 3;
}

ensiie::BMatrix::BMatrix(int nl, int nc) {
	this->nl = nl;
	this->nc = nc;
}

int ensiie::BMatrix::get_nl() const {
	return this->nl;
}

int ensiie::BMatrix::get_nc() const {
	return this->nc;
}

bool ensiie::BMatrix::same_size(const BMatrix& m) const {
	/*first way to do this question*/
	return (this->nl==m.nl && this->nc==m.nc);
}

ensiie::BMatrix::~BMatrix() {
}

}
