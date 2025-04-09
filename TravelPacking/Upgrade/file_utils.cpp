// Định nghĩa các hàm của FileUtils
#include "file_utils.h"
#include "item.h" // Để FileUtils có thể sử dụng struct Item

bool FileUtils::loadItemsFromFile(vector<Item>& items, const string& filePath) {
    ifstream file(filePath);
    if (!file.is_open()) {
        cerr << "Error: Could not open file: " << filePath << endl;
        return false;
    }

    items.clear(); // Clear the vector before loading new items
    string line;
    Item currentItem;
    int lineCount = 1;
    int fieldIndex = 0;

    while (getline(file, line)) {
        if (lineCount != 1 && lineCount % 5 == 0) {
            currentItem.width = stof(line);
            items.push_back(currentItem);
            fieldIndex = 0;
        } else {
            switch (fieldIndex) {
                case 0: currentItem.name = line; break;
                case 1: currentItem.price = stof(line); break;
                case 2: currentItem.weight = stof(line); break;
                case 3: currentItem.height = stof(line); break;
            }
            fieldIndex++;
        }
        lineCount++;
    }

    file.close();
    return true;
}

bool FileUtils::saveItemsToFile(const vector<Item>& items, const string& filePath) {
    ofstream file(filePath);
    if (!file.is_open()) {
        cerr << "Error: Could not open file: " << filePath << endl;
        return false;
    }

    for (const auto& item : items) {
        file << item.name << endl;
        file << item.price << endl;
        file << item.weight << endl;
        file << item.height << endl;
        file << item.width << endl;
    }

    file.close();
    return true;
}
