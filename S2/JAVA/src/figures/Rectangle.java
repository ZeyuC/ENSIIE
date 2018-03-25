package figures;

import points.Point2D;

/** Classe Rectangle héritière de la classe abstraite Figure
 * @author Kévin Cocchi
 */
public class Rectangle extends AbstractFigure {

	protected Point2D pmin = new Point2D();
	protected Point2D pmax = new Point2D();

	// Constructeurs --------------------------------------------------------
	
	/** Constructeur par défaut : rectangle en (0,0) ; (0,0) */
	public Rectangle() {
		this(new Point2D(), new Point2D());
	}
	
	/** Constructeur valué : construit un rectangle à partir de 2 abscisses et 2 ordonnées
	 * @param x1, x2 les abscisses
	 * @param y1, y2 les ordonnées
	 */
	public Rectangle(double x1, double x2, double y1, double y2) {
		this(new Point2D(x1, y1), new Point2D(x2, y2));
	}

	/** Constructeur valué : construit un rectangle à partir de 2 points
	 * @param pmin le point en bas à gauche
	 * @param pmax le point en haut à droite
	 */
	public Rectangle(Point2D pmin, Point2D pmax) {
		this.pmin = new Point2D(pmin);
		this.pmax = new Point2D(pmax);
	}
	
	/** Constructeur de copie
	 * @param r le rectangle à copier
	 */
	public Rectangle(Rectangle r) {
		this(r.pmin, r.pmax);
	}

	
	// Accessseurs ----------------------------------------------------------
	
	/** Accesseur en lecture au point en bas à gauche du rectangle
	 * @return le point en bas à gauche du rectangle
	 */
	public Point2D getMin() { return pmin; }
	
	/** Accesseur en lecture au point en haut à droite du rectangle
	 * @return le point en haut à droite du rectangle
	 */
	public Point2D getMax() { return pmax; }

	
	// Méthodes de classe ---------------------------------------------------

	/** Accès à la hauteur du rectangle
	 * @return la hauteur du rectangle
	 */
	public double hauteur() { return pmax.getY() - pmin.getY(); }

	/** Accès à la largeur du rectangle
	 * @return la largeur du rectangle
	 */
	public double largeur() { return pmax.getX() - pmin.getX(); }
	
	
	// Implémentation de Figure ---------------------------------------------

	/** Implémentation Figure -> Déplacement du rectangle
	 * @param dx déplacement suivant x
	 * @param dy déplacement suivant y
	 * @return une référence vers la figure déplacée
	 */
	@Override
	public Figure deplace(double dx, double dy) {
		pmin.deplace(dx, dy);
		pmax.deplace(dx, dy);
		return this;
	}
	
	/** Implémentation Figure -> Affichage du contenu
	 * @return une chaine représentant le rectangle
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder(super.toString());

		result.append(pmin.toString());
		result.append(", ");
		result.append(pmax.toString());
		
		return result.toString();
	}

	/** Implémentation Figure -> Test de contenu
	 * @param p point à tester
	 * @return true si le point est contenu dans le rectangle, false sinon
	 */
	@Override
	public boolean contient(Point2D p) {
		return ( p.getX() <= pmax.getX() ) && ( p.getX() >= pmin.getX() )
				&& ( p.getY() <= pmax.getY() ) && ( p.getY() >= pmin.getY() );
	}

	/** Implémentation Figure -> Accesseur en lecture du barycentre
	 * @return renvoie le barycentre du rectangle
	 */
	@Override
	public Point2D getCentre() {
		return new Point2D(pmin.getX() + largeur()/2, pmin.getY() + hauteur()/2);
	}

	/** Implémentation Figure -> Calcul de l'aire
	 * @return l'aire couverte par le rectangle
	 */
	@Override
	public double aire() {
		return largeur()*hauteur();
	}

	/** Implémentation Figure -> Comparaison de deux rectangles
	 * @return true si le rectangle est le même, false sinon
	 * @see Figure#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Rectangle))
			return false;

		Rectangle other = (Rectangle) o;
		return ( getMin().equals(other.getMin())
				&& getMax().equals(other.getMax()) )
				|| ( getMax().equals(other.getMin())
				&& getMin().equals(other.getMax()) );
	}

	@Override
	protected boolean equals(Figure f) {
		return equals(f);
	}
	
	/** Implémentation Figure -> hashCode d'un rectangle
	 * @return le hashcode du rectangle
	 * @see Figure#hashCode(java.lang.Object)
	 */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + pmin.hashCode();
        result = 31 * result + pmax.hashCode();
        return result;
    }
}
