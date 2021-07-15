package bootcamp.meli.desafioquality.dto;

public class RoomDTO {

    private String propertyName;
    private String roomName;
    private double area;

    public RoomDTO() {
    }

    public RoomDTO(String propertyName, String roomName, double area) {
        this.propertyName = propertyName;
        this.roomName = roomName;
        this.area = area;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
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
