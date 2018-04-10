package points;

/** Classe définissant un point du plan 2D
 * @author David Roussel
 */
public class Point2D {

	// Attributs de classe ---------------------------------------------------
	
	protected static int nbPoints = 0; // Compteur d'instances
	
	
	// Attributs d'instance --------------------------------------------------
	
	protected double x; // Abscisse
	protected double y; // Ordonnée

	/** Constante servant à comparer deux points entre eux (à {@value} près)
	 * @see #distance(Point2D)
	 * @see #distance(Point2D, Point2D)
	 */
	protected static final double epsilon = 1e-6;

	
	// Constructeurs ---------------------------------------------------------
	
	/** Constructeur par défaut. Initialise un point à l'origine du repère (0.0, 0.0)
	 */
	public Point2D() {
		this(0.0, 0.0);
	}

	/** Constructeur valué
	 * @param x l'abcisse du point à créer
	 * @param y l'ordonnée du point à créer
	 */
	public Point2D(double x, double y) {
		this.x = x;
		this.y = y;
		nbPoints++;
	}

	/** Constructeur de copie
	 * @param p le point dont il faut copier les coordonnées
	 */
	public Point2D(Point2D p) {
		this(p.x, p.y);
	}

	/**
	 * Nettoyeur avant destruction
	 */
	@Override
	protected void finalize() {
		nbPoints--;
	}

	
	// Accesseurs --------------------------------------------------------------
	
	public double getX() { return x; } // Accesseur en lecture de l'abscisse
	public double getY() { return y; } // Accesseur en lecture de l'ordonnée
	
	public static double getNbPoints() { return nbPoints; } // Accesseur en lecture du nombre de points instanciés

	public void setX(double val) { x = val; } // Accesseur en écriture de l'abscisse
	public void setY(double val) { y = val; } // Accesseur en écriture de l'ordonée
	
	/** Accesseur en lecture d'epsilon
	 * @return la valeur d'epsilon choisie pour comparer deux grandeurs à epsilon près
	 * @note on pourrait rendre la variable epsilon publique
	 */
	public static double getEpsilon() {
		return epsilon;
	}

	
	// Affichage ----------------------------------------------------------------
	
	/** Méthode nécessaire pour l'affichage
	 * @return une chaîne de caractères représentant un point
	 */
	@Override
	public String toString() {
		return new String("x = " + x + " y = " + y);
	}

	/** Déplacement d'un point point
	 * @param dx le déplacement en x
	 * @param dy le déplacement en y
	 * @return renvoie la référence vers l'instance courante (this) 
	 */
	public Point2D deplace(double dx, double dy) {
		x += dx;
		y += dy;
		return this;
	}

	
	// Méthodes de classe : opérations sur les points ----------------------------
	
	/** Calcul de l'écart en abcsisse entre deux points
	 * @param p1 le premier point
	 * @param p2 le second point
	 * @return l'écart en abcisse entre les deux points
	 */
	protected static double dx(Point2D p1, Point2D p2) {
		return (p2.x - p1.x);
	}

	/** Calcul de l'écart en ordonnée entre deux points
	 * @param p1 le premier point
	 * @param p2 le second point
	 * @return l'écart en ordonnée entre les deux points
	 */
	protected static double dy(Point2D p1, Point2D p2) {
		return (p2.y - p1.y);
	}

	/** Calcul de la distance 2D entre deux points
	 * @param p1 le premier point
	 * @param p2 le seconde point
	 * @return la distance entre les points p1 et p2
	 * @see #dx(Point2D, Point2D)
	 * @see #dy(Point2D, Point2D)
	 */
	public static double distance(Point2D p1, Point2D p2) {
		double dx = dx(p1, p2);
		double dy = dy(p1, p2);

		return (Math.hypot(dx, dy));
	}

	/** Calcul de distance 2D par rapport au point courant
	 * @param p l'autre point dont on veut calculer la distance
	 * @return la distance entre le point courant et le point p
	 * @see #distance(Point2D, Point2D)
	 */
	public double distance(Point2D p) {
		return distance(this, p);
	}

	/** Test d'égalité entre deux points 2D : identiques si leur distance est inférieure à {@link #epsilon}
	 * Cette méthode n'est utilisée que dans {@link #equals(Object)} donc n'est pas publique
	 * @param p le point dont on veut tester l'égalité par rapport au point courant
	 * @return true si les points sont plus proches que {@link #epsilon}, false sinon
	 */
	protected boolean equals(Point2D p) {
		return (distance(p) < epsilon);
	}

	/** Test d'égalité générique (hérité de la classe Object)
	 * @param o le point à tester (si c'est bien un point)
	 * @return true si les points sont plus proches que {@link #epsilon}, false sinon ou l'argument n'est pas un point
	 * Il est important d'implémenter cette version de la comparaison car lorsque de tels
	 * points seront contenus dans des conteneurs génériques comme des
	 * {@link java.util.Vector} ou des {@link listes.Liste} seule cette comparaison pourra être utilisée
	 */
	@Override
	public boolean equals(Object o) {
		if(o == null)
			return false;
		if(o == this)
			return true;
		
		// Comparaison laxiste (les points 2D et leurs héritiers)
		// if (this.getClass().isInstance(o))
		// Comparaison stricte (uniquement les Points 2D)
		if(this.getClass().equals(o.getClass()))
			return equals((Point2D) o);
		return false;
	}
}
