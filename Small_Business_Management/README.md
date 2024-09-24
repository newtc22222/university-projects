# NNLTTT_PROJECT
# INFORMATION
- Java Development Kit: JavaSE 15
- GUI: Java Swing + Java awt
- Database: MySQL
- Connection: JDBC
- Connection Library: mysql-connector-java-8.0.13.jar

## Tree structure
- | bean
    - | DuAn
    - | NhanVien
    - | PhanCong
    - | PhongBan
    - | ThanNhan
- | dbcontext
    - | Config: database_name, username, password
    - | MySQLConnection: setting connection
    - | DBContext: get a connection
- | dao
    - | DuAnDAO
    - | NhanVienDAO
    - | PhanCongDAO
    - | PhongBanDAO
    - | ThanNhanDAO
- | gui
    - | DoiMatKhauForm
	- | DuAnForm
	- | LoginForm (begin)
	- | MainForm
	- | NhanVienForm
	- | PhanCongVaLuongForm
	- | ThanNhanForm
