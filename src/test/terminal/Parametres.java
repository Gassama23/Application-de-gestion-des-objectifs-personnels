public class Parametres implements Page {

    @Override
    public String getNom() { return "Paramètres"; }

    @Override
    public void afficher() {
        System.out.println("\n  Paramètres");
        System.out.println("  ─────────────────────────────────");
        System.out.println("  Aucun paramètre disponible pour l'instant.");
    }

    @Override
    public void gererInput(Touche touche) {
        // pas d'input spécifique sur cette page
    }
}