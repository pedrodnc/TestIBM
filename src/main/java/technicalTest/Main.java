package technicalTest;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3702/test";
		String username = "pedro";
		String password = "admin";
		
		try {
			
			Connection con= DriverManager.getConnection(url,username,password);
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT * FROM test.proveedores WHERE id_cliente ='"+args+"'");
			
			while(rs.next()) {
				int idProveedor=rs.getInt("id_proveedor");
				String nombre=rs.getString("nombre");
				Date fechaAlta=rs.getDate("fechadeAlta");
				int idCliente=rs.getInt("id_cliente");
				
				System.out.format("%s, %s, %s, %s,\n", idProveedor, nombre, fechaAlta, idCliente);
			}
			
			stmt.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

}
