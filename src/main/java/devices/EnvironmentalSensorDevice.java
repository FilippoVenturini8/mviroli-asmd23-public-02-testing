package devices;

public class EnvironmentalSensorDevice implements Device {
    private final StandardDevice standardDevice;
    private final FailingPolicy failingPolicy;

    public EnvironmentalSensorDevice(StandardDevice standardDevice, FailingPolicy failingPolicy) {
        this.standardDevice = standardDevice;
        this.failingPolicy = failingPolicy;
    }

    @Override
    public void on() throws IllegalStateException {
        if (!failingPolicy.attemptOn()) {
            throw new IllegalStateException("Failed to turn on due to failing policy");
        }
        standardDevice.on();
    }

    @Override
    public void off() {
        standardDevice.off();
    }

    @Override
    public boolean isOn() {
        return standardDevice.isOn();
    }

    @Override
    public void reset() {
        standardDevice.off();
        failingPolicy.reset();
    }
}
