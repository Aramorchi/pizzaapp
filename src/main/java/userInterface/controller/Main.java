package userInterface.controller;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class Main {
    public static void main(String[] args) {
        Server server = new Server(8080);
        
        WebAppContext context = new WebAppContext();
        context.setDescriptor("src/main/java/webapp/WEB-INF/web.xml");
        context.setResourceBase("src/main/java/webapp");
        context.setContextPath("/");
        context.setParentLoaderPriority(true);

        server.setHandler(context);

        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
