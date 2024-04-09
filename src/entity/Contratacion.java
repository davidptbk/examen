package entity;

public class Contratacion {
    private int id;
    private int vacanteId;
    private int coderId;
    private String fechaAplicacion;
    private String estado;
    private double salario;

    private Vacante objVacante;
    private Coder objCoder;

    public Contratacion() {
    }

    public Contratacion(int vacanteId, int coderId, String estado, double salario) {
        this.vacanteId = vacanteId;
        this.coderId = coderId;
        this.estado = estado;
        this.salario = salario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVacanteId() {
        return vacanteId;
    }

    public void setVacanteId(int vacanteId) {
        this.vacanteId = vacanteId;
    }

    public int getCoderId() {
        return coderId;
    }

    public void setCoderId(int coderId) {
        this.coderId = coderId;
    }

    public String getFechaAplicacion() {
        return fechaAplicacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Vacante getObjVacante() {
        return objVacante;
    }

    public void setObjVacante(Vacante objVacante) {
        this.objVacante = objVacante;
    }

    public Coder getObjCoder() {
        return objCoder;
    }

    public void setObjCoder(Coder objCoder) {
        this.objCoder = objCoder;
    }

    @Override
    public String toString() {
        return "Contratacion{" +
                "id=" + id +
                ", fechaAplicacion='" + fechaAplicacion + '\'' +
                ", estado='" + estado + '\'' +
                ", salario=" + salario +
                ", Vacante=" + objVacante.getTitulo() +
                ", Coder=" + objCoder.getNombre() +
                '}';
    }
}
