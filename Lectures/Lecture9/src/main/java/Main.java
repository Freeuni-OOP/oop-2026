public class Main {
    public static void main(String[] args) {
        Charger charger = new UsbCToChargerAdapter(new UsbCCharger());
        charger.charge();
    }
}
