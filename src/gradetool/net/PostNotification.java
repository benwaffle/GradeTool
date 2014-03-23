package gradetool.net;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;

public class PostNotification {
	public static void main(String[] args) {
		addToTwilioQueue("phone", "body", new Date(123456));
	}
	
	public static void addToTwilioQueue(String phone, String body, Date when){
		try {
			URLConnection conn = new URL("http://requestb.in/vzcb63vz").openConnection();
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			
			String query = String.format("notif_due=%s&notif_phone=%s&notif_body=%s", 
					URLEncoder.encode(when.toString(), "UTF-8"),
					URLEncoder.encode(phone, "UTF-8"),
					URLEncoder.encode(body, "UTF-8")
					);
			
			OutputStream os = conn.getOutputStream();
			os.write(query.getBytes("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
