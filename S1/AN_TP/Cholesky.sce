//fonction   : 'Cholesky'
//paremetre  : A une matrcie symétrique et définie positive 
//retour     ; L une matrcie TI  

function [L] = Cholesky(A)
     n=rank(A);
     L = zeros(n,n);
     somme = 0;
   
     for j = 1 : n
         for i = j : n
             somme = 0;
             for k = 1: j-1
                 somme=somme+ L(i,k)*L(j,k);
             end 
             if i>j
                 L(i,j)=(A(i,j)-somme)/L(j,j);
             else            
                 L(i,i)= sqrt(A(i,i)-somme);
             end
         end     
      end
endfunction
     
