import java.sql.*;
import java.util.ArrayList;

public class BDController {
    private Connection connection;
    private PreparedStatement existePesp;
    private PreparedStatement existePespDiccionario;
    private PreparedStatement existePingDiccionario;
    private PreparedStatement existePalabra;

    BDController(){
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/teachmealex", "root","");
            String SQLExistePesp = "select * from palabra where nEsp = ?";
            this.existePesp = connection.prepareStatement(SQLExistePesp);
            String SQLExistePalabra = "select * from palabra where nEsp = ? or nIng = ?";
            this.existePalabra = connection.prepareStatement(SQLExistePalabra);
            String SQLExistePespDiccionario = "select * from desp where palabra = ?";
            this.existePespDiccionario = connection.prepareStatement(SQLExistePespDiccionario);
            String SQLExistePingDiccionario = "select * from ding where word = ?";
            this.existePingDiccionario = connection.prepareStatement(SQLExistePingDiccionario);
        }catch (SQLException e){
            System.out.println("Error: "+e.getMessage());
        }
    }

    public void altaPalabra(Palabra palabra){
        String sql = "insert into palabra values ('"+palabra.getEsp()+"','"+palabra.getIng()+"')";
        try {
            Statement ms = this.connection.createStatement();
            ms.executeUpdate(sql);
            ms.close();
        }catch (SQLException e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    public void bajaPalabra(String palabra){
        String sql = "delete from palabra where nIng = '"+palabra+"' or nEsp = '"+palabra+"';";
        try {
            Statement ms = connection.createStatement();
            ms.executeUpdate(sql);
            ms.close();
        }catch (SQLException e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    public ArrayList<Palabra> palabrasRandom(){
        ArrayList<Palabra> palabras = new ArrayList<>();
        String sql = "select * from palabra order by rand()";
        try {
            Statement ms = connection.createStatement();
            ResultSet rs = ms.executeQuery(sql);
            while (rs.next()){
                palabras.add(new Palabra(rs.getString("nEsp"), rs.getString("nIng")));
            }
            rs.close();
        }catch (SQLException e){
            System.out.println("Error: "+e.getMessage());
        }

        return palabras;
    }

    public boolean existePesp(String palabra){
        boolean existe = true;
        try {
            existePesp.setString(1,palabra);
            ResultSet rs = existePesp.executeQuery();
            if (!rs.first()){
                existe = false;
            }
        }catch (SQLException e){
            System.out.println("Error: "+e.getMessage());
        }
        return existe;
    }
    public boolean existePalabra(String palabra){
        boolean existe = true;
        try {
            existePalabra.setString(1,palabra);
            existePalabra.setString(2,palabra);
            ResultSet rs = existePalabra.executeQuery();
            if (!rs.first()){
                existe = false;
            }
        }catch (SQLException e){
            System.out.println("Error: "+e.getMessage());
        }
        return existe;
    }
    public boolean existePDiccionario(String diccionario,String palabra){
        boolean existe = true;
        try {
            if (diccionario.equalsIgnoreCase("desp")) {
                existePespDiccionario.setString(1, palabra);
                ResultSet rs = existePespDiccionario.executeQuery();
                if (!rs.first()) {
                    existe = false;
                }
            }else {
                existePingDiccionario.setString(1, palabra);
                ResultSet rs = existePingDiccionario.executeQuery();
                if (!rs.first()) {
                    existe = false;
                }
            }
        }catch (SQLException e){
            System.out.println("Error en palabra: "+e.getMessage());
        }
        return existe;
    }
}
