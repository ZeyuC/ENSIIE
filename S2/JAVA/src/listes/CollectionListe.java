package listes;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;

/** Classe CollectionListe impl�mentant l'interface AbstractCollection
 * @author K�vin Cocchi
 * @param <E> le type des �l�ments de la collection
 */
public class CollectionListe<E> extends AbstractCollection<E> {
	
	protected Liste<E> liste;

	/** Constructeur par d�faut */
	public CollectionListe() {
		liste = new Liste<E>();
	}

	/** Constructeur de copie
	 * @param c la collection � copier
	 */
	public CollectionListe(Collection<E> c) {
		liste = new Liste<E>();
		addAll(c);
	}

	@Override
	public boolean add(E elt) {
		liste.add(elt);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean remove(Object o) {
		return liste.remove((E) o);
	}

	@Override
	public int size() {
		return liste.size();
	}

	@Override
	public int hashCode() {
		return liste.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		return liste.equals(o);
	}

	@Override
	public Iterator<E> iterator() {
		return liste.iterator();
	}
}
