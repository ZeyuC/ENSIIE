package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import listes.CollectionListe;

/** Classe de test de la CollectionListe en tant que Collection
 * @author David Roussel
 */
public class CollectionListeTest {
	/** La liste � tester. La nature du contenu de la liste importe peu du moment qu'il est homog�ne
	 */
	private CollectionListe<String> collection;

	/** Liste des �l�ments � ajouter � la collection
	 */
	private static String[] elements = new String[] {
		"Hello",
		"Brave",
		"New",
		"World" };

	/** Element suppl�mentaire � ajouter � la collection
	 */
	private static String extraElement = new String("Of Pain");

	/** Mise en place avant l'ensemble des tests
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {}

	/** Nettoyage apr�s l'ensemble des tests
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {}

	/** Mise en place avant chaque test
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		collection = new CollectionListe<String>();
	}

	/** Nettoyage apr�s chaque test
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		collection.clear();
		collection = null;
		System.gc();
	}

	/** Remplissage d'une collection avec les �l�ments de #elements
	 * @param collection la collection � remplir
	 */
	public static void remplissage(Collection<String> collection){
		for(String elt : elements)
			collection.add(elt);
	}

	/** Test method for {@link listes.CollectionListe#CollectionListe()}.
	 */
	@Test
	public final void testCollectionListe() {
		String testName = new String("CollectionListe<String>()");
		System.out.println(testName);

		assertNotNull(testName + " instance", collection);
		assertTrue(testName + " empty", collection.isEmpty());
		assertEquals(testName + " size == 0", 0, collection.size());
	}

	/** Test method for
	 * {@link listes.CollectionListe#CollectionListe(java.util.Collection)}.
	 */
	@Test
	public final void testCollectionListeCollectionOfE() {
		String testName = new String("CollectionListe<String>(Collection<String>)");
		System.out.println(testName);

		ArrayList<String> otherCollection = new ArrayList<String>();
		remplissage(otherCollection);

		collection = new CollectionListe<String>(otherCollection);

		assertNotNull(testName + " instance", collection);
		assertFalse(testName + " not empty", collection.isEmpty());
		assertEquals(testName + " size", elements.length, collection.size());
		
		int i = 0;
		for(String elt : collection)
			assertSame(testName + " elt[" + String.valueOf(i) + "]", elements[i++], elt);
	}

	/** Test method for {@link listes.CollectionListe#add(java.lang.Object)}.
	 */
	@Test
	public final void testAddE() {
		String testName = new String("CollectionListe<String>.add(String)");
		System.out.println(testName);

		collection.add(extraElement);

		assertEquals(testName + " size", 1, collection.size());
		Iterator<String> it = collection.iterator();
		assertTrue(testName + " iterator not empty", it.hasNext());
		assertSame(testName + " element", extraElement, it.next());
		assertFalse(testName + " iterator end", it.hasNext());
	}

	/** Test method for
	 * {@link java.util.AbstractCollection#addAll(java.util.Collection)}.
	 */
	@Test
	public final void testAddAll() {
		String testName = new String("CollectionListe<String>.addAll(Collection<String>)");
		System.out.println(testName);

		ArrayList<String> otherCollection = new ArrayList<String>();
		remplissage(otherCollection);

		collection.addAll(otherCollection);

		assertNotNull(testName + " instance", collection);
		assertFalse(testName + " not empty", collection.isEmpty());
		assertEquals(testName + " size", elements.length, collection.size());
		
		int i = 0;
		for(String elt : collection)
			assertSame(testName + " elt[" + String.valueOf(i) + "]", elements[i++], elt);
	}

	/** Test method for {@link java.util.AbstractCollection#clear()}.
	 */
	@Test
	public final void testClear() {
		String testName = new String("CollectionListe<String>.clear()");
		System.out.println(testName);
		boolean result;

		// Remplissage
		remplissage(collection);

		// Non vide apr�s remplissage
		result = collection.isEmpty();
		assertFalse(testName + " rempli", result);

		collection.clear();

		// Vide apr�s clear
		result = collection.isEmpty();
		assertTrue(testName + " effac�", result);
	}

	/** Test method for
	 * {@link java.util.AbstractCollection#contains(java.lang.Object)}.
	 */
	@Test
	public final void testContains() {
		String testName = new String("CollectionListe<String>.Contains(String)");
		System.out.println(testName);
		boolean result;

		// Recherche contenu null sur une collection vide
		result = collection.contains(null);
		assertFalse(testName + "null sur col vide", result);

		// Recherche contenu non null sur une collection vide
		result = collection.contains("Bonjour");
		assertFalse(testName + " non null sur col vide", result);

		// Remplissage
		remplissage(collection);

		// Contenu null non trouv� sur liste remplie
		result = collection.contains(null);
		assertFalse(testName + "null sur col remplie", result);

		// Recherche contenu non null non contenu sur une collection remplie
		result = collection.contains("Bonjour");
		assertFalse(testName + " non null sur col remplie", result);

		for(String elt : elements) {
			// Recherche contenu non null contenu dans collection remplie
			result = collection.contains(elt);
			assertTrue(testName + " non null sur col remplie", result);
		}
	}

	/** Test method for
	 * {@link java.util.AbstractCollection#containsAll(java.util.Collection)}.
	 */
	@Test
	public final void testContainsAll() {
		String testName = new String("CollectionListe<String>.ContainsAll(Collection<String>)");
		System.out.println(testName);
		boolean result;

		// Recherche contenu null sur une collection vide
		try {
			result = collection.containsAll(null);
			fail(testName + " null sur collection vide sans exception");
		}
		catch (NullPointerException npe) {
			// Il est normal d'obtenir une telle exception donc rien
		}

		// Remplissage autre collection dans l'ordre direct
		ArrayList<String> forwardCollection = new ArrayList<String>();
		remplissage(forwardCollection);

		// Ajout dans autre collection directe d'un elt suppl�mentaire
		ArrayList<String> forwardCollectionPlus = new ArrayList<String>(forwardCollection);
		forwardCollectionPlus.add(extraElement);

		// Recherche contenu non null sur une collection vide
		result = collection.containsAll(forwardCollection);
		assertFalse(testName + " non null sur col vide", result);

		// Remplissage autre collection dans l'ordre inverse
		ArrayList<String> reverseCollection = new ArrayList<String>();
		for(int i = elements.length - 1; i >= 0; i--)
			reverseCollection.add(elements[i]);
		
		// Ajout dans autre collection inverse d'un elt suppl�mentaire
		ArrayList<String> reverseCollectionPlus = new ArrayList<String>(reverseCollection);
		reverseCollectionPlus.add(extraElement);

		// Remplissage autre collection diff�rente
		ArrayList<String> otherCollection = new ArrayList<String>();
		otherCollection.add("Bonjour");
		otherCollection.add("Brave");
		otherCollection.add("Nouveau");
		otherCollection.add("Monde");

		// Remplissage collection
		remplissage(collection);

		CollectionListe<String> collectionPlus = new CollectionListe<String>(collection);
		collectionPlus.add(extraElement);

		// Contenu null non trouv� sur liste remplie
		try {
			result = collection.containsAll(null);
			fail(testName + "null sur col remplie sans exception");
		}
		catch (NullPointerException npe) {
			// Il est normal d'obtenir une telle exception donc rien
		}

		// Recherche contenu non null non contenu sur une collection remplie
		result = collection.containsAll(otherCollection);
		assertFalse(testName + " non null sur col remplie", result);

		// Recherche contenu identique
		result = collection.containsAll(forwardCollection);
		assertTrue(testName + " identique sur col remplie", result);
		result = collection.containsAll(reverseCollection);
		assertTrue(testName + " invers� sur col remplie", result);

		// Recherche contenu plus petit
		result = collectionPlus.containsAll(forwardCollection);
		assertTrue(testName + " plus petit identique sur col remplie", result);
		result = collectionPlus.containsAll(reverseCollection);
		assertTrue(testName + " plus petit invers� sur col remplie", result);

		// Recherche contenu plus grand
		result = collection.containsAll(forwardCollectionPlus);
		assertFalse(testName + " plus grand identique sur col remplie", result);
		result = collection.containsAll(reverseCollectionPlus);
		assertFalse(testName + " plus petit invers� sur col remplie", result);
	}

	/**
	 * Test method for {@link java.util.AbstractCollection#isEmpty()}.
	 */
	@Test
	public final void testIsEmpty()
	{
		String testName = new String("CollectionListe<String>.isEmpty()");
		System.out.println(testName);
		boolean result = collection.isEmpty();

		assertTrue(testName + " vide", result);

		// Remplissage
		remplissage(collection);

		result = collection.isEmpty();
		assertFalse(testName + " non vide", result);
	}

	/**
	 * Test method for {@link listes.CollectionListe#iterator()}.
	 */
	@Test
	public final void testIterator()
	{
		String testName = new String("CollectionListe<String>.iterator()");
		System.out.println(testName);

		// It�rateur sur liste vide
		Iterator<String> result = collection.iterator();

		assertNotNull(testName + " iterator non null", result);
		assertFalse(testName + " iterator vide", result.hasNext());

		// Remplissage
		remplissage(collection);

		// It�rateur sur liste non vide
		result = collection.iterator();
		assertNotNull(testName + " iterator non null", result);
		assertTrue(testName + " iterator vide", result.hasNext());

		for (int i = 0; i < elements.length; i++)
		{
			assertSame(testName + " iteration[" + String.valueOf(i) + "]",
					elements[i], result.next());
		}
		assertFalse(testName + " iterator termin�", result.hasNext());
	}

	/**
	 * Test method for
	 * {@link java.util.AbstractCollection#remove(java.lang.Object)}.
	 */
	@Test
	public final void testRemove()
	{
		String testName = new String("CollectionListe<String>.remove(String)");
		System.out.println(testName);

		// Retrait d'un �l�ment null sur collection vide
		boolean result = collection.remove(null);
		assertFalse(testName + " retrait elt null sur col vide", result);

		// Retrait d'un �lement non null sur collection vide
		result = collection.remove("Bonjour");
		assertFalse(testName + " retrait elt sur col vide", result);

		// Double Remplissage (pour v�rifier l'ordre des retraits)
		remplissage(collection);
		remplissage(collection);
		// collection = Hello -> Brave -> New -> World -> Hello -> Brave -> New -> World

		// Retrait d'un �l�ment null sur collection remplie
		result = collection.remove(null);
		assertFalse(testName + " retrait elt null sur col", result);

		for (String elt : elements)
		{
			// retrait de la premi�re occurrence
			result = collection.remove(elt);
			// la seconde occurrence est toujours pr�sence
			assertTrue(testName + " retrait 1ere occurrence", result);
			assertTrue(testName + " persistence 2eme occurrence",
					collection.contains(elt));

			// retrait de la seconde occurrence
			result = collection.remove(elt);
			assertTrue(testName + " retrait 2nde occurrence", result);
			assertFalse(testName + " absence 2eme occurrence",
					collection.contains(elt));

			// retrait elt non pr�sent
			result = collection.remove(elt);
			assertFalse(testName + " retrait elt non pr�sent", result);
		}
	}

	/**
	 * Test method for
	 * {@link java.util.AbstractCollection#removeAll(java.util.Collection)}.
	 */
	@Test
	public final void testRemoveAll()
	{
		String testName = new String("CollectionListe<String>.removeAll(" +
				"Collection<String>)");
		System.out.println(testName);
		boolean result;

		// Retrait collection nulle sur collection vide
		// Devrait g�n�rer un exception
		try
		{
			result = collection.removeAll(null);

			fail(testName + " retrait collection null sur collection vide " +
					"sans exception");
		}
		catch (NullPointerException npe)
		{
			// Rien, on s'attends � cette exception
		}

		// Double Remplissage autre collection
		ArrayList<String> otherCollection = new ArrayList<String>();
		remplissage(otherCollection);
		remplissage(otherCollection);

		// Retrait othercollection sur collection vide
		result = collection.removeAll(otherCollection);
		assertFalse(testName + " retrait collection sur collection vide", result);

		// Remplissage collection
		remplissage(collection);

		// Retrait collection nulle sur collection remplie
		try
		{
			result = collection.removeAll(null);

			fail(testName +  " retrait collection null sur collection remplie" +
					" sans exception");
		}
		catch (NullPointerException npe)
		{
			// Rien, on s'attends � cette exception
		}

		// Retrait otherCollection de collection même taille
		result = collection.removeAll(otherCollection);
		assertTrue(testName + " retrait collection +", result);
		result = collection.isEmpty();
		assertTrue(testName + " collection vide apr�s retrait collection +",
				result);

		// Re-remplissages
		otherCollection.clear();
		remplissage(collection);
		remplissage(otherCollection);

		CollectionListe<String> collectionPlus = new CollectionListe<String>(
				collection);
		collectionPlus.add(extraElement);

		// Retrait collection plus grande
		result = collection.removeAll(collectionPlus);
		assertTrue(testName + " retrait collection plus grande", result);
		assertTrue(testName + " col vide apr�s retrait collection plus grande",
				collection.isEmpty());

		// Retrait collection plus petite
		result = collectionPlus.removeAll(otherCollection);
		assertTrue(testName + " retrait collection plus petite", result);
		assertEquals(testName + " taille 1 apr�s retrait collection plus " +
				"petite", 1, collectionPlus.size());
	}

	/**
	 * Test method for
	 * {@link java.util.AbstractCollection#retainAll(java.util.Collection)}.
	 */
	@Test
	public final void testRetainAll()
	{
		String testName = new String("CollectionListe<String>.retainAll(" +
				"Collection<String>)");
		System.out.println(testName);
		boolean result;

		// Retain collection null sur collection vide
		// Devrait g�n�rer une exception
		try
		{
			result = collection.retainAll(null);
			fail(testName + " retainAll(null) sur collection vide sans " +
					"exception");
		}
		catch (NullPointerException npe)
		{
			// Rien, on s'attends � cette exception
		}

		// Remplissage otherCollection
		ArrayList<String> otherCollection = new ArrayList<String>();
		remplissage(otherCollection);

		// Retain otherCollection sur collection vide
		result = collection.retainAll(otherCollection);
		assertFalse(testName + " retainAll elements sur colelction vide", result);

		// Remplissage collection
		collection.addAll(otherCollection);
		collection.add(extraElement);

		// Retain null collection sur collection remplie
		try
		{
			result = collection.retainAll(null);

			fail(testName + " retainAll(null) sur collection remplie sans " +
					"exception");
		}
		catch (NullPointerException npe)
		{
			// Rien, on s'attends � cette exception
		}

		// Retain otherCollection sur collection remplie + extra element
		result = collection.retainAll(otherCollection);
		assertTrue(testName + " retainAll(other) sur col. remplie+", result);
		assertEquals(testName + " retainAll(other) sur col. remplie+ size",
				otherCollection.size(), collection.size());
		Iterator<String> it1 = collection.iterator();
		Iterator<String> it2 = otherCollection.iterator();
		for (; it1.hasNext() && it2.hasNext();)
		{
			assertSame(testName + " retainAll test same elts", it1.next(),
					it2.next());
		}
	}

	/**
	 * Test method for {@link listes.CollectionListe#size()}.
	 */
	@Test
	public final void testSize()
	{
		String testName = new String("CollectionListe<String>.size()");
		System.out.println(testName);
		int result;

		// Taille nulle sur collection vide
		result = collection.size();
		assertEquals(testName + " taille nulle sur collection vide", 0, result);

		// Remplissage
		remplissage(collection);

		// Taille  apr�s remplissage
		result = collection.size();
		assertEquals(testName + " taille collection apr�s remplissage",
				elements.length, result);
	}

	/**
	 * Test method for {@link java.util.AbstractCollection#toArray()}.
	 */
	@Test
	public final void testToArray()
	{
		String testName = new String("CollectionListe<String>.toArray()");
		System.out.println(testName);
		Object[] result;

		// toArray sur collection vide
		result = collection.toArray();
		assertEquals(testName + " toArray collection vide", 0, result.length);

		// Remplissage
		remplissage(collection);

		// toArray apr�s remplissage
		result = collection.toArray();
		assertEquals(testName + " toArray apr�s remplissage",
				elements.length, result.length);
		for (int i = 0; i < elements.length; i++)
		{
			assertSame(testName + " element[" + String.valueOf(i) + "]",
					elements[i], result[i]);
		}
	}

	/**
	 * Test method for {@link java.util.AbstractCollection#toArray(T[])}.
	 */
	@Test
	public final void testToArrayTArray()
	{
		String testName = new String("CollectionListe<String>.toArray(T[])");
		System.out.println(testName);
		String[] result;

		// toArray sur collection vide
		result = collection.toArray(new String[0]);
		assertEquals(testName + " collection vide", 0, result.length);

		// Remplissage
		remplissage(collection);

		// toArray apr�s remplissage
		result = collection.toArray(new String[0]);
		assertEquals(testName + " apr�s remplissage",
				elements.length, result.length);
		for (int i = 0; i < elements.length; i++)
		{
			assertSame(testName + " element[" + String.valueOf(i) + "]",
					elements[i], result[i]);
		}
	}

	/**
	 * Test method for {@link java.util.AbstractCollection#toString()}.
	 */
	@Test
	public final void testToString()
	{
		String testName = new String("CollectionListe<String>.toString()");
		System.out.println(testName);
		String result;

		// Remplissage
		remplissage(collection);

		String expected = new String("[Hello, Brave, New, World]");

		result = collection.toString();

		assertEquals(testName, expected, result);
	}

	/**
	 * Test method for {@link listes.CollectionListe#equals(java.lang.Object)}.
	 */
	@Test
	public final void testEqualsObject()
	{
		String testName = new String("CollectionListe<String>.equals(Object)");
		System.out.println(testName);
		boolean result;

		// Equals sur null
		result = collection.equals(null);
		assertFalse(testName + " null object", result);

		// Remplissage
		remplissage(collection);

		// Equals sur this
		result = collection.equals(collection);
		assertTrue(testName + " this", result);

		// Equals sur objet de nature diff�rente
		result = collection.equals(new Object());
		assertFalse(testName + " this", result);

		// Equals sur CollectionListe non semblable
		CollectionListe<String> otherCollectionListe = new CollectionListe<String>(
				collection);
		collection.add(extraElement);
		result = collection.equals(otherCollectionListe);
		assertFalse(testName + " otherCollectionListe non semblable", result);

		// Equals sur CollectionListe semblable
		otherCollectionListe.add(extraElement);
		result = collection.equals(otherCollectionListe);
		assertTrue(testName + " otherCollectionListe semblable", result);

		// Equals sur Collection non semblable
		collection.remove(extraElement);
		ArrayList<String> otherCollection = new ArrayList<String>(collection);
		collection.add(extraElement);
		result = collection.equals(otherCollection);
		assertFalse(testName + " otherCollection non semblable", result);

		// Equals sur Collection semblable
		// CollectionListe<E> peut se comparer � toute Collection<E>
		otherCollection.add(extraElement);
		result = collection.equals(otherCollection);
		assertTrue(testName + " equals direct", result);
		// ArrayList<E> ne peut se comparer qu'� une autre List<E>
		boolean resultInverse = otherCollection.equals(collection);
		assertFalse(testName + " equals inverse", resultInverse);
	}

	/**
	 * Test method for {@link listes.CollectionListe#hashCode()}.
	 */
	@Test
	public final void testHashCode()
	{
		String testName = new String("CollectionListe<String>.equals(Object)");
		System.out.println(testName);
		int result1, result2;

		ArrayList<String> otherCollection = new ArrayList<String>();

		// hashCode collection vide = 1
		result1 = collection.hashCode();
		result2 = otherCollection.hashCode();
		assertEquals(testName + " hashCode collection vide", 1, result1);
		assertEquals(testName + " hashCodes collections vides", result2, result1);

		// Remplissages
		remplissage(collection);
		remplissage(otherCollection);

		// hasCode collections semblables
		result1 = collection.hashCode();
		result2 = otherCollection.hashCode();
		assertEquals(testName + " hashCode collections remplies", result2,
				result1);

		// hasCode collections dissemblables
		collection.add(extraElement);
		result1 = collection.hashCode();
		assertTrue(testName + " hashCode collections remplies +",
				result2 != result1);

		// [Optionnel]
		// Les collections dissemblables ne sont plus �gales
		assertFalse(testName + " hashCode + equals direct +",
				collection.equals(otherCollection));
	}
}
