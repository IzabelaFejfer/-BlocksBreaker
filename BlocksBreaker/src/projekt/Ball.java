package projekt;

import java.awt.Color;
import java.awt.Graphics;

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
		 int radious, Color c_light, Color c_dark){
		this.PosX = posX;
		this.PosY = posY;
		this.VelX = velX;
		this.VelY = velY;
		this.Radious = radious;
		this.colorLight = c_light;
		this.colorDark = c_dark;
		this.color = c_light;
	}

	public Ball() {
		this.PosX = BallProperties.getBallX();
		this.PosY = BallProperties.getBallY();
		this.VelX = BallProperties.getBallvx();
		this.VelY = BallProperties.getBallvy();
		this.Radious = BallProperties.getRadious();
		this.colorLight = BallProperties.getColor_tryb_jasny();
		this.colorDark = BallProperties.getColor_tryb_ciemny();
		this.color = BallProperties.getColor_tryb_jasny();
	};

	public void paint (Graphics g) {
		g.setColor(this.color);
		g.fillOval(this.PosX, this.PosY, this.Radious, this.Radious);
	};

	public void moveX() {

			this.PosX = this.PosX - this.VelX;
	};

	public void reverseVelX() {
		this.VelX = -this.VelX;
	};
	public void reverseVelY() {
		this.VelY = -this.VelY;
	};

	public void moveY() {
		this.PosY = this.PosY + this.VelY;
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
};
