public class AlocacaoEngenheiroDAO {
    private Connection connection;

    public AlocacaoEngenheiroDAO() throws SQLException {
        this.connection = ConexaoBD.getInstance().getConnection();
    }

    public List<Engenheiro> listarEngenheirosPorProjeto(int idProjeto) throws SQLException {
        List<Engenheiro> engenheiros = new ArrayList<>();
        String sql = "SELECT e.* FROM Engenheiro e JOIN Alocacao_Engenheiro a ON e.idEngenheiro = a.idEngenheiro WHERE a.idProjeto = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idProjeto);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Engenheiro engenheiro = new Engenheiro();
                engenheiro.setIdEngenheiro(rs.getInt("idEngenheiro"));
                engenheiro.setNomeEngenheiro(rs.getString("nomeEngenheiro"));
                engenheiro.setEspecialidade(rs.getString("especialidade"));
                engenheiros.add(engenheiro);
            }
        }
        return engenheiros;
    }
}

