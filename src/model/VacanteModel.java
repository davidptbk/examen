package model;

import database.CRUD;
import database.ConfigDB;
import entity.Empresa;
import entity.Vacante;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VacanteModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Vacante objVacante= (Vacante) obj;

        try {
            String sql= "INSERT INTO vacante(empresa_id, titulo,descripcion,duracion,estado,tecnologia) VALUES (?,?,?,?,?,?);";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            objPrepare.setInt(1, objVacante.getEmpresaId());
            objPrepare.setString(2,objVacante.getTitulo());
            objPrepare.setString(3,objVacante.getDecripcion());
            objPrepare.setString(4,objVacante.getDuracion());
            objPrepare.setString(5,objVacante.getEstado());
            objPrepare.setString(6,objVacante.getTecnologia());


            objPrepare.execute();
            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objVacante.setId(objResult.getInt(1));
                JOptionPane.showMessageDialog(null, "Vacante creada correctamente");

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return findById(objVacante.getId());
    }

    @Override
    public List<Object> findAll() {
        List<Object> listVacantes = new ArrayList<>();

        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT * FROM vacante INNER JOIN empresa ON empresa.id = vacante.empresa_id;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Vacante objVacante = new Vacante();
                Empresa objEmpresa = new Empresa();
                objVacante.setId(objResult.getInt(1));
                objVacante.setEmpresaId(objResult.getInt("vacante.empresa_id"));
                objVacante.setTitulo(objResult.getString("vacante.titulo"));
                objVacante.setDecripcion(objResult.getString("vacante.descripcion"));
                objVacante.setDuracion(objResult.getString("vacante.duracion"));
                objVacante.setEstado(objResult.getString("vacante.estado"));
                objVacante.setTecnologia(objResult.getString("vacante.tecnologia"));

                objEmpresa.setId(objResult.getInt("empresa.id"));
                objEmpresa.setNombre(objResult.getString("empresa.nombre"));
                objEmpresa.setSector(objResult.getString("empresa.sector"));
                objEmpresa.setUbicacion(objResult.getString("empresa.ubicacion"));
                objEmpresa.setContacto(objResult.getString("empresa.contacto"));

                objVacante.setObjEmpresa(objEmpresa);

                listVacantes.add(objVacante);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return listVacantes;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Vacante objVacante = (Vacante) obj;

        try{

            String sql = "UPDATE vacante SET empresa_id=?,titulo=?,descripcion=?,duracion=?,estado=? WHERE id=?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, objVacante.getEmpresaId());
            objPrepare.setString(2,objVacante.getTitulo());
            objPrepare.setString(3,objVacante.getDecripcion());
            objPrepare.setString(4,objVacante.getDuracion());
            objPrepare.setString(5,objVacante.getEstado());
            objPrepare.setString(6,objVacante.getTecnologia());
            objPrepare.setInt(7,objVacante.getId());

            int totalRowsAfected = objPrepare.executeUpdate();


            if (totalRowsAfected > 0){
                JOptionPane.showMessageDialog(null,"La Vacante se ha actualizado correctamente" +objVacante.toString());
            }


        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
            return false;
        }finally {
            ConfigDB.closeConnection();
        }
        return true;
    }

    @Override
    public boolean delete(Object obj) {
        Vacante objVacante = (Vacante) obj;
        Connection objConnection = ConfigDB.openConnection();
        boolean isDelete = false;

        try {
            String sql = "DELETE FROM vacante WHERE id = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, objVacante.getId());

            int totalAffectedRows = objPrepare.executeUpdate();

            if (totalAffectedRows > 0){
                isDelete = true;
                JOptionPane.showMessageDialog(null, "La Vacante se elimino correctamente");
            }

        }catch (SQLException error){
            System.out.println(error.getMessage());
        }
        ConfigDB.closeConnection();
        return isDelete;
    }

    public Vacante findById (int id){

        Connection objConnection = ConfigDB.openConnection();
        Empresa objEmpresa = new Empresa();
        Vacante objVacante = null;

        try{

            String sql = "SELECT * FROM vacante INNER JOIN empresa ON empresa.id = vacante.empresa_id WHERE vacante.id =?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,id);

            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()){
                objVacante = new Vacante();
                objVacante.setId(objResult.getInt(1));
                objVacante.setEmpresaId(objResult.getInt("empresa_id"));
                objVacante.setTitulo(objResult.getString("titulo"));
                objVacante.setDecripcion(objResult.getString("descripcion"));
                objVacante.setDuracion(objResult.getString("duracion"));
                objVacante.setEstado(objResult.getString("estado"));
                objVacante.setTecnologia(objResult.getString("tecnologia"));

                objEmpresa.setId(objResult.getInt("empresa.id"));
                objEmpresa.setNombre(objResult.getString("empresa.nombre"));
                objEmpresa.setSector(objResult.getString("empresa.sector"));
                objEmpresa.setUbicacion(objResult.getString("empresa.ubicacion"));
                objEmpresa.setContacto(objResult.getString("empresa.contacto"));

                objVacante.setObjEmpresa(objEmpresa);
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        ConfigDB.closeConnection();
        return objVacante;
    }

    public  List<Vacante> findByTitulo (String titulo){
        List<Vacante> listVacante = new ArrayList<>();
        Empresa objEmpresa = new Empresa();

        Connection objConnection = ConfigDB.openConnection();

        try{

            String sql = "SELECT * FROM vacante INNER JOIN empresa ON empresa.id = vacante.empresa_id WHERE  titulo LIKE ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,"%"+titulo+"%");

            ResultSet objResult = objPrepare.executeQuery();
            while(objResult.next()){
                Vacante objVacante = new Vacante();
                objVacante.setId(objResult.getInt(1));
                objVacante.setEmpresaId(objResult.getInt("empresa_id"));
                objVacante.setTitulo(objResult.getString("titulo"));
                objVacante.setDecripcion(objResult.getString("descripcion"));
                objVacante.setDuracion(objResult.getString("duracion"));
                objVacante.setEstado(objResult.getString("estado"));
                objVacante.setTecnologia(objResult.getString("tecnologia"));

                objEmpresa.setId(objResult.getInt("empresa.id"));
                objEmpresa.setNombre(objResult.getString("empresa.nombre"));
                objEmpresa.setSector(objResult.getString("empresa.sector"));
                objEmpresa.setUbicacion(objResult.getString("empresa.ubicacion"));
                objEmpresa.setContacto(objResult.getString("empresa.contacto"));

                objVacante.setObjEmpresa(objEmpresa);

                listVacante.add(objVacante);
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        ConfigDB.closeConnection();
        return listVacante;
    }

    public  List<Vacante> findByTecnologia (String tecnologia){
        List<Vacante> listVacante = new ArrayList<>();
        Empresa objEmpresa = new Empresa();

        Connection objConnection = ConfigDB.openConnection();

        try{

            String sql = "SELECT * FROM vacante INNER JOIN empresa ON empresa.id = vacante.empresa_id WHERE  tecnologia LIKE ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,"%"+tecnologia+"%");

            ResultSet objResult = objPrepare.executeQuery();
            while(objResult.next()){
                Vacante objVacante = new Vacante();
                objVacante.setId(objResult.getInt(1));
                objVacante.setEmpresaId(objResult.getInt("empresa_id"));
                objVacante.setTitulo(objResult.getString("titulo"));
                objVacante.setDecripcion(objResult.getString("descripcion"));
                objVacante.setDuracion(objResult.getString("duracion"));
                objVacante.setEstado(objResult.getString("estado"));
                objVacante.setTecnologia(objResult.getString("tecnologia"));

                objEmpresa.setId(objResult.getInt("empresa.id"));
                objEmpresa.setNombre(objResult.getString("empresa.nombre"));
                objEmpresa.setSector(objResult.getString("empresa.sector"));
                objEmpresa.setUbicacion(objResult.getString("empresa.ubicacion"));
                objEmpresa.setContacto(objResult.getString("empresa.contacto"));

                objVacante.setObjEmpresa(objEmpresa);

                listVacante.add(objVacante);
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        ConfigDB.closeConnection();
        return listVacante;
    }

    public  List<Vacante> findByVacantesActivas(){
        List<Vacante> listVacante = new ArrayList<>();
        Empresa objEmpresa = new Empresa();

        Connection objConnection = ConfigDB.openConnection();

        try{

            String sql = "SELECT * FROM vacante INNER JOIN empresa ON empresa.id = vacante.empresa_id WHERE  estado = 'activo';";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();
            while(objResult.next()){
                Vacante objVacante = new Vacante();
                objVacante.setId(objResult.getInt(1));
                objVacante.setEmpresaId(objResult.getInt("empresa_id"));
                objVacante.setTitulo(objResult.getString("titulo"));
                objVacante.setDecripcion(objResult.getString("descripcion"));
                objVacante.setDuracion(objResult.getString("duracion"));
                objVacante.setEstado(objResult.getString("estado"));
                objVacante.setTecnologia(objResult.getString("tecnologia"));

                objEmpresa.setId(objResult.getInt("empresa.id"));
                objEmpresa.setNombre(objResult.getString("empresa.nombre"));
                objEmpresa.setSector(objResult.getString("empresa.sector"));
                objEmpresa.setUbicacion(objResult.getString("empresa.ubicacion"));
                objEmpresa.setContacto(objResult.getString("empresa.contacto"));

                objVacante.setObjEmpresa(objEmpresa);

                listVacante.add(objVacante);
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        ConfigDB.closeConnection();
        return listVacante;
    }

    public Vacante changeEstadoInactivo (int vacante_id){
        Connection objConnection = ConfigDB.openConnection();


        try {
            String sql = "UPDATE vacante SET estado = 'INACTIVO' WHERE id_vacante = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, vacante_id);

            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0){
                JOptionPane.showMessageDialog(null, "La vacante paso a ser INACTIVA");
            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return findById(vacante_id);
    }

    public Vacante changeEstadoActivo (int vacante_id){
        Connection objConnection = ConfigDB.openConnection();


        try {
            String sql = "UPDATE vacante SET estado = 'ACTIVO' WHERE id_vacante = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, vacante_id);

            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0){
                JOptionPane.showMessageDialog(null, "La vacante paso a ser ACTIVA");
            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return findById(vacante_id);
    }
}
