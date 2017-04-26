package com.noisywean.wificlients;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import com.noisywean.wificlients.IpDAO;

 
public class GetIP {
	
	  
	public printOutput getStreamWrapper(InputStream is, String type) {
		return new printOutput(is, type);
	}
 
	public static void main(String[] args) {
 
		Runtime rt = Runtime.getRuntime();
		GetIP rte = new GetIP();
		printOutput errorReported, outputMessage;
	    
		try {
			Process proc = rt.exec("arp -a -N 192.168.137.1");
			// Process proc = rt.exec("mkdir /Users/<username>/Desktop/test1");
			// Process proc = rt.exec("ping http://crunchify.com");
			errorReported = rte.getStreamWrapper(proc.getErrorStream(), "ERROR");
			outputMessage = rte.getStreamWrapper(proc.getInputStream(), "OUTPUT");
			errorReported.start();
			outputMessage.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
 
	private class printOutput extends Thread {
		InputStream is = null;
		IpDAO ip = new IpDAO();
		printOutput(InputStream is, String type) {
			this.is = is;
		}
 
		public void run() {
			String s = null;
			String sub[] = {null};
	        
			try {
				BufferedReader br = new BufferedReader(
						new InputStreamReader(is));
				while ((s = br.readLine()) != null) {
					if(s.contains("Interface") || s.contains("Internet Address"))
						continue;
					if(s.contains("ff-ff-ff-ff-ff-ff"))
						break;
					if(s.length() != 0)
					{   sub = s.split("\\s+");
						System.out.println("");	
						System.out.print(sub[1]);
						System.out.print("  ");	
						System.out.println(sub[2]);
						ip.NewEntry(sub[1],sub[2]);
					}
					
									
					
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
}