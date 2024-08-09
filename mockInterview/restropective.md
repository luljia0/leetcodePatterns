1. What do you think you did well in the interview, and what (if anything) do you wish you had done differently? (3 points)

   I well prepared for the interview for the each required parts including solving the problem correctly, doing the right algorithm analysis and describing the alternative solution. 

   I made a small mistake during the mock interview, which is caused by copying and pasting similar code parts and forgetting to revise the difference variable. So next time it's better to double-check the different parts of the similar code while doing copy and paste.

   The explanation and demonstration during coding are a little bit messy because of nervousness. The solution can be to practice more especially explaining line by line at the same time while coding. 

   

2. What do you think are your greatest strengths and assets in coding interviews and environments like them? (2 points)

   my greatest strength is to state the problem and solution logically and clearly. I'm good at decomposing the problem and its solution and organize them in a strictly logically way an demonstrating them.

   

3. What do you think you will need to work on the most in order to prepare for co-op interviews? (3 points)

   1. The ability and fluency to solve the problem

   2. The ability to demonstrate the solution and explain code

   3. The ability and fluency to analyze algorithm

   4. The ability to questiona and communicate

   5. The ability to debug

      

4. What is your plan for practicing and improving the aspect of your performance you identified in #3? (2 points)

   1. pracitice solving leetcode problems on a daily base

   2. learn more about algorithm systematically

   3. mock interview with friends

      

      

```c
// Lulu Jiao
// 04/09/2023
// Traveling Map HW9

//gcc -Wall -g main.c my_dll.h my_graph.h -o main

#include <ctype.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h> //use to declare MAX_INT

#include "my_dll.h"
#include "my_graph.h"
#include "my_pq.h"
#define MAX_LINES 255
#define MAX_LEN_CITY 20

int main() {

	//Read data from datafile city.dat
	FILE* input = fopen("city.dat", "r"); //open the file in reading mode
    if(input == NULL) { 
        perror("Uable to open the file"); //print out the error message if unable to open the file
        exit(1);
    } 
	
	//read the data from file and build a city array
	char city1[MAX_LEN_CITY], city2[MAX_LEN_CITY]; //string of city name
	int distance; //distance between 2 cities
	int cityNum = 0;
	char** cities = malloc(MAX_LINES * sizeof(char*)); //array of cities
	int i;
	for(i = 0; i < MAX_LINES; i++){
		cities[i] = malloc(MAX_LEN_CITY * sizeof(char)); 
	}
	
	//get the index by string in the array
	int array_get_index(char** strings, int strNum, char* target){
		if(strNum == 0) return -1;
		for(i = 0; i < strNum; i++){
			//printf("String[%d]: %s\n", i, strings[i]);
			if(strcmp(strings[i], target) == 0){
				return i;
			}
		}
		return -1;
	}

	//scan file line by line and add city to cities array
	while(fscanf(input, "%s %s %d", city1, city2, &distance) == 3){// array city1 and city2 decay to a pointer
		//printf("%d\n", array_get_index(cities, cityNum, city1));
		//printf("%d\n", array_get_index(cities, cityNum, city2));

		//if city not exist in array
		if(array_get_index(cities, cityNum, city1) == -1){
			//printf("city1: %s\n", city1);
			strcpy(cities[cityNum], city1); //can't use == here because city1 changes
			cityNum++;
			//printf("%d\n", cityNum);		
		}
		if(array_get_index(cities, cityNum, city2) == -1){
			//printf("city2: %s\n", city2);
			strcpy(cities[cityNum], city2);
			cityNum++;
		}
	}
	fclose(input);
	

	// //print the array
	// for(i= 0; i < cityNum; i++){
	// 	printf("%s\n", cities[i]);
	// }
	
	// Create an alphabetized list of cities for selection by the user
	// by sorting the array using qsort
    // Comparator function for qsort
	int compare_strings(const void* a, const void* b) {
		return strcmp(*(const char**)a, *(const char**)b);
	}
	qsort(cities, cityNum, sizeof(cities[0]), compare_strings); //sizeof(cities[0]) is the size of each element of array
	
	//Display the cities from which to select using a number that your
	//program assigns from the alphabetized list of cities that is
	//dynamically created from your city.dat file.  Example:
	//     Please select an origin city
	//     Enter a number associated with one of the cities below:
	//     Amsterdam    ---  1
	//     Belgrade     ---  2
	//     Bern         ---  3
	//     Genoa        ---  4
	//     Hamburg      ---  5
	//     Lisbon       ---  6
	//     Madrid       ---  7
	//     Munich       ---  8
	//     Naples       ---  9
	//     Paris        --- 10
	//     Warsaw       --- 11
	//
	//     TO QUIT ENTER --  0
	//
	//     $>
while(true){		
	//Have the user select an origin city, displaying the above list
	printf("Please select an origin city\nEnter a number associated with one of the cities below:\n");
	int j;
	for(i= 0; i < cityNum; i++){
		printf("%s", cities[i]);
		//print space according to the length of city name string
		for(j = 0; j < MAX_LEN_CITY - strlen(cities[i]); j++){
			printf(" "); 
		}
		printf("--- %d\n", i + 1);
	}
	printf("TO QUIT ENTER");
	for(j = 0; j < MAX_LEN_CITY - 13; j++){
		printf(" "); 
	}
	printf("--- 0\n");
	//take in user input
	int originNum;
	scanf("%d", &originNum);
	//printf("%d\n", originNum);

	//quit
	if(originNum == 0) break;


	//Have the user select a destination city, removing the origin 
	//city from the list
	printf("Please select a destination city\nEnter a number associated with one of the cities below:\n");
	for(i= 0; i < cityNum; i++){
		if(i == originNum -1) continue;
		printf("%s", cities[i]);
		//print space according to the length of city name string
		for(j = 0; j < MAX_LEN_CITY - strlen(cities[i]); j++){
			printf(" "); 
		}
		printf("--- %d\n", i + 1);
	}
	printf("TO QUIT ENTER");
	for(j = 0; j < MAX_LEN_CITY - 13; j++){
		printf(" "); 
	}
	printf("--- 0\n");
	//take in user input
	int destNum;
	scanf("%d", &destNum);

	//quit
	if(destNum == 0) break;

	//build a graph
	graph_t* citiesGraph = create_graph(); //create a graph
	//add nodes
	for(i = 0; i < cityNum; i++){
		//printf("nodes:%d", graph_add_node(citiesGraph, i + 1));
		graph_add_node(citiesGraph, i + 1);
	}
	//read the file again and add edges 
	FILE* inputAgain = fopen("city.dat", "r"); //open the file in reading mode
    if(inputAgain == NULL) { 
        perror("Uable to open the file"); //print out the error message if unable to open the file
        exit(1);
    } 
	while(fscanf(inputAgain, "%s %s %d", city1, city2, &distance) == 3){
		//printf("city1: %d\n", array_get_index(cities, cityNum, city1)+1);
		//printf("city2: %d\n", array_get_index(cities, cityNum, city2)+1);
		//printf("edges:%d", graph_add_edge(citiesGraph, array_get_index(cities, cityNum, city1)+1, array_get_index(cities, cityNum, city2)+1, distance));
		graph_add_edge(citiesGraph, array_get_index(cities, cityNum, city1)+1, array_get_index(cities, cityNum, city2)+1, distance);
	}
	fclose(inputAgain);
	printf("line 175\n");

	//if not reachable, exit the current loop and ask for input again
	if(graph_is_reachable(citiesGraph, originNum, destNum) == 0) continue;
	printf("line 179\n");

	//Apply Dijkstra's Algorithm to determine the optimal (shortest) path
	// build a cost array of everynode from souceNode and initiate them
	int* costs = malloc(cityNum * sizeof(int));
	assert(find_node(citiesGraph, originNum));
	graph_node_t* originGraphNode = find_node(citiesGraph, originNum);
	for(i = 0; i < cityNum; i++){
		//if reachable from sourceNode, then cost = distance
		if(contains_edge(citiesGraph, originNum, i + 1) == 1){
			assert(originGraphNode->outNeighbors);
			costs[i] = find_edge_dest(find_node(citiesGraph, originNum)->outNeighbors->head, i+1)->w;
		}
		//otherwise, cost is infinite
		else{
			costs[i] = INT_MAX;
		}
	}
	// //print the array
	// for(i= 0; i < cityNum; i++){
	// 	printf("%d\n", costs[i]);
	// }
	printf("line 201\n");


	//build hashmap to store the visited city with key and value being the number of the city
	int* visited = malloc(cityNum*sizeof(int)); 
	for(i = 0; i < cityNum; i++){
		visited[i] = 0;
	}
	assert(visited);
	visited[originNum-1] = 1; // origin city has been visited
		for(i = 0; i < cityNum; i++){
		printf("%d", visited[i]);
		}
	printf("line 214\n");
	
	//build a dll to record path
	int* path = malloc(cityNum* sizeof(int));
	path[0] = originNum;//add the originNum
	int pathNum = 1;
		for(i = 0; i < pathNum; i++){
		printf("%d", path[i]);
		}
	printf("line 223\n");

	graph_node_t* priorityGraphNode = originGraphNode; //the next priority Node 
	graph_node_t* intermediateGraphNode = originGraphNode; // priorityNode that has been added to visited
	while(visited[destNum-1] != 1){
		//create a pq
		pq_t* outNeighborsPq = create_pq();
		printf("line 230\n");
		//add outNeighbors to pq
		node_t* tmp = intermediateGraphNode->outNeighbors->head;
		while(tmp!= NULL){
			graph_edge_t* edgeNode = tmp->data;
			printf("outNeighborWeight%d", edgeNode->w);
			pq_enqueue(citiesGraph, outNeighborsPq, edgeNode);
			tmp = tmp->next;
			printf("line 237\n");
		}
		//if visited keep dequeue until, dequeue can't be NULL because is reachable
		while(pq_empty(outNeighborsPq) == 0){
			if(visited[priorityGraphNode->data -1] == 0) {
				printf("line 242\n");
				break;
			}
			priorityGraphNode = pq_dequeue_lowest(outNeighborsPq);
			//printf("pqNode%d\n", priorityGraphNode->data);
			printf("line 246\n");
		}
		
		// mark as visited, add it to path
		visited[priorityGraphNode->data - 1] = 1;
		printf("line 250\n");
		for(i = 0; i < cityNum; i++){
		printf("%d", visited[i]);
		//printf("line 253\n");
		}

		pathNum++;
		path[pathNum-1] = priorityGraphNode->data;
		for(i = 0; i < pathNum; i++){
		printf("%d", path[i]);
		printf("line 262\n");
		}
		
		//update costs of all outNeighbors of priorityGraphNode
		node_t* tmp2 = priorityGraphNode->outNeighbors->head;
		printf("line 269\n");
		while(tmp2 != NULL){
			graph_edge_t* edgeNode2 = tmp2->data;
			printf("line 270\n");
			// if smaller, update
			if(edgeNode2->w < costs[edgeNode2->dest -1]) {
				costs[edgeNode2->dest -1] = edgeNode2->w;
				printf("line 272\n");
				for(i = 0; i < cityNum; i++){
				printf("%d\n", costs[i]);
				}
			}
			tmp2 = tmp2->next;
		}
		// priorityNode becomes intermediateGraphNode
		intermediateGraphNode = priorityGraphNode;
	}
}

	













	//Report three items:
	//     1. the names of the two cities, such as:
	//          ORIGIN:      Lisbon
	//          DESTINATION: Warsaw
	//     2. the length of the optimal path, such as:
	//          LENGTH:      1629
	//     3. the list of the cities visited along the optimal path, like:
	//          PATH CITIES: Lisbon
	//                       Madrid
	//                       Genoa
	//                       Trieste
	//                       Vienna
	//                       Warsaw

	//Continue to ask for two cities to map until the user selects 0
	

    return EXIT_SUCCESS;
}

```

