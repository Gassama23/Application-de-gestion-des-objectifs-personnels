package org.odk.model;

import java.time.LocalTime;

public class Rappel {
	
	    private int id;

	    private LocalTime heureRappel;

	    private String message;

	    private boolean actif;

	    private int planningId;

	    public Rappel() {}
	    
	    public Rappel(LocalTime heureRappel, String message, boolean actif, int planningId) {
	        this.heureRappel = heureRappel;
	        this.message = message;
	        this.actif = actif;
	        this.planningId = planningId;
	    }

	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public LocalTime getHeureRappel() {
	        return heureRappel;
	    }

	    public void setHeureRappel(
	            LocalTime heureRappel
	    ) {
	        this.heureRappel = heureRappel;
	    }

	    public String getMessage() {
	        return message;
	    }

	    public void setMessage(
	            String message
	    ) {
	        this.message = message;
	    }

	    public boolean isActif() {
	        return actif;
	    }

	    public void setActif(boolean actif) {
	        this.actif = actif;
	    }

	    public int getPlanningId() {
	        return planningId;
	    }

	    public void setPlanningId(
	            int planningId
	    ) {
	        this.planningId = planningId;
	    }

}
