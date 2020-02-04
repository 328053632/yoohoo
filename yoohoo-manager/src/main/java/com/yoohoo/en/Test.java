package com.yoohoo.en;

import org.apache.shiro.crypto.hash.Sha256Hash;

public class Test {

	public static void main(String[] args) {
		System.out.println(new Sha256Hash("123456").toHex());
	}

}
