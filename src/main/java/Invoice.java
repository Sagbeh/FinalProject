import java.util.Date;

public class Invoice {

    int SID;
    int CID;
    double CIDProfit;
    double StoreProfit;
    double AmountPaid;
    Date PaymentDate;
    double Balance;


    Invoice(int sid, int cid, double cidProfit, double storeProfit, double amountPaid, Date paymentDate, double balance) {

        SID = sid;
        CID = cid;
        CIDProfit = cidProfit;
        StoreProfit = storeProfit;
        AmountPaid = amountPaid;
        PaymentDate = paymentDate;
        Balance = balance;
    }

    @Override
    public String toString() { return "[Sale ID: " + SID + "]" + " [CID: " + CID + "]" + " [CID Profit: " + CIDProfit + "]" +
            " [Store Profit: " + StoreProfit + "]" + " [Amount Paid: " + AmountPaid + "]" + " [Payment Date: " + PaymentDate + "]" + " [Balance: " + Balance + "]";
    }

}
