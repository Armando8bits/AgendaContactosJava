package proyectofinal;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author ROQUEARMANDORAMIREZP
 */
public class Contactos {

    private String StrID;
    private Boolean Existe = false;
    Scanner sc = new Scanner(System.in);
    Validaciones valida = new Validaciones();
    String[] StrEtiqueta = {"NOMBRES", "APELLIDO PATERNO", "APELLIDO MATERNO", "EMPRESA", "TELEFONO", "CELULAR", "CORREO", "FECHA DE NACIMIENTO (AAAA-MM-DD)"};
    String[] StrCampo = {"nombres", "Apellido_pa", "Apellido_ma", "Empresa", "Telefono", "Celular", "Correo", "Cumpleanos"};
    String[] StrDato = new String[8]; //ya que son 8 datos que va a recopilar

    private String StrConsulta;
    private Boolean Est = true;

    String agregar() {
        System.out.println("\nINTRODUCE LOS DATOS CORRESPONDIENTES A: ");
        for (int i = 0; i <= 7; i++) {
            do {
                System.out.print("\t> " + StrEtiqueta[i] + ": ");
                StrDato[i] = sc.nextLine();
                Est = valida.SegunSuTipo(i, StrDato[i]);//pasa por parametro el dato y la manera en que se validará
            } while (Est == false);//mientras sea false (dato erroneo) te sigue preguntando
        }

        StrConsulta = "INSERT INTO contactos (`nombres`, `Apellido_pa`, `Apellido_ma`, `Empresa`, `Telefono`, `Celular`, `Correo`, `Cumpleanos`) VALUES ('" + StrDato[0] + "','" + StrDato[1] + "','" + StrDato[2] + "','" + StrDato[3] + "','" + StrDato[4] + "','" + StrDato[5] + "','" + StrDato[6] + "','" + StrDato[7] + "')";
        return StrConsulta;
    }

    String editar() {
        StrConsulta = "";
        boolean salir = false;
        do {
            System.out.print("\tINGRESE ID A MODIFICAR: ");
            StrID = sc.nextLine();
            Est = valida.EsID(StrID);
        } while (Est == false);

        Conexion CompruebaID = new Conexion();
        Existe = CompruebaID.Existe(StrID);
        if (Existe == true) {
            System.out.println("\nINDICA QUE DATOS QUIERES MODIFICAR: ");
            while (!salir) {
                for (int i = 0; i <= 7; i++) {
                    System.out.println("\t[" + (i+1) + "] " + StrEtiqueta[i]);
                }
                System.out.println("\t[9] TODOS");
                System.out.println("\t[0] CANCELAR");
                try {
                    System.out.print("\nIngrese su Opcion: ");
                    int opcion = sc.nextInt();

                    switch (opcion) {
                        case 0:
                            StrConsulta = "";
                            break;
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                            do {
                                System.out.print("\t> " + StrEtiqueta[opcion-1] + ": ");
                                StrDato[opcion-1] = sc.nextLine();
                                Est = valida.SegunSuTipo(opcion-1, StrDato[opcion-1]);//pasa por parametro el dato y la manera en que se validará
                            } while (Est == false);//mientras sea false (dato erroneo) te sigue preguntando
                            StrConsulta = "UPDATE contactos SET "+StrCampo[opcion-1]+"='" + StrDato[opcion-1] + "' WHERE id=" + StrID;
                            System.out.println(" --Modificando Registro...");
                            break;
                        case 9:
                            for (int i = 0; i <= 7; i++) {
                                do {
                                    System.out.print("\t> " + StrEtiqueta[i] + ": ");
                                    StrDato[i] = sc.nextLine();
                                    Est = valida.SegunSuTipo(i, StrDato[i]);//pasa por parametro el dato y la manera en que se validará
                                } while (Est == false);//mientras sea false (dato erroneo) te sigue preguntando
                            }
                            StrConsulta = "UPDATE contactos SET nombres='" + StrDato[0] + "', Apellido_pa='" + StrDato[1] + "', Apellido_ma='" + StrDato[2] + "', Empresa='" + StrDato[3] + "', Telefono='" + StrDato[4] + "', Celular='" + StrDato[5] + "', Correo='" + StrDato[6] + "', Cumpleanos='" + StrDato[7] + "' WHERE id=" + StrID;
                            break;
                        default:
                            System.out.println("\n -- Solo Opciones Entre 0 y 9 --\n");
                    }
                    salir = true; //no importa que opcion escoja, siempre se romperá el ciclo While
                } catch (InputMismatchException j) {
                    System.out.println("\n ** Solo Ingresa un Numero **\n");
                    sc.next();
                }
            }
        }
        return StrConsulta;
    }

    String eliminar() {
        boolean salir = false;
        StrConsulta = "";
        do {
            System.out.print("\tINGRESE ID PARA ELIMINAR: ");
            StrID = sc.nextLine();
            Est = valida.EsID(StrID);
        } while (Est == false);

        Conexion CompruebaID = new Conexion();
        Existe = CompruebaID.Existe(StrID);
        if (Existe == true) {
            while (!salir) {
                System.out.println("\n ==¿Seguro de Eliminar?==");
                System.out.println("\t[1] Si");
                System.out.println("\t[2] No");

                try {
                    System.out.print("Ingrese su Opcion: ");
                    int opcion = sc.nextInt();

                    switch (opcion) {
                        case 1:
                            StrConsulta = "DELETE FROM contactos WHERE id=" + StrID + "";
                            System.out.println(" -Borrando...");
                            salir = true;
                            break;
                        case 2:
                            StrConsulta = "";
                            System.out.println("\n -- Eliminacion Cancelada por Usuario --\n");
                            salir = true;
                            break;
                        default:
                            System.out.println("\n -- Solo Tienes Opcion Entre 1 y 2 --\n");
                    }
                } catch (InputMismatchException j) {
                    System.out.println("\n ** Solo Ingresa un Numero **\n");
                    sc.next();
                }
            }
        }
        return StrConsulta;
    }

    String consulta() {
        String StrCampo = "";
        String StrTemp;
        boolean salir = false;

        while (!salir) {
            System.out.println("\nBUSCAR CONTACTO POR:");
            System.out.println("\t[1] Nombres");
            System.out.println("\t[2] Apellido Paterno");
            System.out.println("\t[3] Apellido Materno\n");

            try {
                System.out.print("Ingrese su Opcion: ");
                int opcion = sc.nextInt();

                switch (opcion) {
                    case 1:
                        StrCampo = "nombres";
                        break;
                    case 2:
                        StrCampo = "Apellido_pa";
                        break;
                    case 3:
                        StrCampo = "Apellido_ma";
                        break;
                    default:
                        System.out.println("\n -- Solo Opciones Entre 1 y 3 --\n");
                }
                salir = true;
            } catch (InputMismatchException j) {
                System.out.println("\n ** Solo Ingresa un Numero **\n");
                sc.next();
            }
        }
        do {
            System.out.print("\tTEXTO A BUSCAR: ");
            StrTemp = sc.nextLine();
            Est = valida.EsNombre_Apellido(StrTemp);
        } while (Est == false);

        StrConsulta = "SELECT * FROM contactos WHERE " + StrCampo + " LIKE '%" + StrTemp + "%'";
        return StrConsulta;
    }

    String ObtenerDirectorio() {
        StrConsulta = "";
        try {
            System.out.println("ESCOJA ORDEN PARA MOSTRAR DIRECTORIO");
            System.out.println("\n\t[1] Nombres ASC");
            System.out.println("\t[2] Nombres DESC");
            System.out.println("\t[3] Apellido Paterno ASC");
            System.out.println("\t[4] Apellido Paterno DESC");
            System.out.println("\t[5] Apellido Materno ASC");
            System.out.println("\t[6] Apellido Materno DESC");
            System.out.println("\t[7] Empresa");
            System.out.println("\t[8] Fecha de Nacimiento ASC");
            System.out.println("\t[9] Fecha de Nacimiento DESC");
            
            System.out.print("\n\tSU OPCION: ");
            int opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    StrConsulta = "SELECT * FROM contactos ORDER BY nombres ASC";
                    break;
                case 2:
                    StrConsulta = "SELECT * FROM contactos ORDER BY nombres DESC";
                    break;
                case 3:
                    StrConsulta = "SELECT * FROM contactos ORDER BY Apellido_pa ASC";
                    break;
                case 4:
                    StrConsulta = "SELECT * FROM contactos ORDER BY Apellido_pa DESC";
                    break;
                case 5:
                    StrConsulta = "SELECT * FROM contactos ORDER BY Apellido_ma ASC";
                    break;
                case 6:
                    StrConsulta = "SELECT * FROM contactos ORDER BY Apellido_ma DESC";
                    break;
                case 7:
                    StrConsulta = "SELECT * FROM contactos ORDER BY Empresa";
                    break;
                case 8:
                    StrConsulta = "SELECT * FROM contactos ORDER BY Cumpleanos ASC";
                    break;
                case 9:
                    StrConsulta = "SELECT * FROM contactos ORDER BY Cumpleanos DESC";
                    break;
                default:
                    System.out.println("\n -- Solo Opciones Entre 1 y 3 --\n");
            }
        } catch (InputMismatchException j) {
            System.out.println("\n ** Solo Ingresa un Numero **\n");
            sc.next();
        }
        return StrConsulta;
    }
}