package Test.TestIBM.src.main.java.technicalTest;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3702/test";
		String username = "root";
		String password = "root";
		if(args[0].contentEquals("5")||args[0].contentEquals("6")) {
			
			try {
				Connection con= DriverManager.getConnection(url,username,password);
				PreparedStatement smt=con.prepareStatement("SELECT * FROM test.proveedores WHERE id_cliente ='"+args[0]+"'");
				
				ResultSet rs=smt.executeQuery();
				while(rs.next()) {
					int idProveedor=rs.getInt("id_proveedor");
					String nombre=rs.getString("nombre");
					Date fechaAlta=rs.getDate("fechadeAlta");
					int idCliente=rs.getInt("id_cliente");
					
					System.out.format("%s, %s, %s, %s,\n", idProveedor, nombre, fechaAlta, idCliente);
				}
				
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}else {System.out.println("El cliente no tiene proveedores asignados");}
		
	}

}
