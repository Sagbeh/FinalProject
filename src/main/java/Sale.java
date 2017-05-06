import java.util.Date;

public class Sale {

    int SID;
    int RID;
    Date SoldDate;
    double SoldPrice;


    Sale(int sid, int rid, Date soldDate, double soldPrice) {

        SID = sid;
        RID = rid;
        SoldDate = soldDate;
        SoldPrice = soldPrice;
    }

    @Override
    public String toString() { return "[Sale ID: " + SID + "]" + " [Record ID: " + RID + "]" + " [Sold Date: " + SoldDate + "]" + " [Sold Price: " + SoldPrice + "]";

    }
}
