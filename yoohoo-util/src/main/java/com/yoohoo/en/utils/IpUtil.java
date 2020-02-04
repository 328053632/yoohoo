package com.yoohoo.en.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class IpUtil {

	@SuppressWarnings("rawtypes")
	public static String getServerIp() {
		String serverIp = null;
		try {
			Enumeration netInterfaces = NetworkInterface.getNetworkInterfaces();
			InetAddress ip = null;
			while (netInterfaces.hasMoreElements()) {
				NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
				Enumeration InterAddress = ni.getInetAddresses();
				boolean findAddr = false;
				while (InterAddress.hasMoreElements()) {
					ip = (InetAddress) InterAddress.nextElement();
					serverIp = ip.getHostAddress();
					if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {
						serverIp = ip.getHostAddress();
						findAddr = true;
						break;
					}
				}
				if (findAddr) {
					break;
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}

		return serverIp;
	}

	public static String getLocalIP() {
		InetAddress addr = null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		byte[] ipAddr = addr.getAddress();
		String ipAddrStr = "";
		for (int i = 0; i < ipAddr.length; i++) {
			if (i > 0) {
				ipAddrStr += ".";
			}
			ipAddrStr += ipAddr[i] & 0xFF;
		}
		return ipAddrStr;
	}

	public static void main(String[] args) {
		System.out.println(getServerIp());
	}
}
