# Lab 02

## Task 1: Reorganize

Experimented `Mockito` features by implementing `Unit Testing` of the `StandardDevice` class with a TDD approach.

Reorganized the tests already implemented and written new ones, using all the types of **Test Doubles** available such as `Dummy`, `Stub`, `Mock`, and `Spy`.

All the tests implemented can be found in the following file: *"reorganized/ReorganizedDeviceTests.java"*.

## Task 2: Testing-LLM

Used `ChatGPT` to write, implement, and improve integration and unit tests.

The aim is to implement an `EnvironmentalSensorsDevice` that depends on both `StandardDevice` and `FailingPolicy`. This device uses the `StandardDevice` and the `FailingPolicy` for the basic behavior (turn on, turn off, and reset) and adds some additional methods for setting and getting data such as temperature, humidity, and air quality.

The goal is to try to write some integration tests on the dependent behaviors and some unit tests on the new features added by this device using `ChatGPT` to generate the tests and then a `TDD` approach to implement the device.

### Integration Tests

With this first prompt, `ChatGPT` generated the integration tests that can be found in the file: `test/devices/EnvironmentalSensorDeviceIntegrationTest.java`

> "Write the implementation of some JUnit integration tests in Java using the Mockito framework. I want to test the EnvironmentalSensorDevice by creating a mock of the StandartDevice and the FailingPolicy, and test all the behaviors that include interaction and dependency between the EnvironmentalSensorDevice and the mocks. You can use different methods and techniques such as dummy, stubs, mock, and spy."

The integration tests generated regard the features of turn on (with success or not), turn off, and reset since they depend on the other components.

But the tests contained the following **errors**:

1. In `testDeviceTurnOnFailure()`, the FailingPolicy mock was configured correctly, but the exception thrown by the device was not handled.
2. In `testDeviceTurnOff()`, the FailingPolicy mock was not correctly configured to make the device able to turn on.
3. In `testDeviceTurnOnSuccess()`, the StandardDevice mock was not correctly configured to make the standard device result on when turned on.

The first two problems were simply solved after pointing it out to `ChatGPT`.

But to solve the third problem, `ChatGPT` proposed to use the method `doCallRealMethod()` that calls the real method of the implementation of `StandardDevice`, which contradicts the idea of mock and leads to problems in case of a missing implementation of the `StandardDevice`.

### Unit Tests

With the following prompt, `ChatGPT` generated the integration tests that can be found in the file: `test/devices/EnvironmentalSensorDeviceUnitTest.java`

> "Now write the unit tests for the EnvironmentalSensorDevice to test the additional methods that the device has in addition."

These tests were really simple and complete, but the code was not clean and a bit repetitive, so it was necessary to write an additional prompt to make the tests more readable and clean:

> "It's better to create just one time the sensor device for all the tests, and also the device wants a failing policy and a standard device (you can create a mock for it and configure it in a way that always turns on in a beforeEach)."

### Conclusions

`ChatGPT` is not really useful for writing integration tests since they are a bit more complicated than the unit tests, and it also introduces errors in the implementation of the tests that, if not spotted, could lead to problems. For writing unit tests, `ChatGPT` is more efficient; it produces correct code but a bit opaque and repetitive, but this can be solved easily with another prompt.
