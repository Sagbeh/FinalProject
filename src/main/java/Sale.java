import java.util.Date;

public class Sale {

    int SaleID;
    int RID;
    Date SoldDate;
    double SoldPrice;


    Sale(int sid, int rid, Date soldDate, double soldPrice) {

        SaleID = sid;
        RID = rid;
        SoldDate = soldDate;
        SoldPrice = soldPrice;
    }

    @Override
    public String toString() { return "[Sale ID: " + SaleID + "]" + " [Record ID: " + RID + "]" + " [Sold Date: " + SoldDate + "]" + " [Sold Price: " + SoldPrice + "]";

    }
}
