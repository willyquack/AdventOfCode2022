import java.io.*;
import java.util.*;
class d16 {
	public static void main(String[] args) {
		try {
			new PVolcano();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static class PVolcano {
		class Valve {
			String name;
			int flowRate;
			ArrayList<String> tunnels;
			boolean open;
			Valve(String n,int rate, ArrayList<String> tunnels){
				this.name = n;
				this.flowRate = rate;
				this.tunnels = tunnels;
				open = false;
			}
		}
		HashMap<String,Valve> valves;
		PVolcano() throws IOException {
			Scanner scan = new Scanner(new File("./small.txt"));
			valves = new HashMap<>();
			while(scan.hasNextLine()) {
				String[] line = scan.nextLine().split(" ");
				String name = line[1];
				int rate = Integer.parseInt(line[4].split("=|;")[1]);
				ArrayList<String> tunnels = new ArrayList<>();
				for(int i = 9;i < line.length;i++) {
					tunnels.add(line[i].split(",")[0]);	
				}
				valves.put(name,new Valve(name,rate,tunnels));
			}
			int minutes = 0;
			Valve curValve = valves.get("AA");
			while(minutes < 30) {
				String move = getMove(curValve);
			}
		}
		String getMove(Valve curValve) {
			return getMove(valves,curValve);
		}
		String getMove(HashMap<String,Valve> curState, Valve curValve,int curValue,int curMinutes) {
			if(curMinutes == 30) {
				return "open " + curValve.name;
			}
			int v = curValve.flowRate * curMinutes;
			String bestMove = "open" + curValve.name;

		}
	}
}
