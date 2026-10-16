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