package T1020625;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DBSinhVienDAO implements SinhVienDAO {

    @Override
    public ArrayList<SinhVien> readDanhSachSinhVien() throws IOException {
        ArrayList<SinhVien> lstSV = new ArrayList<>();
        Connection cnn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql ="select * from SinhVien";
        try {
            try {
				cnn = KetNoiGetter.getInstance().getCnn();
			} catch (ClassNotFoundException e) {e.printStackTrace();}
            stmt = cnn.createStatement();
            rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                String maSinhVien = rs.getString("MaSinhVien");
                String hoTen = rs.getString("HoTen");
                boolean gioiTinhNam = rs.getBoolean("GioiTinhNam");
                String ngaySinh = (rs.getDate("NgaySinh")).toString();
                SinhVien sinhVien = new SinhVien(maSinhVien, hoTen, gioiTinhNam, ngaySinh);
                lstSV.add(sinhVien);
            }
        } catch (SQLException e) {e.printStackTrace(); }
        	finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (cnn != null) cnn.close();
            } catch (SQLException e) { e.printStackTrace();}
        }
        return lstSV;
    }
    
    @Override
    public boolean insertSinhVien(SinhVien sv) {
        Connection cnn = null;
        PreparedStatement pstmt = null;
        try {
            cnn = KetNoiGetter.getInstance().getCnn();
            String sql = "INSERT INTO SinhVien (MaSinhVien, HoTen, GioiTinhNam, NgaySinh) VALUES (?, ?, ?, ?)";
            
            pstmt = cnn.prepareStatement(sql);
            pstmt.setString(1, sv.getMaSinhVien());
            pstmt.setString(2, sv.getHoTen());
            pstmt.setBoolean(3, sv.isGioiTinhNam());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = sdf.parse(sv.getNgaySinh()); 
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime()); 

            pstmt.setDate(4, sqlDate); 

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted >=1; 

        } catch (Exception e) {
            return false; 
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (cnn != null) cnn.close();
            } catch (SQLException ex) {
                return false;
            }
        }
    }
	@Override
	public boolean deleteSinhVien(String maSinhVien) {
		Connection cnn = null;
        PreparedStatement pstmt = null;
        try {
            cnn = KetNoiGetter.getInstance().getCnn();
            String sql = "DELETE FROM SinhVien WHERE MaSinhVien = ?";
            
            pstmt = cnn.prepareStatement(sql);
            pstmt.setString(1,maSinhVien);

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted >=1;

        } catch (Exception e) {
            return false; 
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (cnn != null) cnn.close();
            } catch (SQLException ex) {
                return false;
            }
        }
	}
}
