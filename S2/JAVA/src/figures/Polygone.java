package figures;

import java.util.ArrayList;

import listes.CollectionListe;
import points.Point2D;
import points.Vecteur2D;

/** Classe Polygone h�riti�re de la classe abstraite Figure
 * @author K�vin Cocchi
 */
public class Polygone extends AbstractFigure {

	protected CollectionListe<Point2D> listePoints = null;

	// Constructeurs --------------------------------------------------------
	
	/** Constructeur par d�faut : polygone sans point */
	public Polygone() {
		listePoints = new CollectionListe<>();
	}
	
	/** Constructeur valu� : polygone � un point
	 * @param p un point
	 */
	public Polygone(Point2D p) {
		listePoints = new CollectionListe<>();
		listePoints.add(p);
	}

	/** Constructeur valu� : construit un rectangle � partir de 2 points
	 * @param p1 et p2 deux points
	 */
	public Polygone(Point2D p1, Point2D p2) {
		listePoints = new CollectionListe<>();
		listePoints.add(p1);
		listePoints.add(p2);
	}

	/** Constructeur valu� : construit un rectangle � partir d'une liste de points
	 * @param vp une liste de points
	 */
	public Polygone(ArrayList<Point2D> vp) {
		listePoints = new CollectionListe<>();
		listePoints.addAll(vp);
	}
	
	/** Constructeur de copie
	 * @param f le polygone � copier
	 */
	public Polygone(Polygone f) {
		listePoints = new CollectionListe<>(f.listePoints);
	}

	
	// M�thodes de classe ---------------------------------------------------

	/** Ajout d'un point au polygone
	 * @param x l'abscisse du point
	 * @param y l'ordonn�e du point
	 */
	public void ajouter(double x, double y) { ajouter(new Point2D(x, y)); }

	/** Ajout d'un point au polygone
	 * @param p le point � ajouter
	 */
	public void ajouter(Point2D p) { listePoints.add(p); }

	/** Suppression d'un point du polygone
	 * @param p le point � supprimer
	 * @return true si le point a �t� supprim�, false sinon
	 */
	public boolean supprimer(Point2D p) { return listePoints.remove(p); }

	/** Suppression des points du polygone
	 */
	public void supprimer() { listePoints = null; }
	
	
	// Impl�mentation de Figure ---------------------------------------------

	/** Impl�mentation Figure -> D�placement du rectangle
	 * @param dx d�placement suivant x
	 * @param dy d�placement suivant y
	 * @return une r�f�rence vers la figure d�plac�e
	 */
	@Override
	public Figure deplace(double dx, double dy) {
		for(Point2D point : listePoints)
			point.deplace(dx, dy);
		return this;
	}
	
	/** Impl�mentation Figure -> Affichage du contenu
	 * @return une chaine repr�sentant le polygone
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder(super.toString());
		String separator = "";
		
		for(Point2D point : listePoints) {
			result.append(separator);
			separator = ", ";
			result.append(point.toString());
		}
		
		return result.toString();
	}

	/** Impl�mentation Figure -> Test de contenu
	 * @param p point � tester
	 * @return true si le point est contenu dans le polygone, false sinon
	 */
	@Override
	public boolean contient(Point2D p) {
		// R�sultat initial
		boolean result = true;
		
		// Conversion en un Array<Point2D>
		Point2D[] points = new Point2D[listePoints.size()];
		points = listePoints.toArray(new Point2D[listePoints.size()]);

		// Vecteurs initiaux
		Vecteur2D v1 = new Vecteur2D(p, points[0]);
		Vecteur2D v2 = new Vecteur2D(p, points[1]);

		// Premier produit vectoriel
		double crossp = v1.crossProductN(v2);

		// Signe produit vectoriel initial
		double signInit = crossp >= 0 ? 1 : -1;

		// Produits vectoriels suivants
		double sign;

		// Parcours des points du polygone � la recherche d'un changement
		// de signe du produit vectoriel
		for(int i = 1; i < points.length; i++) {
			v1 = v2;
			v2 = new Vecteur2D(p, points[(i + 1) % points.length]);

			crossp = v1.crossProductN(v2);
			sign = crossp >= 0 ? 1 : -1;

			if(sign != signInit) {
				result = false;
				break;
			}
		}

		return result;
	}

	/** Impl�mentation Figure -> Accesseur en lecture du barycentre
	 * @return renvoie le barycentre du polygone
	 */
	@Override
	public Point2D getCentre() {
		if(listePoints == null)
			return null;

		// Conversion en un Array<Point2D>
		Point2D[] points = new Point2D[listePoints.size()];
		points = listePoints.toArray(new Point2D[listePoints.size()]);

		double cx = 0;
		double cy = 0;
		int j;

		double factor = 0;
		for(int i = 0; i < listePoints.size(); i++) {
			j = (i + 1) % listePoints.size();
			factor = (points[i].getX() * points[j].getY()
					- points[j].getX() * points[i].getY());
			cx += (points[i].getX() + points[j].getX()) * factor;
			cy += (points[i].getY() + points[j].getY()) * factor;
		}
		factor = 1.0 / (6.0 * aire());
		
		return new Point2D(Math.abs(cx*factor), Math.abs(cy*factor));
	}

	/** Impl�mentation Figure -> Calcul de l'aire
	 * @return l'aire couverte par le polygone
	 */
	@Override
	public double aire() {
		double aire = 0;

		// Conversion en un Array<Point2D>
		Point2D[] points = new Point2D[listePoints.size()];
		points = listePoints.toArray(new Point2D[listePoints.size()]);
		
		for(int i = 1; i < points.length - 1; i++) {
			Vecteur2D v1 = new Vecteur2D(points[0], points[i]);
			Vecteur2D v2 = new Vecteur2D(points[0], points[i+1]);
			aire += Math.abs(v1.crossProductN(v2)) / 2;
		}

		return aire;
	}

	/** Impl�mentation Figure -> Comparaison de deux polygones
	 * @return true si le polygone est le m�me, false sinon
	 * @see Figure#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Polygone))
			return false;
		
		Polygone other = (Polygone) o;
		
		if(listePoints.size() != other.listePoints.size())
			return false;
		
		for(Point2D p : listePoints) {
			boolean found = false;
			for(Point2D po : other.listePoints) {
				if(p.equals(po)) {
					found = true;
					break;
				}
			}
			if(!found)
				return false;
		}
		
		return true;
	}

	@Override
	protected boolean equals(Figure f) {
		return equals(f);
	}
	
	/** Impl�mentation Figure -> hashCode d'un polygone
	 * @return le hashcode du polygone
	 * @see Figure#hashCode(java.lang.Object)
	 */
    @Override
    public int hashCode() {
        int result = 17;
		for(Point2D p : listePoints)
        	result = 31 * result + p.hashCode();
        return result;
    }
}
