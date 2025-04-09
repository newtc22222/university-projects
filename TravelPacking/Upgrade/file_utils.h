// Chứa các hàm tiện ích liên quan đến file
#ifndef FILE_UTILS_H
#define FILE_UTILS_H

#include <iostream>
#include <string>
#include <fstream>
#include <vector>

using namespace std;

class FileUtils {
public:
    // Hàm này bây giờ là một phần của class FileUtils và không cần tham số item& nữa.
    static bool loadItemsFromFile(vector<Item>& items, const string& filePath);
    static bool saveItemsToFile(const vector<Item>& items, const string& filePath);
};

#endif // FILE_UTILS_H
