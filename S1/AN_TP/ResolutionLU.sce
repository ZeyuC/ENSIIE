exec('/home/ruitong.xu/anynum/tp1_1.sce')
exec('/home/ruitong.xu/anynum/tp1_1b.sce')

exec('/home/ruitong.xu/anynum/tp1_3a.sce')
function x=ResolutionLU(A,b)
    [L,U] = FactLU(A)
    y = solinf(L,b)
    x = solsup(U,y)
endfunction
