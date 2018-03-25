//fonction   : 'Test_time'
//paremetre  : A une matrcie tridiagonal,n est la taille de A
//retour     ; 
function Test_time(n)
    A=zeros(n,n);
    b=ones(1);
    for i = 1 : n
        for j = 1 : n
            if i == j
                A(i,i) = 3;
            elseif abs(i-j) == 1
                A(i,j)=1;
            else 
                A(i,j)=0;
            end
        end
        b(i)
    end
    
    tic(); 
    Cholesky(A);
    ResolutionCholesky(A,b);
    t_clsky = toc();
    disp('t_clsky='),disp(t_clsky);
    
    tic(); 
    FactLU(A);
    ResolutionLU(A,b);
    t_LU = toc();
    disp('t_LU='),disp(t_LU);
    
endfunction


