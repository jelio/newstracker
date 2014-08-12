package bg.nbu.f58946.parsers;

import bg.nbu.f58946.bo.Site;
import bg.nbu.f58946.parsers.dir.FetchTextDir;
import bg.nbu.f58946.parsers.dnevnik.FetchTextDnevnik;
import bg.nbu.f58946.parsers.offnews.FetchTextOffnews;
import bg.nbu.f58946.parsers.sega.FetchTextSega;
import bg.nbu.f58946.parsers.trud.FetchTextTrud;

public class FetchTextFactory {

	public static IFetchText getFetcher(Site s) {
		switch (s.getName()) {
		case "dnevnik":
			return new FetchTextDnevnik(s);
		case "dir":
			return new FetchTextDir(s);
		case "sega":
			return new FetchTextSega(s);
		case "trud":
			return new FetchTextTrud(s);
		case "offnews":
			return new FetchTextOffnews(s);
		default:
			break;

		}
		return null;

	}
}
