package testingReachability;

public class Addresses {
   private String address;

    public Addresses(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public static Addresses introduceAddress(String address) {
        return new Addresses(address);
    }
}
