Par défaut la gestion des transactions est gérée par le container EJB qui s'occupe des appels à commit et rollback et les méthodes définies dans le DAO servent/sont/fixent les points de démarcations.

Nos DAO sont des stateless session bean (SLSB), ceci est particulièrement important du point de vue de la démarcation des transactions. Le conteneur lance une transaction chaque fois qu'une méthode est appelée et commit si la méthode renvoie un résultat. Si la méthode lève une exception, le conteneur annule/rollback la transaction.

Dans notre implémentation, le seul moment où une requête intervient sur plusieurs DAO en même temps avec des appels en "cascade" est le cas de suppression d'un user.

Nous avons décidé que dans ce cas, les applications liées à cet utilisateur sont réassignées à un autre utilisateur disabled qui récupère et stock ces applications dans le cas où le développeur souhaite revenir sur sa décision

On pourrait aussi implémenter une feature avec par exemple un choix lors de la suppression de l'utilisateur si l'on veut garder les applications ou non.



Toujours en est-il que du coup niveau transactionnel, si la réassignation des projets échouent, on préfère rollback le tout avant la suppression de l'utilisateur.



Le comportement par défaut correspond à celui recherché comme prouvé avec l'expérience suivante :

On voit bien que la méthode deleteUser de UserDAO appelle une méthode reassignProjetOfUser d'un autre DAO ProjectDAO. Par défaut le transaction sera "unique/globale" pour la méthode deleteUser et si une exception est levée dans la méthode reassign, la totalité de la méthode sera rollback et donc le user existe toujours et ses projets lui sont toujours assignés.

![deleteUser-defaultTransaction](D:\Bibliothèque\Yannis\Desktop\HEIG-AMT\AMT-Projet-Off\AMT-Projet-Off\defaultTransaction\deleteUser-defaultTransaction.jpg)

![reassignProjectOfUser-defaultTransaction](D:\Bibliothèque\Yannis\Desktop\HEIG-AMT\AMT-Projet-Off\AMT-Projet-Off\defaultTransaction\reassignProjectOfUser-defaultTransaction.jpg)

![adminer-user](D:\Bibliothèque\Yannis\Desktop\HEIG-AMT\AMT-Projet-Off\AMT-Projet-Off\defaultTransaction\adminer-user.png)

![adminer-projectuser](D:\Bibliothèque\Yannis\Desktop\HEIG-AMT\AMT-Projet-Off\AMT-Projet-Off\defaultTransaction\adminer-projectuser.png)

Dans le cas où l'on change le type de transaction à requires_new le résultat sera différent, en effet une transaction indépendante sera faite pour chaque appel à la BD donc la suppression de l'user sera commit avant l'appel au reassignProject qui lui va lever une exception et être rollback, du coup l'utilisateur n'existera plus mais les projets lui seront toujours assignés malgré tout.

![deleteUser](D:\Bibliothèque\Yannis\Desktop\HEIG-AMT\AMT-Projet-Off\AMT-Projet-Off\requiresNew\deleteUser.png)

![adminer-projectuser](D:\Bibliothèque\Yannis\Desktop\HEIG-AMT\AMT-Projet-Off\AMT-Projet-Off\requiresNew\adminer-projectuser.png)

![adminer_user](D:\Bibliothèque\Yannis\Desktop\HEIG-AMT\AMT-Projet-Off\AMT-Projet-Off\requiresNew\adminer_user.png)



Dans le cas où aucune exception n'est levée, ses projets sont bien ré-assignés au user backup@backup.backup