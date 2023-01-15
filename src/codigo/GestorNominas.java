package codigo;

import modelo.Empleado;
import modelo.Fecha;

import java.util.Random;

public class GestorNominas {
    private final static int MAX_BASE_NOMBRES = 100;
    private final static int NUM_EMPLEADOS = 30;
    private static Empleado empleado[] = new Empleado[NUM_EMPLEADOS];
    private static int numEmpleados = 0;
    
    private static String nombres[] = {"Lionel", "Robert", "Cristiano", "Kevin", "Sadio", 
        "Erling", "Kylian", "Mohamed", "Neymar", "Virgil", "Joshua", "Karim", 
        "Sergio", "Thomas", "Manuel", "Serge", "Thiago", "Trent", "Romelu", 
        "Harry", "Bruno", "Son", "Alphonso", "Ciro", "Zlatan", "Alisson", 
        "Raheem", "Jan", "Jadon", "Timo"};
    private static String apellidos[] = {"Messi", "Lewandowski", "Ronaldo", "De Bruyne", 
        "Mané", "Haaland", "Mbappé", "Salah", "Da Silva", "Van Dijk", 
        "Kimmich", "Benzema", "Ramos", "Muller", "Neuer", "Gnabry", "Alcántara",
        "Arnold", "Lukaku", "Kane", "Fernandes", "Heung",
        "Davies", "Immobile", "Ibrahimovic", "Becker", "Sterling", "Oblak",
        "Sancho", "Werner"};
    
    /**
    * Si existe un empleado con el código "cod" en GestorNóminas,
    * devuelve una cadena que contiene la posición donde se encuentra 
    * dicho empleado dentro del array. En caso contrario, devuelve "n".  
    * @param cod Código de empleado
    * @return cadena con 'n' o el número del código en String
    */
    public static String existeCodigo(String cod) {
        String resultado = "n";
        for (int i = 0; i < numEmpleados; i++) {
            if ((empleado[i].getCodEmpleado()).equals(cod)) {
                resultado = "" + i;
            }
        }
        return resultado;
    }

    /**
     * Genera un código único de empleado con el formato "A_0000" y 
     * comprueba que no exista un empleado con el mismo código en el
     * array empleado[]
     * En caso de que ya exista, genera otro distinto (hasta que sea
     * diferente)
     * @param nombre Nombre del empleado (para la primera letra)
     * @return Código de empleado
     */
    public static String generarCod(String nombre) {
        String codigo = "";
        Random random = new Random();
        String c1 = "" + (nombre.charAt(0)) + "_";
        String c2 = "" + random.nextInt(5001);
        c1 = c1.toUpperCase();
        boolean generado = false;
        
        while (!generado) {
            if (c2.length() == 4) {
                codigo = c1 + c2;
            }
            else if (c2.length() == 3) {
                codigo = c1 + "0" + c2;
            }
            else if (c2.length() == 2) {
                codigo = c1 + "00" + c2;
            }
            else if (c2.length() == 1) {
                codigo = c1 + "000" + c2;
            }
            
            /*es bastante improbable, pero debemos asegurarnos de que
            el código generado no pertenece a ningún otro empleado*/
            if (existeCodigo(codigo).charAt(0) == 'n') {
                generado = true;
            }
        }
        return codigo;
    }
    
    /**
     * Genera un empleado con nombre y apellidos seleccionados aleatoriamente
     * de la listas de nombres[] y apellidos[] disponibles en GestorNominas.
     * Su número de hijos es aleatorio entre 0 y 10 y su complemento de
     * destino entre 0 y 2000.
     * @param salario Salario base, inicializado antes de generar Empleado
     * @return Empleado con las características descritas
     */
    public static Empleado generarEmpleado(double salario) {
        Random random = new Random();
        int i = random.nextInt(30);
        Empleado e = new Empleado(generarCod(nombres[i]), nombres[i], 
                (apellidos[random.nextInt(30)] + " " 
                + apellidos[random.nextInt(30)]), random.nextInt(11), 
                salario, random.nextInt(2001));
        
        return e;
    }
    
    /**
     * Añade el empleado especificado a la colección siempre que sea 
     * posible (es decir, si el array empleado[] no está lleno).
     * Con lleno entendemos que el número de empleados sea igual
     * a NUM_EMPLEADOS, el valor máximo especificado.
     * @param e Empleado a introducir
     * @return true si se pudo insertar, false en caso contrario
     */
    public static boolean addEmpleado(Empleado e) {
        boolean exito = false;
        if (numEmpleados < NUM_EMPLEADOS) {
            empleado[numEmpleados] = e;
            numEmpleados++;
            exito = true;
        }
        return exito;
    }
    
    /**
     * Devuelve una cadena formada por la concatenación de los datos
     * de todos los empleados rgistrados en el array empleado[].
     * @return Cadena con la información de los empleados
     */
    public static String listaEmpleados() {
        String cadena = "";
        for (int i = 0; i < numEmpleados; i++) {
            cadena = cadena + empleado[i].toString() + '\n';
        }
        return cadena;
    }
        
    /**
     * Función auxiliar utilizada para obtener un empleado
     * identificándolo mediante codParcial, que es una cadena que contiene
     * la parte numérica de su identificador en String. Permite 
     * simplificar el código y las búsquedas en el array empleado[]
     * Siempre que se llama a este método sabemos que el codParcial generado
     * existe y es correcto.
     * @param codParcial Código de empleado (parte numérica)
     * @return Empleado con el código buscado
     */
    public static Empleado getEmpleadoNum(String codParcial) {
        return empleado[Integer.parseInt(codParcial)];
    }
    
    /**
     * Función auxiliar utilizada para incrementar en una unidad el
     * número de hijos del empleado con el código parcial 'cod'. De otra manera, 
     * no sería posible acceder a este campo privado de la clase Empleado.
     * @param cod código de empleado que sólo contiene la parte numérica como String
     */
    public static void sumarHijo(String cod) {
        empleado[Integer.parseInt(cod)].addHijo();
    }
    
    /**
     * Modifica el complemento de destino del empleado cuyo código
     * le pasemos como parámetro. El nuevo complemento será el indicado
     * en el parámetro 'comp'.
     * @param comp nuevo complemento de destino
     * @param cod código del empleado del que queremos modificar el complemento
     */
    public static void modificarComplemento(double comp, String cod) {
        empleado[Integer.parseInt(cod)].setComplemento(comp);
    }
    
    /**
     * Calcula la retención de IRPF correspondiente al salario base
     * introducido.
     * @param salario Salario base
     * @return Valor con el IRPF (0.1, 0.15 o 0.2)
     */
    public static float calcularIRPF(double salario) {
        float valor = 0.1f;
        if (salario < 1500) {
            valor = 0.1f;
        }
        else if (salario >= 1500 && salario <= 2500) {
            valor = 0.15f;
        }
        else if (salario > 2500) {
            valor = 0.2f;
        }
        return valor;
    }
    
    /**
     * Devuelve una cadena con la información sobre la nómina de un empleado
     * concreto en una fecha concreta.
     * @param f Fecha de cuya nómina queremos saber la información
     * @param cod código parcial del empleado
     * @return cadena con la nómina, teniendo en cuenta si recibe o no ayudaSocial
     */
    public static String mostrarNomina(Fecha f, String cod) {
        /*calculamos primero todo lo que vamos a utilizar, 
        /* para simplificar el return de después y que no
        /* sea tan largo.*/
        int codigo = Integer.parseInt(cod);
        double salarioBase = empleado[codigo].getSalarioBase();
        if (f.getMes() == 7 || f.getMes() == 12) {
            salarioBase = salarioBase * 2;
        }
        double complementoDestino = empleado[codigo].getComplemento();
        double porcentIRPF = calcularIRPF(salarioBase + complementoDestino);
        double retencionIRPF = porcentIRPF*(salarioBase + complementoDestino);
        double ayudaSocial = 0;
        if (f.getMes() == 9) {
            ayudaSocial = 150 * empleado[codigo].getNumHijos();
        }
        double total = salarioBase + complementoDestino - retencionIRPF
                + ayudaSocial;
        
        if (empleado[codigo].getNumHijos() == 0) {
            return '\n' + empleado[codigo].getCodEmpleado() + '\n' 
                + empleado[codigo].getApellidos() + ", "
                + empleado[codigo].getNombre() + '\n' 
                + f.toString() + '\n' + '\n'
                
                + '\t' + "Salario base" + '\t' + '\t' + String.format("%.2f", salarioBase) + '\n'
                + '\t' + "Complemento de destino" + '\t' + String.format("%.2f", complementoDestino) + '\n' 
                + '\t' + "IRPF " + (int)(porcentIRPF * 100) + "%" + '\t' + '\t' 
                    + String.format("%.2f", retencionIRPF)
                + '\t' + '\n' + '\n'
                + '\t' + "Total a percibir" + '\t' + String.format("%.2f", total) + '\n';
        }
        else {
            return '\n' + empleado[codigo].getCodEmpleado() + '\n' 
                    + empleado[codigo].getApellidos() + ", "
                    + empleado[codigo].getNombre() + '\n' 
                    + f.toString() + '\n' + '\n'

                    + '\t' + "Salario base" + '\t' + '\t' + String.format("%.2f", salarioBase) + '\n'
                    + '\t' + "Complemento de destino" + '\t' + String.format("%.2f", complementoDestino) + '\n' 
                    + '\t' + "IRPF " + (int)(porcentIRPF * 100) + "%" + '\t' + '\t' 
                        + String.format("%.2f", retencionIRPF) + '\n'
                    + '\t' + "Ayuda social" + '\t' + '\t' + String.format("%.2f", ayudaSocial) + '\n' + '\n'
                    + '\t' + "Total a percibir" + '\t' + String.format("%.2f", total) + '\n';
        }
    }
    
    /**
     * Modifica el salario de todos los empleados recorriendo el array
     * y modificando el parámetro salarioBase de las clases Empleado 
     * que contiene.
     * @param salario Nuevo salario base a establecer
     */
    public static void modificarSalario(double salario) {
        for (int i = 0; i < numEmpleados; i++) {
            empleado[i].setSalario(salario);
        }
    }
}