package properties;

import java.awt.Color;
import java.util.Random;

public final class BallProperties {
	static Random r = new Random();

	private static Color color_tryb_jasny = Color.BLUE;
	private static Color color_tryb_ciemny = Color.ORANGE;
	private static int ballX = r.nextInt(760)+5;
	private static int ballY = 300;
	private static int ballvxEasy = 2;
	private static int ballvyEasy = 3;
	private static int radiousEasy= 20;
	private static int ballvxHard = 3;
	private static int ballvyHard = 4;
	private static int radiousHard = 15;

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
	public static int getBallvxEasy() {
		return ballvxEasy;
	}
	public static int getBallvyEasy() {
		return ballvyEasy;
	}
	public static int getRadiousEasy() {
		return radiousEasy;
	}
	public static int getBallvxHard() {
		return ballvxHard;
	}
	public static int getBallvyHard() {
		return ballvyHard;
	}
	public static int getRadiousHard() {
		return radiousHard;
	}
}
