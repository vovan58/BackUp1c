package net.gvk.for1c;

import java.util.ArrayList;

public class CommandExecuter {
	
	String fullPathCom;
	int typeOS;  // 0 - Windows; 1 - Linux
	String codepage;
	String fileseparator;
	
	int exitVal;
	ArrayList<String> stdMessages;
	ArrayList<String> errMessages;
	
	public int getExitVal() {
		return exitVal;
	}

	public ArrayList<String> getStdMessages() {
		return stdMessages;
	}

	public ArrayList<String> getErrMessages() {
		return errMessages;
	}
	
	public String getFullPathCom() {
		return fullPathCom;
	}

	public int getTypeOS() {
		return typeOS;
	}

	public String getCodepage() {
		return codepage;
	}

	public String getFileseparator() {
		return fileseparator;
	}

	public CommandExecuter() {
		this.exitVal = 124;
		this.stdMessages = new ArrayList<String>();
		this.errMessages = new ArrayList<String>();
		
        String osName = System.getProperty("os.name" );
        if (osName.startsWith("Windows")) {
    		this.typeOS = 0;
    		this.fullPathCom = System.getenv("ComSpec") ;
    		this.codepage = "Cp866";
    		this.fileseparator = System.getProperty("file.separator" );
		} else {
    		this.typeOS = 1;
    		this.codepage = "UTF-8";
		}
        switch (typeOS) {
		case 0: // Windows
			
			break;
		case 1:  // Linux

			break;
		default:
			
			break;
		}
	}

	public int executeCommand(String[] args) {
		int qArgs;
        switch (typeOS) {
		case 0:
			qArgs = 2;
			break;
		case 1:
			qArgs = 1;
			break;
		default:
			qArgs = 3;
			break;
		}
		
        String[] cmd = new String[qArgs + args.length];
        switch (typeOS) {
		case 0:  // Windows
	        cmd[0] = fullPathCom ;
	        cmd[1] = "/C" ;
			break;
		case 1:  // Linux
	        cmd[0] = fullPathCom ;
			break;

		default:
			break;
		}
        for (int i = 0; i < args.length; i++) {
			cmd[qArgs+i] = args[i];
		}
        
        try {
        Runtime rt = Runtime.getRuntime();
        String debugString = "";
        
        for (int i = 0; i < cmd.length; i++) {
			debugString = debugString + " " + cmd[i];
		}
        System.out.println("Execing " + debugString);
        
        Process proc = rt.exec(cmd);
        // any error message?
        StreamGobbler errorGobbler = new StreamGobbler(proc.getErrorStream(), codepage);            
        
        // any output?
        StreamGobbler outputGobbler = new StreamGobbler(proc.getInputStream(), codepage);
            
        // kick them off
        errorGobbler.start();
        outputGobbler.start();
                                
        // any error???
        exitVal = proc.waitFor();
        errMessages = errorGobbler.getStdMsg();
        stdMessages = outputGobbler.getStdMsg();
//        System.out.println("ExitValue: " + exitVal);
   
    } catch (Throwable t)
      {
        t.printStackTrace();
      }

        
	return exitVal;	
	}
	
}
