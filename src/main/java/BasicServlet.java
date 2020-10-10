import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            s = cm.getLog();
            request.setAttribute("s", s);
            request.getRequestDispatcher("chat.jsp").forward(request, response);

        } else if (request.getParameter("download") != null) {

            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=temp.txt");

            try {
                ServletOutputStream out = response.getOutputStream();
                StringBuilder sb = new StringBuilder(cm.getLog());
                sb.append(System.lineSeparator());
                String logS = sb.toString();

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
        }

        // Regular GET request (landing page)
        else {
            s = cm.getLog();
            request.setAttribute("s", s);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

    }


}
