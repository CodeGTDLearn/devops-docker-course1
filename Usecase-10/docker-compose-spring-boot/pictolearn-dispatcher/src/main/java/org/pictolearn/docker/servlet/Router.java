package org.pictolearn.docker.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.ProtocolException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//URL PRECEDENTE AO ENDPOINT
@WebServlet(urlPatterns = "/xxx/*", loadOnStartup = 1)
public class Router extends HttpServlet {

    private static final long serialVersionUID = 2787920473586060865L;

    private static final Logger logger = LoggerFactory.getLogger(Router.class);

    //PORTA DO SERVIDOR TARGET
    private final String port = "8080";

    //URL PRECEDENTE AO ENDPOINT
    private final String preEndpoint = "/xxx/";

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        logger.debug("\n\n");
        logger.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        logger.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        logger.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        logger.debug("\n");

        String uri = request.getRequestURI().toString();

        String path = request.getRequestURI().substring(request.getContextPath().length());

        logger.debug("1.1) GET URI: {}", uri);
        logger.debug("1.2) GET Request: {}", path);

        String endpoint = path.substring(path.indexOf(preEndpoint) +
                preEndpoint.length(), path.length());


        logger.debug("2) GET endpoint: {}", endpoint);


        if (StringUtils.isEmpty(endpoint)) {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("Invalid GET CALL");
            out.close();
            return;
        }

        String ipAddress = getIpAddress(response, "web");


        logger.debug("3) GET IpAddress: {}", ipAddress);

        if (ipAddress == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("No Hosts Found");
            return;
        }


        String url = "http://" + ipAddress + ":" + port + "/" + endpoint;


        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        response.addHeader("WEB-HOST-GET-SCALE", ipAddress);
        sendResponse(response, con);

        logger.debug("4) GET url montada: {}", url);

        logger.debug("\n");
        logger.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        logger.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        logger.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        logger.debug("\n\n");
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        logger.debug("\n\n");
        logger.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        logger.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        logger.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        logger.debug("\n");


        String uri = request.getRequestURI().toString();

        String path = request.getRequestURI().substring(request.getContextPath().length());

        logger.debug("1.1) POST URI: {}", uri);
        logger.debug("1.2) POST Request: {}", path);

        String endpoint = path.substring(path.indexOf(preEndpoint) +
                preEndpoint.length(), path.length());


        logger.debug("2) POST endpoint: {}", endpoint);


        if (StringUtils.isEmpty(endpoint)) {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("Invalid POST CALL empty URI");
            out.close();
            return;
        }
        String ipAddress = getIpAddress(response, "web");

        logger.debug("3) POST IpAddress: {}", ipAddress);

        if (ipAddress == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("No Hosts Found");
            return;
        }

        response.addHeader("WEB-HOST-POST-SCALE", ipAddress);
        String url = "http://" + ipAddress + ":" + port + "/" + endpoint;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");

        logger.debug("4) POST url montada: {}", url);


        Enumeration<String> headerNames = request.getHeaderNames();
        Set<String> headers = new HashSet<>();
        while (headerNames.hasMoreElements()) {
            headers.add(headerNames.nextElement());
        }


        if (!CollectionUtils.isEmpty(headers)) {
            int contador = 0;
            for (String header : headers) {
                String value = request.getHeader(header);
                contador++;
                logger.debug("5.{}) POST Add header: {} | value: {} ", new Object[]{contador, header, value});
                con.setRequestProperty(header, value);
            }
        }

        con.setDoOutput(true);
        String body = getBody(request);

        logger.debug("6) POST Body: {}", body);

        logger.debug("\n");
        logger.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        logger.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        logger.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        logger.debug("\n\n");

        OutputStream wr = con.getOutputStream();
        wr.write(body.getBytes("UTF-8"));
        wr.flush();
        wr.close();
        sendResponse(response, con);
    }

    public String getBody(HttpServletRequest request) throws IOException {

        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }

        body = stringBuilder.toString();
        return body;
    }


    private String getIpAddress(HttpServletResponse response, String webServer) throws UnknownHostException, IOException {

        List<String> ipAddr = new ArrayList<>();

        for (InetAddress addr : InetAddress.getAllByName(webServer)) {
            logger.debug("Hostnames: {}", addr.getHostAddress());
            ipAddr.add(addr.getHostAddress());
        }

        int size = ipAddr.size();

        if (size == 0) {
            logger.error("Size less than 1");
            return null;
        }

        logger.debug("Total hosts: {} ", size);

        int random = 0;
        if (size == 1) {
            random = 0;
        } else if (size > 1) {
            random = ThreadLocalRandom.current().nextInt(0, ipAddr.size() - 1);
        }

        logger.debug("Random: {} ", random);
        String ipAddrStr = ipAddr.get(random);
        logger.debug("Returned IP addr: {} ", ipAddrStr);
        return ipAddrStr;
    }

    private void sendResponse(HttpServletResponse response, HttpURLConnection con)
            throws ProtocolException, IOException {

        int responseCode = con.getResponseCode();

        if (responseCode == 200) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            Map<String, List<String>> headerFields = con.getHeaderFields();
            if (!headerFields.isEmpty()) {
                int contador = 0;
                for (String headerField : headerFields.keySet()) {
                    contador++;
                    Object value = headerFields.get(headerField);
                    //logger.debug("Add header RESP: {}, header value : {} ", new Object[]{headerField, value});
                    logger.debug("3.{}) Add header RESP: {} | header value: {}", new Object[]{contador, headerField, value});
                    response.addHeader(headerField, value.toString());
                }
            }

            String inputLine;
            StringBuffer stringOutput = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                stringOutput.append(inputLine);
            }
            logger.debug("Received response : {}  ", stringOutput);
            PrintWriter out = response.getWriter();
            out.println(stringOutput.toString());
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Internal Server Error");
        }

    }
}