package figures;

import points.Point2D;

/** Classe Cercle héritière de la classe abstraite Figure
 * @author Kévin Cocchi
 */
public class Cercle extends AbstractFigure {
	
	protected Point2D centre = new Point2D();
	protected double rayon;

	// Constructeurs --------------------------------------------------------
	
	/** Constructeur par défaut : cercle de centre (0,0) et de rayon 1 */
	public Cercle() {
		this(new Point2D(), 0.0);
	}
	
	/** Constructeur valué : construit un cercle à partir d'une abscisse, d'une ordonnée et d'un rayon
	 * @param x abscisse du centre
	 * @param y ordonnée du centre
	 * @param r rayon du cercle
	 */
	public Cercle(double x, double y, double r) {
		this(new Point2D(x, y), r);
	}
	
	/** Constructeur valué : construit un cercle à partir d'un rayon et d'un point
	 * @param p centre du cercle
	 * @param r rayon du cercle
	 */
	public Cercle(Point2D p, double r) {
		centre = new Point2D(p);
		rayon = r;
	}
	
	/** Constructeur de copie
	 * @param c le cercle à copier
	 */
	public Cercle(Cercle c) {
		this(c.centre, c.rayon);
	}

	
	// Accessseurs ----------------------------------------------------------
	
	/** Accesseur en lecture au rayon du cercle
	 * @return le rayon du cercle
	 */
	public double getRayon() { return rayon; }
	
	/** Accesseur en écriture au rayon du cercle
	 * @param le rayon du cercle
	 */
	public void setRayon(double rayon) { this.rayon = rayon; }

	
	// Implémentation de Figure ---------------------------------------------

	/** Implémentation Figure -> Déplacement du cercle
	 * @param dx déplacement suivant x
	 * @param dy déplacement suivant y
	 * @return une référence vers la figure déplacée
	 */
	@Override
	public Figure deplace(double dx, double dy) {
		centre.deplace(dx, dy);
		return this;
	}
	
	/** Implémentation Figure -> Affichage du contenu
	 * @return une chaine représentant le cercle
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder(super.toString());

		result.append("x = " + centre.getX());
		result.append(" y = " + centre.getY());
		result.append(", r = " + rayon);
		
		return result.toString();
	}

	/** Implémentation Figure -> Test de contenu
	 * @param p point à tester
	 * @return true si le point est contenu dans le triangle, false sinon
	 */
	@Override
	public boolean contient(Point2D p) {
		return ( Math.pow(p.getX() - centre.getX(), 2) + Math.pow(p.getY() - centre.getY(), 2) ) < rayon*rayon;
	}

	/** Implémentation Figure -> Accesseur en lecture du barycentre
	 * @return renvoie le barycentre du cercle
	 */
	@Override
	public Point2D getCentre() {
		return centre;
	}

	/** Implémentation Figure -> Calcul de l'aire
	 * @return l'aire couverte par le cercle
	 */
	@Override
	public double aire() {
		return Math.PI*rayon*rayon;
	}

	/** Implémentation Figure -> Comparaison de deux cercles
	 * @return true si le cercle est le même, false sinon
	 * @see Figure#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Cercle))
			return false;
		
		Cercle other = (Cercle) o;
		return getCentre().equals(other.getCentre())
				&& getRayon() == other.getRayon();
	}

	@Override
	protected boolean equals(Figure f) {
		return equals(f);
	}
	
	/** Implémentation Figure -> hashCode d'un cercle
	 * @return le hashcode du cercle
	 * @see Figure#hashCode(java.lang.Object)
	 */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + centre.hashCode();
        result = 31 * result + Double.valueOf(rayon).hashCode();
        return result;
    }
}
