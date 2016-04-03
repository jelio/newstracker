package bg.nbu.f58946.parsers;

import bg.nbu.f58946.bo.ALL_SITES;
import bg.nbu.f58946.parsers.dir.FetchTextDir;
import bg.nbu.f58946.parsers.dnevnik.FetchTextDnevnik;
import bg.nbu.f58946.parsers.offnews.FetchTextOffnews;
import bg.nbu.f58946.parsers.sega.FetchTextSega;
import bg.nbu.f58946.parsers.trud.FetchTextTrud;

public class FetchTextFactory {

	public static IFetchText getFetcher(ALL_SITES site) {
		switch (site.toString()) {
		case "DNEVNIK":
			return new FetchTextDnevnik(site);
		case "DIR":
			return new FetchTextDir(site);
		case "SEGA":
			return new FetchTextSega(site);
		case "TRUD":
			return new FetchTextTrud(site);
		case "OFFNEWS":
			return new FetchTextOffnews(site);
		default:
			break;

		}
		return null;

	}
}
