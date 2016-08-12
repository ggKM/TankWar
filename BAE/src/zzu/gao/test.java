package zzu.gao;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.constant.Constant;
import com.example.multispeed.ThreadApp;
import com.gao.util.Stopper;


/**
 * Servlet implementation class test
 */

public class test extends HttpServlet {
	private static final long serialVersionUID = 1L;
   // ThreadOne t = null;
    ThreadApp ta = null;
    Operator oper = null;
    Thread t = null;
    int count ;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public test() {
        super();
       // System.out.println("test");
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		//System.out.println("init");
		//t = new ThreadOne();
		//new Thread(t).start();
//		ta = new ThreadApp();
//		ta.run();
//		oper = new Operator();
//		t = new Thread(oper);
//		t.start();
//		count = 1;
		/*try {
			App.run();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		//t.close();
		Stopper.stopThread();
		oper.stop();
		//System.out.println("destory");
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("service");
		doGet(request,response);
		//doPost(request,response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("doget");
		/*int[] speed = t.getSpeed();
		StringBuffer data = new StringBuffer();
		for(int i =0;i<speed.length;i++){
			data.append((String.valueOf(speed[i])));
			data.append("\n");
		}
		data.deleteCharAt(data.length()-1);*/
//		StringBuffer data = new StringBuffer();
//		this.count %= 4;
//		this.count ++;
		/*data.append(this.count);
		data.append("\n");
		double[] d = null ;
		switch(this.count){
		case 1:d = Constant.MAPDEMO.arrayY_B;break;
		case 2:d = Constant.MAPDEMO.arrayY_A;break;
		case 3:d = Constant.MAPDEMO.arrayX_A;break;
		case 4:d = Constant.MAPDEMO.arrayX_B;break;
		default:break;
		}*/
//		double[] d = oper.getData(this.count);
//		for(int i =0;i<d.length;i++){
//			data.append((String.valueOf(d[i])));
//			data.append("\n");
//		}
//		data.deleteCharAt(data.length()-1);
		
		PrintWriter out = response.getWriter();
		out.println("busonline");
//		out.println(data);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("dopost");
		doGet(request,response);
		
	}

}
