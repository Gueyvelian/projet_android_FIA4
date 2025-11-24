# Champignon de la mort.

## Description des consignes à suivre

Lors de cette 4e année à ISIS, nous avons eu l'occasion de découvrir le module "Android". Suite à ce dernier, nous avons dû développer une petite application aillant 3 interfaces, utilisant une API (utilisation de glide pour le chargement des images grace à Ktor) et une base de données local (utilant ROOM).

## Descrition du projet

Lors de ce projet, j'ai fait le choix d'utiliser l'api https://toxicshrooms.vercel.app/api/mushrooms.

Cette API recense de nombreux champignons non-comestibles qui comporte deux degrés de dangerosité:
- Empoisonner
- Mortel

### Fonctionalitées

- Sur la première page, nous retrouvons la page d'accueil avec deux boutons, "Découvrir" et "Favoris". Ces deux boutons, nous permettent de changer de page. (Les différentes pages sont décrites juste au-dessous.)

De plus, dans la barre de recherche du bas, nous pouvons retrouver 3 boutons qui nous permettent de naviguer sur les 3 pages de l'application. Nous y retrouvons un bouton "Découvrir", un bouton "Accueil" et un bouton "Favori" (cette disposition est valable pour toutes les pages.).

<img width="247" height="540" alt="image" src="https://github.com/user-attachments/assets/9afa4c41-11a4-42f4-ad95-01b3e5651e00" />

- Lorsqu'on clique sur le bouton "Découvrir", on accède à une nouvelle page qui nous affiche une liste de tout les champignons toxique (présent dans l'api). Cette page permet aussi de chercher un champignon spécifique grâce à son nom. S'il n'y a pas de champignon correspondant à ce nom dans l'api, la phrase suivante s'affiche sur l'écran "Aucun résultat trouvé". De plus, sur tous les champignons, nous pouvons voir son nom, son nom courant ainsi que la dangerosité de son poison. À côté de cette description, nous pouvons liker le champignon. (Ces données seront alors enregistrées dans une base de données en local afin de pouvoir les retrouver.). Lorsqu'on like un champignon, le "cœur" passe en rouge sinon il reste "non coloré". En haut à gauche, nous avons à disposition, une flèche qui nous permet de faire un retour en arrière.

<img width="245" height="540" alt="image" src="https://github.com/user-attachments/assets/3f34719d-e056-4ccf-85ac-ed4124ea9c79" />

- Lorsqu'on clique sur le bouton "Favoris", on accède à une nouvelle interface qui nous permet de retrouver tous les champignons likés (Ils possèdent la même description que les champignons de la page "découvrir."). Cette interface utilise une base de données en local, les champignons restent donc en favori si l'on quitte l'application (et que nous y revenons plus tard dessus). Si nous voulons retirer un champignon liké de la liste des favoris, nous pouvons cliquer sur l'icône corbeille. Il disparaît alors instantanément. S'il n'y a pas de champignon mis en favori, le texte suivant s'affiche : "Vous n'avez pas encore de favoris". Nous retrouvons en haut à gauche une flèche permettant de faire un retour en arrière.

<img width="243" height="540" alt="image" src="https://github.com/user-attachments/assets/6fdd5eb6-8f2b-44fc-aa3c-f64ae4c34fbc" />

