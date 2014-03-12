package com.blackmoon.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

public class XMLReader {

	Context context;
	XmlPullParser parser;
	DatabaseHandler dbHandler;

	public XMLReader(Context context) {
		this.context = context;
		dbHandler = new DatabaseHandler(context);
	}

	public void readXMl() {
		try {
			dbHandler = new DatabaseHandler(context);
			dbHandler.open();
			
			InputStream is = context.getAssets().open("tinhyeu.xml");
			DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document document = docBuilder.parse(is);
			NodeList listDanhNgon = document.getElementsByTagName("item");
			for (int i = 0; i < listDanhNgon.getLength(); i++) {
				Node nodeWord = listDanhNgon.item(i);
				IdiomItem item = new IdiomItem();
				item.set_category(nodeWord.getParentNode().getNodeName());
				item.set_english(nodeWord.getChildNodes().item(1).getTextContent());
				item.set_vietnamese(nodeWord.getChildNodes().item(3).getTextContent());
				item.set_author(nodeWord.getChildNodes().item(5).getTextContent());
				Log.d("Read XML", item.toString());
				dbHandler.insertIdiomItem(item);
				
			}
			
			dbHandler.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
