// Định nghĩa các hàm của ItemManager
#include "item_manager.h"
#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <limits> // Để sử dụng numeric_limits
#include <algorithm> // Để sử dụng std::sort
#include "file_utils.h"

using namespace std;

ItemManager::ItemManager(const string& path) : filePath(path) {}

ItemManager::~ItemManager() {}



bool ItemManager::loadItemsFromFile() {
    return FileUtils::loadItemsFromFile(items, filePath);
}

bool ItemManager::saveItemsToFile() {
    return FileUtils::saveItemsToFile(items, filePath);
}

void ItemManager::addItem() {
    Item newItem;
    cin.ignore(numeric_limits<streamsize>::max(), '\n'); // Clear buffer
    cout << "Nhap ten san pham can them: ";
    getline(cin, newItem.name);
    cout << "Nhap gia thanh: ";
    cin >> newItem.price;
    cout << "Nhap can nang: ";
    cin >> newItem.weight;
    cout << "Nhap chieu cao: ";
    cin >> newItem.height;
    cout << "Nhap chieu rong: ";
    cin >> newItem.width;
    items.push_back(newItem);
}

bool ItemManager::editItem() {
    string itemName;
    cin.ignore(numeric_limits<streamsize>::max(), '\n');
    cout << "Nhap ten san pham muon sua: ";
    getline(cin, itemName);

    for (auto& item : items) {
        if (item.name == itemName) {
            cout << "Thong tin san pham: " << item;
            getItemInput(item);
            return true;
        }
    }

    cout << "Khong tim thay san pham trong danh sach!" << endl;
    return false;
}

bool ItemManager::deleteItem() {
    string itemName;
    cin.ignore(numeric_limits<streamsize>::max(), '\n');
    cout << "Nhap ten vat dung can xoa: ";
    getline(cin, itemName);

    for (auto it = items.begin(); it != items.end(); ++it) {
        if (it->name == itemName) {
            items.erase(it);
            cout << "\n\t\tDa xoa vat dung co ten la: " << itemName << endl;
            return true;
        }
    }

    cout << "\n\n\tVat dung khong co trong danh sach!" << endl;
    return false;
}

void ItemManager::listItems() const {
    if (items.empty()) {
        cout << "\n\t\tDanh sach vat pham rong." << endl;
        return;
    }
    for (const auto& item : items) {
        cout << item;
    }
}

void ItemManager::searchItemsByName(const string& keyword) const {
    bool found = false;
    for (const auto& item : items) {
        if (item.name.find(keyword) != string::npos) {
            cout << item;
            found = true;
        }
    }
    if (!found) {
        cout << "Khong tim thay vat dung nao co ten chua '" << keyword << "'" << endl;
    }
}

void ItemManager::searchItemsByPrice(float price) const {
    searchItemsByCriteria("gia", price, [](const Item& item, float value) { return item.price == value; });
}

void ItemManager::searchItemsByWeight(float weight) const {
    searchItemsByCriteria("can nang", weight, [](const Item& item, float value) { return item.weight == value; });
}

void ItemManager::searchItemsByHeight(float height) const {
    searchItemsByCriteria("chieu dai", height, [](const Item& item, float value) { return item.height == value; });
}

void ItemManager::searchItemsByWidth(float width) const {
    searchItemsByCriteria("chieu rong", width, [](const Item& item, float value) { return item.width == value; });
}

void ItemManager::sortItemsByPrice() {
    sortItems([](const Item& a, const Item& b) { return a.price > b.price; });
}

void ItemManager::sortItemsByWeight() {
    sortItems([](const Item& a, const Item& b) { return a.weight > b.weight; });
}

void ItemManager::sortItemsByName() {
    sortItems([](const Item& a, const Item& b) { return a.name < b.name; });
}

const vector<Item>& ItemManager::getItems() const {
    return items;
}

void ItemManager::getItemInput(Item& item) {
    cin.ignore(numeric_limits<streamsize>::max(), '\n');
    cout << "\n\t\tNhap ten vat dung moi: ";
    getline(cin, item.name);
    cout << "\n\t\tNhap gia thanh moi: ";
    cin >> item.price;
    cout << "\n\t\tNhap can nang moi: ";
    cin >> item.weight;
    cout << "\n\t\tNhap chieu cao moi: ";
    cin >> item.height;
    cout << "\n\t\tNhap chieu rong moi: ";
    cin >> item.width;
}

template <typename T, typename Predicate>
void ItemManager::searchItemsByCriteria(const string& criteriaName, T value, Predicate predicate) const {
    bool found = false;
    for (const auto& item : items) {
        if (predicate(item, value)) {
            cout << item;
            found = true;
        }
    }
    if (!found) {
        cout << "\n\t\tKhong co vat dung nao co " << criteriaName << " la " << value << "!\n";
    }
}

void ItemManager::sortItems(function<bool(const Item&, const Item&)> comparison) {
    sort(items.begin(), items.end(), comparison);
}
