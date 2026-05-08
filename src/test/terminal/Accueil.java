package test.terminal;
public class Accueil implements Page {

    @Override
    public String getNom() { return "Accueil"; }

    @Override
    public void afficher() {
        System.out.println("\n  Bienvenue sur GoalTracker !");
        System.out.println("  ─────────────────────────────────");
        System.out.println("  Navigue avec ← → pour changer de page.");
    }

    @Override
    public void gererInput(Touche touche) {
        // pas d'input spécifique sur cette page
    }
}