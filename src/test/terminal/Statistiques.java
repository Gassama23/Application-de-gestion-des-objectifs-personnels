package test.terminal;


public class Statistiques implements Page {

    @Override
    public String getNom() { return "Statistiques"; }

    @Override
    public void afficher() {
        System.out.println("\n  Statistiques");
        System.out.println("  ─────────────────────────────────");
        System.out.println("  Goals complétés  : 2");
        System.out.println("  Goals en cours   : 2");
        System.out.println("  Total            : 4");
    }

    @Override
    public void gererInput(Touche touche) {
        // pas d'input spécifique sur cette page
    }
}