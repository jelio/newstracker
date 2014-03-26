package bg.nbu.f58946.parsers;

public enum Feeders {
	DNEVNIK(1), OFFNEWS(2), BNTBG(3), TRUD(4), SEGA(5);

	private int number;

	Feeders(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}
}
