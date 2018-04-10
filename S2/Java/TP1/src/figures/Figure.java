package figures;

import points.Point2D;

/**
 * Interface des figures
 * @author David Roussel
 */
public interface Figure extends Cloneable {
	/** Accesseur en lecture pour le nom de la figure
	 * @return une chaine contenant le nom de la figure
	 */
	public abstract String getNom();

	/** M�thode abstraite pour le d�placement de la figure
	 * @param dx d�placement selon l'axe des X
	 * @param dy d�placement selon l'axe des Y
	 * @return renvoie une r�f�rence vers la figure
	 * @see Point2D#deplace(double, double)
	 */
	public abstract Figure deplace(double dx, double dy);

	/** Affichage contenu
	 * @return une chaine de caract�re repr�sentant la figure
	 */
	@Override
	public abstract String toString();

	/** Teste si le point pass� en argument est contenu � l'int�rieur de la figure
	 * @param p le point � tester
	 * @return la contenance du point � l'int�rieur de la figure
	 */
	public abstract boolean contient(Point2D p);

	/** Renvoie le centre de la figure
	 * @return renvoie le point central de la figure
	 */
	public abstract Point2D getCentre();

	/** Aire couverte par la figure
	 * @return renvoie l'aire couverte par la figure
	 */
	public abstract double aire();

	/** Distance entre les centres de la figure courante et d'une figure pass�e en argument
	 * @param f figure avec laquelle on calcule la distance entre les centres
	 * @return la distance entre les points centraux des deux figures
	 * @see #getCentre()
	 * @see Point2D#distance(Point2D, Point2D)
	 */
	public default double distanceToCentreOf(Figure f) {
		return getCentre().distance(f.getCentre());
	}

	/** Distance entre les centres de deux figures
	 * @param f1 premi�re figure
	 * @param f2 seconde figure
	 * @return la distance entre les points centraux des deux figures
	 * @see #getCentre()
	 * @see Point2D#distance(Point2D, Point2D)
	 */
	public static double distanceToCentre(Figure f1, Figure f2) {
		return f1.getCentre().distance(f2.getCentre());
	}

	/**
	 * Test d'�galit� de la figure courante avec une autre figure.
	 * Cette m�thode n'impl�mente que le test sur la nature des figures,
	 * le test sur le contenu doit �tre r�impl�ment� dans chaque sous classe,
	 * en utilisant cette m�thode pour tester la nature des figures.
	 * @param o la figure dont il faut comparer le contenu.
	 * @return true si les deux figures sont de nature identique et qu'elles ont
	 * le m�me contenu.
	 */
	@Override
	public abstract boolean equals(Object o);
}
