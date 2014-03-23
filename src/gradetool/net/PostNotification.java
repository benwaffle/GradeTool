package gradetool.net;

import java.io.*;
import java.net.*;
import java.util.*;

public class PostNotification {
	public static void main(String[] args) {
		addToTwilioQueue("phone", "body", new Date(12));
		System.out.println("Post made.");
	}
	
	public static void addToTwilioQueue(String phone, String body, Date when) {
		try {
			String query = String.format(
				"notif_due=%s&notif_phone=%s&notif_body=%s", 
				URLEncoder.encode(when.toString(), "UTF-8"),
				URLEncoder.encode(phone, "UTF-8"),
				URLEncoder.encode(body, "UTF-8")
			);
			URL url = new URL("http://iofel.me/");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setInstanceFollowRedirects(false);
			con.setRequestProperty("Content-Type", 
				"application/x-www-form-urlencoded;charset=UTF-8");
			con.setRequestProperty("charset", "utf-8");
			con.setRequestProperty("Content-Length",
				Integer.toString(query.getBytes().length));
			con.setUseCaches(false);
			con.connect();
			
			OutputStream os = con.getOutputStream();
			os.write(query.getBytes());
			os.flush();
			os.close();
			
			int resp = con.getResponseCode();
			System.out.println("Response: "+resp);
			con.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
