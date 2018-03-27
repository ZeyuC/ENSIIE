(**TP1**)

(*EX 1.1*)
(** est_pair
@param x
@return : le resultat est true si x est pair, false sinon
*)
(*let test_pair e = if e mod 2 = 0 then true else false;;*)
let est_pair e = e mod 2 =0;; 

(*EX 1.2*)

(** signe:
@parma : x
@return : -1 si x est negatif, 0 si x=0 et 1 si x est positif.
*)
(*pense à ajouter les parentèse pour le numéro négative*)
let signe x = if x < 0 then (-1)  else if x > 0 then 1 else 0;;(*pense à ;;*)

(*EX 1.3*)
(** sphere
@param r le rayon de la sphere
@pre r positif ou nul
@return volume de la sphere de rayon r
*)
let sphere r = let pi = 3.14 and cube x = x *. x *. x in
4. /. 3. *. pi *. (cube r);;

(*EX 1.4*)
(** max3 :
@param a b c les 3 entiers
@result : le plus grand des 3 entiers a, b et c
*)

let max3 a b c = if a >= b then if a >= c then a else c
                 else if b >= c then b else c;;

(*EX 1.5*)
(*pense à ^ sert à concatener les deux string*)
let encader s = let cadre = "!!" in cadre ^ s ^ cadre ;;

let encader2 s cadre = cadre ^ s ^ cadre ;;

(*EX 1.6*)
(** solutions
@param a b c coefficients d’une \’equation du second degr\’e
@pre a<>0 (sinon ce n’est pas du second degr\’e)
@return nombre de solutions r\’eelles
@raises Failure "solutions : premier degre" si a=0
*)
(*!!attention manipulation pour les float est *. /. -. +.*)
let solutions a b c =
	if a = 0.0 then failwith "solutions : premier degre"
else let delta = b *. b -. 4.0 *. a *. c in 
     if delta < 0.0 then 0 else if delta = 0.0 then 1 else 2;;

(*EX 2.1*)
let rec u n = if n = 0 then 1. else sqrt(u (n-1) +. 2);;

(*EX 2.2*)
let rec somme n = if n =0 then 0 else n + (somme (n-1));;

(*EX 2.3*)
let rec carre n = if n =0 then 0 else (n*n) + (carre (n-1));;

(*EX 2.4*)
let rec dicho a n =
	if n =0 then 1
else let y = dicho (a*a) (n/2) in
if n mod 2 = 0 then y else y * a;;

(*EX 2.5*)
let rec dicho a n =
	if n = 0 then 1
else let y = dicho a (n/2) in
if n mod 2 = 0 then y*y else y *y*a;;

(*EX 3.1*)
let rec nb l = match l with 
    [] -> 0
   |_::r -> 1+ (nb r);;

(*EX 3.2*)
let rec pairs l = match l with
| [] -> 0
| x::r -> if x mod 2 = 0 then 1 + (pairs r)
else pairs r;;

(*EX 3.3*)
let rec somme l = match l with
| [] -> 0.
| x::r -> x +. (somme r);;

(*EX 3.4*)
(*remember there is min max for ocmal*)
let rec minl l = match l with
| [] -> failwith "liste vide"
| x::r -> min x (minl r);;

(*EX 4.1*)
(** inverse_ratio
@param r
@result si r = (p,q) alors le résulat esr (q,p)
@raises : Failure "rationel nul" si r a un d\’enominateur nul
*)

let inverse_ratio r = match r with
    (p,q) -> if p = 0 then failwith "rationel nul" else (q,p);;

(** Interface inverse_ratio
@param r1, r2 2 rationnels supposés valides
@result la somme des 2 rationnels R1 ET R2
*)

let plus_ration r1 r2 = match r1 with
| (p1,q1) -> match r2 with
| (p2,q2) -> (p1*q2 + p2*q1, q1*q2);;




