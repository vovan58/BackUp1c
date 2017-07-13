package net.gvk.for1c;

import java.io.File;

public class Execute1Crac {
	private static String path_rac;
	private static String name_rac;
	
	private static String version_rac;
	
	private static String command;   // первый параметр - команда
	
	private static String options_key[];
	private static String options_value[];

	private static String getPath_rac() {
		return path_rac;
	}

	private static void setPath_rac(String path_rac) {
		Execute1Crac.path_rac = path_rac;
	}

	private static String getVersion_rac() {
		return version_rac;
	}

	private static void setVersion_rac(String version_rac) {
		Execute1Crac.version_rac = version_rac;
	}

	private static String getCommand() {
		return command;
	}

	private static void setCommand(String command) {
		Execute1Crac.command = command;
	}

	private static String[] getOptions_key() {
		return options_key;
	}

	private static void setOptions_key(String[] options_key) {
		Execute1Crac.options_key = options_key;
	}

	private static String[] getOptions_value() {
		return options_value;
	}

	private static void setOptions_value(String[] options_value) {
		Execute1Crac.options_value = options_value;
	}

	private static String getName_rac() {
		return name_rac;
	}

	private static void setName_rac(String name_rac) {
		Execute1Crac.name_rac = name_rac;
	}
	private static boolean rac_exists(String pathname) {
		File rac_file = new File(pathname);
		return rac_file.exists();
	}
	
	public static String search_last_1c() {
		// ищет последнюю установленную версию 1с
		String last_path = "";
		return last_path;
	}
	
	public static void main(String[] args) {
	
		String os_name = System.getProperty("os.name" );
		String os_arch = System.getProperty("os.arch");
		
        if( os_name.startsWith("Windows"))
        {
        	setName_rac("rac.exe");
        }
        else if (os_name.equals( "Linux")) {
        	setName_rac("rac");
        	if (os_arch.equals("amd64")) {
				setPath_rac( "/opt/1C/1CE/x86_64/");
			} else {
				setPath_rac("/opt/1C/1CE/x86/");
			}
		} else {
			System.out.println("неизвестная операционная система: " + os_name);
			System.exit(8);
		}
        String full_path_rac = getPath_rac()+getName_rac();
        if (! rac_exists(full_path_rac)) {
			System.out.println("Не найдена утилита : " + getName_rac());
			System.exit(8);
		}

	}
	

}
