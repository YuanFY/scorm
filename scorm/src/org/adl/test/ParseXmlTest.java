package org.adl.test;

import java.io.FileInputStream;
import java.io.InputStream;

import org.adl.samplerte.server.LMSManifestHandler;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class ParseXmlTest {
	
	@Test
	public void test() throws Exception{
		LMSManifestHandler handler = new LMSManifestHandler();
		InputStream ins = new FileInputStream("F:\\SampleRTEFiles\\tempUploads\\54FD24AA970579527A97E9F8F95BD644\\imsmanifest.xml");
		InputSource in = new InputSource(ins);
		handler.parse(in);
		Document dom = handler.getDocument();
		System.out.println(dom);
	}
}
