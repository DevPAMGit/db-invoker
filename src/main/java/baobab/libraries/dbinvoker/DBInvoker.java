package baobab.libraries.dbinvoker;

import baobab.libraries.dbinvoker.model.ConnexionModel;
import org.jetbrains.annotations.NotNull;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;

/**
 * Classe d'invocation de base de données.
 */
public class DBInvoker {
    /**
     * Le type de la base de données.
     */
    private final ConnexionModel connexion;

    /**
     * L'URL de connexion vers la base de données.
     */
    private final String url;

    /**
     * Le nom de la base de données sur laquelle se connecter.
     */
    private final String bdd;

    /**
     *  Le login de connexion vers la base de données.
     */
    private final String login;

    /**
     * Le mot de passe du login.
     */
    private final String mdp;

    /**
     * Initialise une nouvelle instance de la classe {@link DBInvoker}.
     * @param type      Le type de la base de données.
     * @param url       L'URL de la base de données.
     * @param bdd       La base de données sur laquelle se connecter.
     * @param login     Le login vers la base de données.
     * @param mdp       Le mot de passe du login pour la base de données.
     */
    public DBInvoker(@NotNull String type, @NotNull String url, @NotNull String bdd, @NotNull String login,
                     @NotNull String mdp){
        this.connexion = ConnexionModel.valueOf(type);
        this.login = login;
        this.bdd = bdd;
        this.url = url;
        this.mdp = mdp;
    }

    /**
     * Méthode permettant de récupérer une connexion vers la base de données.
     * @return                          Une instance de type {@link Connection} permettant de passer des requêtes SQL.
     * @throws ClassNotFoundException   Si la classe du driver est introuvable.
     * @throws SQLException             Si une erreur survient lors de l'accès vers la base de données.
     */
    private Connection getConnexion() throws ClassNotFoundException, SQLException {
        Class.forName(this.connexion.driver);
        return DriverManager.getConnection(
                String.format("%s://%s/%s", this.connexion.chaineConnexion, this.url, this.bdd), this.login, this.mdp);
    }

    /**
     * Vérifie si la requete opère une mise à jour en base de données.
     * @param requete   La requête à exécuter.
     * @return          <c>true</c> si la requête est un 'INSERT', 'UDPATE' ou 'DELETE', <c>false</c> sinon.
     */
    private boolean isRequeteMAJ(String requete){
        String req = requete.trim().toLowerCase();

        return req.startsWith("insert") || req.startsWith("update") || req.startsWith("delete");
    }

    /**
     * Execute une requête SQL sur la base de données.
     * @param requete                   La requête SQL à executer.
     * @return                          Le résultat de la requête SQL ou NULL.
     * @throws ClassNotFoundException   Si la classe du driver est introuvable.
     * @throws SQLException             Si une erreur survient lors de l'accès vers la base de données.
     */
    public CachedRowSet executer(@NotNull String requete) throws SQLException, ClassNotFoundException {
        Connection connection = this.getConnexion();
        Statement statement = connection.createStatement();

        if(this.isRequeteMAJ(requete)) {
            statement.executeUpdate(requete);
            return null;
        }

        ResultSet resultSet = statement.executeQuery(requete);

        CachedRowSet resultat =  RowSetProvider.newFactory().createCachedRowSet();
        resultat.populate(resultSet);

        statement.close();
        connection.close();

        return resultat;
    }

}
