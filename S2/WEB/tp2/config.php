<?php
// Suppression des warnings inutiles de pgsql qui sont gérés quand même
error_reporting(E_ERROR | E_PARSE);

$nom_hote = "pgsql2";
$nom_base = "tpphp";
$nom_user = "tpphp";
$mdp = "tpphp";

// Mettre à 1 pour activer l'authentification
$AUTHENT = 1;
?>
