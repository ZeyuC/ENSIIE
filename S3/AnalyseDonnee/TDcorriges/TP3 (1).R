X <- read.table(text=" math scie fran lati d-m
                jean 6.0 6.0 5.0 5.5 8.0
                aline 8.0 8.0 8.0 8.0 9.0
                annie 6.0 7.0 11.0 9.5 11.0
                monique 14.5 14.5 15.5 15.0 8.0
                didier 14.0 14.0 12.0 12.5 10.0
                andre 11.0 10.0 5.5 7.0 13.0
                pierre 5.5 7.0 14.0 11.5 10.0
                brigitte 13.0 12.5 8.5 9.5 12.0
                evelyne 9.0 9.5 12.5 12.0 18.0")


library(ade4)

acp <- dudi.pca(X,scannf=T)
VP <- eigen(as.matrix(t(acp$tab*acp$lw)) %*% as.matrix(acp$tab))
rbind(VP$values,acp$eig)
cbind(VP$vectors[,1],acp$c1[,1])
cbind(as.matrix(acp$tab)%*%as.matrix(VP$vectors[,1]),acp$li[,1])


library(wordcloud)
plot(0,0,type="n",xlim=c(-1.2, 1.2),ylim=c(-1.2,1.2), asp=1)
points(cos(seq(from=0,to=2*pi,length.out=100)),sin(seq(from=0,to=2*pi,length.out=100)),type='l')
points(acp$co[,1:2])
textplot(acp$co[,1],acp$co[,2],names(acp$tab),new=FALSE)
abline(v=0,h=0)
s.arrow(acp$co,add.plot=TRUE, label=NULL)

s.label(acp$li,xax=1,yax=2,clabel=1.5) #il y a des superpositions
title('Projections individus - axes 1&2')
textplot(acp$li[,1],acp$li[,2],rownames(X)) #vient du packages wordcloud et permet 
#qu'il n'y ait pas de superpositions


scatter(acp,posieig='bottomright')
library(scatterplot3d)
scatterplot3d(acp, y=NULL, z=NULL)
par(lwd=2)


t(t(acp$c1^2)*acp$eig)
t(apply(t(t(acp$c1^2)*acp$eig),1,cumsum))
round(t(apply(t(t(acp$c1^2)*acp$eig),1,cumsum))*100,2)
round(100*acp$c1^2,2)




#Avec le truc du prof
res.pca <- prcomp(X)
plot(res.pca)
lambda <- res.pca$sdev^2
barplot(cumsum(lambda)/sum(lambda))
#Le premier axe explqiue 57%, le deuxieme 82% et le 3e 99.9%
str(res.pca)
par(mfrow=c(2,2))
biplot(res.pca, choice = c(1,2), title= "axe 1 & 2")
abline(h=0,v=0)
biplot(res.pca, choice=c(1,3),title="axe 1 & 3")
abline(h=0,v=0)
biplot(res.pca, choice=c(2,3),title="axe 2 & 3")
abline(h=0,v=0)
par(mfrow=c(1,1))


#Faire une acp ?? la main

X <- scale(X, center=T)
n <- nrow(X)
S <- (1/n)*t(X)%*%X
#Equivalent S <- var(X)*(n-1)/n
lambda <- eigen(S)$values
U <- eigen(S)$vectors
C <- X%*%U
par(mfrow=c(2,1))
plot(C[,1:2], type="none")
text(C[,1:2],rownames(X))

textplot(acp$li[,1],acp$li[,2],rownames(X))



library(MASS)
data(crabs)
crabsquant <- crabs[,4:8]

acpcrab <- dudi.pca(crabsquant, scannf=T)
par(mfrow=c(1,1))
textplot(acpcrab$li[,2], acpcrab$li[,3],rownames(crabsquant))
textplot(acpcrab$li[,3], acpcrab$li[,4],rownames(crabsquant))
textplot(acpcrab$li[,2], acpcrab$li[,4],rownames(crabsquant))


plot(0,0,type="n",xlim=c(-1.2, 1.2),ylim=c(-1.2,1.2), asp=1)
points(cos(seq(from=0,to=2*pi,length.out=100)),sin(seq(from=0,to=2*pi,length.out=100)),type='l')
points(acpcrab$co[,2:3])
textplot(acpcrab$co[,2],acpcrab$co[,3],names(acpcrab$tab),new=FALSE)
abline(v=0,h=0)
s.arrow(acpcrab$co[2:3],add.plot=TRUE, label=NULL)

library(FactoMineR)

X <- data.frame(crabsquant,sp.sex=interaction(crabs$sp,crabs$sex))
res <- PCA(X, quali.sup = 6)
plot.PCA(res, col.ind=c("blue","orange", "blue4", "orange4")[X$sp.sex],axes=c(2:3))
#sp.sex range les individus par ordre alphabetique avec d'abord l'espece apres le sex

res <-prcomp(X[,1:5], scale.=TRUE)
library(ggplot2)
ggplot(data=data.frame(res$x), aes(x=PC2, y=PC3))+
  geom_point(colour=as.numeric(interaction(crabs$sp,crabs$sex)))+
  geom_vline(xintercept = 0)+
  geom_hline(yintercept = 0)+
  geom_density2d()
