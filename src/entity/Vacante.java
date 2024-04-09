package entity;

public class Vacante {
    private int id;
    private int empresaId;
    private String titulo;
    private String decripcion;
    private String duracion;
    private String estado;
    private String tecnologia;
    private Empresa objEmpresa;

    public Vacante() {
    }

    public Vacante(int id, int empresaId, String titulo, String decripcion, String duracion, String estado, String tecnologia) {
        this.id = id;
        this.empresaId = empresaId;
        this.titulo = titulo;
        this.decripcion = decripcion;
        this.duracion = duracion;
        this.estado = estado;
        this.tecnologia = tecnologia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(int empresaId) {
        this.empresaId = empresaId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDecripcion() {
        return decripcion;
    }

    public void setDecripcion(String decripcion) {
        this.decripcion = decripcion;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(String tecnologia) {
        this.tecnologia = tecnologia;
    }

    public Empresa getObjEmpresa() {
        return objEmpresa;
    }

    public void setObjEmpresa(Empresa objEmpresa) {
        this.objEmpresa = objEmpresa;
    }

    @Override
    public String toString() {
        return "Vacante: \n" +
                "id: " + id +
                ", titulo: " + titulo + '\'' +
                ", decripcion: " + decripcion + '\'' +
                ", duracion: " + duracion + '\'' +
                ", estado: " + estado + '\'' +
                ", tecnologia: " + tecnologia + '\'' +
                ", Empresa: " + objEmpresa.getNombre() +
                '}';
    }
}
