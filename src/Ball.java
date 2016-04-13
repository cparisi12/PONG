import java.awt.Color;
import java.awt.Graphics;


public class Ball {


	//ball coordinates, size, color
	//speed, gravity, and bounce factor
	private double x, y, size, colorI,
	speedX = 10, speedY = 10, 
	gravity = 5, bouncingFactor = .9;



	/**
	 * @param ballX = ball x coordinate
	 * @param ballY = ball y coordinate
	 * @param ballSize = ball width and height
	 * @param colorIndex = ball color index
	 */
	public Ball(double ballX, double ballY, double ballSize, double colorIndex){
		//storing the variables
		x = ballX;
		y = ballY;
		size = ballSize;
		colorI = colorIndex;
	}

	public void paint(Graphics p){
		//an array with all the colors
		Color[] colors = {Color.black,
				Color.white,Color.cyan,
				Color.gray,Color.blue,
				Color.magenta,Color.orange,
				Color.red,Color.yellow};

		//random number to choose ball color
		p.setColor(colors[(int)colorI]);
		//create ball
		p.fillOval((int)x, (int)y,(int)size, (int)size);

		//ball circumference 
		if((int)colorI == 0){
			p.setColor(Color.white);
		}
		else{
			p.setColor(Color.black);
		}
		p.drawOval((int)x,(int) y, (int)size+1, (int)size+1);
	}

	public int getX(){
		return (int)x;
	}

	/**
	 * Controls the balls position in space
	 * keeping it within the boundaries.
	 */
	public void move(){

		x = x+speedX;

		//if the ball hits the left wall
		//change direction
		if(x<20){
			x = 20;
			speedX  = -speedX;
		}

		//if the ball hits the right wall
		//change direction
		if(x>=670){
			x = 670-size;
			speedX = -speedX;
		}

		y = y+speedY;


		//if ball hits the bottom
		//bounce and change direction
		if(y>360){
			y = 360;
			speedY = -speedY;
		}

		//if the ball is above the frame
		//change direction
		if(y<0){
			speedY = -speedY;
		}

	}

	//changes direction if the
	//ball is blocked by a paddle
	public void block() {
		speedX = -speedX;
		x = x+speedX;
	}

	//returns y coordinate of the ball
	public int getY() {
		
		return (int) y;
	}

	//returns size of the ball
	public int getSize() {
		
		return (int)size;
	}

	//sets the location of the ball
	public void setLoc(int i, int j) {
		
		x= i;
		y = j;
	}

	//gives the ball a random direction
	//for the speed of the ball
	public void setSpeed() {
		int direction = (int)(Math.random()*3);
		if(direction > 0){
			speedX = -speedX;
			speedY = -speedY;
		}
	}

}
