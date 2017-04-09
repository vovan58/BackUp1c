package net.gvk.for1c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

class StreamGobbler extends Thread
{
    InputStream is;
    //String type;
    String codepage;
    ArrayList<String> stdmsg;
    
    public ArrayList<String> getStdMsg() {
		return stdmsg;
	}

	StreamGobbler(InputStream is, String codepage)
    {
        this.is = is;
        this.codepage = codepage; 
        stdmsg = new ArrayList<String>();
    }
    
    public void run()
    {
        try
        {
        	// gvk ++
            InputStreamReader isr = new InputStreamReader(is,codepage); 
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while ( (line = br.readLine()) != null)
            	stdmsg.add(line);
            } catch (IOException ioe)
              {
                ioe.printStackTrace();  
              }
    }
}
