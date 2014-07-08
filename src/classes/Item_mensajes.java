package classes;

public class Item_mensajes {

	String id_mensaje;
	String ruta_img;
	String mej;
	String autor;
	
	public Item_mensajes(String id, String _ruta_img, String mje, String _autor){
		id_mensaje = id;
		ruta_img = _ruta_img;
		mej = mje;
		autor = _autor;
	}
	
	public String getId_mensaje() {
		return id_mensaje;
	}
	public void setId_mensaje(String id_mensaje) {
		this.id_mensaje = id_mensaje;
	}
	public String getRuta_img() {
		return ruta_img;
	}
	public void setRuta_img(String ruta_img) {
		this.ruta_img = ruta_img;
	}
	public String getMej() {
		return mej;
	}
	public void setMej(String mej) {
		this.mej = mej;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	
}
