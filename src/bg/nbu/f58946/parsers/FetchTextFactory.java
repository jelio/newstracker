package bg.nbu.f58946.parsers;

import bg.nbu.f58946.parsers.bnt.FetchTextBnt;
import bg.nbu.f58946.parsers.dnevnik.FetchTextDnevnik;
import bg.nbu.f58946.parsers.offnews.FetchTextOffnews;
import bg.nbu.f58946.parsers.sega.FetchTextSega;
import bg.nbu.f58946.parsers.trud.FetchTextTrud;

public class FetchTextFactory {

	public static IFetchText fetchText(Feeders feed) {
		switch (feed) {
		case DNEVNIK:
			return new FetchTextDnevnik();
		case BNTBG:
			return new FetchTextBnt();
		case SEGA:
			return new FetchTextSega();
		case TRUD:
			return new FetchTextTrud();
		case OFFNEWS:
			return new FetchTextOffnews();
		default:
			break;

		}
		return null;

	}
}
