package listes;

import java.util.Iterator;
import java.util.NoSuchElementException;

/** Classe Liste implémentant l'interface IListe
 * @note On considèrera que la liste ne peut pas contenir d'élément null
 * @author Kévin Cocchi
 * @param <E> le type des éléments de la liste
 */
public class Liste<E> implements IListe<E> {
	
	protected Maillon<E> head;
	
	/** Classe Maillon
	 * @param <E> le type des éléments du maillon
	 */
	private static class Maillon<E> {
		private E data;
		private Maillon<E> next;

		public Maillon(E data, Maillon<E> next) {
			setData(data);
			setNext(next);
		}
		
		public Maillon(E data) {
			this(data, null);
		}
		
		public E getData() { return data; }
		public void setData(E data) { this.data = data; }
		
		public Maillon<E> getNext() { return next; }
		public void setNext(Maillon<E> next) { this.next = next; }
	}
	
	/** Classe ListeIterator implémentant Iterator
	 * @param <E> le type des éléments de l'itérateur
	 */
	private class ListeIterator<F> implements Iterator<E> {
		private Maillon<E> current;
		private Maillon<E> last;
		private Maillon<E> before_last;
		private boolean nextCalled;
		
		public ListeIterator() {
			current = head;
			last = null;
			before_last = null;
			nextCalled = false;
		}
		
		/** (non-Javadoc)
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			return current != null;
		}
		
		/** (non-Javadoc)
		 * @see java.util.Iterator#next()
		 */
		@Override
		public E next() throws NoSuchElementException {
			if(current == null)
				throw new NoSuchElementException("no next element.");
            
            before_last = last;
			last = current;
            current = current.getNext();
            nextCalled = true;
            return last.getData();
		}
		
		@Override
		public void remove() throws IllegalStateException {
			if((last == null) || (!nextCalled))
				throw new IllegalStateException("call next before remove.");
			
			if(last == head)
				head = current;
			else
				before_last.next = current;
			
			nextCalled = false;
		}
	}
	
	/** Constructeur par défaut d'une liste */
	public Liste() {
		head = null;
	}

	/** Constructeur de copie d'une liste
	 * @param l la liste à copier
	 */
	public Liste(IListe<E> l) {
		head = null;
		Iterator<E> it = l.iterator();
		
		while(it.hasNext())
			add(it.next());
	}

	/** Ajout d'un élément en fin de liste
	 * @param elt l'élément à ajouter en fin de liste
	 * @throws NullPointerException si l'on tente d'ajouter un élément null
	 */
	@Override
	public void add(E elt) throws NullPointerException {
		if(elt == null)
			throw new NullPointerException("elt is null.");

		Maillon<E> ajout = new Maillon<E>(elt);
		Maillon<E> last = null;
		Maillon<E> current = head;
		Iterator<E> it = iterator();

		if(head == null)
			head = ajout;
		else {
			while(it.hasNext()) {
				last = current;
				current = current.getNext();
				it.next();
			}
			last.setNext(ajout);
		}
	}
	
	/** Insertion d'un élément en tête de liste
	 * @param elt l'élément à ajouter en tête de liste
	 * @throws NullPointerException si l'on tente d'insérer un élément null
	 */
	@Override
	public void insert(E elt) throws NullPointerException {
		if(elt == null)
			throw new NullPointerException("elt is null.");
		
		Maillon<E> maillon = new Maillon<E>(elt);
		maillon.setNext(head);
		head = maillon;
	}
	
	/** Insertion d'un élément à la (index+1)-ième place
	 * @param elt l'élément à insérer
	 * @param index l'index de l'élément à insérer
	 * @return true si l'élément a pu être inséré à l'index voulu, false sinon
	 * @throws NullPointerException si l'on tente d'insérer un élément null
	 */
	@Override
	public boolean insert(E elt, int index) {
		if(elt == null)
			return false;
		if(index < 0)
			return false;
		
		Maillon<E> ajout = new Maillon<E>(elt);
		Maillon<E> current = head;

		if(index == 0) {
			ajout.setNext(head);
			head = ajout;
			return true;
		}
		
		if(head == null)
			return false;
		
		while(--index != 0) {
			current = current.getNext();
			if(current == null)
				return false;
		}
		
		ajout.setNext(current.getNext());
		current.setNext(ajout);
		
		return true;
	}
	
	/** Test d'égalité au sens du contenu de la liste
	 * @param l la liste dont on doit tester le contenu
	 * @return true si les listes ont même longueur, et les maillons sont égaux deux à deux), false sinon
	 */
	@Override
	public boolean equals(Object o) {
		if(o instanceof Iterable<?>) {
			Iterable<?> l = (Iterable<?>) o;
			
			Iterator<E> it = iterator();
			Iterator<?> it_l = l.iterator();

			while(it.hasNext()) {
				if(!it_l.hasNext())
					return false;
				if(!it.next().equals(it_l.next()))
					return false;
			}
			
			if(it_l.hasNext())
				return false;
			
			return true;
		}
		
		return false;
	}
	
	/** Fonction hashCode
	 * @return le hashCode associé
	 */
	@Override
	public int hashCode() {
		int res = 1;
		Iterator<E> it = iterator();
		
		while(it.hasNext())
			res = 31 * res + it.next().hashCode();
		return res;
	}
	
	/** Représentation de la chaine sous forme de chaine de caractère
	 * @return une chaine de caractère représentant la liste chainée
	 */
	@Override	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Iterator<E> it = iterator();
		String separator = "";
		while(it.hasNext()) {
			sb.append(separator);
			separator = "->";
			sb.append(it.next());
		}
		
		return "[" + sb.toString() + "]";
	}
	
	/** Obtention d'un itérateur pour parcourir la liste
	 * @return un nouvel itérateur sur la liste
	 */
	@Override
	public Iterator<E> iterator() {
		return new ListeIterator<E>();
	}
}
