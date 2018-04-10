/**
 * 
 */
package listes;


import java.util.Iterator;
import java.util.NoSuchElementException;

import com.sun.xml.internal.bind.v2.runtime.RuntimeUtil.ToStringAdapter;


/**
 * @author chenzeyu
 *
 */
public class Liste<E> implements IListe<E> {
    
	protected Maillon<E> head = null;
	
	/** Constructeur par défaut d'une liste */
    public Liste () {
		head = null;
	}
    
    
    /** Constructeur de copie d'une liste
	 * @param l la liste à copier
	 */
	public Liste (IListe<E> l)
	{
		this();
		for(E elt : l){
			add(elt);
		}
	}
	protected void finalize () {
		
	}
	
	private static class Maillon<E>{
		private E data;
	    private Maillon<E> next;
	    public Maillon(E e){
	    	data=e;
	    	next=null;
	    }
		public E getData() {
			return data;
		}
		public void setData(E data) {
			this.data = data;
		}
		public Maillon<E> getNext() {
			return next;
		}
		public void setNext(Maillon<E> next) {
			this.next = next;
		}
		
	}
	
	private class ListeIterator<F> implements Iterator<F>{
        private Maillon<F> current = null;
        private F element;
        private boolean nextCalled;
        private Maillon<F> last = null;
        private Maillon<F> penultimate = null;
        public  ListeIterator() {
			// TODO Auto-generated constructor stub
        	current =(Maillon<F>) head;
        	element = null;
        	penultimate = null;
        	last = null;
        	nextCalled=false;
		}
        
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return (current !=null);
		}

		@Override
		public F next() throws NullPointerException {
			if (current == null)
			{
				throw new NoSuchElementException("liste:ListeIterator:next()");
			}
			// TODO Auto-generated method stub
			penultimate = last;
			last = current;
			element = current.data;
			current=current.next;
			nextCalled=true;
			return element;
		}

		@Override
		public void remove() throws NullPointerException{
			// TODO Auto-generated method stub
			if(nextCalled==false)
			{
				throw new NullPointerException("remove : next not called");
			}
			if(last == head)
			{
				head = head.next;
				last = null;
			}
			else {
				if(penultimate != null)
				{
					penultimate.next=current;
					last=penultimate;
					penultimate=null;
				}
				else {
					throw new NullPointerException("remove : next not called");
				}
			}
			 
		}
		
	}
	
	/* (non-Javadoc)
	 * @see listes.IListe#add(java.lang.Object)
	 */
	@Override
	public void add(E e) throws NullPointerException {
		// TODO Auto-generated method stub
		if (e == null)
			throw new NullPointerException("elt is null.");
		Maillon<E> ajout = new Maillon<E> (e);
		Maillon<E> last = null;
		Maillon<E> current = head;
		Iterator<E> it = iterator();
		 
        	if (head==null)
        	{
        		head = ajout;
        	}
        	else {
				while(it.hasNext())
				{
					last = current;
					current = current.getNext();
					it.next();
				}
				last.setNext(ajout);
			}
        
	}

	/* (non-Javadoc)
	 * @see listes.IListe#insert(java.lang.Object)
	 */
	@Override
	public void insert(E e) throws NullPointerException {
		// TODO Auto-generated method stub
	    if(e !=null)
	    {
	    	Maillon<E> p = new Maillon<E>(e);
	    	p.next = head;
	    	head=p;
	    }
	    else 
	    {
	    	throw new NullPointerException("insertion ds liste vide, index invalide failed");
	    }
	}

	/** Insertion d'un élément à la (index+1)-ième place
	 * @param elt l'élément à insérer
	 * @param index l'index de l'élément à insérer
	 * @return true si l'élément a pu être inséré à l'index voulu, false sinon
	 * @throws NullPointerException si l'on tente d'insérer un élément null
	 */
	@Override
	public boolean insert(E e, int index) {
		// TODO Auto-generated method stub
		if(e == null)
			return false;
		if( index == 0)
		{
			insert(e);
			return true;
		}
		Maillon<E> p = head;
		for(int i=0;i<(index-1) && p!=null;i++)
		{
			p=p.next;
		}
		if(p==null)
		{
			return false;
		}
		Maillon<E> nouveau = new Maillon<E>(e);
		nouveau.next = p.next;
		p.next=nouveau;
		return true;
		
	}
	
	/** Test d'égalité au sens du contenu de la liste
	 * @param l la liste dont on doit tester le contenu
	 * @return true si les listes ont même longueur, et les maillons sont égaux deux à deux), false sinon
	 */
    @Override	    
	public boolean equals(Object obj) {
		if(obj instanceof Iterable<?>){
			Iterable<?> l = (Iterable<?>) obj;
			Iterator<E> it = iterator();
			Iterator<?> it_l = l.iterator();
			
			while (it.hasNext()) {
				if(!it_l.hasNext())
					return false;
				if(!it.next().equals(it_l.next()))
                    return false;				
			}
			
			if(it_l.hasNext())
				return false;
	
			return true;
			
		}
		else {
			return false;
		}
	}
    /** Fonction hashCode
	 * @return le hashCode associé
	 */
    @Override
	public int hashCode() {
		// TODO Auto-generated method stub
		int res =1;
		Iterator<E> it = iterator();
		
		while (it.hasNext()) {
			res = 31 * res + it.next().hashCode();
	    }
		return res;
	}
    /** Représentation de la chaine sous forme de chaine de caractère
	 * @return une chaine de caractère représentant la liste chainée
	 */
    public String toString()
    {
    	StringBuilder sBuilder = new StringBuilder();
        Iterator<E> it = iterator();
        String sp ="";
        while (it.hasNext()) {
			sBuilder.append(sp);
			sp="->";
		    sBuilder.append(it.next());
		}
	    return "[" + sBuilder.toString() + "]";
    	
    }
   @Override
	public boolean empty() {
		// TODO Auto-generated method stub
		return head==null;
	}
	/* (non-Javadoc)
	 * @see listes.IListe#iterator()
	 */
	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return new ListeIterator<E>();
	}

}
