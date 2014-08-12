package bg.nbu.f58946.parsers;

import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import bg.nbu.f58946.bo.Article;
import bg.nbu.f58946.exceptions.BusinessException;

public interface IFetchText {
	String getArticleText(Document doc) throws BusinessException;
	public String getTitle(Document doc) throws BusinessException;
	Elements getElements(Document document);  
	public Map<String, Article> getArticles() throws BusinessException ; 
}
