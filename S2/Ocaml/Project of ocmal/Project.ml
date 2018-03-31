
(**EX1**)

(**---------------------------------Q1------------------------------------**)

type terme = 
  Var of string 
| Const of string 
| Unop of string * terme 
| Binop of string * terme * terme;;

let signature = [("a",0);("b",0);("g",1);("f",2)];;

(** fst
@param un couple (a,b)
@return : le premier element d'un couple
*)
let fst (a,b) = a;;

(** est_dedans
@param un list l , un string str
@return : true si on a resussi à trouvé str dans l false sinon
*)
let rec est_dedans l str = match l with
  [] -> false
| h::r -> if str = (fst h) then true
          else est_dedans r str;;

(** test_terme_s
@param un signature sign , un terme t
@return : true si t satisfait le signature false sinon
*)
let rec test_terme_s sign t = match t with
   |Var str -> true
   |Const str -> est_dedans sign str
   |Unop(str,t)-> if est_dedans sign str && tese_terme sign t then true else false
   |Binop(str,t1,t2)-> if (est_dedans sign str) && (tese_terme sign t1) && (tese_terme sign t2) 
                         then true else false;;
(** test_terme
@param    un terme t
@return : true si t est dans terme bien formé
*)                        
let test_terme t = test_terme_s signature t;; 

(**---------test------------**)
let terme1 = Binop("f", Binop("f", Var "x",Binop("f",Unop("g",Var "t"),Const "a")), Var "u");;                     
test_terme terme1;; (*--true--**)                        
let terme2 = Binop("t",Const "a",Var "e");; 
test_terme terme2;; (*--false--**)          


(**---------------------------------Q2------------------------------------**)

(** list_var
@param    un terme t
@return : une liste qui contient tous les variable de terme t
*)
let rec list_var t  = match t with
| Var str ->  [str] 
| Const str -> []
| Unop(str,t)-> list_var t 
| Binop(str,t1,t2)-> (list_var t1 )@(list_var t2 );;

(** nb_occ
@param    une liste t et un 'a s
@return : le nombre d'occurance de s dans l
*)
let rec nb_occ l s = List.fold_right(function x->function y-> if x =s then y+1 else y) l 0;;

(** if_doule
@param    une liste l
@return : true si le nombre de tous les element de l est 1 false sinon 
*)
let if_doule l  = let l1 = List.map (function x -> if nb_occ t x =1 then true else false) l 
in if nb_occ l1 false >=1 then false else true ;;

(** if_motif
@param  : une terme t
@return : true si t est motif false sinon
*)
let rec if_motif t = let l = list_var t in if_doule l;;  

(**---------test------------**)
let terme1 = Binop ("f",Var "x", Unop ("g", Var "x"));;
if_motif terme1;; (* bool = false*)
let terme2 = Binop ("f",Var "u", Unop ("g", Var "y"));;
if_motif terme2;; (* bool = true*)
let terme3 = Unop ("g",Var "x");;
if_motif terme3;; (* bool = true*)

(**---------------------------------Q3------------------------------------**)
(*compare directement m et t*)

let rec substitution m t  = match (m, t) with
  | (Var v1, str) -> [(v1, str)]
  | (Const s1, Const s2) ->if s1 = s2 then [] else failwith "Ne filtre pas" 
  | (Unop(s1, t1), Unop(s2, t2)) -> if s1 = s2 then (substitution t1 t2) else failwith "Ne filtre pas "
  | (Binop(s1, g1, d1), Binop(s2, g2, d2)) -> if s1 = s2 then (substitution g1 g2)@(substitution d1 d2) else failwith "Ne filtre pas"
  | _ -> failwith "Ne filtre pas";;




(**---------test------------**)
substitution motif1 terme1;; 
(*motif1=Binop ("f", Var "x", Const "a")*)
(*terme1=Binop ("f", Binop ("f", Const "a", Const "b"), Const "b"))*)
(*(string * terme) list = [("x", Binop ("f", Const "a", Const "b"))]*)

substitution_test motif2 terme1;;
(*motif2=Binop ("f", Binop ("f", Var "x", Var "y"), Const "a")*)
(*Exception: Failure "m ne filtre pas t"*)

substitution motif2 terme3;;
(*terme3=Binop ("f",Binop ("f", Binop ("f", Const "a", Const "b"),Binop ("f", Const "a", Const "b")),Const "a")*)
(*(string * terme) list =
[("x", Binop ("f", Const "a", Const "b"));("y", Binop ("f", Const "a", Const "b"))]*)

(**---------------------------------Q4------------------------------------**)

(*la signature pour Arithmetique*)
let sign_plus= [("zero",0);("succ",1);("plus",1)];;

(** compare
@param  : deux liste l1 l2 
@return : true si l2 appartient à l1 false sinon
*)
let rec compare l1 l2 = match (l1,l2) with
| ([],[]) ->true
| (_,[]) -> true
| ([],_) -> false
| (h1::r1,h2::r2)-> if h1 = h2 then (compare r1 r2) else false;;

(** test_sys
@param  : deux deux terme tg td 
@return : true si un système de réécriture est bien formé 
*)
let test_sys tg td = (test_terme_s sign_plus tg) && (test_terme_s sign_plus td) 
&& (if_motif tg) && (if_motif td) && let (l1,l2) = (list_var tg,list_var td) in compare l1 l2 ;; 

(**---------test------------**)
let tg1 = Binop("plus",Var "x",Const "zero");;
let td1 = Var "x";;
let tg2 = Binop("plus",Const "zero", Var "x");;
let td2 = Var "x";;
let tg3 = Binop("plus",Unop ("succ",Var "x"),Var "y");;
let td3 = Binop("plus",Var "x",Unop ("succ",Var "y"));;

test_sys tg1 td1;; (*bool = true*)
test_sys tg2 td2;; (*bool = true*)
test_sys tg3 td3;; (*bool = true*)

(**---------------------------------Q5------------------------------------**)
(** test_sys
@param  : un couple l
@return : return premier élément de l 
*)
let fst l = match l with (c1,c2) -> c1;;

(** test_sys
@param  : un couple l
@return : return deuxième élément de l 
*)
let snd l = match l with (c1,c2) -> c2;;

(** sub_override 
* - : terme -> terme -> (string * terme) list = <fun>
* - the override for substitution so that we can trait the result for the case of "fail" 
@param  : un motif m un terme t
@return : une liste de type (string*terme) 
*)
let rec sub_override m t  = match (m, t) with
  | (Var v1, str) -> [(v1, str)]
  | (Const s1, Const s2) ->if s1 = s2 then [] else [("false",Var "false")]
  | (Unop(s1, t1), Unop(s2, t2)) -> if s1 = s2 then (substitution_test t1 t2) else [("false",Var "false")]
  | (Binop(s1, g1, d1), Binop(s2, g2, d2)) -> if s1 = s2 then (substitution_test g1 g2)@(substitution_test d1 d2) else [("false",Var "false")]
  | _ -> [("false",Var "false")];;

(** if_exsist_sub
- : (string * 'a) list -> bool = <fun> 
@param  : une liste l qui conient le resultat de sub_override
@return : true si il n'y a pas de false, false sinon
*)
let rec if_exsist_sub l =  match l with
| [] ->true
| h::r -> if (fst h) = "false" then false else if_exsist_sub r;;  

(** remplace
- : string * terme -> terme -> terme = <fun>
@param  : un couple s de type string*terme , un terme td
@return : remplace td en fonction de s
*)
let rec remplace s td = match td with
| Var x -> if x = (fst s) then (snd s) else Var x
| Const a-> Const a 
| Unop (u,t) -> Unop(u,(remplace s t))
| Binop(b,t,d) -> Binop(b,(remplace s t),(remplace s d) );;

(** app_une_regle
- : terme * terme -> terme -> terme = <fun>
- : n'applique qu'une regle pour un terme t
@param  : une regle regle_1 un terme t
@return : un terme qui est transformé par regle_1
*)
let app_une_regle regle_1 t = let l = (sub_override (fst regle_1) t) 
in  if (if_exsist_sub l) then List.fold_right(function s -> function td-> (remplace s td)) l (snd regle_1) else t;;

(** app_de_la_regle 
- : (terme * terme) list -> terme -> terme = <fun>
- : applique tous les regles dans regle pour t
@param  : une liste de regle regle un terme t
@return : un terme qui est transformé par regle une fois
*)
let app_de_la_regle regle t = List.fold_right(function r-> function t-> let l = 
    (sub_override (fst r) t) in if (if_exsist_sub l) then app_une_regle r t else t ) regle t;; 

(** app_regle 
- : (terme * terme) list -> terme -> terme = <fun>
@param  : une liste de regle regle un terme t
@return : un terme qui est transformé par regle jusqu'a qu’aucune des `
regles de la liste ne s’applique plus
*)
let app_regle regle t = let t_i = app_de_la_regle regle t in if ( t_i = t ) then t else app regle t_i;;

(**---------test------------**)

let regle = [(Binop("plus",Var "x",Const "zero"),Var "x");
(Binop("plus",Const "zero", Var "x"),Var "x");
(Binop("plus",Unop ("succ",Var "x"),Var "y"),Binop("plus",Var "x",Unop ("succ",Var "y")))];;

let t = Binop("plus",Const "zero",Binop("plus",Unop("succ",Const "zero"),Unop("succ",Const "zero")));;
app_regle regle t;;

let r2 = ((Binop("plus", Const "zero", Var "x")),(Var "x"));;
let terme = Binop("plus", Const "zero", Binop("plus", Unop("succ", Const "zero"), Unop("succ", Const "zero")));;

sub_override  (fst r2) terme;;


(**---------------------------------Q6------------------------------------**)


