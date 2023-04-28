package proyectofinal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author ROQUEARMANDORAMIREZP
 */
public class Conexion {

    int IntContador;

    //Boolean Estado;
    void EJECUTAR(String StrSqlSentencia, int IntModo) {
        try {
            Connection miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/agendapoo2", "root", "");
            Statement miStatement = miConexion.createStatement();

            switch (IntModo) {
                case 1:
                case 2:
                case 3:
                    miStatement.executeUpdate(StrSqlSentencia);
                    break;
                case 4:
                case 5:
                    ResultSet miResultset = miStatement.executeQuery(StrSqlSentencia);
                    System.out.println("______________________________________________________________________________________________________");
                    System.out.println("\nID - NOMBRES - APELLIDO PA - APELLIDO MA - EMPRESA - TELEFONO - CELULAR - CORREO - FECHA_NACIMIENTO");
                    System.out.println("------------------------------------------------------------------------------------------------------");
                    while (miResultset.next()) {
                        IntContador++;
                        System.out.println(miResultset.getString("id") + " - " + miResultset.getString("nombres") + " - " + miResultset.getString("Apellido_pa") + " - " + miResultset.getString("Apellido_ma") + " - " + miResultset.getString("Empresa") + " - " + miResultset.getString("Telefono") + " - " + miResultset.getString("Celular") + " - " + miResultset.getString("Correo") + " - " + miResultset.getString("Cumpleanos"));
                    }
                    System.out.println("______________________________________________________________________________________________________");
                    System.out.println("\t\t\t\t Registros Encontrados: " + IntContador);
                    System.out.println("------------------------------------------------------------------------------------------------------");
                    break;
            }
            System.out.println("\t\t\t\t\t\t\t\tOPERACION CULMINADA EXITOSAMENTE");

        } catch (Exception e) {
            System.out.println("No se conecta :(");
            e.printStackTrace();
        }
    }

    Boolean Existe(String StrId) {
        IntContador = 0;
        Boolean Respuesta = false;
        try {
            Connection miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/agendapoo2", "root", "");
            Statement miStatement = miConexion.createStatement();

            ResultSet miResultset = miStatement.executeQuery("SELECT * FROM contactos WHERE id='" + StrId + "'");
            IntContador = 0;
            System.out.println("\n\t\t >Resultado Optenido: ");
            while (miResultset.next()) {
                IntContador++;
                System.out.println("\t\tID: " + miResultset.getString("id"));
                System.out.println("\t\tNombres: " + miResultset.getString("nombres"));
                System.out.println("\t\tApellido Paterno :" + miResultset.getString("Apellido_pa"));
                System.out.println("\t\tApellido Materno: " + miResultset.getString("Apellido_ma"));
                System.out.println("\t\tEmpresa: " + miResultset.getString("Empresa"));
                System.out.println("\t\tTelefono Convencional: " + miResultset.getString("Telefono"));
                System.out.println("\t\tTelefono Celular: " + miResultset.getString("Celular"));
                System.out.println("\t\tCorreo Electronico: " + miResultset.getString("Correo"));
                System.out.println("\t\tFecha Nacimiento: " + miResultset.getString("Cumpleanos"));
            }
            if (IntContador != 1) {
                System.out.print("ID Erroneo, Intente de Nuevo**");
                Respuesta = false;
            } else {
                Respuesta = true;
            }

        } catch (SQLException e) {
            System.out.println("**ERROR**");
            e.printStackTrace();
            System.out.println(e.getErrorCode());
        }
        return Respuesta;
    }
}
