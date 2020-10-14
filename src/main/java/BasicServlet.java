import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "BasicServlet")
public class BasicServlet extends HttpServlet {

    ChatManager cm = new ChatManager();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        if (name.isEmpty()) {
            name = "anonymous";
        }
        if (request.getParameter("postmessage") != null) {
            request.setAttribute("s", cm.getLog());

            String message = request.getParameter("message");
            if (!message.isEmpty()) {
                cm.setLog(name, message);
            }
        }

        request.setAttribute("n", name);
        request.setAttribute("s", cm.getLog());
        request.getRequestDispatcher("chat.jsp").forward(request, response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String s;

        // Handles getting the messages and filtering by date
        if (request.getParameter("getmessage") != null) {
            String fromString = request.getParameter("from");
            String toString = request.getParameter("to");
            if (!fromString.isEmpty() && !toString.isEmpty()) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd");
                Date from = new Date();
                Date to = new Date();
                try {
                    from = formatter.parse(fromString);
                    to = formatter.parse(toString);
                } catch (ParseException e) {

                }
                s = cm.getLog(from, to);

            } else {
                s = cm.getLog();
            }
            request.setAttribute("s", s);
            request.getRequestDispatcher("chat.jsp").forward(request, response);
        }

        // Handles clearing the messages
        else if (request.getParameter("clear") != null) {
            String fromString = request.getParameter("from");
            String toString = request.getParameter("to");
            if(fromString!=null&& toString!=null) {
                if (!fromString.isEmpty() && !toString.isEmpty()) {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd");
                    Date from = new Date();
                    Date to = new Date();
                    try {
                        from = formatter.parse(fromString);
                        to = formatter.parse(toString);
                    } catch (ParseException e) {

                    }
                    cm.clearLog(from, to);

                } else {
                    cm.clearLog();
                }
            }
            s = cm.getLog();
            request.setAttribute("s", s);
            request.getRequestDispatcher("chat.jsp").forward(request, response);

        } else if (request.getParameter("download") != null) {
            String[][] info = cm.getLogArray();
            if (request.getParameter("format").equals("plain")) {

                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment;filename=temp.txt");

                try {
                    ServletOutputStream out = response.getOutputStream();
                    StringBuilder sb = new StringBuilder();
                    for(int i =0; i< info.length;i++){
                        sb.append("name: "+info[i][0]+"\nmessage: "+info[i][1]+"\ndata: "+info[i][2]+"\n\n");
                    }
                    sb.append(System.lineSeparator());

                    InputStream in =
                            new ByteArrayInputStream(sb.toString().getBytes(StandardCharsets.UTF_8));

                    byte[] outputByte = new byte[sb.length()];
                    //copy binary contect to output stream
                    while (in.read(outputByte, 0, sb.length()) != -1) {
                        out.write(outputByte, 0, sb.length());
                    }
                    in.close();
                    out.flush();
                    out.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (request.getParameter("format").equals("xml")) {
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder;

                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment;filename=temp.xml");

                try {
                    dBuilder = dbFactory.newDocumentBuilder();
                    Document doc = dBuilder.newDocument();
                    // add root element
                    Element rootElem = doc.createElementNS("https://www.soen387.com/assignment1", "Messages");
                    // append root element to document
                    doc.appendChild(rootElem);


                    for (int i = 0; i < info.length; i++) {
                        Element message = doc.createElement("Message");
                        Element nameNode = doc.createElement("Name");
                        nameNode.appendChild(doc.createTextNode(info[i][0]));
                        message.appendChild(nameNode);
                        Element messageNode = doc.createElement("Message");
                        messageNode.appendChild(doc.createTextNode(info[i][1]));
                        message.appendChild(messageNode);
                        Element dateNode = doc.createElement("Date");
                        dateNode.appendChild(doc.createTextNode(info[i][2]));
                        message.appendChild(dateNode);
                        rootElem.appendChild(message);
                    }

                    // create in xml format
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    // for formatting
                    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                    transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "yes");
                    transformer.setOutputProperty("{https://xml.apache.org/xslt}indent-amount", "2");
                    final DOMSource source = new DOMSource(doc);
                    // output
                    ServletOutputStream out = response.getOutputStream();
                    transformer.transform(source, new StreamResult(out));
                    out.flush();
                    out.close();

                } catch (ParserConfigurationException | TransformerException e) {
                    e.printStackTrace();
                }
            }
        }

        else if (request.getParameter("changetheme") != null){
            request.getRequestDispatcher("chat.jsp").forward(request, response);
        }
        // Regular GET request (landing page)
        else {
            s = cm.getLog();
            request.setAttribute("s", s);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

    }


}
