package baobab.libraries.dbinvoker;

import org.junit.jupiter.api.*;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;

/**
 * Classe de test pour les instances de types {@link DBInvokerTest}.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DBInvokerTest {

    /**
     * Le type de base de données sur laquelle se connecter (celle-ci provoque une erreur).
     */
    private final static String TYPE_ERREUR = "POSTGRES_V";

    /**
     * Le type de base de données sur laquelle se connecter.
     */
    private final static String TYPE = "POSTGRES_V1";

    /**
     * L'URL vers la base de données.
     */
    private final static String URL = "localhost:5432";

    /**
     * La base de données sur laquelle se connecter.
     */
    private final static String BDD = "dummy";

    /**
     * Le login de la base de données.
     */
    private final static String LOGIN = "dummy";

    /**
     * Le login de la base de données n erreur.
     */
    private final static String LOGIN_ERREUR = "dummy_login_erreur";

    /**
     * Le mot de passe vers la base de données.
     */
    private final static String MOT_DE_PASSE = "dummy";

    /**
     * Requête d'insertion en base de données à tester.
     */
    private final static String INSERTION = "INSERT INTO dum_dummy(dum_data) VALUES('d1')";

    /**
     * Requête de consultation en base de données à tester.
     */
    private final static String SELECTION = "SELECT * FROM dum_dummy;";

    /**
     * Requête de suppression de données en base à tester.
     */
    private final static String SUPPRESSION = "DELETE FROM dum_dummy;";

    /**
     * Requête de consultation en base de données à tester.
     */
    private final static String MAJ = "UDPATE dum_dummy SET dum_data = 'd2' WHERE dum_data = 'd1'";

    /**
     * La base de données qui servira au test.
     */
    private static DBInvoker invoker;

    @BeforeAll
    public static void initialiser(){
        invoker = new DBInvoker(TYPE, URL, BDD, LOGIN, MOT_DE_PASSE);
    }

    /**
     * Teste une erreur d'initialisation de base de données avec un driver non trouvé.
     */
    @Test
    @Order(1)
    public void testerErreurDriverNonTrouve(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new DBInvoker(TYPE_ERREUR, URL, BDD, LOGIN, MOT_DE_PASSE));
    }

    /**
     * Test une erreur d'accès à la base de données.
     */
    @Test
    @Order(2)
    public void testerErreurAccesBdd() {
        Assertions.assertThrows(SQLException.class,
                () -> new DBInvoker(TYPE, URL, BDD, LOGIN_ERREUR, MOT_DE_PASSE).executer(""));
    }

    /**
     * Méthode permettant de tester les insertions en base de données.
     * @throws ClassNotFoundException   Si la classe du driver est introuvable.
     * @throws SQLException             Si une erreur survient lors de l'accès vers la base de données.
     */
    @Test
    @Order(3)
    public void testerInsertion() throws SQLException, ClassNotFoundException {
        Assertions.assertNull(invoker.executer(INSERTION));
    }

    /**
     * Méthode permettant de tester une consultation en base de données.
     * @throws ClassNotFoundException   Si la classe du driver est introuvable.
     * @throws SQLException             Si une erreur survient lors de l'accès vers la base de données.
     */
    @Test
    @Order(4)
    public void testerSelection() throws SQLException, ClassNotFoundException {
        CachedRowSet resultat = invoker.executer(SELECTION);
        Assertions.assertTrue(resultat.next());
        Assertions.assertEquals("d1", resultat.getString(1));
    }

    /**
     * Méthode permettant de tester une mise à jour de données en base.
     * @throws ClassNotFoundException   Si la classe du driver est introuvable.
     * @throws SQLException             Si une erreur survient lors de l'accès vers la base de données.
     */
    @Test
    @Order(4)
    public void testerMAJ() throws SQLException, ClassNotFoundException {
        Assertions.assertNull(invoker.executer(MAJ));
        CachedRowSet resultat = invoker.executer(SELECTION);
        Assertions.assertTrue(resultat.next());
        Assertions.assertEquals("d2", resultat.getString(1));
    }

    /**
     * Méthode permettant de tester une mise à jour de données en base.
     * @throws ClassNotFoundException   Si la classe du driver est introuvable.
     * @throws SQLException             Si une erreur survient lors de l'accès vers la base de données.
     */
    @Test
    @Order(5)
    public void testerSuppression() throws SQLException, ClassNotFoundException {
        Assertions.assertNull(invoker.executer(SUPPRESSION));
        CachedRowSet resultat = invoker.executer(SELECTION);
        Assertions.assertFalse(resultat.next());
    }
}