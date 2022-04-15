package usermanagement;

import java.time.LocalDate;

public class MyRooms {

    private int id ;
    private String roomType;


    @Override
    public String toString() {
        return "MyRooms{" +
                "id=" + id +
                ", roomType='" + roomType + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", pricePerNight=" + pricePerNight +
                ", iduser=" + iduser +
                '}';
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    private LocalDate startDate;
    private LocalDate endDate;
    private float pricePerNight;

    private int iduser;

    public MyRooms(String roomType, LocalDate startDate, LocalDate endDate, float pricePerNight, int iduser) {
        this.roomType = roomType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.pricePerNight = pricePerNight;
        this.iduser = iduser;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public float getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(float pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public MyRooms() {
    }

   }
