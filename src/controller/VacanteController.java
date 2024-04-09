package controller;

import entity.Vacante;

import model.EmpresaModel;
import model.VacanteModel;

import javax.swing.*;
import java.util.List;

public class VacanteController {
    public static void getAll(){
        VacanteModel objVacanteModel = new VacanteModel();
        List<Object> listVacantes = objVacanteModel.findAll();

        if (listVacantes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay vacantes creadas aun");
        } else {
            String listVacantestxt = "Lista de vacantes \n";
            for (Object iterador : listVacantes) {
                Vacante objMedico = (Vacante) iterador;
                listVacantestxt += objMedico.toString() + "\n";
            }
            JOptionPane.showMessageDialog(null, listVacantestxt);
        }
    }

    public static String getAllString(){
        VacanteModel objVacanteModel = new VacanteModel();
        String listVacantes = "Lista de medicos \n";

        for (Object iterador : objVacanteModel.findAll()){
            Vacante objMedico = (Vacante) iterador;
            listVacantes += objMedico.toString() + "\n";
        }
        return listVacantes;
    }

    public static void  create(){
        VacanteModel objVacanteModel = new VacanteModel();
        EmpresaModel objEmpresaModel = new EmpresaModel();

        int empresaID = Integer.parseInt(JOptionPane.showInputDialog(objEmpresaModel.findAll()+"Ingrese el id de la empresa que pone la vacante"));
        String titulo = JOptionPane.showInputDialog("Ingrese el titulo de la vacante: ");
        String descripcion = JOptionPane.showInputDialog("Ingrese la descripcion de la vacante: ");
        String duracion = JOptionPane.showInputDialog("Ingrese la duracion del contrato de la vacante: ");
        String estado = "activo";
        String tecnologia = JOptionPane.showInputDialog("Ingrese la tecnologia que requiere de la vacante: ");

        Vacante objVacante = new Vacante();

        objVacante.setEmpresaId(empresaID);
        objVacante.setTitulo(titulo);
        objVacante.setDecripcion(descripcion);
        objVacante.setDuracion(duracion);
        objVacante.setEstado(estado);
        objVacante.setTecnologia(tecnologia);

        objVacante = (Vacante) objVacanteModel.insert(objVacante);

        JOptionPane.showMessageDialog(null, objVacante.toString());
    }

    public static void delete(){

        VacanteModel objVacanteModel = new VacanteModel();

        String listVacantes = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listVacantes+"\n Ingresa el id de la vacante que desea eliminar\n"));

        Vacante objVacante = objVacanteModel.findById(idDelete);


        if (objVacante == null){
            JOptionPane.showMessageDialog(null, "Vacante no encontrada");
        }else {
            int confirm = JOptionPane.showConfirmDialog(null, "Estas seguro que deseas eliminar esta vacante?\n" + objVacante.toString());
            if (confirm == 0) {
                objVacanteModel.delete(objVacante);
            }
        }
    }

    public static void update(){
        EmpresaModel objEmpresaModel = new EmpresaModel();
        VacanteModel objVacanteModel = new VacanteModel();

        String listVacantes = getAllString();

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listVacantes+"\nIngresa el ID de la vacante que deseas modificar: \n"));

        Vacante objVacante = objVacanteModel.findById(idUpdate);

        if (objVacante == null){
            JOptionPane.showMessageDialog(null,"No se encontro una vacante con ese id");
        }else {
            JOptionPane.showMessageDialog(null, objVacante.toString());

            int empresaID = Integer.parseInt(JOptionPane.showInputDialog(objEmpresaModel.findAll()+"Ingrese el id de la empresa que pone la vacante"));
            String titulo = JOptionPane.showInputDialog("Ingrese el titulo de la vacante: ");
            String descripcion = JOptionPane.showInputDialog("Ingrese la descripcion de la vacante: ");
            String duracion = JOptionPane.showInputDialog("Ingrese la duracion del contrato de la vacante: ");
            String estado = JOptionPane.showInputDialog("Ingrese el estado de la vacante (Activo, Inactivo): ");
            String tecnologia = JOptionPane.showInputDialog("Ingrese la tecnologia que requiere de la vacante: ");

            objVacante.setEmpresaId(empresaID);
            objVacante.setTitulo(titulo);
            objVacante.setDecripcion(descripcion);
            objVacante.setDuracion(duracion);
            objVacante.setEstado(estado);
            objVacante.setTecnologia(tecnologia);

            objVacanteModel.update(objVacante);
        }
    }
    public static void finByTitulo(){

        String titulo  = JOptionPane.showInputDialog("Ingresa el titulo de la vacante a buscar: ");
        VacanteModel objVacanteModel = new VacanteModel();

        String listVacantes = "VACANTES ENCONTRADAS \n";

        for (Object coder: objVacanteModel.findByTitulo(titulo)){
            listVacantes += coder.toString() +"\n";
        }
        JOptionPane.showMessageDialog(null,listVacantes);
    }

    public static void finByTecnologia(){

        String tecnologia  = JOptionPane.showInputDialog("Ingresa la tecnologia de la vacante a buscar: ");
        VacanteModel objVacanteModel = new VacanteModel();

        String listVacantes = "VACANTES ENCONTRADAS \n";

        for (Object coder: objVacanteModel.findByTecnologia(tecnologia)){
            listVacantes += coder.toString() +"\n";
        }
        JOptionPane.showMessageDialog(null,listVacantes);
    }
}
