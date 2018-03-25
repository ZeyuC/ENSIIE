#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>
typedef struct 
{
	int** m; 
    int size;
} matrix;
/* @requires   size >= 0
   @assigns    *array_1 , **array_2
   @ensures    retourne un tableux 2D 
*/
matrix initi_array(int size)
{
	matrix M;
	int i,j;
	int **array = (int**)malloc(size*sizeof(int *));
	for(i=0;i<size;i++)
	{
		array[i]=(int*)malloc(size*sizeof(int));
	}
	for (i=0;i<size;i++){
   		for (j=0;j<size;j++)
   		{
   			array[i][j]=0;
   		}
    }
    M.m=array;
    M.size=size;
	return M;
}
/* @requires   size >= 0 M nol null
   @assigns    M
   @ensures    retourne un tableux 2D dont les élément sont donneé par tirage aléatoire
*/
matrix remplissage(matrix M,int size,int n)
{
   srand(time(NULL));  
   int i,j;

   for (i=0;i<size;i++){
   		for (j=0;j<size;j++)
   		{
   			M.m[i][j]=rand()%n;
   		}
    }

   return M;
}
/* @requires   m >= 0
   @assigns    *array_1 , **array_2
   @ensures   afficher un tableux 2D 
*/
void affichage(matrix M,int size)
{
	int i,j;
	for (i=0;i<size;i++){
		for (j=0;j<size;j++){
			printf("%d\t",M.m[i][j]);
		}
		printf("\n");
	}
}
/* @requires   size >= 0 M non null
   @assigns    M
   @ensures    afficher les address d'un tableux 2D 
*/
void affichage_address(matrix M,int size)
{
	int i,j;
	for (i=0;i<size;i++){
		for (j=0;j<size;j++){
			printf("%p ",&(M.m[i][j]));   /* %p pour les address*/
		}
		printf("\n");
	}

}
/* @requires   size >= 0 array_1 et array_2 nol null
   @assigns    array
   @ensures    retourne un martrix dont les élément donnée par les addition de matrix array_1 et matrix array_2
*/
matrix addition(matrix M_1,matrix M_2,int size)
{
    matrix M=initi_array(size);
    int i,j;
    for (i=0;i<size;i++){
   	    for (j=0;j<size;j++){
   		     M.m[i][j]=M_1.m[i][j]+M_2.m[i][j];
   	    }
    }
    return M;
}
/* @requires   size >= 0 M_1 et M_2 nol null
   @assigns    M
   @ensures    retourne un martrix dont les élément donnée par les mulitiplication de matrix array_1 et matrix array_2
*/
matrix multiplication(matrix M_1,matrix M_2,int size)
{
	int i,j,k;
	matrix M=initi_array(size);
	for ( i = 0; i < size; i++)
	{
		for ( j = 0; j < size; j++)
		{
			for ( k = 0; k < size; k++)
			{
				(M.m)[i][j]+=M_1.m[i][k]*M_2.m[k][j];
			}
		}
	}
    return M;
}
/* @requires   size >= 0 M non null
   @assigns    M
   @ensures    affiche la trace de M
*/
void trace_array(matrix M,int size)
{
	int i;
	printf("la trace de cette matrice est : \n");
	for (i = 0; i < size; i++)
	{
	  	printf("%d ",(M.m)[i][i]);
	}
	printf("\n");
}
/* @requires   size >= 0 M nol null
   @assigns    M
   @ensures    retourne une matrix transposéé de cette matrix
*/
matrix transposéé(matrix M,int size)
{
	int i,j;
	matrix tran_array=initi_array(size);
	for (i = 0; i < size; i++)
	{
		for (j = 0; j < size ; j++)
			(tran_array.m)[i][j]=(M.m)[j][i];
	}
	return tran_array;
}
/* @requires   size >= 0 M nol null
   @assigns    M
   @ensures    retourne une matrix dont les élément donneé par les élément du triangle de Pascal
*/
matrix Pascal_array(matrix M,int size)
{
	int i,j;
    for (i=0;i<size;i++){
     	for (j=0;j<size;j++)
   		{
   			if(j==0 || i==j)
   				(M.m)[i][j]=1;
   			else if (i < j)
   				(M.m)[i][j]=0;
   			if (i > 1 && j>0)
   				(M.m)[i][j]=(M.m)[i-1][j-1]+(M.m)[i-1][j];
   		}
    }
   	return M;
}
int main()
{
	int size;

	printf("%s\n","veuillez saisir la taille de matrix :");

	scanf("%d",&size);

	matrix array=initi_array(size);

	affichage(array,size);

    affichage_address(array,size);

	array=remplissage(array,size,100);
	affichage(array,size);

	matrix add_array=addition(array,array,size);
	affichage(add_array,size);

	matrix mul_array=multiplication(array,array,size);
	affichage(mul_array,size);

	trace_array(array,size);

	matrix tran_array=transposéé(array,size);
	affichage(tran_array,size);
    
    array=Pascal_array(array,size);
    affichage(array,size);

}