
public class ToDoList {
	
	String date, activity;

	public ToDoList(String date, String activity) {
		super();
		this.date = date;
		this.activity = activity;
	}

	protected String getDate() {
		return date;
	}

	protected void setDate(String date) {
		this.date = date;
	}

	protected String getActivity() {
		return activity;
	}

	protected void setActivity(String activity) {
		this.activity = activity;
	}
}
