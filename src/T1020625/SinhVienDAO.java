package T1020625;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public interface SinhVienDAO {
	ArrayList<SinhVien> readDanhSachSinhVien() throws IOException, ParseException;
	public boolean insertSinhVien (SinhVien sv) ;
	public boolean deleteSinhVien (String maSinhVien) throws IOException;
}
