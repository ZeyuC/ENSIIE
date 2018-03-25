<?php
include("tpModele.php");
include("tpVue.php");

verif_authent();

$numCli = $_POST['numCli'];
$nomMod = $_POST['nomMod'];
$debitMod = $_POST['debitMod'];

enTete("Modification d'un client");

if(set_client($numCli, $nomMod, $debitMod))
	affiche_info("La modification du client $numCli est effectuée.");
else
	affiche_erreur("La modification du client $numCli a échouée.");

pied();
?>
