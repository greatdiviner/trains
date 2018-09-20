# Design of trains problem

## Background

Please refer to [README.md](./README.md) to get the basic idea of the trains problem.

## Development enviroment

* Java 1.8
* Maven 3.5.4

## How-to instuctions

The most straight-forward way to run the application or test cases is open the project in an IDE(eclipse for example).  
The following instructions are how to do these jobs in a console.

1. How to run the application?

    In the base directory of the project,  
    >mvn clean compile  
    >mvn exec:java -Dexec.mainClass="com.thoughtworks.trains.Trains"

2. How to run the application with a self-defined input file?

    In the base directory of the project,  
    >mvn clean package  

    Go to the target directory where the jar is generated,  
    >java -cp trains-0.0.1-SNAPSHOT.jar  com.thoughtworks.trains.Trains "path-to-self-defined-input.txt"

3. How to run the test cases?

    In the base directory of the project,
    >mvn clean compile  
    >mvn test

4. How to input?

    If no argument is passed to the application, the default 'input.txt' in the resource will be used as the input.  
    If a file path is passed to the application as an argument, then application will retrieve the input from the self defined file.

    Each line in the file will be interpreted as a 'command'. There will be 4 types of valid commands:  
    4.1. Graph command, used to update the graph. An example of graph command format:  
    Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7

    4.2. Distance command, used to calculate the distance for a specific trip. An example of distance command format:  
    Distance: A-B-C

    4.3. Trips command, used to calculate the number of trips between 2 towns in the given graph under some constraints. Examples of trips command format:  
    Trips: MAX_STOPS,3,C-C
    Trips: EXACT_STOPS,4,A-C
    Trips: MAX_DISTANCE,30,C-C

    4.4. Shortest command, used to caculate the shortest distance between 2 towns. An example of shortest command format:  
    Shortest: A-C
    Shortest: B-B

    **NOTE:** If a line doesn't match the format, it will be ignored.

5. How to output?

    The output will go to console(System.out).

## General design

At the start of the application, FileUtil will read all lines from the input file.  
A line matching one of the format patterns will be considered as a valid command line.  
For each command line, the application will first translate it into a Command.  
After that, the Command will be executed by CommandExecutor.  
The result will be output to console(System.out).

### Translate from comand line to Command

There're 4 different formats of command lines. Each format will be translated to a different Command.(GraphCommand, DistanceCommand, TripsCommand, ShortestCommand)  
CommandFactory is the **simple factory class** to complete the translation.

### Execute the Command

After the translation, we need to execute the Command and output the result.  
CommandExecutor is the **facade class** to complete the execution. It hides all the complexities of the internal calculations.

## Data Models

1. Town
   A Town is a node in a directed graph.

2. Edge
   An Edge is a combination of a startTown and an endTown. Note that the 2 Towns may or may not be directly connected.

3. Trip
   A Trip is a combination of multiple consecutively connected Towns with the direction from the first to the last.

4. Direction
   A Direction is a combination of an endTown and the distance to the endTown.

5. Graph
   A Graph is a directed graph. It has 2 views - a Town-centered view and an Edge-centered view.
   A Town-centered view considers the graph as a collection of startTowns with all possible Directions for each startTown.
   An Edge-centered view considers the graph as a collection of direct Edges with respective distance for each Edge.

## Detailed solutions for each Command type

1. GraphCommand:

    When GraphCommand is executed, a Graph is passed to CommandExecutor as the argument.  CommandExecutor will update its internal Graph with it. It will use the Graph during the execution the of subsequent Commands.

2. DistanceCommand:

   When DistanceCommand is executed, a Trip is passed to CommandExecutor as the argument.  CommandExecutor will split the Trip into multiple Edges and find out the distance for each Edge. Finally the sum of the distances will be returned as the result.

3. TripsCommand:

    When TripsCommand is executed, an Edge(representing a startTown and an endTown), a filter creteria of qualified Trip and the threshold of the creteria are passed to CommandExecutor as the arguments.  
    CommandExecutor will search out all the Trips from the startTown till the endTown on the Graph, and filters out the ones that do not meet the filter creteria. The internal algorithm is a depth-first search. Finally the count of the qualified Trips is returned as the result.

4. ShortestCommand:

   When ShortestCommand is executed, an Edge(representing a startTown and an endTown) is passed to CommandExecutor as the argument.  
   CommandExecutor will search out the shortest Trip from the startTown till the endTown. The internal algorithm is Dijkstra Algorithm. Finally the total distance of the shortest Trip is returned as the result.
