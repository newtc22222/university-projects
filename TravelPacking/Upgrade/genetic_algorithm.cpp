// Định nghĩa các hàm của GeneticAlgorithm
#include "genetic_algorithm.h"
#include <iostream>
#include <vector>
#include <algorithm>
#include <random>
#include <numeric> // Để sử dụng std::accumulate

using namespace std;

GeneticAlgorithm::GeneticAlgorithm(const vector<Item>& itemList, float weightLimit, int popSize)
    : items(itemList), maxWeight(weightLimit), populationSize(popSize) {
    population.resize(populationSize);
    fitness.resize(populationSize);
    for (int i = 0; i < populationSize; ++i) {
        population[i].resize(items.size());
    }
}

void GeneticAlgorithm::run() {
    initializePopulation();
    vector<int> bestSolution(items.size());
    float bestFitness = 0;
    int generations = 400;

    for (int i = 0; i < generations; ++i) {
        evaluateFitness(bestSolution, bestFitness);
        selection();
        crossover();
        mutation();
    }

    cout << "\n\t\tGoi y: Cac vat dung nen mang theo: " << endl;
    float totalWeight = 0;
    for (size_t j = 0; j < items.size(); ++j) {
        if (bestSolution[j] == 1) {
            cout << "\n\t\t" << j + 1 << ". " << items[j].name
                 << ": gia( " << items[j].price << " ), can nang( " << items[j].weight << " )" << endl;
            totalWeight += items[j].weight;
        }
    }
    cout << "\n\t\tTong can nang: " << totalWeight << endl;
    cout << "\n\t\tTong gia tri: " << bestFitness << endl;
}

void GeneticAlgorithm::initializePopulation() {
    random_device rd;
    mt19937 gen(rd());
    uniform_int_distribution<> dist(0, 1);

    for (int i = 0; i < populationSize; ++i) {
        for (size_t j = 0; j < items.size(); ++j) {
            population[i][j] = dist(gen);
        }
    }
}

void GeneticAlgorithm::evaluateFitness(vector<int>& bestSolution, float& bestFitness) {
    for (int i = 0; i < populationSize; ++i) {
        fitness[i] = calculateFitness(population[i]);
    }

    for (int i = 0; i < populationSize; ++i) {
        if (fitness[i] > bestFitness) {
            bestFitness = fitness[i];
            bestSolution = population[i];
        }
    }
}

float GeneticAlgorithm::calculateFitness(const vector<int>& individual) {
    float totalWeight = 0;
    float totalValue = 0;
    for (size_t j = 0; j < items.size(); ++j) {
        if (individual[j] == 1) {
            totalWeight += items[j].weight;
            totalValue += items[j].price;
        }
    }
    return (totalWeight <= maxWeight) ? totalValue : 0;
}

void GeneticAlgorithm::selection() {
    vector<float> tempFitness = fitness;
    sort(tempFitness.begin(), tempFitness.end(), greater<float>());
    float threshold = tempFitness[populationSize * 80 / 100];

    random_device rd;
    mt19937 gen(rd());
    uniform_int_distribution<> dist(0, 1);

    for (int i = 0; i < populationSize; ++i) {
        if (fitness[i] <= threshold) {
            for (size_t j = 0; j < items.size(); ++j) {
                population[i][j] = dist(gen);
            }
        }
    }
}

void GeneticAlgorithm::crossover() {
    random_device rd;
    mt19937 gen(rd());
    uniform_int_distribution<> dist(0, populationSize - 1);
    uniform_int_distribution<> bitDist(0, 1);

    for (int i = 0; i < 50; ++i) {
        int parent1 = dist(gen);
        int parent2 = dist(gen);
        for (size_t j = 0; j < items.size(); ++j) {
            if (bitDist(gen) == 1) {
                swap(population[parent1][j], population[parent2][j]);
            }
        }
    }
}

void GeneticAlgorithm::mutation() {
    random_device rd;
    mt19937 gen(rd());
    uniform_int_distribution<> popDist(0, populationSize - 1);
    uniform_int_distribution<> itemDist(0, items.size() - 1);

    for (int i = 0; i < 5; ++i) {
        int index = popDist(gen);
        int bit = itemDist(gen);
        population[index][bit] = (population[index][bit] == 0) ? 1 : 0;
    }
}
