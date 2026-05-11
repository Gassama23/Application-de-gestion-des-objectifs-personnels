package test.terminal;

public class Main {

    public static void main(String[] args) throws Exception {

        Terminal.activerModeRaw();

        Page[] pages = {
                new Accueil(),
                new MesObjectifs(),
                new Statistiques(),
                new Parametres()
        };

        Navigation nav = new Navigation(pages);

        while (true) {
            Terminal.clear();
            nav.afficherBarre();
            nav.getPageActive().afficher();

            Touche touche = Input.lire();

            if (touche == Touche.ECHAP) break;

            if (touche == Touche.FLECHE_GAUCHE || touche == Touche.FLECHE_DROITE) {
                nav.gererInput(touche);
            } else {
                nav.getPageActive().gererInput(touche);
            }
        }

        Terminal.restaurer();
        Terminal.clear();
        System.out.println("Au revoir !");
    }
}