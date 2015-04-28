package f2.spw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage bi;	
	Graphics2D big;
	ArrayList<Sprite> sprites = new ArrayList<Sprite>();

	public GamePanel() {
		bi = new BufferedImage(400, 600, BufferedImage.TYPE_INT_ARGB);
		big = (Graphics2D) bi.getGraphics();
		big.setBackground(Color.BLACK);
	}

	public void updateGameUI(GameReporter reporter){
		big.clearRect(0, 0, 400, 600);
		
		big.setColor(Color.WHITE);
		big.drawString(String.format("Score %08d", reporter.getScore()), 260, 20);
		big.drawString(String.format("Combo : %03d", reporter.getCombo()), 260, 40);
		big.drawString(String.format("HP : %03d", reporter.getHp()), 150, 20);
		big.drawString(String.format("Nuclear : %01d", reporter.getNc()), 20, 20);
		big.drawString(String.format("Life Point : %01d", reporter.getLp()), 20, 40);
		for(Sprite s : sprites){
			s.draw(big);
		}
		repaint();
		
		
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		int s0 = 0;
		g2d.drawImage(bi, null, s0, 0);
	}

}
