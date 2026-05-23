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
import java.util.UUID;

public class UI {
    private  final  static UserController userController = new UserController();


    public static void displayUsers(List<APIResponse<List<UserResponseDto>>> users) {
//        if (users == null || users.data() == null) {
//            System.out.println("No users found.");
//            return;
//        }
        Table table = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER);
        table.addCell("id");
        table.addCell("uuid");
        table.addCell("name");
        table.addCell("email");
        table.addCell("profile");

        for (APIResponse<List<UserResponseDto>> user : users) {
             for (UserResponseDto uu : user.data()) {
                table.addCell(uu.id().toString(), new CellStyle(CellStyle.HorizontalAlign.center));
                table.addCell(uu.uuid(), new CellStyle(CellStyle.HorizontalAlign.center));
                table.addCell(uu.name(), new CellStyle(CellStyle.HorizontalAlign.center));
                table.addCell(uu.email(), new CellStyle(CellStyle.HorizontalAlign.center));

                table.addCell(uu.profile(), new CellStyle(CellStyle.HorizontalAlign.center));
            }

        }

        System.out.println(table.render());
    }
    private static void thumbnail(){
        System.out.println("""
                ==================== User Management System ========================
                1. Create User
                
                2. Search User by name
                3. Delete User by ID
                4. Update User by UUID
                5. List all Users
                6. Exit
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
                    System.out.print("[+] Insert id: ");
                    Integer id = new Scanner(System.in).nextInt();
                    System.out.print("[+] Insert name: ");
                    String name = new Scanner(System.in).nextLine();
                    System.out.print("[+] Insert email: ");
                    String email = new Scanner(System.in).nextLine();
                    System.out.print("[+] insert password: ");
                    String password = new  Scanner(System.in).nextLine();
                    System.out.print("[+] insert profile: ");
                    String profile = new  Scanner(System.in).nextLine();
//
                    CreateUserDto createUserDto = CreateUserDto.builder()
                            .id(id)
                            .uuid(UUID.randomUUID().toString())
                            .name(name)
                            .email(email)
                            .password(password)
                            .profile(profile)
                            .build();
                    APIResponse<UserResponseDto> createUser = userController.createUser(createUserDto);
                    System.out.println(createUser.data());

//                   displayUsers();

                }

                case 2 -> {
                    System.out.print("Enter name to search: ");
                    String name = new Scanner(System.in).nextLine();
                    APIResponse<List<UserResponseDto>> users = userController.searchUserByName(name);
                    displayUsers(List.of(users));


                }
                case 3 -> {
                    System.out.print("Enter User ID to delete: ");
                 int id = new Scanner(System.in).nextInt();
                    APIResponse<Integer> deleteUser = userController.deleteUserByid(id);
                    System.out.println(deleteUser.message());
                }
                case 4 -> {
                    System.out.print("Enter User ID to update: ");
                  int  id = new Scanner(System.in).nextInt();
                    System.out.print("[+] Input new name: ");
                    String name = new Scanner(System.in).nextLine();
                    System.out.print("[+] Input  new email: ");
                    String email = new Scanner(System.in).nextLine();
                    System.out.print("[+] Input  new password: ");
                    String password = new  Scanner(System.in).nextLine();
                    System.out.print("[+] Input  new profile url: ");
                    String profile = new Scanner(System.in).nextLine();

                    UpdateRequestDto updateRequestDto = new UpdateRequestDto(name, email, password, profile);
                    APIResponse<UserResponseDto> updateUser = userController.updateUserByUuid(id, updateRequestDto);
                    System.out.println(updateUser.message());

                }
                case 5-> {
                    APIResponse<List<UserResponseDto>> allUsers = userController.getAllUser();
                    displayUsers(List.of(allUsers));

                }
                case 6-> {
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
