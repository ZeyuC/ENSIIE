<?php
/* Fonction d'affichage de l'en-tête de la page */
function enTete($titre) {
    $current_page = basename($_SERVER['SCRIPT_FILENAME']);

    echo '<!DOCTYPE html>
    <html>
        <head>
            <meta charset="utf-8" />
            <title>'.$titre.'</title>
            <link rel="stylesheet" href="tpStyle.css"/>
            <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
            <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
            <link rel="icon" href="favicon.ico" type="image/x-icon" />
            <meta name="theme-color" content="#3F51B5">
        </head>

        <body>
            <div class="wrapper">
                <header>';

    echo '<h1>'.$titre.'</h1>';

    if($current_page != "index.php" && $current_page != "tpConnexion.php") {
        print '<a href="index.php"><i class="material-icons">arrow_back</i></a>';
    }
    if($current_page != "tpQuitter.php" && $current_page != "tpConnexion.php" && $current_page != "tpQuitter.php") {
        print '<a href="tpQuitter.php" class="logout"><i class="material-icons">power_settings_new</i></a>';
    }

    echo '<div class="clearfix"></div></header><section class="main">';
}

/* Fonction d'affichage du pied de la page */
function pied() {
    echo '
            </div>
        </body>
    </html>';
}

/* Affichage basique d'une chaîne */
function affiche($str) { echo $str; }
/* Affichage d'une information */
function affiche_info($str) { echo '<p>'.$str.'</p>'; }
/* Affichage d'une erreur */
function affiche_erreur($str) { echo '<p class="erreur">'.$str.'</p>'; }

/* Affichage du menu de sélection */
function affiche_menu() {
    echo '
    <form action="tpGestionClient.php" method="post">'.
        ajout_champ('number', '', 'numCli', 'Numéro de client', 'numCli', 1).
        "<fieldset>
            <legend>Choix</legend>\n".
                ajout_champ("radio", "v", "choix", "Visualisation", "v", 1)."\n".
                ajout_champ("radio", "m", "choix", "Modification", "m", 1)."\n".
                ajout_champ("radio", "c", "choix", "Création", "c", 1)."\n".
                ajout_champ("radio", "a", "choix", "Achat", "a", 1)."\n".
        "</fieldset>\n".
        ajout_champ('submit', 'Envoyer', 'soumission', '', '', 0)."\n".
    '</form>';
}

/* Affichage du formulaire de modification */
function affiche_form_modif($numCli, $nomCli, $debitCli) {
	echo '
    <form action="tpModifClient.php" method="post">'.
        "<fieldset>\n
            <legend>Modification</legend>\n".
                ajout_champ('hidden', $numCli, 'numCli', '', '', 0).
                ajout_champ('text', $nomCli, 'nomMod', 'Nom du client', 'nomMod', 1)."\n".
                ajout_champ('text', $debitCli, 'debitMod', 'Débit du client', 'debitMod', 1)."\n".
        "</fieldset>\n".
        ajout_champ('submit', 'Envoyer', 'soumission', '', '', 0)."\n".
    '</form>';
}

/* Affichage du formulaire d'achat */
function affiche_form_achat($numCli, $achat) {
	echo '
    <form action="tpNvelAchat.php" method="post">'.
        "<fieldset>\n
            <legend>Achat</legend>\n".
                ajout_champ('hidden', $numCli, 'numCli', '', '', 0).
                ajout_champ('text', $achat, 'montant', 'Montant', 'montant', 1)."\n".
        "</fieldset>\n".
        ajout_champ('submit', 'Envoyer', 'soumission', '', '', 0)."\n".
    '</form>';
}

/* Affichage du formulaire de création */
function affiche_form_creation($numCli) {
    echo '
    <form action="tpCreationClient.php" method="post">'.
        "<fieldset>\n
            <legend>Création</legend>\n".
                ajout_champ('hidden', $numCli, 'numCli', '', '', 0).'<br/>'.
                ajout_champ('text', $nomCli, 'nomCli', 'Nom du client', 'nomCli', 1)."\n".
                ajout_champ('text', $debitCli, 'debitInit', 'Débit initial', 'debitInit', 1)."\n".
        "</fieldset>\n".
        ajout_champ('submit', 'Envoyer', 'soumission', '', '', 0)."\n".
    '</form>';
}

/* Page de connexion */
function vue_connexion() {
    echo '
    <p>Bienvenue sur l\'application de gestion des débits clients.</p>
    <p>Pour utiliser l\'application, veuillez d\'abord rentrer le mot de passe.</p><br/>

    <form action="tpVerifMDP.php" method="post">
        <label>Mot de passe</label>
        <input type="password" name="mdp" size="8"/><br/>
        <input type="submit" value="Valider"/>
    </form>
    <div class="clearfix"></div>';
}

/* Page de déconnexion */
function vue_deconnexion() {
    echo '
    <p>La déconnexion a bien eu lieu.</p>
    <p>Merci d\'avoir utilisé l\'apllication.</p><br/>';
}

/* Affichage des erreurs liées à la gestion de client */
function vue_gestion_client($client_arr, $choix, $numCli) {
    if($client_arr[0] == 0 && $choix == "v") /* Client existant ; Visualisation */
        affiche_info("Nom : $client_arr[1]<br/>Débit : $client_arr[2]");
    if($client_arr[0] == 0 && $choix == "m") /* Client existant ; Modification */
        affiche_form_modif($numCli, $client_arr[1], $client_arr[2]);
    if($client_arr[0] == 0 && $choix == "a") /* Client existant ; Achat */
        affiche_form_achat($numCli, 0);
    if($client_arr[0] == 0 && $choix == "c") /* Client existant ; Création */
        affiche_erreur("Ce numéro de client est déjà existant.");

    if($client_arr[0] == -1 && $choix != "c") /* Client inexistant */
        affiche_erreur("Aucun client correspondant n'a été trouvé.");
    elseif($client_arr[0] == -1 && $choix == "c") /* Client inexistant ; Création */
        affiche_form_creation($numCli);

    if($client_arr[0] == -2)
        affiche_erreur("La requête n'a pas pu être exécutée.");
    if($client_arr[0] == -3)
        affiche_erreur("Une connexion à la base de donnée n'a pas pu être établie.");
}

/* Prend en arguments, dans l'ordre : type, value, name, label, id, (required), (step) */
function ajout_champ() {
	$tmp = '';

    // Label de l'input (autre que radio)
	if(!empty(func_get_arg(3)) && func_get_arg(0) != "radio")
        $tmp.= '<label for="'.func_get_arg(4) .'">'.func_get_arg(3).'</label>';

    // Input
	$tmp .= '<input type="'.func_get_arg(0).'" ';
	if(!empty(func_get_arg(4)))
        $tmp.= 'id="'.func_get_arg(4).'" ';
	if(!empty(func_get_arg(1)))
        $tmp.= 'value="'.func_get_arg(1).'" ';
	if(!empty(func_get_arg(2)))
		$tmp.= 'name="'.func_get_arg(2).'" ';
	if(func_num_args() > 5 && func_get_arg(5))
		$tmp.= 'required="required" ';
	if(func_num_args() > 6 && func_get_arg(0) == "number" && func_get_arg(6) == -1)
		$tmp .= 'step="any" ';
    $tmp .= '>';

    // Label de l'input (type radio)
	if(!empty(func_get_arg(3)) && func_get_arg(0) == "radio")
		$tmp.= '<label for="'.func_get_arg(4) .'">'.func_get_arg(3).'</label>';

    if(func_get_arg(0) == "submit")
        $tmp .= '<div class="clearfix"></div>';

	return $tmp;
}

function number_fr($num){
    return number_format($num, 2, ',', ' ');
}
?>
