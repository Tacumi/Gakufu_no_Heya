package GakufunoHeya;
/**
 * このクラスはそれぞれのフィールドについてゲッターとセッターを持っています.
 * 経験値を加算したいときはincreaseExp(int 経験値)としてください.
 * レベルアップの処理もその時に行います.
 * レベルアップは安全のため外部から呼び出すことの出来ないメソッドになっています. **/

public class Status{
	private int level;
	private int exp;
	private int expLimit;
	private String name;

	public Status(){
		Status("");
	}
	public Status(String name){
		Status(1,0,10,name);
	}
	public Status(int level,int exp, int expLimit,String name){
		setLevel(level);
		setExp(exp);
		setExpLimit(expLimit);
		setName(name);
	}
	public void setLevel(int level){
		if(level<1){
			level = 1;
		}else{
			this.level = level;
		}
		return;
	}
	public void setExp(int exp){
		if(exp<0){
			exp = 0;
		}else{
			this.exp = exp;
		}
		return;
	}
	public void setExpLimit(int expLimit){
		if(expLimit<1){
			expLimit = 1;
		}else{
			this.expLimit = expLimit;
		}
		return;
	}
	public void setName(String name){
		if(name == ""){
			this.name = "Jenco";
		}else{
			this.name = name;
		}
	}
	public void increaseExp(int increaseValue){
		setExp(getExp()+increaseValue);
		if(getExp() > getExpLimit()){
			levelUp();
		}
	}
	public int getLevel(){return level;}
	public int getExp(){return exp;}
	public int getExpLimit(){return expLimit;}
	public String getName(){return name;}

	private void levelUp(){
		setLevel(getLevel()+1);
		setExp(getExp()-getExpLimit());
		setExpLimit(getExpLimit()+10*getLevel());
	}
}
