data(iris)
X <- iris[,1:4]
cluster <- iris$Species
#Compute the matrix C=(cik) where cik=1 if i belongs to ck, 0 otherwise
C <- matrix(0,150,3)
for ( i in seq(1,150))
{
  if(cluster[i]=="setosa")
  {
    C[i,1] = 1
  }
  if(cluster[i]=="versicolor")
  {
    C[i,2] = 1
  }
  if(cluster[i]=="virginica")
  {
    C[i,3] = 1
  }
}
C

#En version plus rapide
library(nnet)
C <- class.ind(iris$Species)
C

#Compute M the matrix of gravity center
X <- as.matrix(X)
M <- solve((t(C)%*%C))%*%t(C)%*%X
M

dX <- dist(X)
classif <- hclust(d = dX, method= "ward.D2")
plot(classif)
plot(rev(classif$height), type='l', main="hauteurs du dendrogramme d??croissantes", ylab="classif$height", xlab="", las=1)
points(1:length(classif$height), rev(classif$height), pch=20)
c <- (cbind(1:length(classif$height), rev(classif$height)))
c2 <- diff(c[,2])
res <- diff(c2)
res

P <- X
RSQ <- rep(0, nrow(P))
SQTot <- sum(scale(P,scale=FALSE)^2)
for (i in 1:nrow(P)){
  Cla <- as.factor(cutree(hclust(dX,"ward.D2"),i))
  RSQ[i] <- sum(t((t(sapply(1:ncol(P),function(i) tapply(P[,i],Cla,mean)))-apply(P,2,mean))^2)*as.vector(table(Cla)))/SQTot
}
plot(RSQ, type='l', xlab="nombre de classes")
points(1:length(RSQ),RSQ,pch=20)
abline(h=1)


library(MASS)
data(crabs)
#Represent all crabs with a color corresponding to the specy and a symbol to the sex
plot(crabs)
pairs(crabs, col=c("blue","orange")[crabs$sp], pch=c(20,21)[crabs$sex])
crabsquant <- crabs[,4:8]
pairs(crabsquant, col=c("blue","orange")[crabs$sp], pch=c(20,21)[crabs$sex])


#Use kmeans to find 4 classes in crabsquant
nbclasse = 4
KM <- kmeans(crabsquant,nbclasse)
#couleur <- c("blue", "red", "orange2", "pink")
plot(crabsquant, asp = 1, pch = 19, col = rainbow(nbclasse)[KM$cluster])
points(KM$centers, pch = 8, col = rainbow(nbclasse)[KM$cluster], cex = 2)
trueClasses <- paste(crabs$sex,crabs$sp,sep="-")
table(trueClasses, KM$cluster)
pairs(crabsquant, col=KM$cluster)
pairs(crabsquant, col=as.factor(trueClasses))


for (i in 1:100)
{
  KM <- kmeans(crabsquant,nbclasse)
  #couleur <- c("blue", "red", "orange2", "pink")
  plot(crabsquant, asp = 1, pch = 19, col = rainbow(nbclasse)[KM$cluster])
  points(KM$centers, pch = 8, col = rainbow(nbclasse)[KM$cluster], cex = 2)
}

Newcrabs <- crabsquant[,-3]/crabsquant$CL

nbclasse = 4
KM2 <- kmeans(Newcrabs,nbclasse)
#couleur <- c("blue", "red", "orange2", "pink")
plot(Newcrabs, asp = 1, pch = 19, col = rainbow(nbclasse)[KM2$cluster])
points(KM2$centers, pch = 8, col = rainbow(nbclasse)[KM2$cluster], cex = 2)
trueClasses2 <- paste(crabs$sex,crabs$sp,sep="-")
table(trueClasses2, KM2$cluster)
pairs(Newcrabs, col=KM2$cluster)
pairs(Newcrabs, col=as.factor(trueClasses2))


for (i in 1:100)
{
  KM2 <- kmeans(Newcrabs,nbclasse)
  #couleur <- c("blue", "red", "orange2", "pink")
  plot(Newcrabs, asp = 1, pch = 19, col = rainbow(nbclasse)[KM2$cluster])
  points(KM2$centers, pch = 8, col = rainbow(nbclasse)[KM2$cluster], cex = 2)
}


Vect <- rep(0,20)
for (i in 1:20)
{
  nbClasse = i
  KM <- kmeans(crabsquant,nbClasse)
  Vect[i] <- KM$withinss
}
plot(Vect)

hist(mapply(function(x){kmeans(crabsquant,4,nstart=1)$tot.withinss},1:1000))
hist(mapply(function(x){kmeans(Newcrabs,4,nstart=1)$tot.withinss},1:1000))



#graphe styl?? pour montrer les centres de classes
#structure
z <- kde2d(Newcrabs[,2], Newcrabs[,4])
contour(z)

#affiche les crabes male/femelle bleus/oranges
points(Newcrabs[,c(2,4)],col=c("blue","orange")[crabs$sp],pch=c(20,21)[crabs$sex])

#affiche les centres de classes
points(KM2$center[,c(2,4)], cex=3, pch=21, bg="red")


#Mixture Models

#Exercise 1

#a)
nks <- rmultinom(1,1000,prob=c(1/3,2/3))
means <-c(0,4)
sds <- c(1,1/2)
samples <- mapply(function(nk, mean, sd){rnorm(nk, mean, sd)}, nks, means,sds)
hist(x<-unlist(samples))


curve(dnorm(x,mean=4, sd=1/2),-2,7, col="blue")
curve(dnorm(x,mean=0,sd=1),-2,7, add=T, col="orange")


mixture <- function(x){1/3*dnorm(x,mean=0,sd=1)+2/3*dnorm(x,mean=4, sd=1/2)}

curve(mixture(x),-2,7, add=T, col="red")

 #b)
res.kmeans <- kmeans(unlist(samples),2,nstart=10)
plot(unlist(samples), asp = 1, pch = 19, col = rainbow(2)[res.kmeans$cluster], ylim=c(0,5))
list.of.cluster <- split(data.frame(x), res.kmeans$cluster)

#c)
by(x,res.kmeans$cluster,sd)


library(mclust)
M <- Mclust(x)
M$parameters
#initialisaion : hierarchie clustering et fait turner l'algo
summary(M)
plot(M)

ME <- Mclust(x, modelNames = "E")
plot(ME)
#Avec E on cherche des sigma egaux pour chaque gaussienne, or ici ce n'est pas ce qu'on cherche

#e)
#Le meilleur model est celui avec V


#Exercice 2

data <- faithful
Mdata <- Mclust(data)
summary(data)
Mdata$parameters
plot(Mdata)
summary(Mdata)
#La sortie nous dit que le meilleur modele est compos?? de 3 Gaussiennes ayant la m??me variance

#Mdata$z est la matrice des tik (=proba de l'??l??ment i d'appartenir ?? une classe k)

dData <- dist(data)
hData <- hclust(dData, method="ward.D")
plot(hData)
hData3 <- cutree(hData,3)
plot(hData3)
Mclust.cluster <- Mclust(data)$classification
table(hData3,Mclust.cluster)
