function [L,U]=FactLU(A)
    n = size(A,1)
    L = eye(n,n)
    U = zeros(n,n)
    for i=1:n-1
        for j = i:n
            s=0
            for k=1:i-1
                s=s+L(i,k)*U(k,j)
            end
            U(i,j) = A(i,j)-s
        end
        for j=i+1:n
            s=0
            for k=1:i-1
                s=s+L(j,k)*U(k,i)
            end 
            L(j,i) = 1/U(i,i) *(A(j,i)-s)
        end
         
    end
    
    s=0
    for k=1:n-1
        s=s+L(n,k)*U(k,n)
        
    end
    U(n,n)=A(n,n)-s
endfunction
