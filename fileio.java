import java.io.*;
import java.net.*;

class accessCount {
	static int money;
        static int exp;
        




	File dataFile = new File("counter.cgi");
	void checkFile() {
		try {
			if (checkBeforeReadfile(this.dataFile)) {
				this.money = Integer.parseInt(fileToString(this.dataFile));
                                this.exp = Integer.parseInt(fileToString(this.dataFile));
				System.out.println("Last Money: " + this.money);
                                System.out.println("Last exp: " + this.exp);
			} else {
				this.dataFile.createNewFile();
				this.money = 0;
                                this.exp = 0;

				FileOutputStream foutstream = new FileOutputStream(
						this.dataFile);
				OutputStreamWriter foswriter = new OutputStreamWriter(
						foutstream);
				PrintWriter pw = new PrintWriter(foswriter);

				pw.println("Last Money: " + this.money);
                                pw.println("Last Exp: " + this.exp);
				pw.close();
				System.out.println("Create a Counter File.");
			}
		} catch (Exception e) {
		}
	}

	boolean checkBeforeReadfile(File file) {
		if (file.exists()) {
			if (file.isFile() && file.canRead()) {
				return true;
			}
		}
		return false;
	}

	String fileToString(File file) throws IOException {
		BufferedReader br = null;
		try {
			// ファイルを読み込むバッファドリーダを作成します。
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					file)));
			// 読み込んだ文字列を保持するストリングバッファを用意します。
			StringBuffer sb = new StringBuffer();
			// ファイルから読み込んだ一文字を保存する変数です。
			int c;
			// ファイルから１文字ずつ読み込み、バッファへ追加します。
			while ((c = br.read()) != -1) {
				sb.append((char) c);
			}
			// バッファの内容を文字列化して返します。
			return sb.toString();
		} finally {
			// リーダを閉じます。
			br.close();
		}
	}
}

public class fileio {
	public static void main(String args[]) {
		accessCount c = new accessCount();
		File dataFile = c.dataFile;
		try {

			c.checkFile();
			ServerSocket ss = new ServerSocket(12354);
			while (true) {
				Socket s = ss.accept();
				ServerThreadSample sts = new ServerThreadSample(s);
				Thread theThread = new Thread(sts);
				theThread.start();
			}
		} catch (Exception e) {
			System.err.print(e);
		}
	}

	static boolean checkBeforeWritefile(File file) {
		if (file.exists()) {
			if (file.isFile() && file.canWrite()) {
				return true;
			}
		}
		return false;
	}

}

class ServerThreadSample implements Runnable {
	Socket s;

	public ServerThreadSample(Socket socket) {
		this.s = socket;
	}

	public void run() {
		accessCount c = new accessCount();
		File dataFile = c.dataFile;
		try {
			InputStream instream = s.getInputStream();
			InputStreamReader isreader = new InputStreamReader(instream);
			BufferedReader bfreader = new BufferedReader(isreader);

			String theLine;
			theLine = bfreader.readLine();
			theLine = theLine.substring(theLine.indexOf(" ") + 1);
			theLine = theLine.substring(0, theLine.indexOf(" "));
			theLine = theLine.substring(theLine.indexOf("/") + 1);
			System.out.println("\n" + "filename: " + theLine);

			OutputStream outstream = s.getOutputStream();
			OutputStreamWriter oswriter = new OutputStreamWriter(outstream);
			BufferedWriter bfwriter = new BufferedWriter(oswriter);
			PrintWriter pwriter = new PrintWriter(bfwriter);

			File theFile = new File(theLine);
			if (theFile.exists() == false) {
				pwriter.print("HTTP/1.0 404 NotFound\r\n\r\n");
				pwriter.println("404 Error!!");
				pwriter.flush();
				pwriter.close();
				System.err.print("File does not exist:" + theLine + "\n");
				s.close();
				return;
			}
			FileInputStream finstream = new FileInputStream(theFile);
			InputStreamReader fisreader = new InputStreamReader(finstream);
			BufferedReader fbfreader = new BufferedReader(fisreader);

			c.money++;
                        c.exp++;
			FileOutputStream foutstream = new FileOutputStream(dataFile);
			OutputStreamWriter foswriter = new OutputStreamWriter(foutstream);
			PrintWriter pw = new PrintWriter(foswriter);

			pw.print(c.money);
                        pw.print(c.exp);
			pw.close();
			System.out.println("Access Money:" + c.money);
                        System.out.println("Access Exp:" + c.exp);

			pwriter.print("HTTP/1.0 200 OK\r\nContent-Type: text/html\r\n\r\n");
			do {
				theLine = fbfreader.readLine();
				if (theLine != null) {
					pwriter.println(theLine);
				}
			} while (theLine != null);

			pwriter.flush();
			pwriter.close();
		} catch (Exception e) {
			System.err.print(e);
		}
	}
}
