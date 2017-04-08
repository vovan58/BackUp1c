package net.gvk.for1c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

class StreamGobbler extends Thread
{
    InputStream is;
    String type;
    String codepage;
    ArrayList<String> stdmsg;
    
    public ArrayList<String> getStdMsg() {
		return stdmsg;
	}

	StreamGobbler(InputStream is, String type)
    {
        this.is = is;
        this.type = type;
        this.codepage = new String ("Cp866"); //("UTF-8");
        String osName = System.getProperty("os.name" );
        String osNameWin = osName.substring(0, 6);
        // System.getenv("ComSpec");
        if( osNameWin.equals( "Windows" )) {
        	this.codepage = new String("Cp866");
        }
        stdmsg = new ArrayList<String>();
    }
    
    public void run()
    {
        try
        {
        	// gvk ++
            InputStreamReader isr = new InputStreamReader(is,codepage); // ,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while ( (line = br.readLine()) != null)
                //System.out.println(type + ">" + line); 
            	stdmsg.add(line);
            } catch (IOException ioe)
              {
                ioe.printStackTrace();  
              }
    }
}
