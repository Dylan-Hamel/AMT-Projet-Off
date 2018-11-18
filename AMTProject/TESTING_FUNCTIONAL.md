Nous avons réalisés les test fonctionnels avec l'aide de FluentLenium.

Dû à des difficultés à faire fonctionner le tout (WebDriver, etc.), il nous est resté peu de temps pour rédiger plus de tests que ceux demandés.

De plus les tests ne sont pas parfaits car les résultats de certains tests peuvent dépendre de celui du register, en effet il est sensé (mais pas possible d'utiliser le DAO vu que le test est fait depuis le PC et pas le serveur d'application) supprimer et recréer un utilisateur, et cet utilisateur est (devrait car finalement un autre compte créé par défaut par le script de la BDD est utilisé pour les autres tests) utilisé par les autres tests donc dans le cas où ce test échoue, les autres risquent d'échouer aussi (l'ordre des tests étant arbitraire).

Nous aurions pu faire un test "global" qui répond à toutes les demandes du WP1 à la suite mais ç'aurait été plus compliqué de "debugger" car le test s'arrête à la première "erreur".

### Developer creates an account

Ce test inclut aussi déjà la partie login même si un autre test plus simple existe aussi pour tester le login (fail et réussi), il va remplir les champs de la page register et soumettre le formulaire et tenter de se connecter avec le nouveau compte.

Afin de vérifier que toutes les données entrées lors de la création de l'utilisateur soient correctes et dans les bon champs, on fait une assertion dans la page home après connexion avec les valeurs entrées dans le formulaire de création de compte.

![1 - checkLoggedInUserInfos](..\functional\1 - checkLoggedInUserInfos.png)

![1 - createUser adminer](..\functional\1 - createUser adminer.png)

![1 - createUser code](..\functional\1 - createUser code.png)

Le test est orange à cause d'un problème d'envoi de mail au moment du test :

![1 - mailError](..\functional\1 - mailError.png)

### Developer logs in

Test le plus simple qui remplit juste le formulaire de login avec un user existant et vérifie que l'on est bien sur la page home ensuite. Un autre test permet de vérifier que si les infos des connexions sont erronées on ne peut pas se connecter.

![2 - logs in fail](../functional\2 - logs in fail.png)

### Developer create 25 applications

Le test crée 25 applications via le formulaire dédié en changeant le nom et la description du projet/application en ajoutant à la fin la valeur de l'incrément i.

Il n'y a pas vraiment d'assertion pour ce test mais il plante si le projet ne peut pas être ajouté pour quelconque raison (déjà un projet avec le même nom, etc.). On ne vérifie pas si les 25 projets ont bien été créés.

![3 - Fluent](..\functional\3 - Fluent.png)

![3 - AMT-web](..\functional\3 - AMT-web.png)

![3 - adminer](..\functional\3 - adminer.png)

### Developer browses the list of applications (3 pages of 10, 10, and 5 applications)

Pour que ce test fonctionne correctement, il faut qu'il y ait au moins 3 pages d'applications (donc au moins 21) et vu que l'ordre des tests n'est pas forcément connu à l'avance, un autre compte créé par défaut dans la BDD est utilisé pour ce test.

Le test change aussi la pagination de la page de 10 à 5 mais les seuls assertions sont la valeur du "select" qui est bien 5, le test ne compte pas le nombre d'applications effectivement affichées.

![4 - browse 25 apps](../functional\4 - browse 25 apps.png)

### Developer logs out

Après un login, clique sur le logout du menu de navigation en haut et vérifie que l'on se retrouve bien à nouveau sur la page login.

Ce test est inclut dans le suivant.

### Developer tries to go back to the list of applications and is redirected to login page

Si on essaye d'accéder à une page autre que resetPWD, register et login sans être connecter, on devrait être redirigé vers la page de login.

![5 - 6 - code](..\functional\5 - 6 - code.png)