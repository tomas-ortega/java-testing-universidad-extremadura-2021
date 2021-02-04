package tutorial.tdd.end2end;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.meterware.httpunit.HttpUnitOptions;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebResponse;

public class HttpUnitTest {
	
	@BeforeAll
	public static void init() {
		HttpUnitOptions.setScriptingEnabled(false);
	}
	
	@Test
	@Disabled
	void testLoadGoogle() throws Exception {
		WebConversation webconversation = new WebConversation();
		WebResponse response = null;
		
		response = webconversation.getResponse("https://www.google.es");
		
		assertEquals(200, response.getResponseCode());
		
		WebForm[] forms = response.getForms();
		
		assertTrue(forms.length == 1);
		
		assertEquals("Buscar con Google", forms[0].getParameter("q").getControls()[0].getTitle());
	}
}
