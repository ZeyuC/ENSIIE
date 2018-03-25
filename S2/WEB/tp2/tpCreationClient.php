<?php
include("tpModele.php");
include("tpVue.php");

verif_authent();

$numCli = $_POST['numCli'];
$nomCli = $_POST['nomCli'];
$debitInit = $_POST['debitInit'];

enTete("Création d'un client");

if(create_client($numCli, $nomCli, $debitInit))
	affiche_info("La création du client ".$numCli." est effectuée.");
else
	affiche_erreur("Impossible de se connecter.");

pied();
?>
