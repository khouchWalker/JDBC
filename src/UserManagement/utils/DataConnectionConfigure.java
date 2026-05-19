package UserManagement.utils;

import UserManagement.model.dto.UserResponseDto;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.Table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class DataConnectionConfigure {


    public static Connection getConnection() {
        String username  = "postgres";
        String password = "qwer";
        String url = "jdbc:postgresql://localhost:5433/postgres";
        try {
            return DriverManager.getConnection(
                    url,
                    username,
                    password

            );
        }catch (Exception exception){
            System.out.println("Connection Failed! Check output console");
        }
        return null;
    }
}
