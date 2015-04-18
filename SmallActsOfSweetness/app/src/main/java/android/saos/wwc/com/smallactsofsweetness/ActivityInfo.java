package android.saos.wwc.com.smallactsofsweetness;

public class ActivityInfo {
	enum Type {
		DONATION,
		REDEEM,
		MILESTONE
	}

	private Type type;
	private String title;
	private String message;
	private long timestamp;

	public ActivityInfo(Type type, String title, String message, long timestamp) {
		this.type = type;
		this.title = title;
		this.message = message;
		this.timestamp = timestamp;
	}

	public Type getType() {
		return type;
	}

	public String getTitle() {
		return title;
	}

	public String getMessage() {
		return message;
	}

	public long getTimestamp() {
		return timestamp;
	}
}
