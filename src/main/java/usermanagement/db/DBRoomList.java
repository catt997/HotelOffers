package usermanagement.db;

import usermanagement.RoomList;
import usermanagement.MyRooms;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DBRoomList {

    public boolean newReservation(MyRooms u) {

        System.out.println(u);

        boolean isInserted=false;
        try {
            // 1. ma conectez la db
            final String URL = "jdbc:postgresql://localhost:5432/postgres";
            final String USERNAME = "admin";
            final String PASSWORD = "admin123";

            Class.forName("org.postgresql.Driver");

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // 2. creez un prepared ststement si il populez cu date
            PreparedStatement pSt = conn.prepareStatement("INSERT INTO myrooms (roomType,dateIn, dateOut, pricepernight, iduser) VALUES(?,?,?,?,?)");
            pSt.setString(1,u.getRoomType());

            Date date = Date.valueOf(u.getStartDate());
            pSt.setDate(2, date);

            Date date1 = Date.valueOf(u.getEndDate());
            pSt.setDate(3, date1);

            pSt.setFloat(4, u.getPricePerNight());

            pSt.setInt(5, u.getIduser());

            // 3. executie
            int insert = pSt.executeUpdate();
            if(insert!=-1)
                isInserted=true;
            System.out.println(isInserted);

            pSt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            isInserted=false;

        }

        return isInserted;
    }

    public List<RoomList> getRoomList(int idUser, String search) {

        RoomList mfl =null;
        List<RoomList> list = new ArrayList<>();
        // 1. ma conectez la db
        final String URL = "jdbc:postgresql://localhost:5432/postgres";
        final String USERNAME = "admin";
        final String PASSWORD = "admin123";
        int id =-1;
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // 2. fac un query pe o tabela , intai creez obiectul

            PreparedStatement pSt = conn.prepareStatement("select * from myrooms where iduser=? and roomtype like CONCAT( '%',?,'%') ORDER BY datein desc");

            pSt.setInt(1, idUser);
            pSt.setString(2, search);

            // 3. executie
            ResultSet rs = pSt.executeQuery();

            // atata timp cat am randuri
            while (rs.next()) {

                mfl = new RoomList();
                mfl.setId(rs.getInt("id"));
                mfl.setRoomType(rs.getString("roomtype"));

                Date dateInFromDB = rs.getDate("datein");
                LocalDate localDateIn = dateInFromDB.toLocalDate();

                Date dateOutFromDB = rs.getDate("dateout");
                LocalDate localDateOut = dateOutFromDB.toLocalDate();


                mfl.setPricePerNight(rs.getInt("pricepernight"));
                mfl.setDateIn(localDateIn);
                mfl.setDateOut(localDateOut);

                list.add(mfl);

            }

            rs.close();
            pSt.close();
            conn.close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static void main(String[] args) {

        DBRoomList db = new DBRoomList();

        List<RoomList> l = db.getRoomList(1,"");

        for(int i = 0;i<l.size();i++) {

            RoomList mfl = (RoomList) l.get(i);

            System.out.println(mfl.toString()); // just to test we get the right data from db
        }

    }
}
