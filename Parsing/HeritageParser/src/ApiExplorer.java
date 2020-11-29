import java.io.*;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* Name of the class has to be "Main" only if the class is public. */
public class ApiExplorer {

	public static void main(String[] args) throws java.lang.Exception {
		int count = 0;
		String ccmaName = "", ccbaMnm1 = "", ccbaKdcd = "", ccbaAsno = "", ccbaCtcd = "", latitude = "", longitude = "";
		String ccbaLcad = "", ccceName = "", ccbaAsdt = "", imageUrl = "", content = "";
		try {
			// XML 데이터를 읽어옴
			for (int page = 30; page <= 50 ; page++) {
				URL url = new URL(
						"http://www.cha.go.kr/cha/SearchKindOpenapiList.do?pageUnit=100&pageIndex=" + page);
				InputStream in = url.openStream();

				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = factory.newDocumentBuilder();
				Document doc = db.parse(in);
				Element el = doc.getDocumentElement();
				// <item> ~ </item>을 하나의 노드로 노드 리스트를 만듬
				NodeList itemList = el.getElementsByTagName("item");
				for (int i = 0; i < itemList.getLength(); i++) {
					// <item> ~ </item> 노드를 하나씩 읽어옴
					Node itemNode = itemList.item(i);
					// <item> ~ </item> 사이의 태그들로 노드 리스트를 만듬
					NodeList subList = itemNode.getChildNodes();

					// <item> ~ </item> 사이의 태그를 하나씩 읽어와 해당 태그와 일치할 경우 변수에 저장
					for (int j = 0; j < subList.getLength(); j++) {
						Node subNode = subList.item(j);
						if (subNode.getNodeName().equals("ccmaName"))
							ccmaName= subNode.getTextContent();
						if (subNode.getNodeName().equals("ccbaMnm1"))
							ccbaMnm1= subNode.getTextContent();
						if (subNode.getNodeName().equals("ccbaKdcd"))
							ccbaKdcd= subNode.getTextContent();
						if (subNode.getNodeName().equals("ccbaAsno"))
							ccbaAsno = subNode.getTextContent();
						if (subNode.getNodeName().equals("ccbaCtcd"))
							ccbaCtcd = subNode.getTextContent();
						if (subNode.getNodeName().equals("latitude"))
							latitude = subNode.getTextContent();
						if (subNode.getNodeName().equals("longitude"))
							longitude = subNode.getTextContent();
					}
					URL urlDetail = new URL(
							"http://www.cha.go.kr/cha/SearchKindOpenapiDt.do?ccbaKdcd=" + ccbaKdcd + "&ccbaAsno=" + ccbaAsno + "&ccbaCtcd=" + ccbaCtcd);
					InputStream inDetail = urlDetail.openStream();

					DocumentBuilderFactory factoryDetail = DocumentBuilderFactory.newInstance();
					DocumentBuilder dbDetail = factoryDetail.newDocumentBuilder();
					Document docDetail = dbDetail.parse(inDetail);
					Element elDetail = docDetail.getDocumentElement();
					// <item> ~ </item>을 하나의 노드로 노드 리스트를 만듬
					NodeList itemListDetail = elDetail.getElementsByTagName("item");
					for (int k = 0; k < itemListDetail.getLength(); k++) {
						// <item> ~ </item> 노드를 하나씩 읽어옴
						Node itemNodeDetail = itemListDetail.item(k);
						// <item> ~ </item> 사이의 태그들로 노드 리스트를 만듬
						NodeList subListDetail = itemNodeDetail.getChildNodes();

						// <item> ~ </item> 사이의 태그를 하나씩 읽어와 해당 태그와 일치할 경우 변수에 저장
						for (int l = 0; l < subListDetail.getLength(); l++) {
							Node subNodeDetail = subListDetail.item(l);
							if (subNodeDetail.getNodeName().equals("ccbaLcad"))
								ccbaLcad= subNodeDetail.getTextContent();
							if (subNodeDetail.getNodeName().equals("ccceName"))
								ccceName= subNodeDetail.getTextContent();
							if (subNodeDetail.getNodeName().equals("ccbaAsdt"))
								ccbaAsdt= subNodeDetail.getTextContent();
							if (subNodeDetail.getNodeName().equals("imageUrl"))
								imageUrl = subNodeDetail.getTextContent();
							if (subNodeDetail.getNodeName().equals("content"))
								content = subNodeDetail.getTextContent();
						}
					}
					System.out.println("item #" + ++count);
					System.out.println("문화재종목 : " + ccmaName);
					System.out.println("문화재명 : " + ccbaMnm1);
					System.out.println("종목코드 : " + ccbaKdcd);
					System.out.println("지정번호 : " + ccbaAsno);
					System.out.println("시도코드 : " + ccbaCtcd);
					System.out.println("위도 : " + latitude);
					System.out.println("경도 : " + longitude);
					System.out.println("소재지상세 : " + ccbaLcad.trim());
					System.out.println("시대 : " + ccceName);
					System.out.println("지정일 : " + ccbaAsdt);
					System.out.println("이미지URL : " + imageUrl);
					System.out.println("내용 : " + content);
					System.out.println("******************");
					
					MySQL.insert("HERITAGE", ccbaMnm1, ccmaName, ccbaLcad.trim(), ccceName, content, ccbaAsdt, ccbaCtcd, imageUrl, latitude, longitude);
					
					ccmaName = "";
					ccbaMnm1 = "";
					ccbaKdcd = "";
					ccbaAsno = "";
					ccbaCtcd = "";
					latitude = "";
					longitude = "";
					ccbaLcad = "";
					ccceName = "";
					ccbaAsdt = "";
					imageUrl = "";
					content = "";
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}