//fonction   : 'triGauss'
//paremetre  : A une matrcie ,b,un vecteur de reel
//retour     ; A un vecteur TS, b un vecteur de reel

function [At,bt]=trigGauss(A,b)
    n = length(b);
    for k = 1:n-1
        if A(k,k)=0
            printf("erreur");
            return 1;
        else
            for i = k+1:n
                c=A(i,k)/A(k,k);
                b(i)=b(i)-c*b(k);
                A(i,k)=0;
                for j=k+1:n
                    A(i,j)=A(i,j)-c*A(k,j)
                end
            end
        end
    end
At=A; 
bt=b;
endfunction
