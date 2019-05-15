package comp1110.ass2;
import java.util.ArrayList;
import java.util.Random;

public class RailroadInk
{

    final String[][] board = {
            {"A0","A1","A2","A3","A4","A5","A6"},
            {"B0","B1","B2","B3","B4","B5","B6"},
            {"C0","C1","C2","C3","C4","C5","C6"},
            {"D0","D1","D2","D3","D4","D5","D6"},
            {"E0","E1","E2","E3","E4","E5","E6"},
            {"F0","F1","F2","F3","F4","F5","F6"},
            {"G0","G1","G2","G3","G4","G5","G6"}};

    /**
     * Determine whether a tile placement string is well-formed:
     * - it consists of exactly 5 characters;
     * - the first character represents a die A or B, or a special tile S
     * - the second character indicates which tile or face of the die (0-5 for die A and special tiles, or 0-2 for die B)
     * - the third character represents the placement row A-G
     * - the fourth character represents the placement column 0-6
     * - the fifth character represents the orientation 0-7
     *
     * @param tilePlacementString a candidate tile placement string
     * @return true if the tile placement is well formed
     */
    public static boolean isTilePlacementWellFormed(String tilePlacementString) {
        // FIXME Task 2: determine whether a tile placement is well-formed
        if (tilePlacementString.length() != 5) {
            return false;
        }
        char first = tilePlacementString.charAt(0);
        char second = tilePlacementString.charAt(1);
        char third = tilePlacementString.charAt(2);
        char fourth = tilePlacementString.charAt(3);
        char fifth = tilePlacementString.charAt(4);

        if (first == 'A' || first == 'B' || first == 'S') {
            if (((first == 'A' || first == 'S') && (second >= '0' && second <= '5')) || ((first == 'B') && (second >= '0' && second <= '2'))) {
                if (third >= 'A' && third <= 'G') {
                    if (fourth >= '0' && fourth <= '6') {
                        if (fifth >= '0' && fifth <= '7') {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Determine whether a board string is well-formed:
     * - it consists of exactly N five-character tile placements (where N = 1 .. 31);
     * - each piece placement is well-formed
     * - no more than three special tiles are included
     *
     * @param boardString a board string describing the placement of one or more pieces
     * @return true if the board string is well-formed
     */
    public static boolean isBoardStringWellFormed(String boardString) {
        // FIXME Task 3: determine whether a board string is well-formed

        int specialCount=0;
        boolean allGoodPlacement=true;
        if (boardString == null || boardString.length() /5 > 31 || boardString.equals("") || boardString.length()%5!=0) {
            return false;
        } else if (boardString.length()%5==0) {
            for (int i = 0; i < boardString.length(); i = i + 5) {
                String tilePlacementString = boardString.substring(i, i + 5);
                if(tilePlacementString.charAt(0) == 'S')
                {
                    specialCount++;
                }
                if (!isTilePlacementWellFormed(tilePlacementString)) {
                    allGoodPlacement = false;
                }
            }
        }
        return specialCount<=3 && allGoodPlacement;
    }


    /**
     * Determine whether the provided placements are neighbours connected by at least one validly connecting edge.
     * For example,
     * - areConnectedNeighbours("A3C10", "A3C23") would return true as these tiles are connected by a highway edge;
     * - areConnectedNeighbours("A3C23", "B1B20") would return false as these neighbouring tiles are disconnected;
     * - areConnectedNeighbours("A0B30", "A3B23") would return false as these neighbouring tiles have an
     * invalid connection between highway and railway; and
     * areConnectedNeighbours("A0B30", "A3C23") would return false as these tiles are not neighbours.
     *
     * @return true if the placements are connected neighbours
     */
    public static boolean areConnectedNeighbours(String tilePlacementStringA, String tilePlacementStringB) {
        // FIXME Task 5: determine whether neighbouring placements are connected
        int xa = Integer.parseInt(tilePlacementStringA.charAt(3)+"");
        int ya = (int)(tilePlacementStringA.charAt(2)-65);
        int xs = Integer.parseInt(tilePlacementStringB.charAt(3)+"");
        int ys = (int)(tilePlacementStringB.charAt(2)-65);
        if(Math.abs(xa-xs)==1 && Math.abs(ya-ys)==1) return false;
        else if((Math.abs(xa-xs)==1||Math.abs(ya-ys)==1)) {
            String pica = testConnect(tilePlacementStringA);
            String picb = testConnect(tilePlacementStringB);
            if(tilePlacementStringA.charAt(3)==tilePlacementStringB.charAt(3)&&tilePlacementStringA.charAt(2)>tilePlacementStringB.charAt(2)){
                if(pica.charAt(1)=='-'|| picb.charAt(3)=='-')return false;
                return pica.charAt(1)==picb.charAt(3);//a down
            }
            else if(tilePlacementStringA.charAt(3)==tilePlacementStringB.charAt(3)&&tilePlacementStringA.charAt(2)<tilePlacementStringB.charAt(2))
            {
                if(pica.charAt(3)=='-'|| picb.charAt(1)=='-')return false;
                return pica.charAt(3)==picb.charAt(1);//a up
            }
            else if(tilePlacementStringA.charAt(2)==tilePlacementStringB.charAt(2)&&tilePlacementStringA.charAt(3)>tilePlacementStringB.charAt(3))
            {
                if(pica.charAt(0)=='-'|| picb.charAt(2)=='-')return false;
                return pica.charAt(0)==picb.charAt(2);//a right
            }
            else if(tilePlacementStringA.charAt(2)==tilePlacementStringB.charAt(2)&&tilePlacementStringA.charAt(3)<tilePlacementStringB.charAt(3))
            {
                if(pica.charAt(2)=='-'|| picb.charAt(0)=='-')return false;
                else return pica.charAt(2)==picb.charAt(0);//a left
            }else return false;
        }
        else return false;
    }


    /**
     * Given a well-formed board string representing an ordered list of placements,
     * determine whether the board string is valid.
     * A board string is valid if each tile placement is legal with respect to all previous tile
     * placements in the string, according to the rules for legal placements:
     * - A tile must be placed such that at least one edge connects to either an exit or a pre-existing route.
     *   Such a connection is called a valid connection.
     * - Tiles may not be placed such that a highway edge connects to a railway edge;
     *   this is referred to as an invalid connection.
     *   Highways and railways may only join at station tiles.
     * - A tile may have one or more edges touching a blank edge of another tile;
     *   this is referred to as disconnected, but the placement is still legal.
     *
     * @param boardString a board string representing some placement sequence
     * @return true if placement sequence is valid
     */
    public static boolean isValidPlacementSequence(String boardString) {
        String[][] railArrT = {{"S0","26"},{"S1","123567"},{"S3","01234567"},{"S4","2356"},{"S5","1357"},
                {"A0","0147"},{"A1","0246"},{"A2","023456"},{"B0","26"},{"B1","35"},{"B2","1357"}};
        String[][] railArrD = {{"S0","04"},{"S1","013457"},{"S3","01234567"},{"S4","0147"},{"S5","1357"},
                {"A0","256"},{"A1","0246"},{"A2","012467"},{"B0","04"},{"B1","17"},{"B2","1357"}};
        String[][] railArrL = {{"S0","15"},{"S1","012456"},{"S3","01234567"},{"S4","1245"},{"S5","0246"},
                {"A0","0367"},{"A1","1357"},{"A2","123457"},{"B0","15"},{"B1","24"},{"B2","0246"}};
        String[][] railArrR = {{"S0","37"},{"S1","023467"},{"S3","01234567"},{"S4","0367"},{"S5","0246"},
                {"A0","1245"},{"A1","1357"},{"A2","013567"},{"B0","37"},{"B1","06"},{"B2","0246"}};
        String[][] highArrT = {{"S0","013457"},{"S1","04"},{"S2","01234567"},{"S4","0147"},{"S5","0246"},
                {"A3","023456"},{"A4","0246"},{"A5","0147"},{"B0","04"},{"B1","04"},{"B2","0246"}};
        String[][] highArrD = {{"S0","123567"},{"S1","26"},{"S2","01234567"},{"S4","2356"},{"S5","0246"},
                {"A3","012467"},{"A4","0246"},{"A5","2356"},{"B0","26"},{"B1","26"},{"B2","0246"}};
        String[][] highArrL = {{"S0","023467"},{"S1","37"},{"S2","01234567"},{"S4","0367"},{"S5","1357"},
                {"A3","123457"},{"A4","1357"},{"A5","0367"},{"B0","37"},{"B1","37"},{"B2","1357"}};
        String[][] highArrR = {{"S0","012456"},{"S1","15"},{"S2","01234567"},{"S4","1245"},{"S5","1357"},
                {"A3","013567"},{"A4","1357"},{"A5","1245"},{"B0","15"},{"B1","15"},{"B2","1357"}};
        ArrayList<Boolean> boolarr = new ArrayList<>();
        String cms = "ABCDEFG";
        String start = "A1A3A5B0B6D0D6F0F6G1G3G5";
        if(boardString.length()==5 && start.contains(boardString.substring(2,4))) return true;
        if(boardString.length()%5!=0)return false;
        else if(boardString.length()>5*49) return false;
        else{
            String[] strarr = new String[boardString.length()/5];
            int index =0;
            for(int i =0; i<boardString.length();i+=5){
                strarr[index] = boardString.substring(i,i+5);
                index++;
            }
            ArrayList<Boolean> isolate = new ArrayList<>();
            for(String s : strarr){
                if((s.charAt(0)!='A' || s.charAt(0)!='B' || s.charAt(0)!='S') &&
                        (Integer.parseInt(s.charAt(1)+"")<0 || Integer.parseInt(s.charAt(1)+"")>5) &&
                        !cms.contains(s.charAt(2)+"") &&
                        (Integer.parseInt(s.charAt(3)+"")<0 || Integer.parseInt(s.charAt(3)+"")>6) &&
                        (Integer.parseInt(s.charAt(4)+"")<0 || Integer.parseInt(s.charAt(4)+"")>7)) boolarr.add(false);
                //内容合法数列
                else boolarr.add(true);
                if(s.substring(2,4).equals("A1")||s.substring(2,4).equals("A5")){
                    for(String[] sarr:railArrT)
                        if(s.substring(0,2).equals(sarr[0]) && sarr[1].contains(s.charAt(4)+"")) boolarr.add(false);
                }else if(s.substring(2,4).equals("G1") || s.substring(2,4).equals("G5")){
                    for(String[] sarr:railArrD)
                        if(s.substring(0,2).equals(sarr[0]) && sarr[1].contains(s.charAt(4)+"")) boolarr.add(false);
                }else if(s.substring(2,4).equals("D0")){
                    for(String[] sarr:railArrL)
                        if(s.substring(0,2).equals(sarr[0]) && sarr[1].contains(s.charAt(4)+"")) boolarr.add(false);
                }else if(s.substring(2,4).equals("D6")){
                    for(String[] sarr:railArrR)
                        if(s.substring(0,2).equals(sarr[0]) && sarr[1].contains(s.charAt(4)+"")) boolarr.add(false);
                }else if(s.substring(2,4).equals("B0") || s.substring(2,4).equals("F0")){
                    for(String[] sarr:highArrL)
                        if(s.substring(0,2).equals(sarr[0]) && sarr[1].contains(s.charAt(4)+"")) boolarr.add(false);
                }else if(s.substring(2,4).equals("B6") || s.substring(2,4).equals("F6")){
                    for(String[] sarr:highArrR)
                        if(s.substring(0,2).equals(sarr[0]) && sarr[1].contains(s.charAt(4)+"")) boolarr.add(false);
                }else if(s.substring(2,4).equals("A3")){
                    for(String[] sarr:highArrT)
                        if(s.substring(0,2).equals(sarr[0]) && sarr[1].contains(s.charAt(4)+"")) boolarr.add(false);
                }else if(s.substring(2,4).equals("G3")) {
                    for (String[] sarr : highArrD)
                        if (s.substring(0, 2).equals(sarr[0]) && sarr[1].contains(s.charAt(4) + "")) boolarr.add(false);
                }
                //marginal legal
                ArrayList<Boolean> connect= new ArrayList<>();

                for(String a:strarr){
                    if(!a.equals(s)){
                        int xa = Integer.parseInt(a.charAt(3)+"");
                        int ya = (int)(a.charAt(2)-65);
                        int xs = Integer.parseInt(s.charAt(3)+"");
                        int ys = (int)(s.charAt(2)-65);
                        if(Math.abs(xa-xs)==1||Math.abs(ya-ys)==1){
                            connect.add(true);
                            String pica = testConnect(a);
                            String picb = testConnect(s);
                            if(a.charAt(3)==s.charAt(3)&&a.charAt(2)>s.charAt(2))
                                boolarr.add(pica.charAt(1)==picb.charAt(3)||pica.charAt(1)=='-'||picb.charAt(3)=='-');//a下
                            else if(a.charAt(3)==s.charAt(3)&&a.charAt(2)<s.charAt(2))
                                boolarr.add(pica.charAt(3)==picb.charAt(1)||pica.charAt(3)=='-'||picb.charAt(1)=='-');//a上
                            else if(a.charAt(2)==s.charAt(2)&&a.charAt(3)>s.charAt(3))
                                boolarr.add(pica.charAt(0)==picb.charAt(2)||pica.charAt(0)=='-'||picb.charAt(2)=='-');//a右
                            else if(a.charAt(2)==s.charAt(2)&&a.charAt(3)<s.charAt(3))
                                boolarr.add(pica.charAt(2)==picb.charAt(0)||pica.charAt(2)=='-'||picb.charAt(0)=='-');//a左
                        } else if(start.contains(a.substring(2,4))) connect.add(true);
                         else
                            connect.add(false);
                    }
                }
                if(!connect.contains(true)) boolarr.add(false);
                if(start.contains(s.substring(2,4))) isolate.add(true);
                else isolate.add(false);
            }
            if(!isolate.contains(true)) boolarr.add(false);
        }
        if(boolarr.contains(false)) return false;
        else return true;
        // FIXME Task 6: determine whether the given placement sequence is valid
    }
//LEFT-TOP-RIGHT-BOTTOM R = RAIL, H=HIGH
    private static String testConnect(String input){
        String pic = input.substring(0,2);
        String ori = input.charAt(4)+"";
        if(pic.equals("S0")) pic="HHHR";
        else if(pic.equals("S1")) pic="RHRR";
        else if(pic.equals("S2")) pic="HHHH";
        else if(pic.equals("S3")) pic="RRRR";
        else if(pic.equals("S4")) pic="HHRR";
        else if(pic.equals("S5")) pic="RHRH";
        else if(pic.equals("A0")) pic="RR--";
        else if(pic.equals("A1")) pic="-R-R";
        else if(pic.equals("A2")) pic="-RRR";
        else if(pic.equals("A3")) pic="-HHH";
        else if(pic.equals("A4")) pic="-H-H";
        else if(pic.equals("A5")) pic="HH--";
        else if(pic.equals("B0")) pic="-H-R";
        else if(pic.equals("B1")) pic="-HR-";
        else if(pic.equals("B2")) pic="RHRH";
        String out = rolate(pic,ori);
        return out;
    }
    private static String rolate(String pic, String ori){
        int orit = Integer.parseInt(ori);
        char[] pic_out = new char[4];
        if(orit<4){
            for (int i = 0; i < 4; i++)
                pic_out[(orit+i)%4] = pic.charAt(i);
        }else{
            char[] flip = {pic.charAt(2),pic.charAt(1),pic.charAt(0),pic.charAt(3)};
            for (int i = 0; i < 4; i++)
                pic_out[(orit+i)%4] = flip[i];
        }
        String out ="";
        for(char c:pic_out)
            out+=c;
        return out;
    }

    /**
     * Generate a random dice roll as a string of eight characters.
     * Dice A should be rolled three times, dice B should be rolled once.
     * Die A has faces numbered 0-5.
     * Die B has faces numbered 0-2.
     * Each die roll is composed of a character 'A' or 'B' representing the dice,
     * followed by a digit character representing the face.
     *
     * @return a String representing the die roll e.g. A0A4A3B2
     */
    public static String generateDiceRoll() {
        String out = "";
        for (int i = 0; i < 3; i++) {
            Random diceA = new Random();
            int ranDiceA = diceA.nextInt(5);
            out+=("A"+ranDiceA);
        }
        Random diceB = new Random();
        int ranDiceB = diceB.nextInt(2);
        out+=("B"+ranDiceB);
        // FIXME Task 7: generate a dice roll
        return out;
    }

    /**
     * Given the current state of a game board, output an integer representing the sum of all the following factors
     * that contribute to the player's final score.
     * <p>
     * * Number of exits mapped
     * * Number of centre tiles used
     * * Number of dead ends in the network
     *
     * @param boardString a board string representing a completed game
     * @return integer (positive or negative) for score *not* considering longest rail/highway
     */
    public static int getBasicScore(String boardString)
{
    Board board = new Board();
    for(int i=0; i<boardString.length()-1; i+=5)
    {
        board.addTile(boardString.substring(i, i+5));
    }

    ScoreCalculator sc = new ScoreCalculator(board);
    return sc.getBasicScore();

}// only one test not passed, will debug it later.
    /**
     * Given a valid boardString and a dice roll for the round,
     * return a String representing an ordered sequence of valid piece placements for the round.
     * @param boardString a board string representing the current state of the game as at the start of the round
     * @param diceRoll a String representing a dice roll for the round
     * @return a String representing an ordered sequence of valid piece placements for the current round
     * @see RailroadInk#generateDiceRoll()
     */
    public static String generateMove(String boardString, String diceRoll) {
        // FIXME Task 10: generate a valid move
        String result = "";
        int size = boardString.length() / 5;
        Board board = new Board();
        for (int i = 0; i < size; i++) {
            board.addTile(boardString.substring(i * 5, i * 5 + 5));
        }
        for (int y = 0; y < 7; y++) {
            for (int x = 0; x < 7; x++) {
                //build the id from the x and y values
                StringBuilder id = new StringBuilder();
                id.append((char) (y + 65));
                id.append(x);
                for (int i = 0; i < 4; i++) {
                    String dice = diceRoll.substring(i * 4, i * 4 + 4);
                    /* ============= FIXME*/System.out.println(dice);
                    String placementString = dice + id.toString();
                    /* ============= FIXME*/System.out.println(placementString);//id + coords + orientation
                    if (board.addTile(placementString)) {
                        result += id;
                    }
                }
            }
        }
        /* ============= FIXME*/System.out.println(result);

        return result;

    }

    /**
     * Given the current state of a game board, output an integer representing the sum of all the factors contributing
     * to `getBasicScore`, as well as those attributed to:
     * <p>
     * * Longest railroad
     * * Longest highway
     *
     * @param boardString a board string representing a completed game
     * @return integer (positive or negative) for final score (not counting expansion packs)
     */
    public static int getAdvancedScore(String boardString) {

        int totalScore = 0;
        int size = boardString.length() / 5;
        Board board = new Board();
        for (int i = 0; i < size; i++) {
            board.addTile(boardString.substring(i * 5, i * 5 + 5));
        }
        ScoreCalculator scoreCal = new ScoreCalculator(board);
        totalScore = scoreCal.getAdvancedScore();
        return totalScore;

        // FIXME Task 12: compute the total score including bonus points

    }

}


