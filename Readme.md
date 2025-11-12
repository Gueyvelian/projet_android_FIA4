The poisonous mushroom.

Lors de cette 4e année à ISIS, nous avons eu l'occasion de découvrir un module "Android". Suite à ce dernier, nous avons dû développer une petite application aillant 3 interfaces, utilisant une API et une base de données.

Lors de ce projet, j'ai fait le choix d'utiliser l'api https://toxicshrooms.vercel.app/api/mushrooms.

Cette API recense de nombreux champignons non-comestibles et comporte deux degrés de dangerosité:
- Empoisonner
- Mortel

Sur la première page, nous retrouvons la page d'accueil avec deux boutons, "cueillir" et "champignon liké".
- Lorsqu'on clique sur le bouton "cueillir", on accède à une nouvelle page qui permet de chercher dans les différents champignons de l'api si le champignon que l'on a trouvé est toxique. Si c'est le cas, nous pouvons liker ce champignon et lui ajouter un lieu géographique (optionnel). (Ces données seront alors enregistrées dans une base de données en local afin de pouvoir les retrouver.).

    - Lorsqu'on clique sur le bouton "champignon liké", on accède à une nouvelle interface qui nous permet de retrouver tous les champignons likés ainsi que le lieu où ils ont été trouvés (si ce champ a été rempli). Cette interface utilise une base de données en local.

Afin de quitter ces deux pages et de retourné à la page d'accueil sur les deux pages précédentes, nous retrouvons un bouton "flèche" de retour en arrière.
De plus, lorsque nous nous trouvons sur la page "cueillir" nous pouvons retrouver une petite icône "cœur" qui nous redirige vers la page "champignon liké" et sur la page "champignon liké" nous retrouvons une icône "loupe" qui nous redirige vers la page "cueillir".