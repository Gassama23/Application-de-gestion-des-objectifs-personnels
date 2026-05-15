package org.odk.repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.odk.DatabaseConnection;
import org.odk.model.AttentesUtilisateur;
import org.odk.repository.interfaces.AttentesUtilisateurRepository;

public class AttentesUtilisateurRepositoryJdbc implements AttentesUtilisateurRepository {


    @Override
    public void save(AttentesUtilisateur a) {

    	String sql = "INSERT INTO attentes_utilisateur (" +
    	        "revenu_mensuel, epargne_actuelle, montant_vise, delai_mois, comportement_financier, " +
    	        "categories_depenses, situation_dettes, montant_dettes, fonds_urgence, " +
    	        "objectif_sport, poids_a_perdre, niveau, frequence_hebdo, duree_seance, acces_salle, type_entrainement, contraintes_sante, " +
    	        "objectif_final, temps_quotidien, historique, " +
    	        "domaine_prioritaire, niveau_discipline, type_defi, utilisateur_id, objectif_id) " +
    	        "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setDouble(1, a.getRevenuMensuel());
            stmt.setDouble(2, a.getEpargneActuelle());
            stmt.setDouble(3, a.getMontantVise());
            stmt.setInt(4, a.getDelaiMois());
            stmt.setInt(5, a.getComportementFinancier());
            stmt.setString(6, a.getCategoriesDepenses());
            stmt.setInt(7, a.getSituationDettes());
            stmt.setDouble(8, a.getMontantDettes());
            stmt.setInt(9, a.getFondsUrgence());

            stmt.setInt(10, a.getObjectifSport());
            stmt.setDouble(11, a.getPoidsAPerdre());
            stmt.setInt(12, a.getNiveau());
            stmt.setInt(13, a.getFrequenceHebdo());
            stmt.setInt(14, a.getDureeSeance());
            stmt.setBoolean(15, a.isAccesSalle());
            stmt.setInt(16, a.getTypeEntrainement());
            stmt.setInt(17, a.getContraintesSante());

            
            stmt.setInt(18, a.getObjectifFinal());
            stmt.setInt(19, a.getTempsQuotidien());
            
            stmt.setInt(20, a.getHistorique());

            stmt.setInt(21, a.getDomainePrioritaire());
            stmt.setInt(22, a.getNiveauDiscipline());
            stmt.setInt(23, a.getTypeDefi());

            stmt.setInt(24, a.getUtilisateurId());
            stmt.setInt(25, a.getObjectifId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(AttentesUtilisateur a) {

        String sql = "UPDATE attentes_utilisateur SET " +
                "revenu_mensuel=?, epargne_actuelle=?, montant_vise=?, delai_mois=?, comportement_financier=?, " +
                "categories_depenses=?, situation_dettes=?, montant_dettes=?, fonds_urgence=?, " +
                "objectif_sport=?, poids_a_perdre=?, niveau=?, frequence_hebdo=?, duree_seance=?, acces_salle=?, type_entrainement=?, contraintes_sante=?, " +
                "sujet=?, objectif_final=?, temps_quotidien=?, style_apprentissage=?, historique=?, " +
                "domaine_prioritaire=?, niveau_discipline=?, type_defi=?, utilisateur_id=?, objectif_id=? " +
                "WHERE id=?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setDouble(1, a.getRevenuMensuel());
            stmt.setDouble(2, a.getEpargneActuelle());
            stmt.setDouble(3, a.getMontantVise());
            stmt.setInt(4, a.getDelaiMois());
            stmt.setInt(5, a.getComportementFinancier());
            stmt.setString(6, a.getCategoriesDepenses());
            stmt.setInt(7, a.getSituationDettes());
            stmt.setDouble(8, a.getMontantDettes());
            stmt.setInt(9, a.getFondsUrgence());

            stmt.setInt(10, a.getObjectifSport());
            stmt.setDouble(11, a.getPoidsAPerdre());
            stmt.setInt(12, a.getNiveau());
            stmt.setInt(13, a.getFrequenceHebdo());
            stmt.setInt(14, a.getDureeSeance());
            stmt.setBoolean(15, a.isAccesSalle());
            stmt.setInt(16, a.getTypeEntrainement());
            stmt.setInt(17, a.getContraintesSante());

            
            stmt.setInt(18, a.getObjectifFinal());
            stmt.setInt(19, a.getTempsQuotidien());
            
            stmt.setInt(20, a.getHistorique());

            stmt.setInt(21, a.getDomainePrioritaire());
            stmt.setInt(22, a.getNiveauDiscipline());
            stmt.setInt(23, a.getTypeDefi());

            stmt.setInt(24, a.getUtilisateurId());
            stmt.setInt(25, a.getObjectifId());

            stmt.setInt(26, a.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {

        String sql = "DELETE FROM attentes_utilisateur WHERE id=?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public AttentesUtilisateur findById(int id) {

        String sql = "SELECT * FROM attentes_utilisateur WHERE id=?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    AttentesUtilisateur a = new AttentesUtilisateur();

                    a.setId(rs.getInt("id"));

                    a.setRevenuMensuel(rs.getDouble("revenu_mensuel"));
                    a.setEpargneActuelle(rs.getDouble("epargne_actuelle"));
                    a.setMontantVise(rs.getDouble("montant_vise"));
                    a.setDelaiMois(rs.getInt("delai_mois"));
                    a.setComportementFinancier(rs.getInt("comportement_financier"));
                    a.setCategoriesDepenses(rs.getString("categories_depenses"));
                    a.setSituationDettes(rs.getInt("situation_dettes"));
                    a.setMontantDettes(rs.getDouble("montant_dettes"));
                    a.setFondsUrgence(rs.getInt("fonds_urgence"));

                    a.setObjectifSport(rs.getInt("objectif_sport"));
                    a.setPoidsAPerdre(rs.getDouble("poids_a_perdre"));
                    a.setNiveau(rs.getInt("niveau"));
                    a.setFrequenceHebdo(rs.getInt("frequence_hebdo"));
                    a.setDureeSeance(rs.getInt("duree_seance"));
                    a.setAccesSalle(rs.getBoolean("acces_salle"));
                    a.setTypeEntrainement(rs.getInt("type_entrainement"));
                    a.setContraintesSante(rs.getInt("contraintes_sante"));

                    a.setObjectifFinal(rs.getInt("objectif_final"));
                    a.setTempsQuotidien(rs.getInt("temps_quotidien"));
                    a.setHistorique(rs.getInt("historique"));

                    a.setDomainePrioritaire(rs.getInt("domaine_prioritaire"));
                    a.setNiveauDiscipline(rs.getInt("niveau_discipline"));
                    a.setTypeDefi(rs.getInt("type_defi"));

                    a.setUtilisateurId(rs.getInt("utilisateur_id"));
                    a.setObjectifId(rs.getInt("objectif_id"));

                    return a;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<AttentesUtilisateur> findAll() {

        String sql = "SELECT * FROM attentes_utilisateur";
        List<AttentesUtilisateur> list = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                AttentesUtilisateur a = new AttentesUtilisateur();

                a.setId(rs.getInt("id"));

                a.setRevenuMensuel(rs.getDouble("revenu_mensuel"));
                a.setEpargneActuelle(rs.getDouble("epargne_actuelle"));
                a.setMontantVise(rs.getDouble("montant_vise"));
                a.setDelaiMois(rs.getInt("delai_mois"));
                a.setComportementFinancier(rs.getInt("comportement_financier"));
                a.setCategoriesDepenses(rs.getString("categories_depenses"));
                a.setSituationDettes(rs.getInt("situation_dettes"));
                a.setMontantDettes(rs.getDouble("montant_dettes"));
                a.setFondsUrgence(rs.getInt("fonds_urgence"));

                a.setObjectifSport(rs.getInt("objectif_sport"));
                a.setPoidsAPerdre(rs.getDouble("poids_a_perdre"));
                a.setNiveau(rs.getInt("niveau"));
                a.setFrequenceHebdo(rs.getInt("frequence_hebdo"));
                a.setDureeSeance(rs.getInt("duree_seance"));
                a.setAccesSalle(rs.getBoolean("acces_salle"));
                a.setTypeEntrainement(rs.getInt("type_entrainement"));
                a.setContraintesSante(rs.getInt("contraintes_sante"));

                a.setObjectifFinal(rs.getInt("objectif_final"));
                a.setTempsQuotidien(rs.getInt("temps_quotidien"));
                a.setHistorique(rs.getInt("historique"));

                a.setDomainePrioritaire(rs.getInt("domaine_prioritaire"));
                a.setNiveauDiscipline(rs.getInt("niveau_discipline"));
                a.setTypeDefi(rs.getInt("type_defi"));

                a.setUtilisateurId(rs.getInt("utilisateur_id"));
                a.setObjectifId(rs.getInt("objectif_id"));

                list.add(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public AttentesUtilisateur findByUtilisateurId(int utilisateurId) {

        String sql = "SELECT * FROM attentes_utilisateur WHERE utilisateur_id=?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, utilisateurId);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    AttentesUtilisateur a = new AttentesUtilisateur();
                    a.setId(rs.getInt("id"));
                    a.setUtilisateurId(rs.getInt("utilisateur_id"));
                    a.setObjectifId(rs.getInt("objectif_id"));
                    return a;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public AttentesUtilisateur findByObjectifId(int objectifId) {

        String sql = "SELECT * FROM attentes_utilisateur WHERE objectif_id=?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, objectifId);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    AttentesUtilisateur a = new AttentesUtilisateur();
                    a.setId(rs.getInt("id"));
                    a.setUtilisateurId(rs.getInt("utilisateur_id"));
                    a.setObjectifId(rs.getInt("objectif_id"));
                    return a;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}