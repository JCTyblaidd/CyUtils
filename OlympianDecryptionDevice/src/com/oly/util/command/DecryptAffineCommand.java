package com.oly.util.command;

import java.awt.Color;

import com.oly.decryption.key.KeyDecrypter;
import com.oly.ui.TextWindow;
import com.oly.util.ITextWindowCommand;
import com.oly.web.WebScraper;

public class DecryptAffineCommand implements ITextWindowCommand{
	
	
	@Override
	public String getName() {
		return "affine";
	}

	@Override
	public void run(String[] args, TextWindow link) {
		if(args.length != (3 + 1)) {
			link.write_col("affine [text/{WEBID}] [shift a*x] [shift +c]", Color.RED);
			return;
		}else {
			String toDecrypt = getText(args[0+1].trim());
			int i = Integer.parseInt(args[1+1].trim());
			int j = Integer.parseInt(args[2+1].trim());
			link.write_col(KeyDecrypter.shiftAffine(toDecrypt, i, j).substring(4),Color.GRAY);
		}
	}
	
	
	private String getText(String str) {
		//WEB =FORMAT {3B}
		if(str.startsWith("{") && str.endsWith("}")) {
			char i = str.charAt(1);
			char j = Character.toUpperCase(str.charAt(2));
			
			return WebScraper.getChallenge(Integer.parseInt(new String(new char[]{j})), i);
		}else {
			return str;
		}
	}
	
}
