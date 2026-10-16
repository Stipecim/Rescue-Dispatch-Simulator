# Rescue Dispatch Simulator

- robust console-based application that manages emergency incidents.

Task assesses:

- program design
- collections
- stack behaviours
- exception handling
- automated testing
- clear communication

## Build

- core Simulator; no graphical interface, database, networking, or file storage is required.
- Begins with a small set of sample incidents and repeatedly displays a menu until user chooses to exit. All data exists onlu for the current run of the program.

## Rules

- write, compile, run, and test multi-class Java application.
- stated problem into program design.
- objects, methods, collections, and a stack for appropriate responsibilities.
- validate input and recover safely from expected errors using exceptions.
- apply readable coding conventions and explain implementation decisions.

## Core Domain Rules

| Field | Rules |
|---|---|
| IndicentID | A unique positive integer. |
| Location | A non-blank line of text. |
| Type | MEDICAL, FIRE, TRAFFIC, or OTHER. |
| Severity | An integer from 1 (lowest) to 5 (highest). |
| Status | WAITING or DISPATCHED. |
| Dispatch order | Highest severity first; if tied, the lowest incident ID first. |

## Required Functionality

- View incidents
- add an incident
- Dispatch next incident
- Undo last dispatch
- Search by ID
- Show session summary
- exit

## Class Structure

- incident
- DispatchCentre
- ConsoleApp
- Dispatch centre test

## Collections and Stack Requirement

- Java collection to store waiting incidents.
- genuine LIFO stack abstraction for dispatch history

## Error Handling and Recovery

- Must not terminate because invalid input. Explain appropriate usage.

| Situation | Required response |
|---|---|
| NonNon-numeric menu option, ID, or severity | Catch the relevant input/conversion exception and ask again. |
| Severity outside 1-5 | Reject with a meaningful message and ask again. |
| Blank location or unsupported type | Reject with a meaningful message and ask again. |
| Duplicate incident ID | Reject without changing existing data. |
| Dispatch with no waiting incidents | Report that no dispatch is possible; continue running.|
| Undo with empty history | Report that there is nothing to undo; continue running.|

## JUnit Testing

- automated tests for DispatchCentre and others where useful.

### independent tests for:
- adding a valid incident;
- rejecting a duplicate ID;
- dispatching the highest-severity incidentl;
- applying the ID tie-break rule;
- dispatch or undo restoring the most recent dispatch;
- at least one invalid field or state transition;

## Technical boundries

| Required | Not required / out of scope |
|---|---|
| Java console application | Swing or any other GUI |
| Multiple purposeful classes | Database, web service, or networking |
| Java collections and LIFO stack | Reading from or writing to files |
| JUnit tests | Third-party libraries other than JUnit |
| Runs in the specified VS Code environment | IDE-specific project dependencies |

## Sample interaction
```
    Waiting incidents
    -----------------
    104 | Union Street | FIRE    | severity 5
    108 | King Street  | MEDICAL | severity 4

    Select: 3
    Dispatched incident 104 to Union Street.

    Select: 4
    Dispatch undone. Incident 104 is waiting again.
```
