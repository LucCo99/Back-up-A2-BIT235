package net.javaguides.login.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.javaguides.login.bean.Article;

public class ArticleDao {

    // Define instance variables
    private String DBURL = "jdbc:mysql://localhost:3306/emp?serverTimezone=Australia/Melbourne";
    private String DBUsername = "root";
    private String DBPassword = "p@ssw0rd";

    private String INSERTARTICLESQL = "INSERT INTO articles (title, body, date) VALUES (?, ?, ?);";
    private String INSERTCATEGORYSQL = "INSERT INTO categories (category) VALUES (?);";
    private String SELECTARTICLEID = "SELECT id, title, body, date FROM articles WHERE id = ?";
    private String SELECTALLARTICLES = "SELECT * FROM articles";
    private String DELETEARTICLESQL = "DELETE FROM articles WHERE id = ?;";
    private String UPDATEARTICLESQL = "UPDATE articles SET title = ?, body = ?, date = ? WHERE id = ?;";
    private String HIDEARTICLESQL = "UPDATE articles SET status = 1 WHERE id = ?";


    public ArticleDao() {
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

    public void insertArticle(Article article) throws SQLException {
    	System.out.println(INSERTARTICLESQL);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(INSERTARTICLESQL);
            preparedStatement.setString(1, article.getTitle());
            preparedStatement.setString(2, article.getBody());
            preparedStatement.setString(3, article.getDate());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } finally {
            finallySQLException(connection, preparedStatement, null);
        }
    }
    
    //insert Category 
    public void insertCategory(Article article) throws SQLException {
    	System.out.println(INSERTARTICLESQL);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(INSERTCATEGORYSQL);
            preparedStatement.setString(1, article.getCategory());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } finally {
            finallySQLException(connection, preparedStatement, null);
        }
    }
    
   

    public Article selectArticle(int id) {
        Article article = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SELECTARTICLEID);
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String title = rs.getString("title");
                String body = rs.getString("body");
                String date = rs.getString("date");
//                String category = rs.getString("category");
                article = new Article(id, title, body, date);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } finally {
            finallySQLException(connection, preparedStatement, rs);
        }
        return article;
    }
    public List<Article> searchArticlesByKeyword(String keyword) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<Article> articles = new ArrayList<>();
        try {
            connection = getConnection();
            String query = "SELECT * FROM articles WHERE title LIKE ? OR body LIKE ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + keyword + "%");
            preparedStatement.setString(2, "%" + keyword + "%");
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String body = rs.getString("body");
                String date = rs.getString("date");
                String category = rs.getString("category");
                articles.add(new Article(id, title, body, date));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } finally {
            finallySQLException(connection, preparedStatement, rs);
        }
        return articles;
    }
    
   // List Individual Article
    public Article selectArticleById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Article article = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("SELECT id, title, body, date FROM articles WHERE id = ?");
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String title = rs.getString("title");
                String body = rs.getString("body");
                String date = rs.getString("date");
//                String category = rs.getString("category");
                article = new Article(id, title, body, date);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } finally {
            finallySQLException(connection, preparedStatement, rs);
        }
        return article;
    }



    public List<Article> selectAllArticles() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<Article> articles = new ArrayList<>();
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SELECTALLARTICLES);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String body = rs.getString("body");
                String date = rs.getString("date");
//                String category = rs.getString("category");
                int status = rs.getInt("status");
                if (status != 1) {
                    articles.add(new Article(id, title, body, date));
                }
            }
        } catch (SQLException e) {
            printSQLException(e);
        } finally {
            finallySQLException(connection, preparedStatement, rs);
        }
        return articles;
    }
      
    //list only hide article
    public List<Article> selectHideArticles() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<Article> articles = new ArrayList<>();
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SELECTALLARTICLES);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String body = rs.getString("body");
                String date = rs.getString("date");
//                String category = rs.getString("category");
                int status = rs.getInt("status");
                if (status != 0) {
                    articles.add(new Article(id, title, body, date));
                }
            }
        } catch (SQLException e) {
            printSQLException(e);
        } finally {
            finallySQLException(connection, preparedStatement, rs);
        }
        return articles;
    }
//hide 
    
    public boolean setArticlePrivate(int id) throws SQLException {
        boolean articleSetPrivate = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(HIDEARTICLESQL);
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                articleSetPrivate = true;
            }
        } finally {
            finallySQLException(connection, preparedStatement, null);
        }
        return articleSetPrivate;
    }



    
//    public List<Categories> selectAllCategories() {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet rs = null;
//        List<Categories> Categoriess = new ArrayList<>();
//        try {
//            connection = getConnection();
//            preparedStatement = connection.prepareStatement("SELECT * FROM category");
//            rs = preparedStatement.executeQuery();
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String title = rs.getString("title");
//                String category = rs.getString("category");
//                Categoriess.add(new Categories(id, title, category));
//            }
//        } catch (SQLException e) {
//            printSQLException(e);
//        } finally {
//            finallySQLException(connection, preparedStatement, rs);
//        }
//        return Categoriess;
//    }

    public boolean deleteArticle(int id) throws SQLException {
        boolean articleDeleted = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection(); 
            preparedStatement = connection.prepareStatement(DELETEARTICLESQL);
            preparedStatement.setInt(1, id);
            articleDeleted = preparedStatement.executeUpdate() > 0 ? true:false;
        } finally {
            finallySQLException(connection, preparedStatement, null);
        }
        return articleDeleted;
    }


public boolean updateArticle(Article article) throws SQLException {
    boolean articleUpdated = false;
    Connection connection = null; 
    PreparedStatement preparedStatement = null;
    try {
        connection = getConnection(); 
        preparedStatement = connection.prepareStatement(UPDATEARTICLESQL);
        // Use the appropriate setters to set the values for the prepared statement
        preparedStatement.setString(1, article.getTitle());
        preparedStatement.setString(2, article.getBody());
        preparedStatement.setString(3, article.getDate());
        preparedStatement.setInt(4, article.getId());
        articleUpdated = preparedStatement.executeUpdate() > 0 ? true:false;
    } catch (SQLException e) {
        // Handle any exceptions that occur during execution of the prepared statement
        printSQLException(e);
    } finally {
        finallySQLException(connection, preparedStatement, null);
    }
    return articleUpdated;
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
