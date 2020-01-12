package original.util;

import java.io.*;
import java.util.*;
import java.net.*;
import java.sql.*;

public class ConnectionUtil{
	private static Proxy proxy
		= new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 8080));
	private static String proxySwitch = "0";
	
	public static String callGet(String strGetUrl){
		HttpURLConnection con = null;
		StringBuffer result = new StringBuffer();
		
		try{
			URL url = new URL(strGetUrl);
			if(proxySwitch.equals("1")){
				con = (HttpURLConnection)url.openConnection(proxy);
			}
			else{
				con = (HttpURLConnection)url.openConnection();
			}
			con.setRequestMethod("GET");
			con.connect();

			// HTTP Responce
			final int status = con.getResponseCode();
			if(status == HttpURLConnection.HTTP_OK){

				final InputStream in = con.getInputStream();
				String encoding = con.getContentEncoding();
				if(null == encoding){
					encoding = "UTF-8";
				}
				final InputStreamReader inReader = new InputStreamReader(in, encoding);
				final BufferedReader bufReader = new BufferedReader(inReader);
				String line = null;

				while((line = bufReader.readLine()) != null){
					result.append(line);
				}
				bufReader.close();
				inReader.close();
				in.close();
			}
			else{
				System.out.println(status);
			}
		}
		catch(Exception e1){
			e1.printStackTrace();
		}
		finally{
			if(con != null)	con.disconnect();
		}
		System.out.println("result=" + result.toString()); 
		return result.toString();
	}

	public String callPost(String strPostUrl, String JSON){
		StringBuffer result = new StringBuffer();
		OutputStreamWriter out;
		HttpURLConnection con = null;
		
		try{
	  	URL url = new URL(strPostUrl);
			con = (HttpURLConnection)url.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("POST");
			con.setRequestProperty("Accept-Language", "jp");
			con.setRequestProperty("Content-Type", "application/JSON; charset=utf-8");
			con.setRequestProperty("Content-Length", String.valueOf(JSON.length()));

			out = new OutputStreamWriter(con.getOutputStream());
			out.write(JSON);
			out.flush();
			con.connect();

			final int status = con.getResponseCode();	
			if(status == HttpURLConnection.HTTP_OK){	// Connection AC
				final InputStream in = con.getInputStream();
				String encoding = con.getContentEncoding();
				if(null == encoding){
					encoding = "UTF-8";
				}
				final InputStreamReader inReader = new InputStreamReader(in, encoding);
				final BufferedReader bufReader = new BufferedReader(inReader);

				String line = null;
				while((line = bufReader.readLine()) != null){
					result.append(line);
				}
				bufReader.close();
				inReader.close();
				in.close();
			}
			else{	// Connection failed
				System.out.println(status);
			}
		}
		catch(Exception e1){
			e1.printStackTrace();
		}
		finally{
			if(con != null){
				con.disconnect();
			}
		}
		return result.toString();
	}
}

