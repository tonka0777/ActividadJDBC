import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EstudianteDAO {

    public void listarPorSemestre(int semestre) {
        String sql = "SELECT id, nombre, apellido, semestre, correo FROM estudiante WHERE semestre = ?";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setInt(1, semestre);
            ResultSet resultado = statement.executeQuery();

            System.out.println("Estudiantes del semestre " + semestre + ":");
            while (resultado.next()) {
                System.out.println(
                        resultado.getInt("id") + " - " +
                                resultado.getString("nombre") + " " +
                                resultado.getString("apellido") + " - " +
                                "Correo: " + resultado.getString("correo")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        EstudianteDAO dao = new EstudianteDAO();
        dao.listarPorSemestre(2);
    }
}