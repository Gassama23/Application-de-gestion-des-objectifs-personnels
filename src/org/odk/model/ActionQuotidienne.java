package org.odk.model;

	import java.util.Date;

	public class ActionQuotidienne {

	    private int id;
	    private String description;
	    private int jourCible;
	    private String heureRecommandee;
	    private boolean accomplie;
	    private Date dateValidation;

	    public ActionQuotidienne() {
	    }

	    // Constructeur avec paramètres
	    public ActionQuotidienne(int id, String description, int jourCible,
	                            String heureRecommandee, boolean accomplie, Date dateValidation) {
	        this.id = id;
	        this.description = description;
	        this.jourCible = jourCible;
	        this.heureRecommandee = heureRecommandee;
	        this.accomplie = accomplie;
	        this.dateValidation = dateValidation;
	    }

	    // Getters & Setters

	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	    public int getJourCible() {
	        return jourCible;
	    }

	    public void setJourCible(int jourCible) {
	        this.jourCible = jourCible;
	    }

	    public String getHeureRecommandee() {
	        return heureRecommandee;
	    }

	    public void setHeureRecommandee(String heureRecommandee) {
	        this.heureRecommandee = heureRecommandee;
	    }

	    public boolean isAccomplie() {
	        return accomplie;
	    }

	    public void setAccomplie(boolean accomplie) {
	        this.accomplie = accomplie;
	    }

	    public Date getDateValidation() {
	        return dateValidation;
	    }

	    public void setDateValidation(Date dateValidation) {
	        this.dateValidation = dateValidation;
	    }

	    // Méthode demandée
	    public ActionQuotidienne obtenir() {
	        return this;
	    }
	}
