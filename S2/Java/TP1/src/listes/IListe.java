package listes;

import java.util.Iterator;

/** Interface d'une liste g�n�rique d'�l�ments
 * @note On consid�rera que la liste ne peut pas contenir d'�l�ment null
 * @author David Roussel
 * @param <E> le type des �l�ments de la liste
 */
public interface IListe<E> extends Iterable<E> {
	/** Ajout d'un �l�ment en fin de liste
	 * @param elt l'�l�ment � ajouter en fin de liste
	 * @throws NullPointerException si l'on tente d'ajouter un �l�ment null
	 */
	public abstract void add(E elt) throws NullPointerException;

	/** Insertion d'un �l�ment en t�te de liste
	 * @param elt l'�l�ment � ajouter en t�te de liste
	 * @throws NullPointerException si l'on tente d'ins�rer un �l�ment null
	 */
	public abstract void insert(E elt) throws NullPointerException;

	/** Insertion d'un �l�ment � la (index+1)-i�me place
	 * @param elt l'�l�ment � ins�rer
	 * @param index l'index de l'�l�ment � ins�rer
	 * @return true si l'�l�ment a pu �tre ins�r� � l'index voulu, false sinon
	 * @throws NullPointerException si l'on tente d'ins�rer un �l�ment null
	 */
	public abstract boolean insert(E elt, int index);

	/** Suppression de la premi�re occurrence de l'�l�ment elt
	 * @param elt l'�lement � supprimer
	 * @return true si l'�l�ment a �t� trouv� et supprim� de la liste
	 * @note doit fonctionner m�me si elt est null
	 */
	public default boolean remove(E elt) {
		Iterator<E> it = iterator();
		
		while(it.hasNext()) {
			if(it.next().equals(elt)) {
				it.remove();
				return true;
			}
		}
		
		return false;
	}

	/** Suppression de toutes les instances de elt dans la liste
	 * @param elt l'�l�ment � supprimer
	 * @return true si au moins un �l�ment a �t� supprim�
	 * @note doit fonctionner m�me si elt est null
	 */
	public default boolean removeAll(E elt) {
		boolean removed = false;
		Iterator<E> it = iterator();
		
		while(it.hasNext()) {
			if(it.next().equals(elt)) {
				it.remove();
				removed = true;
			}
		}

		return removed;
	}

	/** Nombre d'�l�ments dans la liste
	 * @return le nombre d'�l�ments actuellement dans la liste
	 */
	public default int size() {
		int count = 0;
		Iterator<E> it = iterator();
		
		while(it.hasNext()) {
			it.next();
			count++;
		}

		return count;
	}

	/** Effacement de la liste
	 */
	public default void clear() {
		Iterator<E> it = iterator();
		
		while(it.hasNext()) {
			it.next();
			it.remove();
		}
	}

	/** Test de liste vide
	 * @return true si la liste est vide, false sinon
	 */
	public default boolean empty() {
		return size() == 0;
	}
	
	/** Test d'�galit� au sens du contenu de la liste
	 * @param o la liste dont on doit tester le contenu
	 * @return true si les listes ont m�me longueur, et les maillons sont �gaux deux � deux, false sinon
	 * @note on serait tent� d'en faire une "default method" dans la mesure o�
	 *       l'on peut n'utiliser que l'it�rateur pour parcourir les �l�ments de
	 *       la liste MAIS les m�thodes par d�faut n'ont pas le droit de
	 *       surcharger les m�thodes de la superclasse Object
	 */
	@Override
	public abstract boolean equals(Object o);

	/** HashCode d'une liste
	 * @return le hashCode de la liste
	 * @note on serait tent� d'en faire une "default method" dans la mesure o�
	 *       l'on peut n'utiliser que l'it�rateur pour parcourir les �l�ments de
	 *       la liste MAIS les m�thodes par d�faut n'ont pas le droit de
	 *       surcharger les m�thodes de la superclasse Object
	 */
	@Override
	public abstract int hashCode();

	/** Repr�sentation de la chaine sous forme de chaine de caract�re
	 * @return une chaine de caract�re repr�sentant la liste chain�e
	 * @note on serait tent� d'en faire une "default method" dans la mesure o�
	 *       l'on peut n'utiliser que l'it�rateur pour parcourir les �l�ments de
	 *       la liste MAIS les m�thodes par d�faut n'ont pas le droit de
	 *       surcharger les m�thodes de la superclasse Object
	 */
	@Override
	public abstract String toString();

	/** Obtention d'un it�rateur pour parcourir la liste : <code>
	 * Liste<Type> l = new Liste<Type>();
	 * ...
	 * for (Iterator<Type> it = l.iterator(); it.hasNext(); ) {
	 * 		... it.next() ...
	 * }
	 * for (Type elt : l) {
	 * 		... elt ...
	 * }
	 * </code>
	 * @return un nouvel it�rateur sur la liste
	 * @see {@link Iterable#iterator()}
	 */
	@Override
	public abstract Iterator<E> iterator();
}
