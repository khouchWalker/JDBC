package UserManagement.view;

import UserManagement.controller.UserController;
import UserManagement.model.dto.CreateUserDto;
import UserManagement.model.dto.UpdateRequestDto;
import UserManagement.model.dto.UserResponseDto;
import UserManagement.utils.APIResponse;
import UserManagement.utils.DataConnectionConfigure;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.Table;

import java.util.List;
import java.util.Scanner;

public class UI {
    private  final  static UserController userController = new UserController();


    public static void displayUsers(List<UserResponseDto> users) {

        if (users == null || users.isEmpty()) {
            System.out.println("No users found.");
            return;
        }

        Table table = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER);

        table.addCell("id");
        table.addCell("uuid");
        table.addCell("name");
        table.addCell("email");
        table.addCell("profile");

        for (UserResponseDto user : users) {

            table.addCell(user.id().toString(),
                    new CellStyle(CellStyle.HorizontalAlign.center));

            table.addCell(user.uuid(),
                    new CellStyle(CellStyle.HorizontalAlign.center));

            table.addCell(user.name(),
                    new CellStyle(CellStyle.HorizontalAlign.center));

            table.addCell(user.email(),
                    new CellStyle(CellStyle.HorizontalAlign.center));

            table.addCell(user.profile(),
                    new CellStyle(CellStyle.HorizontalAlign.center));
        }

        System.out.println(table.render());
    }
    private static void thumbnail(){
        System.out.println("""
                ==================== User Management System ========================
                1. Create User
                2. Search User by UUID
                3. Search User by name
                4. Delete User by UUID
                5. Update User by UUID
                6. List all Users
                7. Exit
                """);
    }
    private static int insertOption(){
        System.out.print("[+] Insert your option: ");
        try {
            return Integer.parseInt(new Scanner(System.in).nextLine());
        } catch (Exception e) {
            return 1;
        }
    }
    public static  void getRendered(){
        while (true){
            thumbnail();
            System.out.println("--");
            switch (insertOption()){
                case 1-> {
                    System.out.println("Create user");
                    System.out.print("[+] Insert name: ");
                    String name = new Scanner(System.in).nextLine();
                    System.out.print("[+] Insert email: ");
                    String email = new Scanner(System.in).nextLine();
                    System.out.print("[+] insert password: ");
                    String password = new  Scanner(System.in).nextLine();
//
                    CreateUserDto createUserDto = new CreateUserDto(name,email,password);
                    APIResponse<UserResponseDto> createUser = userController.createUser(createUserDto);
                    System.out.println(createUser.message());
                    if (createUser.data() != null) {
                        DataConnectionConfigure.getConnection();

                    }
                }
                case 2 ->{
                    System.out.print("Enter User UUID: ");
                    String userUUID = new Scanner(System.in).nextLine();
                    APIResponse<UserResponseDto> getUser = userController.getUserById(userUUID);
                    System.out.println(getUser.message());
                    if (getUser.data() != null) {
                        DataConnectionConfigure.getConnection();

                    }
                }
                case 3 -> {
                    System.out.print("Enter name to search: ");
                    String name = new Scanner(System.in).nextLine();
                    APIResponse<List<UserResponseDto>> users = userController.searchUserByName(name);
                    System.out.println(users.message());
                    DataConnectionConfigure.getConnection();

                }
                case 4 -> {
                    System.out.print("Enter User UUID to delete: ");
                    String uuid = new Scanner(System.in).nextLine();
                    APIResponse<Integer> deleteUser = userController.deleteUserByUuid(uuid);
                    System.out.println(deleteUser.message());
                }
                case 5 -> {
                    System.out.print("Enter User UUID to update: ");
                    String uuid = new Scanner(System.in).nextLine();
                    System.out.print("[+] Input new name: ");
                    String name = new Scanner(System.in).nextLine();
                    System.out.print("[+] Input  new email: ");
                    String email = new Scanner(System.in).nextLine();
                    System.out.print("[+] Input  new password: ");
                    String password = new  Scanner(System.in).nextLine();
                    System.out.print("[+] Input  new profile url: ");
                    String profile = new Scanner(System.in).nextLine();

                    UpdateRequestDto updateRequestDto = new UpdateRequestDto(name, email, password, profile);
                    APIResponse<UserResponseDto> updateUser = userController.updateUserByUuid(uuid, updateRequestDto);
                    System.out.println(updateUser.message());
                    if (updateUser.data() != null) {
                        DataConnectionConfigure.getConnection();

                    }
                }
                case 6-> {
                    APIResponse<List<UserResponseDto>> allUsers = userController.getAllUser();
                    displayUsers();

                }
                case 7-> {
                    System.out.println("System closed ");
                    try {
                        System.exit(0);
                    }catch (Exception ignore){}
                }
                default -> System.out.println("invalid input");
            }
        }
    }
}
