# MarsRovers  
  
- [MarsRovers](#marsrovers)
  * [The Problem](#the-problem)
  * [**Classes**](#--classes--)
    + [**Plateau**](#--plateau--)
      - [**Data**](#--data--)
      - [**Behavior**](#--behavior--)
      - [**Constructor**](#--constructor--)
    + [**Rover**](#--rover--)
      - [**Data**](#--data---1)
      - [**Behavior**](#--behavior---1)
      - [**Constructor**](#--constructor---1)
    + [**Position**](#--position--)
      - [**Data**](#--data---2)
      - [**Behavior**](#--behavior---2)
      - [**Constructor**](#--constructor---2)
    + [**ControllerImpl**](#--controllerimpl--)
      - [Data](#data)
      - [Commands](#commands)
        * [**CreatePlatea Command**](#--createplatea-command--)
        * [**AddRover Command**](#--addrover-command--)
        * [**ProcessCommands Command**](#--processcommands-command--)
        * [**Report Command**](#--report-command--)
    + [Input / Output](#input---output)
      - [Examples](#examples)
  
## The Problem  

A squad of robotic rovers are to be landed by NASA on a plateau on Mars. This plateau, which is curiously rectangular, must be navigated by the rovers so that their on-board cameras can get a complete view of the surrounding terrain to send back to Earth.

A rover's position and location is represented by a combination of x and y co-ordinates and a letter representing one of the four cardinal compass points. The plateau is divided up into a grid to simplify navigation. An example position might be 0, 0, N, which means the rover is in the bottom left corner and facing North.

In order to control a rover, NASA sends a simple string of letters. The possible letters are 'L', 'R' and 'M'. 'L' and 'R' makes the rover spin 90 degrees left or right respectively, without moving from its current spot. 'M' means move forward one grid point, and maintain the same heading.

Assume that the square directly North from (x, y) is (x, y+1).

The first line of input is the upper-right coordinates of the plateau, the lower-left coordinates are assumed to be 0,0. The rest of the input is information pertaining to the rovers that have been deployed. Each rover has two lines of input. The first line gives the rover's position, and the second line is a series of instructions telling the rover how to explore the plateau.

The position is made up of two integers and a letter separated by spaces, corresponding to the x and y coordinates and the rover's orientation.

Each rover will be finished sequentially, which means that the second rover won't start to move until the first one has finished moving.

## **Classes**

### **Plateau**  
---  


#### **Data**

-   **width – int**
    -   If the width **is less than or equal to 0,** throw a
        **IlligalArgumentException** with message: **"Invalid width
        size."**
-   **height – int**
    -   If the height **is less than or equal to 0,** throw a
        **IlligalArgumentException** with message: **"Invalid height
        size."**

#### **Behavior**

`
boolean isOccupied()
`

**Check the position of every rover in the RoverRepository. If the position that
is checked is the same with the position of any of the rovers, the method
returns true** otherwise **false.**

#### **Constructor**

A **Plateau** should take the following values upon initialization:

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
(int width, int height, RoverRepository rovers)
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

### **Rover**  
---  


#### **Data**

-   **String – name**
    -   Hold basic name: "Rover\#" + number
-   **ArrayDeque<Characters> – commands**
    -   If the commands are not valid **,** throw a **IlligalArgumentException**
        with message: **"\*\*** Allowed commands are: [L, R, M]. **\*\*"**
-   **Plateau - plateau**
-   **Position - position**
    -   If the positions are outside the Plateau **,** throw a
        **IlligalArgumentException** with message: **"Rover is outside the
        Plateau."**
    -   If the position is inside the Plateau, but there is another Rover on
        that position, throw a **IlligalArgumentException** with message:
        **"Position {otherRoverX otherRoverY} already occupied by another
        rover. "**
-   **Orientation – orientation**
    -   If the orientation is not valid **,** throw a
        **IlligalArgumentException** with message: **"Invalid orientation for
        the {roverName}."**

#### **Behavior**

`void move()`

The **move()** method change the position of every rover when called. It changes
the position based on the orientation they are facing. Before seting the
"oldPosition" to the new one, it checks if the "newPosition" is inside the
Plateau boundaries(**throws IlligalArgumentException("Rover cannot go outside
the plateau boundaries."))** as well as checking if another rover is at that
position **(throws IlligalArgumentException("Position {newPositionX
newPositionY} already occupied by another rover."))**.

`void rotate()`

The **rotate()** method change the orientation of the rover when called. It
changes the orientation based on the command it is called with.

Example:
```
orientation = N, command = L -> orientation = W
orientation = S, command = L -> orientation = E
orientation = W, command = R -> orientation = N
orientation = E, command = R -> orientation = S
```
#### **Constructor**

A **Rover** should take the following values upon initialization:

**(Plateau plateau, int coordinateX, int coordinateY, String orientation, String
commands)**

### **Position**
---  
#### **Data**

-   **x – int**
    -   If the `x` is less than 0 or greater that Plateau width, **throw
        a** IlligalArgumentException **with message:**" **Invalid X Coordinate.
        Outside Plateau bounds.**"
-   **y – int**
    -   If the `y` is less than 0 or greater that Plateau width, **throw
        a** IlligalArgumentException **with message:**" **Invalid Y Coordinate.
        Outside Plateau bounds.**"  
        
#### **Behavior**



`
boolean isInsidePlateau()
`

The **isInsidePlateau()** method checks the position is inside the boundaries of
the Plateau.

`
Position changePosition(Orientation orientation)
`

The **changePosition()** method change the position of the rover when called. It
changes the position based on the orientation it is facing.

Example:

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
oldPosition = {3, 3}
orientation =  -> newPosition = 
orientation =  -> newPosition = 
orientation =  -> newPosition = 
orientation =  -> newPosition = 
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

#### **Constructor**

A **Position** should take the following values upon initialization:

`(int x, int y)`

### **ControllerImpl**  
---  


#### Data

- `Plateau` - Plateau
- `rovers` – RoversRepository

#### Commands

There are several **commands** , which control the **business\*\*** logic **of
the** application\*\*.

##### **CreatePlatea Command**

Parameters:

-   width – int  
-   height – int  

**Functionality**

If Plateau is already initialized, we **throw an** **IllegalArgumentException**
with **the following message:**

> "Plateau already created!"  

If Plateau receives valid parameters it will be initialized successfully and the
method **return** **the following message:**

> "Successfully created Plateau with width: {width}, height: {height}."  

  
##### **AddRover Command**


Parameters:

-   x – int
-   y – int
-   orientation – String
-   commands - String

**Functionality**

If Plateau is not already initialized, we **throw an** **NullPointerException**
with **the following message:**

**"Plateau is not created!"**

If Plateau is already created, and we give valid parameters to method, we create
the Rover and add it to the RoverRepository **,** and **return the following
message:**

> "Successfully added %s.., {roverName}"

##### **ProcessCommands Command**

**Functionality**

If RoverRepository is empty, we **throw an** **IllegalArgumentException** with
**the following message:**

> "No rovers added yet."

If RoverRepository is not empty, we iterate through all rovers and execute it's
commands and the method **return\*\*** the following message:\*\*

> "Successfully processed commands for all rovers."  


##### **Report Command**


**Functionality**

If RoverRepository is empty, we **throw an** **IllegalArgumentException** with
**the following message:**

> "No rovers added yet."

If RoverRepository is not empty, we iterate through all rovers and get
information about each rover, and the method **return\*\*** the following
message:\*\*

**Output format for each rover is:**

> \- {roverName}  
\-- Location {roverCoordinateX roverCoordinateX roverOrientation}**

### Input / Output  
---  

Below, you can see the **format** in which **each command** will be given in the
input:

-   **CreatePlateau** {width} {height}
-   **AddRover** {coordinateX} {coordinateY} {Orientation} {Commands}
-   **ProcessCommands**
-   **Report**
-   **Exit**  
  
#### Examples  

> Input  

```
CreatePlateau 5 5
AddRover 1 2 N LMLMLMLMM
AddRover 3 3 E MMRMMRMRRM
ProcessCommands
Report
Exit
```  

> Output  

```
Successfully created Plateau with width:5, height:5.
Successfully added Rover#0.
Successfully added Rover#1.
Successfully processed commands for all rovers.

-  Rover#0
-- Location 1 3 N
-  Rover#1
-- Location 5 1 E
Mission accomplished!
```  
  
---  
  
> Input  
  
```  
CreatePlateau 6 6
AddRover 2 2 E M
AddRover 3 1 N M
ProcessCommands
Report
Exit
```  

> Output  
  
```  
Successfully created Plateau with width:6, height:6.
Successfully added Rover#0.
Successfully added Rover#1.
Position {3 2} already occupied by another rover.

-  Rover#0
-- Location 3 2 E
-  Rover#1
-- Location 3 1 N
Mission accomplished!
```  
  
---  
  
> Input  
  
```
CreatePlateau 2 2
AddRover 0 0 N MMM 
ProcessCommands
Report
Exit
```  
  
> Output  
  
```  
Successfully created Plateau with width:2, height:2.
Successfully added Rover#0.
Invalid Y Coordinate. Outside Plateau bounds.

-  Rover#0
-- Location 0 2 N
Mission accomplished!
```
