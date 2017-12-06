package com.gxb.web.json;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import net.sf.json.JSONObject;

/**
 * Describe����װjson����
 * @author Wolkin
 *
 */

public class JsonRequest {
	public static final String ADD_URL = null;
	
	public static void init() {
		try {
			//��������
			URL url = new URL(ADD_URL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			//application/x-javascript text/xml->xml���� application/x-javascript->json���� application/x-www-form-urlencoded->������ application/json;charset=utf-8 -> json����
			connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			
			connection.connect();
			
			//POST����
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			JSONObject obj = new JSONObject();
			obj.element("account_name_or_id", "");
			
			out.writeBytes(obj.toString());
			out.flush();
			out.close();
			
			//��ȡ��Ӧ
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String lines;
			StringBuffer sb = new StringBuffer("");
			
			while ((lines = reader.readLine()) != null) {  
                lines = new String(lines.getBytes(), "utf-8");  
                sb.append(lines);  
            }
			
			System.out.println(sb);
			reader.close();
			
			connection.disconnect();
		}catch (MalformedURLException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (UnsupportedEncodingException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }
	}
}
