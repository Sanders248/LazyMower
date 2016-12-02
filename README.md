# LazyMower
Android App and Service to simulate a remote control of a mower with a phone or tablet


AndroidApp:
	Android App with 4 buttons which allow to launch some actions: go ahead, go back, turn right or turn left.
	Then, the web API is called with the id of the app (randomly defined for now) and with the requiered action.

	
APIMower
	REST Service with JAX-RS (JAVA 1.7) on a Glassfish serveur (version 4.1.1) (launched with Netbeans)
	This one will create logs every time a called is made on the good path.


