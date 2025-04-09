// Định nghĩa các hàm của DisplayManager
#include "display_manager.h"
#include <iostream>
#include <string>
#include <vector>
#include <windows.h>
#include <conio.h>

using namespace std;

DisplayManager::DisplayManager()
    : menuX(40), menuY(9), menuWidth(30), menuHeight(2),
      textColor(11), backgroundColor(1), highlightColor(75) {}

void DisplayManager::clearScreen() {
    system("cls");
}

void DisplayManager::gotoXY(int x, int y) {
    HANDLE hStdout = GetStdHandle(STD_OUTPUT_HANDLE);
    COORD position = { static_cast<SHORT>(x), static_cast<SHORT>(y) };
    SetConsoleCursorPosition(hStdout, position);
}

void DisplayManager::setColor(int color) {
    HANDLE hConsoleOutput = GetStdHandle(STD_OUTPUT_HANDLE);
    CONSOLE_SCREEN_BUFFER_INFO screen_buffer_info;
    GetConsoleScreenBufferInfo(hConsoleOutput, &screen_buffer_info);
    WORD wAttributes = screen_buffer_info.wAttributes;
    color &= 0x000f;
    wAttributes &= 0xfff0;
    wAttributes |= color;
    SetConsoleTextAttribute(hConsoleOutput, wAttributes);
}

void DisplayManager::setBackgroundColor(int color) {
    HANDLE mau = GetStdHandle(STD_OUTPUT_HANDLE);
    SetConsoleTextAttribute(mau, color);
}

void DisplayManager::showCursor(bool visible) {
    HANDLE handle = GetStdHandle(STD_OUTPUT_HANDLE);
    CONSOLE_CURSOR_INFO cursor = { 1, visible };
    SetConsoleCursorInfo(handle, &cursor);
}

void DisplayManager::drawHighlightedBox(int x, int y, int width, int height, int highlightColor, const string& text) {
    drawBox(x, y, width, height, textColor, highlightColor, text);
}

void DisplayManager::drawBox(int x, int y, int width, int height, int textColor, int backgroundColor, const string& text) {
    setBackgroundColor(backgroundColor);
    for (int iy = y + 1; iy <= y + height - 1; iy++) {
        for (int ix = x + 1; ix <= x + width - 1; ix++) {
            gotoXY(ix, iy); cout << " ";
        }
    }
    setColor(7);
    gotoXY(x + 1, y + 1);
    cout << text;

    // Draw border
    setBackgroundColor(1);
    setColor(textColor);
    if (height <= 1 || width <= 1) return;
    for (int ix = x; ix <= x + width; ix++) {
        gotoXY(ix, y); cout << "-";
        gotoXY(ix, y + height); cout << "-";
    }
    for (int iy = y; iy <= y + height; iy++) {
        gotoXY(x, iy); cout << "|";
        gotoXY(x + width, iy); cout << "|";
    }
    gotoXY(x, y); cout << "+";
    gotoXY(x + width, y); cout << "+";
    gotoXY(x, y + height); cout << "+";
    gotoXY(x + width, y + height); cout << "+";
}

void DisplayManager::drawMenu(int x, int y, int width, int height, int textColor, int backgroundColor, const vector<string>& options) {
    for (size_t i = 0; i < options.size(); ++i) {
        drawBox(x, y + (i * 2), width, height, textColor, backgroundColor, options[i]);
        if (i != 0) {
            gotoXY(x, y + (i * 2)); cout << "|";
            gotoXY(x + width, y + (i * 2)); cout << "|";
        }
    }
}

void DisplayManager::disableResizeWindow() {
    HWND hWnd = GetConsoleWindow();
    SetWindowLong(hWnd, GWL_STYLE, GetWindowLong(hWnd, GWL_STYLE) & ~WS_SIZEBOX);
}

void DisplayManager::setConsoleTitle(const wchar_t* title) {
     SetConsoleTitleW(title);
}



MenuState DisplayManager::showMainMenu(ItemManager& itemManager) {
    clearScreen();
    setColor(15);
    gotoXY(28, 2);
    cout << " CHAO MUNG BAN DEN VOI CHUONG TRINH SAP XEP HANH LI ";
    gotoXY(7, 5);
    setColor(15);
    cout << "Nhan phim di chuyen << LEN >> hoac << XUONG >> de tuy chinh va nhan phim di chuyen << SANG PHAI >> de chon";
    showCursor(false);
    vector<string> options = { "Liet ke cac vat pham co san", "Them mot vat dung", "Sua mot vat dung", "Xoa mot vat dung",
                              "Tim kiem vat dung", "Sap xep thong tin vat dung", "Goi y vat dung mang theo", "Thoat" };
    int sl = options.size();
    drawMenu(menuX, menuY, menuWidth, menuHeight, textColor, backgroundColor, options);
    int xp = menuX, yp = menuY;
    int xcu = xp; int ycu = yp;
    bool selected = false;
    MenuState nextState = MenuState::MENU;

    while (!selected) {
        if (_kbhit()) {
            char c = _getch();
            if (c == -32) {
                c = _getch();
                if (c == 72) { // Up
                    if (yp != menuY)
                        yp -= 2;
                    else
                        yp = menuY + menuHeight * (sl - 1);
                } else if (c == 80) { // Down
                    if (yp != menuY + menuHeight * (sl - 1))
                        yp += 2;
                    else
                        yp = menuY;
                } else if (c == 77) { // Right (Enter)
                    selected = true;
                    int j = abs((menuY - yp) / 2);
                    switch (j) {
                        case 0: nextState = MenuState::OUTPUT; break;
                        case 1: nextState = MenuState::ADD; break;
                        case 2: nextState = MenuState::EDIT; break;
                        case 3: nextState = MenuState::DELETE; break;
                        case 4: nextState = MenuState::SEARCH; break;
                        case 5: nextState = MenuState::SORT; break;
                        case 6: nextState = MenuState::SUGGEST; break;
                        case 7: nextState = MenuState::EXIT; break;
                    }
                }
                // Highlight selected option
                gotoXY(xcu, ycu);
                drawHighlightedBox(xcu, ycu, menuWidth, menuHeight, backgroundColor, options[abs((menuY - ycu) / 2)]);
                xcu = xp; ycu = yp;
                drawHighlightedBox(xp, yp, menuWidth, menuHeight, highlightColor, options[abs((menuY - yp) / 2)]);
            }
        }
    }
    return nextState;
}

void DisplayManager::outputItems(ItemManager& itemManager) {
    clearScreen();
    setColor(15);
    gotoXY(43, 2);
    cout << " >>> XUAT DANH SACH <<<";
    gotoXY(7, 5);
    setColor(15);
    cout << "Nhan phim di chuyen << SPACE >> de tiep tuc";
    showCursor(false);

    itemManager.listItems(); // Sử dụng hàm của ItemManager

    cout << "\n\t\t\t\t\t  DA XUAT DANH SACH THANH CONG!";
    cout << "\n\t\t\t\t\tNHAN PHIM << SPACE >> DE TRO VE !";
    while (_getch() != 32); // Wait for space bar
}


void DisplayManager::searchItemsMenu(ItemManager& itemManager) {
    clearScreen();
    setColor(15);
    gotoXY(41, 2);
    cout << " >>> TIM KIEM DANH SACH <<<";
    gotoXY(7, 5);
    setColor(15);
    cout << "Nhan phim di chuyen << LEN >> hoac << XUONG >> de tuy chinh va nhan phim di chuyen << SANG PHAI >> de chon";
    showCursor(false);
    vector<string> options = { "Tim kiem theo ten", "Tim kiem theo gia", "Tim kiem theo can nang",
                                "Tim kiem theo chieu dai", "Tim kiem theo chieu rong", "Thoat" };
    int sl = options.size();
    drawMenu(menuX, menuY, menuWidth, menuHeight, textColor, backgroundColor, options);
    int xp = menuX, yp = menuY;
    int xcu = xp; int ycu = yp;
    bool selected = false;

    while (!selected) {
        if (_kbhit()) {
            char c = _getch();
            if (c == -32) {
                c = _getch();
                if (c == 72) { // Up
                    if (yp != menuY)
                        yp -= 2;
                    else
                        yp = menuY + menuHeight * (sl - 1);
                } else if (c == 80) { // Down
                    if (yp != menuY + menuHeight * (sl - 1))
                        yp += 2;
                    else
                        yp = menuY;
                } else if (c == 77) { // Right
                    selected = true;
                    int j = abs((menuY - yp) / 2);
                    string p;
                    float t;
                    clearScreen();
                    setColor(15);
                    switch (j) {
                        case 0:
                            cout << "\n\t\tNhap ten vat dung ban can tim:  ";
                            cin.ignore(numeric_limits<streamsize>::max(), '\n');
                            getline(cin, p);
                            itemManager.searchItemsByName(p);
                            break;
                        case 1:
                            cout << "\n\t\tNhap gia can tim kiem:  ";
                            cin >> t;
                            itemManager.searchItemsByPrice(t);
                            break;
                        case 2:
                            cout << "\n\t\tNhap can nang can tim kiem: ";
                            cin >> t;
                            itemManager.searchItemsByWeight(t);
                            break;
                        case 3:
                            cout << "\n\t\tNhap chieu dai can tim kiem: ";
                            cin >> t;
                            itemManager.searchItemsByHeight(t);
                            break;
                        case 4:
                            cout << "\n\t\tNhap chieu rong can tim kiem: ";
                            cin >> t;
                            itemManager.searchItemsByWidth(t);
                            break;
                        case 5:
                            break;
                    }
                    cout << "\n\t\t\t\t\tNHAN PHIM << SPACE >> DE TRO VE !";
                    while (_getch() != 32);
                }
                // Highlight
                gotoXY(xcu, ycu);
                drawHighlightedBox(xcu, ycu, menuWidth, menuHeight, backgroundColor, options[abs((menuY - ycu) / 2)]);
                xcu = xp; ycu = yp;
                drawHighlightedBox(xp, yp, menuWidth, menuHeight, highlightColor, options[abs((menuY - yp) / 2)]);
            }
        }
    }
}

void DisplayManager::sortItemsMenu(ItemManager& itemManager) {
    clearScreen();
    setColor(15);
    gotoXY(41, 2);
    cout << " >>> SAP XEP DANH SACH <<<";
    gotoXY(7, 5);
    setColor(15);
    cout << "Nhan phim di chuyen << LEN >> hoac << XUONG >> de tuy chinh va nhan phim di chuyen << SANG PHAI >> de chon";
    showCursor(false);
    vector<string> options = { "Sap xep theo ten", "Sap xep theo gia", "Sap xep theo can nang", "Thoat" };
    int sl = options.size();
    drawMenu(menuX, menuY, menuWidth, menuHeight, textColor, backgroundColor, options);
    int xp = menuX, yp = menuY;
    int xcu = xp; int ycu = yp;
    bool selected = false;

    while (!selected) {
        if (_kbhit()) {
            char c = _getch();
            if (c == -32) {
                c = _getch();
                if (c == 72) { // Up
                    if (yp != menuY)
                        yp -= 2;
                    else
                        yp = menuY + menuHeight * (sl - 1);
                } else if (c == 80) { // Down
                    if (yp != menuY + menuHeight * (sl - 1))
                        yp += 2;
                    else
                        yp = menuY;
                } else if (c == 77) { // Right
                    selected = true;
                    int j = abs((menuY - yp) / 2);
                    clearScreen();
                    setColor(15);
                    switch (j) {
                        case 0:
                            itemManager.sortItemsByName();
                            cout << "\n\t\t\t\t\t     DA SAP XEP THANH CONG!       ";
                            break;
                        case 1:
                            itemManager.sortItemsByPrice();
                            cout << "\n\t\t\t\t\t     DA SAP XEP THANH CONG!       ";
                            break;
                        case 2:
                            itemManager.sortItemsByWeight();
                            cout << "\n\t\t\t\t\t     DA SAP XEP THANH CONG!       ";
                            break;
                        case 3:
                            break;
                    }
                    itemManager.listItems();
                    cout << "\n\t\t\t\t\tNHAN PHIM << SPACE >> DE TRO VE !";
                    while (_getch() != 32);
                }
                // Highlight
                gotoXY(xcu, ycu);
                drawHighlightedBox(xcu, ycu, menuWidth, menuHeight, backgroundColor, options[abs((menuY - ycu) / 2)]);
                xcu = xp; ycu = yp;
                drawHighlightedBox(xp, yp, menuWidth, menuHeight, highlightColor, options[abs((menuY - yp) / 2)]);
            }
        }
    }
}

float DisplayManager::getInputWeight(){
    clearScreen();
    setColor(15);
    cout << "\n\t\tNhap can nang ban co the mang theo: ";
    float weight;
    cin >> weight;
    return weight;
}
