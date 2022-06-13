package projekt;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Map {
	public int map[][];
	public int height;
	public int width;
	int d;
	Color kolorKlockow;
	Color kolorSiatki;
	
	Map(int a, int b, int c){
		map = new int [a][b];
		for (int i =0 ; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				map[i][j]=1;
			}
		}
		width = 600/b;
		height = 220/a;
		d=c;
		kolorKlockow = Color.RED;
		kolorSiatki = Color.white;
	}
	
	public void trybJasny() {
		kolorKlockow = Color.RED;
		kolorSiatki= Color.white;
	}
	
	public void trybCiemny() {
		kolorKlockow = Color.BLUE;
		kolorSiatki= Color.YELLOW;
	}
	
	public void draw(Graphics2D g) {
		for (int i =0 ; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				if(map[i][j]>0) {
					if (d==0)
					{
					g.setColor(kolorKlockow);
					g.fillRect( width+width*j, height+height*i, width, height);
					g.setStroke(new BasicStroke(3));
					g.setColor(kolorSiatki);
					g.drawRect( width+width*j, height+height*i, width, height);
					}
					else if (d==1)
					{
					g.setColor(kolorSiatki);
					g.fillRect( width+width*j, height+height*i, width, height);
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect( width+width*j, height+height*i, width, height);
					}
				}
			}
		}
	}
	
	public void setBrickValue(int value, int a, int b) {
		map[a][b] = value;
	}
}