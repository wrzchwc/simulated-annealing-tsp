# Held-Karp Branch & Bound

## Context

The project was created as one of the tasks during Effective Algorithms Design, during my
studies at [Wroclaw University of Science and Technology](https://pwr.edu.pl/en/).

## Introduction

The task aimed to create the implementation algorithm for solving TSP based on
the [Simulated annealing technique](https://en.wikipedia.org/wiki/Simulated_annealing). 

The process of verifying the accuracy and correctness of created algorithms was based on the test instances come from
the following sources:

- [1st source](http://jaroslaw.mierzwa.staff.iiar.pwr.wroc.pl/pea-stud/tsp)
- [2nd source](http://jaroslaw.rudy.staff.iiar.pwr.wroc.pl/files/pea/instances.zip)

The instances are also available in the `instances` directory in this repository.

##Algorithm variants
###Cooling schedules

- geometric (Cauchy's)
- linear

### Methods of neighbourhood exploration

- swap
- insert

### Ways of selecting solution

- greedy
- insert

### Ways of initial solution choice

- 200 % of MHC calculated with [Nearest neighbour algorithm](https://en.wikipedia.org/wiki/Nearest_neighbour_algorithm)
- 150 % of the cost of randomly generated [HC](https://en.wikipedia.org/wiki/Hamiltonian_path)

## Application modes

`Demonstrate` - mode designed to present application functioning for the individual instance. User enters 
the name of file containing matrix and the TSP is being solved and the result.

`Execute` - mode designed to perform research on the test instances. After providing the name of the
`configuration file` the application solves TSP for specified instances twice for each variant of the algorithm and 
saves the absolute errors to the `output file` file.

### Configuration file

The configuration file should have .txt extension. The required format presents as follows:

`name` `number`  
`name`  `number`  
(...)  
`name` `number`

where:

- `name` - name of the file containing test instance
- `number` - TSP solution (Minimal Hamiltonian cycle)

### Output file

The results of the experiment will be solved in `results.txt` file. The output file presents as follows:

`name` `a` `b` `c` `d` `e` `f` `g` `h`  
`name` `a` `b` `c` `d` `e` `f` `g` `h`  
(...)  
`name` `a` `b` `c` `d` `e` `f` `g` `h`

where:

- `name` - name of the file containing test instance
- `a` - absolute error for geometric cooling schedule
- `b` - absolute error for linear cooling schedule
- `c` - absolute error for swap method of neighbourhood exploration
- `d` - absolute error for insert method of neighbourhood exploration
- `e` - absolute error for greedy way of selecting solution 
- `f` - absolute error for steepest way of selecting solution 
- `g` - absolute error for choosing initial solution with Nearest neighbour algorithm
- `h` - absolute error for choosing initial solution randomly

## Instance format
The instances in the application are loaded from the .txt files. The instances represent graphs as the adjacency matrices.
The required format of the instance presents as follows:  
`name`  
`x` `x` `x`  
`x` `x` `x`  
`x` `x` `x`  
`solution`

where:

- `name` - name of the instance
- `x` - weight of given edge
- `solution` - Minimal Hamiltonian cycle (TSP solution) (optional parameter)



