//fonction   : 'solinf'
//paremetre  : L,une matrcie TL,b,un vecteur de reel
//retour     ; x un vecteur reel

function [x] = solinf(L,b)
    x = b;  
    n=length(b);
    for i=1:n
        somme=0;
        if i=1
            x(i)=b(i)/L(i,i);
        else 
            for j=1:i-1
                somme=somme+L(i,j)*x(j);
            end
            x(i)=(b(i)-somme)/L(i,i);
        end            
    end
endfunction


