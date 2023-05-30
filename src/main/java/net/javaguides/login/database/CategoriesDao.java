package net.javaguides.login.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.javaguides.login.bean.Categories;


public class CategoriesDao {

    // Define instance variables
    private String DBURL = "jdbc:mysql://localhost:3306/emp?serverTimezone=Australia/Melbourne";
    private String DBUsername = "root";
    private String DBPassword = "p@ssw0rd";

    private String INSERTCategoriesSQL = "INSERT INTO articles (title, body, date) VALUES (?, ?, ?);";
    private String SELECTCategoriesID = "SELECT id, title, body, date FROM articles WHERE id = ?";
    private String SELECTALLCategoriesS = "SELECT * FROM categories";
    private String DELETECategoriesSQL = "DELETE FROM articles WHERE id = ?;";
    private String UPDATECategoriesSQL = "UPDATE articles SET title = ?, body = ?, date = ? WHERE id = ?;";

    public CategoriesDao() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DBURL, DBUsername, DBPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertCategories(Categories Categories) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(INSERTCategoriesSQL);
            //preparedStatement.setLong(1, Categories.getId());
            preparedStatement.setString(2, Categories.getTitle());
            //preparedStatement.setDate(3, Categories.getDate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } finally {
            finallySQLException(connection, preparedStatement, null);
        }
    }

    public Categories selectCategories(int id) {
        Categories Categories = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SELECTCategoriesID);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String title = rs.getString("title");
                String category = rs.getString("category");
                Categories = new Categories(id, title, category);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } finally {
            finallySQLException(connection, preparedStatement, rs);
        }
        return Categories;
    }

    
    public List<Categories> selectAllCategories() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<Categories> Categoriess = new ArrayList<>();
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SELECTALLCategoriesS);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String category = rs.getString("category");
                Categoriess.add(new Categories(id, category));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } finally {
            finallySQLException(connection, preparedStatement, rs);
        }
        return Categoriess;
    }

    public boolean deleteCategories(int id) throws SQLException {
        boolean CategoriesDeleted = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection(); 
            preparedStatement = connection.prepareStatement(DELETECategoriesSQL);
            preparedStatement.setInt(1, id);
            CategoriesDeleted = preparedStatement.executeUpdate() > 0 ? true : false;
        } finally {
            finallySQLException(connection, preparedStatement, null);
        }
        return CategoriesDeleted;
    }


public boolean updateCategories(Categories Categories) throws SQLException {
    boolean CategoriesUpdated = false;
    Connection connection = null; 
    PreparedStatement preparedStatement = null;
    try {
        connection = getConnection(); 
        preparedStatement = connection.prepareStatement(UPDATECategoriesSQL);
        // Use the appropriate setters to set the values for the prepared statement
        preparedStatement.setString(1, Categories.getTitle());
        preparedStatement.setString(3, Categories.getCategory());
        CategoriesUpdated = preparedStatement.executeUpdate() > 0 ? true : false;
    } catch (SQLException e) {
        // Handle any exceptions that occur during execution of the prepared statement
        printSQLException(e);
    } finally {
        finallySQLException(connection, preparedStatement, null);
    }
    return CategoriesUpdated;
}

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    private void finallySQLException(Connection c, PreparedStatement p, ResultSet r){
    	 if (r != null)	{
             try {
                r.close();
             } catch (Exception e) {}
                r = null;
             }

          if (p != null) {
             try {
                p.close();
             } catch (Exception e) {}
                p = null;
             }

          if (c != null) {
             try {
                c.close();
             } catch (Exception e) {
           	  c = null;
             }

          }
    }
}
