//fonction   : 'ResolutionGauss'
//paremetre  : A une matrcie ,b,un vecteur de reel
//retour     ; x un vecteur de reel

function [x]=ResolutionGauss(A,b)
         [At,bt]=trigGauss(A,b);
         x=solsup(At,bt);
endfunction
