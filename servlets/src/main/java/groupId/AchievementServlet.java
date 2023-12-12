package groupId;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import groupId.db.CRUDService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/achievement")
public class AchievementServlet extends HttpServlet {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final CRUDService crudService = new CRUDService();

    public AchievementServlet() {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long playerId = Long.parseLong(request.getParameter("playerId"));
        PrintWriter pw = response.getWriter();
        List<Achievement> achievements = crudService.getPlayer(playerId).getAchievements();
        if (achievements != null) {
            pw.println(objectMapper.writeValueAsString(achievements));
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            pw.println("Такой ачивки не существует");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Achievement achievement = objectMapper.readValue(request.getInputStream(), Achievement.class);
        PrintWriter pw = response.getWriter();
        if (crudService.updateAchievements(achievement)) {
            pw.println(objectMapper.writeValueAsString(crudService.getPlayer(achievement.getPlayerId()).getAchievements()));
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            pw.println("Такой ачивки не существует");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Achievement achievement = objectMapper.readValue(request.getInputStream(), Achievement.class);
        PrintWriter pw = response.getWriter();
        boolean flag = crudService.createAchievement(achievement);
        if (flag) {
            pw.println(objectMapper.writeValueAsString(crudService.getPlayer(achievement.getPlayerId()).getAchievements()));
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            pw.println("Такая ачивка уже существует");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        long id = Long.parseLong(req.getParameter("id"));
        if (crudService.deleteAchievement(id)) {
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
