import java.util.ArrayList;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.*;
class Piece{
  private ArrayList<int[]> tempArray = new ArrayList<int[]>();
  private String icon;
  private final String path = "Icons/";
  protected int team;
  protected boolean canAct = true;;
  private int[][] check  = {
    {-1,-1},
    {-1,1},
    {1,1},
    {-1,1},
    {-1,0},
    {0,1},
    {1,0},
    {0,-1}
  };
  
  Piece(String icon, int team){
    this.icon = path+icon;
    this.team = team;
  }
    
  public int getTeam(){
    return team;
  }

  public ArrayList<int[]> getSquares(int row, int col, boolean card, boolean diag, int min, int max){
    ArrayList<int[]> squares = new ArrayList<int[]>();
    int checkMax = 8;
    int checkMin = 0;
    if (!diag){
      checkMin = 4;
    }
    if (!card){
      checkMax = 4;
    }
    for (int i=min; i<max; i++){
      for (int square=checkMin; square<checkMax; square++){
        int[] cord = new int[2];
        cord[0] = row + check[square][0]*i;
        cord[1] = col + check[square][1]*i;
        squares.add(cord);
      }
    }
    return squares;
  }
  
  public ArrayList<int[]>move(Coordinate[][] board, int row, int col, boolean card, boolean diag, int min, int max){
    ArrayList<int[]> squares = getSquares(row, col, card, diag, min, max);
    for (int i=0; i<squares.size(); i++){
      int[] square = squares.get(i);
      if (board[square[0]][square[1]].getPiece()!=null){
        squares.remove(squares.get(i));
      }
    }
    return squares;
  }

  public BufferedImage getImage(){
    BufferedImage returnImage = new BufferedImage(Game.sqaure, Game.sqaure, BufferedImage.TYPE_3BYTE_BGR);
    try{
      returnImage = ImageIO.read(new File(icon));
    } catch(Exception e){
      e.printStackTrace();
    }
    
    Image image = returnImage.getScaledInstance(Game.sqaure, Game.sqaure, Image.SCALE_SMOOTH);
    returnImage = new BufferedImage(Game.sqaure, Game.sqaure, BufferedImage.TYPE_3BYTE_BGR);
    returnImage.getGraphics().drawImage(image, 0, 0, null);
    return returnImage;
  }
}