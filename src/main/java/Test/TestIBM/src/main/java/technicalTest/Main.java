package Test.TestIBM.src.main.java.technicalTest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/test";
		String username = "root";
		String password = "root";
		if(args[0].contentEquals("5")||args[0].contentEquals("6")) {
			
			try {
				Connection con= DriverManager.getConnection(url,username,password);
				PreparedStatement smt=con.prepareStatement("SELECT * FROM test.proveedores WHERE id_cliente ='"+args[0]+"'");
				
				ResultSet rs=smt.executeQuery();
				
				ArrayList<Object> lista= new ArrayList<Object>();
				
				FileOutputStream fos = new FileOutputStream("output.txt");
			    ObjectOutputStream oos = new ObjectOutputStream(fos); 
			    
				while(rs.next()) {
					int idProveedor=rs.getInt("id_proveedor");
					String nombre=rs.getString("nombre");
					Date fechaAlta=rs.getDate("fechadeAlta");
					int idCliente=rs.getInt("id_cliente");
					lista.add(idProveedor);
					lista.add(nombre);
					lista.add(fechaAlta);
					lista.add(idCliente);
					
					for(int i=0;i<lista.size();i++){
				        oos.writeObject(lista.get(i));
				    }
			        
			        
					System.out.format("%s, %s, %s, %s,\n", idProveedor, nombre, fechaAlta, idCliente);
				}
				oos.close();
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {System.out.println("El cliente no tiene proveedores asignados");}
		
		ArrayList<Object> lista= new ArrayList<Object>();
		
	}

}
