package bg.nbu.f58946.parsers;

import bg.nbu.f58946.parsers.dnevnik.ParserDnevnik;

public class ParserFactory {
	public static IParser getParser(Feeders feed, String url) {
		switch (feed) {
		case DNEVNIK:
			return new ParserDnevnik();
		case BNTBG:
			break;
		case BTABG:
			break;
		case FOCUS:
			break;
		case NEWSBG:
			break;
		default:
			break;
		}
		return null;
	}
}
