# Sheep Dog Trials 

## Overview

This is a Java game called "Sheepdog Trials". In this gridbased game, the player controls a dog among a heard of sheep. The sheep run away from the dog, which allows them to be chased into a pen. The game is won when all sheep are in the pen.

A system design report includes a class diagram and the flow of execution.


## Running the program

To play the game, follow these steps:

1. Clone the project into local system.
2. Open SheepdogTrials.java in your preferred IDE.
3. Press the IDE's run button to execute the program.
4. After entering the field setting, i.e. fixed or random, the field of the game will be shown. 
- Empty patches of grass are shown as '-'. 
- The dog is represented by 'D' and sheep by 'S'. 
- The pen area is represented by 'P'.

    Example of the field. \
The field has 8 rows and 8 columns. \
XXP----- \
PPP----- \
-------D \
-------- \
--S----S \
-------- \
-------- \
-----S-- 


## Game features

- The user can choose between random and fixed setup at start-up. For the random setup, the following are chosen at random: grid size, pen size and position, number and location of sheep. However, make sure that the game is playable.

- A representation of the state of the game. The fixed setup is a $8 x 8$ grid with one dog, 5 sheep and a pen area of size $2 \times 3$ (or $3 \times 2$ ). No bushes.

- To move the dog up, enter 'W' and press enter. Use 'S' for down, 'A' for left and 'D' for right.

- Display the number of moves made by the player so far.

- When a sheep enters the pen area it will stay in the pen. It does not matter which part of the pen the sheep enters i.e. the squares of the pen are never counted as "occupied".

- Sheep is flocking when the dog is absent and reluctance to enter pen.

- Sheep move away from the dog as it approaches. Dogs and sheep cannot move out of bounds.

- When the game is over, display a "victory" message and offer the user to restart the game.

