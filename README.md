# Database Invoker
## Introduction
Librairie permettant d'effectuer des commandes de base de données. Le but étant de rester sur le principe KISS (Kepp It
Simple and Stupid).
 
## Utilisation
Pour utiliser la librairie, il suffit de créer une instance de type <code>baobab.libraries.dbinvoker.DBInvoker</code> 
avec cinq paramètres de type <code>String</code>  : 
* <code>type</code>: limité à ces valeurs selon le type de base de donnée sur laquelle on se connecte 
<code>POSTGRES_V1</code>, <code>MYSQL_V1</code>.
* <code>url</code> : L'URL de connexion de la base de données.
* <code>bdd</code> : Le nom de la base de données.
* <code>login</code> : Le login de connexion.
* <code>mdp</code> : Le mot de passe de connexion.

<u>Exemple</u> : <code>DBInvoker invoker = new DBInvoker("POSTGRES_V1", "170.0.0.1", "dummy", "root", "root");</code>

Dans le cas ou l'argument <code>type</code> n'est pas renseigné correctement (c'est-à-dire : pas dans les valeurs 
proposées), la méthode retourne une <code>Exception</code> de type <code>IllegalArgumentException</code>.

Une fois l'instance de classe <code>DBInvoker</code> créé, il suffit d'appeler la méthode <code>executer</code>.

<u>Exemple</u> : <code>CachedRowSet resultat = invoker.executer("SELECT * FROM dum_dummy;");</code>

Celle-ci renvoie une instance de type <code>CachedRowSet</code> (une implémentation de la classe <code>ResultSet</code>)
si la requête à exécuter est de type consultation (<code>SELECT</code>) dans les autres cas <code>null</code> (<code>INSERT</code>,
<code>UPDATE</code>, <code>DELETE</code>). Lors de son exécution, la méthode peut lancer différentes exceptions selon 
l'erreur rencontrée :

* <code>ClassNotFoundException</code> Si la classe du driver de base de données est introuvable. Celà veut dire que la 
librairie ne gère pas pour le moment le type de base de données sélectionnée. 
* <code>SQLException</code> Si une erreur survient lors de l'exécution de la requête SQL. 