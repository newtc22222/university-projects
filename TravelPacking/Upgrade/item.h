// Định nghĩa struct Item
#ifndef ITEM_H
#define ITEM_H

#include <string>
#include <iostream> // Để sử dụng ostream

using namespace std;

// Struct Item (có thể chuyển thành class nếu cần thêm phương thức)
struct Item {
    string name;
    float price;
    float weight;
    float height;
    float width;

    Item(); // Constructor mặc định
    Item(string name, float price, float weight, float height, float width);

    // Overload toán tử xuất để in Item dễ dàng hơn
    friend ostream& operator<<(ostream& os, const Item& item);
};

#endif