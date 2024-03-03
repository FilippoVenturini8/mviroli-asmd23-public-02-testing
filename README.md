# Lab 02

## Task 1 : Reorganise

Experimented `Mockito` features by implementing `Unit Testing` of the `StandardDevice` class with a TDD approach.

Reorganised the tests already implemented and written now ones, using all the types of **Test Doubles** available such as `Dummy`, `Stub`, `Mock` and `Spy`.

All the tests implemented can be found in the following file: *"reorganized/ReorganizedDeviceTests.java"*.

## Task 2 : Testing-LLM

Used `ChatGPT` for write, implement and improve integration and unit tests.

The aim is to implement an `EnvironmentalSensorsDevice` that depend on both `StandardDevice` and `FailingPolicy`. This device uses the `StandardDevice` and the `FailingPolicy` for the basic behaviour (turn on, turn off and reset) and add some additional methods for set and get some data such as temperature, humidity and air quality.

The goal is to try to write some integration tests on the dependent behaviours and some unit tests on the new features added by this device using `ChatGPT` for generate the tests and then a `TDD` approach for implement the device. 

### Integration Tests

With this first prompt, `ChatGPT` generated the integration tests that can be found in the file: `test/devices/EnvironmentalSensorDeviceIntegrationTest.java`

> "Write the implementation of some JUnit integration tests in Java using the Mockito framework. I want to test the EnvironmentalSensorDevice by creating mock of the StandartDevice and the FailingPolicy, and test all the behaviours that includes interaction and dependency between the EnvironmentalSensorDevice and the mocks. You can use different methods and technique such as dummy, stubs, mock and spy."
