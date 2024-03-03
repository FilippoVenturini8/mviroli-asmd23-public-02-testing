package devices;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

public class EnvironmentalSensorDeviceIntegrationTest {

    @Test
    public void testDeviceTurnOnSuccess() {
        // Create mocks for StandardDevice and FailingPolicy
        StandardDevice standardDeviceMock = mock(StandardDevice.class);
        FailingPolicy failingPolicyMock = mock(FailingPolicy.class);

        // Create instance of EnvironmentalSensorDevice with mocks
        EnvironmentalSensorDevice sensorDevice = new EnvironmentalSensorDevice(standardDeviceMock, failingPolicyMock);

        // Stubbing behavior for FailingPolicy
        when(failingPolicyMock.attemptOn()).thenReturn(true);

        // Stubbing behavior for StandardDevice's isOn() method to return true
        when(standardDeviceMock.isOn()).thenReturn(true);

        // Call the method to be tested
        sensorDevice.on();

        // Verify that StandardDevice's on() method is called and the device is turned on
        verify(standardDeviceMock).on();
        assertTrue(sensorDevice.isOn());
    }



    @Test
    public void testDeviceTurnOnFailure() {
        // Create mocks for StandardDevice and FailingPolicy
        StandardDevice standardDeviceMock = mock(StandardDevice.class);
        FailingPolicy failingPolicyMock = mock(FailingPolicy.class);

        // Create instance of EnvironmentalSensorDevice with mocks
        EnvironmentalSensorDevice sensorDevice = new EnvironmentalSensorDevice(standardDeviceMock, failingPolicyMock);

        // Stubbing behavior for FailingPolicy
        when(failingPolicyMock.attemptOn()).thenReturn(false);

        // Call the method to be tested
        try {
            sensorDevice.on();
            fail("Expected IllegalStateException was not thrown");
        } catch (IllegalStateException e) {
            // Verify that StandardDevice's on() method is not called
            verify(standardDeviceMock, never()).on();
            assertFalse(sensorDevice.isOn());
        }
    }

    @Test
    public void testDeviceTurnOff() {
        // Create mocks for StandardDevice and FailingPolicy
        StandardDevice standardDeviceMock = mock(StandardDevice.class);
        FailingPolicy failingPolicyMock = mock(FailingPolicy.class);

        // Create instance of EnvironmentalSensorDevice with mocks
        EnvironmentalSensorDevice sensorDevice = new EnvironmentalSensorDevice(standardDeviceMock, failingPolicyMock);

        // Stubbing behavior for FailingPolicy to ensure turn on attempt succeeds
        when(failingPolicyMock.attemptOn()).thenReturn(true);

        // Stubbing behavior for StandardDevice's isOn() method to return true
        when(standardDeviceMock.isOn()).thenReturn(true);

        // Call the method to be tested
        sensorDevice.on();

        // Verify that StandardDevice's on() method is called
        verify(standardDeviceMock).on();
        assertTrue(sensorDevice.isOn());

        // Reset the mocks
        reset(standardDeviceMock);

        // Call the method to be tested
        sensorDevice.off();

        // Verify that StandardDevice's off() method is called
        verify(standardDeviceMock).off();
        assertFalse(sensorDevice.isOn());
    }


    @Test
    public void testDeviceReset() {
        // Create mocks for StandardDevice and FailingPolicy
        StandardDevice standardDeviceMock = mock(StandardDevice.class);
        FailingPolicy failingPolicyMock = mock(FailingPolicy.class);

        // Create instance of EnvironmentalSensorDevice with mocks
        EnvironmentalSensorDevice sensorDevice = new EnvironmentalSensorDevice(standardDeviceMock, failingPolicyMock);

        // Call the method to be tested
        sensorDevice.reset();

        // Verify that StandardDevice's off() method is called and failingPolicy's reset() method is called
        verify(standardDeviceMock).off();
        verify(failingPolicyMock).reset();
        assertFalse(sensorDevice.isOn());
    }
}
