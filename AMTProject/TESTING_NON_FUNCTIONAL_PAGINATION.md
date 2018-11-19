Afin de tester l'impact de la pagination sur les performances de la webapp, nous avons créé un test script JMeter grâce à l'enregistrement de script (proxy, etc.) de JMeter puis nous l'avons modifié pour qu'il lance 20 et 100 users en parallèle pour comparer, la première fois avec une page de taille 10 et la seconde de taille 50. Nous nous sommes aussi basés sur les scripts du projet exampleMVC / MVCDemo de M. Liechti notamment quant à l'utilisation du plugin ultimate thread group

Afin de voir l'impact sur le serveur déployant l'application, visualvm a été connecté à la machine docker. Pour ce faire il a fallu copier les fichiers JAR 'jboss-client.jar' et 'jboss-cli-client.jar' de 'wildfly-14.0.1.Final\bin\client' vers 'visualvm_142\platform\lib' puis ajouter un host 192.168.99.100 et enfin une JMX connexion 'service:jmx:remote+http://192.168.99.100:9990'

Les résultats et l'analyse de l'impact sur les performances sont les suivantes :

![10 vs 50 - response time](..\pagination\10 vs 50 - response time.png)

Le graphique contient les responses time moyens des requêtes sur une page affichant 10 projets en violet et 50 en vert. On voit une nette séparation sur la gauche entre 0 et 2400ms qui correspond à 20 utilisateurs simultanés et de 2600 à 5600 qui correspond à 100 utilisateurs simultanés. On voit aussi qu'il est dans l'ensemble environ 2 fois plus lent de charger la page avec 50 projets que celle de 10 projets.

![avg-bytes](..\pagination\avg-bytes.png)

Une page avec 10 projets est environ 4 fois plus petite qu'une avec 50 (12856 vs 47585).



L'impact sur les performances côté client est non négligeable et important, de ce fait il est intéressant de mettre une pagination par défaut assez "basse" (10 semble être un bon nombre).



![visualvm](..\pagination\visualvm.png)

Le test a été lancé à 13:53:35, on voit bien un pic du CPU à ce moment. Il aurait été intéressant de vérifier plus en détail l'impact sur les ressources du serveur mais nous ne maitrisons pas très bien visualvm et nous avions de la peine à filtrer sur ce qui nous intéresse et pas le temps de chercher plus.



A noter que nous pensons que nos tests sont légèrement "faussés" par le fait que des Println de debug dans le code sont toujours présents et peuvent avoir un impact sur la performance du serveur, cela rend plus "lent" les réponses du serveur, permettant d'accentuer les différences sur la partie impact client montrée plus haut. L'application n'est pas optimisée mais il y aurait quand même un impact si c'était le cas, probablement plus léger.