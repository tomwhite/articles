package org.tiling.s3proxy;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.ServletHolder;

public class S3Proxy {
	public static void main(String[] args) throws Exception {
		if (args.length != 3) {
			System.err.println("Usage: java " + S3Proxy.class.getName() + " awsAccessKey awsSecretKey s3Hostname");
			System.exit(1);
		}
		
	    Server server = new Server(80);
	    Context root = new Context(server, "/", Context.SESSIONS);
	    ServletHolder servletHolder = new ServletHolder(S3ProxyServlet.class);
	    servletHolder.setInitParameter("awsAccessKey", args[0]);
	    servletHolder.setInitParameter("awsSecretKey", args[1]);
	    servletHolder.setInitParameter("s3Hostname", args[2]);
	    root.addServlet(servletHolder, "/*");
	    server.start();
	    
	    server.join();
	}
}
