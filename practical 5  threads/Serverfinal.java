import java.io.*; 
import java.net.*;
public class Serverfinal
{
    public static void main(String args[])
    {
        int port = 6789;//port number
        Serverfinal server = new Serverfinal( port );
        //calling constructructor of server final and initialisig in object(server) of serverfina; 
        server.startServer(); 
        //calling startserver function 
    }
    // declare a server socket and a client socket for the server; 
    // declare the number of connections
    ServerSocket echoServer = null; 
    //declaring echo server 
    Socket clientSocket = null;
    //declaring client socket 
    int numConnections = 0;  
    int port;
    public Serverfinal( int port ) 
    { 
        this.port = port;
        //port is initialised in member variable port
    }
    public void stopServer() //Switching off the server
    {
        System.out.println( "Server cleaning up." ); 
        System.exit(0);
    }
    public void startServer() //starting the server 
    {
        // Try to open a server socket on the given port
        // Note that we can't choose a port less than 1024 if we are not 
        // privileged users (root)
        try
        {
            echoServer = new ServerSocket(port);
            //initialising server by calling server socket function
        }
        catch (IOException e)
        {
            //if error persist
            System.out.println(e);
        }
        System.out.println( "Server is started and is waiting for connections." );
        System.out.println( "With multi-threading, multiple connections are allowed." );
        System.out.println( "Any client can send -1 to stop the server.");
        // Whenever a connection is received, start a new thread to process the connection and wait for the next connection.
        while ( true )
        {   
            try
            {
                clientSocket = echoServer.accept();
                //clientsocket is intialised
                numConnections ++;
                //number of connectections is increament
                Server2Connection oneconnection = new Server2Connection(clientSocket, numConnections,this); //caling constructor of server2 funcction 
                new Thread(oneconnection).start();
                //run function is called f or each thread
            }
            catch (IOException e)
            {
                System.out.println(e);
            }
        }
    }
}
class Server2Connection implements Runnable
{
    BufferedReader is;
    PrintStream os; 
    Socket clientSocket;
    int id;
    Serverfinal server;
    public Server2Connection(Socket clientSocket, int id, Serverfinal server) 
    { 
        this.clientSocket = clientSocket;
        this.id = id; 
        this.server = server;
        System.out.println( "Connection " + id + " established with: " + clientSocket ); 
        try
        {
            is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            //data input stream of server
            os = new PrintStream(clientSocket.getOutputStream()); 
            //output stream of serve
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    } 
    public void run()
    {
        String line; 
        try
        {       
            boolean serverStop = false;
            while (true)
            {                    line = is.readLine(); //getting line from client 
                System.out.println( "Received " + line + " from Connection " + id + "." );//displaying
                int n = Integer.parseInt(line); 
                if ( n == -1 )
                {
                    //checking if to stop the server or client
                    serverStop = true; break;
                } 
                if ( n == 0 ) 
                    break; 
                os.println("" + n*n );
                //writing square in input buffer of client
            }
            System.out.println( "Connection " + id + " closed." ); 
            //closing client connection 
            is.close(); 
            os.close(); 
            clientSocket.close();
            if ( serverStop ) 
                server.stopServer();
            //this is used to close all the connection
        } 
        catch (IOException e)
        {
            System.out.println(e);//if error occured
        }
    }
}