#Design

##Introduction
The primary goal of our project is to make an easily extensible Cellular Automata Java
application.  There will only be one part of the code that is closed, and that is the
general gameplay.  Our goal is to have every single other part of the game open to
extension.  The gameplay class will simply call general methods in other classes that
can be implemented with subclasses.  This way, it is very easy to add new rules and
make design changes without needing to modify the gameplay class extensively.  New
models, cell types, and cell states will be implemented very simply by adding
subclasses. In the beginning, there will be a model for every CellSociety type.  In
addition, there will be a cell type that goes along with each model. Finally, for each
cell type, there will be a series of cell states that the cells can be in.  Each cell 
will be able to have a single state at any time.

##Overview
This program will have 5 main pieces.  Those pieces are the game, the grid, the model,
the cells, and the cell states.  All pieces except the game will be extensible.  They
will be built in such a manor that adding a new mode to the game is as easy as
extending a single class, and adding no more than a line or two of code somewhere to
instantiate that class.

The first piece is the game.  The game will be composed of two very simple, dumb
classes.  The first class is a game manager class.  This class will simply be
responsible for instantiating the mode and other objects, and making sure they all
work together.  The game will have a step method that is called every so often.
This step method will then call the step methods of everything else that moves
on the screen, and cheks the return of those step functions to make sure everything
is functioning properly and the simulation has not ended.  In addition to the game
manager class, there will also be an xml parser class.  This class will take in a
filename, and the return will be a data structure of some sort containing the xml
information.

The second piece is the grid.  The grid is likely the most simple of the models,
however, it can be extended.  The default grid will simply be a square or
rectangular grid in which each block has 8 neighbors, and each block can query
the state of its neighbors.  This can be subclassed, however, to any orientation
or shape of cells.  This is convenient for shapes that don't necessarily have
8 neighbors.

The third piece is the model.  This is arguably the most important part of
Cell Society as it defines the way that the simulation is run.  It is the
brains of all operations that happen on the cell level.  Different subclasses
of model will have different ways of controlling cells, their states, and the
grid.  Input from the terminal or the xml file will determine which type of
model object to instantiate.

Fourthly, the cell class will containt the information about each cell in the
cell society.  These cells will all have a specific loation on the grid, and
variables indicating its current state.  It will also have an init method and a 
method that will change the current state of the cell to the next state that
the cell needs to be in.  Subclasses of the main cell will also be able to have
different parameters and different ways to interact with other cells.

Finally, each cell will have an instance variable of their state.  This cell
state class will contain all information the cell needs to know about its
current state.  The default states are on and off, but like the other classes,
cell state can also be extended. If a model is created where there need to be
cells with three possible states, this will not be difficult to implement
because it simply requires writing a new subclass.

## User Interface
The main screen of the program contains three elements. As seen in the screenshot below, there is a menu toolbar on the top that can open xml files and quit. The grid is the main part of the UI, with buttons on the right to start, stop and reset the simulation.  In addition, there will be a slider to control the amount of time between frames.

![This is cool, too bad you can't see it](Screenshot.png "Main Screen")

**File menu contains the following:**
* Open XML
* Quit

The slider panel is unique for each type of animation.

Erroneous situations:

1.	Invalid XML file: when user clicks OK, return to file selection window and ask user to select a new file
2.	XML Parameters out of range: when user clicks OK, return to file selection window and ask user to select a new file

## Design Details

Game Class: 
`myGrid`, `myModel`, `myMenu`, `myControlPanel`, `myAdjustPanel`, `myGraph`, `myTitle`, `myTimer`
-	`newAnimation(int number)`
  Start a new animation. Model is specified by number. Return model.
-	`start()`
 	Start the animation. Void.
-	`stop()`
  Pause the animation. Void.
-	`reset()`
  Reset the animation, reset all cells to initial configuration. Void.

Grid Class: 
It has width and height. It has cells and a model.
- `getNumRows()`
  Get the width of the grid. Return `int`.
- `getNumCols()`
  Get the height of the grid. Return `int`.
- `isValid(Location loc)`
  Return if the location is Valid. Return `boolean`.
- E get(Location loc)
  Get the cell on loc. Return `cell`.
- getNeighbors(Location loc)
  Return a list of neighbor cells. Return `ArrayList<Cell>`

Abstract Model Class: 
It has a control panel
-	generateGrid(XML)
-	placeCellOnGrid(Grid,XML)
-	calculateState(ArrayList<Cell> neighbors, Cell cell)


Abstract Cell Class:
- public Cell()
- public getState()
- public setState()
- public Grid<Cell> getGrid()
- public Location getLocation()
- public void putSelfInGrid(Grid<Cell> gr, Location loc)
- public void removeSelfFromGrid()
- public void moveTo(Location newLocation)

Location Class:
Convension: Top left corner is (0,0)
- public Location(int x, int y)
- public int getX()
- public int getY()

State Class:
- State()
- public void setState(int state)
- public int getState()
- public boolean equals(State s)
- public double compareTo(State s)

##Design Considerations
Our highest design consideration will mainly focus on the extendibility of our code. All conditions for each cellular automata model (CA) will be specified in our XML file. Thus, we must take into consideration all potential conditions and how to interpret them into useful and volatile parameters in our code. 

The primary conditions our group discussed in preparing for the design of our game include the rules for change in cell state and the number of possible states a cell can hold. For both these conditions, we decided it would be necessary to create separate classes for these as these conditions differ greatly from model to model. We will call these the “Model” class and the “States” class as reference. 

While this will create more classes in our project and potentially high dependency within classes, we decided that this method would be most effective when we are challenged to extend our code to other models. For example, the Model class will probably have dependencies on the States class and Cell class, using get methods from the States class to determine a current state, and change a state based on a set of rules defined in Model.  

Furthermore, we decided that Model should be an abstract class, with the different types of models (ex. Schelling’s segregation model, Wa-tor prey-predator model, etc.) extending the Model class. Thus, we will have to take into consideration the types of methods within the Model class as well.

##Team Responsibilities
(One person) will work on the XML processing portion of the project. This entails formatting the XML file containing the rules for each model, and also creating the Model abstract class which translates the XML file rules into appropriate parameters, and methods that change. 

(Another person) will work on the Grid and Cell classes. This person may also need to work with (State class person) to make sure Cell extracts the correct dependencies from the State class to get its current state.

(This person) will work on the State class and the Game Manager class. This person will also work with (grid and cell person). 
