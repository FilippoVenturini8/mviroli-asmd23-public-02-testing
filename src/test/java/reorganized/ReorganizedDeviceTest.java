package reorganized;

import devices.Device;
import devices.FailingPolicy;
import devices.RandomFailing;
import devices.StandardDevice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReorganizedDeviceTest {
    private FailingPolicy failingPolicy;
    private Device device;

    @BeforeEach
    void init() {
        this.failingPolicy = mock(FailingPolicy.class);
        this.device = new StandardDevice(this.failingPolicy);
    }

    @Test
    @DisplayName("Device is created correctly")
    void testCreateDevice(){
        assertNotNull(this.device);
    }

    @Test
    @DisplayName("Device is initially off")
    void testInitiallyOff(){
        assertFalse(this.device.isOn());
        verifyNoInteractions(this.failingPolicy);
    }

    @Test
    @DisplayName("Device can be switched on")
    void testCanBeSwitchedOn(){
        when(this.failingPolicy.attemptOn()).thenReturn(true);
        device.on();
        assertTrue(device.isOn());
        verify(this.failingPolicy).attemptOn();
    }

    @Test
    @DisplayName("Device won't switch on if failing")
    void testWontSwitchOn() {
        when(this.failingPolicy.attemptOn()).thenReturn(false);
        assertThrows(IllegalStateException.class, () -> device.on());
        verify(this.failingPolicy).attemptOn();
    }

    @Test
    @DisplayName("Device can be switched off")
    void testSwitchOff(){
        when(this.failingPolicy.attemptOn()).thenReturn(true);
        this.device.on();
        assertTrue(this.device.isOn());
        this.device.off();
        assertFalse(this.device.isOn());
    }

    @Test
    @DisplayName("The reset works correctly")
    void testReset() {
        when(this.failingPolicy.attemptOn()).thenReturn(true);
        device.on();
        device.reset();
        assertEquals(2, Mockito.mockingDetails(this.failingPolicy).getInvocations().size());
    }

    @Test
    @DisplayName("Device switch on and off until failing")
    void testSwitchesOnAndOff() {
        when(this.failingPolicy.attemptOn()).thenReturn(true, true, false);
        IntStream.range(0, 2).forEach(i -> {
            device.on();
            assertTrue(device.isOn());
            device.off();
            assertFalse(device.isOn());
        });
        assertThrows(IllegalStateException.class, () -> device.on());
    }
}
