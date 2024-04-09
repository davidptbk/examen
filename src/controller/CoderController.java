package controller;

import entity.Coder;
import model.CoderModel;

import javax.swing.*;

public class CoderController {

    public static void getAll(){
        CoderModel objCoderModel = new CoderModel();
        String listCoder= "Lista de coders \n";

        for (Object iterador : objCoderModel.findAll()){
            Coder objCoder = (Coder) iterador;
            listCoder += objCoder.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, listCoder);
    }

    public static String getAllString(){
        CoderModel objCoderModel = new CoderModel();
        String listCoder = "Lista de coders \n";

        for (Object iterador : objCoderModel.findAll()){
            Coder objCoder = (Coder) iterador;
            listCoder += objCoder.toString() + "\n";
        }
        return listCoder;
    }

    public static void create(){
        CoderModel objCoderModel = new CoderModel();

        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del coder: ");
        String apellidos = JOptionPane.showInputDialog("Ingrese los apellidos del coder: ");
        String documento = JOptionPane.showInputDialog("Ingrese el documento del coder: ");
        int cohorte = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cohorte del coder: "));
        String cv = JOptionPane.showInputDialog("Ingrese el cv del coder: ");
        String clan = JOptionPane.showInputDialog("Ingrese el clan del coder: ");

        Coder objCoder = new Coder();
        objCoder.setNombre(nombre);
        objCoder.setApellidos(apellidos);
        objCoder.setDocumento(documento);
        objCoder.setCohorte(cohorte);
        objCoder.setCv(cv);
        objCoder.setClan(clan);

        objCoder = (Coder) objCoderModel.insert(objCoder);

        JOptionPane.showMessageDialog(null, objCoder.toString());
    }

    public static void delete(){
        CoderModel objCoderModel = new CoderModel();
        String listCoders= getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listCoders + "\n Ingresa el id del Coder a eliminar"));
        Coder objCoder = objCoderModel.findById(idDelete);
        int confirm = 1;

        if (objCoder == null){
            JOptionPane.showMessageDialog(null, "Coder no encontrado");
        }else{
            confirm = JOptionPane.showConfirmDialog(null, "Estas seguro de borrar esta Coder?. \n" + objCoder.toString());

            if (confirm == 0){
                objCoderModel.delete(objCoder);
            }
        }
    }
    public static void update(){
        CoderModel objCoderModel = new CoderModel();
        String listCoders = getAllString();
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listCoders + "\n Ingresa el id del Coder a actualizar"));

        Coder objCoder= objCoderModel.findById(idUpdate);

        if (objCoder == null){
            JOptionPane.showMessageDialog(null, "Coder no encontrado");
        }else {
            String nombre = JOptionPane.showInputDialog("Ingrese el nombre del coder: ");
            String apellidos = JOptionPane.showInputDialog("Ingrese los apellidos del coder: ");
            String documento = JOptionPane.showInputDialog("Ingrese el documento del coder: ");
            int cohorte = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cohorte del coder: "));
            String cv = JOptionPane.showInputDialog("Ingrese el cv del coder: ");
            String clan = JOptionPane.showInputDialog("Ingrese el clan del coder: ");

            objCoder.setNombre(nombre);
            objCoder.setApellidos(apellidos);
            objCoder.setDocumento(documento);
            objCoder.setCohorte(cohorte);
            objCoder.setCv(cv);
            objCoder.setClan(clan);

            objCoderModel.update(objCoder);
        }
    }

    public static void finByCohorte(){

        int cohorte  = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el cohorte del coder a buscar: "));
        CoderModel objCoderModel = new CoderModel();

        String listaCoders = "CODERS ENCONTRADOS \n";

        for (Object coder: objCoderModel.findByCohorte(cohorte)){
            listaCoders += coder.toString() +"\n";
        }
        JOptionPane.showMessageDialog(null,listaCoders);
    }

    public static void finByClan(){

        String clan  = JOptionPane.showInputDialog("Ingresa el clan del coder a buscar: ");
        CoderModel objCoderModel = new CoderModel();

        String listaCoders = "CODERS ENCONTRADOS \n";

        for (Object coder: objCoderModel.findByClan(clan)){
            listaCoders += coder.toString() +"\n";
        }
        JOptionPane.showMessageDialog(null,listaCoders);
    }
    public static void finByTecnologia(){

        String tecnologia  = JOptionPane.showInputDialog("Ingresa la tecnologia del coder a buscar: ");
        CoderModel objCoderModel = new CoderModel();

        String listaCoders = "CODERS ENCONTRADOS \n";

        for (Object coder: objCoderModel.findByTecnolgoia(tecnologia)){
            listaCoders += coder.toString() +"\n";
        }
        JOptionPane.showMessageDialog(null,listaCoders);
    }
}
