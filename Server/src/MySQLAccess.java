
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
    /** 
* MySQLAccess
* contains a 
*  <ul>
* <li><code>Connection</code>   
* <li><code>Statement</code>
* <li><code>PreparedStatement</code>
* <li><code>ResultSet</code>
* <li><code>driver </code>
* </ul>
* Methods
* <ul>
* <li> execSP
* <li> execWithParameters
* <li> execResultSet
* <li> execResults
* <li> execID
* <li> close
* </ul>
* @author: Joanna Smith
* @version          4.0
* @see             Class Validation
* @see             Class WordCount
*/
public class MySQLAccess {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private String conn = "jdbc:mysql://localhost/wordcount?user=mySQLuser&password=secret";
    private String driver = "com.mysql.cj.jdbc.Driver";
    final ObservableList ol = FXCollections.observableArrayList();
    final ObservableList ol2 = FXCollections.observableArrayList();
        /** 
* This procedure uses the connection above,
* creates a <code> preparedStatement</code> and 
* uses <code>executeQuery</code> to run the code
* passed in the parameter
* Does not produce a <code>resultset</code>
* 
* @param    query   the mySQL procedure or statement to run
* @throws Exception exception
* @author:  Joanna Smith
* @version  4.0
* @see      execWithParameters
* @see      execResultSet
* @see      execResults
* @see      execID
* @see      close
*/
    public void execSP( String query) throws Exception{
         try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName(driver).newInstance();
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection(conn);
            
    
                        // PreparedStatements can use variables and are more efficient
            preparedStatement = connect
                    .prepareStatement(query);

            preparedStatement.executeQuery();
             
            } catch (Exception e) {
            throw e;
        } finally {
            close();
        }

    }
    /** 
* This procedure uses the connection above,
* creates a <code> preparedStatement</code> and 
* uses <code>executeQuery</code> to run the code
* passed in the parameter
* It accepts parameters to use in the query/procedure
* Does not produce a <code>resultset</code>
* 
* @param    query   the mySQL procedure or statement to run
* @param    params   a list of the parameters to add to the query
* @throws   Exception  exception
* @author:  Joanna Smith
* @version  4.0
* @see      execSP
* @see      execResultSet
* @see      execResults
* @see      execID
* @see      close
*/
    public void execWithParameters( String query, List<String> params) throws Exception{
         try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName(driver);
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection(conn);
            
    
            // PreparedStatements can use variables and are more efficient
            preparedStatement = connect
                    .prepareStatement(query);
           
            // Parameters start with 1
            int i = 1;
            for (String s : params) {
                 preparedStatement.setString(i++, s);
            }
            resultSet = preparedStatement.executeQuery();
            
            } catch (Exception e) {
            //System.out.println(e.getMessage());
            throw e;
        } finally {
            close();
        }

    }
    /** 
* This procedure uses the connection above,
* creates a <code> preparedStatement</code> and 
* uses <code>executeQuery</code> to run the code
* passed in the parameter
* Produces a <code>resultset</code>
* 
* @param    query   the mySQL procedure or statement to run
* @param    params  the list of parameters to pass to the query/statement
* @throws   Exception exception
* @return   CachedRowSet a copy of the resultSet returned in the query
* @author:  Joanna Smith
* @version  4.0
* @see      execSP
* @see      execWithParameters
* @see      execResultSet
* @see      execResults
* @see      execID
* @see      close
*/
    public CachedRowSet execResultSet( String query, List<String> params) throws Exception{
                
            RowSetFactory factory = RowSetProvider.newFactory();
            CachedRowSet rowset = factory.createCachedRowSet();
            
         try {
            // This will load the MySQL driver, each DB has its own driver
            
            Class.forName(driver).newInstance();
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection(conn);
            
    
                        // PreparedStatements can use variables and are more efficient
            preparedStatement = connect
                    .prepareStatement(query);
           
            // Parameters start with 1
            int i = 1;
            for (String s : params) {
                 preparedStatement.setString(i++, s);
            }
            resultSet = preparedStatement.executeQuery();
            
            rowset.populate(resultSet);
            
    
            } catch (Exception e) {
            System.out.println(e.getMessage());
            //throw e;
        } finally {
            close();
        }
        return rowset;
    }
    /** 
* This procedure uses the connection above,
* creates a <code> preparedStatement</code> and 
* uses <code>executeQuery</code> to run the code
* passed in the parameter
* does not accept parameters to run in the query/statement
* Produces a <code>resultset</code>
* 
* @param    query   the mySQL procedure or statement to run
* @throws   Exception exception
* @return   CachedRowSet  a copy of the resultSet returned in the query
* @author:  Joanna Smith
* @version  4.0
* @see      execSP
* @see      execWithParameters
* @see      execResults
* @see      execID
* @see      close
*/
   public CachedRowSet execResults( String query) throws Exception{
            RowSetFactory factory = RowSetProvider.newFactory();
            CachedRowSet rowset = factory.createCachedRowSet();
            
         try {
            // This will load the MySQL driver, each DB has its own driver
            
            Class.forName(driver).newInstance();
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection(conn);
            
    
                        // PreparedStatements can use variables and are more efficient
            preparedStatement = connect
                    .prepareStatement(query);
           
            // Parameters start with 1

            resultSet = preparedStatement.executeQuery();
            
            rowset.populate(resultSet);
            
            
    
            } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
        return rowset;
    }
/** 
* This procedure uses the connection above,
* creates a <code> preparedStatement</code> and 
* uses <code>executeQuery</code> to run the code
* passed in the parameter
* Does not produce a <code>resultset</code>
* 
* @param    query   the mySQL procedure or statement to run
* @param    params  the list of parameters to pass to the query/statement
* @throws   Exception exception
* @return   returnID integer produced by the mySQL procedure, usually the auto-increment from an insert statement
* @author:  Joanna Smith
* @version  4.0
* @see      execSP
* @see      execWithParameters
* @see      execResultSet
* @see      execResults
* @see      close
*/     
        public int execID( String query, List<String> params) throws Exception{
            int returnID=0;
         try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName(driver);
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection(conn);
            
    
                        // PreparedStatements can use variables and are more efficient
            preparedStatement = connect
                    .prepareStatement(query);
           
            // Parameters start with 1
            int i = 1;
            for (String s : params) {
                 preparedStatement.setString(i++, s);
            }
            returnID=preparedStatement.executeUpdate();
            
            } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
         return returnID;

    }

/** 
* This procedure closes the connection above,
* any resultSets opened above, or any preparedStatements open

* @author:  Joanna Smith
* @version  4.0
* @see      execSP
* @see      execWithParameters
* @see      execResultSet
* @see      execResults
* @see      execID
* @see      close
*/  
    // You need to close the resultSet
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }

}
