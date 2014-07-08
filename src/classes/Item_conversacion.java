package classes;

public class Item_conversacion {
	
	String mje;
	String fecha;
	String from;
	
	public Item_conversacion(String _mje, String _fecha,String _from){
		this.mje = _mje;
		this.fecha  = _fecha;
		this.from = _from;
	}
	public String getMje() {
		return mje;
	}
	public void setMje(String mje) {
		this.mje = mje;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	
	
	
	
	
}
