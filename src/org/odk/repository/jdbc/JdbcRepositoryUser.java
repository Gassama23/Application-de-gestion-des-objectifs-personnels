package org.odk.repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.odk.DatabaseConnection;

import org.odk.enums.EnumRole;

import org.odk.model.Admin;
import org.odk.model.User;
import org.odk.model.Utilisateur;

import org.odk.repository.interfaces.UserRepository;

public class JdbcRepositoryUser implements UserRepository {

    @Override
    public User sauvegarder(User user) {

        String sql = """
            INSERT INTO utilisateur(
                nom,
                prenom,
                email,
                mot_de_passe,
                role,
                streak_actuel,
                meilleur_streak,
                date_inscription
            )
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (

                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(
                                sql,
                                Statement.RETURN_GENERATED_KEYS
                        )
        ) {

            ps.setString(1, user.getNom());

            ps.setString(2, user.getPrenom());

            ps.setString(3, user.getEmail());

            ps.setString(4, user.getMotDePasse());

            ps.setString(
                    5,
                    user.getRole().name()
            );

            /*
             * Si utilisateur normal.
             */
            if (user instanceof Utilisateur utilisateur) {

                ps.setInt(
                        6,
                        utilisateur.getStreakActuel()
                );

                ps.setInt(
                        7,
                        utilisateur.getMeilleurStreak()
                );

            } else {

                ps.setInt(6, 0);
                ps.setInt(7, 0);
            }

            ps.setDate(
                    8,
                    java.sql.Date.valueOf(
                            user.getDateInscription()
                    )
            );

            ps.executeUpdate();

            /*
             * ID généré.
             */
            try (ResultSet rs =
                         ps.getGeneratedKeys()) {

                if (rs.next()) {

                    user.setId(
                            rs.getInt(1)
                    );
                }
            }

            System.out.println(
                    "✓ Utilisateur sauvegardé."
            );

        } catch (Exception e) {

            System.err.println(
                    "Erreur sauvegarde utilisateur : "
                            + e.getMessage()
            );
        }

        return user;
    }

    @Override
    public User trouverParEmail(
            String email
    ) {

        String sql = """
            SELECT *
            FROM utilisateur
            WHERE email = ?
        """;

        try (

                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(sql)
        ) {

            ps.setString(1, email);

            try (ResultSet rs =
                         ps.executeQuery()) {

                if (rs.next()) {

                    EnumRole role =
                            EnumRole.valueOf(
                                    rs.getString("role")
                            );

                    User user;

                    /*
                     * Création dynamique selon rôle.
                     */
                    if (role == EnumRole.ADMIN) {

                        user = new Admin();

                    } else {

                        Utilisateur utilisateur =
                                new Utilisateur();

                        utilisateur.setStreakActuel(
                                rs.getInt("streak_actuel")
                        );

                        utilisateur.setMeilleurStreak(
                                rs.getInt("meilleur_streak")
                        );

                        user = utilisateur;
                    }

                    user.setId(
                            rs.getInt("id")
                    );

                    user.setNom(
                            rs.getString("nom")
                    );

                    user.setPrenom(
                            rs.getString("prenom")
                    );

                    user.setEmail(
                            rs.getString("email")
                    );

                    user.setMotDePasse(
                            rs.getString("mot_de_passe")
                    );

                    user.setRole(role);

                    user.setDateInscription(
                            rs.getDate("date_inscription")
                                    .toLocalDate()
                    );

                    return user;
                }
            }

        } catch (Exception e) {

            System.err.println(
                    "Erreur recherche utilisateur : "
                            + e.getMessage()
            );
        }

        return null;
    }

    @Override
    public boolean emailExiste(
            String email
    ) {

        String sql = """
            SELECT id
            FROM utilisateur
            WHERE email = ?
        """;

        try (

                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(sql)
        ) {

            ps.setString(1, email);

            try (ResultSet rs =
                         ps.executeQuery()) {

                return rs.next();
            }

        } catch (Exception e) {

            System.err.println(
                    "Erreur vérification email : "
                            + e.getMessage()
            );
        }

        return false;
    }

	@Override
	public void mettreAJourStreak(Utilisateur utilisateur) {
		String sql = """
		        UPDATE utilisateur
		        SET streak_actuel = ?,
		            meilleur_streak = ?
		        WHERE id = ?
		    """;

		    try (

		            Connection connection = DatabaseConnection.getConnection();

		            PreparedStatement ps = connection.prepareStatement(sql)
		    ) {

		        ps.setInt( 1, utilisateur.getStreakActuel());

		        ps.setInt(2, utilisateur.getMeilleurStreak());

		        ps.setInt(3,utilisateur.getId());

		        ps.executeUpdate();

		    } catch (Exception e) {

		        System.err.println( "Erreur mise à jour streak : " + e.getMessage()
		        );
		    }
		
	}

	@Override
	public int nbUtilsateurs() {
		// TODO Auto-generated method stub
		String sql = """
		        SELECT count(id)
		        FROM utilisateur
		    """;

		    try (

		            Connection connection = DatabaseConnection.getConnection();

		            PreparedStatement ps = connection.prepareStatement(sql);
		    		
		    		ResultSet rs = ps.executeQuery();
		    ) {

		        if (rs.next()) {
					return rs.getInt(1);
				}

		    } catch (Exception e) {

		        System.err.println( "Erreur de compte du nombre utilisateurs " + e.getMessage()
		        );
		    }
		return 0;
	}

	@Override
	public List<Utilisateur> listeUtilisateurs() {
		// TODO Auto-generated method stub
		String sql = """
		        SELECT *
		        FROM utilisateur
		    """;
		
		List<Utilisateur> utilisateurs = new ArrayList();

		    try (

		            Connection connection = DatabaseConnection.getConnection();

		            PreparedStatement ps = connection.prepareStatement(sql);
		    		
		    		ResultSet rs = ps.executeQuery();
		    ) {

		    	while (rs.next()) {
		            Utilisateur user = new Utilisateur();
		            user.setId(rs.getInt("id"));
		            user.setNom(rs.getString("nom")); // Adapter selon les colonnes et setters
		            user.setPrenom(rs.getString("prenom"));
		            user.setEmail(rs.getString("email"));
		            
		            if (rs.getString("role").equals(EnumRole.UTILISATEUR)) {
			            user.setRole(EnumRole.UTILISATEUR);						
					} else {
						user.setRole(EnumRole.ADMIN);	
					}
		            // ... autres champs
		            utilisateurs.add(user);
		        }

		    } catch (Exception e) {

		        System.err.println( "Erreur lors de la récupération des utilisateurs " + e.getMessage()
		        );
		    }
		return utilisateurs;
	}
}