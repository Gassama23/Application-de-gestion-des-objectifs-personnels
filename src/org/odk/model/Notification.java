import java.util.Date;

public class Notification {
    
    // Attributs privés (indiqués par le signe '-' )
    private int id;
    private EnumTypeNotification type; // c'est le même nom que Awa a donné à l'EnumNotification
    private String message;
    private Date dateEnvoi;
    private boolean lue;

    /**
     * Constructeur pour initialiser une nouvelle notification.
     */
    public Notification(int id, EnumTypeNotification type, String message, Date dateEnvoi, boolean lue) {
        this.id = id;
        this.type = type;
        this.message = message;
        this.dateEnvoi = dateEnvoi;
        this.lue = lue;
    }

    /**
     * Méthode d'envoi (indiquée par le signe '+')
     */
    public void envoyer() {
        // Logique d'envoi à implémenter selon les besoins du groupe
        System.out.println("Notification " + id + " envoyée : " + message);
    }

    // --- Getters et Setters ---
    // Indispensables pour accéder aux attributs privés depuis les autres classes (ex: Utilisateur)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EnumTypeNotification getType() {
        return type;
    }

    public void setType(EnumTypeNotification type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(Date dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    public boolean isLue() {
        return lue;
    }

    public void setLue(boolean lue) {
        this.lue = lue;
    }
}