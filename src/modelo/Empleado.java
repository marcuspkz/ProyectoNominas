package modelo;

/**
 *
 * @author Marco Zúñiga
 */
public class Empleado {
    private String codEmpleado;
    private String nombre;
    private String apellidos;
    private int numHijos;
    private double salarioBase;
    private double complemento;
    
    //constructores que usaremos a conveniencia
    public Empleado(String cod) {
        this.codEmpleado = cod;
    }
    
    public Empleado(String cod, String nom, String ap) {
        this.codEmpleado = cod;
        this.nombre = nom;
        this.apellidos = ap;
        //valores por defecto
        this.numHijos = 0;
        this.salarioBase = 1000;
        this.complemento = 150;
    }
    
    public Empleado(String cod, String nom, String ap, int nhi, double sb, double comp) {
        this.codEmpleado = cod;
        this.nombre = nom;
        this.apellidos = ap;
        this.numHijos = nhi;
        this.salarioBase = sb;
        this.complemento = comp;
    }
    
    //setters necesarios
    public void setComplemento(double c) {
        this.complemento = c;
    }
    
    public void setSalario(double s) {
        this.salarioBase = s;
    }
    
    /**
     * Suma un hijo a la instancia Empleado.
     * @return true si se pudo hacer (no tenía aún 10 hijos), false en caso contrario
     */
    public boolean addHijo() {
        boolean exito = false;
        if (this.numHijos < 10) {
            this.numHijos++;
            exito = true;
        }
        return exito;
    }
    
    //getters
    public String getCodEmpleado() {
        return this.codEmpleado;
    }
    
    public String getNombre() {
        return this.nombre;
    }
    
    public String getApellidos() {
        return this.apellidos;
    }
    
    public int getNumHijos() {
        return this.numHijos;
    }
    
    public double getSalarioBase() {
        return this.salarioBase;
    }
    
    public double getComplemento() {
        return this.complemento;
    }
    
    /**
     * Devuelve una cadena con los datos del empleado.
     * @return Cadena con la información
     */
    @Override
    public String toString() {
        return String.format("%-10s%-15s%-45s%5d%12.2f%12.2f", codEmpleado, nombre, apellidos, numHijos, complemento, salarioBase);
    }
}
