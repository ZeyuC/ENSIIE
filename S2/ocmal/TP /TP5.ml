(**TP5**)

(*EX 1.1*)

let rec partition p l = match l with
| [] -> ([],[])
| h::r-> let (l1,l2) = partition p r in if p h then (h::l1,l2) else (l1,h::l2);;
val partition : ('a -> bool) -> 'a list -> 'a list * 'a list = <fun>

let partition p l = fold_right (function x->function (l1,l2)-> 
	if p x then (x::l1,l2)
else (l1,x::l2) ) l ([],[]);;
val partition : ('a -> bool) -> 'a list -> 'a list * 'a list = <fun>

partition (function x-> x > 0) [1;-2;4;3;0];;

(*EX 1.2*)

let rec quicksort l = match l with
| [] -> []
| [h] -> l
| h::r-> let (l1,l2) = partition (function x-> x < h) r in
         (quicksort l1)@[h]@(quicksort l2);;
val quicksort : 'a list -> 'a list = <fun>

quicksort [4;2;8;3;1;8;0];;

(*EX 1.3*)

let rec quicksort comp l = match l with
| [] -> []
| [h]-> l
| h::r -> let (l1,l2) = partition (function y -> comp y h < 0) r in
(quicksort comp l1)@[h]@(quicksort comp l2);;
val quicksort : ('a -> 'a -> int) -> 'a list -> 'a list = <fun>

quicksort (function x->function y-> x-y)  [4;2;3;8;1;8;0];; 
quicksort (function x->function y-> y-x)  [4;2;3;8;1;8;0];; 

(*EX 1.4*)
quicksort (function (x1,y1) -> function (x2,y2)-> x1*y2 - y1*x2) 
[(2,3) ; (1,4) ; (5, 4)];;



(*EX 2.1*)
type forme =
  Point 
| Cercle of float
| Rectangle of float*float;;

let t = [Point;Cercle(2.0);Rectangle(2.0,1.0);Rectangle(2.0,3.0);Rectangle(3.0,3.0)];;
(*EX 2.2*)
let f1 = Cercle(3.4) and f2 = Rectangle(4.0,1.0);;

(*EX 2.3*)
type surface = S of float;;

let aire f = let pi =3.14 in match f with
| Point -> S 0.0
| Cercle r -> S (pi *. r *. r)
| Rectangle (a,b) -> S (a *. b ) ;;

let somme_surf (S f1) (S f2) = S (f1 +. f2);;

let rec somme_aire l = match l with
| [] -> S 0.0
| f::r-> somme_surf (aire f) (somme_aire r);;

somme_aire t;;

let somme_aire_fold l = List.fold_right
 (function f -> function y-> somme_surf (aire f) y ) l (S 0.0);;

somme_aire_fold t;;

(*EX 2.4*)
let exsist_sq l = List.exists
(function f-> match f with
| Rectangle(a,b)-> a = b | _-> false) l ;;

val exsist_sq : forme list -> bool = <fun>

exsist_sq t;;

let rec exsist_sq l = match l with
| [] -> false
| h::r->match h with 
Rectangle(a,b) -> a = b || exsist_sq r
|_-> exsist_sq r;;


(*EX 2.5*)
type couleur = Rouge | Bleu | Jaune ;;
type forme_color = 
Point 
| Rectangle of float * float * couleur
| Cercle of float * couleur;;


(*EX 2.6*)
let rouges l = List.filter
(function f -> match f with
| Rectangle(_,_,Rouge) -> true
| Cercle(_,Rouge-> true
| _ -> false) l;;


(*EX3.1*)
type nb = E of int | F of float;;

(*EX3.2*)
let addition nb1 nb2 = match (nb1,nb2) with
| (E a,E b) -> E (a+b)
| (E a,F b) -> F ((float_of_int a) +. b)
| (F a,E b) -> F ((float_of_int b) +. a)
| (F a,F b) -> F (a +. b);;


let n = E 3 and m = E 4;;

(*EX3.3*)
let rec addlist l = match l with
| [] -> E 0
| n::r -> addition n (addlist r);;

(* ou avec fold_right*)
let addlist l = List.fold_right addition l (E 0);;
addlist [E 4 ; F 4.5 ; E (-1)];;

(*EX4.1*)
type ’a arbre_bin =
| Vide
| Noeud of 'a * 'a arbre_bin * 'a arbre_bin;;

let rec nbnoeuds a = match a with
| Vide -> 0
| Noeud(_,g,d) -> 1 + nbnoeuds(g) + nbnoeuds(d);; 
val nbnoeuds : 'a arb -> int = <fun>

let ex_arbre =Noeud(4,
Noeud(10, Noeud(5, Noeud(7,Vide,Vide),
Noeud(8,Vide,Vide)),
Noeud(30, Vide,
Noeud(40,Vide,Vide))),Noeud(7,Vide,Vide));;

(*EX4.2*)
let rec hauteur a = match a with
| Vide -> 0
| Noeud(_,g,d) -> max (1+(hauteur g)) (1+(hauteur d));;

(*EX4.3*)
let rec valpos a = match a with
| Vide -> true
| Noeud(r,g,d) -> (r >= 0) && (valpos g) && (valpos d);;

(*EX4.4*)

(*parcours infixe (GRD)中序遍历*)
let rec infixe a = match a with
| Vide -> []
| Noeud(r,g,d) -> (infixe g)@[r]@(infixe d);;

(*un parcours prefixe (RGD)前序遍历*)
let rec prefixe a = match a with
| Vide -> []
| Noeud(r,g,d) -> [r]@(prefixe g)@(prefixe d);;

(*un parcours postfixe (GDR)后序遍历*)
let rec postfixe a = match a with
| Vide -> []
| Noeud(r,g,d) -> (postfixe g)@(postfixe d)@[r];;

(*EX5.1*)
type couleur = Trefle | Pique | Coeur | Carreau;;
type carte_ordi = | Roi of couleur | Dame of couleur | Cavalier of couleur
| Valet of couleur | Chiffre of int*couleur;;
type carte = Excuse | Atout of int | Carte_couleur of carte_ordi;;

let nbAtouts m =
	List.fold_right
	(function c -> function y-> match c with
	| Atout _ -> y+1
	| _ ->y) m 0;;

nbAtouts [Excuse; Atout 1; Atout 21; Carte_couleur (Roi Trefle)];;

(*EX5.2*)
let valeur_carte c = match c with
| Excuse | Atout 1 | Atout 21 | Carte_couleur (Roi _) -> 9
| Carte_couleur (Dame _) -> 7
| Carte_couleur (Cavalier _) -> 5
| Carte_couleur (Valet _) -> 3
| _ -> 1;;

let rec valeur_main m = match m with
[] -> 0
| c::r -> (valeur_carte c) + (valeur_main r);;

let valeur_main1 l =
List.fold_right (function c -> function y -> (valeur_carte c) + y) l 0;;


(*EX6.1*)
type categorie = A | B | C ;;
type personne = Etudiant of string * int 
| Admin of string * categorie 
| Enschercheur of string * string ;; 

let ecole = [Etudiant("A",1);Admin("a",A);Enschercheur("ENS","ccc")];;
(*EX6.2*)
let rec listing l = match l with
| [] -> []
|  h::r -> let l1 = listing r in match h with
| Etudiant (a,_) -> (a::l1)
| Admin (a,_) -> (a::l1)
| Enschercheur(a,_)->(a::l1);;

listing ecole;;

(*EX6.3*)
let rec separe l = match l with
| [] -> ([],[])
| p::r -> let (etu,autres) = separer r in match p with
| Etudiant(s,_) -> (s::etu,autres)
| Admin(s,_) -> (etu,s::autres)
| Enschercheur(s,_) -> (etu,s::autres);;

separe ecole;;