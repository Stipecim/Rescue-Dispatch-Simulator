Rescue Dispatch Simulator

-robust console-based application that manages emergency incidents.Task
 asesses:
    - program design
    - collections
    - stack behaviours
    - exception handling
    - automated testing
    - clear communication

build:
    - core Simulator; no graphical interface, database, networking, or file
      storage is required.
    - Begins with a small set of sample incidents and repeatedly displays
      a menu until user chooses to exit. All data exists onlu for the
      current run of the program.

rules:
    - write, compile, run, and test multi-class Java application.
    - stated problem into program design.
    - objects, methods, collections, and a stack for appropriate
      responsibilities.
    - validate input and recover safely from expected errors using
      exceptions.
    - apply readable coding conventions and explain implementation
      decisions.


Core domain rules:

Field                   Rules
    IndicentID              A unique positive integer.
    Location                A non-blank line of text.
    Type                    MEDICAL, FIRE, TRAFFIC, or OTHER.
    Severity                An integer from 1 (lowest) to 5 (highest).
    Status                  WAITING or DISPATCHED.
    Dispatch order          Highest severity first; if tied, the lowest
                            incident ID first.

Required functionality:
    - View incidents
    - add an incident
    - Dispatch next incident
    - Undo last dispatch
    - Search by ID
    - Show session summary
    - exit

Class structure:
    - incident
    - DispatchCentre
    - ConsoleApp
    - Dispatch centre test

Collections and stack rquirement:
    - Java collection to store waiting incidents.
    - genuine LIFO stack abstraction for dispatch history

Error handling and recovery:

