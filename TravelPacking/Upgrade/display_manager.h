// Quản lý hiển thị
#ifndef DISPLAY_MANAGER_H
#define DISPLAY_MANAGER_H

#include <iostream>
#include <string>
#include <vector>
#include <windows.h> // For console manipulation
#include "item.h"    // Để DisplayManager có thể hiển thị thông tin Item
#include "item_manager.h"

using namespace std;

// Forward declaration của ItemManager
class ItemManager;

// Enum class cho các trạng thái của menu
enum class MenuState {
    MENU, OUTPUT, ADD, EDIT, DELETE, SEARCH, SORT, SUGGEST, EXIT
};

class DisplayManager {
public:
    // Các hàm console
    void clearScreen();
    void gotoXY(int x, int y);
    void setColor(int color);
    void setBackgroundColor(int color);
    void showCursor(bool visible);
    void drawHighlightedBox(int x, int y, int width, int height, int highlightColor, const string& text);
    void drawBox(int x, int y, int width, int height, int textColor, int backgroundColor, const string& text);
    void drawMenu(int x, int y, int width, int height, int textColor, int backgroundColor, const vector<string>& options);
    void disableResizeWindow();
    void setConsoleTitle(const wchar_t* title);

    // Các hàm menu
    MenuState showMainMenu(ItemManager& itemManager);
    void outputItems(ItemManager& itemManager);
    void searchItemsMenu(ItemManager& itemManager);
    void sortItemsMenu(ItemManager& itemManager);
    float getInputWeight();

private:
    int menuX;
    int menuY;
    int menuWidth;
    int menuHeight;
    int textColor;
    int backgroundColor;
    int highlightColor;
};

#endif