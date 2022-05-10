package properties;

import java.awt.Color;
import java.util.Random;

public final class BallProperties {
	static Random r = new Random();

	private static Color color_tryb_jasny = Color.BLUE;
	private static Color color_tryb_ciemny = Color.ORANGE;
	private static int ballX = r.nextInt(760)+5;
	private static int ballY = 300;
	private static int ballvx = 2;
	private static int ballvy = 3;
	private static int radious= 20;

	public static Color getColor_tryb_jasny() {
		return color_tryb_jasny;
	}
	public static Color getColor_tryb_ciemny() {
		return color_tryb_ciemny;
	}
	public static int getBallX() {
		return ballX;
	}
	public static int getBallY() {
		return ballY;
	}
	public static int getBallvx() {
		return ballvx;
	}
	public static int getBallvy() {
		return ballvy;
	}
	public static int getRadious() {
		return radious;
	}
}
