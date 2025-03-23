package T1020625;
public class TXTSinhVienDAOFactory extends SinhVienDAOMethod {
    @Override
    public SinhVienDAO createDAO(String path) {
        txtSinhVienDAO dao = new txtSinhVienDAO();
        dao.setSource(path);
        return dao;
    }
}