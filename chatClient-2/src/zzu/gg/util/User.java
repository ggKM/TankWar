package zzu.gg.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class User {

	private String name;
	private String myIP;
	 
    private int IPPort =6666;    
  
	
    public User(){
    	try {
    		name = InetAddress.getLocalHost().getHostName();
			myIP = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public User(String ip){
    	this.myIP = ip;
    }
    public boolean equals(Object u){
    	return ((User)u).getIP().equals( this.myIP);
    }
    public User(String ip,String name,int port){
    	myIP = ip;
    	this.name = name;
    	this.IPPort = port;
    }
	public String getName(){
		return this.name;
	}
	public String getIP(){
		return this.myIP;
	}
	public int getIPPort(){
		return this.IPPort;
	}
}
