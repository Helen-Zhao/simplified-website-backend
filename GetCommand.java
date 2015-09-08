package assignment2;

class GetCommand {
	String command;
	@SuppressWarnings("unused") //This class gets the command and calls the appropriate class
	void ExecuteCommand(String com) throws ArrayIndexOutOfBoundsException{
		try {
			command = com;
			String[] args = com.split(":");
			if (args[0].equalsIgnoreCase("LISTCONTENT")){
				ListContent lc = new ListContent(command);
			} else if (args[0].equalsIgnoreCase("LISTMEMBERS")) {
				ListMembers lm = new ListMembers(command);
			} else if (args[0].equalsIgnoreCase("LISTFOLDER")) {
				ListFolder lf = new ListFolder(command, args[1]);
			} else if (args[0].equalsIgnoreCase("LISTMEMBERCONTENT")){
				ListMemberContent lmc = new ListMemberContent(command, args[1], args[2]);
			} else if (args[0].equalsIgnoreCase("LISTVISIBLECONTENT")){
				ListVisibleContent lvc = new ListVisibleContent(command, args[1]);
			} else if (args[0].equalsIgnoreCase("LISTCONTENTWITHTAG")){
				ListContentWithTag lcwt = new ListContentWithTag(command, args[1], args[2]);
			} else if (args[0].equalsIgnoreCase("LISTCONTENTLESS")){
				ListContentless lcl = new ListContentless(command);
			} else if (args[0].equalsIgnoreCase("LISTUNOWNED")){
				ListUnowned lu = new ListUnowned(command);
			} else { //If invalid, throw exception
				System.out.println("Invalid command, please restart program with a valid command.");
			}
		} catch(ArrayIndexOutOfBoundsException missingArgs){
			System.out.println("Arguments missing for command " + command + ", please try again.");
		}
	}
}
