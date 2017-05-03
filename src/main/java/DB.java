import java.sql.*;
import java.util.ArrayList;

public class DB {

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";        //Configure the driver needed
    private static final String DB_CONNECTION_URL = "jdbc:mysql://localhost/RecordStore?useLegacyDatetimeCode=false&serverTimezone=America/Chicago";
    private static final String USER = "sam";
    private static final String PASSWORD = "agbeh";
    private static final String C_TABLE = "consignors";
    private static final String R_TABLE = "records";
    private static final String S_TABLE = "sales";
    private static final String I_TABLE = "invoices";
    private static final String CID_COL = "cid";
    private static final String FIRSTNAME_COL = "first_name";
    private static final String LASTNAME_COL = "last_name";
    private static final String PHONE_COL = "phone_number";
    private static final String RID_COL = "rid";
    private static final String ARTIST_COL = "artist";
    private static final String TITLE_COL = "title";
    private static final String SALESPRICE_COL = "sales_price";
    private static final String DATEADDED_COL = "date_added";
    private static final String STATUS_COL = "status";
    private static final String NOTIFIED_COL = "notified";
    private static final String SALEID_COL = "sale_id";
    private static final String SOLDDATE_COL = "sold_date";
    private static final String SOLDPRICE_COL = "sold_price";
    private static final String CIDPROFIT_COL = "cid_profit";
    private static final String STOREPROFIT_COL = "store_profit";
    private static final String AMOUNTPAID_COL = "amount_paid";
    private static final String PAYMENTDATE_COL = "payment_date";
    private static final String BALANCE_COL = "balance";



    DB() {

        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Can't instantiate driver class; check you have drives and classpath configured correctly?");
            cnfe.printStackTrace();
            System.exit(-1);  //No driver? Need to fix before anything else will work. So quit the program
        }
    }

    void createTable() {

        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);
             Statement statement = conn.createStatement()) {

            //You should have already created a database via terminal/command prompt

            //Create a table in the database, if it does not exist already
            //Can use String formatting to build this type of String from constants coded in your program
            //Don't do this with variables with data from the user!! That's what ParameterisedStatements are, and that's for queries, updates etc. , not creating tables.
            // You shouldn't make database schemas from user input anyway.
            String createConsignorsTableSQLTemplate = "CREATE TABLE IF NOT EXISTS %s (%s int, %s VARCHAR (25), %s VARCHAR (25), %s int)";
            String createConsignorsTableSQL = String.format(createConsignorsTableSQLTemplate, CID_COL, FIRSTNAME_COL, LASTNAME_COL, PHONE_COL);

            String createRecordsTableSQLTemplate = "CREATE TABLE IF NOT EXISTS %s (%s int, % int, %s VARCHAR (50), %s VARCHAR (50), % decimal (10,2), % date, %s int, % VARCHAR (1))";
            String createRecordsTableSQL = String.format(createRecordsTableSQLTemplate, RID_COL, CID_COL, ARTIST_COL, TITLE_COL, SALESPRICE_COL, DATEADDED_COL, STATUS_COL, NOTIFIED_COL);



            statement.executeUpdate(createConsignorsTableSQL);
            System.out.println("Created consignors table");

            statement.close();
            conn.close();

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }


    void addRecord(Consignor consignor)  {

        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD)) {

            String addConsignorSQL = "INSERT INTO " + TABLE_NAME + " VALUES ( ? , ? ) " ;
            PreparedStatement addConsignorPS = conn.prepareStatement(ConsignorSQL);
            ConsignorPS.setString(1, consignor.name);
            ConsignorPS.setDouble(2, consignor.recordTime);

            ConsignorPS.execute();

            System.out.println("Added Consignor object for " + consignor);

            ConsignorPS.close();
            conn.close();

        } catch (SQLException se) {
            se.printStackTrace();
        }

    }

    ArrayList<Consignor> fetchAllRecords() {

        ArrayList<Consignor> allRecords = new ArrayList();

        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);
             Statement statement = conn.createStatement()) {

            String selectAllSQL = "SELECT * FROM " + TABLE_NAME;
            ResultSet rsAll = statement.executeQuery(selectAllSQL);

            while (rsAll.next()) {
                String name = rsAll.getString(NAME_COL);
                double recordTime = rsAll.getDouble(RECORD_COL);
                Consignor consignor = new Consignor(name, recordTime);
                allRecords.add(consignor);
            }

            rsAll.close();
            statement.close();
            conn.close();

            return allRecords;    //If there's no data, this will be empty

        } catch (SQLException se) {
            se.printStackTrace();
            return null;  //since we have to return something.
        }
    }

    void delete(Consignor consignor) {

        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD)) {

            String deleteSQLTemplate = "DELETE FROM %s WHERE %s = ? AND %s = ?";
            String deleteSQL = String.format(deleteSQLTemplate, TABLE_NAME, NAME_COL, RECORD_COL);
            System.out.println("The SQL for the prepared statement is " + deleteSQL);
            PreparedStatement deletePreparedStatement = conn.prepareStatement(deleteSQL);
            deletePreparedStatement.setString(1, consignor.name);
            deletePreparedStatement.setDouble(2, consignor.recordTime);
            //For debugging - displays the actual SQL created in the PreparedStatement after the data has been set
            System.out.println(deletePreparedStatement.toString());

            //Delete
            deletePreparedStatement.execute();

            //And close everything.
            deletePreparedStatement.close();;
            conn.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }


    void updateRecord(Consignor consignor) {

        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD)) {

            String sqlUpdate = "UPDATE consignors SET Solve_Time= ? WHERE Cube_Solver= ?";
            PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate);
            psUpdate.setString(2, consignor.name);
            psUpdate.setDouble(1, consignor.recordTime);

            psUpdate.executeUpdate();

            psUpdate.close();
            conn.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

}
