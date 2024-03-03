package devices;

public class EnvironmentalSensorDevice implements Device {
    private final StandardDevice standardDevice;
    private final FailingPolicy failingPolicy;
    private double airQuality;
    private double temperature;
    private double humidity;

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

    public void setAirQuality(double expectedAirQuality) {
        this.airQuality = expectedAirQuality;
    }

    public double getAirQuality() {
        return this.airQuality;
    }

    public void setTemperature(double expectedTemperature) {
        this.temperature = expectedTemperature;
    }

    public double getTemperature() {
        return this.temperature;
    }

    public void setHumidity(double expectedHumidity) {
        this.humidity = expectedHumidity;
    }

    public double getHumidity() {
        return this.humidity;
    }
}
