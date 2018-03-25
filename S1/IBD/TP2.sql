/***Projections et restictions***/

/*select nom from buveur;*/

/*select ville from Viticulteur
order by ville;*/

/*select n_commande,c_qte from Commande
  where c_qte >= 6 and c_qte <= 15;*/

/*select n_commande from Livraison 
where l_date > '2002-12-25';*/

/*select n_vin,cru,region from Vin
 where region like 'BO%' or region like '%NE';*/

/***Jointures et requÃªtes d'existence ou de comparaison***/

/***Questions principales***/

/*select distinct vit.n_viticulteur,nom from  vin join viticulteur vit 
on vin.n_viticulteur=vit.n_viticulteur 
where region='LOIRE' and millesime='1993';*/  /*1.SQL-92*/

/*select distinct vit.n_viticulteur,nom from vin,viticulteur vit 
 where vin.n_viticulteur = vit.n_viticulteur and region='LOIRE'    
 and millesime='1993';*/           /* 1.SQL-89*/

/* select nom,prenom from Commande join Buveur 
 on Commande.n_buveur=Buveur.n_buveur join vin
 on Commande.n_vin=vin.n_vin where cjmmru='POMMARD';*/  /*2.SQL-92*/

/*select nom,prenom from Commande,Buveur,vin
where Commande.n_buveur=Buveur.n_buveur and Commande.n_vin=vin.n_vin 
and cru='POMMARD';*/                /*2.SQL-89*/

/*select vit.nom from Buveur B join Commande C on B.n_buveur=C.n_buveur 
join Vin on C.n_vin=Vin.n_vin join Viticulteur vit on
Vin.n_viticulteur=vit.n_viticulteur where B.nom='DUPOND';*/ /*3.SQL-92*/

/*select vit.nom from Buveur B,Commande C,Vin,Viticulteur vit
where B.n_buveur=C.n_buveur and C.n_vin=Vin.n_vin and
Vin.n_viticulteur=vit.n_viticulteur and B.nom='DUPOND';*/   /*3.SQL-89*/

/*select vit.n_viticulteur,vit.nom from Viticulteur vit join Vin 
on vit.n_viticulteur=Vin.n_viticulteur join Commande C on
C.n_vin=Vin.n_vin join Buveur B on B.n_buveur=C.n_buveur 
where B.b_ville=vit.v_ville;*/     /*4*/

/*select n_buveur,nom from buveur
where buveur.n_buveur not in ( select n_buveur from commande);*/ /*5.a*/

/*select n_buveur,nom from buveur where not exists ( select * from commande where commande.n_buveur = buveur.n_buveur);*/                /*5.b*/

/*select buveur.n_buveur,nom from buveur left outer join commande on buveur.n_buveur = commande.n_buveur where n_vin is NULL;*/    /*5.c !n_vin is null!*/

/* select cru from vin join commande on vin.n_vin=commande.n_vin 
where vin.n_vin not in
(select n_vin from commande where c_qte>=12);*/     /*6.a*/

/*select cru from vin,commande where not exists
(select * from commande where vin.n_vin=commande.n_vin and
 commande.c_qte>=12) and vin.n_vin=commande.n_vin;*/  /*6.b*/

/* select cru from commande left outer join (select * from commande where c_qte>=12) as com on commande.n_vin=com.n_vin left outer join vin on commande.n_vin=vin.n_vin where com.c_qte is null ; */                            /*6.c.1*/ /*facon1*/
/*select cru from commande left outer join commande com on (commande.n_vin=com.n_vin and com.c_qte>=12) left outer join vin on commande.n_vin=vin.n_vin where com.c_qte is null ; */                                          /*6.c.2*/  /*facon2*/

/*select distinct buveur.n_buveur,nom from buveur join commande on buveur.n_buveur=commande.n_buveur where buveur.n_buveur not in (select n_buveur from commande join vin on commande.n_vin=vin.n_vin 
where region !='BOURGOGNE');  */                  /*7.a*/

/*select distinct buveur.n_buveur,nom from buveur,commande where not exists (select * from vin,commande where vin.n_vin=commande.n_vin and commande.n_buveur=buveur.n_buveur and region !='BOURGOGNE') and buveur.n_buveur=commande.n_buveur;   /*7.b*/

/*select distinct buveur.n_buveur,nom from buveur join commande on buveur.n_buveur=commande.n_buveur where buveur.n_buveur in(select n_buveur from commande join vin on commande.n_vin=vin.n_vin where region ='BORDEAUX') and buveur.n_buveur in(select n_buveur from commande join vin on commande.n_vin=vin.n_vin where region ='BOURGOGNE') and buveur.n_buveur not in(select n_buveur from commande join vin on commande.n_vin=vin.n_vin where region !='BORDEAUX' and region !='BOURGOGNE');*/                          /*8.a*/

/* select v_ville from viticulteur 
zeyu_vins-> union
zeyu_vins-> select b_ville from buveur;*/  /*9*/

/***Fonctions de calcul et agrÃgats***/
/**Questions principales**/

/*select count(distinct cru) from vin;*/  /*1*/

/*select count(n_commande) from livraison
where l_date between '2001-01-01' and '2002-01-01';*/ /*2*/

/*select (n_commande) from livraison
where extract(year from l_date)='2001'; */       /*2.2*/

/*select region,count(region) as "nombre de vin" from vin
group by (region);*/        /*3*/

/*select vin.n_viticulteur,nom,count(vin.n_viticulteur) as "nombre de vins"
from vin,viticulteur vit where vin.n_viticulteur = vit.n_viticulteur
group by nom,vin.n_viticulteur
order by count(vin.n_viticulteur) DESC;*/     /*4*/

/*select commande.n_buveur,nom,round(avg(c_qte)) "quantite moyenne vendue"
from buveur,commande where commande.n_buveur=buveur.n_buveur
and b_ville='PARIS' group by commande.n_buveur,nom;*/ /*5*/

/*select commande.n_buveur,nom,sum(c_qte) "quantite moyenne vendue"
from buveur,commande where commande.n_buveur=buveur.n_buveur
 group by commande.n_buveur,nom
 having avg(c_qte)>=12;*/            /*6*/

/* select vin.n_viticulteur,nom,count(vin.n_viticulteur) as "nombre de vins"
from vin,viticulteur vit where vin.n_viticulteur = vit.n_viticulteur
group by nom,vin.n_viticulteur
having count(vin.n_viticulteur)>=2;*/     /*7*/

/**Questions supplementaire**/

/*select count(*) from (select count(*) from buveur
group by b_ville) b*/  /*1*/

/*select sum(c_qte) from commande
where extract (year from c_date)='2002';*/   /*2*/

/*select reigion,count(distinct n_viticulteur) "nombre de viticulteur" from vin group by region;*/ /*3*/

/*select buveur.n_buveur,nom,count(distinct n_vin) from buveur join commande on buveur.n_buveur=commande.n_buveur group by buveur.n_buveur;*/ /*4.1*/

/*select buveur.n_buveur,nom,count(distinct n_vin) from buveur left join commande on buveur.n_buveur=commande.n_buveur group by buveur.n_buveur;*/ /*4.2*/

/*select viticulteur.n_viticulteur,nom,avg(c_qte)"quantite moyenne vendue"
from viticulteur join vin using(n_viticulteur) join commande using(n_vin) 
where region='BOURGOGNE' group by n_viticulteur;*/  /*5*/

/*select viticulteur.n_viticulteur,nom,sum(c_qte)"quantite " from viticulteur join vin using(n_viticulteur) join commande using(n_vin) group by n_viticulteur having avg(c_qte)>=12;*:         /*6*/

/*SELECT n_buveur,nom,count(distinct cru) FROM buveur B JOIN commande C USING(n_buveur) JOIN vin USING(n_vin) GROUP BY n_buveur*/           /*7*/
/*SELECT n_buveur,nom,region,count(distinct cru)"nombre de vins commandes" FROM buveur B JOIN commande C USING(n_buveur) JOIN vin USING(n_vin) GROUP BY region,n_buveur 
HAVING count(distinct cru)>=2;*/ /*8*/
