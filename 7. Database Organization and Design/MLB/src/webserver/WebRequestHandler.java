package webserver;

import controller.BaseController;
import controller.ControllerFactory;
import java.io.*;
import java.net.*;

class WebRequestHandler implements Runnable {

    String SERVER_PAGE_EXT = ".ssp";
    String INDEX_PAGE = "index.htm";
    String WWW_ROOT;
    Socket connSocket;
    BufferedReader inFromClient;
    DataOutputStream outToClient;
    String urlName;
    String query;
    byte[] bytesToSend;

    @Override
    public void run() {
        processRequest();
    }

    public WebRequestHandler(Socket connectionSocket, String WWW_ROOT) throws Exception {
        this.WWW_ROOT = WWW_ROOT;
        this.connSocket = connectionSocket;
        inFromClient = new BufferedReader(new InputStreamReader(connSocket.getInputStream()));
        outToClient = new DataOutputStream(connSocket.getOutputStream());
    }

    public void processRequest() {
        try {
            extractURL();
            generateBytesToSend();
            if (bytesToSend != null) {
                outputResponseHeader();
                outputResponseBody();
            } else {
                outputError(404, "Not Found");
            }
            connSocket.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            outputError(400, "Server error");
        }
    } // end of processARequest

    private void generateBytesToSend() throws Exception {
    	if (urlName==null) return;
        if (urlName.contains(SERVER_PAGE_EXT)) {
            System.out.println("generating contents for: " + urlName);
            String ssp = urlName.substring(0, urlName.indexOf(SERVER_PAGE_EXT));
            BaseController serverApp = ControllerFactory.getServerApp(ssp);
            if (serverApp != null) {
                serverApp.init(query);
                bytesToSend = serverApp.response().getBytes();
            }
        } else {
            String fileName = WWW_ROOT + urlName;
            System.out.println("mapping to filename: " + fileName);
            File fileInfo = new File(fileName);
            if (fileInfo.isFile()) {
                FileInputStream fileStream = new FileInputStream(fileName);
                bytesToSend = new byte[(int) fileInfo.length()];
                fileStream.read(bytesToSend);
                fileStream.close();
            }
        }
    }

    private void extractURL() throws Exception {
        String requestMessageLine = inFromClient.readLine();
        if (requestMessageLine==null) return;
        System.out.println("request line: " + requestMessageLine);
        String[] request = requestMessageLine.split("\\s");
        if (request.length < 2 || !request[0].equals("GET")) {
            outputError(500, "Bad request");
        }
        urlName = request[1];
        if (urlName.equalsIgnoreCase("/")) {
            urlName = INDEX_PAGE;
        } else if (urlName.startsWith("/") == true) {
            urlName = urlName.substring(1);
        }
        if (urlName.contains("?")) {
            String[] parts = urlName.split("\\?"); // must escape the ?
            urlName = parts[0];
            query = parts[1];
        }
    }

    private void outputResponseHeader() throws Exception {
        outToClient.writeBytes("HTTP/1.0 200 Document Follows\r\n");
        if (urlName.endsWith(".jpg")) {
            outToClient.writeBytes("Content-Type: image/jpeg\r\n");
        } else if (urlName.endsWith(".gif")) {
            outToClient.writeBytes("Content-Type: image/gif\r\n");
        } else if (urlName.endsWith(".png")) {
            outToClient.writeBytes("Content-Type: image/png\r\n");
        } else if (urlName.endsWith(".html") || urlName.endsWith(".htm")
                || urlName.endsWith(SERVER_PAGE_EXT)) {
            outToClient.writeBytes("Content-Type: text/html\r\n");
        } else {
            outToClient.writeBytes("Content-Type: text/plain\r\n");
        }
    }

    private void outputResponseBody() throws Exception {
        int numOfBytes = bytesToSend.length;
        outToClient.writeBytes("Content-Length: " + numOfBytes + "\r\n");
        outToClient.writeBytes("\r\n");
        outToClient.write(bytesToSend, 0, numOfBytes);
    }

    void outputError(int errCode, String errMsg) {
    	try {
    		if (!connSocket.isClosed()) {
    			outToClient.writeBytes("HTTP/1.0 " + errCode + " " + errMsg + "\r\n");
            	outToClient.writeBytes("\r\n");
            	outToClient.writeBytes("<html>");
            	outToClient.writeBytes("<h1>" + errCode + " " + urlName + " " + errMsg + "</h1>");
            	outToClient.writeBytes("</html>");
    		} 
   			System.out.println("output error: " + errCode + " " + urlName + " " + errMsg);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}
