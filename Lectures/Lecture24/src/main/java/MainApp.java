import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class MainApp {

    public static void main(String[] args) throws Exception {

        Tomcat tomcat = new Tomcat();

        Connector httpsConnector = new Connector();
        httpsConnector.setPort(8443);
        httpsConnector.setSecure(true);
        httpsConnector.setScheme("https");

        httpsConnector.setProperty("SSLEnabled", "true");
        String baseDir = new File("Lectures/Lecture24").getAbsolutePath();
        httpsConnector.setProperty("keystoreFile", baseDir + "/keystore.p12");
        httpsConnector.setProperty("keystorePass", "12345678");
        httpsConnector.setProperty("keystoreType", "PKCS12");

        tomcat.getService().addConnector(httpsConnector);
        tomcat.setConnector(httpsConnector);

        Context ctx = tomcat.addWebapp("", new File(baseDir + "/src/main/webapp").getAbsolutePath());

        Tomcat.addServlet(ctx, "LoginServlet", new LoginServlet());
        ctx.addServletMappingDecoded("/login", "LoginServlet");

        Tomcat.addServlet(ctx, "CommentServlet", new CommentServlet());
        ctx.addServletMappingDecoded("/comment", "CommentServlet");

        Tomcat.addServlet(ctx, "TransferServlet", new TransferServlet());
        ctx.addServletMappingDecoded("/transfer", "TransferServlet");

        tomcat.start();
        tomcat.getServer().await();
    }
}