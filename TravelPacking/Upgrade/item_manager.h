// Quản lý vật phẩm
#ifndef ITEM_MANAGER_H
#define ITEM_MANAGER_H

#include <iostream>
#include <vector>
#include <string>
#include "item.h" // Để ItemManager có thể quản lý các đối tượng Item

using namespace std;

class ItemManager {
public:
    ItemManager(const string& filePath);
    ~ItemManager();
    bool loadItemsFromFile();
    bool saveItemsToFile();
    void addItem();
    bool editItem();
    bool deleteItem();
    void listItems() const;
    void searchItemsByName(const string& keyword) const;
    void searchItemsByPrice(float price) const;
    void searchItemsByWeight(float weight) const;
    void searchItemsByHeight(float height) const;
    void searchItemsByWidth(float width) const;
    void sortItemsByPrice();
    void sortItemsByWeight();
    void sortItemsByName();
    const vector<Item>& getItems() const; // Getter cho danh sách vật phẩm

private:
    vector<Item> items;
    string filePath;
    void getItemInput(Item& item);
    template <typename T, typename Predicate>void searchItemsByCriteria(const string& criteriaName, T value, Predicate predicate) const;
    void sortItems(function<bool(const Item&, const Item&)> comparison);
};

#endif