#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

typedef struct node* list;         
struct node                            /*ne peux pas ecrire comme struct node* */
{
	double value;
	list next;
};

/* @requires   initialisation 
   @assigns    
   @ensures    retourne un NULL list
*/
list inti_list()

{
      return NULL;
}

/* @requires   l non null
   @assigns    l
   @ensures    retourne un NULL list
*/
int if_empty(list l)
{
	if (l==NULL)
	return 0;
	else return 1; 
}
/* @requires   l non null
   @assigns    currentList
   @ensures    retourne le longeur de list L
   */
int longeur_list (list l)
{
  int n=0;
  list currentList = l;
  while (if_empty(currentList))
  { 
    currentList=currentList->next;
    n++;
  }
  return n;
}
/* @requires   l non null ,value non null
   @assigns    l
   @ensures    retourne le nouveux list L
   */
list insert_head(list l, double value)
{
  list newnode = (list)malloc(sizeof(struct node));

  newnode->next=l;
	newnode->value=value;
	l=newnode;
	return l;
}
/*list* pl 是指向list 的指针，而list 是指向结构体的指针*/  
void insert_Head(list* pl, double value) /*这里通过指针 pl 修改 l 的地址 */
{
	*pl=insert_head(*pl,value);
	/*printf("%f\n", (*ql)->value);*/  
} 
/* @requires   l non null
   @assigns    delet le head de l
   @ensures    retourne un l list 
*/
list delet_head(list l)               
{
     if (if_empty(l)){
     	list p =l;
      l=p->next;
      free(p);
  }
     else {
     	printf("%s\n","list is null");
     	return l;
     }
     return l;
}
void delet_Head (list *pl)
{
     *pl=delet_head(*pl);
}
/* @requires   l non null
   @assigns    afficher tous les éléments de l 
   @ensures    
*/
void print_list(list l)
{
	list currentList = l;
	while (if_empty(currentList))
	{
		printf("%lf\n", currentList->value);	
    currentList=currentList->next;
	}
}

/* @requires   l non null
   @assigns    currentList
   @ensures    retourne la somme des noeudes de list l
   */
double somme_noeude (list l)
{
	double somme=0;
	list currentList = l;
	while (if_empty(currentList))
	{
        somme+=currentList->value;
        currentList=currentList->next;
	}
	return somme;
}
/* @requires   l non null
   @assigns    currentList
   @ensures    retourne un list dont les élement sont dans l'ordre invese que ceux de list l 
   */
list inverse_list (list l)
{
    list currentList = inti_list();
    int i =0; 
    int size = longeur_list(l);
    for (i=0;i<size;i++)
    {
    	insert_Head(&currentList,l->value); 	
      l=l->next;
    }
    l=currentList;
    return l;
}
/* @requires   l non null
   @assigns    currentList
   @ensures    insert un élément en fonction de son value à la liste l 
   */
list insert_l(list l, double value) 
{
    list newnode = (list)malloc(sizeof(struct node));
    list t=l;
    newnode->value=value;
    int flag=0;
    while(if_empty(t))
    {
        if( value < t->value)
        {
           break; 
        }
        else
        {
            t=t->next;
            flag++;
        }
    }   
    list pre = (list)malloc(sizeof(struct node));
    pre->next=l;
    pre->value=-1;
    int i;
    for(i=0;i<flag;i++)
    {
        pre=pre->next;
    }
    if(flag==1)
    {
     newnode->next=l->next;
     l->next=newnode;
    return l;
    }
    else if(flag>1)
    {
     
      newnode->next=pre->next;
      pre->next=newnode;
      return l;
    }
    else
     { 
      newnode->next=l;
      return newnode;
     }
   
}
 
void insert_L(list* pl, double value) 
{
    *pl=insert_l(*pl,value);
  
} 
/* @requires   l non null
   @assigns    currentList
   @ensures    retourne un list élement qui égale à un e donné 10-20 près 
   */
int recherche_e(list l, double e)
{
     int flag=0;
     int index =1;
     while(if_empty(l))
     {
         if (e==l->value)
            {
         return index;
         flag=1;
         } 
         else
         { 
          index++;
          l=l->next;
         }
     }
    return 0;
}
/* @requires   l non null
   @assigns    currentList
   @ensures    retirer les élement de list l qui égale à un e donné 10-20 près 
   */
list retirer_e(list l, double e)
{
     int i=0;
     int index = recherche_e(l,e);
     list n = l;
     for ( i=0;i<index-2;i++)
     {
          n=n->next;
     }
     list q =n->next;
     n->next = q->next;
     free(q);
     n=n->next;
     return n;
 }
/*la type abstrait des piles à base de listes*/


/*@requires size >0 ,array non null
  @assigns  array[],b
  @ensures  les élément de ce tableaux sont triées
 */
void tri_insertion(double array[], int size)
{
    
    int i,j;
    double tmp;
    for (i=1;i<size;i++){
    	tmp = array[i];
    	for (j=i;j>0 && array[j-1]>tmp;j--){
    		array[j]=array[j-1];
    	}
        array[j]=tmp;
    }
}
/*@requires l non null
  @assigns  a[],currentList,newlist
  @ensures  retourn une liste comportant les mémes élément triéé qu'une liste l 
 */
list tri_newlist(list l)
{
	list currentList = l;

	int size = longeur_list(l);

	double a[size];
	int i;
	for (i=0;i<size;i++)
		{     
		a[i]=currentList->value;
    currentList=currentList->next;		
		}

	tri_insertion(a,size);
    
    list newlist = inti_list();
	for (i=0;i<size;i++)
		{
		insert_Head(&newlist,a[i]);	
		}
	newlist=inverse_list(newlist);
	return newlist;

}
/*@requires l et nombre non null
  @assigns  a[],currentList,newlist,trier les premier nombre d'élément de l
  @ensures  retourn une liste comportant les mémes élément triéé qu'une liste l 
 */
list tri_list(list l , int nombre)
{
	list currentList = l;
    int size = longeur_list(l);
    printf("%d\n",size );
    if (nombre > size)
	      nombre=size;

    double a[size];
    int i;
	for ( i=0;i<size;i++)
		{     
		a[i]=currentList->value;	
    currentList=currentList->next;
		}
    
	tri_insertion(a,nombre);
    
   
  list newlist = inti_list();
	for ( i=0;i<size;i++)
		{
		insert_Head(&newlist,a[i]);	
		}
	
	newlist=inverse_list(newlist);
	return newlist;

}

typedef struct Stack* stack; 
struct Stack
{
       list sommet;
};
/*@requires ...
  @assigns  ..
  @ensures  retourn 0 s null, retourn 1 s non null 
 */
int ifempty(stack s)
{
        return if_empty(s->sommet);
}
/*@requires s non null
  @assigns  suprime le sommet de s
  @ensures  ...
 */
void pop(stack s)
{
        delet_Head(&s->sommet);
}
/*@requires l et value non null
  @assigns  ajoute un nombre à s
  @ensures  ..;
 */
void push(stack s,double value)
{
	insert_Head(&s->sommet,value);
}

/*@requires s non null
  @assigns  
  @ensures  affiche tout les element de s
 */
void print_stack(stack s)
{
	print_list(s->sommet);
}
/*@requires 
  @assigns  stack s initialisation
  @ensures  retourn un stack
 */
stack inti_stack()
{
   stack s= (stack)malloc(sizeof(struct Stack));
   s->sommet=inti_list();
   return s;
}

int main()
{    

   
	/*stack s =inti_stack();
	push(s,2.1);
	push(s,3.2);
	push(s,9.2);
	print_stack(s);
	pop(s);
	print_stack(s);
        printf("%d\n",ifempty(s));*/
	 list l =inti_list();
   

      insert_L(&l,2.1);
	   insert_L(&l,3.2);


      insert_L(&l,4.9);

      insert_L(&l,1.5);
      insert_L(&l,9.6);
      print_list(l);
      
      
       printf("aa %d\n",recherche_e(l,4.9));
     /* l=tri_list(l,5);
      print_list(l);   */
 
    
      delet_Head(&l);
     
      print_list(l);
     
     // printf("La somme %f\n",somme_noeude(l));
     
     
      //printf("%d\n",recherche_e(l,4.9));
      //retirer_e(l,4.9);


 
    return 0;
}
