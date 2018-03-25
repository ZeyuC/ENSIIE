#include <stdio.h>
#include <stdlib.h>
#define MAX_SIZE 100

/* @requires   quelle condition sur les paremètres?
   @assigns    quelle variable modifiéés?
   @ensures    quel état final?
*/
typedef struct 
{
	int count;
	int value[MAX_SIZE];
}stack;

/* @requires   s is non null
   @assigns    *s
   @ensures    s d'initialisation 
*/
void init_stack(stack *s)
{
   s->count=0;
}
void push(stack *s, int v){
   if (s->count > MAX_SIZE) {
    printf("stack is full\n");
    exit (1);
  }
   s->value[s->count]=v;
   s->count++;
}
int is_empty(stack *s){
    if (s->count == 0) return 1;
    return 0;
}

void pop(stack *s){
   if (!is_empty(s))
   	(s->count)-- ;
   else printf("%s\n","The stack " );
}

void print_stack(stack *s)
{
     int i;
     for (i=s->count-1; i>=0; i--)
     	printf("%d ", s->value[i]);
     printf("\n");
}

void duplie_sommet(stack *s)
{
	s->value[s->count++]=s->value[s->count-1];
}

void duplie_sommet_1 (stack *s)
{
	s->value[s->count++]=s->value[s->count-1]-1;
}
void duplie_sommet_2 (stack *s)
{
	duplie_sommet_1(s);
    s->value[s->count-2]=s->value[s->count-2]*s->value[s->count-3];
}
int facatoriel (stack *s)
{
	int sub=1;
	int i;
	for (i=0; i<s->count; i++)
		sub*=s->value[i];
	return sub;
}
int facator (int i)
{
	int n;
	stack *sp=(stack*)malloc(sizeof(stack));
	init_stack(sp);
	push(sp,i);
	print_stack(sp);
	for(n=0; n < i-1; n++)
       {
            duplie_sommet_1(sp);
            print_stack(sp);
       }
     return facatoriel(sp);
}
/* @requires   i > 0
   @assigns    
   @ensures    return le factoriel de i
*/
int facator_1 (int i)
{
	int n;
	stack *sp=(stack*)malloc(sizeof(stack));
	init_stack(sp);
	push(sp,i);
	print_stack(sp);
	push(sp,i-1);
	print_stack(sp);
	for(n=0; n < i-2; n++)
       {
            duplie_sommet_2(sp);
            print_stack(sp);
       }
     return sp->value[i-2];
}
/* @requires   n1 et n2 non null
   @assigns    
   @ensures    return le max entre n1 et n2
*/
double  * max_double(double *n1, double *n2)
{
	if(*n1 > *n2 )
      return n1;
    else return n2;
}
void swap(int *a,int *b)
{
	int tmp;
	tmp = *a;
    *a = *b;
    *b = tmp;
}
void print_array(int array[], int size)
{
     int i;
     for (i=0; i<size; i++)
     	printf("%d ", array[i]);
     printf("\n");
}
/*@requires size >0 ,array non null
  @assigns  array[],b
  @ensures  les élément de ce tableaux sont triées
 */
void tri_selection(int array[], int size)
{
    
    int i,j;
    for (i=0;i<size-1;i++){
    	int flag=i;
    	for (j=i;j<size-1;j++){
    		if (array[flag] > array[j+1])
    		    flag=j+1;
    	}
    	swap(&array[i],&array[flag]);
    }
}

/* @requires   a et b et size non null
   @assigns    diff[MAX_SIZE]
   @ensures    return diff
*/
int * vexteur_diff(int a[],int b[],int size)
{
	
	static int diff[MAX_SIZE];
    int i;
    for (i=0;i<size;i++)
    	diff[i]=a[i]-b[i];
    return diff;

}
/* @requires   t et size non null
   @assigns    a[5]
   @ensures    return un tableaux trié sans modifier le taleaux d'origine
*/
int * tri_array (int t[],int size)
{    
  static int tri_a[5];

	for(int i=0;i<size;i++)
		tri_a[i]=t[i];	
	tri_selection(tri_a,size);
	return tri_a;
}
/* @requires   a et b et size_a,size_b non null
   @assigns    stack *e_c
   @ensures    return un stack e_c qui contient les élement commun dans a et b
*/
stack * element_commun(int a[],int size_a,int b[],int size_b)
{
	static stack *e_c;
    e_c=malloc(sizeof(stack));
	init_stack(e_c);
	tri_selection(a,size_a);       //on fait d'abord tri pour les deux tableux
	tri_selection(b,size_b);
	int i=0,j=0;
	while ( i< size_a && j < size_b)
		{
            if (a[i]<b[j])
            	i++;
            else if (a[i]>b[j])
            	j++;
            else {
            	e_c->value[e_c->count++]=a[i];
            	i++;
            	j++;
            }
		}
	
	tri_selection(e_c->value,e_c->count);
	printf("%d\n",e_c->count );
	return e_c;
}
int main()
{ 

	/*int a[8]={2,4,1,8,6,19,-4,6};
	int b[6]={6,4,2,5,6,2};
    
    stack *c = malloc(sizeof(stack));
	init_stack(c);
    c = element_commun(a,8,b,6);
    printf("%d\n",c->count);
    print_array( c->value,c->count);*/
	/*print_array(vexteur_diff(a,b,4),4);*/
    /*print_array(tri_array(t,5),5);*/
   /* int t[5]={2,4,1,8,-7};
    print_array(t,5);
    int * p = (int *)malloc(sizeof(int));
    p = tri_array(t,5);
    print_array(p,5);*/
  
     /*printf("%d\n",facator(10));
	double *d1=(double*)malloc(sizeof(double));
	double *d2=(double*)malloc(sizeof(double));
	 printf("%s\n","sairir deux double:");
     scanf("%lf %lf", &(*d1),&(*d2));
     printf("Le nombre max est : %lf\n",*max_double(d1,d2));*/
	/*stack *s;
	s=malloc(sizeof(stack));
	init_stack(s);
	printf("%d\n",is_empty(s));
	push(s,5);
	push(s,6);
	push(s,12);
	print_stack(s);
	pop(s);
	print_stack(s);
	printf("%d\n",s->count );
	duplie_sommet(s);
	print_stack(s);
	printf("%d\n",s->count );
	printf("%d\n",facatoriel(s));*/
    return 0;
}