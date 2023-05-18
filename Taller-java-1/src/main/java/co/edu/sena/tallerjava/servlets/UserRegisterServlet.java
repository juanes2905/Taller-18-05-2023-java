package co.edu.sena.tallerjava.servlets;
import co.edu.sena.tallerjava.model.User;
import co.edu.sena.tallerjava.model.repository.Repository;
import co.edu.sena.tallerjava.model.repository.UserRepositoryImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/registerUser")
public class UserRegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);

        // collect all from data
        String user_firstname = req.getParameter("user_firstname");
        String user_lastname = req.getParameter("user_lastname");
        String user_email = req.getParameter("user_email");
        String user_password = req.getParameter("user_password");
        // fill it up a User bean
        User user = new User();
        user.setUser_firstname(user_firstname);
        user.setUser_lastname(user_lastname);
        user.setUser_email(user_email);
        user.setUser_password(user_password);

        // call repository layer and save the user object to DB
        Repository<User> repository = new UserRepositoryImpl();
        int rows = 0;

        try {
            repository.saveObj(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // todo prepare an information message for user about success or failure of the operation
        if (rows ==0){
            System.out.println("Ocurrió un error");
        }else{
            System.out.println("Registro exitoso");
        }
        // todo write the message back to the page in client browser
    }
}
