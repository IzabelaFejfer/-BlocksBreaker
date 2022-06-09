package projekt;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Map {
	public int map[][];
	public int height;
	public int width;
	int d;
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
	
	}
	public void draw(Graphics2D g) {
		for (int i =0 ; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				if(map[i][j]>0) {
					if (d==0)
					{
					g.setColor(Color.red);
					g.fillRect( width+width*j, height+height*i, width, height);
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.white);
					g.drawRect( width+width*j, height+height*i, width, height);
					}
					else if (d==1)
					{
					g.setColor(Color.white);
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
