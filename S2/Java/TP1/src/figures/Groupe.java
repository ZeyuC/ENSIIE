package figures;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import listes.CollectionListe;
import points.Point2D;

public class Groupe implements Collection<Figure>, Figure {
	
	protected CollectionListe<Figure> figures = new CollectionListe<>();

	// Constructeurs --------------------------------------------------------

	/** Constructeur par défaut : groupe sans figure */
	public Groupe() {
		figures = new CollectionListe<>();
	}

	/** Constructeur valué : construit un groupe à partir d'une liste de figures
	 * @param fg une liste de figures
	 */
	public Groupe(ArrayList<Figure> fg) {
		figures = new CollectionListe<>();
		figures.addAll(fg);
	}

	/** Constructeur de copie
	 * @param g le groupe à copier
	 */
	public Groupe(Groupe g) {
		figures = new CollectionListe<>();
		figures.addAll(g.figures);
	}

	
	// Méthodes de classe ---------------------------------------------------

	@Override
	public String getNom() {
		return "Groupe";
	}

	/** Ajout d'une figure au groupe
	 * @param f la figure à ajouter
	 * @return true si la figure a été ajoutée, false sinon
	 */
	@Override
	public boolean add(Figure f) {
		return figures.add(f);
	}

	/** Ajout d'une collection de figures au groupe
	 * @param fc la collection de figures à ajouter
	 * @return true si la collection de figures a été ajoutée, false sinon
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

	/** Vérification de l'appartenance d'une figure au groupe
	 * @param f la figure à tester
	 * @return true si la figure appartient au groupe, false sinon
	 */
	@Override
	public boolean contains(Object o) {
		return figures.contains(o);
	}

	/** Vérification de l'appartenance de figures au groupe
	 * @param fc la collection de figures à tester
	 * @return true si toutes les figures appartiennent au groupe, false sinon
	 */
	@Override
	public boolean containsAll(Collection<?> fc) {
		return figures.containsAll(fc);
	}

	/** Vérifie si le groupe est vide
	 * @return true si le groupe est vide, false sinon
	 */
	@Override
	public boolean isEmpty() {
		return figures.isEmpty();
	}

	/** Obtention d'un itérateur pour parcourir le groupe
	 * @return un nouvel itérateur sur le groupe
	 */
	@Override
	public Iterator<Figure> iterator() {
		return figures.iterator();
	}

	/** Suppression d'une figure du groupe
	 * @param f la figure à supprimer
	 * @return true si la figure a été supprimée, false sinon
	 */
	@Override
	public boolean remove(Object f) {
		return figures.remove(f);
	}

	/** Suppression d'une collection de figures du groupe
	 * @param fc la collection de figures à supprimer
	 * @return true si la collection de figures a été supprimée, false sinon
	 */
	@Override
	public boolean removeAll(Collection<?> fc) {
		return figures.removeAll(fc);
	}

	/** Suppression des figures d'une collection du groupe
	 * @param fc la collection de figures à supprimer
	 * @return true si la collection de figures a été supprimée, false sinon
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
	 * @return un Array qui décrit le groupe
	 */
	@Override
	public Object[] toArray() {
		return figures.toArray();
	}

	/** Conversion d'un groupe en un Array
	 * @param un Array auquel ajouter les éléments
	 * @return un Array qui décrit le groupe
	 */
	@Override
	public <T> T[] toArray(T[] arg0) {
		return figures.toArray(arg0);
	}
	
	
	// Implémentation de Figure ---------------------------------------------

	/** Implémentation Figure -> Déplacement du groupe
	 * @param dx déplacement suivant x
	 * @param dy déplacement suivant y
	 * @return une référence vers le groupe déplacé
	 */
	@Override
	public Figure deplace(double dx, double dy) {
		for(Figure f : figures)
			f.deplace(dx, dy);
		return this;
	}
	
	/** Implémentation Figure -> Affichage du contenu
	 * @return une chaine représentant le groupe de figures
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

	/** Implémentation Figure -> Test de contenu
	 * @param p point à tester
	 * @return true si le point est contenu dans le groupe, false sinon
	 */
	@Override
	public boolean contient(Point2D p) {
		boolean res = false;
		for(Figure f : figures)
			res = f.contient(p) || res;
		return res;
	}

	/** Implémentation Figure -> Accesseur en lecture du barycentre
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
	
	/** Implémentation Figure -> Calcul de l'aire
	 * @return l'aire couverte par le groupe de figures
	 */
	@Override
	public double aire() {
		double aire = 0;
		for(Figure f : figures)
			aire += f.aire();
		return aire;
	}

	/** Implémentation Figure -> Comparaison de deux polygones
	 * @return true si le polygone est le même, false sinon
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
	
	/** Implémentation Figure -> hashCode d'un polygone
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
