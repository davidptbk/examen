package controller;

import entity.Coder;
import entity.Contratacion;
import entity.Vacante;
import model.CoderModel;
import model.ContratacionModel;
import model.VacanteModel;

import javax.swing.*;
import java.util.List;

public class ContratacionController {
    public static void getAll(){
        ContratacionModel objContratacionModel = new ContratacionModel();
        List<Object> listContratacion = objContratacionModel.findAll();

        if (listContratacion.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay contrataciones para listar");
        } else {
            String listContatoText = "Lista de contrataciones \n";
            for (Object iterador : listContratacion) {
                Contratacion objContratacion = (Contratacion) iterador;
                listContatoText += objContratacion.toString() + "\n";
            }
            JOptionPane.showMessageDialog(null, listContatoText);
        }
    }

    public static String getAllString(){
        ContratacionModel objContratacionModel = new ContratacionModel();
        String listContatoText = "Lista de contrataciones \n";

        for (Object iterador : objContratacionModel.findAll()){
            Contratacion objContratacion = (Contratacion) iterador;
            listContatoText += objContratacion.toString() + "\n";
        }
        return listContatoText;
    }
    public static void create(){

        ContratacionModel objContratacionModel = new ContratacionModel();
        VacanteModel objVacanteModel = new VacanteModel();

        int vacanteId = Integer.parseInt(JOptionPane.showInputDialog(objVacanteModel.findByVacantesActivas() + "\n Ingrese el id de la vacante que se contratara"));
        int coderId = Integer.parseInt(JOptionPane.showInputDialog(CoderController.getAllString() + "\n Ingrese el id de el Coder que tomara la vacante"));
        String estado = "activo";
        double salario = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el salario : "));

        Vacante objVacante = objVacanteModel.findById(vacanteId);

        CoderModel objCoderModel = new CoderModel();
        Coder objCoder= objCoderModel.findById(coderId);

        if (objVacante == null){
            JOptionPane.showMessageDialog(null, "Vacante no encontrada");
        }else if (objCoder == null) {
            JOptionPane.showMessageDialog(null, "Coder no encontrado");
        } else{
            Contratacion objContratacion = new Contratacion();
            objContratacion.setVacanteId(vacanteId);
            objContratacion.setCoderId(coderId);
            objContratacion.setEstado(estado);
            objContratacion.setSalario(salario);
            objContratacion.setObjVacante(objVacante);
            objContratacion.setObjCoder(objCoder);

            //validacion si la tecologia la tiene el coder
            if (objCoder.getCv().contains(objVacante.getTecnologia())){
                 objContratacion = (Contratacion) objContratacionModel.insert(objContratacion);
                JOptionPane.showMessageDialog(null, objContratacion.toString());

                //metodo que cambia el estado de vacante
                objVacanteModel.changeEstadoInactivo(vacanteId);
                JOptionPane.showMessageDialog(null, """
                        Vacante ()
                        
                        """);
            }else {
                JOptionPane.showMessageDialog(null,"El coder no cuenta con los conocimientos para esta vacante");
            }
        }
    }

    public static void update(){
        ContratacionModel objContratacionModel = new ContratacionModel();
        String listContratos= getAllString();
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listContratos + "\n Ingresa el id del contrato que vas a actualizar"));

        Contratacion objContratacion = objContratacionModel.findContratacionById(idUpdate);

        if (objContratacion == null){
            JOptionPane.showMessageDialog(null, "Contratacion no encontrada");
        }else {
            int vacanteId = objContratacion.getVacanteId();
            int coderId = Integer.parseInt(JOptionPane.showInputDialog(CoderController.getAllString() + "\n Ingrese el id de el nuevo coder que tomara la contratacion : "));
            double salario = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese el nuevo salario del contrato : "));

            VacanteModel objVacanteModel = new VacanteModel();
            Vacante objVacante = objVacanteModel.findById(vacanteId);

            CoderModel objCoderModel = new CoderModel();
            Coder objCoder = objCoderModel.findById(coderId);


            objContratacion.setId(idUpdate);
            objContratacion.setSalario(salario);
            objContratacion.setObjVacante(objVacante);
            objContratacion.setObjCoder(objCoder);

            objContratacionModel.update(objContratacion);
        }
    }

    public static void delete(){
        ContratacionModel objContratacionModel = new ContratacionModel();
        VacanteModel objVacanteModel = new VacanteModel();
        String listContratacion = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listContratacion + "\n Ingresa el id del contrato a eliminar"));
        Contratacion objCOntratacion = objContratacionModel.findContratacionById(idDelete);

        if (objCOntratacion == null){
            JOptionPane.showMessageDialog(null, "Contratacion no encontrada");
        }else{
            int confirm = JOptionPane.showConfirmDialog(null, "Estas seguro de borrar este contrato? \n" + objCOntratacion.toString());
            if (confirm == 0){
                objContratacionModel.delete(objCOntratacion);
                //metodo que acitva el estado de la vacante
                objVacanteModel.changeEstadoActivo(objCOntratacion.getVacanteId());
            }
        }
    }
}


