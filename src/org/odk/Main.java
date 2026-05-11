package org.odk;

import org.odk.service.InitialisationService;
import org.odk.ui.ConsoleMenu;

public class Main {

	public static void main(String[] args) { 
		 InitialisationService initialisationService = new InitialisationService();

	        initialisationService.creerAdminParDefaut();
		/*
         * Création du menu principal.
         */
        ConsoleMenu consoleMenu = new ConsoleMenu();

        /*
         * Lancement de l'application.
         */
        consoleMenu.lancer();
	}

}
