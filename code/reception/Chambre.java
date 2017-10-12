public class Chambre {
	int id;
	int numero;
	Boolean occupe;
	Boolean propre;

	public Chambre(int numero_, boolean occupe_, boolean propre_){
        this.numero = numero_;
		this.occupe = occupe_;
		this.propre = propre_;
	}
}