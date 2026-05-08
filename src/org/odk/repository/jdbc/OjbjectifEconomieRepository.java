		package org.odk.repository.jdbc;
		
		import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.odk.model.ObjectifEconomie;
		
		public class OjbjectifEconomieRepository {
			
			private ObjectifRepository objectifRepository=new ObjectifRepository();
			//	ajout d'un ojectif economie
			public ObjectifEconomie ajouterOjectifEconomie(ObjectifEconomie o,Connection connection) throws SQLException {
				    objectifRepository.ajouterOjectif(o, connection);
			     String sql= "insert into objectif_economie(objectif_id,montant_cible,montant_epargne,delai_mois) "
			     		+ "values(?,?,?,?,?,?)";
			     PreparedStatement pStatement=connection.prepareStatement(sql);
			     pStatement.setInt(1, o.getId());
			     pStatement.setInt(2, o.getMontant_cible());
			     pStatement.setInt(3, o.getMontant_epargne());
			     pStatement.setInt(4, o.getDelai_mois());
			     pStatement.executeUpdate();
			     return o;
		}
		//modification d'un ojectif sur l'ecomie
		public void modifier(ObjectifEconomie o,Connection connection) throws SQLException {
		//pour celui du parent
		String sqlparent="update objectif set nom_objectif=?, description=?, date_debut=?,date_fin=?,statut=?,utilisateur_id=? where id=?";
		PreparedStatement pStatement=connection.prepareStatement(sqlparent);
		pStatement.setString(2, o.getNom_objectif() );
		pStatement.setString(3, o.getDescription());
		pStatement.setDate(4, java.sql.Date.valueOf(o.getDate_debut()));
		pStatement.setDate(5, java.sql.Date.valueOf(o.getDate_fin()));
		pStatement.setString(6, o.getStatus().name());
		pStatement.setInt(7, o.getUtilsateur_id());
		pStatement.setInt(1, o.getId());
		//pour celui de  l'enfant
		String sqlenfant="update objectif_economie set montant_cible=?, montant_epargne=?, delai_mois=? where id=?";
		PreparedStatement pStatement1=connection.prepareStatement(sqlenfant);
		pStatement.setInt(2, o.getMontant_cible() );
		pStatement.setInt(3, o.getMontant_epargne());
		pStatement.setInt(4, o.getDelai_mois());
		pStatement1.setInt(1, o.getId());
		pStatement.executeUpdate();
		
		}
		public void supprimer(int id,Connection connection) throws SQLException {
		
		      (new ObjectifRepository()).supprimer(id, connection);
		
		}
		             
		}
