#include "pile.h"

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

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
		printf("%d\n", currentList->value);	
    currentList=currentList->next;
	}
}

/*@requires ...
  @assigns  ..
  @ensures  retourn 0 s null, retourn 1 s non null 
 */
int ifempty(stack *s)
{
        return if_empty(s->sommet);
}
/*@requires s non null
  @assigns  suprime le sommet de s
  @ensures  ...
 */
int pop_s(stack *s)
{
     if (ifempty(s)){
     int i = s->sommet->value;
    
     delet_Head(&s->sommet);
     return i;
     }
     else 
   	{
   		printf("The stack is empty");
        return 1;
    }

}
/*@requires l et value non null
  @assigns  ajoute un nombre à s
  @ensures  ..;
 */
void push_s(stack *s,int  value)
{
	insert_Head(&(s->sommet),value);
}

/*@requires s non null
  @assigns  
  @ensures  affiche tout les element de s
 */
void print_stack(stack *s)
{
	print_list(s->sommet);
}
/*@requires 
  @assigns  stack s initialisation
  @ensures  retourn un stack
 */
stack* inti_stack()
{
   stack* s= malloc(sizeof(struct Stack));
   s->sommet=inti_list();
   return s;
}
