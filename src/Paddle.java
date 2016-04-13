import java.awt.Color;
import java.awt.Graphics;

/**
 * 
 * @author ChrisParisi
 *
 *This class will be responsible for creating the
 *paddles on the frame. They will be rectangles
 *and have a location.
 */
public class Paddle {

	private double x, y;
	private int width = 20, height = 100;
	//determines AI player with integer 0 or 1
	public int playerType;
	public int jump = 5;

	/**
	 * This will create a paddle at the location set
	 * in the Game constructor which will call this
	 * constructor. A paddle will be created on the left
	 * and controlled by the user and a paddle will be
	 * created on the right and controlled by the 
	 * computer.
	 * @param someX
	 * @param someY
	 */
	public Paddle(int someX, int someY, int type){
		playerType = type;
		x = someX;
		y = someY;


	}

	public void paint(Graphics p){

		if(playerType == 0){
			p.setColor(Color.BLUE);
			p.fillRect((int)x, (int)y, (int)width, (int)height);
			p.setColor(Color.white);
			p.drawRect((int)x, (int)y, (int)width+1, (int)height+1);
		}

		if(playerType == 1){
			p.setColor(Color.RED);
			p.fillRect((int)x, (int)y, (int)width, (int)height);
			p.setColor(Color.white);
			p.drawRect((int)x, (int)y, (int)width+1, (int)height+1);
		}


	}

	public int getX(){
		return  (int)x + width;
	}

	public void moveUp(){

		y = y - jump;

	}

	public void moveDown(){

		y = y + jump;
	}
	/**
	 * This method will return the paddle
	 * location of whichever paddle is requested
	 * by the client.
	 * @return
	 */
	public int getLocation(){
		return 0;

	}

	/**
	 * This method will be in charge of controlling the 
	 * robot opponent. The AI opponents paddle will
	 * follow the ball along its y axis but with a delay.
	 * The faster the ball goes the easier it is to score
	 * on the AI opponent.
	 */
	public void AI(){

	}

	public int getY() {
		// TODO Auto-generated method stub
		return (int) y;
	}

	public void setY(int i) {
		// TODO Auto-generated method stub
		y= i;
	}

	public void setYai(int y2) {
		// TODO Auto-generated method stub
		
			if(y < y2 && y+height < 370){
			try {
				Thread.sleep(5);                 
			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			y = y +10;
			}
			if(y > y2 && y>0){
				try {
					Thread.sleep(5);                 
				} catch(InterruptedException ex) {
					Thread.currentThread().interrupt();
				}
				y = y -10;
				
			}
		
	}

	public int getHeight() {
		// TODO Auto-generated method stub
		return height;
	}

	public void takeNotice(){

	}
}
