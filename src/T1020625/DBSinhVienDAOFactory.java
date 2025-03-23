package T1020625;

public class DBSinhVienDAOFactory extends SinhVienDAOMethod {
    @Override
    public SinhVienDAO createDAO(String path) {
        return new DBSinhVienDAO(); // Path không dùng cho DB
    }
}
