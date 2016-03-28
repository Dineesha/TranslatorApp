package loginmongo;

/**
 * Created by hsenid on 3/16/16.
 */

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

public class DomXmlParser {

	

	public String readResponse(InputStream input) {


		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			String er1=e.getMessage();
			System.out.println("Exception thrown  :\" + e");
			System.out.println("exception :\" +er1");
			//e.printStackTrace();
		}
		Document doc = null;
		try {
			doc = dBuilder.parse(input);
		} catch (SAXException e) {
			//e.printStackTrace();
			String er1=e.getMessage();
			System.out.println("Exception thrown  :\" + e");
			System.out.println("exception :\" +er1");
		} catch (IOException e) {
			String er1=e.getMessage();
			System.out.println("Exception thrown  :\" + e");
			System.out.println("exception :\" +er1");
			//e.printStackTrace();
		}


		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

		NodeList nList = doc.getElementsByTagName("text");

		System.out.println("----------------------------");
		// ArrayList<String> listValues = new ArrayList<String>();

		// String
		// passValue=String.valueOf(listValues.add(nList.item(0).getAttributes().getNamedItem("text").getNodeValue()));
		String passValue = nList.item(0).getTextContent();

		System.out.println(passValue);
		return passValue;

	}
}
