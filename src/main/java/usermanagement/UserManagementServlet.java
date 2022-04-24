package usermanagement;

import usermanagement.db.DBUser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/userManagement")
public class UserManagementServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        String action = req.getParameter("action"); // name as in the html form, register.html
        System.out.println("action is:" + action);
        boolean succes = false;
        if (action != null && action.equalsIgnoreCase("NEW")) {

            succes= newUser(req, resp);
            if(succes)
            {
                RequestDispatcher rd=req.getRequestDispatcher("login.html");
                try {
                    rd.forward(req, resp);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                error(resp, "There is an error while trying to create this user, please try again!");
            }
            // daca l-a creat cu success du-te la login , altfel ramai aici

        } else if (action != null && action.equalsIgnoreCase("LOGIN")) {
            //afisare
            succes = loginUser(req, resp);  // if isLoggedIn=true atunci intram pe pagina urmatoare cu listMyRooms.jsp
            if(succes) // daca isLoggedIn este false atunci trimit userul inapoi la pagina de login
            {
                RequestDispatcher rd=req.getRequestDispatcher("listMyRooms.jsp");
                try {
                    rd.forward(req, resp);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                RequestDispatcher rd=req.getRequestDispatcher("login.html");
                try {
                    rd.forward(req, resp);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else if (action != null && action.equalsIgnoreCase("OUT")) {
            HttpSession s = req.getSession();
            s.invalidate();
            try {
                resp.sendRedirect("login.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

         else {
            System.out.println("Nu a venit action, deci nu fac nimic");
            error(resp, "error on UI side");
        }

    }

    private boolean newUser(HttpServletRequest req, HttpServletResponse resp) {
        // citesc date de la  browser email , parola1, parola2, accept, vreau oferte

        String email = req.getParameter("email");
        String pwd = req.getParameter("pwd");
        String confirmPwd = req.getParameter("confirmPwd");
        String accepthtml = req.getParameter("accept");
        String offerhtml= req.getParameter("offer");

        System.out.println(pwd+confirmPwd);
        // validari
        if(!pwd.equals(confirmPwd))
        {
           error(resp, "pwd is not the same as confirm password");
           return false;
        }
        if(!accepthtml.equals("YES"))
        {
            error(resp, "you must accept terms and conditions");
            return false;
        }

        boolean accept=false;
        boolean offer=false;
         if (accepthtml != null && accepthtml.equalsIgnoreCase("YES"))
             accept=true;
        if (offerhtml != null && offerhtml.equalsIgnoreCase("YES"))
            offer=true;

        DBUser dbUser = new DBUser();
        User u = new User(email,pwd,confirmPwd, accept, offer);
        boolean inserted = dbUser.newUser(u);


        return inserted;

    }

    private boolean loginUser(HttpServletRequest req, HttpServletResponse resp) {
        User u = null;  //daca user u este null atunci inseamna ca nu s-a creat obiectul pentru ca nu exista in DB combinatia de user+pass
        String email = req.getParameter("email");
        String pwd = req.getParameter("pwd");
        boolean isLoggedIn=false;

        DBUser dbUser = new DBUser();
        u = dbUser.login(email, pwd);
        if (u != null) // daca u e diferit de null inseamna ca am gasit in DB combinatia de user+pass
        {
            HttpSession s = req.getSession();   //
            s.setAttribute("id", u.getId());
            s.setAttribute("email", u.getEmail());
            isLoggedIn=true;
        }
       return isLoggedIn;  //ne ajuta la "LOGIN", daca loginUser=success ajungem pe cealalta pagina listMyRooms.jsp
    }



    private void error( HttpServletResponse resp, String mesaj) {

        try {
            PrintWriter pw = resp.getWriter();
            pw.println(mesaj);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void returnJsonResponse(HttpServletResponse response, String jsonResponse) {
        response.setContentType("application/json");
        PrintWriter pr = null;
        try {
            pr = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert pr != null;
        pr.write(jsonResponse);
        pr.close();
    }

}
