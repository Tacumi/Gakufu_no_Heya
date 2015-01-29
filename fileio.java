package_GakufunoHeya;

import java.io.*;

import java.io.*;
import GakufunoHeya.*;

public class fileio{
	private File saveFile;
	private Status stat;
	public static final String saveFileName = "status.sav";

	public fileio(Status stat){
		saveFile = new File(saveFileName);
		this.stat = stat;
		if(isReadable(saveFile)){
			// no operation
		}else
		{
			try{
				this.saveFile.createNewFile();
				saveStatus();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	public void saveStatus(){
		try{
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(this.saveFile)));
			pw.printf("%d,%d,%d,%d,%d,%d,%s\n",
					stat.getLevel(),
					stat.getExp(),
					stat.getExpLimit(),
					stat.getMoney(),
					stat.getFull(),
					stat.getFullLimit(),
					stat.getName());
			pw.flush();
			pw.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public void loadStatus(){
		String[] valueString;
		try{
			BufferedReader br = new BufferedReader(new FileReader(this.saveFile));
			valueString = br.readLine().split(",");

			stat.setLevel(Integer.parseInt(valueString[0]));
			stat.setExp(Integer.parseInt(valueString[1]));
			stat.setExpLimit(Integer.parseInt(valueString[2]));
			stat.setMoney(Integer.parseInt(valueString[3]));
			stat.setFull(Integer.parseInt(valueString[4]));
			stat.setFullLimit(Integer.parseInt(valueString[5]));
			stat.setName(valueString[6]);

			br.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	boolean isReadable(File file) {
		if (file.exists()) {
			if (file.isFile() && file.canRead()) {
				return true;
			}
		}
		return false;
	}
}

;
        

