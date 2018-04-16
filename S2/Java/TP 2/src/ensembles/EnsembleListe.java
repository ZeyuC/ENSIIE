package ensembles;

import java.util.Iterator;

import listes.Liste;

/**
 * Ensemble à base de listes
 *
 * 
 */
public class EnsembleListe<E> extends EnsembleGenerique<E> {
	/**
	 * Conteneur sous-jacent : une Liste<E>
	 */
	protected Liste<E> liste;

	/**
	 * Constructeur par défaut d'un ensemble à base de {@link listes.Liste}
	 */
	public EnsembleListe() {
		liste = new Liste<E>();
	}

	/**
	 * Constructeur de copie à partir d'un {@link Iterable}
	 *
	 * @param elements l'itérable dont on doit copier les éléments
	 */
	public EnsembleListe(Iterable<E> elements) {
		liste = new Liste<E>();
		for(E elt : elements)
			ajout(elt);
	}

	/**
	 * Ajout d'un élément à un ensemble ssi celui ci n'est pas null et qu'il
	 * n'est pas déjà présent
	 * Ce qui revient dans le cas présent à ajouter un élément à la liste si
	 * celui ci n'y est pas déjà présent
	 *
	 * @param element l'élément à ajouter à l'ensemble (on considèrera que l'on
	 *        ne peut pas ajouter d'élément null)
	 * @return true si l'élément a pu être ajouté à l'ensemble, false sinon ou
	 *         si l'on a tenté d'insérer un élément null (auquel cas il n'est
	 *         pas inséré)
	 * @see ensembles.EnsembleGenerique#ajout(java.lang.Object)
	 */
	@Override
	public boolean ajout(E element) {
		if(element == null || contient(element))
			return false;

		liste.add(element);
		return true;
	}

	/**
	 * Taille de l'ensemble : réimplémentation en utilisant les propriétés de la
	 * liste sous-jacente plutôt que l'itérateur (amélioration de performances)
	 *
	 * @return le nombre d'éléments dans l'ensemble
	 * @see ensembles.EnsembleGenerique#cardinal()
	 */
	@Override
	public int cardinal() {
		return liste.size();
	}

	/**
	 * Union avec un autre ensemble en réutilisant la méthode de classe union
	 * écrite dans l'ensemble Générique (
	 * {@link ensembles.EnsembleGenerique#union(ensembles.Ensemble, ensembles.Ensemble, ensembles.Ensemble)}
	 * ) et un nouvel {@link ensemble.EnsembleListe} pour stocker le résultat.
	 *
	 * @param ensemble l'autre ensemble avec lequel on veut créer une union
	 * @return un nouvel ensemble contenant l'union de l'ensemble courant et de
	 *         l'ensemble passé en argument
	 * @see ensembles.EnsembleGenerique#union(ensembles.Ensemble,
	 *      ensembles.Ensemble, ensembles.Ensemble)
	 */
	@Override
	public Ensemble<E> union(Ensemble<E> ensemble) {
		Ensemble<E> res = new EnsembleListe<E>();
		Ensemble.union(this, ensemble, res);
		return res;
	}

	/**
	 * Intersection avec un autre ensemble en réutilisant la méthode de classe
	 * intersection écrite dans l'ensemble Générique (
	 * {@link ensembles.EnsembleGenerique#intersection(ensembles.Ensemble, ensembles.Ensemble, ensembles.Ensemble)}
	 * ) et un nouvel {@link ensemble.EnsembleListe} pour stocker le résultat.
	 *
	 * @param ensemble l'autre ensemble avec lequel on veut créer une
	 *        intersection
	 * @return un nouvel ensemble contenant l'intersection de l'ensemble courant
	 *         et de l'ensemble passé en argument
	 * @see ensembles.EnsembleGenerique#intersection(ensembles.Ensemble,
	 *      ensembles.Ensemble, ensembles.Ensemble)
	 */
	@Override
	public Ensemble<E> intersection(Ensemble<E> ensemble) {
		Ensemble<E> res = new EnsembleListe<E>();
		Ensemble.intersection(this, ensemble, res);
		return res;
	}

	/**
	 * Complément avec un autre ensemble en réutilisant la méthode de classe
	 * complement écrite dans l'ensemble Générique (
	 * {@link ensembles.EnsembleGenerique#complement(ensembles.Ensemble, ensembles.Ensemble, ensembles.Ensemble)}
	 * ) et un nouvel {@link ensemble.EnsembleListe} pour stocker le résultat.
	 *
	 * @param ensemble l'autre ensemble avec lequel on veut créer le complément
	 * @return un nouvel ensemble contenant uniquement les éléments présents
	 *         dans l'ensemble courant mais PAS dans l'ensemble passé en argument
	 * @see ensembles.EnsembleGenerique#complement(ensembles.Ensemble,
	 *      ensembles.Ensemble, ensembles.Ensemble)
	 */
	@Override
	public Ensemble<E> complement(Ensemble<E> ensemble) {
		Ensemble<E> res = new EnsembleListe<E>();
		Ensemble.complement(this, ensemble, res);
		return res;
	}

	/**
	 * Factory method fournissant un itérateur sur l'ensemble en réutilisant
	 * l'itérateur de la liste sous-jacente
	 *
	 * @return un nouvel itérateur sur cet ensemble
	 * @see ensembles.EnsembleGenerique#iterator()
	 */
	@Override
	public Iterator<E> iterator() {
		return liste.iterator();
	}
}
