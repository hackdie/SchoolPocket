package classes;

public class AlumnosMje_Item {

	private String no_de_control;
	private String Nombre;
	private boolean checked;
	
	
	public AlumnosMje_Item(String no_control, String nombre, boolean selected) {
		super();
		this.no_de_control = no_control;
		this.Nombre = nombre;
		this.checked = selected;
	 }
	
	
	public String getNo_de_control() {
		return no_de_control;
	}
	public void setNo_de_control(String no_de_control) {
		this.no_de_control = no_de_control;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	} 
	
}
