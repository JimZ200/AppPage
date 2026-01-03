import javax.swing.*;
import java.sql.*;
import java.util.*;

public class DataBaseManager {

    private static final String url = "jdbc:sqlite:usersdb.db";

    public static boolean checkLogin(String userName, String password) {
        String sql = "Select * from Users where username = ? and password = ? ";

        try (Connection conn = DriverManager.getConnection(url)) {

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1,userName);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            System.out.println("Error" + e.getMessage());
            return false;
        }

    }

    public static boolean addInfo(String username, String password, String fname, String lname){
        String sql = "insert into Users values(?,?,?,?, '#FFFFFF')";

        try(Connection conn = DriverManager.getConnection(url)){

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1,username);
            pstmt.setString(2,password);
            pstmt.setString(3,fname);
            pstmt.setString(4,lname);

            int rowInserted = pstmt.executeUpdate();

            return rowInserted > 0;

        } catch (SQLException e){
            return false;
        }
    }

    public static void setColour(String colour, String userName){
        String sql = "update Users set backgroundColour = ? where username = ?";

        try(Connection conn = DriverManager.getConnection(url)){
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, colour);
            ps.setString(2,userName);

            int row = ps.executeUpdate();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static String getColour(String userName){
        String sql = "select backgroundColour from Users where username = ?";

        try(Connection conn = DriverManager.getConnection(url)){
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1,userName);

            ResultSet result = ps.executeQuery();

            if(result.next()) return result.getString("backgroundColour");

        } catch (SQLException e){
            System.out.println(e.getMessage());

        }

        return "#FFFFFF";
    }

    public static void addNotes(String note, String username){
        String sql = "insert into Notes(username, noteContent) values(?,?)";

        try(Connection conn = DriverManager.getConnection(url)){

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, note);

            int row = ps.executeUpdate();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<String> getNotes(String userName){

        ArrayList<String> note = new ArrayList<>();

        String sql = "Select noteContent from Notes where username = ?";

        try(Connection conn = DriverManager.getConnection(url)){

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, userName);

            ResultSet result = ps.executeQuery();

            while(result.next()){
                note.add(result.getString("noteContent"));
            }

            return note;

        } catch (SQLException e){
            return new ArrayList<>();
        }
    }
}
