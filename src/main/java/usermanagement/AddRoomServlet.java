package usermanagement;

import usermanagement.db.DBRoomList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet("/addRoom")
public class AddRoomServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {

        HttpSession s = req.getSession();
        Object o = s.getAttribute("id"); // daca pe sesiune exista obiectul numit id sau nu exista voi lua diferite decizii

        float price = 200;


        String roomType = req.getParameter("roomType");

        String dateIN = req.getParameter("dateIn");
        String dateOut = req.getParameter("dateOut");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        //convert String to LocalDate
        LocalDate lDateIn = LocalDate.parse(dateIN, formatter);
        LocalDate lDateOut = LocalDate.parse(dateIN, formatter);

        if(o!=null )
        {
            int iduser = (int)o;

            MyRooms mfl = new MyRooms(roomType, lDateIn, lDateOut, price , iduser);
            DBRoomList db = new DBRoomList();
            db.newReservation(mfl);
        }
        else
        {
            error(resp, "Operation forbidden. User is not logged in or reservation is not arriving to server.");
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

    private void error( HttpServletResponse resp, String mesaj) {

        try {
            PrintWriter pw = resp.getWriter();
            pw.println(mesaj);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}