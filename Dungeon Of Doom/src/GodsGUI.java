import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GodsGUI {
	
	private JLabel[][] map;
	private GameLogic gameLogic;
	
	public GodsGUI(){
		gameLogic = new GameLogic();
		gui();
	}
	
	//This method sets up the initial GUI. 
	public void gui(){
		//Setting up a new JFrame.
		JFrame mainFrame = new JFrame("Gods Eye View");
		mainFrame.setBackground(Color.BLACK);
		
		//Setting up a new JPanel.
		JPanel move = new JPanel();
		move.add(mapPanel());
		mapIcons(gameLogic.getMapString());
		move.setBackground(Color.BLACK);
		
		mainFrame.add(move);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}

	/*This method looks at each character from the lookReply() and replaces
	  them with their corresponding icon.*/
	private void mapIcons(String lookReply){
		String[] lookString = new String[9];
		lookString = lookReply.split("\n");
		 ImageIcon wall = new ImageIcon("/Users/Sophie/Desktop/icons/wall.png");
		 ImageIcon floor = new ImageIcon("/Users/Sophie/Desktop/icons/floor.png");
		 ImageIcon player = new ImageIcon("/Users/Sophie/Desktop/icons/player.jpg");
		 ImageIcon playerWithSword = new ImageIcon("/Users/Sophie/Desktop/icons/playerS.jpg");
		 ImageIcon coin = new ImageIcon("/Users/Sophie/Desktop/icons/coins.jpg");
		 ImageIcon exit = new ImageIcon("/Users/Sophie/Desktop/icons/exit.jpg");
		 ImageIcon door = new ImageIcon("/Users/Sophie/Desktop/icons/door.jpg");
		 ImageIcon key = new ImageIcon("/Users/Sophie/Desktop/icons/key.jpg");
		 ImageIcon sword = new ImageIcon("/Users/Sophie/Desktop/icons/sword.jpg");
		 ImageIcon dragon = new ImageIcon("/Users/Sophie/Desktop/icons/monster.jpg");
		 //Loop through each individual character and replace them with their corresponding icon.
		 for(int i = 0; i<9; i++){
			 for(int l = 0; l<19; l++){
				 lookString[i].charAt(l);
				 if(lookString[i].charAt(l) == '#'){
					 map[i][l].setIcon(wall);
				 }else if(lookString[i].charAt(l) == '.'){
					 map[i][l].setIcon(floor);
				 }else if(lookString[i].charAt(l) == 'P' && gameLogic.getNumberOfSwords()<=0){
					 map[i][l].setIcon(player);
				 }else if(lookString[i].charAt(l) == 'G'){
					 map[i][l].setIcon(coin);
				 }else if(lookString[i].charAt(l) == 'E'){
					 map[i][l].setIcon(exit);
				 }else if(lookString[i].charAt(l) == 'D'){
					 map[i][l].setIcon(door);
				 }else if(lookString[i].charAt(l) == 'K'){
					 map[i][l].setIcon(key);
				 }else if(lookString[i].charAt(l) == 'M'){
					 map[i][l].setIcon(dragon);
				 }else if(lookString[i].charAt(l) == 'S'){
					 map[i][l].setIcon(sword);
				 }else if(lookString[i].charAt(l) == 'P' && gameLogic.getNumberOfSwords()>0){
					 map[i][l].setIcon(playerWithSword);
				 }
			 }
		 }
	}

	//This method sets all the characters from the lookReply() to JLabels.
	private JPanel mapPanel(){
		JPanel mapPanel = new JPanel(new GridLayout(9,19));
		map = new JLabel[9][19];
		/*Loop through the rows and columns and create a JLabel each time and add them
		  to the JPanel. */
		for(int i = 0; i<9; i++){
			for(int j = 0; j<19; j++){
				JLabel label = new JLabel();
				map[i][j] = label;
				mapPanel.add(map[i][j]);
			}
		}
		return mapPanel;
	}


}

