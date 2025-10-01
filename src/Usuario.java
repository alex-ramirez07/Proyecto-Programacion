import java.util.Date;

class Usuario {
    private String id;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String barrio;
    private String telefono;
    private String contraseña;

    public Usuario(String id, String nombre, String apellido, Date fechaNacimiento,
                   String barrio, String telefono, String contraseña) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.barrio = barrio;
        this.telefono = telefono;
        this.contraseña = contraseña;
    }
}