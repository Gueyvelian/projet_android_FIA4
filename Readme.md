The poisonous mushroom.

Lors de cette 4e anné à ISIS, nous avons eu locasion de découvrir un modul "android". Suite à ce dernier, nous avons dù developper une petite application aillant 3 interface, utilisant une API et une base de donné.

Lors de ce projet, j'ai fait le choix d'utiliser l'api https://toxicshrooms.vercel.app/api/mushrooms.

Cette API recence de nombreux champignon non comestible et comporte deux degret de dangeureusité:
    - Empoisoné
    - Mortel

Sur la premiere page nous retrouvons la page d'accuil avec deux bouton, "cueillir" et "champignon liké".
    - Lorsqu'on clique sur le bouton "cueillir", on accsede à une nouvelle page qui permet de chercher dans les diferent champignon de l'api si le champignon que l'on a trouver est toxic (cela mene vers la deuxieme page). Si c'est le cas nous pouvons liker ce champignon et lui ajouté un lieux geographique (optionel). Ces donnée serons alors enregistre dans une base de donné en local afin de pouvoir les retrouvé).
    - Lorsqu'on clique sur le bouton "champignon liké", on acsede à un nouvelle interface qui nous permet de retrouver tout les champignon liké ainsi que le lieux ou ils ont été trouvé si ce champ a été rempli. Cette interface utilise une base de donné en local.
Afin de quitter ces deux page et de retourné à la page d'accuil sur les deux page préssédante nous retrouvons un bouton "fleche" de retour en arriere. 
De plus lorsque nous nous trouvons sur la page "cueillir" nous pouvons retrouver une petite icone "coeur" qui nous redirige vers la page "champignon liké" et sur la page "champignon liké" nous retrouvons une icon "loupe" qui nous redigige vers la page "cueillir".
