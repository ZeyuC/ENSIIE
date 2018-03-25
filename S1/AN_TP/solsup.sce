//fonction   : 'solsup'
//paremetre  : L,une matrcie TL,b,un vecteur de reel
//retour     ; x un vecteur reel

function [x] = solsup(U,b)
    x = b;  
    n=length(b);
    for i = n:-1:1
        somme=0;
        if i=n
            x(i)=b(i)/U(i,i);
        else 
            for j=i+1:n
                somme=somme+U(i,j)*x(j);
            end
            x(i)=(b(i)-somme)/U(i,i);
        end            
    end
endfunction

