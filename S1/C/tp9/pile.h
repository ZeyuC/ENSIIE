#ifndef _PILE_H
#define _PILE_H

#define MAX_SIZE 1000000
typedef struct pile_base
{
	int count;
	int value[MAX_SIZE];
}pile;

pile *pile_vide();
int  est_vide(pile*);
void push(pile*,int);
int  pop(pile*);

typedef struct node *list;

struct node
{
	int value;
	list next;
};

typedef struct Stack
{
	list sommet;
}stack;
stack *inti_stack();
int  ifempty(stack*);
void push_s(stack*,int);
int  pop_s(stack*);



#endif
