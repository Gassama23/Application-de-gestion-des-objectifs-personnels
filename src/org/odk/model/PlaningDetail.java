package org.odk.model;

public class PlaningDetail {

    private int id;
    private String titreSection;
    private String contenu;
    private int ordre;

    public PlaningDetail(int id, String titreSection, String contenu, int ordre) {
        this.id = id;
        this.titreSection = titreSection;
        this.contenu = contenu;
        this.ordre = ordre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitreSection() {
        return titreSection;
    }

    public void setTitreSection(String titreSection) {
        this.titreSection = titreSection;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public int getOrdre() {
        return ordre;
    }

    public void setOrdre(int ordre) {
        this.ordre = ordre;
    }

    public void afficherDetail() {
        System.out.println("ID : " + id);
        System.out.println("Titre : " + titreSection);
        System.out.println("Contenu : " + contenu);
        System.out.println("Ordre : " + ordre);
    }
}