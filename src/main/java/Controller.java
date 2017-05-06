import java.util.ArrayList;


public class Controller {
    static RecordStoreGUI gui;
    static DB db;

    public static void main(String[] args) {

        Controller controller = new Controller();
        controller.startApp();

    }

    private void startApp() {

        db = new DB();
        db.createTable();
        ArrayList<Consignor> consignors = db.fetchAllConsignors();
        ArrayList<Record> records = db.fetchAllRecords();
        ArrayList<Sale> sales = db.fetchAllSales();
        gui = new RecordStoreGUI(this);
        gui.setConsignorListData(consignors);
    }


    ArrayList getAllConsignors() {
        return db.fetchAllConsignors();
    }

    ArrayList getAllRecords() {
        return db.fetchAllRecords();
    }

    ArrayList getAllSales() {
        return db.fetchAllSales();
    }

    ArrayList getAllInvoices() {
        return db.fetchAllInvoices();
    }

    void addConsignorToDatabase(Consignor consignor) {db.addConsignor(consignor);
    }

    void addRecordToDatabase(Record record) {db.addRecord(record);
    }

    void addSaleToDatabase(Sale sale) {db.addSale(sale);
    }

    void addInvoiceToDatabase(Invoice invoice) {db.addInvoice(invoice);
    }

    void delete(Record record) {
        db.deleteRecord(record);
    }

    void updateRecordInDatabase(Record record) { db.updateRecord(record);}
}
