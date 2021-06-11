package quiz02;

public class Speaker {

	private int volume;  // 0~100
	
	public void volUp() {
		volume++;
		if (volume > 100) volume = 100;
		System.out.println("현재 볼륨: " + volume);
	}
	public void volDown() {
		volume--;
		if (volume < 0) volume = 0;
		System.out.println("현재 볼륨: " + volume);
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	
}
