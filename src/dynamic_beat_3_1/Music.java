package dynamic_beat_3_1;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Music extends Thread 
		{
		private Player player;
		private boolean isLoop; 	//���ѹݺ����� �ѹ��� ������������� ����
		private File file; 
		private FileInputStream fis;
		private BufferedInputStream bis;
		
		public Music(String name, boolean isLoop) 
		{
			try 
			{
				this.isLoop=isLoop;
				file = new File(Main.class.getResource("../Music/"+ name).toURI());
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
			}
				catch (Exception e) 
				{
					System.out.println(e.getMessage());
				}
		}
		
		public int getTime() 
		{ // ��� ��ġ�� �˷��ִ� �޼ҵ�
			if (player == null)
				return 0;
			return player.getPosition();
		}
		
		public void close() 	//������ �׻� �����Ҽ��ְԲ� �ϴ� �޼ҵ� 
		{
			isLoop = false;
			player.close();
			this.interrupt();
		}
		
		@Override
		public void run() 
		{
			try 
			{
				do 
				{
				player.play();
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
				} while(isLoop);
			}
			catch (Exception e) 
			{
				System.out.println(e.getMessage());
			}
		}
}
