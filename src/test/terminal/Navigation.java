public class Navigation {

    private Page[] pages;
    private int index = 0;

    private static final String BOLD  = "\033[1m";
    private static final String CYAN  = "\033[36m";
    private static final String RESET = "\033[0m";

    public Navigation(Page[] pages) {
        this.pages = pages;
    }

    public void afficherBarre() {
        System.out.print("\n  ");
        for (int i = 0; i < pages.length; i++) {
            if (i == index) {
                System.out.print(CYAN + BOLD + "[ " + pages[i].getNom() + " ]" + RESET + "  ");
            } else {
                System.out.print("  " + pages[i].getNom() + "  ");
            }
        }
        System.out.println("\n");
    }

    public void gererInput(Touche touche) {
        if (touche == Touche.FLECHE_GAUCHE && index > 0)
            index--;
        if (touche == Touche.FLECHE_DROITE && index < pages.length - 1)
            index++;
    }

    public Page getPageActive() {
        return pages[index];
    }
}