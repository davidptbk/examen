package model;

import database.CRUD;
import database.ConfigDB;
import entity.Coder;
import entity.Empresa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpresaModel {


    public List<Object> findAll() {
        List<Object> listEmpresa = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT * FROM empresa;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Empresa objEmpresa = new Empresa();

                objEmpresa.setId(objResult.getInt("id"));
                objEmpresa.setNombre(objResult.getString("nombre"));
                objEmpresa.setSector(objResult.getString("sector"));
                objEmpresa.setUbicacion(objResult.getString("ubicacion"));
                objEmpresa.setContacto(objResult.getString("contacto"));

                listEmpresa.add(objEmpresa);
            }

        }catch (SQLException error){
            System.out.println(error.getMessage());        }

        ConfigDB.closeConnection();
        return listEmpresa;
    }

}
