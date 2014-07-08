package classes;
public class Item_boleta {
	protected int id;
	protected String Nombre_materia;
	protected String Codigo_carrera;
	protected int Calificacion;
	protected int Creditos;
	protected String Tipo_evaluacion;
	protected String Observaciones;
	
	
	public Item_boleta(){
		this.Nombre_materia ="";
		this.Codigo_carrera="";
		this.Tipo_evaluacion="";
		this.Observaciones="";
	}
	
	public Item_boleta(int _id, String _nombre_materia, String _codigo_carrera,
							int _calificacion,int _creditos, String _tipo_evaluacion, String _observacion){
		
		id = _id;
		Nombre_materia = _nombre_materia;
		Codigo_carrera  = _codigo_carrera;
		Calificacion = _calificacion;
		Creditos = _creditos;
		Tipo_evaluacion = _tipo_evaluacion;
		Observaciones = _observacion;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre_materia() {
		return Nombre_materia;
	}

	public void setNombre_materia(String nombre_materia) {
		Nombre_materia = nombre_materia;
	}

	public String getCodigo_carrera() {
		return Codigo_carrera;
	}

	public void setCodigo_carrera(String codigo_carrera) {
		Codigo_carrera = codigo_carrera;
	}

	public int getCreditos() {
		return Creditos;
	}

	public void setCreditos(int creditos) {
		Creditos = creditos;
	}

	public int getCalificacion() {
		return Calificacion;
	}

	public void setCalificacion(int calificacion) {
		Calificacion = calificacion;
	}

	public String getTipo_Evaluacion() {
		return Tipo_evaluacion;
	}

	public void setTipo_Evaluacion(String tipo_evaluacion) {
		Tipo_evaluacion = tipo_evaluacion;
	}

	public String getObservaciones() {
		return Observaciones;
	}

	public void setObservaciones(String observaciones) {
		Observaciones = observaciones;
	}	
}
