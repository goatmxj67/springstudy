package ex05_java;

public class Singer {

	private String name;
	private Song song;
	
	public Singer() {}
	public Singer(String name, Song song) {
		super();
		this.name = name;
		this.song = song;
	}

	public void info() {
		System.out.println("가수명: " + name);
		System.out.println("대표곡명: " + song.getTitle());
		System.out.println("대표곡장르: " + song.getGenre());
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Song getSong() {
		return song;
	}
	public void setSong(Song song) {
		this.song = song;
	}
	
}
