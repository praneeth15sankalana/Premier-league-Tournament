import java.util.Scanner;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


public class PremireleagueManager  implements LeagueManager, Serializable   {
	private static final long seralVersionUID = 3L;

	
	private final ArrayList<FootballClub> league;
	
	private final ArrayList<Match> matches;
	FootballClub clb;
	Match match;
 	List <Match> matchList = new ArrayList <Match>();
	
 	
	public PremireleagueManager ()  {
		
		
		league = new ArrayList<>();
		matches = new ArrayList<>();
		
		try {
			displaymanu();
		}
		catch(IOException e){
			e.printStackTrace();
			
		}
		
	}



	@Override
	public void displaymanu() throws IOException  {
		
		try {
			read();
		}
		catch(IOException e){
			e.printStackTrace();
			
		}
		while (true) {
			
			System.out.println("    ----- Premire League ------");
			System.out.println("");
			System.out.println("Add Football club to league ( Press 1 ) ");
			System.out.println("Delete club from league ( Press 2 )  ");
			System.out.println("Display statistics for club  (Press 3 ) ");
			System.out.println("Display premire league table ( Press 4 ) ");
			System.out.println("Add played match ( Press 5 ) ");
			System.out.println("Quit ( Press 6 ) ");
		
			Scanner scanner = new Scanner(System.in);
			System.out.println("");
			System.out.print(">> ");
			String line = scanner.nextLine();
			int command = 0;
			
			try {
				command = Integer.parseInt(line);
			}catch (Exception e) {
				
			}
			
			switch(command) {
			case 1 :
				addTeam();
				
			break;
			
			case 2 :
				deleteTeam();
				
			break;
				
			case 3 :
				displayStatistics();
				
			break;
			
			case 4 :
				displayTable();
				
			break;	
			
			case 5 :
				addPlayedmatch();
				
			break;	
			
			case 6 :
				gUI();
				//exit();
			
				//gUI();
				
				
				//write();
				//System.exit(0);
				
			break;	
			
			case 7:
				exit();
				
			break;	
			
			
				
			default:
				System.out.println(" Please enter valid input..! ");
			}
			
		}
		}
	@Override
	public void exit() throws IOException   {
		write();
		System.exit(0);
		
	}
	
	
	@Override
	public void addTeam() {
		
		Scanner scanner = new Scanner(System.in);
		

		System.out.println("Enter Club Name  :  ");
		String add = scanner.nextLine();

		for(FootballClub footballClubIsExit : league) {
			
			if(add.equals(footballClubIsExit.getName())) {
			System.out.println("That Club is already in the league.");
			System.out.println("");
			return;
			}
		}
		
		
		System.out.println("Enter Location  :  ");
		String loca = scanner.nextLine();
	
		clb = new FootballClub(add,loca,0,0,0,0,0,0,0);
		league.add(clb);

		
	}
	@Override
	public void deleteTeam() {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter Club Name  :  ");
		String del  = scanner.nextLine();
		for(FootballClub clb : league) {
			if(clb.getName().equals(del)) {
				league.remove(clb);
				System.out.println("Club  "   +    clb.getName()   +   "  removed. ");
				return;
			}
			
		}
		System.out.println("That club isn't in league");
	}
	@Override
	public void displayStatistics() {
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter Club Name  :  ");
		String line = scanner.nextLine();
		for (FootballClub clb : league) {
			if(clb.getName().equals(line)) {
				System.out.println("Club " + clb.getName());
				System.out.println("Matches won    : " + clb.getWins());
				System.out.println("Matches lost   : " + clb.getDefeats());
				System.out.println("Matches draw   : " + clb.getDraws());
				System.out.println("Scored goals   : " + clb.getNumberofgoalscored());
				System.out.println("Recived goals  : " + clb.getNumberofgoalsreceived());
				System.out.println("Points         : " + clb.getNumberofpoints());
				System.out.println("Matches played : " + clb.getNumberofmatchesplayed());				
				return;
			}
		}
		System.out.println("That club isn't in league");
		
	}
	@Override
	public void displayTable() {
		 Collections.sort(league , new Compare());
		 for(FootballClub clb : league) {
			 System.out.println("| Club : |"  +  clb.getName()  +  "|  Points : |" + clb.getNumberofpoints()  +  "| Goal difference :  |" +  (clb.getNumberofgoalscored()-clb.getNumberofgoalsreceived()) );
		 }

		 
		
	}
	@Override
	public void addPlayedmatch() {
		Scanner scanner = new Scanner(System.in);
		Date date;
		while(true) {
			
		 System.out.print("Enter date (mm-dd-yyyy) : ");
		 String line = scanner.nextLine();
		 
		 try {
			 date = new SimpleDateFormat("mm-dd-yyyy").parse(line);
			 break;
			 
		 }catch(ParseException ex) {
			 System.out.println("Enter correct date format");
			 return;
		 }
		}
		 String team1 = null;
		 int a = 0;
		while(true) {
		 System.out.println("Enter team 1 : ");
		 String line = scanner.nextLine();
		 FootballClub first = null;
		 	for(FootballClub clb : league) {
		 		if (clb.getName().equals(line));
		 		first=clb;
		 		team1 = line;
		 		a = 1;
		 	}
		 	if(first == null) {
		 		System.out.println("That club not in league.");
		 		return;
		 	}
		 	if(a == 1) {
		 		break;
		 	}
		}
		 	
		   String team2 = null;
		   int b = 0;
		while(true) {
		 	 System.out.println("Enter team 2 : ");
			 String line = scanner.nextLine();
			 FootballClub second = null;
			 	for(FootballClub clb : league) {
			 		if (clb.getName().equals(line));
			 		second=clb;
			 		team2 = line;
			 		b = 1;
			 	}
			 	if(second == null) {
			 		System.out.println("That club not in league.");
			 		return;
			 	}
			 	if(b == 1) {
			 		break;
			 	}
		}
			 	
			 	System.out.println("Enter team 1 goals : ");
			 	String line1 = scanner.nextLine();
			 	int firstGoals =-1;
			 		try {
			 			firstGoals=Integer.parseInt(line1);
			 		}catch (Exception e) {
			 			System.out.println("invalid Score");
			 		}
			 	if(firstGoals <= -1) {
			 		System.out.println("plese enter number of goals");
			 		return;
			 	}
			 	
			 	
			 	System.out.println("Enter team 2 goals : ");
			 	String line2 = scanner.nextLine();
			 	int secondGoals =-1;
			 		try {
			 			secondGoals=Integer.parseInt(line2);
			 		}catch (Exception e) {
			 			System.out.println("invalid Score");
			 			
			 		}
			 	if(secondGoals <= -1) {
			 		System.out.println("plese enter number of goals");
			 		return;
			 	}
			 	

			 	
			 	for(FootballClub footballObj : league) {
			 		
			 		if(team1.equals(footballObj.getName())) {
			 			

					 	if(firstGoals > secondGoals) {
					 		footballObj.setWins(footballObj.getWins() + 1);
					 		footballObj.setNumberofpoints(footballObj.getNumberofpoints() + 3);
					 		footballObj.setNumberofgoalscored(footballObj.getNumberofgoalscored() + firstGoals);
					 		footballObj.setNumberofgoalsreceived(footballObj.getNumberofgoalsreceived() + secondGoals);
					 		footballObj.setNumberofmatchesplayed(footballObj.getNumberofmatchesplayed() + 1);
					 		
					 		
					 	}
					 	
					 	else if (firstGoals < secondGoals) {
					 		footballObj.setDefeats(footballObj.getDefeats() + 1 );
					 		
					 		footballObj.setNumberofgoalscored(footballObj.getNumberofgoalscored() + firstGoals);
					 		footballObj.setNumberofgoalsreceived(footballObj.getNumberofgoalsreceived() + secondGoals);
					 		footballObj.setNumberofmatchesplayed(footballObj.getNumberofmatchesplayed() + 1);
					 	}
					 	
					 	else {
					 		footballObj.setDraws(footballObj.getDraws() + 1);
					 		footballObj.setNumberofgoalscored(footballObj.getNumberofgoalscored() + firstGoals);
					 		footballObj.setNumberofgoalsreceived(footballObj.getNumberofgoalsreceived() + secondGoals);
					 		footballObj.setNumberofmatchesplayed(footballObj.getNumberofmatchesplayed() + 1);
					 		
					 	}
			 		}
			 		
			 		if(team2.equals(footballObj.getName())) {
			 			
			 			if(firstGoals > secondGoals) {
					 		footballObj.setWins(footballObj.getWins() + 1);
					 		footballObj.setNumberofpoints(footballObj.getNumberofpoints() + 3);
					 		footballObj.setNumberofgoalscored(footballObj.getNumberofgoalscored() + secondGoals);
					 		footballObj.setNumberofgoalsreceived(footballObj.getNumberofgoalsreceived() + firstGoals);
					 		footballObj.setNumberofmatchesplayed(footballObj.getNumberofmatchesplayed() + 1);
					 		
					 		
					 	}
					 	
					 	else if (firstGoals < secondGoals) {
					 		footballObj.setDefeats(footballObj.getDefeats() + 1 );
					 		
					 		footballObj.setNumberofgoalscored(footballObj.getNumberofgoalscored() + secondGoals);
					 		footballObj.setNumberofgoalsreceived(footballObj.getNumberofgoalsreceived() + firstGoals);
					 		footballObj.setNumberofmatchesplayed(footballObj.getNumberofmatchesplayed() + 1);
					 	}
					 	
					 	else {
					 		footballObj.setDraws(footballObj.getDraws() + 1);
					 		footballObj.setNumberofgoalscored(footballObj.getNumberofgoalscored() + secondGoals);
					 		footballObj.setNumberofgoalsreceived(footballObj.getNumberofgoalsreceived() + firstGoals);
					 		footballObj.setNumberofmatchesplayed(footballObj.getNumberofmatchesplayed() + 1);
					 		
					 	}
					 	
			 		}
			 	}
	}
			 	
	@Override		 	
	public void write() throws IOException  {
		
		 File file1 = new File("MATCH.txt");

	        PrintWriter writer1 = new PrintWriter("MATCH.txt");
	        writer1.print("");

	        FileOutputStream fileOutputStream1 = new FileOutputStream(file1,true);
	        ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(fileOutputStream1);

	        Iterator iterator1 = league.iterator();
	        while (iterator1.hasNext()){
	            Match newMatch = (Match) iterator1.next();
	            objectOutputStream1.writeObject(newMatch);

	        }
	        writer1.close();
	        
	}
	
	@Override
	public void read() throws IOException {

		FileInputStream input = new FileInputStream("MATCH.txt");

		ObjectInputStream input1 = new ObjectInputStream(input);
		List<Match> li = new ArrayList<>();

		while (true) {
			try {

				Match read = (Match) input1.readObject();
				li.add(read);

			} catch (IOException | ClassNotFoundException e) {
				break;
			}
		}
		matchList.addAll(li);
	}


		public void gUI() {



//	     Create Stages , windows    //

			Stage windowMainMenu = new Stage();
			windowMainMenu.setTitle("Main menu");
//
//			Stage windowScoreboard = new Stage();
//			windowScoreboard.setTitle("Score board");
//
//			Stage windowGeneratematch = new Stage();
//			windowGeneratematch.setTitle("Generate match");
//
//			Stage windowdate = new Stage();
//			windowdate.setTitle("Date");
//
//			Stage windowSearch = new Stage();
//			windowSearch.setTitle(" Search match");

//	      Button Controls    //


///          Layout 1 - Main Menu                       ///	 


			Pane layoutMainMenu = new Pane();
			layoutMainMenu.setStyle("-fx-background-color:#DC143C ;");

			Label labelCaption = new Label("Premire league Manager");
//			labelCaption.setTranslateY(-300);
			labelCaption.setFont(new Font("Arial", 60));
			labelCaption.setStyle("-fx-background-color: #B22222;");
//			layoutMainMenu.getChildren().add(labelCaption);

			Button buttonScoreboard = new Button("Score board ");
//			buttonScoreboard.setTranslateY(-180);
			buttonScoreboard.setMaxWidth(500);
			buttonScoreboard.setMaxHeight(60);
			buttonScoreboard.setFont(new Font("Arial", 25));
//			layoutMainMenu.getChildren().add(buttonScoreboard);

			Button buttonGeneratematch = new Button("Generate match");
//			buttonGeneratematch.setTranslateY(-100);
			buttonGeneratematch.setMaxWidth(500);
			buttonGeneratematch.setMaxHeight(60);
			buttonGeneratematch.setFont(new Font("Arial", 25));
//			layoutMainMenu.getChildren().add(buttonGeneratematch);

			Button buttonDate = new Button("Date");
//			buttonDate.setTranslateY(-20);
			buttonDate.setMaxWidth(500);
			buttonDate.setMaxHeight(60);
			buttonDate.setFont(new Font("Arial", 25));
//			layoutMainMenu.getChildren().add(buttonDate);

			Button buttonSearchmatch = new Button("Search match");
//			buttonSearchmatch.setTranslateY(60);
			buttonSearchmatch.setMaxWidth(500);
			buttonSearchmatch.setMaxHeight(60);
			buttonSearchmatch.setFont(new Font("Arial", 25));
//			layoutMainMenu.getChildren().add(buttonSearchmatch);

			Button buttonExit = new Button("Exit");
//			buttonExit.setTranslateY(140);
			buttonExit.setMaxWidth(500);
			buttonExit.setMaxHeight(60);
			buttonExit.setFont(new Font("Arial", 25));
//			layoutMainMenu.getChildren().add(buttonExit);

			VBox vB = new VBox();
			vB.setSpacing(10);
			vB.setLayoutX(50);
			vB.getChildren().addAll(labelCaption,buttonScoreboard,buttonGeneratematch,buttonDate,buttonSearchmatch,buttonExit);
			layoutMainMenu.getChildren().add(vB);
//			BorderPane bP = new BorderPane();
//			bP.setCenter(layoutMainMenu);
			Scene scene = new Scene(layoutMainMenu, 800, 750);

			windowMainMenu.setScene(scene);
			windowMainMenu.showAndWait();
//			windowMainMenu.showAndWait();

//	     menu  Button Controls

	   /*     buttonScoreboard.setOnAction(value ->{
	            
	            windowMainMenu.close();
	            windowScoreboard.showAndWait();
	        });  
	        
	        buttonGeneratematch.setOnAction(value ->{
	            
	            windowMainMenu.close();
	            windowGeneratematch.showAndWait();
	        });  
	        
	        buttonDate.setOnAction(value ->{
	            
	            windowMainMenu.close();
	            windowdate.showAndWait();
	        }); 
	        
	        buttonSearchmatch.setOnAction(value ->{
	           
	            windowMainMenu.close();
	            windowSearch.showAndWait();
	        });  
	        
	        buttonExit.setOnAction(value ->{
	            windowMainMenu.close();
	        });  
	       */
			///          Layout 2   -   Score board                      ///
//
			Pane layoutScoreboard = new Pane();

			layoutScoreboard.setStyle("-fx-background-color: #1eb2a6;");

			TableView<Match> table;

			//club name column
			TableColumn<Match, String> clubNameColumn = new TableColumn<>("Club Name");
			clubNameColumn.setPrefWidth(200);
			clubNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

			//LOCATION column
			TableColumn<Match, String> clubLocationColumn = new TableColumn<>("Club location");
			clubLocationColumn.setPrefWidth(200);
			clubLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

			//WINS column
			TableColumn<Match, String> winsColumn = new TableColumn<>("Wins");
			winsColumn.setPrefWidth(200);
			winsColumn.setCellValueFactory(new PropertyValueFactory<>("wins"));

			//draws column
			TableColumn<Match, String> drawsColumn = new TableColumn<>("Draws");
			drawsColumn.setPrefWidth(200);
			drawsColumn.setCellValueFactory(new PropertyValueFactory<>("draws"));

			//defeats column
			TableColumn<Match, String> defeatsColumn = new TableColumn<>("Defeats");
			defeatsColumn.setPrefWidth(200);
			defeatsColumn.setCellValueFactory(new PropertyValueFactory<>("defeats"));

			//goals received column
			TableColumn<Match, String> goalsReceivedColumn = new TableColumn<>("goals received");
			goalsReceivedColumn.setPrefWidth(200);
			goalsReceivedColumn.setCellValueFactory(new PropertyValueFactory<>("goalsReceived"));

			//goals Scored column
			TableColumn<Match, String> goalsScoredColumn = new TableColumn<>("goals Scored");
			goalsScoredColumn.setPrefWidth(200);
			goalsScoredColumn.setCellValueFactory(new PropertyValueFactory<>("goalsScored"));

			//points column
			TableColumn<Match, String> pointsColumn = new TableColumn<>("points");
			pointsColumn.setPrefWidth(200);
			pointsColumn.setCellValueFactory(new PropertyValueFactory<>("points"));


			table = new TableView<>();
//			table.setItems();
			table.getColumns().addAll(clubNameColumn, clubLocationColumn, winsColumn, drawsColumn, defeatsColumn, goalsReceivedColumn, goalsScoredColumn, pointsColumn);
			Scene scene1 = new Scene(layoutScoreboard, 800, 750);
			layoutScoreboard.getChildren().add(table);
			buttonScoreboard.setOnAction(event -> {
				windowMainMenu.setScene(scene1);
				windowMainMenu.showAndWait();
			});


//
//			Button buttonBack0 = new Button("Back");
//			buttonBack0.setTranslateX(50);
//			buttonBack0.setTranslateY(400);
//			buttonBack0.setMaxWidth(700);
//			buttonBack0.setMinHeight(50);
//			layoutScoreboard.getChildren().add(buttonBack0);
//
//			Scene sceneScoreboard = new Scene(layoutScoreboard, 800, 500);
//
//			windowScoreboard.setScene(sceneScoreboard);
//
//			buttonBack0.setOnAction(value -> {
//				windowScoreboard.close();
//				windowMainMenu.showAndWait();
//			});
//
//			//  public ObservableList<Match>(){
//			// 	ObservableList<Match>match= FXCollections.observableArrayList();
//
//
//			//// LAYOUT   3 - Generate match  //
//
//
///*
//
//	   //// LAYOUT   4 - DATE  //
//
	        
//	        GridPane layoutdate = new GridPane();
//
//
//	        layoutdate.setStyle("-fx-background-color: #FF6347;");
//
//	        //Labels
//	        TextField textBoxdate1 = new TextField();
//	        Label labelTop5 = new Label("date     : ");
//	        labelTop5.setTranslateX(50);
//	        labelTop5.setTranslateY(100);
//	        labelTop5.setFont(new Font("Arial", 25));
//	        textBoxdate1.setStyle("-fx-background-color: #DC143C;");
//	        layoutdate.getChildren().add(labelTop5);
//
//	        Label labelTop6 = new Label("");
//	        labelTop6.setTranslateX(350);
//	        labelTop6.setTranslateY(300);
//	        labelTop6.setFont(new Font("Arial", 25));
//	        layoutdate.getChildren().add(labelTop6);
//
//	        TextField textBoxdate = new TextField();
//	        textBoxdate.setTranslateX(400);
//	        textBoxdate.setTranslateY(100);
//	        textBoxdate.setMaxWidth(350);
//	        textBoxdate.setFont(new Font(22));
//	        textBoxdate.setStyle("-fx-background-color: #A4EFDC;");
//	        layoutdate.getChildren().add(textBoxdate);
//
//	        Button buttonBack2 = new Button("Back");
//	        buttonBack2.setTranslateX(50);
//	        buttonBack2.setTranslateY(500);
//	        buttonBack2.setMaxWidth(300);
//	        buttonBack2.setMinHeight(70);
//	        layoutdate.getChildren().add(buttonBack2);
//
//	        Button buttonSearch = new Button("Search");
//	        buttonSearch.setOnAction(event -> {
//	            for(Match match:matchList){
//	                if(match.getDate().equals(textBoxdate.getText())){
//	                    labelTop6.setText("date:"+match.getDate()+"\n"+"team 1:"+match.getTeam1()+"\n"+"team 1 score:"+match.getTeam1Score()+"\n"+"team 2:"+match.getTeam2()+"\n"+"team 2 score:"+match.getTeam2Score());
//
//	                }
//	            }
//	        });
//	        buttonSearch.setTranslateX(430);
//	        buttonSearch.setTranslateY(500);
//	        buttonSearch.setMaxWidth(300);
//	        buttonSearch.setMinHeight(70);
//	        layoutdate.getChildren().add(buttonSearch);
//
//
//	        Scene scenedate = new Scene(layoutdate, 800, 600);
//
//	        windowdate.setScene(scenedate);
//
//	        buttonBack2.setOnAction(value ->{
//	            windowdate.close();
//	            windowMainMenu.showAndWait();
//	        });
//
//
//
//	  //              Layout 5 - search                      ///
//
//	        GridPane layoutSearch = new GridPane();
//
//	        layoutSearch.setStyle("-fx-background-color: #FF6347;");
//
//	        //Labels
//	        TextField textBoxDe = new TextField();
//	        Label labelTop8 = new Label("Enter date    : ");
//	        labelTop5.setTranslateX(50);
//	        labelTop5.setTranslateY(100);
//	        labelTop5.setFont(new Font("Arial", 25));
//	        textBoxDe.setStyle("-fx-background-color: #DC143C;");
//	        layoutSearch.getChildren().add(labelTop5);
//
//	        Label labelTop9 = new Label("");
//	        labelTop6.setTranslateX(350);
//	        labelTop6.setTranslateY(300);
//	        labelTop6.setFont(new Font("Arial", 25));
//	        layoutSearch.getChildren().add(labelTop6);
//
//
//	        TextField textBoxDet = new TextField();
//	        textBoxDet.setTranslateX(400);
//	        textBoxDet.setTranslateY(100);
//	        textBoxDet.setMaxWidth(350);
//	        textBoxDet.setFont(new Font(22));
//	        textBoxDet.setStyle("-fx-background-color: #A4EFDC;");
//	        layoutSearch.getChildren().add(textBoxDet);
//
//	        Button buttonBack1 = new Button("Back");
//	        buttonBack1.setTranslateX(50);
//	        buttonBack1.setTranslateY(500);
//	        buttonBack1.setMaxWidth(300);
//	        buttonBack1.setMinHeight(70);
//	        layoutSearch.getChildren().add(buttonBack1);
//
//	        Button buttonSearch2 = new Button("Search");
//	        buttonSearch.setOnAction(event -> {
//	            for(Match match:matchList){
//	                if(match.getDate()().equals(textBoxDe.getText())){
//	                    labelTop6.setText("team 1:"+match.getTeam1()+"\n"+"team 1 Score:"+match.getTeam1Score()+"\n"+"team 2:"+match.getTeam2()+"\n"+"team 2 score:"+match.getTeam2Score());
//
//	                }
//	            }
//	        });
//	        buttonSearch2.setTranslateX(430);
//	        buttonSearch2.setTranslateY(500);
//	        buttonSearch2.setMaxWidth(300);
//	        buttonSearch2.setMinHeight(70);
//	        layoutSearch.getChildren().add(buttonSearch);
//
//
//	        Scene sceneSearch = new Scene(layoutSearch, 800, 600);
//
//	        windowSearch.setScene(sceneSearch);
//
//	        buttonBack2.setOnAction(value ->{
//	            windowSearch.close();
//	            windowMainMenu.showAndWait();
//	        });
//*/

		}
	 }


	

