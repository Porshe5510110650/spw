package f2.spw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;


public class GameEngine implements KeyListener, GameReporter{
	GamePanel gp;
		
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private SpaceShip v;	
	private int combo=0;
	private Timer timer;
	private long score = 0;
	private double difficulty = 0.1;
	private long count = 0;
	
	public GameEngine(GamePanel gp, SpaceShip v) {
		this.gp = gp;
		this.v = v;		
		
		gp.sprites.add(v);
		
		timer = new Timer(50, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
		process();
			}
		});
		timer.setRepeats(true);
		
		
	}
	
	public void start(){
		timer.start();
	
	}
	
	private void generateEnemy(){
		Enemy e = new Enemy((int)(Math.random()*390), 30);
		gp.sprites.add(e);
		enemies.add(e);
	}
	
	private void process(){
		if(Math.random() < difficulty){
			generateEnemy();
		}
	
	
		
		Iterator<Enemy> e_iter = enemies.iterator();
		while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			e.proceed();
			
			if(!e.isAlive()){
				e_iter.remove();
				gp.sprites.remove(e);
			
			}
			if(e.isHit()){
				combo++;
				if(combo>10){
					score += 20;
					count += 20;
				}
				else{
				score += 10;
				count += 10;
				}
			}
		}
			
		gp.updateGameUI(this);
		
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double er;
		Rectangle2D.Double br;
		for(Enemy e : enemies){
			er = e.getRectangle();
			if(er.intersects(vr)){
				e.hitMe();
				minus();
				combo = 0;
				return;
			}
			for(Bullet b : bullets){
				br = b.getRectangle();
				if(br.intersects(er)){
					e.getHit();
					b.getHit();
					return;
				}				
			}
		}
	}
	
	public void minus(){
		hp -= 10 ;
		if(hp <= 0){
			die();
		}
	
	}
	public void die(){
		timercheck.stop();
		
	}
	
	void controlVehicle(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			v.move(-1);
			break;
		case KeyEvent.VK_RIGHT:
			v.move(1);
			break;
			difficulty += 0.1;
			break;
		}
	}
	
	public int getCombo(){
		return combo;
	}
	
	public long getScore(){
		return score;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		controlVehicle(e);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing		
	}
	
	
		
	
}
