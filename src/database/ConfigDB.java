package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {
    //Este atributo tendra el estado de la conexion.
    public static Connection objConnection = null;

    //Metodo para conectar la BD
    public static Connection openConnection(){

        try {
            //siempre es este link
            Class.forName("com.mysql.cj.jdbc.Driver");

            //creamos las variables de conexion
            // la url es siempre igual solo cambia el localhoost si tenemos la bd en linea,
            // el puerto se modifica solo si se cambio anteriormente
            // y el nombre de la base de datos a la del proyecto que estemos trabajando sea en linea o local
            String url = "jdbc:mysql://localhost:3306/examen";
            //User por defecto es root a menos que se cambie
            String user = "root";
            //la contraseña de la base de datos si tiene
            String password = "";

            //establecer la conexion
            objConnection =(Connection) DriverManager.getConnection(url,user,password);
            System.out.println("me conecte correctamente");

        }catch (ClassNotFoundException error){
            System.out.println("Driver no instalado");
        }catch (SQLException error) {
            System.out.println("error en la conexion con bases de datos"+ error.getMessage());
        }
        return objConnection;
    }

    //Metodo para finalizar una conexion
    public static void closeConnection(){
        try {
            //si hay una conexion se procede a cerrar la conexion
            if (objConnection != null) {
                objConnection.close();
                System.out.println("Cerrada exitosamente");
            }
        }catch(SQLException error){
            System.out.println("Error :"+error.getMessage());
        }
    }
}
