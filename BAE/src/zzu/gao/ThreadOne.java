package zzu.gao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ThreadOne implements Runnable {

	private boolean flag = true;
	private int[] speed = new int[24];
	private int times = 0;
	private String ret = null;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int i=0;
		while(flag){
			i %= 5;
		
			speed[0] = ++i;//speed[0] 1-5
			for(int count =1;count<speed.length;count++){
				speed[count] = (int) (Math.random()*100);
			}
			//internet();
			//System.out.println(++i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void close(){
		this.flag = false;
	}

	public int[] getSpeed(){
		return this.speed;
	}
	public String getStr(){
		return this.ret;
	}
	
	private void internet(){
		URL url = null;
		try {
			url = new URL("http://sh.qihoo.com/");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//一行一个数
		URLConnection con = null;
		try {
			con = (URLConnection) url.openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		String line = null; 
		
		try {
			++ this.times;
			line = br.readLine();
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.ret = this.times+line;
		
	}
}
