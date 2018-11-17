Par défaut la gestion des transactions est gérée par le container EJB qui s'occupe des appels à commit et rollback et les méthodes définies dans le DAO servent/sont/fixent les points de démarcations.

Nos DAO sont des stateless session bean (SLSB), ceci est particulièrement important du point de vue de la démarcation des transactions. Le conteneur lance une transaction chaque fois qu'une méthode est appelée et commit si la méthode renvoie un résultat. Si la méthode lève une exception, le conteneur annule/rollback la transaction.

Dans notre implémentation, le seul moment où une requête intervient sur plusieurs DAO en même temps avec des appels en "cascade" est le cas de suppression d'un user

Nous avons décidé que dans ce cas, les applications liées à cet utilisateur sont réassignées à un autre utilisateur disabled qui récupère et stock ces applications dans le cas où le développeur souhaite revenir sur sa décision

On pourrait aussi implémenter une feature avec par exemple un choix lors de la suppression de l'utilisateur si l'on veut garder les applications ou non



Toujours en est-il que du coup niveau transactionnel, si la réassignation des projets échouent, on préfère rollback le tout avant la suppression de l'utilisateur



Comme vu dans le cours, il existe des risques/dangers liés à l'isolation (AC**I**D) et dans notre cas



Le comportement par défaut correspond à celui recherché comme prouvé avec l'expérience suivante :

