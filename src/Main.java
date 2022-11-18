import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;

public class Main {
    public static void main(String[] args) {

        try{
            // Establish connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/itiwasabat?" + "user=root&password=admin");
            Employee e = new Employee(conn);

            // Add employee
            e.add("Abdalrashid",011506);
            System.out.println("Added succefully");

            // Get all employees
            ResultSet employees = e.getAll(20,0);
            while(employees.next()){
                System.out.println("NAME: " +
                        employees.getString("name")
                        + "Phone number: "
                        + employees.getInt("phone"));
            }

            // Get employee ID for update
            ResultSet rs = e.getByName("Abdalrashid");
            rs.next();
            Integer ID = rs.getInt("id");

            // Update Employee
            e.update(ID,"Shido",663322);
            System.out.println(" Updated successfully");

            // Delete Employee
            e.delete(ID);
            System.out.println(" Deleted successfully");

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}