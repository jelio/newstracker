package bg.nbu.f58946.parsers;

import bg.nbu.f58946.parsers.dir.ParserDir;
import bg.nbu.f58946.parsers.dnevnik.ParserDnevnik;
import bg.nbu.f58946.parsers.offnews.ParserOffnews;
import bg.nbu.f58946.parsers.sega.ParserSega;
import bg.nbu.f58946.parsers.trud.ParserTrud;

public class ParserFactory {
	public static IParser getParser(Feeders feed, String url) {
		switch (feed) {
		case DNEVNIK:
			return new ParserDnevnik();
		case DIRBG:
			return new ParserDir();
		case SEGA:
			return new ParserSega();
		case TRUD:
			return new ParserTrud();
		case OFFNEWS:
			return new ParserOffnews();
		default:
			break;
		}
		return null;
	}
}
