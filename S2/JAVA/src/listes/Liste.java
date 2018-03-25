package listes;

import java.util.Iterator;
import java.util.NoSuchElementException;

/** Classe Liste impl�mentant l'interface IListe
 * @note On consid�rera que la liste ne peut pas contenir d'�l�ment null
 * @author K�vin Cocchi
 * @param <E> le type des �l�ments de la liste
 */
public class Liste<E> implements IListe<E> {
	
	protected Maillon<E> head;
	
	/** Classe Maillon
	 * @param <E> le type des �l�ments du maillon
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
	
	/** Classe ListeIterator impl�mentant Iterator
	 * @param <E> le type des �l�ments de l'it�rateur
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
	
	/** Constructeur par d�faut d'une liste */
	public Liste() {
		head = null;
	}

	/** Constructeur de copie d'une liste
	 * @param l la liste � copier
	 */
	public Liste(IListe<E> l) {
		head = null;
		Iterator<E> it = l.iterator();
		
		while(it.hasNext())
			add(it.next());
	}

	/** Ajout d'un �l�ment en fin de liste
	 * @param elt l'�l�ment � ajouter en fin de liste
	 * @throws NullPointerException si l'on tente d'ajouter un �l�ment null
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
	
	/** Insertion d'un �l�ment en t�te de liste
	 * @param elt l'�l�ment � ajouter en t�te de liste
	 * @throws NullPointerException si l'on tente d'ins�rer un �l�ment null
	 */
	@Override
	public void insert(E elt) throws NullPointerException {
		if(elt == null)
			throw new NullPointerException("elt is null.");
		
		Maillon<E> maillon = new Maillon<E>(elt);
		maillon.setNext(head);
		head = maillon;
	}
	
	/** Insertion d'un �l�ment � la (index+1)-i�me place
	 * @param elt l'�l�ment � ins�rer
	 * @param index l'index de l'�l�ment � ins�rer
	 * @return true si l'�l�ment a pu �tre ins�r� � l'index voulu, false sinon
	 * @throws NullPointerException si l'on tente d'ins�rer un �l�ment null
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
	
	/** Test d'�galit� au sens du contenu de la liste
	 * @param l la liste dont on doit tester le contenu
	 * @return true si les listes ont m�me longueur, et les maillons sont �gaux deux � deux), false sinon
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
	 * @return le hashCode associ�
	 */
	@Override
	public int hashCode() {
		int res = 1;
		Iterator<E> it = iterator();
		
		while(it.hasNext())
			res = 31 * res + it.next().hashCode();
		return res;
	}
	
	/** Repr�sentation de la chaine sous forme de chaine de caract�re
	 * @return une chaine de caract�re repr�sentant la liste chain�e
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
	
	/** Obtention d'un it�rateur pour parcourir la liste
	 * @return un nouvel it�rateur sur la liste
	 */
	@Override
	public Iterator<E> iterator() {
		return new ListeIterator<E>();
	}
}
