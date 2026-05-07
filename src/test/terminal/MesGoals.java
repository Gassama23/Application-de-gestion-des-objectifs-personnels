public class MesGoals implements Page {

    private String[] goals = {
            "Apprendre Java",
            "Finir le projet TUI",
            "Lire un livre par mois",
            "Faire du sport 3x/semaine"
    };

    private int selected = 0;

    @Override
    public String getNom() { return "Mes Goals"; }

    @Override
    public void afficher() {
        System.out.println("\n  Mes Goals");
        System.out.println("  ─────────────────────────────────");
        for (int i = 0; i < goals.length; i++) {
            if (i == selected) {
                System.out.println("  \033[1m\033[36m> " + goals[i] + "\033[0m");
            } else {
                System.out.println("    " + goals[i]);
            }
        }
        System.out.println("\n  (↑ ↓ pour naviguer)");
    }

    @Override
    public void gererInput(Touche touche) {
        if (touche == Touche.FLECHE_HAUT && selected > 0)
            selected--;
        if (touche == Touche.FLECHE_BAS && selected < goals.length - 1)
            selected++;
    }
}