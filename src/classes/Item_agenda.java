package classes;

public class Item_agenda {
	
	public String numero_tel;
	public String nombre_puesto;
	public String nombre_encargado;
	public String horario;
	public int avatar;
	
	public Item_agenda(String _tel, String _puesto, String _encargado,String _horario, int _avatar){
		this.numero_tel = _tel;
		this.nombre_puesto = _puesto;
		this.nombre_encargado = _encargado;
		this.horario = _horario;
		this.avatar = _avatar;
	}
	
	public int getAvatar() {
		return avatar;
	}

	public void setAvatar(int avatar) {
		this.avatar = avatar;
	}
	
	public String getNumero_tel() {
		return numero_tel;
	}
	public void setNumero_tel(String numero_tel) {
		this.numero_tel = numero_tel;
	}
	public String getNombre_puesto() {
		return nombre_puesto;
	}
	public void setNombre_puesto(String nombre_puesto) {
		this.nombre_puesto = nombre_puesto;
	}
	public String getNombre_encargado() {
		return nombre_encargado;
	}
	public void setNombre_encargado(String nombre_encargado) {
		this.nombre_encargado = nombre_encargado;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String _horario) {
		this.horario = _horario;
	}
	
	
	
	

}
