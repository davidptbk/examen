package model;

import database.CRUD;
import database.ConfigDB;
import entity.Coder;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoderModel implements CRUD {

    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Coder objCoder = (Coder) obj;

        try {
            String sql = "INSERT INTO coder (nombre, apellidos, documento, cohorte, cv, clan) VALUES( ?,?,?,?,?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objCoder.getNombre());
            objPrepare.setString(2, objCoder.getApellidos());
            objPrepare.setString(3, objCoder.getDocumento());
            objPrepare.setInt(4, objCoder.getCohorte());
            objPrepare.setString(5, objCoder.getCv());
            objPrepare.setString(6, objCoder.getClan());


            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objCoder.setId(objResult.getInt(1));
                JOptionPane.showMessageDialog(null, "Coder creado correctamente");
            }

        }catch (Exception error){
            System.out.println(error.getMessage());
        }

        ConfigDB.closeConnection();
        return objCoder;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listCoders = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT * FROM coder;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Coder objCoder = new Coder();

                objCoder.setId(objResult.getInt("id"));
                objCoder.setNombre(objResult.getString("nombre"));
                objCoder.setApellidos(objResult.getString("apellidos"));
                objCoder.setDocumento(objResult.getString("documento"));
                objCoder.setCohorte(objResult.getInt("cohorte"));
                objCoder.setCv(objResult.getString("cv"));
                objCoder.setClan(objResult.getString("clan"));

                listCoders.add(objCoder);
            }

        }catch (SQLException error){
            System.out.println(error.getMessage());        }

        ConfigDB.closeConnection();
        return listCoders;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Coder objCoder= (Coder) obj;
        boolean isUpdate = false;

        try {
            String sql = "UPDATE coder SET nombre = ?, apellidos = ?, documento=?, cohorte=?, cv=?, clan=? WHERE id = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1, objCoder.getNombre());
            objPrepare.setString(2, objCoder.getApellidos());
            objPrepare.setString(3, objCoder.getDocumento());
            objPrepare.setInt(4, objCoder.getCohorte());
            objPrepare.setString(5, objCoder.getCv());
            objPrepare.setString(6, objCoder.getClan());
            objPrepare.setInt(7, objCoder.getId());

            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0){
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "El coder fue actualizada correctamente");
            }

        }catch (SQLException error){
            System.out.println(error.getMessage());        }
        ConfigDB.closeConnection();
        return isUpdate;
    }

    @Override
    public boolean delete(Object obj) {
        Coder objCoder= (Coder) obj;
        Connection objConnection = ConfigDB.openConnection();
        boolean isDelete = false;

        try {
            String sql = "DELETE FROM coder WHERE id = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, objCoder.getId());

            int totalAffectedRows = objPrepare.executeUpdate();

            if (totalAffectedRows > 0){
                isDelete = true;
                JOptionPane.showMessageDialog(null, "El coder se elimino correctamente");
            }

        }catch (SQLException error){
            System.out.println(error.getMessage());
        }

        ConfigDB.closeConnection();
        return isDelete;
    }

    public Coder findById(int id){
        Connection objConnection = ConfigDB.openConnection();
        Coder objCoder= null;

        try {
            String sql = "SELECT * FROM especialidad WHERE id = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, id);

            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()){
                objCoder = new Coder();
                objCoder.setId(objResult.getInt("id"));
                objCoder.setNombre(objResult.getString("nombre"));
                objCoder.setApellidos(objResult.getString("apellidos"));
                objCoder.setDocumento(objResult.getString("documento"));
                objCoder.setCohorte(objResult.getInt("cohorte"));
                objCoder.setCv(objResult.getString("cv"));
                objCoder.setClan(objResult.getString("clan"));
            }

        }catch (SQLException error){
            System.out.println(error.getMessage());
        }

        ConfigDB.closeConnection();
        return objCoder;
    }

    public  List<Coder> findByCohorte (int cohorte){
        List<Coder> listCoder = new ArrayList<>();

        Connection objConnection = ConfigDB.openConnection();

        try{

            String sql = "SELECT * FROM coder WHERE  cohorte = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, cohorte);

            ResultSet objResult = objPrepare.executeQuery();
            while(objResult.next()){
                Coder objCoder = new Coder();

                objCoder.setId(objResult.getInt("id"));
                objCoder.setNombre(objResult.getString("nombre"));
                objCoder.setApellidos(objResult.getString("apellidos"));
                objCoder.setDocumento(objResult.getString("documento"));
                objCoder.setCohorte(objResult.getInt("cohorte"));
                objCoder.setCv(objResult.getString("cv"));
                objCoder.setClan(objResult.getString("clan"));

                listCoder.add(objCoder);
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        ConfigDB.closeConnection();
        return listCoder;
    }

    public  List<Coder> findByClan (String clan){
        List<Coder> listCoder = new ArrayList<>();

        Connection objConnection = ConfigDB.openConnection();

        try{

            String sql = "SELECT * FROM coder WHERE  clan = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,clan);

            ResultSet objResult = objPrepare.executeQuery();
            while(objResult.next()){
                Coder objCoder = new Coder();
                objCoder.setId(objResult.getInt("id"));
                objCoder.setNombre(objResult.getString("nombre"));
                objCoder.setApellidos(objResult.getString("apellidos"));
                objCoder.setDocumento(objResult.getString("documento"));
                objCoder.setCohorte(objResult.getInt("cohorte"));
                objCoder.setCv(objResult.getString("cv"));
                objCoder.setClan(objResult.getString("clan"));

                listCoder.add(objCoder);
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        ConfigDB.closeConnection();
        return listCoder;
    }

    public  List<Coder> findByTecnolgoia (String tecnologia){
        List<Coder> listCoder = new ArrayList<>();

        Connection objConnection = ConfigDB.openConnection();

        try{

            String sql = "SELECT * FROM coder WHERE  cv LIKE ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,"%"+tecnologia+"%");

            ResultSet objResult = objPrepare.executeQuery();
            while(objResult.next()){
                Coder objCoder = new Coder();
                objCoder.setId(objResult.getInt("id"));
                objCoder.setNombre(objResult.getString("nombre"));
                objCoder.setApellidos(objResult.getString("apellidos"));
                objCoder.setDocumento(objResult.getString("documento"));
                objCoder.setCohorte(objResult.getInt("cohorte"));
                objCoder.setCv(objResult.getString("cv"));
                objCoder.setClan(objResult.getString("clan"));

                listCoder.add(objCoder);
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        ConfigDB.closeConnection();
        return listCoder;
    }
}
