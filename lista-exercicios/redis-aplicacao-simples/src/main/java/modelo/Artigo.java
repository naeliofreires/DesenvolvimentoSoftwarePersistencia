package modelo;

public class Artigo {

	private String name;
	private String description;
	private String filename;
	private String posting_date;
	
	public Artigo() {
		// TODO Auto-generated constructor stub
	}

	public Artigo(String name, String description, String filename, String posting_date) {
		super();
		this.name = name;
		this.description = description;
		this.filename = filename;
		this.posting_date = posting_date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getPosting_date() {
		return posting_date;
	}

	public void setPosting_date(String posting_date) {
		this.posting_date = posting_date;
	}
	
}
