package T1020625;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class KetNoiGetter {
    private Connection cnn = null;
    static String url =null;
    public Connection getCnn(){
        return cnn;
    }

    private static KetNoiGetter instance = null;

    private KetNoiGetter() throws SQLException, ClassNotFoundException {
    	if (url ==null) {
    		try {
				ArrayList<String> arr = FileHandler.readAll("config.txt");
				url = arr.get(1).split("\\|")[1];
			} catch (IOException e) {e.printStackTrace();}
    		
    	}
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        //String url = "jdbc:sqlserver://localhost:1433;databaseName=ViDuDB1;user=sa;password=sa;trustServerCertificate=true;";

        cnn = DriverManager.getConnection(url);
    }

    public static KetNoiGetter getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null || instance.cnn.isClosed()) {
            instance = new KetNoiGetter();
        }
        return instance;
    }
}
