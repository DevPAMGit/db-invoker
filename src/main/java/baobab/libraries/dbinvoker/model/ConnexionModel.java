package baobab.libraries.dbinvoker.model;

import org.jetbrains.annotations.NotNull;

/**
 * Enumeration des types de connexion possible.
 */
public enum ConnexionModel {
    /**
     * Modèle de connexion pour postgres.
     */
    POSTGRES_V1("org.postgresql.Driver", "jdbc:postgresql"),

    /**
     * Modèle de connexion pour mysql.
     */
    MYSQL_V1("com.mysql.jdbc.Driver", "jdbc:mysql");

    /**
     * Le driver de connexion.
     */
    public final String driver;

    /**
     * La chaine de connexion du driver.
     */
    public final String chaineConnexion;

    /**
     * Initialise une nouvelle instance de la classe {@link ConnexionModel}.
     * @param driver           La chaine de caractères définissant la classe du driver à utiliser.
     * @param chaineConnexion  Le début de chaîne de connexion vers la base de données.
     */
    ConnexionModel(@NotNull String driver, @NotNull String chaineConnexion){
        this.driver = driver;
        this.chaineConnexion = chaineConnexion;
    }
}
