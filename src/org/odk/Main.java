package org.odk;

import org.odk.ui.ConsoleMenu;

public class Main {

	public static void main(String[] args) { 
		System.out.println("\n  Bienvenue sur GestionObjectifs !");
        System.out.println("  ─────────────────────────────────");
        System.out.println("  Navigue avec ← → pour changer de page.");
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
