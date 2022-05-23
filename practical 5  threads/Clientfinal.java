import java.io.*; 
import java.net.*;
public class Clientfinal
{ 
    public static void main(String[] args)
    {
        String hostname = "localhost"; //hostname 
        int port = 6789; //port numbrt
        // declaration section:
        // clientSocket: our Clientfinal socket // os: output stream
        // is: input stream
        Socket clientSocket = null;
        DataOutputStream os = null; 
        BufferedReader is = null;
        // Initialization section:aa
        // Try to open a socket on the given port // Try to open input and output streams
        try
        {
            clientSocket = new Socket(hostname, port);
            //initialising client socket 
            os = new DataOutputStream(clientSocket.getOutputStream()); //output stream of client socket 
            is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //input stream of client socket
        }
        catch (UnknownHostException e)
        {
            System.err.println("Don't know about host: " + hostname); 
        } 
        catch (IOException e)
        {
            System.err.println("Couldn't get I/O for the connection to: " + hostname);
        }
        // If everything has been initialized then we want to write some data // to the socket we have opened a connection to on the given port
        if (clientSocket == null || os == null || is == null) 
        {
            System.err.println( "Something is wrong. One variable is null." ); 
            return;
        } try 
        { 
            while ( true )
            {
                System.out.print( "Enter an integer (0 to stop connection, -1 to stop server): " );
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));//taking input from terminal 
                String keyboardInput = br.readLine(); //storing input in keyboard 
                os.writeBytes( keyboardInput + "\n" );
                //writing keyboard into input stream buffer of server
                int n = Integer.parseInt( keyboardInput ); 
                if ( n == 0 || n == -1 ) 
                {
                    //condiition to get out from loop break;
                }
                String responseLine = is.readLine();
                //getting input stream in response line
                System.out.println("Server returns its square as: " + responseLine); 
            }
            // clean up:
            // close the output stream
            // close the input stream 
            // close the socket
            // os.close(); 
            // is.close(); 
            //clientSocket.close();
        }
        catch (UnknownHostException e)
        {
            System.err.println("Trying to connect to unknown host: " + e); 
        } 
        catch (IOException e)
        {
            System.err.println("IOException: " + e);
        }
    }
}