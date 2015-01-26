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
	private int money;
	private int full, fullLimit;
	private String name;

	public int getLevel(){return level;}
	public int getExp(){return exp;}
	public int getExpLimit(){return expLimit;}
	public int getMoney(){return money;}
	public int getFull(){return full;}
	public int getFullLimit(){return fullLimit;}
	public String getName(){return name;}

	public Status(){
		this("");
	}
	public Status(String name){
		this(1,0,10,50,name);
	}
	public Status(int level,int exp, int expLimit, int money, String name){
		this(level,exp,expLimit,money,50,  100,      name);
		/*   level exp expLimit money full fulllimit name */
	}
	public Status(int level,int exp, int expLimit,int money, int full, int fullLimit, String name){
		setLevel(level);
		setExp(exp);
		setExpLimit(expLimit);
		setMoney(money);
		setName(name);
		setFull(full);
		setFullLimit(fullLimit);
	}
	public void setName(String name){
		if(name == ""){
			this.name = "Jenco";
		}else{
			this.name = name;
		}
	}
	public int eatFoods(){
		if(getFull() + 40 < getFullLimit() + 20){
			setFull(getFull() + 40);
			increaseExp(10);
			return 0;
		}else{
			// won't eat food motion
			return -1;
		}
	}
	public void increaseExp(int increaseValue){
		setExp(getExp()+increaseValue);
		if(getExp() > getExpLimit()){
			levelUp();
		}
	}
	private void setFull(int full){
		if(full<1){
			isDead=true;
			full=0;
		}else{
			if(full < getFullLimit())
				this.full = full;
			else
				this.full = getFullLimit();
		}
	}
	private void setMoney(int money){
		if(money<0) this.money=0;
		else this.money=money;
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
	private void setFullLimit(int limit){
		this.fullLimit = limit;
	}

	private void levelUp(){
		setLevel(getLevel()+1);
		setExp(getExp()-getExpLimit());
		setExpLimit(getExpLimit()+10*getLevel());
		setFullLimit(getFullLimit() + 5);
	}
}
