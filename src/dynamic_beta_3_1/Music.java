package dynamic_beta_3_1;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Music extends Thread 
		{
		private Player player;
		private boolean isLoop; 	//무한반복인지 한번만 재생인지에대한 설정
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
		{ // 재생 위치를 알려주는 메소드
			if (player == null)
				return 0;
			return player.getPosition();
		}
		
		public void close() 	//음악을 항상 종료할수있게끔 하는 메소드 
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
