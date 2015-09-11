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
cell state can also be extended. If sometime in the future, a model is created
where there need to be cells with three possible states, this will not be
difficult to implement because it simply requires writing a new subclass.
