package proyectofinal;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author ROQUEARMANDORAMIREZP
 */
public class ProyectoFinal {

    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        String Consulta = "";
        //Boolean Estado;
        int opcion; //Guardaremos la opcion del usuario

//Consulta.length() < 1 &&
        while (!salir) {
            System.out.println("\n::::::::::::::::::::::::::::::::::::::::");
            System.out.println("::          AGENDA DIGITAL            ::");
            System.out.println("::::::::::::::::::::::::::::::::::::::::");
            System.out.println("         OPCIONES:");
            System.out.println("\t[1] Ingresar Contactos");
            System.out.println("\t[2] Modificar Contactos");
            System.out.println("\t[3] Eliminar Contactos");
            System.out.println("\t[4] Consultar Contactos");
            System.out.println("\t[5] Ver todos los contactos");
            System.out.println("\t[6] Salir\n");

            try {
                System.out.print("Escribe Una de las Opciones: ");
                opcion = sn.nextInt();

                switch (opcion) {
                    case 1:
                        Contactos nuevoContacto = new Contactos();
                        Consulta = nuevoContacto.agregar();
                        break;
                    case 2:
                        Contactos modificaContacto = new Contactos();
                        Consulta = modificaContacto.editar();
                        break;
                    case 3:
                        Contactos eliminaContacto = new Contactos();
                        Consulta = eliminaContacto.eliminar();
                        break;
                    case 4:
                        Contactos consultaContacto = new Contactos();
                        Consulta = consultaContacto.consulta();
                        break;
                    case 5:
                        Contactos verTodosContactos = new Contactos();
                        Consulta = verTodosContactos.ObtenerDirectorio();
                        break;
                    case 6:
                        salir = true;
                        System.out.println("\n BYE !");
                        sn.close();
                        break;
                    default:
                        System.out.println("\n -- Solo Números Entre 1 y 6 --\n");
                }
                if (Consulta.length() > 1) {
                    Conexion sentencia = new Conexion();
                    sentencia.EJECUTAR(Consulta,opcion);
                }
            } catch (InputMismatchException t) {
                System.out.println("\n ** Debes Insertar un Numero **\n");
                sn.next();
            }
        }
    }
}