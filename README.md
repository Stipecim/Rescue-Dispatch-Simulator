# Rescue Dispatch Simulator

Simple Java console application for managing emergency incidents.

## Compile

From the project root:

```bash
mkdir -p build/classes
javac -d build/classes src/main/java/*.java
```

## Run

```bash
java -cp build/classes ConsoleApp
```

## Run with sample data

```bash
java -cp build/classes ConsoleApp -sample
```

## Run tests

Compile the tests:

```bash
mkdir -p build/test-classes

javac \
    -cp "build/classes:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar" \
    -d build/test-classes \
    src/test/java/*.java
```

Run the tests:

```bash
java \
    -cp "build/classes:build/test-classes:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar" \
    org.junit.runner.JUnitCore DispatchCentreTest
```

## Using Ant

Clean:
```bash
ant clean
```

Compile:

```bash
ant compile
```

Run:

```bash
ant run
```

Run with sample data:

```bash
ant run-with-sample
```

Run tests:

```bash
ant test
```
## Requirements

### Fully implemented

- Adding a new incident.
- Viewing waiting and dispatched incidents.
- Dispatching the next incident based on severity.
- Undoing the most recent dispatch using LIFO behaviour.
- Searching for an incident by ID.
- Preventing duplicate incident IDs.
- Displaying a session summary.
- Incident validation and exception handling.
- Automated JUnit tests.
- Optional sample incident data.

### Partly implemented

- Additional automated tests can be added for edge cases and invalid input.

### Unimplemented

- No additional core requirements are currently known to be unimplemented.

## Class Design

The program is divided into several classes so that each class has a clear responsibility.

`Incident` represents an single emergency incident. It stores information such as the incident ID, location, incident type, severity and status.

`DispatchCentre` manages incidents. It stores waiting incidents in an `ArrayList` and stores dispatched incidents in a `Deque`. A `Deque` is used for the dispatch history because undoing a dispatch requires Last-In-First-Out (LIFO) behaviour.

`ConsoleApp` is responsible for interaction with the user, it displays the menu, reads user input and calls the appropriate methods in `DispatchCentre`, and catches exceptions throw by dispatch centre and incident classes. 

`IncidentType` and `IncidentStatus` are enums. They restrict incident types and statuses to a fixed set of valid values.

`DispatchException` is a custom exception used when invalid operations or invalid incident data are detected.

`DispatchCentreTest` contains JUnit tests for the behaviour of the dispatch centre.