# Database Invoker
## Introduction
 
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
proposées), la méthode retourne une <code>Exception</code> de type <code>IllegalArgumentException</code> 