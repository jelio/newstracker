package bg.nbu.f58946.parsers;

public enum Feeders {
	DNEVNIK(100), NEWSBG(200), BNTBG(300), FOCUS(400), BTABG(500);

	private int number;

	Feeders(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}
}
