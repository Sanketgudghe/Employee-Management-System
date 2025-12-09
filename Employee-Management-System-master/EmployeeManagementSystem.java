/******************** Importing Essential Libraries ************************/
import java.util.*;
import java.io.*;

/*************************** MENU OF EMS ****************************/
class MainMenu {
    public void menu() {
        System.out.println("\t\t*******************************************");
        System.out.println("\t\t\t  EMPLOYEE MANAGEMENT SYSTEM");
        System.out.println("\t\t*******************************************");
        System.out.println("\t\t\t    --------------------");
        System.out.println("\t\t\t     ~ Developed in Java");
        System.out.println("\t\t\t    --------------------");
        System.out.println("\n\nPress 1 : To Add an Employee Details");
        System.out.println("Press 2 : To See an Employee Details");
        System.out.println("Press 3 : To Remove an Employee");
        System.out.println("Press 4 : To Update Employee Details");
        System.out.println("Press 5 : To Exit the EMS Portal");
    }
}

/************************* Taking Employee Details ************************/
class EmployeeDetail {
    String name;
    String fatherName;
    String email;
    String position;
    String employeeId;
    String salary;
    String contact;

    public void getInfo(Scanner sc) {
        System.out.print("Enter Employee's name --------: ");
        name = sc.nextLine();
        System.out.print("Enter Employee's Father name -: ");
        fatherName = sc.nextLine();
        System.out.print("Enter Employee's ID ----------: ");
        employeeId = sc.nextLine();
        System.out.print("Enter Employee's Email ID ----: ");
        email = sc.nextLine();
        System.out.print("Enter Employee's Position ----: ");
        position = sc.nextLine();
        System.out.print("Enter Employee contact Info --: ");
        contact = sc.nextLine();
        System.out.print("Enter Employee's Salary ------: ");
        salary = sc.nextLine();
    }
}

/************************ To add details of Employee *********************/
class EmployeeAdd {
    public void createFile(Scanner sc) {
        EmployeeDetail emp = new EmployeeDetail();
        emp.getInfo(sc);

        try {
            File f1 = new File("file" + emp.employeeId + ".txt");
            if (f1.createNewFile()) {
                try (FileWriter myWriter = new FileWriter(f1)) {
                    myWriter.write("Employee ID      : " + emp.employeeId + "\n" +
                                   "Employee Name    : " + emp.name + "\n" +
                                   "Father's Name    : " + emp.fatherName + "\n" +
                                   "Contact          : " + emp.contact + "\n" +
                                   "Email            : " + emp.email + "\n" +
                                   "Position         : " + emp.position + "\n" +
                                   "Salary           : " + emp.salary);
                }
                System.out.println("\nEmployee has been Added :)\n");
            } else {
                System.out.println("\nEmployee already exists :(");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

/************************ To Show details of Employee *********************/
class EmployeeShow {
    public void viewFile(String id) throws Exception {
        File file = new File("file" + id + ".txt");
        if (!file.exists()) {
            System.out.println("\nEmployee not found!");
            return;
        }

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
        }
    }
}

/***************************** To Remove Employee *************************/
class EmployeeRemove {
    public void removeFile(String id) {
        File file = new File("file" + id + ".txt");
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("\nEmployee has been removed Successfully");
            } else {
                System.out.println("\nError while removing employee!");
            }
        } else {
            System.out.println("\nEmployee does not exist :( ");
        }
    }
}

/************************ To Update details of Employee ********************/
class EmployeeUpdate {
    public void updateFile(String id, String oldData, String newData) throws IOException {
        File file = new File("file" + id + ".txt");
        if (!file.exists()) {
            System.out.println("\nEmployee not found!");
            return;
        }

        StringBuilder fileContent = new StringBuilder();
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                fileContent.append(sc.nextLine()).append("\n");
            }
        }

        String updatedContent = fileContent.toString().replace(oldData, newData);

        try (FileWriter myWriter = new FileWriter(file)) {
            myWriter.write(updatedContent);
        }

        System.out.println("\nEmployee details updated successfully!");
    }
}

/************************ To Exit from the EMS Portal *********************/
class CodeExit {
    public void out() {
        System.out.println("\n*****************************************");
        System.out.println(" Thank You For Using Employee Management System :) ");
        System.out.println("*****************************************");
        System.exit(0);
    }
}

/***************************** Main Class *******************************/
public class EmployeeManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EmployeeShow epv = new EmployeeShow();
        MainMenu menu = new MainMenu();

        int choice = 0;
        while (true) {
            menu.menu();
            System.out.print("\nPlease Enter choice : ");
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input, please enter a number!");
                continue;
            }

            switch (choice) {
                case 1:
                    new EmployeeAdd().createFile(sc);
                    break;
                case 2:
                    System.out.print("\nEnter Employee ID : ");
                    String idToShow = sc.nextLine();
                    try {
                        epv.viewFile(idToShow);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 3:
                    System.out.print("\nEnter Employee ID : ");
                    String idToRemove = sc.nextLine();
                    new EmployeeRemove().removeFile(idToRemove);
                    break;
                case 4:
                    System.out.print("\nEnter Employee ID : ");
                    String idToUpdate = sc.nextLine();
                    try {
                        epv.viewFile(idToUpdate);
                        System.out.print("\nEnter text to update: ");
                        String oldData = sc.nextLine();
                        System.out.print("Enter new text: ");
                        String newData = sc.nextLine();
                        new EmployeeUpdate().updateFile(idToUpdate, oldData, newData);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 5:
                    new CodeExit().out();
                    break;
                default:
                    System.out.println("\nInvalid Choice! Please enter 1-5.");
            }

            System.out.print("\nPress Enter to Continue...");
            sc.nextLine();
        }
    }
}
