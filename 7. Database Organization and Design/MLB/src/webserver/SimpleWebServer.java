package webserver;

/**
 * Code is taken from Computer Networking: A Top-Down Approach Featuring 
 * the Internet, second edition, copyright 1996-2002 J.F Kurose and K.W. Ross, 
 * All Rights Reserved.
 **/
import java.net.ServerSocket;
import java.net.Socket;

class SimpleWebServer{
    public static int SERVER_PORT = 8080;    
    public static String WWW_ROOT = "www/";
   
    public static void main(String args[]) throws Exception  {
      // see if we do not use default server port
      if (args.length >= 1) {
        SERVER_PORT = Integer.parseInt(args[0]);
      }
      // see if we want a different root
      if (args.length >= 2) {
        WWW_ROOT = args[1];
      }
      // create server socket
      ServerSocket listenSocket = new ServerSocket(SERVER_PORT);
      System.out.println("server listening at: " + listenSocket);
      System.out.println("server www root: " + WWW_ROOT);
      while (true) {
        try {
          // take a ready connection from the accepted queue
          Socket connectionSocket = listenSocket.accept();
          System.out.println("receive request from " + connectionSocket);
          // process a request
          WebRequestHandler wrh = new WebRequestHandler(connectionSocket, WWW_ROOT);
          Thread t = new Thread(wrh);
          t.start();
        } catch (Exception e) {
            e.printStackTrace();
            listenSocket.close();
            return; // if we get here, abort
        } 
      } // end of while (true)
    } // end of main
} // end of class SimpleWebServer
