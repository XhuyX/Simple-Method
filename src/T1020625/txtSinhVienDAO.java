package T1020625;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class txtSinhVienDAO implements SinhVienDAO {
	@lombok.Setter
	String source;
	
    @Override
    public ArrayList<SinhVien> readDanhSachSinhVien() throws IOException {
        ArrayList<SinhVien> sv = new ArrayList<>();
        ArrayList<String> lines = FileHandler.readAll(source);
        for (String line:lines) {
        	String[] part = line.split(",");
            if (part.length == 4) {
                SinhVien x = new SinhVien(part[0], part[1],
                		Boolean.parseBoolean(part[2]), part[3]);
                sv.add(x);}
        }
                
        return sv;
    }
    @Override
    public boolean insertSinhVien (SinhVien sv) {
 
    	if (isExisted (sv.getMaSinhVien())) return false;
    	try (BufferedWriter writer = new BufferedWriter(new FileWriter(source, true))) {
    		 writer.write("\n" + sv.getMaSinhVien() + "," + sv.getHoTen() + "," + sv.isGioiTinhNam() + "," + sv.getNgaySinh());
            return true;
        } catch (IOException e) {
           return false;
        }
    }
    Boolean isExisted (String maSV) {
    	ArrayList<String> lines;
		try {
			lines = FileHandler.readAll(source);
		} catch (IOException e) {
			return false;
		}
		for (String line : lines) {
				String [] part = line.split(",");
					if (part.length==4) {
						if (part[0].equals(maSV)) return true;
					}
					
				}
		return false;
    }
	@Override
	public boolean deleteSinhVien(String maSinhVien) throws IOException {
	    ArrayList<String> sv = new ArrayList<>();
	    boolean isDeleted = false;
	    ArrayList <String> lines = FileHandler.readAll(source);
	    for (String line: lines) {
	    	String[] part = line.split(",");
	    	if (part.length ==4) {
	    		if (part[0].equals(maSinhVien)) isDeleted = true;
	    		else sv.add(line);
	    	}
	    }
	            
	    if (isDeleted) {
	    	FileHandler.addAll(sv, source);   
	    }
	    return isDeleted;
	           
	   
	}
}
