#Ex1

Psup120 <- 1-pnorm(120,100,15)
Pinf100 <- pnorm(100,100,15)
IQ_sup_120 <- function(x){
  ifelse(x >120,dnorm(x, mean=100, sd=15),NA)
}
library(ggplot2)
ggplot(data.frame(x=c(20,180)),aes(x))+
  stat_function(fun=dnorm,args=list(mean=100,sd=15))+
  stat_function(fun=IQ_sup_120,geom="area", fill="coral")



#Ex2

#cf la correction car c'est de l'??crit 
#cf photo 1/10

#Ex3

n = 10
ns = 1000
estimation <- matrix(0,ns,2)
for (i in 1:ns){
  x <- rnorm(10)
  estimation[i,1] <- mean((x-mean(x))^2)
  estimation[i,2] <- estimation[i,1]*n/(n-1)
}
hist(estimation[,1], breaks=50)
abline(v=1)

colMeans(estimation)


#Ex4

library(MASS)
X <- mvrnorm(1000,c(0,0),Sigma = matrix(c(2,1,1,0.75),2,2))
plot(X)
mv2density <- function(x,y,mu= c(0,0), Sigma = matrix(c(2,1,1,0.75),2,2)){
  (2*pi)^(-1)*det(Sigma)^(-1/2)*exp(-1/2*(rbind(c(x,y)-mu)%*%solve(Sigma)%*%(cbind(c(x,y)-mu))))
}
plot(X(c(1,2),c(2,3),c(0,0), Sigma = matrix(c(2,1,1,0.75),2,2)))
