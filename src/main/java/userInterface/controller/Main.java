package userInterface.controller;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class Main {
    public static void main(String[] args) {
        WebAppContext context = new WebAppContext();
        context.setDescriptor("/webapp/WEB-INF/web.xml");
        context.setResourceBase("/webapp");
        context.setContextPath("/");
        context.setParentLoaderPriority(true);

        Server server = new Server(8080);

        server.setHandler(context);

        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
