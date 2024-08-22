import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjetoDAO {
    private Connection connection;

    public ProjetoDAO() throws SQLException {
        this.connection = ConexaoBD.getInstance().getConnection();
    }

    public void inserir(Projeto projeto) throws SQLException {
        String sql = "INSERT INTO Projeto (nomeProjeto, local, dataInicio, dataTermino) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, projeto.getNomeProjeto());
            stmt.setString(2, projeto.getLocal());
            stmt.setString(3, projeto.getDataInicio());
            stmt.setString(4, projeto.getDataTermino());
            stmt.executeUpdate();
        }
    }

    public void atualizar(Projeto projeto) throws SQLException {
        String sql = "UPDATE Projeto SET nomeProjeto = ?, local = ?, dataInicio = ?, dataTermino = ? WHERE idProjeto = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, projeto.getNomeProjeto());
            stmt.setString(2, projeto.getLocal());
            stmt.setString(3, projeto.getDataInicio());
            stmt.setString(4, projeto.getDataTermino());
            stmt.setInt(5, projeto.getIdProjeto());
            stmt.executeUpdate();
        }
    }

    public void excluir(int idProjeto) throws SQLException {
        String sql = "DELETE FROM Projeto WHERE idProjeto = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idProjeto);
            stmt.executeUpdate();
        }
    }

    public Projeto listar(int idProjeto) throws SQLException {
        String sql = "SELECT * FROM Projeto WHERE idProjeto = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idProjeto);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Projeto projeto = new Projeto();
                projeto.setIdProjeto(rs.getInt("idProjeto"));
                projeto.setNomeProjeto(rs.getString("nomeProjeto"));
                projeto.setLocal(rs.getString("local"));
                projeto.setDataInicio(rs.getString("dataInicio"));
                projeto.setDataTermino(rs.getString("dataTermino"));
                return projeto;
            }
        }
        return null;
    }

    public List<Projeto> listarTodos() throws SQLException {
        List<Projeto> projetos = new ArrayList<>();
        String sql = "SELECT * FROM Projeto";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Projeto projeto = new Projeto();
                projeto.setIdProjeto(rs.getInt("idProjeto"));
                projeto.setNomeProjeto(rs.getString("nomeProjeto"));
                projeto.setLocal(rs.getString("local"));
                projeto.setDataInicio(rs.getString("dataInicio"));
                projeto.setDataTermino(rs.getString("dataTermino"));
                projetos.add(projeto);
            }
        }
        return projetos;
    }
}
