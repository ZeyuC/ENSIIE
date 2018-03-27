(**TP3**)

(*EX 1.1*)
let rec repeat n x = if n <= 0 then [] else x::(repeat (n-1) x);;

val repeat : int -> 'a -> 'a list = <fun>

(*EX 1.2*)
(** nb_occ
@param x, un élement, l une liste
@return le nombre d’occurrences de x dans la liste l
*)
let rec nb_occ x l = match l with
| [] -> 0
| h::r -> if h = x then 1+ (nb_occ x r)
else nb_occ x r;;

val nb_occ : 'a -> 'a list -> int = <fun> (*les deux premier sont 'a*)

(*EX 1.3*)
(** last
@param l, liste
@return le dernier élément de l si l n’est pas vide
@raises Failure "last : liste vide" si l est vide
*)
let rec last l = match l with
| [] -> failwith "liste vide"
| h::r -> if r = [] then h else (last r);;

let rec last l = match l with
| [] -> failwith "liste vide"
| [e]-> e 
| h::r ->last r;;

(*EX 1.4*)
let rec is_increasing l = match l with
| a::b::c -> a <= b && (is_increasing (b::r))
| _-> true;;
val is_increasing : 'a list -> bool = <fun>


(*EX 2.5*)
let rec som_nb l = match l with
| [] -> (0.,0)
| h::r -> let (m,n) = som_nb r in (h +.m,n+1)  ;; 

let moyenne l = if l =[] then failwith "vide"
else let (m,n) = som_nb l in (m /. (float_of_int n));;

(*EX 3.1*)

(** inserer
@param e , l (l est une liste triéee dans l’ordre croissant)
@result la liste triee dans l’ordre croissant composée des élements de l et de e
*)

let rec inserer e l = match l with
| [] -> [e]
| a::r -> if e < a then e::l else (*!!*)a::(inserer e r);;



