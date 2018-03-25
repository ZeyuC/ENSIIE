//fonction   : 'ResolutionCholesky'
//paremetre  : A une matrcie symétrique et définie positive,b est un vecteur
//retour     ; x est un vecteur

function [x] = ResolutionCholesky(A,b)
    n=rank(A);
    L=Cholesky(A);
    U=L';
    y = solinf(L,b);
    
    x = solsup(U,y);

endfunction  
