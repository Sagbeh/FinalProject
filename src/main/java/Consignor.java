public class Consignor {

    int CID;
    String firstName;
    String lastName;
    int phoneNumber;

    Consignor(int cid, String fName, String lName, int phone) {

        CID = cid;
        firstName = fName;
        lastName = lName;
        phoneNumber = phone;

    }

    @Override
    public String toString() { return "[CID: " + CID + "]" + " [First Name: " + firstName + "]" + " [Last Name: " + lastName + "]" + " [Phone Number: " + phoneNumber + "]";
    }
}
