import java.sql.*;

public class oProducte {
	int codi;
	String nom;
	int stock;
	Double preu;
	Double iva;
	int quantitat;
	
	public oProducte() 
	{
		
	}
	
	public static oProducte tornaProducte(int codiProducte, int quantitat,Connection connexioPsql) throws Exception 
	{
		
		oProducte producte = new oProducte();
		boolean ya = false;
		// estat
		Statement stmt=connexioPsql.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
		// resultat
		ResultSet resultat = stmt.executeQuery("SELECT * FROM productes WHERE codi = "+codiProducte+";");
		while(resultat.next() && !ya) 
		{
			if(resultat.getInt("codi") == codiProducte) 
			{
				producte.codi = resultat.getInt("codi");
				producte.nom = resultat.getString("nom");
				producte.quantitat = quantitat;
				producte.preu = resultat.getDouble("preu");
				producte.iva = resultat.getDouble("iva");
				ya = true;
			}
		}
		return producte;
		
	}

	public static void llistarProductes(Connection connexioPsql) throws Exception {
		// TODO Auto-generated method stub
		// estat
			Statement stmt=connexioPsql.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
			// resultat
			ResultSet resultat = stmt.executeQuery("SELECT * FROM productes;");
			System.out.println("|--------------------------------------------------------|");
			System.out.println("|                        Productes                       |");
			System.out.println("|--------------------------------------------------------|");
			while(resultat.next())
			{
				System.out.println("   Codi Producte:  " + resultat.getString("codi"));
				
				System.out.println("      Nom Producte -- " + resultat.getString("nom") + " \n      Preu Producte -- " + resultat.getDouble("preu") + " \n      IVA Producte -- " + resultat.getInt("iva") + " ");
				System.out.println("");
				System.out.println("----------------------------------------------------------");
				System.out.println("");
			}
			
	}

	public static int comprovaExistenciaProdu(int codiProducte,Connection connexioPsql) throws Exception
	{
		int returnInt = -1;
		// estat
		Statement stmt=connexioPsql.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
		// resultat
		ResultSet resultat = stmt.executeQuery("SELECT * FROM productes WHERE codi = "+codiProducte+";");
		if(codiProducte == 0) return returnInt = 2;
		if(resultat.next()) return returnInt = 0;
		else return returnInt = 1;
	}

	public static int verificarQuantitat(int codiProducte, int quantitatProducte, Connection connexioPsql) throws SQLException {
		int resultat = -1;
		
		Statement stmt=connexioPsql.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
		// resultat
		ResultSet resultatQuery = stmt.executeQuery("SELECT * FROM productes WHERE codi = "+codiProducte+";");
		resultatQuery.next();
		if(quantitatProducte <= resultatQuery.getInt("stock")) 
		{
			return resultat = 0;
		}
		else return resultat = 1;
	}

	public int getCodi() {
		return codi;
	}

	public void setCodi(int codi) {
		this.codi = codi;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Double getPreu() {
		return preu;
	}

	public void setPreu(Double preu) {
		this.preu = preu;
	}

	public Double getIva() {
		return iva;
	}

	public void setIva(Double iva) {
		this.iva = iva;
	}

	public int getQuantitat() {
		return quantitat;
	}

	public void setQuantitat(int quantitat) {
		this.quantitat = quantitat;
	}
}
