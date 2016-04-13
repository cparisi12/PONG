import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 
 * @author ChrisParisi
 *
 *The game class will be used to create
 *and organize everything that will be used
 *throughout the duration of this program.
 *This will also be the class that will recognize
 *actions and keys performed by the user.
 *
 */

public class Game extends JPanel 
implements ActionListener, AIAlarmListener, GameAlarmListener, KeyListener {

	//Declaring Variables
	//my graphics frame
	private JFrame frame;
	//height and width of my frame 
	private int height = 400, width = 700;
	//string to reference title
	private String title = "| PONG |";
	//the array of buttons
	private JButton [] button;
	//Instance of the alarm class;
	private Alarm alarm;
	//separate alarm for AI
	private Alarm aiAlarm;
	//new collection
	private LinkedCollection collection = new LinkedCollection();
	//game score set by user
	private int score;

	//Instantiating variables
	//temporary speed value
	private int period = 50;
	private boolean running = false;
	private String [] BUTTONS = {"Play","Pause","Next Point"};
	private Paddle user, AI;
	private int userScore, AIscore;
	public int gap = 30;
	public int y = 150;
	private int pause = 1;
	private Ball gameBall;
	private boolean GamePlayed = false;


	/**
	 * This will be the game constructor responsible
	 * for creating the new alarm, set the period
	 * of that alarm, start that alarm, and set the
	 * alarm to running. The constructor will also
	 * handle the set up of the frame the game will 
	 * be played in, height, width, title, etc. This 
	 * constructor will also add the three buttons
	 * to the J panel. It will also create two new paddles
	 * one for the user and one for the computer opponent.
	 * A linked collection of balls will be made at a length
	 * passed to the constructor as 
	 * @param someScore
	 */
	public Game(int someScore){

		//set score
		score = someScore;

		//create and start alarms
		alarm = new GameAlarm(this);
		aiAlarm = new AIAlarm(this);
		alarm.setPeriod(period);
		aiAlarm.setPeriod(period-10);

		//create game frame
		frame = new JFrame(title);
		frame.setSize(width, height+65);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this,BorderLayout.CENTER);


		//panel for all the buttons
		JPanel controlPanel = new JPanel();
		//panel background color
		controlPanel.setBackground(Color.BLUE);


		//empty array for buttons
		button = new JButton[BUTTONS.length];
		//puts each button in the array
		//and adds it to the panel
		for (int i=0; i<BUTTONS.length; i++) 
		{
			button[i] = new JButton(BUTTONS[i]);
			button[i].addActionListener(this);
			button[i].setFocusable(false);
			controlPanel.add(button[i]);

		}

		//adding balls to the linked list
		for(int i = 0; i<score; i++){
			Ball temp = new Ball(width/2, height/2, 20, 9*Math.random());
			collection.add(temp);

		}

		//centers the buttons at the top
		frame.add(controlPanel, BorderLayout.NORTH);

		//creating the two paddles
		user = new Paddle(gap, y, 0);
		AI = new Paddle(width-gap-20, y, 1);

		//more frame settings
		frame.addKeyListener(this);
		frame.setFocusable(true);
		frame.setVisible(true);
		

	}







	/**
	 * 
	 * This method will be responsible for reacting
	 * to a user clicking any of the three buttons.
	 *  
	 * If the Play button is clicked the head ball 
	 * in the collection will be sent into play and
	 * the next ball will be set to head. 
	 * 
	 * If the Pause button is clicked the game freezes
	 * 
	 * If the Resume button is clicked the game resumes
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		

		//if the play button is pressed the game
		//begins. Alarms start and gameBall is set
		if(e.getSource() == button[0]){
			if(!GamePlayed){
				running = true;
				gameBall = collection.next();
				alarm.start();
				aiAlarm.start();
				GamePlayed = true;
			}

		}

		//pauses and unpauses the game
		if(e.getSource() == button[1]){
			pause = -pause;
			if(!GameOver()){
				if(pause<0){
					button[1].setText("unPause");
					running = false;
				}
				else{
					running = true;
					button[1].setText("Pause");

				}
				repaint();
			}
		}

		//plays the next ball after a point
		//is scored by either player
		if(e.getSource() == button[2]){
			
			if(!GameOver() && !running){
				period = 50;
				running = true;
				collection.reset(gameBall);
				collection.remove();
				collection.reset();
				gameBall = collection.next();
				gameBall.setLoc(350, 200);
				gameBall.setSpeed();
				repaint();
			}
			
		}

	}

	/**
	 * This method controls the AI paddle by setting the 
	 * paddle to the location of the ball but delaying the
	 * alarm so the AI is beatable by the user as the ball 
	 * increases speed. 
	 */
	private void AIcontrol(){

		if(gameBall.getY() > AI.getY() || gameBall.getY() < AI.getY())
			AI.setYai((int)gameBall.getY() - AI.getHeight()/2);

	}
	
	//this method is responsible for events that occur
	//when either player blocks a shot with their paddle.
	//this controls the ball direction after being blocked
	//as well as increase the speed of the ball. Both players
	//have a chance to get a random power shot that 
	//temporarily increase the speed of that shot.
	public void blockedShot(){

		int random = (int)(Math.random()*5);

		if(user.getX() > gameBall.getX() && 
				gameBall.getY() > user.getY() && 
				gameBall.getY() < user.getY()+user.getHeight()){ 
			gameBall.block();
			if(period>30)
				period-=2;
			alarm.setPeriod(period - random);
			
		}

		else if(AI.getX() < gameBall.getX()+40 && 
				gameBall.getY() > AI.getY() && 
				gameBall.getY() < AI.getY() + AI.getHeight()){
			gameBall.block();
			if(period>30)
				period-=2;
			alarm.setPeriod(period - random);
			
		}



	}

	/**
	 * Method to paint everything.
	 */
	public void paint(Graphics p){

		Font myFont = new Font("Impact", Font.ITALIC | Font.BOLD, 50);
		
		p.setColor(Color.GREEN);
		p.fillRect(0, 0, width, height);

		p.setColor(Color.white);
		p.drawLine(20, 0, 20,height-20 );
		p.drawLine(20, height-20, width-20,height-20 );
		p.drawLine(width-20,height-20, width-20,0 );
		p.setColor(Color.black);
		p.drawString("By: Christopher Parisi", 100, 393);
		p.setColor(Color.ORANGE);
		p.setFont(myFont);
		p.drawString("[ "+userScore+" - "+AIscore+" ]", 280, 200);
		if(GameOver()){
			if(userScore>AIscore)
				p.drawString("You are the weiner!", 150, 260);
			else
				p.drawString("The AI is the weiner!", 140, 260);
		}

		user.paint(p);
		AI.paint(p);
		if(gameBall != null)
			gameBall.paint(p);

	}

	/**
	 * 
	 * Method responsible for the movement of the ball.
	 * If the alarm is running, move the ball and 
	 * test for a blocked shot or scored point.
	 * 
	 */
	@Override
	public void takeNotice() {
		
		if(running && gameBall != null){
			gameBall.move();
			blockedShot();
			scoredPoint();
			repaint();

		}

	}

	//this method controls the actions that are performed
	//when a point is scored by either player. If a point
	//is scored: the game stops running, increments score
	//for appropriate player, resets period, and removes
	//the current game ball
	private void scoredPoint() {

		if(gameBall != null && gameBall.getX() < gap && !GameOver()){
			running = false;
			AIscore++;
			period = 50;
			alarm.setPeriod(period);
			
			try {
				Thread.sleep(50);                 
			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			gameBall = null;
		}	

		if(gameBall != null && gameBall.getX() + gameBall.getSize() > 660 && !GameOver()){
			running = false;
			userScore++;
			period = 50;
			alarm.setPeriod(period);
			
			try {
				Thread.sleep(50);                
			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			gameBall = null;
		}	

	}


	//method to test if the score
	//limit has been reached
	private boolean GameOver() {
		
		if(userScore + AIscore == score)
			return true;
		else
			return false;
	}


	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}

	/**
	 * This will be the only key event implemented.
	 * When the up arrow is pressed the players
	 * paddle will move 25 pixels up.
	 * When the down arrow is pressed the players
	 * paddle will move 25 pixels down.
	 */
	@Override
	public void keyPressed(KeyEvent e) {

		if(e.getKeyCode() == KeyEvent.VK_UP && user.getY() > 10){
			user.setY(user.getY() - 25);
			repaint();
		}

		if(e.getKeyCode() == KeyEvent.VK_DOWN && user.getY()+user.getHeight() < height-gap){
			user.setY(user.getY() + 25);
			repaint();
		}

		if(e.getKeyCode() == KeyEvent.VK_CAPS_LOCK && AI.getY() > 10){
			AI.setY(AI.getY() - 25);
			repaint();
		}

		if(e.getKeyCode() == KeyEvent.VK_SHIFT && AI.getY()+AI.getHeight() < height-gap){
			AI.setY(AI.getY() + 25);
			repaint();
		}

	}

	//method to handle the ai alarm
	//and ai control
	@Override
	public void performAITask() {
		if(running)
			AIcontrol();
	}


}
