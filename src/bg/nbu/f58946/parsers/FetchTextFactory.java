package bg.nbu.f58946.parsers;

import bg.nbu.f58946.parsers.dnevnik.FetchTextDnevnik;

public class FetchTextFactory {

	public static IFetchText fetchText(Feeders feed) {
		switch (feed) {
		case DNEVNIK:
			return new FetchTextDnevnik();
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
