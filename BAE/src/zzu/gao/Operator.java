package zzu.gao;

import com.example.constant.Constant;

public class Operator implements Runnable {
	private double[] data1;
	private double[] data2;
	private double[] data3;
	private double[] data4;
	private boolean bl_stop = false;

	Operator() {
		this.data1 = new double[Constant.MAPDEMO.arrayY_B.length + 1];
		data1[0] = 1;
		for (int i = 1; i < data1.length; i++) {
			data1[i] = Constant.MAPDEMO.arrayY_B[i - 1];
		}
		
		this.data2 = new double[Constant.MAPDEMO.arrayY_A.length + 1];
		data2[0] = 2;
		for (int i = 1; i < data2.length; i++) {
			data2[i] = Constant.MAPDEMO.arrayY_A[i - 1];
		}
		
		this.data3 = new double[Constant.MAPDEMO.arrayX_A.length + 1];
		data3[0] = 3;
		for (int i = 1; i < data3.length; i++) {
			data3[i] = Constant.MAPDEMO.arrayX_A[i - 1];
		}
		
		this.data4 = new double[Constant.MAPDEMO.arrayX_B.length + 1];
		data4[0] = 4;
		for (int i = 1; i < data4.length; i++) {
			data4[i] = Constant.MAPDEMO.arrayX_B[i - 1];
		}
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (!bl_stop) {
			
			for(int i=1;i<data1.length;i++){
				if(Constant.MAPDEMO.arrayY_B[i-1]!=-1&&Constant.MAPDEMO.arrayY_B[i-1]!=data1[i]){
					data1[i] = Constant.MAPDEMO.arrayY_B[i-1];
				}
			}
			
			for(int i=1;i<data2.length;i++){
				if(Constant.MAPDEMO.arrayY_A[i-1]!=-1&&Constant.MAPDEMO.arrayY_A[i-1]!=data2[i]){
					data2[i] = Constant.MAPDEMO.arrayY_A[i-1];
				}
			}
			
			for(int i=1;i<data3.length;i++){
				if(Constant.MAPDEMO.arrayX_A[i-1]!=-1&&Constant.MAPDEMO.arrayX_A[i-1]!=data3[i]){
					data3[i] = Constant.MAPDEMO.arrayX_A[i-1];
				}
			}
			
			for(int i=1;i<data4.length;i++){
				if(Constant.MAPDEMO.arrayX_B[i-1]!=-1&&Constant.MAPDEMO.arrayX_B[i-1]!=data4[i]){
					data4[i] = Constant.MAPDEMO.arrayX_B[i-1];
				}
			}
			
			
			
			if (this.isValid()) {
				try {
					Thread.sleep(30000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
		}

	}

	public void stop() {
		this.bl_stop = true;
	}

	

	private boolean isValuedData(double[] data) {
		for (int i = 1; i < data.length; i++) {
			if (data[i] != -1 && data[i] != 0) {
				return true;
			}
		}
		return false;
	}

	private boolean isValid() {
		return this.isValuedData(data1) && this.isValuedData(data2)
				&& this.isValuedData(data3) && this.isValuedData(data4);
	}
	
	
		
	
	
	public double[] getData(double head){
		if(data1[0] ==head){
			return data1;
		}
		if(data2[0] ==head){
			return data2;
		}
		if(data3[0] ==head){
			return data3;
		}
		if(data4[0] ==head){
			return data4;
		}
		return data1;
	}
	
}
