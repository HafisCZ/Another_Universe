package utilities;

public class UCounter {
	
	private long value_end;
	private long value;
	private boolean enabled;

	public UCounter(long needed_value){
		this.value_end = needed_value;
		this.value = 0;
		this.enabled = true;
	}
	
	public void setEndLoop(long end){
		this.value_end = end;
	}
	
	public void updateLoop(int increase_by){
		if(this.enabled && this.value <= this.value_end){
			this.value += increase_by;
		}
	}
	
	public void enableLoop(){
		this.value = 0;
		this.enabled = true;
	}
	
	public boolean isEnabled(){
		return this.enabled;
	}
	
	public void pauseLoop(boolean pause){
		this.enabled = pause;
	}
	
	public void disableLoop(){
		this.value = 0;
		this.enabled = false;
	}
	
	public void resetLoop(){
		this.value = 0;
	}
	
	public void setLoop(long loop){
		this.value = loop;
	}
	
	public long getCurrentLoop(){
		return this.value;
	}
	
	public long getEndLoop(){
		return this.value_end;
	}
	
	public boolean checkLoop(){
		if(this.value >= this.value_end && this.enabled){
			this.value = 0;
			return true;
		}else{
			return false;
		}
	}
	
}
