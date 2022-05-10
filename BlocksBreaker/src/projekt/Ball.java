package projekt;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import properties.BallProperties;

public class Ball {
	
	private int PosX;
	private int PosY;
	private int VelX;
	private int VelY;
	private int Radious;
	private Color colorLight;
	private Color colorDark;
	private Color color;

	Ball(int posX, int posY, int velX, int velY,
		 int radious, Color col){
		this.PosX = posX;
		this.PosY = posY;
		this.VelX = velX;
		this.VelY = velY;
		this.Radious = radious;
		this.color = col;
	}

	public void paint (Graphics g) {
		g.setColor(this.color);
		g.fillOval(this.PosX, this.PosY, this.Radious, this.Radious);
	};

	public void moveX() {
		this.PosX = this.PosX - this.VelX;
		if(this.PosX < 0) {
			this.reverseVelX();	
			}
		else if (this.PosX > 750){
			this.reverseVelX();	
		};
	};

	public void reverseVelX() {
		this.VelX = -this.VelX;
	};

	public void reverseVelY() {
		this.VelY = -this.VelY;
	};

	public void moveY() {
		this.PosY = this.PosY + this.VelY;
		if(this.PosY<0) {
			this.reverseVelY();	
		}
	};

	public int getPosX() {
		return this.PosX;
	};

	public int getPosY() {
		return this.PosY;
	};

	public int getRadious() {
		return this.Radious;
	};
	
	public boolean intersects(Rectangle rec) {
		if (new Rectangle(this.PosX, this.PosY, this.Radious, this.Radious).intersects(rec))
			return true;
		return false;
	};

	public Rectangle getRectangle() {
		return new Rectangle(this.PosX, this.PosY, this.Radious, this.Radious);
	};

};
