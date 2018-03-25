<?php
include("tpVue.php");
include("tpModele.php");

$mdp = $_POST['mdp'];

enTete("Vérification du mot de passe");

if(verif_mdp($mdp)) {
	config();
	header('Location: index.php');
}
else {
	affiche_erreur("Le mot de passe entré est eronné.");
    affiche_info('Veuillez-essayer de nouveau <a href="tpConnexion.php">ici</a>.');
}

pied();
?>
