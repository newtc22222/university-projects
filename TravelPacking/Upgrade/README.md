## Giải thích cấu trúc mới:

- `main.cpp`: Chứa hàm main, khởi tạo và điều phối các đối tượng, và vòng lặp chính của chương trình.
- `display_manager.h/cpp`: Quản lý tất cả các tương tác hiển thị với console. Menu, in thông tin, v.v.
- `item_manager.h/cpp`: Quản lý danh sách các Item (thêm, sửa, xóa, tìm kiếm, sắp xếp, lưu/tải).
- `genetic_algorithm.h/cpp`: Gói gọn thuật toán di truyền vào một class.
- `file_utils.h/cpp`: Chứa các hàm tiện ích cho việc đọc và ghi file.
- `item.h/cpp`: Định nghĩa struct Item và các hàm liên quan.

## Lợi ích của việc chia nhỏ code:

- **Tính mô-đun**: Mỗi phần của chương trình được tách thành một đơn vị độc lập, dễ hiểu và quản lý hơn.
- **Khả năng tái sử dụng**: Các class như DisplayManager và ItemManager có thể được tái sử dụng trong các dự án khác.
- **Dễ bảo trì**: Khi có lỗi hoặc cần thay đổi, bạn chỉ cần tập trung vào một file cụ thể, thay vì phải duyệt qua toàn bộ code.
- **Phát triển song song**: Nhiều người có thể làm việc trên các file khác nhau cùng một lúc.
- **Giảm thiểu sự phụ thuộc**: Thay đổi trong một file ít ảnh hưởng đến các file khác.