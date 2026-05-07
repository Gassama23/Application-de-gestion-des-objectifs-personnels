package org.odk.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.odk.model.Objectif;

public class ObjectifRepository {
	  public int ajouterOjectif(Objectif o,Connection connection) throws SQLException {
		  
		        
		        	     String sql= "insert into objectif(nom_objectif,description,date_debut,date_fin,statut,utilisateur_id) "
		        	     		+ "values(?,?,?,?,?,?)";
		        	     PreparedStatement pStatement=connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		        	     pStatement.setString(1, o.getNom_objectif());
		        	     pStatement.setString(2, o.getDescription());
		        	     pStatement.setDate(3, java.sql.Date.valueOf(o.getDate_debut()));
		        	     pStatement.setDate(4, java.sql.Date.valueOf(o.getDate_fin()));
		        	     pStatement.setString(5, o.getStatus().name());
		        	     pStatement.setInt(6, o.getUtiliseur_id());
		        	     pStatement.executeUpdate();
		        	     ResultSet rs = pStatement.getGeneratedKeys();
		        	        int id = 0;
		        	        if (rs.next()) id = rs.getInt(1);
		        	        o.setId(id);

		        	        pStatement.close();
		        	        return id;
		 
	  }
	  public void modifier(Objectif o,Connection connection) throws SQLException {
		  
		       String sql="update objectif set nom_objectif=?, description=?, date_debut=?,date_fin=?,statut=?,utilisateur_id=? where id=?";
		       PreparedStatement pStatement=connection.prepareStatement(sql);
		       pStatement.setString(1, o.getNom_objectif() );
	      	   pStatement.setString(2, o.getDescription());
	      	   pStatement.setDate(3, java.sql.Date.valueOf(o.getDate_debut()));
	      	   pStatement.setDate(4, java.sql.Date.valueOf(o.getDate_fin()));
	      	   pStatement.setString(5, o.getStatus().name());
	      	   pStatement.setInt(6, o.getUtiliseur_id());
	      	   pStatement.setInt(0, o.getId());
	      	  
	      	   
	      	   pStatement.executeUpdate();
		  
	  }
	  public void supprimer(int id,Connection connection) throws SQLException {
		  
	       String sql="delete from objectif where id=?";
	       PreparedStatement pStatement=connection.prepareStatement(sql);
	       pStatement.setInt(0,id);
     	   pStatement.executeUpdate();
	  
     }

}
