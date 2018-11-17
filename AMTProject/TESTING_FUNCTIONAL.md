Nous avons réalisés les test fonctionnels avec l'aide de FluentLenium

Dû à des difficultés à faire fonctionner le tout (WebDriver, etc.), il nous est resté peu de temps pour rédiger plus de tests que ceux demandés.

De plus les tests ne sont pas parfaits car les résultats de certains tests peuvent dépendre de celui du register, en effet il supprime et recréer un utilisateur, et cet utilisateur est utilisé par les autres tests donc dans le cas où ce test échoue, les autres risquent d'échouer aussi (l'ordre des tests est en plus arbitraire)

Des screenshot de "ratés" ont été inclut dans le document étant donné que des screenshot quand tout fonctionne sont peu parlants.

### Developer creates an account

Ce test inclut aussi déjà la partie login même si un autre test plus simple existe aussi pour tester le login (fail et réussi), il va remplir les champs de la page register et soumettre le formulaire et tenter de se connecter avec le nouveau compte.

Afin de vérifier que toutes les données entrées lors de la création de l'utilisateur soient correctes et dans les bon champs, on fait une assertion dans la page home après connexion avec les valeurs entrées dans le formulaire de création de compte.

![1 - checkLoggedInUserInfos](D:\Bibliothèque\Yannis\Desktop\HEIG-AMT\AMT-Projet-Off\AMT-Projet-Off\functional\1 - checkLoggedInUserInfos.png)



### Developer logs in

Test le plus simple qui remplit juste le formulaire de login avec un user existant et vérifie que l'on est bien sur la page home ensuite. Un autre test permet de vérifier que si les infos des connexions sont erronées on ne peut pas se connecter.

![2 - logs in fail](D:\Bibliothèque\Yannis\Desktop\HEIG-AMT\AMT-Projet-Off\AMT-Projet-Off\functional\2 - logs in fail.png)

### Developer create 25 applications

Le test crée 25 applications via le formulaire dédié en changeant le nom et la description du projet/application en ajoutant à la fin la valeur de l'incrément i.



### Developer browses the list of applications (3 pages of 10, 10, and 5 applications)



### Developer logs out



### Developer tries to go back to the list of applications and is redirected to login page

