package bootcamp.meli.desafioquality.dto;

public class RoomAreaDTO {
    private String roomName;
    private double area;

    public RoomAreaDTO() {
    }

    public RoomAreaDTO(String roomName, double area) {
        this.roomName = roomName;
        this.area = area;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }
}
