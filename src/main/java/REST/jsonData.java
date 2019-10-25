package REST;

public class jsonData {

	String name;
	int qty;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	@Override
	public String toString() {
		return "jsonData [name=" + name + ", qty=" + qty + "]";
	}

}