package figures;

import points.Point2D;
import points.Vecteur2D;

/** Classe Triangle h�riti�re de la classe abstraite Figure
 * @author K�vin Cocchi
 */
public class Triangle extends AbstractFigure {
	
	protected Point2D[] points = new Point2D[3];

	// Constructeurs --------------------------------------------------------
	
	/** Constructeur par d�faut : triangle isoc�le de 1 de base et de 1 de haut � partir de l'origine */
	public Triangle() {
		points[0] = new Point2D(0.0, 0.0);
		points[1] = new Point2D(1.0, 0.0);
		points[2] = new Point2D(0.5, 1.0);
	}

	/** Constructeur valu� : construit un triangle � partir de 3 points
	 * @param p1 premier point
	 * @param p2 second point
	 * @param p3 troisi�me point
	 */
	public Triangle(Point2D p1, Point2D p2, Point2D p3) {
		points[0] = new Point2D(p1);
		points[1] = new Point2D(p2);
		points[2] = new Point2D(p3);
	}

	/** Constructeur de copie
	 * @param t le triangle � copier
	 */
	public Triangle(Triangle t) {
		this(t.points[0], t.points[1], t.points[2]);
	}

	// Accessseurs ----------------------------------------------------------
	
	/** Accesseur en lecture pour le n-i�me point
	 * @param n l'indice du point recherch�
	 * @return le n-i�me point du triangle
	 */
	public Point2D getPoint(int n) {
		if((n > (points.length - 1)) || (n < 0)) {
			System.err.println("Triangle getPoint index invalide");
			return null;
		}
		else
			return points[n];
	}

	// Impl�mentation de Figure ---------------------------------------------
	
	/** Impl�mentation Figure -> D�placement du triangle
	 * @param dx d�placement suivant x
	 * @param dy d�placement suivant y
	 * @return une r�f�rence vers la figure d�plac�e
	 */
	@Override
	public Figure deplace(double dx, double dy) {
		for(Point2D p : points)
			p.deplace(dx, dy);
		
		return this;
	}

	/** Impl�mentation Figure -> Affichage du contenu
	 * @return une chaine repr�sentant l'objet (les trois points)
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder(super.toString());
		String separator = "";
		
		for(Point2D point : points) {
			result.append(separator);
			separator = ", ";
			result.append(point.toString());
		}
		
		return result.toString();
	}

	/** Impl�mentation Figure -> Test de contenu
	 * Teste si le point pass� en argument est contenu � l'int�rieur du triangle
	 * Pour savoir si un point est contenu dans un polygone convexe
	 * il suffit d'effectuer le produit vectoriel des vecteurs
	 * reliant ce point avec deux points cons�cutifs le long du
	 * polygone, et ceci le long de chaque paire de points dans le
	 * polygone.
	 * Si on observe un changement de signe du produit vectoriel entre
	 * deux paires de vecteurs cela signifie que le point se trouve �
	 * l'ext�rieur du polygone.
	 * Contre-exemple : lorsqu'un point se trouve � l'int�rieur d'un
	 * polygone convexe la suite des produits vectoriels des paires de
	 * vecteurs ne change jamais de signe !
	 * @param p point � tester
	 * @return true si le point est contenu dans le triangle, false sinon
	 */
	@Override
	public boolean contient(Point2D p) {
		// R�sultat initial
		boolean result = true;

		// Vecteurs initiaux
		Vecteur2D v1 = new Vecteur2D(p, points[0]);
		Vecteur2D v2 = new Vecteur2D(p, points[1]);

		// premier produit vectoriel
		double crossp = v1.crossProductN(v2);

		// signe produit vectoriel initial
		double signInit = crossp >= 0 ? 1 : -1;

		// produits vectoriels suivants
		double sign;

		// parcours des points du polygone � la recherche d'un changement
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
	 * @return renvoie le barycentre du triangle
	 */
	@Override
	public Point2D getCentre() {
		double sx = 0.0;
		double sy = 0.0;
		// Somme des coordonn�es des points
		for(int i = 0; i < points.length; i++) {
			sx += points[i].getX();
			sy += points[i].getY();
		}
		// Renvoi de la moyenne de chaque coordonn�e
		return new Point2D(sx / points.length, sy / points.length);
	}

	/** Impl�mentation Figure -> Calcul de l'aire
	 * @return l'aire couverte par le triangle
	 */
	@Override
	public double aire() {
		// pour calculer l'aire d'un polygone convexe du plan XY, on utilise
		// une nouvelle fois les propri�t�s du produit vectoriel.
		// La norme du produit vectoriel repr�sente le double de l'aire
		// couverte par les deux vecteurs dont on calcule ce produit.
		// il suffit donc de faire cette somme sur tous les triangle qui
		// composent le polygone en formant des vecteurs constitu�s par des
		// couples de points cons�cutifs le long du polygone.
		// Bon tout ca c'est bien mais pour un triangle c'est plus simple :

		Vecteur2D v1 = new Vecteur2D(points[0], points[1]);
		Vecteur2D v2 = new Vecteur2D(points[0], points[2]);

		return (Math.abs(v1.crossProductN(v2)) / 2.0);
	}

	/** Impl�mentation Figure -> Comparaison de deux triangles
	 * @return true si le triangle est le m�me, false sinon
	 * @see Figure#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Triangle))
			return false;
		
		Triangle other = (Triangle) o;
		for(int i = 0; i < points.length; i++) {
			boolean found = false;
			for(int j = 0; j < points.length; j++) {
				if(points[i].equals(other.points[j])) {
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
	
	/** Impl�mentation Figure -> hashCode d'un triangle
	 * @return le hashcode du triangle
	 * @see Figure#hashCode(java.lang.Object)
	 */
    @Override
    public int hashCode() {
        int result = 17;
        for(int i = 0; i < points.length; i++)
        	result = 31 * result + points[i].hashCode();
        return result;
    }
}
