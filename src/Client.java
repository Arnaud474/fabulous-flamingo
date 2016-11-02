
import Game.Board;

import java.io.*;
import java.net.*;
import java.util.Arrays;


class Client {

    private static Client INSTANCE;

    private Socket clientSocket;
    private BufferedInputStream in;
    private BufferedOutputStream out;
    private int clientColor;
    private Board board;
    private boolean isStarted;

    private Client(){
        try{
            clientSocket = new Socket("localhost", 8888);
            in    = new BufferedInputStream(clientSocket.getInputStream());
            out   = new BufferedOutputStream(clientSocket.getOutputStream());
            board = new Board(8,8);
            isStarted = false;
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void start(){

        //To make sure there is no problems with the server
        boolean stop = false;

        //Check if client is already started
        if(!isStarted){

            isStarted = true;

            //Infinite loop to poll the server (if there is no errors)
            while(!stop){

                try{
                    char cmd = 0;

                    //Reads the command code from the server
                    cmd = (char)in.read();

                    switch(cmd){
                        //New Game, this client is white
                        case '1':
                            clientColor = 4;
                            System.out.println(Arrays.deepToString(formatBoardData())); //Prints bidimensional array properly
                            break;
                        //New Game, this client is black
                        case '2':
                            clientColor = 2;
                            System.out.println(Arrays.deepToString(formatBoardData())); //Prints bidimensional array properly
                            break;
                        //Server asks for next move on this client and returns last move played
                        case '3':
                            board.updateBoard(readMove());
                            break;
                        //Invalid movement
                        case '4':

                            break;
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                    stop = true;
                }


            }

        }


    }


    public static Client getInstance(){
        if(INSTANCE == null)
        {
            INSTANCE = new Client();
        }

        return INSTANCE;
    }

    private int[][] formatBoardData() throws IOException{

        //Int array to represent the board
        byte[] buffer = new byte[1024];

        //Size of the current buffer
        int size = in.available();

        //Read the buffer
        in.read(buffer, 0, size);

        //Put the value from the string inside an array
        String[] boardValues = new String(buffer).trim().split(" ");

        //Set board values inside the board class
        board.setBoard(boardValues);

        return board.getBoard();
    }

    /**
     * Reads move from the server en returns it as string
     *
     * @return String containing the move
     * @throws IOException
     */
    private String readMove() throws IOException{

        byte[] buffer = new byte[16];

        int size = in.available();

        in.read(buffer, 0, size);

        return new String(buffer);
    }

    /**
     * Send move to the game server move must be in either of these formats
     * D4D5 D4 D5
     *
     * @param move
     */
    private void sendMove(String move){

    }

    /**
     * Gets player color for this client
     *
     * @return The color of the player on this client
     */
    public int getClientColor() {
        return clientColor;
    }
}
