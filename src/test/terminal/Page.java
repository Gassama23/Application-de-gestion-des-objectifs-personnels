package test.terminal;


public interface Page {
    String getNom();
    void afficher();
    void gererInput(Touche touche);
}