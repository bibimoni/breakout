import java.awt.*;

public class ball {
    int ballX = 300;
    int ballY = 250;
    int ballWidth = 10;
    int ballHeight = 10;
    public int vectorX = -1;
    public int vectorY = -2;

    int paddleY = 400 - 13; // gridheight - paddle height - space between void and paddle
    panel Panel;

    public void changeXVect() {
        vectorX *= -1;
    }

    public void changeYVect() {
        vectorY *= -1;
    }

    public void display(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillOval(ballX, ballY, ballWidth, ballHeight);
    }

    public void moveBall() {

        ballX += vectorX;
        ballY += vectorY;
        
        if (ballX <= 3 || ballX >= panel.GRID_WIDTH - 3 - ballWidth) {
            vectorX *= -1;
        }
        if (ballY <= 3) {
            vectorY *= -1;
        }
        checkCollisions(); // check if the ball collies with the paddle
    }
    //detect the collision between the ball and the paddle
    public void checkCollisions() {
        Rectangle ballRect = new Rectangle(ballX, ballY, ballWidth, ballHeight);
        if (ballRect.intersects(new Rectangle(panel.paddleX, paddleY, panel.paddleWidth, 10))) 
        {
        	int paddlePart = panel.paddleWidth / 5;
        	int paddle1 = panel.paddleX + paddlePart;
        	int paddle2 = panel.paddleX + 2*paddlePart;
        	int paddle3 = panel.paddleX + 3*paddlePart;
        	int paddle4 = panel.paddleX + 4*paddlePart;
        	int paddle5 = panel.paddleX + 5*paddlePart;
        	
        	if(ballX < paddle1 || (ballX < paddle5 && ballX > paddle4)) 
        	{	
        		vectorY *= -1;
        		if(vectorX < 9) {
        		vectorX *= 3;
        		}
        	}
        	else if((ballX > paddle1 && ballX < paddle2) || (ballX < paddle4 && ballX > paddle3)) 
        	{
        		vectorY *= -1;
        		if(vectorX < 6) {
        		vectorX *= 2;
        		}
        	}
        	else if(ballX > paddle2 && ballX < paddle3) 
        	{
        		vectorY *= -1;
        		if(vectorX > 2) {
        			vectorX /= 2;
        		}
        	}
        }
    }
    //getting the rectangle to use it in the logic class
    public Rectangle getRect() {
        Rectangle ballRect = new Rectangle(ballX, ballY, ballWidth, ballHeight);
        return ballRect;
    }

}


