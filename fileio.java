package_GakufunoHeya;

import java.io.*;

import java.io.*;
import GakufunoHeya.*;

public class fileio{
	private File saveFile;
	private Status stat;
	public static final String saveFileName = "status.sav"

	public fileio(Status stat){
		saveFile = new File(saveFileName,false);
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
			PrintWriter pw = new PrintWriter(
					new OutputStreamWriter(new FileOutputStream(this.saveFile)));
			pw.printf("%d,%d,%d,%d,%d,%d,%s\n",
					stat.getLevel(),
					stat.getExp(),
					stat.getExpLimit()
					stat.getMoney()
					stat.getFull()
					stat.getFullLimit()
					stat.getName());
			pw.close()
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public void loadStatus(){
		try{
			BufferedReader br = new BufferedReader(FileReader(this.saveFile));
			StringTokenizer st = new StringTokenizer(br.readLine(),",");

			stat.setLevel(parseInt(st.nextToken()));
			stat.setExp(parseInt(st.nextToken()));
			stat.setExpLimit(parseInt(st.nextToken()));
			stat.setMoney(parseInt(st.nextToken()));
			stat.setFull(parseInt(st.nextToken()));
			stat.setFullLimit(parseInt(st.nextToken()));
			stat.setName(st.nextToken());

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
        

