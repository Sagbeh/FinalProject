import java.util.ArrayList;


public class Controller {
    static GUI gui;
    static DB db;

    public static void main(String[] args) {

        Controller controller = new Controller();
        controller.startApp();

    }

    private void startApp() {

        db = new DB();
        db.createTable();
        ArrayList<Consignor> consignors = db.fetchAllRecords();
        ArrayList<Record> records = db.fetchAllRecords();
        ArrayList<Sale> sales = db.fetchAllRecords();
        gui = new GUI(this);
        gui.setListData();
    }


    ArrayList getAllData() {
        return db.fetchAllRecords();
    }

    void addRecordToDatabase(Consignor consignor) {
        db.addRecord(consignor);
    }

    void delete(Consignor consignor) {
        db.delete(consignor);
    }

    void updateRecordInDatabase(Consignor consignor) { db.updateRecord(consignor);}
}
