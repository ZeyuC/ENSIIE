package figures;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import listes.CollectionListe;
import points.Point2D;

public class Groupe implements Collection<Figure>, Figure {
	
	protected CollectionListe<Figure> figures = new CollectionListe<>();

	// Constructeurs --------------------------------------------------------

	/** Constructeur par d�faut : groupe sans figure */
	public Groupe() {
		figures = new CollectionListe<>();
	}

	/** Constructeur valu� : construit un groupe � partir d'une liste de figures
	 * @param fg une liste de figures
	 */
	public Groupe(ArrayList<Figure> fg) {
		figures = new CollectionListe<>();
		figures.addAll(fg);
	}

	/** Constructeur de copie
	 * @param g le groupe � copier
	 */
	public Groupe(Groupe g) {
		figures = new CollectionListe<>();
		figures.addAll(g.figures);
	}

	
	// M�thodes de classe ---------------------------------------------------

	@Override
	public String getNom() {
		return "Groupe";
	}

	/** Ajout d'une figure au groupe
	 * @param f la figure � ajouter
	 * @return true si la figure a �t� ajout�e, false sinon
	 */
	@Override
	public boolean add(Figure f) {
		return figures.add(f);
	}

	/** Ajout d'une collection de figures au groupe
	 * @param fc la collection de figures � ajouter
	 * @return true si la collection de figures a �t� ajout�e, false sinon
	 */
	@Override
	public boolean addAll(Collection<? extends Figure> fc) {
		return figures.addAll(fc);
	}

	/** Suppression des figures du groupe
	 */
	@Override
	public void clear() {
		figures.clear();
	}

	/** V�rification de l'appartenance d'une figure au groupe
	 * @param f la figure � tester
	 * @return true si la figure appartient au groupe, false sinon
	 */
	@Override
	public boolean contains(Object o) {
		return figures.contains(o);
	}

	/** V�rification de l'appartenance de figures au groupe
	 * @param fc la collection de figures � tester
	 * @return true si toutes les figures appartiennent au groupe, false sinon
	 */
	@Override
	public boolean containsAll(Collection<?> fc) {
		return figures.containsAll(fc);
	}

	/** V�rifie si le groupe est vide
	 * @return true si le groupe est vide, false sinon
	 */
	@Override
	public boolean isEmpty() {
		return figures.isEmpty();
	}

	/** Obtention d'un it�rateur pour parcourir le groupe
	 * @return un nouvel it�rateur sur le groupe
	 */
	@Override
	public Iterator<Figure> iterator() {
		return figures.iterator();
	}

	/** Suppression d'une figure du groupe
	 * @param f la figure � supprimer
	 * @return true si la figure a �t� supprim�e, false sinon
	 */
	@Override
	public boolean remove(Object f) {
		return figures.remove(f);
	}

	/** Suppression d'une collection de figures du groupe
	 * @param fc la collection de figures � supprimer
	 * @return true si la collection de figures a �t� supprim�e, false sinon
	 */
	@Override
	public boolean removeAll(Collection<?> fc) {
		return figures.removeAll(fc);
	}

	/** Suppression des figures d'une collection du groupe
	 * @param fc la collection de figures � supprimer
	 * @return true si la collection de figures a �t� supprim�e, false sinon
	 */
	@Override
	public boolean retainAll(Collection<?> fc) {
		return figures.retainAll(fc);
	}

	/** Calcule de la taille d'un groupe de figures
	 * @return la taille du groupe
	 */
	@Override
	public int size() {
		return figures.size();
	}

	/** Conversion d'un groupe en un Array
	 * @return un Array qui d�crit le groupe
	 */
	@Override
	public Object[] toArray() {
		return figures.toArray();
	}

	/** Conversion d'un groupe en un Array
	 * @param un Array auquel ajouter les �l�ments
	 * @return un Array qui d�crit le groupe
	 */
	@Override
	public <T> T[] toArray(T[] arg0) {
		return figures.toArray(arg0);
	}
	
	
	// Impl�mentation de Figure ---------------------------------------------

	/** Impl�mentation Figure -> D�placement du groupe
	 * @param dx d�placement suivant x
	 * @param dy d�placement suivant y
	 * @return une r�f�rence vers le groupe d�plac�
	 */
	@Override
	public Figure deplace(double dx, double dy) {
		for(Figure f : figures)
			f.deplace(dx, dy);
		return this;
	}
	
	/** Impl�mentation Figure -> Affichage du contenu
	 * @return une chaine repr�sentant le groupe de figures
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("Groupe : ");
		String separator = "\n";

		for(Figure f : figures) {
			result.append(separator);
			result.append(f.toString());
		}
		
		return result.toString();
	}

	/** Impl�mentation Figure -> Test de contenu
	 * @param p point � tester
	 * @return true si le point est contenu dans le groupe, false sinon
	 */
	@Override
	public boolean contient(Point2D p) {
		boolean res = false;
		for(Figure f : figures)
			res = f.contient(p) || res;
		return res;
	}

	/** Impl�mentation Figure -> Accesseur en lecture du barycentre
	 * @return renvoie le barycentre du groupe
	 */
	@Override
	public Point2D getCentre() {
		double x = 0;
		double y = 0;
		int count = 0;
		
		for(Figure f : figures) {
			x += f.getCentre().getX();
			y += f.getCentre().getY();
			count++;
		}
		
		if(count != 0)
			return new Point2D(x/count, y/count);
		return new Point2D();
	}
	
	/** Impl�mentation Figure -> Calcul de l'aire
	 * @return l'aire couverte par le groupe de figures
	 */
	@Override
	public double aire() {
		double aire = 0;
		for(Figure f : figures)
			aire += f.aire();
		return aire;
	}

	/** Impl�mentation Figure -> Comparaison de deux polygones
	 * @return true si le polygone est le m�me, false sinon
	 * @see Figure#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Groupe))
			return false;
		
		Groupe other = (Groupe) o;
		
		if(size() != other.size())
			return false;
		
		for(Figure f : figures) {
			boolean found = false;
			for(Figure fo : other.figures) {
				if(f.equals(fo)) {
					found = true;
					break;
				}
			}
			if(!found)
				return false;
		}
		
		return true;
	}
	
	/** Impl�mentation Figure -> hashCode d'un polygone
	 * @return le hashcode du polygone
	 * @see Figure#hashCode(java.lang.Object)
	 */
    @Override
    public int hashCode() {
        int result = 17;
		for(Figure f : figures)
        	result = 31 * result + f.hashCode();
        return result;
    }
}
