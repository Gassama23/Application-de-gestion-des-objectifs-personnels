package org.odk.model;

public class PlanningDetail {

    private int id;

    private String titreSection;

    private String contenu;

    private int ordreSection;

    private Planning planning;

    
    public PlanningDetail() {
    }

    
    public PlanningDetail(
            String titreSection,
            String contenu,
            int ordreSection,
            Planning planning
    ) {
        this.titreSection = titreSection;
        this.contenu = contenu;
        this.ordreSection = ordreSection;
        this.planning = planning;
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

    public int getOrdreSection() {
        return ordreSection;
    }

    public void setOrdreSection(int ordreSection) {
        this.ordreSection = ordreSection;
    }

    public Planning getPlanning() {
        return planning;
    }

    public void setPlanning(Planning planning) {
        this.planning = planning;
    }

    /**
     * Affichage console propre.
     */
    public void afficherDetail() {

        System.out.println(
                "\n===== DETAIL PLANNING ====="
        );

        System.out.println(
                "Titre : " + titreSection
        );

        System.out.println(
                "Contenu : " + contenu
        );

        System.out.println(
                "Ordre : " + ordreSection
        );

        System.out.println(
                "============================"
        );
    }

    @Override
    public String toString() {

        return "\nPlanningDetail {" +
                "\n id = " + id +
                "\n titreSection = '" + titreSection + '\'' +
                "\n contenu = '" + contenu + '\'' +
                "\n ordreSection = " + ordreSection +
                "\n}";
    }
}