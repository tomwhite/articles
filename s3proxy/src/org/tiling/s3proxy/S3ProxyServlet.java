package org.tiling.s3proxy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.jets3t.service.S3ServiceException;
import org.jets3t.service.impl.rest.httpclient.RestS3Service;
import org.jets3t.service.security.AWSCredentials;

public class S3ProxyServlet extends HttpServlet {
	
	static class PackageRestS3Service extends RestS3Service {

		public PackageRestS3Service(AWSCredentials awsCredentials)
				throws S3ServiceException {
			super(awsCredentials);
		}
		
		@Override
		public void buildAuthorizationString(HttpMethod httpMethod)
				throws S3ServiceException {
			super.buildAuthorizationString(httpMethod);
		}
		
	}
	
	private String awsAccessKey;
	private String awsSecretKey;
	private String s3Hostname;
	
	@Override
	public void init() {
		ServletConfig config = getServletConfig();
		awsAccessKey = config.getInitParameter("awsAccessKey");
		awsSecretKey = config.getInitParameter("awsSecretKey");
		s3Hostname = config.getInitParameter("s3Hostname");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		HttpClient client = new HttpClient();
		HttpMethod method = getMethod(req);
		int statusCode = client.executeMethod(method);
		log(statusCode + " " + method.getURI());
		resp.setStatus(statusCode);
		Header[] responseHeaders = method.getResponseHeaders();
		for (Header header : responseHeaders) {
			resp.setHeader(header.getName(), header.getValue());
		}
		InputStream in = method.getResponseBodyAsStream();
		OutputStream out = resp.getOutputStream();
		if (in != null) {
			copy(in, out);
		}
	}

	private void copy(InputStream in, OutputStream out) throws IOException {
		byte[] buf = new byte[4096];
		while (true) {
			int bytesRead = in.read(buf);
			if (bytesRead == -1) {
				break;
			}
			out.write(buf, 0, bytesRead);
		}
	}

	private HttpMethod getMethod(HttpServletRequest req) throws ServletException {
		try {
			URI uri = new URI("http", null, s3Hostname, 80,
					req.getRequestURI(), req.getQueryString(), null);
			GetMethod method = new GetMethod(uri.toASCIIString());
			List<String> headerNames = Collections.list(req.getHeaderNames());
			for (String headerName : headerNames) {
				if (!headerName.equals("Host")) {
					method.addRequestHeader(headerName, req
							.getHeader(headerName));
				}
			}
			AWSCredentials credentials = new AWSCredentials(awsAccessKey,
					awsSecretKey);
			// Add Authorization header to method
			new PackageRestS3Service(credentials).buildAuthorizationString(method);
			return method;
		} catch (URISyntaxException e) {
			throw new ServletException(e);
		} catch (S3ServiceException e) {
			throw new ServletException(e);
		}
	}
	

}
