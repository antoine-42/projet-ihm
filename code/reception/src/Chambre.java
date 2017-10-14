public class Chambre {
	int number;
	int type;
	Boolean occupied;
	Boolean clean;

	public Chambre(int number_, int type_, boolean occupied_, boolean clean_){
        this.number = number_;
        this.type = type_;
		this.occupied = occupied_;
		this.clean = clean_;
	}

    String[] getInfo(){
        return new String[]{
                String.valueOf(this.number),
                TypeChambre.TYPE[this.type]
        };
    }
}