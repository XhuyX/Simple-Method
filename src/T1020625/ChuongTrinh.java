package T1020625;
import java.io.IOException;
import java.util.ArrayList;

public class ChuongTrinh {
	
	private static void hienThiDanhSach(ArrayList<SinhVien> danhSach) {
	    for (SinhVien sv : danhSach) {
	        System.out.println(
	            sv.getMaSinhVien() + " " + 
	            sv.getHoTen() + " " + 
	            sv.isGioiTinhNam() + " " + 
	            sv.getNgaySinh()
	        );
	    }
	}
	private static void themSinhVienTXT(SinhVienDAO daotxt, SinhVien sv) {
	    boolean result = daotxt.insertSinhVien(sv);
	    System.out.println(result ? "Đã thêm vào TXT" : "Lỗi thêm vào TXT");
	}
	private static void themSinhVienDB(SinhVienDAO daodb, SinhVien sv) {
	    boolean result = daodb.insertSinhVien(sv);
	    System.out.println(result ? "Đã thêm vào DB" : "Lỗi thêm vào DB");
	}

	private static void xoaSinhVienDB(SinhVienDAO daodb, String maSV) throws IOException {
	    boolean result = daodb.deleteSinhVien(maSV);
	    System.out.println(result ? "Đã xóa từ DB" : "Lỗi xóa từ DB");
	}
	private static void xoaSinhVienTXT(SinhVienDAO daotxt, String maSV) throws IOException {
	    boolean result = daotxt.deleteSinhVien(maSV);
	    System.out.println(result ? "Đã xóa từ TXT" : "Lỗi xóa từ TXT");
	}

	public static void main(String[] arg) {
	    try {
	    	ArrayList<String> config = FileHandler.readAll("config.txt");
	    	String [] line1 = config.get(0).split("\\|");
	    	String [] line2 = config.get(1).split("\\|");
	    	
	    	SinhVienDAOMethod txtFactory = new TXTSinhVienDAOFactory();
	        SinhVienDAOMethod dbFactory = new DBSinhVienDAOFactory();

	        SinhVienDAO daotxt = txtFactory.createDAO(line2[0]); // path từ config
	        SinhVienDAO daodb = dbFactory.createDAO(line2[1]);;
	        
	        // Đọc và hiển thị
	        hienThiDanhSach(daotxt.readDanhSachSinhVien());
	        System.out.println("Sinh viên từ database");
	        hienThiDanhSach(daodb.readDanhSachSinhVien());

	        // Thêm sinh viên
	        themSinhVienTXT(daotxt, new SinhVien("T102002", "Huỳnh Quang", true, "2003/01/01"));
	        themSinhVienDB(daodb, new SinhVien("T101997", "Nguyễn Công", true, "1999-01-01"));

	        // Xóa sinh viên
	        xoaSinhVienTXT(daotxt, "T102002");
	        xoaSinhVienDB(daodb, "T102002");

	    } catch (Exception e) {
	        System.err.println("Lỗi chương trình: " + e.getMessage());
	        e.printStackTrace();
	    }
	}
}
