package net.gvk.for1c;

import java.util.ArrayList;

public class StarterTest {

	public static void main(String[] args) {
//        if (args.length < 1)
//        {
//            System.out.println("USAGE: java GoodWindowsExec <cmd>");
//            System.exit(1);
//        }
//        
//        CommandExecuter commandExecuter = new CommandExecuter();
//        
//        int exitVal = commandExecuter.executeCommand(args);
//        
//        if (exitVal == 0) {
//            ArrayList<String> outmsg = commandExecuter.getStdMessages();
//            for (String err : outmsg) {
//				System.out.println(err);
//			}
//		} else {
//            ArrayList<String> outmsg = commandExecuter.getStdMessages();
//            for (String err : outmsg) {
//				System.out.println(err);
//			}
//
//            outmsg = commandExecuter.getErrMessages();
//            for (String msg : outmsg) {
//				System.out.println(msg);
//			}
//		}
//            System.out.println("ExitValue: " + exitVal);        
// 
		Execute1Crac executor = new Execute1Crac();
		executor.initialize();
		//* test 1
		//String [] args1 = new String [] {"--help","cluster"};
		//* test 2
//		executor.setCommand("help");
//		String [] args1 = new String [] {"cluster "};
		//* test 2
//		String [] args1 = new String [] {"192.168.1.39","cluster","list"};
		
		//* test 3
		//executor.setCommand("cluster");
		executor.setHost("192.168.1.39");
		executor.setCluster_name("gvk1c");
		executor.takeClusterID();
	}
		
}
