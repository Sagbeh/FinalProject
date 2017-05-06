import sun.reflect.generics.repository.ConstructorRepository;

import java.sql.*;
import java.sql.Date;
import java.util.*;

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
    private static final String SID_COL = "sid";
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
            String createConsignorsTableSQLTemplate = "CREATE TABLE IF NOT EXISTS %s (%s int not NULL AUTO_INCREMENT, %s VARCHAR (25), %s VARCHAR (25), %s VARCHAR (25), PRIMARY KEY (%s))";
            String createConsignorsTableSQL = String.format(createConsignorsTableSQLTemplate, C_TABLE, CID_COL, FIRSTNAME_COL, LASTNAME_COL, PHONE_COL, CID_COL);

            String createRecordsTableSQLTemplate = "CREATE TABLE IF NOT EXISTS %s (%s int not NULL AUTO_INCREMENT, %s int, %s VARCHAR (50), %s VARCHAR (50), %s decimal (10,2), %s date, %s int, %s VARCHAR (1), PRIMARY KEY (%s))";
            String createRecordsTableSQL = String.format(createRecordsTableSQLTemplate, R_TABLE, RID_COL, CID_COL, ARTIST_COL, TITLE_COL, SALESPRICE_COL, DATEADDED_COL, STATUS_COL, NOTIFIED_COL, RID_COL);

            String createSalesTableSQLTemplate = "CREATE TABLE IF NOT EXISTS %s (%s int not NULL AUTO_INCREMENT, %s int, %s decimal (10,2), %s decimal (10,2), PRIMARY KEY (%s))";
            String createSalesTableSQL = String.format(createSalesTableSQLTemplate, S_TABLE, SID_COL, RID_COL, SOLDDATE_COL, SOLDPRICE_COL, SID_COL);

            String createInvoicesTableSQLTemplate = "CREATE TABLE IF NOT EXISTS %s (%s int, %s int, %s decimal (10,2), %s decimal (10,2), %s decimal (10,2), %s date, %s decimal (10,2))";
            String createInvoicesTableSQL = String.format(createInvoicesTableSQLTemplate, I_TABLE, SID_COL, CID_COL, CIDPROFIT_COL, STOREPROFIT_COL, AMOUNTPAID_COL, PAYMENTDATE_COL, BALANCE_COL);



            statement.executeUpdate(createConsignorsTableSQL);
            System.out.println("Created consignors table");
            statement.executeUpdate(createRecordsTableSQL);
            System.out.println("Created records table");
            statement.executeUpdate(createSalesTableSQL);
            System.out.println("Created sales table");
            statement.executeUpdate(createInvoicesTableSQL);
            System.out.println("Created invoices table");

            statement.close();
            conn.close();

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }


    void addConsignor(Consignor consignor)  {

        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD)) {

            String addConsignorSQL = "INSERT INTO " + C_TABLE + " VALUES (?, ?, ?) ";
            PreparedStatement addConsignorPS = conn.prepareStatement(addConsignorSQL);

            addConsignorPS.setString(1, consignor.firstName);
            addConsignorPS.setString(2, consignor.lastName);
            addConsignorPS.setString(3, consignor.phoneNumber);

            addConsignorPS.executeUpdate();

            System.out.println("Added Consignor object for " + consignor);

            addConsignorPS.close();
            conn.close();

        } catch (SQLException se) {
            se.printStackTrace();
        }

    }

    void addRecord(Record record)  {

        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD)) {

            String addRecordSQL = "INSERT INTO " + R_TABLE + " VALUES (?, ?, ?, ?, ?, ?, ?) ";
            PreparedStatement addRecordPS = conn.prepareStatement(addRecordSQL);
            addRecordPS.setInt(1, record.CID);
            addRecordPS.setString(2, record.Artist);
            addRecordPS.setString(3, record.Title);
            addRecordPS.setDouble(4, record.SalesPrice);
            addRecordPS.setDate(5, (Date) record.DateAdded);
            addRecordPS.setDouble(6, record.Status);
            addRecordPS.setString(7, record.Notified);


            addRecordPS.executeUpdate();

            System.out.println("Added Record object for " + record);

            addRecordPS.close();
            conn.close();

        } catch (SQLException se) {
            se.printStackTrace();
        }

    }

    void addSale(Sale sale)  {

        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD)) {

            String addSaleSQL = "INSERT INTO " + S_TABLE + " VALUES (?, ?, ?) ";
            PreparedStatement addSalePS = conn.prepareStatement(addSaleSQL);
            addSalePS.setInt(1, sale.RID);
            addSalePS.setDate(2, (Date) sale.SoldDate);
            addSalePS.setDouble(3, sale.SoldPrice);
            

            addSalePS.executeUpdate();

            System.out.println("Added Sale object for " + sale);

            addSalePS.close();
            conn.close();

        } catch (SQLException se) {
            se.printStackTrace();
        }

    }

    void addInvoice(Invoice invoice)  {

        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD)) {

            String addInvoiceSQL = "INSERT INTO " + I_TABLE + " VALUES (?, ?, ?, ?, ?, ?, ?) ";
            PreparedStatement addInvoicePS = conn.prepareStatement(addInvoiceSQL);
            addInvoicePS.setInt(1, invoice.SID);
            addInvoicePS.setInt(2, invoice.CID);
            addInvoicePS.setDouble(3, invoice.CIDProfit);
            addInvoicePS.setDouble(4, invoice.StoreProfit);
            addInvoicePS.setDouble(5, invoice.AmountPaid);
            addInvoicePS.setDate(6, (Date) invoice.PaymentDate);
            addInvoicePS.setDouble(7, invoice.Balance);

            addInvoicePS.executeUpdate();

            System.out.println("Added Invoice object for " + invoice);

            addInvoicePS.close();
            conn.close();

        } catch (SQLException se) {
            se.printStackTrace();
        }

    }

    ArrayList<Consignor> fetchAllConsignors() {

        ArrayList<Consignor> allConsignors = new ArrayList();

        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);
             Statement statement = conn.createStatement()) {

            String selectAllConsignorsSQL = "SELECT * FROM " + C_TABLE;
            ResultSet rsConsignors = statement.executeQuery(selectAllConsignorsSQL);

            while (rsConsignors.next()) {
                int cid = rsConsignors.getInt(CID_COL);
                String firstName = rsConsignors.getString(FIRSTNAME_COL);
                String lastName = rsConsignors.getString(LASTNAME_COL);
                String phoneNumber = rsConsignors.getString(PHONE_COL);
                Consignor consignor = new Consignor(cid, firstName, lastName, phoneNumber);
                allConsignors.add(consignor);
            }

            rsConsignors.close();
            statement.close();
            conn.close();

            return allConsignors;    //If there's no data, this will be empty

        } catch (SQLException se) {
            se.printStackTrace();
            return null;  //since we have to return something.
        }
    }

    ArrayList<Record> fetchAllRecords() {

        ArrayList<Record> allRecords = new ArrayList();

        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);
             Statement statement = conn.createStatement()) {

            String selectAllRecordsSQL = "SELECT * FROM " + R_TABLE;
            ResultSet rsRecords = statement.executeQuery(selectAllRecordsSQL);

            while (rsRecords.next()) {
                int rid = rsRecords.getInt(RID_COL);
                int cid = rsRecords.getInt(CID_COL);
                String artist = rsRecords.getString(ARTIST_COL);
                String title = rsRecords.getString(TITLE_COL);
                Double salesPrice = rsRecords.getDouble(SALESPRICE_COL);
                Date dateAdded = rsRecords.getDate(DATEADDED_COL);
                int status = rsRecords.getInt(STATUS_COL);
                String notified = rsRecords.getString(NOTIFIED_COL);
                
                Record record = new Record(rid, cid, artist, title, salesPrice, dateAdded, status, notified);
                allRecords.add(record);
            }

            rsRecords.close();
            statement.close();
            conn.close();

            return allRecords;    //If there's no data, this will be empty

        } catch (SQLException se) {
            se.printStackTrace();
            return null;  //since we have to return something.
        }
    }

    ArrayList<Sale> fetchAllSales() {

        ArrayList<Sale> allSales = new ArrayList();

        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);
             Statement statement = conn.createStatement()) {

            String selectAllSalesSQL = "SELECT * FROM " + S_TABLE;
            ResultSet rsSales = statement.executeQuery(selectAllSalesSQL);

            while (rsSales.next()) {
                int sid = rsSales.getInt(SID_COL);
                int cid = rsSales.getInt(CID_COL);
                Date soldDate = rsSales.getDate(SOLDDATE_COL);
                Double soldPrice = rsSales.getDouble(SOLDPRICE_COL);
                Sale sale = new Sale(sid, cid, soldDate, soldPrice);
                allSales.add(sale);
            }

            rsSales.close();
            statement.close();
            conn.close();

            return allSales;    //If there's no data, this will be empty

        } catch (SQLException se) {
            se.printStackTrace();
            return null;  //since we have to return something.
        }
    }

    ArrayList<Invoice> fetchAllInvoices() {

        ArrayList<Invoice> allInvoices = new ArrayList();

        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);
             Statement statement = conn.createStatement()) {

            String selectAllInvoicesSQL = "SELECT * FROM " + I_TABLE;
            ResultSet rsInvoices = statement.executeQuery(selectAllInvoicesSQL);

            while (rsInvoices.next()) {
                int sid = rsInvoices.getInt(SID_COL);
                int cid = rsInvoices.getInt(CID_COL);
                Double cidProfit = rsInvoices.getDouble(CIDPROFIT_COL);
                Double storeProfit = rsInvoices.getDouble(STOREPROFIT_COL);
                Double amountPaid = rsInvoices.getDouble(AMOUNTPAID_COL);
                Date paymentDate = rsInvoices.getDate(PAYMENTDATE_COL);
                Double balance = rsInvoices.getDouble(BALANCE_COL);
                Invoice invoice = new Invoice(sid, cid, cidProfit, storeProfit, amountPaid, paymentDate, balance);
                allInvoices.add(invoice);
            }

            rsInvoices.close();
            statement.close();
            conn.close();

            return allInvoices;    //If there's no data, this will be empty

        } catch (SQLException se) {
            se.printStackTrace();
            return null;  //since we have to return something.
        }
    }

    void deleteRecord(Record record) {

        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD)) {

            String deleteRecordSQLTemplate = "DELETE FROM %s WHERE %s = ?";
            String deleteRecordSQL = String.format(deleteRecordSQLTemplate, R_TABLE, RID_COL);
            System.out.println("The SQL for the prepared statement is " + deleteRecordSQL);
            PreparedStatement deleteRecordPreparedStatement = conn.prepareStatement(deleteRecordSQL);
            deleteRecordPreparedStatement.setInt(1, record.RID);
            //For debugging - displays the actual SQL created in the PreparedStatement after the data has been set
            System.out.println(deleteRecordPreparedStatement.toString());

            //Delete
            deleteRecordPreparedStatement.executeUpdate();

            //And close everything.
            deleteRecordPreparedStatement.close();;
            conn.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }


    void updateRecord(Record record) {

        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD)) {

            String sqlUpdate = "UPDATE records SET Notified= ? WHERE RID= ?";
            PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate);
            psUpdate.setString(1, record.Notified);
            psUpdate.setDouble(2, record.RID);

            psUpdate.executeUpdate();

            psUpdate.close();
            conn.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

}
