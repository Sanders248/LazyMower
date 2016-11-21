# LazyMower
Android App and Service to simulate a remote control of a mower with a phone or tablet


AndroidApp:
	Application Android contenant 4 boutons permettant d’exécuter les actions suivantes: Avancer, reculer, tourner à droite et tourner à gauche.
	Ensuite l'API web est appelé avec l'identifiant de l'application(définit aléatoirement) ainsi qu'avec l'action demandé.

	
APIMower
	Service REST avec JAX-RS (JAVA 1.7) sur un serveur Glassfish (4.1.1) (lancé avec Netbeans)
    Celui-ci créera des logs (à l'emplacement indiqué dans le code source) dès qu'un appel sera effectué sur le bon chemin.


