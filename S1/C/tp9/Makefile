CC = gcc -Wall -Wextra -ansi
OBJ= fichier.o lexeme.o pile.o pile_liste.o evaluation.o

all : prog
fichier.o : fichier.h
lexeme.o  : lexeme.h
evaluation: evaluation.h
pile_liste.o :pile.h
pile.o : pile.h
prog : $(OBJ)
	$(CC) -o $@ $^
