package org.odk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.odk.DatabaseConnection;
import org.odk.enums.EnumStatut;
import org.odk.model.ObjectifEconomie;
import org.odk.repository.jdbc.OjbjectifEconomieRepository;

public class ObjectifEconomieService {
    
    private OjbjectifEconomieRepository economieRepository = new OjbjectifEconomieRepository();
    
    public void creerObjectifEconomie(ObjectifEconomie e) throws Exception {
 
            // la validation des données saisies par l'utlisateur
            if (e.getDescription() == null || e.getDescription().trim().isEmpty()) {
                throw new Exception("La description est obligatoire");
            }
            if (e.getNom_objectif() == null || e.getNom_objectif().trim().isEmpty()) {
                throw new Exception("Le nom de l'objectif est obligatoire");
            }
            if (e.getDelai_mois() <= 0) {
                throw new Exception("Le délai en mois doit être positif");
            }
            if (e.getMontant_cible() <= 0) {
                throw new Exception("Le montant cible doit être positif");
            }
            
           
            Connection conn = DatabaseConnection.getConnection();
            
            // Insertion
            economieRepository.ajouterOjectifEconomie(e, conn);
            
            System.out.println(" Objectif sur economie insérée  avec succès ! ID: " + e.getId());
            
       
    }
    
    public void modifierObjectifEconomie(ObjectifEconomie e) throws SQLException {
        
        	Connection conn = DatabaseConnection.getConnection();
            
            economieRepository.modifier(e, conn);
            
            System.out.println(" Modification effectuée avec succès !");
            
        
    }
    
    public void supprimerObjectifEconomie(int id) throws SQLException {
       
            Connection conn = DatabaseConnection.getConnection();
            
            economieRepository.supprimer(id, conn);
            
            System.out.println(" Suppression effectuée avec succès !");
            
        
    }
    public static EnumStatut attributionAutomatiqueStatu(LocalDate date_debut,LocalDate date_fin) {
    	         LocalDate aujourdhuiDate=LocalDate.now();
    	         if(aujourdhuiDate.isBefore(date_debut)) {
    	        	 return EnumStatut.Avenir;
    	         }else if(aujourdhuiDate.isAfter(date_fin)) {
    	        	  return EnumStatut.Termine;
    	         }else {
					return EnumStatut.Encours;
				}
    }
    public static void ListeObjectifForOneUser(int user_id) throws SQLException {

        Connection connection = DatabaseConnection.getConnection();

        List<ObjectifEconomie> objectifEconomielEconomies =
                OjbjectifEconomieRepository.getOjectifs(user_id, connection);

        if (objectifEconomielEconomies.isEmpty()) {
            System.out.println("Aucun objectif");
            return;
        }

        System.out.println("========== LISTE DES OBJECTIFS ==========");

        int i = 1;

        for (ObjectifEconomie obe : objectifEconomielEconomies) {

            System.out.println("\nObjectif #" + i++);
            System.out.println("----------------------------------------");
            System.out.println("Nom         : " + obe.getNom_objectif());
            System.out.println("Description : " + obe.getDescription());
            System.out.println("Status      : " + obe.getStatus());
            System.out.println("Date début   : " + obe.getDate_debut());
            System.out.println("Date fin     : " + obe.getDate_fin());
            System.out.println("----------------------------------------");
        }

        System.out.println("========================================");

        connection.close();
    }}