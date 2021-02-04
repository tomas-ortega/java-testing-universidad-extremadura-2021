package tutorial.tdd.end2end;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.logging.Level;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class AmazonBookTest {
	
	@BeforeAll
	public static void deactivateLog() {
		java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
	}

	@Test
	@Disabled
	void testHtmlHomePage() throws Exception {
		final WebClient webClient = new WebClient();
		
		final HtmlPage homePage = webClient.getPage("https://htmlunit.sourceforge.io");
		
		assertTrue(homePage.getTitleText().contains("Welcome"));
		
		final String pageAsXml = homePage.asXml();
		
		assertTrue(pageAsXml.contains("<div class=\"container-fluid\">"));
		
		final String pageAsText = homePage.asText();
		
		assertTrue(pageAsText.contains("Support for the HTTP and HTTPS protocols"));
	}
	
	@Test
	@Disabled
	void testSearchBookAmazonAlberCamusCheckPrice() throws Exception {
		final WebClient webClient = new WebClient();
	    String bookPrice = null;
		
	    //Obtener la página de Amazon
	    final HtmlPage amazonHomePage = webClient.getPage("https://www.amazon.es");
	    
	    //Obtener el formulario de búsqueda de artículos de la página principal de Amazon
	    final HtmlForm formSearch = amazonHomePage.getFormByName("site-search");
	    
	    //Obtener el elemento html input text del formulario
	    final HtmlTextInput inputTextField = formSearch.getInputByName("field-keywords");
	    
	    //Obtener el botón del formulario de búsqueda (lupita)
	    final HtmlSubmitInput searchButton = formSearch.getInputByValue("Ir");
	    
	    //Inserto en el cajetín de texto un patrón de búsqueda
	    inputTextField.type("albert camus el extranjero");
	    
	    //Hago clic en el botón de la lupa y obtengo a la página de resultados
	    final HtmlPage bookSearchResult = searchButton.click();
	    
	    //Seleccionar de la lista de libros encontrados el primer libro de la lista
	    List<HtmlAnchor> nodeListAHref = bookSearchResult.getByXPath("//a[contains(@class, 'a-link-normal a-text-normal')]");
	    
	    HtmlPage bookPageDetail = nodeListAHref.get(0).click();
	    
	    DomElement priceElement =  bookPageDetail.getFirstByXPath("//span[contains(@class, 'a-size-medium a-color-price offer-price a-text-normal')]");
	    
	    bookPrice = priceElement.getChildNodes().get(0).getNodeValue();
	    
	    System.out.println("BOOK PRICE: " + bookPrice);
	    
	    assertTrue(bookPrice.contains("9,50"));
	}
}








