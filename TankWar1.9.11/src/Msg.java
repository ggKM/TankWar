import java.io.DataInputStream;
import java.net.DatagramSocket;
/**
 * 
 * @author gg
 *
 */
public interface Msg {
	/**
	 * ̹�˲�������Ϣ
	 */
	public static final int TANK_NEW_MSG = 1;
	
	/**
	 * ̹���ƶ�����Ϣ
	 */
	public static final int TANK_MOVE_MSG = 2;
	
	/**
	 * �ӵ���������Ϣ
	 */
	public static final int MISSILE_NEW_MSG = 3;
	
	/**
	 * ̹����������Ϣ
	 */
	public static final int TANK_DEAD_MSG = 4;
	
	/**
	 * �ӵ���������Ϣ
	 */
	public static final int MISSILE_DEAD_MSG = 5;
	
	/**
	 * ��������
	 * @param ds
	 * @param IP
	 * @param udpPort
	 */
	public void send(DatagramSocket ds, String IP, int udpPort);
	
	/**
	 * ���ղ���������
	 * @param dis
	 */
	public void parse(DataInputStream dis);
}
