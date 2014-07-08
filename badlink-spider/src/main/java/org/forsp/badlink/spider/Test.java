package org.forsp.badlink.spider;

import org.forsp.badlink.spider.impl.HtmlUnitBrowser;

public class Test {

	public static void main(String[] arg) {
		HtmlUnitBrowser br = new HtmlUnitBrowser("http://znaj.net");
		br.open("http://znaj.net");
	}
}
