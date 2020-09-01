# CoffeeMachine

TestNg Library is required to execute the project.

Design of the Coffee Machine Assignment
---------------------------------------

There will be N outlets present in the machine, any beverage can be requested from any outlet ( Assumption from my understanding of the problem statement )

Ingredient Containers will hold each of the ingredients

Each Ingredient Container will be linked with all the outlets

When a request to prepare a beverage is made , ingredients from their containers will only be dispensed to the outlet if all the necessary ingredients
required for its preparation are present. ( only one request will be made to check this and reduce the quantities at a time)

An outlet will prepare the beverage and serve it once it has all the ingredients and a process is initiated. ( N number of preparations can happen simultaneously )

A request will be rejected if it is made from an outlet which is already in use ( preparing another beverage )
