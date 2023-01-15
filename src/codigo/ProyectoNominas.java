package codigo;

/**
 *
 * @author Marco Zúñiga
 */
import codigo.GestorNominas;
import modelo.Fecha;
import java.util.Scanner;

public class ProyectoNominas {
    public static void main(String[] args) {
        int op = -1;
        double salarioBase = -1;
        Scanner teclado = new Scanner(System.in);
        while (salarioBase <= 0) {
            System.out.print("Introduzca el salario base de la empresa: ");
            salarioBase = teclado.nextDouble();
        }
        System.out.print('\n');
       
        //generamos 30 empleados pseudoaleatorios
        for (int i = 0; i <= 30; i++) {
            GestorNominas.addEmpleado(GestorNominas.generarEmpleado(salarioBase));
        }
        
        /*utilizaremos bucles while y switches para gestionar el menú de nuestro
        * programa, que deberá ser iterativo y comprobar que los datos introducidos
        * por el usuario están en el rango de valores coherentes/que puede manejar
        * el programa*/
        while (op != 0) {
            System.out.print("1- Listado empleados" + '\n' + "2- Modificación de datos" 
                + '\n' + "3- Nómina" + '\n' + "4- Modificación del Salario Base" 
                + '\n' + "0- Fin" + '\n' + "Opción: ");
            op = teclado.nextInt();
            System.out.print('\n');
            switch (op) {
                case 0:
                    break;
                case 1:
                    System.out.print(String.format("%-10s%-15s%-45s%5s%12s%12s", "CÓDIGO", "NOMBRE", "APELLIDOS", "HIJOS", "COMPLEMENTO", "SALARIO"));
                    System.out.println('\n' + GestorNominas.listaEmpleados());
                    break;
                case 2:
                    String codigoNum = "";
                    System.out.print("Introduzca el código de empleado: ");
                    teclado.nextLine();     //limpiamos el buffer de entrada
                    codigoNum = teclado.nextLine();
                    codigoNum = codigoNum.toUpperCase();
                    /*es más sencillo indexar un empleado según su posición
                    * en el vector que buscando todo el rato su código dentro
                    * del mismo. Por tanto, una vez el usuario introduce
                    * el código, lo utilizaremos para saber qué posición
                    * le corresponde al empleado en el array y utilizar
                    * dicho dato para modificar los datos correspondientes si
                    * es necesario.*/
                    codigoNum = GestorNominas.existeCodigo(codigoNum);
                    /* "desaparece" el codigo A_0000 y tenemos un numero (string)
                    guardado en codigoNum con la posición de dicho empleado*/
                    if (codigoNum.equals("n")) {
                        System.out.println("No está registrado ningún empleado con ese código" + '\n');
                    }
                    else {
                        System.out.println("Modificando datos del empleado " + 
                                (GestorNominas.getEmpleadoNum(codigoNum)).getNombre() 
                                + " " + (GestorNominas.getEmpleadoNum(codigoNum)).getApellidos() + '\n');
                        System.out.print("1- Modificar nº de hijos" + '\n' + "2- Modificar complemento de destino" 
                            + '\n' + "¿Qué opción desea modificar?: ");
                        int op_2 = -1;
                        while (op_2 != 1 && op_2 != 2) {
                            op_2 = teclado.nextInt();
                            System.out.print('\n');
                            switch (op_2) {
                                case 1:
                                    GestorNominas.sumarHijo(codigoNum);
                                    System.out.println("Añadido nuevo hijo al empleado " 
                                            + (GestorNominas.getEmpleadoNum(codigoNum)).getNombre() + " "
                                            + (GestorNominas.getEmpleadoNum(codigoNum)).getApellidos() + '\n');
                                    break;
                                case 2:
                                    System.out.print("Introduzca el nuevo complemento de destino: ");
                                    double nuevoComp = teclado.nextDouble();
                                    if (nuevoComp < 0) {
                                        System.out.println("El complemento no puede ser negativo");
                                    }
                                    else {
                                        GestorNominas.modificarComplemento(nuevoComp, codigoNum);
                                    }
                                    
                                    System.out.print('\n');
                                    break;
                                default:
                                    System.out.print("Valor no válido, introduzca una opción correcta: ");
                                    break;
                            }
                        }
                    }
                    break;
                case 3:
                    System.out.print("Introduzca el código de empleado: ");
                    teclado.nextLine();     //limpiamos el buffer de entrada
                    codigoNum = teclado.nextLine();
                    codigoNum = codigoNum.toUpperCase();
                    codigoNum = GestorNominas.existeCodigo(codigoNum);
                    if (codigoNum.equals("n")) {
                        System.out.println("No está registrado ningún empleado con ese código");
                        System.out.print('\n');
                    }
                    else {
                        int dia, mes, anio;
                        System.out.print("Introduzca el día: ");
                        dia = teclado.nextInt();
                        System.out.print("Introduzca el mes: ");
                        mes = teclado.nextInt();
                        System.out.print("Introduzca el año: ");
                        anio = teclado.nextInt();
                        Fecha fecha = new Fecha(dia, mes, anio);
                        if (fecha.esCorrecta()) {
                            System.out.println(GestorNominas.mostrarNomina(fecha, codigoNum));
                        }
                        else {
                            System.out.println("Introduzca un formato de fecha válido" + '\n');
                        }
                    }
                    break;
                case 4:
                    System.out.print("Introduzca el nuevo salario: ");
                    double nuevoSalario = teclado.nextDouble();
                    if (nuevoSalario > 0) {
                        GestorNominas.modificarSalario(nuevoSalario);
                        System.out.print('\n');
                    }
                    else {
                        System.out.println("El inspector de Hacienda está de camino. Introduce un valor válido" + '\n');
                    }
                    break;
                default:
                    System.out.println("Valor no válido" + '\n');
                    break;
            }
        }
    }
}