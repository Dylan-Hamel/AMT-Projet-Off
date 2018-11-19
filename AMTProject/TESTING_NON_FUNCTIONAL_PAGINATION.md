Afin de tester l'impact de la pagination sur les performances de la webapp, nous avons créé un test script JMeter grâce à l'enregistrement de script (proxy, etc.) de JMeter puis nous l'avons modifié pour qu'il lance 20 et 100 users en parallèle pour comparer, la première fois avec une page de taille 10 et la seconde de taille 50. Nous nous sommes aussi basés sur les scripts du projet exampleMVC / MVCDemo de M. Liechti notamment quant à l'utilisation du plugin ultimate thread group

Afin de voir l'impact sur le serveur déployant l'application, visualvm a été connecté à la machine docker. Pour ce faire il a fallu copier les fichiers JAR 'jboss-client.jar' et 'jboss-cli-client.jar' de 'wildfly-14.0.1.Final\bin\client' vers 'visualvm_142\platform\lib' puis ajouter un host 192.168.99.100 et enfin une JMX connexion 'service:jmx:remote+http://192.168.99.100:9990'

Les résultats et l'analyse de l'impact sur les performances sont les suivantes :

