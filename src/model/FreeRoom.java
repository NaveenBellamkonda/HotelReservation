package model;

public class FreeRoom extends Room{
    public FreeRoom(String roomNumber,Double price,RoomType roomType){
        super();
        this.price= Double.valueOf(0);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
