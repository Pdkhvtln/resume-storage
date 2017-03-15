package com.urise.webapp.web;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.ListStorage;
import com.urise.webapp.storage.Storage;
import com.urise.webapp.utils.Config;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by andrew on 13.03.17.
 */

public class resumeServlet extends HttpServlet {
    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.getInstance().getStorage();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "text/html; charset=UTF-8");
        String titlePage="Заголовок";
        Writer writer = response.getWriter();
        writer.write(
                "<html>\n" +
                        "<head>\n" +
                        "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                        "<link rel=\"stylesheet\" href=\"css/style.css\">\n" +
                        "<title>" + titlePage + "</title>" +
                        "</head>\n" +
                        "<body>\n" +
                        "<section>\n" +
                        "<table border =\"1\" cellpadding=\"8\" cellspasing=\"0\">\n" +
                        "<tr>\n" +
                        "<th>Имя</th>\n" +
                        "<th>Email</th>\n" +
                        "</tr>\n");
        for (Resume resume : storage.getAllSorted()) {
            writer.write(
                    "<tr>\n" +
                            "<td><a href=\"resume?uuid=" + resume.getUuid() + "\">" + resume.getFullName() + "</a></td>\n" +
                            "<td>"+resume.getContact(ContactType.MAIL)+"</td>\n"+
                            "</tr>\n");
        }
writer.write("</table>\n"+"</section\n>"+"</body>\n"+"</html>\n");

        String parameterValue = request.getParameter("uuid");

    }
}
