// Quản lý thuật toán di truyền
#ifndef GENETIC_ALGORITHM_H
#define GENETIC_ALGORITHM_H

#include <iostream>
#include <vector>
#include <functional> // Để sử dụng std::function
#include "item.h"    // Để GeneticAlgorithm có thể sử dụng struct Item

using namespace std;

class GeneticAlgorithm {
public:
    GeneticAlgorithm(const vector<Item>& items, float maxWeight, int populationSize = 500);
    void run();

private:
    const vector<Item>& items;
    float maxWeight;
    int populationSize;
    vector<vector<int>> population;
    vector<float> fitness;

    void initializePopulation();
    void evaluateFitness(vector<int>& bestSolution, float& bestFitness);
    void selection();
    void crossover();
    void mutation();
    float calculateFitness(const vector<int>& individual);
};

#endif