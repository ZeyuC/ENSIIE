<?php
session_start();
include("config.php");
include("db.php");

/* Fonction qui renvoie l'utilisateur sur la page de login si besoin */
function verif_authent() {
    global $AUTHENT;

    if($AUTHENT == 1)
        if(!$_SESSION['nomuser'])
            header('Location: tpConnexion.php');
}

function config() {
    global $nom_hote, $nom_user, $nom_base, $mdp;
    $_SESSION['nomhote'] = $nom_hote;
    $_SESSION['nombase'] = $nom_base;
    $_SESSION['nomuser'] = $nom_user;
    $_SESSION['mdp'] = $mdp;
}

/*
Permet de récupérer le nom et le débit d'un client de numéro donné.
Retourne un tableau comprenant trois valeurs :
- première valeur (information sur l'exécution) :
        0 si tout s'est bien passé
        -1 si aucun client n'a été trouvé avec le numéro donné
        -2 si la requête n'a pas pu être exécutée
        -3 si la connexion à la BD n'a pas réussi
- lseconde valeur : nom du client si tout s'est bien passé, 0 sinon
- troisième valeur : débit du client si tout s'est bien passé, 0 sinon
*/
function get_client($numCli) {
    if($db = db_connect()) {
        $req = "SELECT nom_client, debit_client
                FROM client WHERE num_client = ".$numCli;
        $rep = db_query($db, $req);

        if(!($rep))
            return array(-2, 0, 0);

        if(db_count($rep) == 0)
            return array(-1, 0, 0);

        $fetch = db_fetch($rep);
        return array(0, $fetch['nom_client'], $fetch['debit_client']);
		db_close($db);
	}

    return array(-3, 0, 0);
}

function insert_achat($numCli, $montant) {
    if($montant <= 0) {
        affiche_erreur("Achat impossible : le montant ne peut pas être négatif ou nul.");
        return false;
    }

    if($db = db_connect()) {
        $req = "UPDATE client
                SET debit_client = debit_client - ".test_input($montant)."
                WHERE num_client = ".test_input($numCli)." AND debit_client - ".test_input($montant)." >= 0";
        $rep = db_query($db, $req);
        if(db_count($rep) == 0) {
            affiche_erreur("Achat impossible : le débit du client est insuffisant.");
            return false;
        }

        $req = "INSERT INTO achat(montant_achat, date_achat, client)
                VALUES(".format_number(test_input($montant)).", CURRENT_DATE, ".test_input($numCli).")";
        $rep = db_query($db, $req);
        if(db_count($rep) == 0)
            return false;

        db_close($db);
        return true;
    }

    return false;
}

function create_client($numCli, $nomCli, $debitInit) {
	if($debitMod < 0) {
		affiche_erreur("Le débit du client ne peut pas être négatif.");
		return false;
	}

	if($db = db_connect()) {
		$req = "INSERT INTO client(num_client, nom_client, debit_client)
                VALUES(".test_input($numCli).", '".test_input($nomCli)."', ".format_number(test_input($debitInit)).")";
		db_query($db, $req);

		db_close($db);
		return true;
	}

	return false;
}

function set_client($numCli, $nomMod, $debitMod) {
	if($debitMod < 0) {
		affiche_erreur("Le débit du client ne peut pas être négatif.");
		return false;
	}

	if($db = db_connect()) {
		$req = "UPDATE client
				SET nom_client = '".test_input($nomMod)."', debit_client = '".format_number(test_input($debitMod))."'
				WHERE num_client = ".test_input($numCli);
		db_query($db, $req);
		db_close($db);
		return true;
	}

	return false;
}

function verif_mdp($mdp) {
	if($db = db_connect()) {
		$_SESSION['mdp'] = $mdp;
		db_close($db);
		return true;
	}

	return false;
}

function detruire_session() {
	session_destroy();
}

function test_input($data) {
    $data = trim($data);
    $data = stripslashes($data);
    $data = htmlspecialchars($data);
    return $data;
}

function format_number($str) {
    return str_replace(',', '.', $str);
}
?>
