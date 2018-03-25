package points;

/** Classe d�finissant un point du plan 2D
 * @author David Roussel
 */
public class Point2D {

	// Attributs de classe ---------------------------------------------------
	
	protected static int nbPoints = 0; // Compteur d'instances
	
	
	// Attributs d'instance --------------------------------------------------
	
	protected double x; // Abscisse
	protected double y; // Ordonn�e

	/** Constante servant � comparer deux points entre eux (� {@value} pr�s)
	 * @see #distance(Point2D)
	 * @see #distance(Point2D, Point2D)
	 */
	protected static final double epsilon = 1e-6;

	
	// Constructeurs ---------------------------------------------------------
	
	/** Constructeur par d�faut. Initialise un point � l'origine du rep�re (0.0, 0.0)
	 */
	public Point2D() {
		this(0.0, 0.0);
	}

	/** Constructeur valu�
	 * @param x l'abcisse du point � cr�er
	 * @param y l'ordonn�e du point � cr�er
	 */
	public Point2D(double x, double y) {
		this.x = x;
		this.y = y;
		nbPoints++;
	}

	/** Constructeur de copie
	 * @param p le point dont il faut copier les coordonn�es
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
	public double getY() { return y; } // Accesseur en lecture de l'ordonn�e
	
	public static double getNbPoints() { return nbPoints; } // Accesseur en lecture du nombre de points instanci�s

	public void setX(double val) { x = val; } // Accesseur en �criture de l'abscisse
	public void setY(double val) { y = val; } // Accesseur en �criture de l'ordon�e
	
	/** Accesseur en lecture d'epsilon
	 * @return la valeur d'epsilon choisie pour comparer deux grandeurs � epsilon pr�s
	 * @note on pourrait rendre la variable epsilon publique
	 */
	public static double getEpsilon() {
		return epsilon;
	}

	
	// Affichage ----------------------------------------------------------------
	
	/** M�thode n�cessaire pour l'affichage
	 * @return une cha�ne de caract�res repr�sentant un point
	 */
	@Override
	public String toString() {
		return new String("x = " + x + " y = " + y);
	}

	/** D�placement d'un point point
	 * @param dx le d�placement en x
	 * @param dy le d�placement en y
	 * @return renvoie la r�f�rence vers l'instance courante (this) 
	 */
	public Point2D deplace(double dx, double dy) {
		x += dx;
		y += dy;
		return this;
	}

	
	// M�thodes de classe : op�rations sur les points ----------------------------
	
	/** Calcul de l'�cart en abcsisse entre deux points
	 * @param p1 le premier point
	 * @param p2 le second point
	 * @return l'�cart en abcisse entre les deux points
	 */
	protected static double dx(Point2D p1, Point2D p2) {
		return (p2.x - p1.x);
	}

	/** Calcul de l'�cart en ordonn�e entre deux points
	 * @param p1 le premier point
	 * @param p2 le second point
	 * @return l'�cart en ordonn�e entre les deux points
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

	/** Test d'�galit� entre deux points 2D : identiques si leur distance est inf�rieure � {@link #epsilon}
	 * Cette m�thode n'est utilis�e que dans {@link #equals(Object)} donc n'est pas publique
	 * @param p le point dont on veut tester l'�galit� par rapport au point courant
	 * @return true si les points sont plus proches que {@link #epsilon}, false sinon
	 */
	protected boolean equals(Point2D p) {
		return (distance(p) < epsilon);
	}

	/** Test d'�galit� g�n�rique (h�rit� de la classe Object)
	 * @param o le point � tester (si c'est bien un point)
	 * @return true si les points sont plus proches que {@link #epsilon}, false sinon ou l'argument n'est pas un point
	 * Il est important d'impl�menter cette version de la comparaison car lorsque de tels
	 * points seront contenus dans des conteneurs g�n�riques comme des
	 * {@link java.util.Vector} ou des {@link listes.Liste} seule cette comparaison pourra �tre utilis�e
	 */
	@Override
	public boolean equals(Object o) {
		if(o == null)
			return false;
		if(o == this)
			return true;
		
		// Comparaison laxiste (les points 2D et leurs h�ritiers)
		// if (this.getClass().isInstance(o))
		// Comparaison stricte (uniquement les Points 2D)
		if(this.getClass().equals(o.getClass()))
			return equals((Point2D) o);
		return false;
	}
}
