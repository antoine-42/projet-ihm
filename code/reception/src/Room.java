public class Room {
	int number;
	int type;
	Boolean occupied;
	Boolean clean;
	String description;

	Room(int number_, int type_, boolean occupied_, boolean clean_, String description_){
        this.number = number_;
        this.type = type_;
		this.occupied = occupied_;
		this.clean = clean_;
		this.description = description_;
	}
    Room(int number_, int type_, boolean occupied_, boolean clean_){
        this.number = number_;
        this.type = type_;
        this.occupied = occupied_;
        this.clean = clean_;
    }

    String[] getInfo(){
        return new String[]{
                String.valueOf(this.number),
                RoomType.TYPE[this.type]
        };
    }
}