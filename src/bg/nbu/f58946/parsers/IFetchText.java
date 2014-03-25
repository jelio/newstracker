package bg.nbu.f58946.parsers;

import org.jsoup.nodes.Document;

import bg.nbu.f58946.exceptions.BusinessException;

public interface IFetchText {
	String getArticleText(Document doc) throws BusinessException;
}
