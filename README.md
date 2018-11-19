# AMT - Projet 2018 - WP1



Pour lancer le serveur d'application et déployer automatiquement notre app web, il suffit de faire un ```docker-compose up --build``` dans le dossier AMT-Projet-Off\AMTProject\docker\yannis

Ce dernier va run 3 containers : wildfly (9090 app, 9990 admin), mysql (3036) et adminer (6060 phpmyadmin en mieux)



L'application est ensuite accessible via :

http://192.168.99.100:9090/AMT-Projet/login ; l'IP peut varier, utiliser celle de la VM docker

Un utilisateur avec 25 projets existe par défaut : test@test.test ; testPWD



http://192.168.99.100:6060 pour accéder à la base de données; user = amt ; pwd = amt1234

