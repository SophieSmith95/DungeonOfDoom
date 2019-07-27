import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

public class GUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Map map;
	private GameLogic gameLogic;
	private JTextArea infoText;
	private JLabel[][] map2;
	
	public GUI(){
		map = new Map();
		gameLogic = new GameLogic();
		gui();
	}
	
	/*This method is used to listen for when a button is pressed by a user
	  and to carry out the corresponding action. */
	public void actionPerformed(ActionEvent e){
		String actionCommand = e.getActionCommand(); //This detects what button is pressed.
		if(actionCommand.equals("Play Game")){
			infoText.setText(map.printGameName(new File("Map.txt")));
			gameLogic.placePlayer('P');
			mapIcons(gameLogic.look());
		}else if(actionCommand.equals("Move Up")){
			infoText.setText(gameLogic.processCommand("move n"));
			mapIcons(gameLogic.look());
		}else if(actionCommand.equals("Move Down")){
			infoText.setText(gameLogic.processCommand("move s"));
			mapIcons(gameLogic.look());
		}else if(actionCommand.equals("Move Right")){
			infoText.setText(gameLogic.processCommand("move e"));
			mapIcons(gameLogic.look());
		}else if(actionCommand.equals("Move Left")){
			infoText.setText(gameLogic.processCommand("move w"));
			mapIcons(gameLogic.look());
		}else if(actionCommand.equals("Pickup")){
			infoText.setText(gameLogic.processCommand("Pickup"));
		}else if(actionCommand.equals("Quit")){
			if(gameLogic.processCommand("Quit").equals("You have won!")){
				System.exit(0);
			}else{
				infoText.setText(gameLogic.processCommand("Quit"));
			}
		}else if(actionCommand.equals("Hello")){
			infoText.setText(gameLogic.processCommand("Hello"));
		}else if(actionCommand.equals("Open Door")){
			infoText.setText(gameLogic.openDoor());
			mapIcons(gameLogic.look());
		}else if(actionCommand.equals("Slay")){
			infoText.setText(gameLogic.slayDragon());
			mapIcons(gameLogic.look());
		}else if(actionCommand.equals("Gods Eye View")){
			infoText.setText(gameLogic.processCommand("GodsEye"));
		}
	}
	
	
	//This method sets up the initial GUI. 
	public void gui(){
		//Setting up a new JFrame.
		JFrame mainFrame = new JFrame("Dungeons Of Doom");
		mainFrame.setBackground(Color.WHITE);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Setting up a new JPanel.
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);

		//This allows the mainPanel to be split into three sections.
		GridBagLayout layout = new GridBagLayout();
		mainPanel.setLayout(layout);
		GridBagConstraints gbc = new GridBagConstraints();
		
		JPanel move = getMovePanel();
		move.setBackground(Color.WHITE);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		mainPanel.add(move, gbc);

		JPanel mapPanel = getMapPanel();
		mapPanel.setBackground(Color.WHITE);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 0;
		mainPanel.add(mapPanel,gbc);
		
		JPanel commandPanel = getCommandPanel();
		commandPanel.setBackground(Color.WHITE);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 2;
		gbc.gridy = 0;
		mainPanel.add(commandPanel, gbc);
		
		
		mainFrame.add(mainPanel);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	
	//This method sets up the central map panel of the GUI.
	private JPanel getMapPanel(){
		//This sets up the area where the map it to be printed.
		JPanel mapPanel = new JPanel();
		mapPanel.setBackground(Color.WHITE);
		mapPanel.setLayout(new BoxLayout(mapPanel, BoxLayout.Y_AXIS));
		mapPanel.add(mapPanel());
		
		//This sets up the area when any extra text can be printed.
		JPanel infoPanel = getInfoPanel();
		infoPanel.setBackground(Color.WHITE);
		infoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		mapPanel.add(infoPanel);
		
		return mapPanel;
	}
	
	//This method creates a new text area to display text to the screen.
	private JPanel getInfoPanel(){
		JPanel infoPanel = new JPanel();
		infoText = new JTextArea(10, 10);
		infoText.setAlignmentX(Component.CENTER_ALIGNMENT);
		infoPanel.add(infoText);
		return infoPanel;
	}
	
	//This method sets up all the move buttons.
	private JPanel getMovePanel(){
		JPanel movePanel = new JPanel();
		movePanel.setBackground(Color.white);

		GridBagLayout layout = new GridBagLayout();
		movePanel.setLayout(layout);
		GridBagConstraints gbc = new GridBagConstraints();
		
		JButton blank = new JButton();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		blank.setBackground(Color.white);
		blank.setOpaque(true);
		blank.setBorderPainted(false);
		movePanel.add(blank,gbc);
		
		JButton up = new JButton("Move Up");
		up.setMnemonic(KeyEvent.VK_W);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 0;
		up.setIcon(new ImageIcon("/Users/Sophie/Desktop/icons/UpArrow.jpeg"));
		movePanel.add(up, gbc);
		up.addActionListener(this);
		
		JButton blank2 = new JButton();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 2;
		gbc.gridy = 0;
		blank2.setBackground(Color.white);
		blank2.setOpaque(true);
		blank2.setBorderPainted(false);
		movePanel.add(blank2,gbc);
		
		JButton left = new JButton("Move Left");
		left.setMnemonic(KeyEvent.VK_A);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 1;
		left.setIcon(new ImageIcon("/Users/Sophie/Desktop/icons/LeftArrow.png"));
		movePanel.add(left, gbc);
		left.addActionListener(this);
		
		JButton blank3 = new JButton();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 1;
		blank3.setBackground(Color.white);
		blank3.setOpaque(true);
		blank3.setBorderPainted(false);
		movePanel.add(blank3,gbc);
		
		JButton right = new JButton("Move Right");
		right.setMnemonic(KeyEvent.VK_D);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 2;
		gbc.gridy = 1;
		right.setIcon(new ImageIcon("/Users/Sophie/Desktop/icons/RightArrow.png"));
		movePanel.add(right, gbc);
		right.addActionListener(this);
		
		JButton blank4 = new JButton();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 2;
		blank4.setBackground(Color.white);
		blank4.setOpaque(true);
		blank4.setBorderPainted(false);
		movePanel.add(blank4,gbc);
		
		JButton down = new JButton("Move Down");
		down.setMnemonic(KeyEvent.VK_S);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 2;
		down.setIcon(new ImageIcon("/Users/Sophie/Desktop/icons/DownArrow.jpeg"));
		movePanel.add(down,gbc);
		down.addActionListener(this);
		
		JButton blank5 = new JButton();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 2;
		gbc.gridy = 2;
		blank5.setBackground(Color.white);
		blank5.setOpaque(true);
		blank5.setBorderPainted(false);
		movePanel.add(blank5,gbc);
		
		return movePanel;
	}
	
	//This method sets up all the command buttons.
	private JPanel getCommandPanel(){
		JPanel commandPanel = new JPanel();
		commandPanel.setBackground(Color.WHITE);
		
		GridBagLayout layout = new GridBagLayout();
		commandPanel.setLayout(layout);
		GridBagConstraints gbc = new GridBagConstraints();
		
		JButton playGame = new JButton("Play Game");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		commandPanel.add(playGame, gbc);
		playGame.addActionListener(this);
		
		JButton quit = new JButton("Quit");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 0;
		commandPanel.add(quit,gbc);
		quit.addActionListener(this);
		
		JButton pickUp = new JButton("Pickup");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 1;
		commandPanel.add(pickUp, gbc);
		pickUp.addActionListener(this);
		
		JButton hello = new JButton("Hello");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 1;
		commandPanel.add(hello, gbc);
		hello.addActionListener(this);
		
		JButton slay = new JButton("Slay");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 2;
		commandPanel.add(slay, gbc);
		slay.addActionListener(this);

		JButton openDoor = new JButton("Open Door");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 2;
		commandPanel.add(openDoor, gbc);
		openDoor.addActionListener(this);
		
		JButton godsEye = new JButton("Gods Eye View");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		commandPanel.add(godsEye, gbc);
		godsEye.addActionListener(this);
		
		return commandPanel;
	}
	
	//This method sets all the characters from the lookReply() to JLabels.
	private JPanel mapPanel(){
		JPanel mapPanel = new JPanel(new GridLayout(5,5));
		map2 = new JLabel[5][5];	
		/*Loop through the rows and columns and create a JLabel each time and add them
		  to the JPanel. */
		for(int i = 0; i<5; i++){
			for(int j = 0; j<5; j++){
				JLabel label = new JLabel();
				map2[i][j] = label;
				mapPanel.add(map2[i][j]);
			}
		}
		return mapPanel;
	}
	
	/*This method looks at each character from the lookReply() and replaces
	  them with their corresponding icon.*/
	private void mapIcons(String lookReply){
		String[] lookString = new String[5];
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
		 for(int i = 0; i<5; i++){
			 for(int l = 0; l<5; l++){
				 lookString[i].charAt(l);
				 if(lookString[i].charAt(l) == '#'){
					 map2[i][l].setIcon(wall);
				 }else if(lookString[i].charAt(l) == '.'){
					 map2[i][l].setIcon(floor);
				 }else if(lookString[i].charAt(l) == 'P' && gameLogic.getNumberOfSwords()<=0){
					 map2[i][l].setIcon(player);
				 }else if(lookString[i].charAt(l) == 'G'){
					 map2[i][l].setIcon(coin);
				 }else if(lookString[i].charAt(l) == 'E'){
					 map2[i][l].setIcon(exit);
				 }else if(lookString[i].charAt(l) == 'D'){
					 map2[i][l].setIcon(door);
				 }else if(lookString[i].charAt(l) == 'K'){
					 map2[i][l].setIcon(key);
				 }else if(lookString[i].charAt(l) == 'M'){
					 map2[i][l].setIcon(dragon);
				 }else if(lookString[i].charAt(l) == 'S'){
					 map2[i][l].setIcon(sword);
				 }else if(lookString[i].charAt(l) == 'P' && gameLogic.getNumberOfSwords()>0){
					 map2[i][l].setIcon(playerWithSword);
				 }
			 }
		 }
	}
	
 	public static void main (String[] args){
		new GUI();
	}
 		
}