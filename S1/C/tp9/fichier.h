/* Les deux lignes suivantes sont la uniquement pour eviter 
   que fichier.h soit inclus plusieurs fois. */
#ifndef _FICHIER_H
#define _FICHIER_H

typedef struct flux_base* flux;

flux ouvre(char*);
void ferme(flux);
char* lit_un_mot(flux);
int fin_de_fichier(flux);

#endif /* fichier.h */
