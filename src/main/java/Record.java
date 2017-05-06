import java.util.Date;

public class Record {

    int RID;
    int CID;
    String Artist;
    String Title;
    Double SalesPrice;
    Date DateAdded;
    int Status;
    String Notified;


    Record(int rid, int cid, String artist, String title, double salesPrice, Date dateAdded, int status, String notified) {

        RID = rid;
        CID = cid;
        Artist = artist;
        Title = title;
        SalesPrice = salesPrice;
        DateAdded = dateAdded;
        Status = status;
        Notified = notified;

    }

    @Override
    public String toString() { return "[RID: " + RID + "]" + " [CID: " + CID + "]" + " [Artist: " + Artist + "]" + " [Title: " + Title + "]" +
            " [Sales Price: " + SalesPrice + "]" + " [Date Added: " + DateAdded + "]" + " [Status: " + Status + "]" + " [Notified?: " + Notified + "]";
    }


}
