package gameproject;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.awt.Font;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class GameProject extends PApplet 
{
	//String [][] grid = {
			//{"0","0","0","0"},
			//{"0","0","0","0"},
			//{"0","0","0","0"},
			//{"0","0","0","0"}
	//};
	int box,column,row;
	int copyRow, copyColumn;
	PFont font,changeFont,newFont,finalFont,start,scoreboard;
	PImage bg;
	int count =0;
	int a = 0;
	int score;
	int [][] grid = {
			{0,0,0,0},
			{0,0,0,0},
			{0,0,0,0},
			{0,0,0,0},
	};
	int [][] copy = new int [4][4];
	public static void main(String [] args)
	{
		PApplet.main("gameproject.GameProject");
		
	}
	 enum GameState 
	 {
	        MENU,
	        RUNNING
	        
	 }
	 static GameState currentState;
	public void setup() 
	{
		 currentState = GameState.MENU;
		font = createFont("Arial", 110, true);
		changeFont = createFont("Arial", 95, true);
		newFont = createFont("Arial", 80, true);
		finalFont = createFont("Arial", 40, true);
		start = createFont("Arial", 60, true);
		scoreboard = createFont("Arial", 20, true);
		bg = loadImage("grid.png");
		pickNum();
		pickNum();
		
	}


	public void settings()
	{
		size(800,800);
		
	}
	public void draw() 
	{
		 switch(currentState)
		 {
	        case MENU:
	            drawMenu();
	            break;
	        case RUNNING:
	            drawRunning();
		 }
		
		
	}
	public void keyReleased()
	{
		if(key == 'w')
		{
			shiftBlocksUp();
			addUp();
			//waitTime();
			pickNum();
		
		}
		if(key == 'a')
		{
			shiftBlocksLeft();
			addLeft();
			//waitTime();
			pickNum();
		}
		if(key == 's')
		{
			shiftBlocksDown();
			addDown();
			//waitTime();
			pickNum();
		
		}
		if(key == 'd')
		{
			shiftBlocksRight();
			addRight();
			//waitTime();
			pickNum();
		}
	}
	public void mousePressed()
	{
        if( currentState == GameState.MENU && mouseX > 200 && mouseX < 600 && mouseY > 525 && mouseY < 675)
        {
            currentState = GameState.RUNNING;
           
        }
    }
	public void drawMenu(){
        clear();
        background(255,215,0);
        fill(204,204,204);
        rect(200,525, 400,150);
        textFont(start);
		fill(255,255,255);
		text("Start Game",254,613);
		textFont(font);
		fill(255,255,255);
		text("2048",275,313);
    }
     
    public void drawRunning()
    {
        clear();
        background(bg);
		update();
    }
	public void shiftBlocksUp()
	{
		while(a<4)
		{
			for(int i = 3; i>0;i--)
			{
				for (int j = 3; j>-1; j--)
				{
					if((grid[i][j]>0) && grid[i-1][j]==0)
					{
						grid[i-1][j]=grid[i][j];
						grid[i][j]=0;
					}
				}
			}
			a++;
		}
		a=0;
	}
	public void shiftBlocksDown()
	{
		while(a<4)
		{
			for(int i = 0; i<grid.length-1;i++)
			{
				for (int j = 0; j<grid[0].length; j++)
				{
					if((grid[i][j]>0) && grid[i+1][j]==0)
					{
						grid[i+1][j]=grid[i][j];
						grid[i][j]=0;
					}
				}
			}
			a++;
		}
		a=0;
	}
	public void shiftBlocksRight()
	{
		while(a<4)
		{
			for(int i = 0; i<grid.length;i++)
			{
				for (int j = 0; j<grid[0].length-1; j++)
				{
					if((grid[i][j]>0) && grid[i][j+1]==0)
					{
						grid[i][j+1]=grid[i][j];
						grid[i][j]=0;
					}
				}
			}
			a++;
		}
		a=0;
	}
	public void shiftBlocksLeft()
	{
		while(a<4)
		{
			for(int i = 3; i>-1;i--)
			{
				for (int j = 3; j>0; j--)
				{
					if((grid[i][j]>0) && grid[i][j-1]==0)
					{
						grid[i][j-1]=grid[i][j];
						grid[i][j]=0;
					}
				}
			}
			a++;
		}
		a=0;
	}
	public void pickNum()
	{
		row = (int)(Math.random()*4);
		column = (int)(Math.random()*4);
		box = (int)(Math.random()*100);
		copyRow = row;
		copyColumn = column;
		if(box >30 && grid[row][column]==0)
		{
			grid[row][column] = 2;

		}
		else if(box >30 && (grid[row][column]!=0))
		{
			pickNum();
		}
		else if(box<30 && grid[row][column]==0)
		{
			grid[row][column] = 4;

		}
		else if(box<30 && (grid[row][column]!=0))
		{
			pickNum();
		}
	}
	public void update()
	{
		for(int i = 0; i<grid.length;i++)
		{
			for (int j = 0; j<grid[0].length; j++)
			{
				if(grid[i][j]<10 && grid[i][j]!= 0)
				{
					textFont(font);
					fill(255,255,255);
					text(grid[i][j],j*200+80,i*200+150);
				}
				if(grid[i][j]==8)
				{
					textFont(font);
					fill(255,127,80);
					text(grid[i][j],j*200+80,i*200+150);
				}
				if(grid[i][j]>10 && grid[i][j]<100)
				{
					textFont(changeFont);
					fill(255,127,80);
					text(grid[i][j],j*200+60,i*200+140);
				}
				if(grid[i][j]>100 && grid[i][j]<1000)
				{
					textFont(newFont);
					fill(255,255,102);
					text(grid[i][j],j*200+40,i*200+130);
				}
				if(grid[i][j]>1000 && grid[i][j]<10000)
				{
					textFont(finalFont);
					fill(255,255,51);
					text(grid[i][j],j*200+30,i*200+120);
				}
			}
		}
		textFont(scoreboard);
		fill(255,255,255);
		text("Score: " + score,20,20);
	}
	public void addUp()
	{
		for(int i = 0; i<grid.length-1;i++)
		{
			for (int j = 0; j<grid[0].length; j++)
			{
				if(grid[i][j]==grid[i+1][j]&&grid[i][j]!=0)
				{
					grid[i][j] *= 2;
					grid[i+1][j]=0;
					score+=grid[i][j];
				}
			}
		}
		shiftBlocksUp();
	}
	public void addDown()
	{
		for(int i = 3; i>0;i--)
		{
			for (int j = 3; j>-1; j--)
			{
				if(grid[i][j]==grid[i-1][j]&&grid[i][j]!=0)
				{
					grid[i][j] *= 2;
					grid[i-1][j]=0;
					score+=grid[i][j];
				}
			}
		}
		shiftBlocksDown();
	}
	public void addRight()
	{
		for(int i = 3; i>-1;i--)
		{
			for (int j = 3; j>0; j--)
			{
				if(grid[i][j]==grid[i][j-1]&&grid[i][j]!=0)
				{
					grid[i][j] *= 2;
					grid[i][j-1]=0;
					score+=grid[i][j];
				}
			}
		}
		shiftBlocksRight();
	}
	public void addLeft()
	{
		for(int i = 0; i<grid.length;i++)
		{
			for (int j = 0; j<grid[0].length-1; j++)
			{
				if(grid[i][j]==grid[i][j+1]&&grid[i][j]!=0)
				{
					grid[i][j]*= 2;
					grid[i][j+1]=0;
					score+=grid[i][j];
				}
			}
		}
		shiftBlocksLeft();
	}
}
