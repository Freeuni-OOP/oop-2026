public class UsbCToChargerAdapter implements Charger {
    private final UsbCCharger usbCCharger;

    public UsbCToChargerAdapter(UsbCCharger usbCCharger) {
        this.usbCCharger = usbCCharger;
    }

    @Override
    public void charge() {
        usbCCharger.plugInUsbC();
    }
}

