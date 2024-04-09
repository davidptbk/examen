package model;

import database.CRUD;
import database.ConfigDB;
import entity.Coder;
import entity.Contratacion;
import entity.Empresa;
import entity.Vacante;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContratacionModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Contratacion objContratacion = (Contratacion) obj;

        try {
            String sql = "INSERT INTO cita (vacante_id, coder_id , fecha_aplicacion, estado, salario ) VALUES( ?, ?, ?, ?, ?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);


            objPrepare.setInt(1, objContratacion.getVacanteId());
            objPrepare.setInt(2, objContratacion.getCoderId());
            objPrepare.setString(3, objContratacion.getFechaAplicacion());
            objPrepare.setString(4, objContratacion.getEstado());
            objPrepare.setDouble(5, objContratacion.getSalario());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();
            while (objResult.next()){
                objContratacion.setId(objResult.getInt(1));
                JOptionPane.showMessageDialog(null, "Contratacion creada correctamente");
            }
        }catch (Exception error){
            System.out.println(error.getMessage());
        }
        ConfigDB.closeConnection();
        return objContratacion;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listContratos = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT * FROM contratacion " +
                    "INNER JOIN coder ON coder.id = contratacion.coder_id " +
                    "INNER JOIN vacante ON vacante.id = contratacion.vacante_id" +
                    "INNER JOIN empresa ON empresa.id = vacante.empresa_id;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Contratacion objContratacion = new Contratacion();
                Coder objCoder = new Coder();
                Vacante objVacante = new Vacante();
                Empresa objEmpresa = new Empresa();

                objContratacion.setId(objResult.getInt("contratacion.id"));
                objContratacion.setVacanteId(objResult.getInt("contratacion.vacante_id"));
                objContratacion.setCoderId(objResult.getInt("contratacion.coder_id"));
                objContratacion.setEstado(objResult.getString("contratacion.estado"));
                objContratacion.setSalario(objResult.getDouble("contratacion.salario"));


                objCoder.setId(objResult.getInt("coder.id"));
                objCoder.setNombre(objResult.getString("coder.nombre"));
                objCoder.setApellidos(objResult.getString("coder.apellidos"));
                objCoder.setDocumento(objResult.getString("coder.documento"));
                objCoder.setCohorte(objResult.getInt("coder.cohorte"));
                objCoder.setCv(objResult.getString("coder.cv"));
                objCoder.setClan(objResult.getString("coder.clan"));

                objVacante.setId(objResult.getInt("vacante.id"));
                objVacante.setTitulo(objResult.getString("vacante.titulo"));
                objVacante.setDecripcion(objResult.getString("vacante.apellidos"));
                objVacante.setDuracion(objResult.getString("vacante.duracion"));
                objVacante.setEstado(objResult.getString("vacante.estado"));
                objVacante.setTecnologia(objResult.getString("vacante.tecnologia"));

                objEmpresa.setId(objResult.getInt("empresa.id"));
                objEmpresa.setNombre(objResult.getString("empresa.nombre"));
                objEmpresa.setSector(objResult.getString("empresa.sector"));
                objEmpresa.setUbicacion(objResult.getString("empresa.ubicacion"));
                objEmpresa.setContacto(objResult.getString("empresa.contacto"));

                objContratacion.setObjCoder(objCoder);
                objVacante.setObjEmpresa(objEmpresa);
                objContratacion.setObjVacante(objVacante);

                listContratos.add(objContratacion);
            }

        }catch (SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();
        return listContratos;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Contratacion objContratacion = (Contratacion) obj;
        boolean isUpdate = false;

        try {
            String sql = "UPDATE contratacion SET vacante_id = ?, coder_id = ?, estado = ?, salario=?  WHERE id = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);


            objPrepare.setInt(1, objContratacion.getVacanteId());
            objPrepare.setInt(2, objContratacion.getCoderId());
            objPrepare.setString(3, objContratacion.getEstado());
            objPrepare.setDouble(4,objContratacion.getSalario());
            objPrepare.setInt(5, objContratacion.getId());

            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0){
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "La contratacion se actualizo correctamente");
            }

        }catch (Exception error){
            System.out.println(error.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return isUpdate;
    }

    @Override
    public boolean delete(Object obj) {
        Contratacion objContratacion = (Contratacion) obj;
        Connection objConnection = ConfigDB.openConnection();
        boolean isDelete = false;

        try {
            String sql = "DELETE FROM contratacion WHERE id = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, objContratacion.getId());

            int totalAffectedRows = objPrepare.executeUpdate();

            if (totalAffectedRows > 0){
                isDelete = true;
                JOptionPane.showMessageDialog(null, "La contratacion se elimino correctamente");
            }

        }catch (Exception error){
            System.out.println(error.getMessage());
        }

        ConfigDB.closeConnection();
        return isDelete;
    }

    public Contratacion findContratacionById(int id){
        Connection objConnection = ConfigDB.openConnection();
        Contratacion objContratacion = null;

        try {
            String sql = "SELECT * FROM cita " +
                    "INNER JOIN paciente ON paciente.id = cita.id_paciente " +
                    "INNER JOIN medico ON medico.id = cita.id_medico " +
                    "WHERE cita.id = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, id);

            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()){
                objContratacion = new Contratacion();
                Coder objCoder = new Coder();
                Vacante objVacante = new Vacante();
                Empresa objEmpresa = new Empresa();

                objContratacion.setId(objResult.getInt("contratacion.id"));
                objContratacion.setVacanteId(objResult.getInt("contratacion.vacante_id"));
                objContratacion.setCoderId(objResult.getInt("contratacion.coder_id"));
                objContratacion.setEstado(objResult.getString("contratacion.estado"));
                objContratacion.setSalario(objResult.getDouble("contratacion.salario"));


                objCoder.setId(objResult.getInt("coder.id"));
                objCoder.setNombre(objResult.getString("coder.nombre"));
                objCoder.setApellidos(objResult.getString("coder.apellidos"));
                objCoder.setDocumento(objResult.getString("coder.documento"));
                objCoder.setCohorte(objResult.getInt("coder.cohorte"));
                objCoder.setCv(objResult.getString("coder.cv"));
                objCoder.setClan(objResult.getString("coder.clan"));

                objVacante.setId(objResult.getInt("vacante.id"));
                objVacante.setTitulo(objResult.getString("vacante.titulo"));
                objVacante.setDecripcion(objResult.getString("vacante.apellidos"));
                objVacante.setDuracion(objResult.getString("vacante.duracion"));
                objVacante.setEstado(objResult.getString("vacante.estado"));
                objVacante.setTecnologia(objResult.getString("vacante.tecnologia"));

                objEmpresa.setId(objResult.getInt("empresa.id"));
                objEmpresa.setNombre(objResult.getString("empresa.nombre"));
                objEmpresa.setSector(objResult.getString("empresa.sector"));
                objEmpresa.setUbicacion(objResult.getString("empresa.ubicacion"));
                objEmpresa.setContacto(objResult.getString("empresa.contacto"));

                objContratacion.setObjCoder(objCoder);
                objVacante.setObjEmpresa(objEmpresa);
                objContratacion.setObjVacante(objVacante);

            }

        }catch (Exception error){
            System.out.println(error.getMessage());
        }
        ConfigDB.closeConnection();
        return objContratacion;
    }

}
