import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.scene.shape.*;
import javafx.event.*;
import javafx.animation.*;
import javafx.util.*;
import java.util.*;

//https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life sim rules
//https://en.wikipedia.org/wiki/Brian%27s_Brain for later!

public class Life extends Application {
	
	public static void main(String args[]) {
		launch(args);
	}//main
	
	@Override
	
	public void start(Stage myStage) {
		
		Group root = new Group();
		
		myStage.setTitle("Life - Startup");
		Scene myScene = new Scene (root, 450, 450);
		
		Label title = new Label();
		title.setText("life.");
		title.setLayoutX(190);
		title.setLayoutY(150);
		title.setFont(new Font("Times New Roman", 36));
		
		TextField inputBox = new TextField();
		inputBox.setLayoutX(150);
		inputBox.setLayoutY(225);
		Label inputText = new Label();
		inputText.setText("Enter a starting population");
		inputText.setLayoutX(130);
		inputText.setLayoutY(200);
		
		inputBox.setOnAction(event -> fillArray(inputBox));

		root.getChildren().add(inputBox);
		root.getChildren().add(inputText);
		root.getChildren().add(title);

		myStage.setScene(myScene);
		myStage.show();
		
	}//start
	
	public void fillArray (TextField inputBox){
		
		String inputString;
        int input;
		int i = 0;
        inputString = inputBox.getText();
        input = Integer.parseInt(inputString);
		
		boolean[][] currentGen = new boolean [150][150];
		
		
		while (i < input){
			
			Random random = new Random();
			
			int xcoord = random.nextInt(150);
			int ycoord = random.nextInt(150); 

			if (currentGen[xcoord][ycoord] == false){
				
				currentGen[xcoord][ycoord] = true;
				i++;
				
			}//increase i if not a duplicate
			
			
		}

		lifeWindow(input, currentGen);
		
	}//fillArray
	
	
	public void lifeWindow (int input, boolean[][]currentGen ) {
		
		Group root = new Group();
		Stage stage2 = new Stage();
		stage2.setTitle("Life");
		Scene scene2 = new Scene (root, 450, 450);
		Canvas myCanvas = new Canvas (450, 450);
		root.getChildren().add(myCanvas);
		
		int i, j;
		
		Rectangle [][] cell = new Rectangle [150][150];

			for (i = 0; i < 150; i++){
				
				for (j = 0; j < 150; j++){
					
					if (currentGen[i][j] == true){

						cell[i][j] = new Rectangle();
						cell[i][j].setX(j*3);
						cell[i][j].setY(i*3);
						cell[i][j].setWidth(3);
						cell[i][j].setHeight(3);
						cell[i][j].setStroke(Color.BLACK);
						
						root.getChildren().add(cell[i][j]);

						
					}
					else if (currentGen[i][j] == false){

						cell[i][j] = new Rectangle();
						cell[i][j].setX(j*3);
						cell[i][j].setY(i*3);
						cell[i][j].setWidth(3);
						cell[i][j].setHeight(3);
						cell[i][j].setStroke(Color.WHITE);
						cell[i][j].setFill(Color.WHITE);
						
						root.getChildren().add(cell[i][j]);

					}
					
				}//for loop for individual rows of cells, x coordinate
				
			}//for loop for whole rows of cells, y coordinate
	
 
		stage2.setScene(scene2);
		stage2.show();
		
		nextGeneration(currentGen, cell);
		
		
	}//lifeWindow
	
	public void nextGeneration( boolean[][]currentGen, Rectangle [][] cell ){
		
		boolean[][] nextGen = new boolean [150][150];
		boolean[][] temp = new boolean [150][150];
		
		KeyFrame k = new KeyFrame(Duration.millis(100),
			e ->
			{
						
				for (int i = 0; i < 150; i++){
					
					for (int j = 0; j < 150; j++){
						
						if (currentGen[i][j] == true){

							int neighbors = cellChecker(cell, i, j, currentGen);
						
							if (neighbors >= 2 && neighbors <= 3){
								nextGen[i][j] = true;
							}
							else{
								nextGen[i][j] = false;
							}

						}//checks alive cells
						
						else if (currentGen[i][j] == false){

							int neighbors = cellChecker(cell, i, j, currentGen);
						
							if (neighbors == 3){
								nextGen[i][j] = true;
							}
							else{
								nextGen[i][j] = false;
							}
						
						}//checks dead cells
						
					}//for loop for individual rows of cells, x coordinate
					
				}//for loop for whole rows of cells, y coordinate
			
				//determines next generation
		
				for (int i = 0; i < 150; i++){
					
					for (int j = 0; j < 150; j++){
					
						if (nextGen[i][j] == true){

							cell[i][j].setStroke(Color.BLACK);
							cell[i][j].setFill(Color.BLACK);

						}
						else if (nextGen[i][j] == false){

							cell[i][j].setStroke(Color.WHITE);
							cell[i][j].setFill(Color.WHITE);

						}
					
					}//for loop for individual rows of cells, x coordinate
				
				}//for loop for whole rows of cells, y coordinate
			
			//changes cells in next generation
			
			});

        Timeline t = new Timeline(k);
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
		
		
	}//nextGeneration
	
	
	public static int cellChecker(Rectangle [][] cell, int i, int j, boolean[][] currentGen){
		
		int neighbors = 0;
		int x, y;
		
		x = j - 1;
		y = i + 1;
		if ( x > 0 && x < 150 && y > 0 && y < 150){	
			if (cell[x][y].getStroke().equals(Color.BLACK)){	
				neighbors++;	
			}//increases neighbors if a surrounding cell is black
		}//checks if square is inside window
		
		x = j;
		y = i + 1;
		if ( x > 0 && x < 150 && y > 0 && y < 150){	
			if (cell[x][y].getStroke().equals(Color.BLACK)){	
				neighbors++;	
			}//increases neighbors if a surrounding cell is black	
		}//checks if square is inside window
		
		x = j + 1;
		y = i + 1;
		if ( x > 0 && x < 150 && y > 0 && y < 150){
			if (cell[x][y].getStroke().equals(Color.BLACK)){
				neighbors++;
			}//increases neighbors if a surrounding cell is black
		}//checks if square is inside window
		
		x = j + 1;
		y = i;
		if ( x > 0 && x < 150 && y > 0 && y < 150){
			if (cell[x][y].getStroke().equals(Color.BLACK)){
				neighbors++;
			}//increases neighbors if a surrounding cell is black
		}//checks if square is inside window
		
		x = j + 1;
		y = i - 1;
		if ( x > 0 && x < 150 && y > 0 && y < 150){	
			if (cell[x][y].getStroke().equals(Color.BLACK)){	
				neighbors++;	
			}//increases neighbors if a surrounding cell is black	
		}//checks if square is inside window
		
		x = j;
		y = i - 1;
		if ( x > 0 && x < 150 && y > 0 && y < 150){	
			if (cell[x][y].getStroke().equals(Color.BLACK)){	
				neighbors++;	
			}//increases neighbors if a surrounding cell is black	
		}//checks if square is inside window
		
		x = j - 1;
		y = i - 1;
		if ( x > 0 && x < 150 && y > 0 && y < 150){
			if (cell[x][y].getStroke().equals(Color.BLACK)){
				neighbors++;
			}//increases neighbors if a surrounding cell is black
		}//checks if square is inside window
		
		x = j - 1;
		y = i;
		if ( x > 0 && x < 150 && y > 0 && y < 150){
			if (cell[x][y].getStroke().equals(Color.BLACK)){
				neighbors++;
			}//increases neighbors if a surrounding cell is black
		}//checks if square is inside window
		
		return neighbors; 
		
	}//method to determine the amount of neighbors, checks every surrounding cell's color
	
	
}

