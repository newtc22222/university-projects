// Định nghĩa các constructor và toán tử của Item
#include "item.h"

Item::Item() : name("Unknown"), price(0), weight(0), height(0), width(0) {}

Item::Item(string n, float p, float w, float h, float wi)
    : name(n), price(p), weight(w), height(h), width(wi) {}

// Overload ostream operator
ostream& operator<<(ostream& os, const Item& item) {
    os << "\n\t\t-----------------------------------------"
       << "\n\t\t|Ten vat dung: " << item.name
       << "\n\t\t|   - Gia thanh: " << item.price
       << "\n\t\t|   - Can nang: " << item.weight
       << "\n\t\t|   - Chieu dai: " << item.height
       << "\n\t\t|   - Chieu rong: " << item.width << endl;
    return os;
}