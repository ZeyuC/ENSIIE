#include "EBS.h"
#include "EComplet.h"
#include "matrixFull.h"
#include "EReduit.h"
#include <iostream>
#include <cmath>
#include  <fstream>
void Complet_put()
{
    EComplet m_put;
    m_put.initial_C(true,100.0);   
    ensiie::Matrix_Full* Cn = m_put.complet(m_put);
    std::ofstream OutFile1("result/Complet_put.txt");
    OutFile1 << Cn[0];
    OutFile1.close();
    
}

void Complet_call()
{
    EComplet m_call;
    m_call.initial_C(false,100.0);
    ensiie::Matrix_Full* Cn= m_call.complet(m_call);
    std::ofstream OutFile2("result/Complet_call.txt");
    OutFile2 << Cn[0];
    OutFile2.close();
}

void Reduit_put()
{
    /*The test for EDP reduit*/
    EReduit mr_put;   
    mr_put.initial_C(true,100.0);
    mr_put.initial_U(mr_put);
    
    ensiie::Matrix_Full* Cn = mr_put.reduit(mr_put);
    std::ofstream OutFile3("result/Reduit_put.txt");
    OutFile3 << Cn[0];
    OutFile3.close();
}

void Reduit_call()
{
    EReduit mr_call;
    mr_call.initial_C(false,100.0);
    mr_call.initial_U(mr_call);
    ensiie::Matrix_Full* Cn = mr_call.reduit(mr_call);
    std::ofstream OutFile4("result/Reduit_call.txt");
    OutFile4 << Cn[0];
    OutFile4.close();
}
int main()
{
    /*The test for EDP complet*/
    std::cout << "running , please be patient ,we need about 1mins"<<"\n";

    Complet_put();
    Reduit_put();
    Complet_call();
    Reduit_call();
    
    
    std::cout << "success,all the result reserved in result/*.txt"<<"\n";
    return 0;
}
