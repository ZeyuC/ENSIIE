package figures;

import points.Point2D;

/**
 * Classe abstraite Figure Contient une donn�es concr�te : le nom de la figure (
 * {@link #nom} )
 * <ul>
 * <li>des m�thodes d'instance</li>
 * <ul>
 * <li>concr�tes
 * <ul>
 * <li>un constructeur avec un nom : {@link #AbstractFigure(String)}</li>
 * <li>un accesseur pour ce nom : {@link #getNom()}</li>
 * <li>la m�thode toString pour afficher ce nom {@link #toString()}</li>
 * <li>{@link #distanceToCentreOf(Figure)}</li>
 * </ul>
 * <li>abstraites
 * <ul>
 * <li>{@link #deplace(double,double)}</li>
 * <li>{@link #contient(Point2D)}</li>
 * <li>{@link #getCentre()}</li>
 * <li>{@link #aire()}</li>
 * </ul>
 * </ul>
 * <li>des m�thodes de classes</li>
 * <ul>
 * <li>concr�tes</li>
 * <ul>
 * <li>{@link #distanceToCentre(Figure,Figure)}</li>
 * </ul>
 * </ul>
 * </ul>
 * @author David Roussel
 */
public abstract class AbstractFigure implements Figure {
	
	protected String nom;

	/** Constructeur par d�faut d'une figure */
	protected AbstractFigure() {
		nom = getClass().getSimpleName();
	}

	/** Constructeur par d�faut d'une figure
	 * @param unNom chaine de caract�re pour initialiser le nom de la figure
	 */
	protected AbstractFigure(String unNom) {
		nom = unNom;
	}

	/**
	 * @return le nom
	 * @see figures.Figure#getNom()
	 */
	@Override
	public String getNom() {
		return nom;
	}

	/* 
	 * (non-Javadoc)
	 * @see figures.Figure#deplace(double, double)
	 */
	@Override
	public abstract Figure deplace(double dx, double dy);

	/*
	 * (non-Javadoc)
	 * @see figures.Figure#toString()
	 */
	@Override
	public String toString() {
		return (nom + " : ");
	}

	/*
	 * (non-Javadoc)
	 * @see figures.Figure#contient(points.Point2D)
	 */
	@Override
	public abstract boolean contient(Point2D p);

	/*
	 * (non-Javadoc)
	 * @see figures.Figure#getCentre()
	 */
	@Override
	public abstract Point2D getCentre();

	/*
	 * (non-Javadoc)
	 * @see figures.Figure#aire()
	 */
	@Override
	public abstract double aire();

	/**
	 * Comparaison de deux figures en termes de contenu
	 * @return true si f est du m�me types que la figure courante et qu'elles
	 * ont un contenu identique
	 */
	protected abstract boolean equals(Figure f);

	/**
	 * Comparaison de deux figures, on ne peut pas v�rifier grand chose pour
	 * l'instant � part la classe et le nom
	 * @note impl�mentation partielle qui ne v�rifie que null/this/et l'�galit�
	 * de classe
	 * @see figures.Figure#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		// TODO remplacer par l'impl�metation
		return false;
	}

	// /**
	// * Hashcode d'une figure (impl�mentation partielle bas�e sur le nom d'une
	// * figure) --> Non utilis�
	// * @see java.lang.Object#hashCode()
	// */
	// @Override
	// public int hashCode()
	// {
	// final int prime = 31;
	// int result = 1;
	// result = (prime * result) + ((nom == null) ? 0 : nom.hashCode());
	// return result;
	// }
}
