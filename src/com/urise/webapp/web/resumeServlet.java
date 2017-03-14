package com.urise.webapp.web;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.ListStorage;
import com.urise.webapp.storage.Storage;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by andrew on 13.03.17.
 */

public class resumeServlet extends HttpServlet {
    protected Storage storage;
    protected static final String UUID_1 = "uuid1";
    protected static final String FULL_NAME_1 = "First Man";

    protected Resume RESUME_1 = new Resume(UUID_1, FULL_NAME_1);

    protected static final String UUID_2 = "uuid2";
    protected static final String FULL_NAME_2 = "Second Man";
    protected Resume RESUME_2 = new Resume(UUID_2, FULL_NAME_2);

    protected static final String UUID_3 = "uuid3";
    protected static final String FULL_NAME_3 = "Third Man";
    protected Resume RESUME_3 = new Resume(UUID_3, FULL_NAME_3);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        if (storage == null) {
            storage = new ListStorage();
            storage.save(RESUME_1);
            storage.save(RESUME_2);
            storage.save(RESUME_3);
        }
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "text/html; charset=UTF-8");
        String parameterValue = request.getParameter("uuid");

        if (parameterValue != null) {
            try {
                Resume resume = storage.get(parameterValue);
                if (resume != null) {
                    beginPage(response, new String("Запрашиваемое резюме с uuid: "));// + resume.getUuid()));

                    response.getWriter().print("<tr>");
                    response.getWriter().print("<td>" + resume.getUuid() + "</td>");
                    response.getWriter().print("<td>" + resume.getFullName() + "</td>");
                    response.getWriter().print("</tr>");

                    finishPage(response);
                }
            } catch (NotExistStorageException e) {
                response.getWriter().print("Запрашиваемого резюме нет в БД.");
            }
        } else {//parameterValue == null

            beginPage(response, new String("Таблица резюме"));
            for (Resume item : storage.getAllSorted()) {
                response.getWriter().print("<tr>");
                response.getWriter().print("<td>" + item.getUuid() + "</td>");
                response.getWriter().print("<td>" + item.getFullName() + "</td>");
                response.getWriter().print("</tr>");
            }
            finishPage(response);
        }
    }

    private void beginPage(HttpServletResponse response, String titlePage) throws IOException {
        response.getWriter().print("<html>");
        response.getWriter().print("<head>");
        response.getWriter().print("<title>" + titlePage + "</title>");
        response.getWriter().print("</head>");
        response.getWriter().print("<body>");
        response.getWriter().print("<table border = 2>");
        response.getWriter().print("<tr>");
        response.getWriter().print("<td>UUID_ID</td>");
        response.getWriter().print("<td>FULL_NAME</td>");
        response.getWriter().print("</tr>");
    }

    private void finishPage(HttpServletResponse response) throws IOException {
        response.getWriter().print("<table>");
        response.getWriter().print("</table>");
        response.getWriter().print("</body>");
        response.getWriter().print("</html>");
    }
}
