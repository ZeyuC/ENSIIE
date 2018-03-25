<?php
include("tpModele.php");
include("tpVue.php");

verif_authent();

enTete("Gestion des clients");

/// Récupération du numéro de client entré dans le formulaire
$numCli = $_POST['numCli'];

// Si aucune case n'a été cochée
if(!isset($_POST['choix'])) {
    affiche_erreur("Vous devez cocher une case.");
}
// Si le numéro de client est manquant ou invalide
elseif((!isset($numCli)) | $numCli == "" | ! is_numeric($numCli)) {
    affiche_erreur("Vous devez entrer un numéro de client.");
}
else {
    $client = get_client($numCli);

    switch($_POST['choix']) {
		case "v": /* Consultation d'un client */
            affiche_info("Consultation du client numéro $numCli.");
            vue_gestion_client($client, $_POST['choix'], $numCli);
		break;

		case "m": /* Modification d'un client */
			affiche_info("Modification du client numéro $numCli.");
            vue_gestion_client($client, $_POST['choix'], $numCli);
		break;

		case "a": /* Enregistrement d'un achat */
            affiche_info("Achat du client numéro $numCli.");
            vue_gestion_client($client, $_POST['choix'], $numCli);
        break;

        case "c": /* Création d'un client */
            affiche_info("Création du client numéro $numCli.");
            vue_gestion_client($client, $_POST['choix'], $numCli);
        break;

        default: /* Cas par défaut (ne devrait jamais se produire) */
            affiche_erreur("Vous devez cocher une case.");
        break;
    }
}

pied();
?>
