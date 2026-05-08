package org.odk.model;

public class PlanningDetail {

    private int id;
    private String titre_section;
    private String contenu;
    private int ordre_section;
    private int planning_id;

    public PlanningDetail(int id, String titreSection, String contenu, int ordre, int planning) {
        this.id = id;
        this.titre_section = titreSection;
        this.contenu = contenu;
        this.ordre_section = ordre;
        this.planning_id = planning;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitreSection() {
        return titre_section;
    }

    public void setTitreSection(String titreSection) {
        this.titre_section = titreSection;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public int getOrdre() {
        return ordre_section;
    }

    public void setOrdre(int ordre) {
        this.ordre_section = ordre;
    }

    public int getPlanning_id() {
		return planning_id;
	}

	public void setPlanning_id(int planning) {
		this.planning_id = planning;
	}
	
    public void afficherDetail() {
        System.out.println("ID : " + id);
        System.out.println("Titre : " + titre_section);
        System.out.println("Contenu : " + contenu);
        System.out.println("Ordre : " + ordre_section);
        System.out.println("Planning : " + planning_id);
    }

}