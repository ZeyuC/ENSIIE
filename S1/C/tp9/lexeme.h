/* Les deux lignes suivantes sont la uniquement pour eviter 
   que lexeme.h soit inclus plusieurs fois. */
#ifndef _LEXEME_H
#define _LEXEME_H
typedef struct lexeme_base* lexeme;

lexeme chaine_vers_lexeme(char*);
int est_constante(lexeme);
int est_plus(lexeme);
int val_constante(lexeme);

#endif /* lexeme.h */
