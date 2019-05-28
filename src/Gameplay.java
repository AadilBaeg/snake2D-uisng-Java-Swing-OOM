import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Gameplay extends JPanel implements KeyListener, ActionListener{
	
	private int[] snakeXpos = new int[750];		//for X-axis motion of the snake
	private int[] snakeYpos = new int[750];		//for Y-axis motion of the snake
	
	private boolean right = false;				// for the direction of snake
	private boolean left = false;
	private boolean up = false;
	private boolean down = false;
	
	
	private ImageIcon rightmouth;			// for image of rightmouth
	private ImageIcon upmouth;				// for image of upmouth
	private ImageIcon downmouth;			// for image of downmouth
	private ImageIcon leftmouth;			// for image of leftmouth
	
	private int lengthOfSnake = 3;
	
	private Timer timer; 					//for managing the speed of the snake
											//where Timer is inbuilt class
	
	private int delay = 100; 	//to manage the speed of the snake this variable is used.
	
	private ImageIcon titleImage;			// for image of Title
	private ImageIcon snakeImage; 			// for spherical shell i.e., the body of snake.
	
	
	private int[] possibleFoodPosX = {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,
			425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
	
	private int[] possibleFoodPosY = {75,100,125,150,175,200,225,250,275,300,325,350,375,400,
			425,450,475,500,525,550,575,600,625};			
														//these two arrays are contain all possible 
														//locations where food can be present.
	
	private ImageIcon foodImage;
	
	private Random random = new Random();		//position of pickup
	
	private int X = random.nextInt(possibleFoodPosX.length);	// ie can be any from possibleFoodPosX array
	private int Y = random.nextInt(possibleFoodPosY.length);	// ie can be any from possibleFoodPosY array
	
	private int score = 0;			// for scoreboard
	
	private int moves = 0;
	
	private boolean isRunning = true;
	
	
	
	public Gameplay()
	{
		addKeyListener(this);			//add keyListener, inside bracket we will put
									// the object of the class which is implementing
								  // KeyListener, since it is Gameplay class, we use 'this'
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
		timer = new Timer(delay, this);		//inside constructor we will
											//instantiate timer class
											//first we write variable then
											//the context of this snake
		
		timer.start();						//after this we will start the timer
		
		
	}									
	
	public void paint(Graphics g)
	{
		
		if(moves == 0)
		{
			snakeXpos[2] = 50;
			snakeXpos[1] = 75;
			snakeXpos[0] = 100;
			
			snakeYpos[2] = 100;
			snakeYpos[1] = 100;
			snakeYpos[0] = 100;
		}
		
		
		// draw title image border
		g.setColor(Color.white);
		g.drawRect(24, 10, 851, 55);
		
		//draw titleImage
		titleImage = new ImageIcon(Gameplay.class.getClassLoader().getResource("picturesStorage/snaketitle.jpg"));
		titleImage.paintIcon(this, g, 25, 11);	// we used this because
												// the object  of the class which
												// it is using has to there, 
												// so we simply write this 
		//draw border for gameplay
		g.setColor(Color.white);
		g.drawRect(24, 74, 851, 577);
		
		//draw background for the gameplay
		g.setColor(Color.black);
		g.fillRect(25, 75, 850, 575);
		
		//draw scorecard
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Score : "+score, 780, 30);
		
		//draw the length of snake
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Length : "+lengthOfSnake, 780, 50);
		
		
		rightmouth = new ImageIcon(Gameplay.class.getClassLoader().getResource("picturesStorage/rightmouth.png"));
		rightmouth.paintIcon(this, g, snakeXpos[0], snakeYpos[0]);
		
		
		for(int i = 0; i < lengthOfSnake; i++)	//inside this we will detect the direction
		{										//of the snake
			if(i==0 && right)
			{
				rightmouth = new ImageIcon(Gameplay.class.getClassLoader().getResource("picturesStorage/rightmouth.png"));
				rightmouth.paintIcon(this, g, snakeXpos[i], snakeYpos[i]);
				
			}
			if(i==0 && left)
			{
				leftmouth = new ImageIcon(Gameplay.class.getClassLoader().getResource("picturesStorage/leftmouth.png"));
				leftmouth.paintIcon(this, g, snakeXpos[i], snakeYpos[i]);
				
			}
			if(i==0 && up)
			{
				upmouth = new ImageIcon(Gameplay.class.getClassLoader().getResource("picturesStorage/upmouth.png"));
				upmouth.paintIcon(this, g, snakeXpos[i], snakeYpos[i]);
				
			}
			if(i==0 && down)
			{
				downmouth = new ImageIcon(Gameplay.class.getClassLoader().getResource("picturesStorage/downmouth.png"));
				downmouth.paintIcon(this, g, snakeXpos[i], snakeYpos[i]);
				
			}
			if(i!=0)
			{
				snakeImage = new ImageIcon(Gameplay.class.getClassLoader().getResource("picturesStorage/snakeImage.png"));
				snakeImage.paintIcon(this, g, snakeXpos[i], snakeYpos[i]);
			}
			
		}
		foodImage = new ImageIcon(Gameplay.class.getClassLoader().getResource("picturesStorage/enemy.png"));
																			// we will draw the food icon					
		
		if(possibleFoodPosX[X] == snakeXpos[0] && possibleFoodPosY[Y] == snakeYpos[0])	// we will check whether head of snake
		{										// is colliding with food or not
			
			lengthOfSnake++;					//if collision occurs then increase snake length
			score++;							// update score
			
			X = random.nextInt(possibleFoodPosX.length);	//generate new location of food
			Y = random.nextInt(possibleFoodPosY.length);
		
		}
		
		foodImage.paintIcon(this, g, possibleFoodPosX[X], possibleFoodPosY[Y]);	//draw food icon
		
		for(int i = 1 ; i < lengthOfSnake ; i++ )
		{
			if(snakeXpos[i] == snakeXpos[0] && snakeYpos[i] == snakeYpos[0])
			{
				right = false;
				left = false;
				up = false;
				down = false;
				isRunning = false;
				
				g.setColor(Color.WHITE);
				g.setFont(new Font("arial", Font.BOLD, 50));
				g.drawString("Game Over ", 300, 300);
				
				g.setFont(new Font("arial", Font.BOLD, 20));
				g.drawString("Space to restart ", 350, 340);
				
			}
		}
			
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		timer.start();
		
		if(isRunning = true)
		{
			if(right == true)
			{
				for(int i = lengthOfSnake-1 ; i>=0 ; i--)
				{
					snakeYpos[i+1] = snakeYpos[i];
				}
				for(int i = lengthOfSnake ; i>=0 ; i--)
				{
					if(i==0)			// ie it is the head of the snake
					{
						snakeXpos[0] = snakeXpos[0] + 25;
					}
					else
					{
						snakeXpos[i] = snakeXpos[i-1];
					}
					
					if(snakeXpos[i] > 850)	// if snake reaches the right end of border
					{							// it must come from the left end moving towards right 
						 snakeXpos[i] = 25;
					}
				}
			}								
			
			if(left == true)
			{
				for(int i = lengthOfSnake-1 ; i>=0 ; i--)
				{
					snakeYpos[i+1] = snakeYpos[i];
				}
				for(int i = lengthOfSnake ; i>=0 ; i--)
				{
					if(i==0)			// ie it is the head of the snake
					{
						snakeXpos[0] = snakeXpos[0] - 25;
					}
					else
					{
						snakeXpos[i] = snakeXpos[i-1];
					}
					
					if(snakeXpos[i] < 25)	
					{
						 snakeXpos[i] = 850;
					}
				}
				
			}
			if(up == true)
			{
				for(int i = lengthOfSnake-1 ; i>=0 ; i--)
				{
					snakeXpos[i+1] = snakeXpos[i];
				}
				for(int i = lengthOfSnake ; i>=0 ; i--)
				{
					if(i==0)			// ie it is the head of the snake
					{
						snakeYpos[0] = snakeYpos[0] - 25;
					}
					else
					{
						snakeYpos[i] = snakeYpos[i-1];
					}
					
					if(snakeYpos[i] < 75)	
					{
						 snakeYpos[i] = 625;
					}
				}

			}
			if(down == true)
			{
				for(int i = lengthOfSnake-1 ; i>=0 ; i--)
				{
					snakeXpos[i+1] = snakeXpos[i];
				}
				for(int i = lengthOfSnake ; i>=0 ; i--)
				{
					if(i==0)			// ie it is the head of the snake
					{
						snakeYpos[0] = snakeYpos[0] + 25;
					}
					else
					{
						snakeYpos[i] = snakeYpos[i-1];
					}
					
					if(snakeYpos[i] > 625)	
					{
						 snakeYpos[i] = 75;
					}
				}

			}
		}
		repaint();				// outside this loop we will have to call this repaint 
								// method so that updated changes are seen
								// Once the repaint method is called it will call the paint()
								// method which will run everything inside of paint() method.  
		
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE && isRunning == false)
		{
			moves = 0;
			score = 0;			//reinitialisation when snake stops
			lengthOfSnake = 3;
			isRunning = true;
			repaint();
		}
		if(isRunning == true)
		{
			if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			{
				moves++;
				right = true;
				
				if(left == false)
				{
					right = true;
				}
				else
				{
					right = false;
					left = true; 
				}
				up = false;
				down = false;
			}
			if(e.getKeyCode() == KeyEvent.VK_LEFT)
			{
				moves++;
				left = true;
				
				if(right == false)
				{
					left = true;
				}
				else
				{
					left = false;
					right = true; 
				}
				up = false;
				down = false;
			}
			if(e.getKeyCode() == KeyEvent.VK_UP)
			{
				moves++;
				up = true;
				
				if(down == false)
				{
					up = true;
				}
				else
				{
					up = false;
					down = true; 
				}
				left = false;
				right = false;
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN)
			{
				moves++;
				down = true;
				
				if(up == false)
				{
					down = true;
				}
				else
				{
					down = false;
					up = true; 
				}
				left = false;
				right = false;
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
