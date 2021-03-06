package fr.mvc.metier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import fr.mvc.model.*;
import fr.mvc.connection.AccesBD;

public class Requetes {

	/**
	 * M�thode ajouter un nouvel apprenant
	 * 
	 * @param apprenant
	 * @throws SQLException
	 */
	public static void ajouterApprenant(Apprenant apprenant) throws SQLException {
		try {
			PreparedStatement prepareStatement = AccesBD.getConnection()
					.prepareStatement("INSERT INTO Apprenant VALUES( ? , ? , ? , ? , ? , ?, ?)");
			prepareStatement.setInt(1, apprenant.getId());
			prepareStatement.setString(2, apprenant.getPrenom());
			prepareStatement.setString(3, apprenant.getName());
			prepareStatement.setDate(4, apprenant.getDateDeNaissance());
			prepareStatement.setString(5, apprenant.getEmail());
			prepareStatement.setString(6, apprenant.getPhoto());
			prepareStatement.setInt(7, apprenant.getId_region());
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erreur à la création !");
			e.fillInStackTrace();
		}

	}

	/**
	 * m�thode pour modifier le nom d'un apprenant
	 * 
	 * @param apprenant
	 * @throws SQLException
	 */
	public static void modifierApprenant(Apprenant apprenant) throws SQLException {
		try {
			PreparedStatement prepareStatement = AccesBD.getConnection()
					.prepareStatement("UPDATE apprenant SET nom = ? WHERE id_apprenant = ? ");
			prepareStatement.setString(1, apprenant.getName());
			prepareStatement.setInt(2, apprenant.getId());
			prepareStatement.executeUpdate();
			System.out.println("Modification effectué pour l' apprenant : " + apprenant);

		} catch (SQLException e) {
			System.out.println("Erreur lors de la modification !");
		}
	}

	/**
	 * m�thode pour effacer un apprenant.
	 * 
	 * @param apprenant
	 * @throws SQLException
	 */

	public static void supprimerapprenant(Apprenant apprenant) throws SQLException {
		Statement statement = null;

		try {
			statement = AccesBD.getConnection().createStatement();
			String sql = "DELETE FROM Apprenant WHERE id_apprenant=" + apprenant.getId();
			statement.executeUpdate(sql);
			System.out.println("Suppression de " + apprenant + " effectué");
		} catch (SQLException e) {
			System.out.println("Erreur lors de la suppression du apprenant !");
		}
	}

	/**
	 * M�thode pour retourner tous les apprenants dans un tableau
	 *
	 * @return ArrayList<Apprenant>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ArrayList<Apprenant> getAllApprenant() throws ClassNotFoundException, SQLException

	{
		ArrayList<Apprenant> apprenants = new ArrayList<Apprenant>();
		String requete = "SELECT * FROM Apprenant ORDER  BY nom";
		ResultSet resultat = AccesBD.executerQuery(requete);
		while (resultat.next()) {
			Apprenant p = Mapping.mapperApprenant(resultat);
			apprenants.add(p);
		}
		return apprenants;
	}

	/**
	 * M�thode ajouter une nouvelle region
	 * 
	 * @param region
	 * @throws SQLException
	 */
	public static void ajouterRegion(Region region) throws SQLException {
		PreparedStatement prepareStatement = AccesBD.getConnection()
				.prepareStatement("INSERT INTO `Region` VALUES(? , ?)");
		prepareStatement.setInt(1, region.getId());
		prepareStatement.setString(2, region.getName());
		prepareStatement.executeUpdate();

	}

	/**
	 * m�thode pour modifier une nouvelle region
	 * 
	 * @param region
	 * @throws SQLException
	 */
	public static void modifierRegion(Region region) throws SQLException {
		try {
			PreparedStatement prepareStatement = AccesBD.getConnection()
					.prepareStatement("UPDATE Region SET nom = ? WHERE id_region= ? ");
			prepareStatement.setString(1, region.getName());
			prepareStatement.setInt(2, region.getId());
			prepareStatement.executeUpdate();
			System.out.println("Modification effectué pour la region: " + region);

		} catch (SQLException e) {
			System.out.println("Erreur lors de la modification !");
		}
	}

	/**
	 * m�thode pour effacer une nouvelle region
	 * 
	 * @param region
	 * @throws SQLException
	 */

	public static void supprimerRegion(Region region) throws SQLException {
		Statement statement = null;

		try {
			statement = AccesBD.getConnection().createStatement();
			String sql = "DELETE FROM Region WHERE id_region=" + region.getId();
			statement.executeUpdate(sql);
			System.out.println("Suppression de " + region + " effectué");
		} catch (SQLException e) {
			System.out.println("Erreur lors de la suppression!");
		}
	}

	/**
	 * M�thode pour retourner tous les Regions dans un tableau
	 *
	 * @return ArrayList<Region>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ArrayList<Region> getAllRegion() throws ClassNotFoundException, SQLException

	{
		ArrayList<Region> regions = new ArrayList<Region>();
		String requete = "SELECT * FROM Region ORDER  BY nom";
		ResultSet resultat = AccesBD.executerQuery(requete);
		while (resultat.next()) {
			Region p = Mapping.mapperRegion(resultat);
			regions.add(p);
		}
		return regions;
	}

	/**
	 * M�thode pour retourner tous les activites dans un tableau
	 *
	 * @return ArrayList<Activites>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ArrayList<Activites> getAllActivite() throws ClassNotFoundException, SQLException

	{
		ArrayList<Activites> activites = new ArrayList<Activites>();
		String requete = "SELECT * FROM Activite ORDER  BY nom";
		ResultSet resultat = AccesBD.executerQuery(requete);
		while (resultat.next()) {
			Activites p = Mapping.mapperActivite(resultat);
			System.out.println(p);
			activites.add(p);
		}
		return activites;
	}

	/**
	 * M�thode ajouter une nouvelle activité.
	 * 
	 * @param activite
	 * @throws SQLException
	 */
	public static void ajouterActivite(Activites activite) throws SQLException {
		PreparedStatement prepareStatement = AccesBD.getConnection()
				.prepareStatement("INSERT INTO `Activite` VALUES(?, ?)");
		prepareStatement.setInt(1, activite.getId());
		prepareStatement.setString(2, activite.getName());
		prepareStatement.executeUpdate();

	}

	/**
	 * M�thode ajouter une nouvelle activité à 1 args
	 * 
	 * @param activite
	 * @throws SQLException
	 */
	public static void ajouterActiviteUniqueArgs(Activites activite) throws SQLException {
		PreparedStatement prepareStatement = AccesBD.getConnection()
				.prepareStatement("INSERT INTO `Activite` VALUES( ?)");
		prepareStatement.setString(2, activite.getName());
		prepareStatement.executeUpdate();

	}

	/**
	 * methode pour afficher region
	 * 
	 * @return String
	 * @param region_id
	 * @throws SQLException
	 */


	public static String afficherRegion(int region_id) throws SQLException , ClassNotFoundException{

		String sql = "SELECT * FROM Region WHERE id_region =  " + region_id +" ;";
		ResultSet resultat = AccesBD.executerQuery(sql);
		resultat.next();
		Region region = Mapping.mapperRegion(resultat);
		String nomRegion = region.getName();
		return nomRegion;
	}

	/**
	 * m�thode pour modifier une nouvelle activité.
	 * 
	 * @param activite
	 * @throws SQLException
	 */
	public static void modifierActivite(Activites activite) throws SQLException {
		try {
			PreparedStatement prepareStatement = AccesBD.getConnection()
					.prepareStatement("UPDATE Activite SET nom = ? WHERE id_activite = ? ");
			prepareStatement.setString(1, activite.getName());
			prepareStatement.setInt(2, activite.getId());
			prepareStatement.executeUpdate();
			System.out.println("Modification effectué pour l' activité : " + activite);

		} catch (SQLException e) {
			System.out.println("Erreur lors de la modification !");
		}
	}

	/**
	 * m�thode pour supprimer une nouvelle activité.
	 * 
	 * @param activite
	 * @throws SQLException
	 */

	public static void supprimerActivite(Activites activite) throws SQLException {
		Statement statement = null;

		try {
			statement = AccesBD.getConnection().createStatement();
			String sql = "DELETE FROM Activite WHERE NOM = " +"\'" + activite.getName() + "\'";
			statement.executeUpdate(sql);
			System.out.println("Suppression de " + activite + " effectué");
		} catch (SQLException e) {
			System.out.println("Erreur lors de la suppression d' une activité !");
		}
	}

	/**
	 * m�thode pour afficher les apprenants.
	 * 
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static  void afficherApprenants() throws SQLException, ClassNotFoundException {
		ArrayList apprenants= Requetes.getAllApprenant();
		System.out.println("Voici la liste des apprenants :");
		System.out.println();
		for(int i =0; i < apprenants.size();i++ ){
			apprenants.get(i);
			System.out.println(apprenants.get(i).toString());
		}

	}

	public static void afficherApprenantsParRegion() throws SQLException, ClassNotFoundException {
		Statement statement = null;
		String result="";
		System.out.println("Voici la liste des apprenants classée par région :");
		System.out.println();
		for (int i=1; i<=3; i++) {
			result = afficherRegion(i);
			System.out.println(i + "./ " + result);
			System.out.println();		
			
			try {
				statement = AccesBD.getConnection().createStatement();
				ArrayList<Apprenant> apprenants = new ArrayList<Apprenant>();
				String requete = "SELECT * FROM Apprenant WHERE id_region=" + i;
				ResultSet resultat = AccesBD.executerQuery(requete);
				while (resultat.next()) {
					Apprenant p = Mapping.mapperApprenant(resultat);
					apprenants.add(p);
				}

				for (int j = 0; j < apprenants.size(); j++) {
					// apprenants.get(j);
					System.out.println(apprenants.get(j).toString());
				}
				System.out.println();

			} catch (SQLException e) {
				System.out.println("Erreur lors de l'affichage");
			}

		}

	}

	public static void afficherActivitesParApprenant(String name) throws SQLException, ClassNotFoundException {
		Statement statement = null;
		try {
			statement = AccesBD.getConnection().createStatement();
			ArrayList<Activites> activites = new ArrayList<Activites>();
			String requete = "SELECT Activite.* FROM Activite, Effectuer, Apprenant WHERE (Apprenant.nom = '"
			+ name + "' and Effectuer.id_apprenant = Apprenant.id_apprenant and Effectuer.id_activite = Activite.id_activite)";
			ResultSet resultat = AccesBD.executerQuery(requete);
			while (resultat.next()) {
				Activites p = Mapping.mapperActivite (resultat);
				activites.add(p);
			}
			System.out.println("Voici les activités de l'apprenant " + name);
			System.out.println(activites);

		} catch (SQLException e) {
			System.out.println("Erreur lors de l'affichage");
		}
	}

	public static void afficherApprenantsParActivite(String nomActivite) throws SQLException, ClassNotFoundException {
		Statement statement = null;
		try {
			statement = AccesBD.getConnection().createStatement();
			ArrayList<Apprenant> apprenants = new ArrayList<Apprenant>();
			String requete = "select apprenant.* from apprenant, effectuer, activite where (activite.nom = '" + nomActivite + "' and effectuer.id_apprenant = apprenant.id_apprenant and effectuer.id_activite = activite.id_activite)";
			ResultSet resultat = AccesBD.executerQuery(requete);
			while (resultat.next()) {
				Apprenant p = Mapping.mapperApprenant(resultat);
				apprenants.add(p);
			}

			System.out.println("Voici la liste des apprenants qui ont pour activite " + nomActivite + " :");
			System.out.println();

			for(int j =0; j < apprenants.size(); j++ ){
				// apprenants.get(j);
				System.out.println(apprenants.get(j).toString());
			}
			System.out.println();

		} catch (SQLException e) {
			System.out.println("Erreur lors de l'affichage");
		}
		
	}

	/**
	 * m�thode pour créer arraylist d'activité non pratiquées.
	 * 
	 * @returns ArrayList<Activites>
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */

	public static ArrayList<Activites> getActivityNeverDone() throws SQLException, ClassNotFoundException {
		String sql = "select Activite.id_activite,Activite.nom from Activite left join Effectuer on Activite.id_activite = Effectuer.id_activite where Effectuer.id_activite is null";
		ResultSet res = AccesBD.executerQuery(sql);
		ArrayList<Activites> neverDoneActivites = new ArrayList<Activites>();
		while (res.next()) {
			Activites activite = Mapping.mapperActivite(res);
			neverDoneActivites.add(activite);
		}
		return neverDoneActivites;
	}

	/**
	 * m�thode pour afficher les activités non-pratiquer.
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void afficherActiviteNeverDone() throws SQLException, ClassNotFoundException {
		ArrayList res = Requetes.getActivityNeverDone();
		int i = 0;
		while (i < res.size()) {
			System.out.println(res.get(i).toString());
			i++;
		}

	}

	/**
	 * m�thode pour ajouter deux activités.
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void ajouteDeuxActivites(Apprenant apprenant) {
		String sql = "insert into Effectuer set id_activite = ?, id_apprenant = ?";
		try {
			PreparedStatement prepareStatement = AccesBD.getConnection().prepareStatement(sql);
			prepareStatement.setInt(1, 8);
			prepareStatement.setInt(2, apprenant.getId());
			prepareStatement.executeUpdate();
			System.out.println(
					"Modification effectuée pour: " + apprenant.getName());

		} catch (SQLException e) {
			System.out.println("Erreur lors de la modification !");
		}
		try {
			PreparedStatement prepareStatement = AccesBD.getConnection().prepareStatement(sql);
			prepareStatement.setInt(1,9);
			prepareStatement.setInt(2, apprenant.getId());
			prepareStatement.executeUpdate();
			System.out.println(
					"Modification effectuée pour: " + apprenant.getName());

		} catch (SQLException e) {
			System.out.println("Erreur lors de la modification !");
		}
	}
}
