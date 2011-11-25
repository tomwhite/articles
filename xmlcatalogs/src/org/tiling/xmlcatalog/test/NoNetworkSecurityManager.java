package org.tiling.xmlcatalog.test;

import java.net.SocketPermission;
import java.security.AccessControlException;
import java.security.Permission;

public class NoNetworkSecurityManager extends SecurityManager {
	
	public void checkConnect(String host, int port, Object context) {
		checkConnect(host, port);
	}

	public void checkConnect(String host, int port) {
		if (host == null) {
			throw new NullPointerException("host can't be null");
		}
		if (!host.startsWith("[") && host.indexOf(':') != -1) {
			host = "[" + host + "]";
		}
		Permission permission;
		if (port == -1) {
			permission = new SocketPermission(host, "resolve");
		} else {
			permission = new SocketPermission(host + ":" + port, "connect");
		}
		throw new AccessControlException("Opening a socket connection is restricted.", permission);
	}

}
