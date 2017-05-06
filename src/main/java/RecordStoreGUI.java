import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by samagbeh on 5/3/17.
 */
public class RecordStoreGUI extends JPanel {

    private JList<Consignor> allConsignorsList;
    private DefaultListModel<Consignor> allConsignorsModel;



    private Controller controller;


    RecordStoreGUI(Controller controller) {

        this.controller = controller;  //Store a reference to the controller object.
        // Need this to make requests to the database.

        allConsignorsModel = new DefaultListModel<Consignor>();
        allConsignorsList.setModel(allConsignorsModel);

    }

    void setConsignorListData(ArrayList<Consignor> data) {

        //Display data in allDataTextArea
        allConsignorsModel.clear();

        for (Consignor consignor : data) {
            allConsignorsModel.addElement(consignor);
        }

    }

}
