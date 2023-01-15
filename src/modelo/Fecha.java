package modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Marco
 */
public class Fecha {
    private int dia;
    private int mes;
    private int anio;
    
    public Fecha() {
        //si no se introduce fecha se fija la fecha actual por defecto
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd");
        this.dia = Integer.parseInt(formatter.format(date));
        formatter = new SimpleDateFormat("MM");
        this.mes = Integer.parseInt(formatter.format(date));
        formatter = new SimpleDateFormat("yyyy");
        this.anio = Integer.parseInt(formatter.format(date));
    }
    
    //constructor que se usará
    public Fecha(int d, int m, int a) {
        this.dia = d;
        this.mes = m;
        this.anio = a;
    }
    
    //setters y getters
    public int getDia() {
        return this.dia;
    }
    
    public int getMes() {
        return this.mes;
    }
        
    public int getAnio() {
        return this.anio;
    }
    
    public void setDia(int d) {
        this.dia = d;
    }
    
    public void setMes(int m) {
        this.mes = m;
    }
    
    public void setAnio(int a) {
        this.anio = a;
    }
    
    /**
     * Devuelve el mes con letra.
     * @return cadena con el mes que corresponde al mes de la instancia Fecha
     */
    public String getMesLetras() {
        String cadena = "";
        switch (this.mes) {
            case 1:
                cadena = "enero";
                break;
            case 2:
                cadena = "febrero";
                break;
            case 3:
                cadena = "marzo";
                break;
            case 4:
                cadena = "abril";
                break;
            case 5:
                cadena = "mayo";
                break;
            case 6:
                cadena = "junio";
                break;
            case 7:
                cadena = "julio";
                break;
            case 8:
                cadena = "agosto";
                break;
            case 9:
                cadena = "septiembre";
                break;
            case 10:
                cadena = "octubre";
                break;
            case 11:
                cadena = "noviembre";
                break;
            case 12:
                cadena = "diciembre";
                break;
            default:
                cadena = "unknown";
                break;
        }
        return cadena;
    }
    
    /**
     * Método usado para verificar si un año es bisiesto o no.
     * @return true si es bisiesto, false en caso contrario
     */
    public boolean esBisiesto() {
        boolean loEs = false;
        if (this.anio % 4 == 0) {
            if (this.anio % 100 == 0) {
                if (this.anio % 400 == 0) {
                    loEs = true;
                }
                else {
                    loEs = false;
                }
            }
            else {
                loEs = true;
            }
        }
        return loEs;
    }
    
    /**
     * Método que devuelve el número de días de un mes determinado,
     * teniendo en cuenta si es bisiesto en caso de ser febrero.
     * @return Valor entero con el número de días del mes correspondiente
     */
    public int getDiasMes() {
        int numDias = 0;
        if (this.mes == 1 || this.mes == 3
                || this.mes == 5 || this.mes == 7
                || this.mes == 8 || this.mes == 10
                || this.mes == 12) {
            numDias = 31;
        }
        else if (this.mes == 4
                || this.mes == 6 || this.mes == 9
                || this.mes == 11) {
            numDias = 30;
        }
        else if (this.mes == 2 && !this.esBisiesto()) {
            numDias = 28;
        }
        else if (this.mes == 2 && this.esBisiesto()) {
            numDias = 29;
        }
        return numDias;
    }
    
    /**
     * Comprueba si la fecha es correcta acorde a los criterios del
     * calendario gregoriano (28, 29, 30 o 31 días según el mes/año, mes 
     * comprendido entre 1 y 12 y año mayor o igual a 0).
     * @return true si es correcta, false en caso contrario
     */
    public boolean esCorrecta() {
        boolean loEs = false;
        
        if (this.anio >= 0) {
            switch (this.getDiasMes()) {
                case 30:
                    if (this.dia >= 1 && this.dia <= 30) {
                        loEs = true;
                    }
                    break;
                case 31:
                    if (this.dia >= 1 && this.dia <= 31) {
                        loEs = true;
                    }               
                    break;
                case 28:
                    if (this.dia >= 1 && this.dia <= 28) {
                        loEs = true;
                    } 
                    break;
                case 29:
                    if (this.dia >= 1 && this.dia <= 29) {
                        loEs = true;
                    }  
                    break;
                default:
                    loEs = false;
                    break;
            }
        }
        return loEs;
    }
    
    /**
     * Devuelve una cadena con la fecha en formato dd/mm/aaaa.
     * @return Cadena con la fecha
     */
    @Override
    public String toString() {
        return this.dia + "/" + this.mes + "/" + this.anio;
    }
}
