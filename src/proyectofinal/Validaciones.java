package proyectofinal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ROQUEARMANDORAMIREZP
 */
public class Validaciones {

    Boolean Respuesta;

    Boolean EsID(String StrTexto) {
        Respuesta = true;
        if (StrTexto.length() < 1 || StrTexto.length() > 5) {
            System.out.println("\t\tDame un ID Valido");
            return Respuesta = false;
        }
        for (int x = 0; x < StrTexto.length(); x++) {
            char c = StrTexto.charAt(x);
            if (!((c >= '0' && c <= '9'))) {
                System.out.println("\t\tNo Ingrese Caracteres Invalidos al ID, Error en Digito #" + (x + 1));
                return Respuesta = false; // Si no está entre 0 y 9
                // al primer fallo, acaba y no pierde tiempo analizando la cadena
            }
        }
        return Respuesta;
    }

    Boolean EsEmail(String StrTexto) {
        Respuesta = true;
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" //comfigura el patrón para validar email
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(StrTexto);
        if (StrTexto.length() < 1 || StrTexto.length() > 245 || mather.find() == false) {
            Respuesta = false;   //si la sql es menor a 1 O no cuadra con el formato de email, nada ok
            System.out.println("\t\tNo se Cumple con Formato Apropiado de Email (nombre@dominio.com)");
        }
        return Respuesta;
    }

    Boolean EsNombre_Apellido(String StrTexto) {
        //return s.substring(0, 45); //devuelve los primeros 45 caracteres
        Respuesta = true;

        if (StrTexto.length() < 1 || StrTexto.length() > 50) {//si la sql es menor a 1 y mayor que 50 nada ok
            Respuesta = false;
            System.out.println("\t\t Hay Espacio para 50 Caracteres");
        }
        for (int x = 0; x < StrTexto.length(); x++) {
            char c = StrTexto.charAt(x);
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ')) {
                Respuesta = false; // Si no está entre a y z, ni entre A y Z, ni es un espacio
                System.out.println("\t\t No Ingrese Caracteres Invalidos, error en caracter #" + (x + 1));
                break;// al primer fallo, acaba y no pierde tiempo analizando la cadena
            }
        }
        /*if (Respuesta == true) {
            System.out.println(Respuesta + "; TODO PERFECTO");
        }*/
        return Respuesta;
    }

    Boolean EsCelular(String StrTexto) {
        Respuesta = true;
        if (StrTexto.length() < 10 || StrTexto.length() > 20) {
            System.out.println("\t\tDame un Numero Creible de Minimo 10 Digitos");
            return Respuesta = false;
        }
        for (int x = 0; x < StrTexto.length(); x++) {
            char c = StrTexto.charAt(x);
            if (!((c >= '0' && c <= '9') || c == '+')) {
                System.out.println("\t\tNo Ingrese Caracteres Invalidos al Numero, Error en Digito #" + (x + 1));
                return Respuesta = false; // Si no está entre 0 y 9, o "+"
                // al primer fallo, acaba y no pierde tiempo analizando la cadena
            }
        }
        return Respuesta;
    }

    Boolean EsTelefono(String StrTexto) {
        Respuesta = true;
        if (StrTexto.length() < 7 || StrTexto.length() > 20) {
            System.out.println("\t\tDame un Numero Creible, minino 7 Digitos");
            return Respuesta = false;
        }
        for (int x = 0; x < StrTexto.length(); x++) {
            char c = StrTexto.charAt(x);
            if (!((c >= '0' && c <= '9') || c == '+')) {
                System.out.println("\t\tNo Ingrese Caracteres Invalidos al Numero, Error en Digito #" + (x + 1));
                return Respuesta = false; // Si no está entre 0 y 9, o "+"
                // al primer fallo, acaba y no pierde tiempo analizando la cadena
            }
        }
        return Respuesta;
    }

    Boolean EsFecha(String StrTexto) {
        Respuesta = true;
        int IntDia, IntMes, IntAno, dia_maximo = 0;

        if (StrTexto.length() != 10) {
            System.out.println("\t\tDame una Fecha Creible, del Formato: (AAAA-MM-DD)");
            return Respuesta = false;
        }
        for (int x = 0; x < StrTexto.length(); x++) {
            char c = StrTexto.charAt(x);
            if (!((c >= '0' && c <= '9') || c == '-' || c == ',' || c == '/' || c == '.')) {
                System.out.println("\t\tNo Ingrese Caracteres Invalidos a la Fecha");
                return Respuesta = false; // Si no está entre 0 y 9, o "-"
                // al primer fallo, acaba y no pierde tiempo analizando la cadena
            }
        }
        IntAno = Integer.valueOf(StrTexto.substring(0, 4)); //devuelve los primeros 4 caracteres
        IntMes = Integer.valueOf(StrTexto.substring(5, 7));
        IntDia = Integer.valueOf(StrTexto.substring(8, 10));
        System.out.println("\t\tla Fecha Proporcionada Es: " + IntAno + "-" + IntMes + "-" + IntDia);
        if (IntAno < 1900) {
            System.out.println("\t\tDudo que seas amigo de Don Alfonso :/");
            return Respuesta = false;
        }
        if (IntMes >= 1 && IntMes <= 12 && IntAno > 1900) {
            switch (IntMes) {
                case 4:
                case 6:
                case 9:
                case 11:
                    dia_maximo = 30;
                    break;
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    dia_maximo = 31;
                    break;
                case 2:
                    if (IntAno % 4 == 0 && IntAno % 100 != 0 || IntAno % 400 == 0) {
                        dia_maximo = 29;
                    } else {
                        dia_maximo = 28;
                    }
                    break;
            }
            if (IntDia >= 1 && IntDia <= dia_maximo) {
                Respuesta = true;
            } else {
                System.out.println("\t\tFecha Incorrecta");
                return Respuesta = false;
            }
        } else {
            System.out.println("\t\tFecha Incorrecta");
            return Respuesta = false;
        }
        return Respuesta;
    }

    Boolean SegunSuTipo(int opt, String Dato) {
        //Este Metodo hace la validacion segun el metodo que le encarguen
        Boolean OK = false;
        switch (opt) {
            case 0:
            case 1:
            case 2:
            case 3:
                OK = EsNombre_Apellido(Dato);
                break;
            case 4:
                OK = EsTelefono(Dato);
                break;
            case 5:
                OK = EsCelular(Dato);
                break;
            case 6:
                OK = EsEmail(Dato);
                break;
            case 7:
                OK = EsFecha(Dato);
                break;
        }
        return OK;
    }
}
