#include <iostream>
#include "display_manager.h" // Quản lý hiển thị
#include "item_manager.h"    // Quản lý vật phẩm
#include "genetic_algorithm.h" // Quản lý thuật toán di truyền
#include "file_utils.h"    // Quản lý file

using namespace std;

// Khai báo biến toàn cục (nên hạn chế và xem xét di chuyển vào class quản lý)
string path = "item.txt";
int so_luong_item_hientai; // Biến quyết định số lượng item hiện tại
int n = 500;                // Số lượng cá thể trong quần thể (có thể chuyển vào GeneticAlgorithm)

int main() {
    // Khởi tạo và quản lý các đối tượng
    DisplayManager displayManager;
    ItemManager itemManager(path); // Truyền đường dẫn file cho ItemManager
    //GeneticAlgorithm geneticAlgorithm(itemManager.getItems(), n); // Khởi tạo GeneticAlgorithm với danh sách vật phẩm và kích thước quần thể

    // Tải dữ liệu từ file
    if (!itemManager.loadItemsFromFile()) {
        cout << "Không thể tải dữ liệu từ file. Chương trình có thể không hoạt động đúng." << endl;
        // Xử lý lỗi nếu không tải được file
    }

    // Vô hiệu hóa thay đổi kích thước cửa sổ console
    displayManager.disableResizeWindow();
    displayManager.setConsoleTitle(L"ARANGE LUGGAGE");

    MenuState state = MenuState::MENU; // Sử dụng enum class
    while (state != MenuState::EXIT) {
        switch (state) {
            case MenuState::MENU:
                state = displayManager.showMainMenu(itemManager); // Truyền itemManager để tương tác với dữ liệu
                break;
            case MenuState::OUTPUT:
                displayManager.outputItems(itemManager);
                state = MenuState::MENU; // Quay lại menu sau khi thực hiện
                break;
            case MenuState::ADD:
                itemManager.addItem();
                itemManager.saveItemsToFile(); // Lưu thay đổi vào file
                state = MenuState::MENU;
                break;
            case MenuState::EDIT:
                itemManager.editItem();
                itemManager.saveItemsToFile();
                state = MenuState::MENU;
                break;
            case MenuState::DELETE:
                itemManager.deleteItem();
                itemManager.saveItemsToFile();
                state = MenuState::MENU;
                break;
            case MenuState::SEARCH:
                displayManager.searchItemsMenu(itemManager); // Quản lý tìm kiếm
                state = MenuState::MENU;
                break;
            case MenuState::SORT:
                displayManager.sortItemsMenu(itemManager);
                state = MenuState::MENU;
                break;
            case MenuState::SUGGEST: {
                float canNang = displayManager.getInputWeight();
                GeneticAlgorithm geneticAlgorithm(itemManager.getItems(), canNang, n); // Khởi tạo *trong* case này
                geneticAlgorithm.run();
                state = MenuState::MENU;
                break;
            }
            case MenuState::EXIT:
                // Lưu dữ liệu trước khi thoát
                if (!itemManager.saveItemsToFile()) {
                    cout << "Có lỗi xảy ra khi lưu dữ liệu." << endl;
                }
                break;
        }
    }

    return 0;
}