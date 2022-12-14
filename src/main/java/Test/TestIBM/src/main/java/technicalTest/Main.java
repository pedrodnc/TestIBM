package Test.TestIBM.src.main.java.technicalTest;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class Main {

	public static void main(String[] args) {
		//URL a la bbdd local, con usario y pwd creados previamente
		String url = "jdbc:mysql://localhost:3702/test";
		String username = "pedro";
		String password = "admin";
		
		//Comprobacion si hay clientes asignados
		if(args[0].contentEquals("5")||args[0].contentEquals("6")) {
			
			try {
				//Conexion a la bbdd, prepared statement para almacenar la query, resulset para recoger los datos extraidos
				Connection con= DriverManager.getConnection(url,username,password);
				//El id_cliente es pasado por argumentos
				PreparedStatement smt=con.prepareStatement("SELECT * FROM test.proveedores WHERE id_cliente ='"+args[0]+"'");
				
				ResultSet rs=smt.executeQuery();
				//ArrayList para almacenar los datos
				ArrayList<Object> lista= new ArrayList<Object>();
				
				FileOutputStream fos = new FileOutputStream("output.txt");
			    ObjectOutputStream oos = new ObjectOutputStream(fos); 
			    //Bucle para rellenar la List con los datos extraidos
				while(rs.next()) {
					int idProveedor=rs.getInt("id_proveedor");
					String nombre=rs.getString("nombre");
					Date fechaAlta=rs.getDate("fechadeAlta");
					int idCliente=rs.getInt("id_cliente");
					lista.add(idProveedor);
					lista.add(nombre);
					lista.add(fechaAlta);
					lista.add(idCliente);
					//Escribo en el fichero ,los datos que existen en la lista
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
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {System.out.println("El cliente no tiene proveedores asignados");}	
	}

}
