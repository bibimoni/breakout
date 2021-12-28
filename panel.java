import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class panel extends JPanel implements ActionListener {
    // game unit
    public static final int GRID_WIDTH = 600;
    public static final int GRID_HEIGHT = 400;
    public static final int GRID_SIZE = 25;
    
    // the paddle location
    public static int paddleWidth = 100;
    public static int paddleX = GRID_WIDTH / 2 - paddleWidth / 2;
    // declaration 
    public int numbersOfBlocks = 30;
    Timer timer;
    ball ball;
    brick[] bricks;
    int paddleY = 400 - 13; // gridheight - paddle height - space between void and paddle
    //init
    
    panel() {
    	
        ball = new ball();
        bricks = new brick[30];
        this.setPreferredSize(new Dimension(GRID_WIDTH, GRID_HEIGHT));
        this.setFocusable(true);
        this.setBackground(Color.BLACK);
        this.addKeyListener(new controller(this));
        int k = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                bricks[k] = new brick(j * 80 + 60, i * 40 + 50);
                k++;
            }
        }
        startGame();   
    }
    //use all the painting method
    public void paintComponent(Graphics g) {
        // drawing background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, GRID_WIDTH, GRID_HEIGHT);
        // drawing the rest
        draw(g);
        ball.display(g);
        displayBrick(g);
        if(checkWinCondition()) {
        	winGame(g);
        }
        if(checkLoseCondition()) {
        	loseGame(g);
        }
    }
    //display the brick if its available
    public void displayBrick(Graphics g) {
        for (int i = 0; i < numbersOfBlocks; i++) {
            if (bricks[i].isAvailable()) {
                g.setColor(Color.GREEN);
                g.fillRect(bricks[i].x, bricks[i].y, 80, 40);

                g.setColor(Color.BLACK);
                g.drawRect(bricks[i].x, bricks[i].y, 80, 40);
            }
        }
    }  
    public void draw(Graphics g) {
        // drawing paddle
        g.setColor(Color.RED);
        g.fillRect(paddleX, paddleY, paddleWidth, 10);
        // border
        g.setColor(Color.YELLOW);
        g.fillRect(0, 0, GRID_WIDTH, 3);
        g.fillRect(0, 0, 3, GRID_HEIGHT);
        g.fillRect(GRID_WIDTH - 3, 0, 3, GRID_HEIGHT);
    }
    //moving of the paddle and make sure it wont go outside the screen
    //teleport it back to the end of the border from each side
    public void moveLeft() {
        if (paddleX <= 3) {
            paddleX = 3;
        } else {
            paddleX -= 10;
        }
        this.repaint();
    }

    public void moveRight() {
        if (paddleX >= GRID_WIDTH - 3 - paddleWidth) {
            paddleX = GRID_WIDTH - 3 - paddleWidth;
        } else {
            paddleX += 10;
        }
        this.repaint();
    }
    //starting the timer
    public void startGame() {
        timer = new Timer(10, this);
        timer.start();
    }
    //check the collision between the ball and the brick
    public void checkCollisions() {
        for (int i = 0; i < numbersOfBlocks; i++) {
            if (ball.getRect().intersects(bricks[i].getRect())) {
                int ballLeft = (int) ball.getRect().getMinX();
                int ballHeight = (int) ball.getRect().getHeight();
                int ballWidth = (int) ball.getRect().getWidth();
                int ballTop = (int) ball.getRect().getMinY();
                
                //creating 4 points of the point
                Point pointRight = new Point(ballLeft + ballWidth + 1, ballTop);
                Point pointLeft = new Point(ballLeft - 1, ballTop);
                Point pointTop = new Point(ballLeft, ballTop - 1);
                Point pointBottom = new Point(ballLeft, ballTop + ballHeight + 1);
                
                //if the brick rect has 1 of the 4 corners, it changes the 
                //velocity vector and also delete the brick
                if (bricks[i].isAvailable()) {
                    if (bricks[i].getRect().contains(pointRight)) {
                        ball.changeXVect();
                        bricks[i].setAvailable(false);
                    } else if (bricks[i].getRect().contains(pointLeft)) {
                        ball.changeXVect();
                        bricks[i].setAvailable(false);
                    }
                    if (bricks[i].getRect().contains(pointBottom)) {
                        ball.changeYVect();
                        bricks[i].setAvailable(false);
                    } else if (bricks[i].getRect().contains(pointTop)) {
                        ball.changeYVect();
                        bricks[i].setAvailable(false);
                    }
                }
            }
        }
    }
    //check if the number of blocks are available = 0, then the player has won the game
    public boolean checkWinCondition() {
    	int count = 0;
    	for(int i = 0; i < numbersOfBlocks; i++) {
    		if(!bricks[i].isAvailable()) {
    			count++;
    		}
    	}
    	if(count == numbersOfBlocks) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    //if the ball goes below the paddle, the game ends
    public boolean checkLoseCondition() {
    	int ballTop = (int) ball.getRect().getMinY();
    	int paddleBottom = paddleY;
    	
    	if(ballTop > paddleBottom) {
    		return true;
    	}	
    	return false;
    }
    //draw the winning screen
    public void winGame(Graphics g) {
    	g.setColor(Color.WHITE);
    	g.setFont(new Font("Times New Roman", Font.BOLD, 35));
    	FontMetrics metrics = getFontMetrics(g.getFont());
    	g.drawString("YOU WIN", (GRID_WIDTH - metrics.stringWidth("YOU WIN"))/2, (GRID_HEIGHT - g.getFont().getSize())); 	
    }
    //draw the losing screen
    public void loseGame(Graphics g) {
    	g.setColor(Color.WHITE);
    	g.setFont(new Font("Times New Roman", Font.BOLD, 35));
    	FontMetrics metrics = getFontMetrics(g.getFont());
    	g.drawString("YOU LOSE", (GRID_WIDTH - metrics.stringWidth("YOU LOSE"))/2, (GRID_HEIGHT - g.getFont().getSize())); 	
    }
    //perform the ball moving and checking the collision 
    //between the ball and brick every <delay>
    //repainting
    public void actionPerformed(ActionEvent e) {
    	if(!checkWinCondition() || checkLoseCondition()) {
    		ball.moveBall();
    		checkCollisions();
    		this.repaint();
    	}
    }
}


