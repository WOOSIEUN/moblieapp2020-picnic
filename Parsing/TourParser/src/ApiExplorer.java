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
		String trrsrtNm= "", ar = "", appnDate = "", aceptncCo = "", prkplceCo = "", trrsrtIntrcn = "", institutionNm = "",
				rdnmadr = "", lnmadr = "", latitude = "", longitude = "";
		try {
			// XML 데이터를 읽어옴
			for (int page = 1; page < 7; page++) {
				URL url = new URL(
						"http://api.data.go.kr/openapi/tn_pubr_public_trrsrt_api?serviceKey=gsjKp5IjStX8lMcA8%2BMWpEXSB00etOkWuRsrKRYKaWQm4%2BF7Cr%2Fa%2B1w5eRG2T61WtzSo1CbtPSvxGqL5zaQrpQ%3D%3D&numOfRows=100&type=xml&pageNo=" + page);
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
						if (subNode.getNodeName().equals("trrsrtNm"))
							trrsrtNm = subNode.getTextContent();
						if (subNode.getNodeName().equals("ar"))
							ar = subNode.getTextContent();
						if (subNode.getNodeName().equals("appnDate"))
							appnDate = subNode.getTextContent();
						if (subNode.getNodeName().equals("aceptncCo"))
							aceptncCo = subNode.getTextContent();
						if (subNode.getNodeName().equals("prkplceCo"))
							prkplceCo = subNode.getTextContent();
						if (subNode.getNodeName().equals("trrsrtIntrcn"))
							trrsrtIntrcn = subNode.getTextContent();
						if (subNode.getNodeName().equals("institutionNm"))
							institutionNm = subNode.getTextContent();
						if (subNode.getNodeName().equals("rdnmadr"))
							rdnmadr = subNode.getTextContent();
						if (subNode.getNodeName().equals("lnmadr"))
							lnmadr = subNode.getTextContent();
						if (subNode.getNodeName().equals("latitude"))
							latitude = subNode.getTextContent();
						if (subNode.getNodeName().equals("longitude"))
							longitude = subNode.getTextContent();
					}
					System.out.println("item #" + i);
					System.out.println("관광지명 : " + trrsrtNm);
					System.out.println("면적 : " + ar);
					System.out.println("지정일자 : " + appnDate);
					System.out.println("수용인원 수 : " + aceptncCo);
					System.out.println("주차가능 수 : " + prkplceCo);
					System.out.println("내용 : " + trrsrtIntrcn);
					System.out.println("관리기관 : " + institutionNm);
					System.out.println("도로명주소 : " + rdnmadr);
					System.out.println("지번주소 : " + lnmadr);
					System.out.println("위도 : " + latitude);
					System.out.println("경도 : " + longitude);
					System.out.println("******************");
					
					MySQL.insert("TOUR", trrsrtNm, ar, appnDate, aceptncCo, prkplceCo, trrsrtIntrcn, institutionNm, rdnmadr, lnmadr, latitude, longitude);
					
					trrsrtNm = "";
					ar = "";
					appnDate = "";
					aceptncCo = "";
					prkplceCo= "";
					trrsrtIntrcn = "";
					institutionNm = "";
					rdnmadr = "";
					lnmadr = "";
					latitude = "";
					longitude = "";
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}