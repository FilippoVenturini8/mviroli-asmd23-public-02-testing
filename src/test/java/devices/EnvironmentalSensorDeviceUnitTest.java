package devices;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EnvironmentalSensorDeviceUnitTest {

    private EnvironmentalSensorDevice sensorDevice;
    private StandardDevice standardDeviceMock;
    private FailingPolicy failingPolicyMock;

    @BeforeEach
    public void setUp() {
        // Create mock for StandardDevice with configured behavior to always turn on
        standardDeviceMock = mock(StandardDevice.class);

        // Create mock for FailingPolicy
        failingPolicyMock = mock(FailingPolicy.class);
        when(failingPolicyMock.attemptOn()).thenReturn(true); // Configure FailingPolicy to always succeed

        // Create instance of EnvironmentalSensorDevice with mock StandardDevice and FailingPolicy
        sensorDevice = new EnvironmentalSensorDevice(standardDeviceMock, failingPolicyMock);
    }

    @Test
    public void testOn() {
        // Call on method
        sensorDevice.on();

        when(standardDeviceMock.isOn()).thenReturn(true);

        // Verify that the device is turned on
        assertTrue(sensorDevice.isOn());
    }

    @Test
    public void testOff() {
        // Call on method
        sensorDevice.on();

        // Call off method
        sensorDevice.off();

        when(standardDeviceMock.isOn()).thenReturn(false);

        // Verify that the device is turned off
        assertFalse(sensorDevice.isOn());
    }

    @Test
    public void testReset() {
        // Call on method
        sensorDevice.on();

        // Call reset method
        sensorDevice.reset();

        // Verify that the device is turned off after reset
        assertFalse(sensorDevice.isOn());
    }

    @Test
    public void testAirQuality() {
        // Set air quality
        double expectedAirQuality = 80.0;
        sensorDevice.setAirQuality(expectedAirQuality);

        // Verify that the returned air quality matches the expected value
        assertEquals(expectedAirQuality, sensorDevice.getAirQuality(), 0.001);
    }

    @Test
    public void testTemperature() {
        // Set temperature
        double expectedTemperature = 25.0;
        sensorDevice.setTemperature(expectedTemperature);

        // Verify that the returned temperature matches the expected value
        assertEquals(expectedTemperature, sensorDevice.getTemperature(), 0.001);
    }

    @Test
    public void testHumidity() {
        // Set humidity
        double expectedHumidity = 50.0;
        sensorDevice.setHumidity(expectedHumidity);

        // Verify that the returned humidity matches the expected value
        assertEquals(expectedHumidity, sensorDevice.getHumidity(), 0.001);
    }
}
