package org.odk.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.odk.DatabaseConnection;
import org.odk.model.ObjectifEconomie;
import org.odk.repository.jdbc.OjbjectifEconomieRepository;

public class ObjectifEconomieService {
    
    private OjbjectifEconomieRepository economieRepository = new OjbjectifEconomieRepository();
    
    public void creerObjectifEconomie(ObjectifEconomie e) {
        Connection conn = null;
        
        try {
            //  VALIDATIONS MÉTIER
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
            
           
            conn = DatabaseConnection.getConnection();
            
            // Insertion
            economieRepository.ajouterOjectifEconomie(e, conn);
            
            System.out.println("✅ Insertion effectuée avec succès ! ID: " + e.getId());
            
        } catch (Exception ex) {
            
            ex.printStackTrace();
        } 
    }
    
    public void modifierObjectifEconomie(ObjectifEconomie e) {
        Connection conn = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            
            economieRepository.modifier(e, conn);
            
            conn.commit();
            System.out.println("✅ Modification effectuée avec succès !");
            
        } catch (Exception ex) {
            
            ex.printStackTrace();
        }
    }
    
    public void supprimerObjectifEconomie(int id) {
        Connection conn = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);
            
            economieRepository.supprimer(id, conn);
            
            conn.commit();
            System.out.println("✅ Suppression effectuée avec succès !");
            
        } catch (Exception ex) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException rollbackEx) {}
            ex.printStackTrace();
        }
    }
}