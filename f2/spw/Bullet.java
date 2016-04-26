package f2.spw;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;


public class Bullet extends Sprite{
	public static final int Y_TO_FADE = 20; //distance of bullet that start to fade
	public static final int Y_TO_DIE = 0; //end of panel, when bullet out of panel it's die
	
	private int step = 20; //speed of bullet
	private boolean alive = true;
	
	public Bullet(int x, int y) {
		super(x , y, 5, 15);	
	}

	@Override
	public void draw(Graphics2D g) {
		if(y > Y_TO_FADE)
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		else{
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
					(float)(Y_TO_DIE - y)/(Y_TO_DIE - Y_TO_FADE)));
		}
		g.setColor(Color.GREEN);
		g.fillOval(x, y, width, height);
		
	}

	public void proceed(){
		y -= step;
		if(y < Y_TO_DIE){
			alive = false;
		}
	}
	
	public boolean isAlive(){
		return alive;
	}
	
	public void getHit(){
		this.alive = false;
	}

}
