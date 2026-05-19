package UserManagement.model;


import UserManagement.utils.DataConnectionConfigure;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//repository or DAO data access object
public class UserDao{
    public List<User> findAll(){
        String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();
        try(Connection connection = DataConnectionConfigure.getConnection()){
            Statement statement = connection.createStatement();
            boolean isExecuted = statement.execute(sql);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()){;
                int id = resultSet.getInt("id");
                String uuid = resultSet.getString("uuid");
                String userName = resultSet.getString("user_name");
                String email  =resultSet.getString("email");
                String password = resultSet.getString("password");
                String profile  = resultSet.getString("profile");
                User user = new User(id,uuid,userName,email,password,profile);
                // add user object to list
                users.add(user);
            }
        }catch (Exception exception){
            System.out.println("Connection failed");
        }
        return users;
    }
    public int remove(User user){
        String sql = "DELETE FROM users WHERE id=?";
         Connection connection = DataConnectionConfigure.getConnection();
         try {
             PreparedStatement pstmt = connection.prepareStatement(sql);
             pstmt.setInt(1,user.getId());
             int delete = pstmt.executeUpdate();
         }catch (SQLException exception){
             System.out.println(exception.getMessage());
         }



        return 1;
    }
    public User update(User uu){
        // update
        String sql  = """
                UPDATE users 
                SET user_name = ?, email = ?, password = ?, profile = ?
                WHERE id = ?
                """;
        // try ca
        User user = UserDatabase.users.stream()
                .filter(u->u.getId().equals(uu.getId()))
                .findFirst().get();
        if(user==null){
            throw new RuntimeException("User is not found");
        }
        // remove old version of User
        UserDatabase.users.remove(user);
        // update
        user.setName(uu.getName());
        user.setEmail(uu.getEmail());
        user.setPassword(uu.getPassword());
        user.setProfile(uu.getProfile());
        UserDatabase.users.add(user);
        return user;
    }
    public User save(User user){
        String sql = """
                INSERT  INTO users(uuid, user_name, email, password, profile)
                VALUES (?,?,?,?,?)
                """;
        try(Connection connection = DataConnectionConfigure.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // set value for replace ? symbol in SQL Statement
            preparedStatement.setString(1,user.getUuid());
            preparedStatement.setString(2,user.getName());
            preparedStatement.setString(3,user.getEmail());
            preparedStatement.setString(4,user.getPassword());
            preparedStatement.setString(5,user.getProfile());
            //
            int rowAffected = preparedStatement.executeUpdate();
            if(rowAffected<=0){
                throw new RuntimeException("Failed to insert new data into table users");
            }
            return user;
        }catch (Exception exception){
            System.out.println("Error during insert new user");
        }
        return null;
    }
}
