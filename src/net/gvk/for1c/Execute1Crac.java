package net.gvk.for1c;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

public class Execute1Crac {
	private String path_rac;
	private String name_rac;

	private String version_rac;

	private String command; // первый параметр - команда
	private String subcommand; // второй параметр - команда

	private String options_key[];
	private String options_value[];

	private ArrayList<String> stdmsg;
	private ArrayList<String> errmsg;
	private int exitVal;

	private String host;
	private String port;

	private String cluster_name;
	private String cluster_uuid;

	private String cluster_user;
	private String cluster_pswd;

	private String base_uuid;
	private String base_name;

	private String base_user;
	private String base_pswd;

	private String code_permit; // код разрешения

	private String getPath_rac() {
		return path_rac;
	}

	private void setPath_rac(String path_rac) {
		this.path_rac = path_rac;
	}

	private String getVersion_rac() {
		return version_rac;
	}

	private void setVersion_rac(String version_rac) {
		this.version_rac = version_rac;
	}
	
	public String getCluster_name() {
		return cluster_name;
	}

	public void setCluster_name(String cluster_name) {
		this.cluster_name = cluster_name;
		this.cluster_uuid = new String("");
	}

	public String getCluster_uuid() {
		return cluster_uuid;
	}

	public ArrayList<String> getStdmsg() {
		return stdmsg;
	}

	public void setCluster_uuid(String cluster_uuid) {
		this.cluster_uuid = cluster_uuid;
	}

	public String getCluster_user() {
		return cluster_user;
	}

	public void setCluster_user(String cluster_user) {
		this.cluster_user = cluster_user;
	}

	public String getCluster_pswd() {
		return cluster_pswd;
	}

	public void setCluster_pswd(String cluster_pswd) {
		this.cluster_pswd = cluster_pswd;
	}

	public String getBase_uuid() {
		return base_uuid;
	}

	public void setBase_uuid(String base_uuid) {
		this.base_uuid = base_uuid;
	}

	public String getBase_name() {
		return base_name;
	}

	public void setBase_name(String base_name) {
		this.base_name = base_name;
	}

	public String getBase_user() {
		return base_user;
	}

	public void setBase_user(String base_user) {
		this.base_user = base_user;
	}

	public String getBase_pswd() {
		return base_pswd;
	}

	public void setBase_pswd(String base_pswd) {
		this.base_pswd = base_pswd;
	}

	private String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
		this.subcommand = "";
	}

	public void setSubcommand(String subcommand) {
		this.subcommand = subcommand;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setPort(String port) {
		this.port = port;
	}

	private String[] getOptions_key() {
		return options_key;
	}

	private void setOptions_key(String[] options_key) {
		this.options_key = options_key;
	}

	private String[] getOptions_value() {
		return options_value;
	}

	private void setOptions_value(String[] options_value) {
		this.options_value = options_value;
	}

	private String getName_rac() {
		return name_rac;
	}

	private void setName_rac(String name_rac) {
		this.name_rac = name_rac;
	}

	private boolean rac_exists(String pathname) {
		File rac_file = new File(pathname);
		return rac_file.exists();
	}

	public String search_last_1c() {
		// ищет последнюю установленную версию 1с пока только среди 32 битных
		// String last_path = "";
		// String cur_path = new String();
		String last_path = "C:\\Program Files (x86)\\1cv8\\8.3.10.2650\\bin\\"; // debug
		String os_arch = System.getProperty("os.arch");

		if (os_arch.equals("amd64")) {
			String cur_path = System.getenv("ProgramFiles(x86)") + "\\1cv8";
		} else {
			String cur_path = System.getenv("ProgramFiles") + "\\1cv8";
		}
		// дальше сканировать директории - а надо? лучше установить в диалоге!!!

		return last_path;
	}

	public void initialize() {

		stdmsg = new ArrayList<String>();
		errmsg = new ArrayList<String>();
		host = new String("");
		port = new String("");
		command = new String("");
		subcommand = new String("");
		cluster_name = new String("");

		// инициализация всех переменных - сначала и для отладки!!!
		String os_name = System.getProperty("os.name");
		String os_arch = System.getProperty("os.arch");

		if (os_name.startsWith("Windows")) {
			setName_rac("rac.exe");
			setPath_rac(search_last_1c());
		} else if (os_name.equals("Linux")) {
			setName_rac("rac");
			if (os_arch.equals("amd64")) {
				setPath_rac("/opt/1C/1CE/x86_64/");
			} else {
				setPath_rac("/opt/1C/1CE/x86/");
			}
		} else {
			System.out.println("неизвестная операционная система: " + os_name);
			System.exit(8);
		}
		String full_path_rac = getPath_rac() + getName_rac();
		if (!rac_exists(full_path_rac)) {
			System.out.println("Не найдена утилита : " + getName_rac());
			System.exit(8);
		}

	}

	public void execute(String[] args1) {
		// очистим сообщения
		stdmsg.clear();
		errmsg.clear();
		// предварительные вычисления
		// выполнить rac
		CommandExecuter commandExecuter = new CommandExecuter();
		int q_args = 1;

		if (!host.isEmpty() || (!port.isEmpty())) {
			q_args++;
		}
		if (!command.isEmpty()) {
			q_args++;
			if (!subcommand.isEmpty()) {
				q_args++;
			}
		}

		String[] cmd = new String[q_args + args1.length];
		String full_path_rac = getPath_rac() + getName_rac();
		cmd[0] = full_path_rac;
		int currant_cmd_index = 1;
		if ((!host.isEmpty()) || (!port.isEmpty())) {
			String port_host = new String("");
			if (!host.isEmpty()) {
				port_host = port_host + host;
			}
			if (!port.isEmpty()) {
				port_host = port_host + ":" + port;
			}
			cmd[currant_cmd_index++] = port_host;
		}
		
		if (!command.isEmpty()) {
			cmd[currant_cmd_index++] = getCommand();
			if (!subcommand.isEmpty()) {
				cmd[currant_cmd_index++] = getSubcommand();
			}
			
		}
		for (int i = 0; i < args1.length; i++) {
			cmd[q_args + i] = args1[i];
		}

		exitVal = commandExecuter.executeCommand(cmd);

		if (exitVal == 0) {
			stdmsg = commandExecuter.getStdMessages();
			for (String err : stdmsg) {
				System.out.println(err);
			}
		} else {
			stdmsg = commandExecuter.getStdMessages();
			for (String err : stdmsg) {
				System.out.println(err);
			}

			errmsg = commandExecuter.getErrMessages();
			for (String msg : errmsg) {
				System.out.println(msg);
			}
		}
		System.out.println("ExitValue: " + exitVal);

	}

	public String getSubcommand() {
		return subcommand;
	}

	public void takeClusterID() {
		if (cluster_name.isEmpty()) {
			System.err.print("не указано имя кластера");
		} else {
			// задаем команды
			setCommand("cluster");
			setSubcommand("list");
			String [] args1 = new String [] {};

			execute(args1);
			// анализируем ответ
			if (exitVal == 0) {
				ArrayList<String> strset = getStdmsg();
				String last_cluster = new String("");
				for (int i = 0; i < strset.size(); i++) {
					String curstr = strset.get(i);
					
				}
				
				
				
				
			} else {
				System.err.print("ошибочное имя кластера :" + getCluster_name());
			}

		}
	}


}
