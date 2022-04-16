package usermanagement;

import org.json.JSONObject;
import usermanagement.db.DBRoomList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/listrooms")
public class ListRoomsServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {

        HttpSession s = req.getSession();
        Object o = s.getAttribute("id"); // daca pe sesiune exista obiectul numit id sau nu exista voi lua diferite decizii

        if(o!=null)
        {

            int iduser = (int)o;
            String search = req.getParameter("search");
            if(search==null)
                search="";

            DBRoomList db = new DBRoomList();
            List<RoomList> l = db.getRoomList(iduser, search);
            JSONObject json = new JSONObject();
            json.put("listFromBackend", l);
            String result = json.toString();
            returnJsonResponse(resp, result);
        }
        else
        {
            error(resp, "Operation forbidden. User is not logged in.");
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