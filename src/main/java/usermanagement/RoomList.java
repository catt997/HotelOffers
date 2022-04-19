package usermanagement;

import java.time.LocalDate;

public class RoomList {

    private int id ;
    private String roomType;

    public float getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(float pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    private float pricePerNight;

    @Override
    public String toString() {
        return "MyRooms{" +
                "id=" + id +
                ", roomType='" + roomType + '\'' +
                ", roomDateIn=" + dateIn +
                ", roomDateOut=" + dateOut +
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

    private LocalDate dateIn;
    private LocalDate dateOut;
    private int iduser;

    public RoomList(String roomType, LocalDate dateIn, LocalDate dateOut, float pricePerNight, int iduser) {
        this.roomType = roomType;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.iduser = iduser;
        this.pricePerNight = pricePerNight;
    }

    public RoomList() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public LocalDate getDateOut() {
        return dateOut;
    }

    public void setDateIn(LocalDate dateIn) {
        this.dateIn = dateIn;
    }
    public LocalDate getDateIn() {
        return dateIn;
    }

    public void setDateOut (LocalDate dateOut) {
        this.dateOut = dateOut;
    }

}
