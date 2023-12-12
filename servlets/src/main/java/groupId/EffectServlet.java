package groupId;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import groupId.db.CRUDService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/effect")
public class EffectServlet extends HttpServlet {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final CRUDService crudService = new CRUDService();

    public EffectServlet() {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long playerId = Long.parseLong(request.getParameter("playerId"));
        PrintWriter pw = response.getWriter();
        List<Effect> effects = crudService.getPlayer(playerId).getEffects();
        if (effects != null) {
            pw.println(objectMapper.writeValueAsString(effects));
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            pw.println("У игрока нет эффектов");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Effect ceffect  = objectMapper.readValue(request.getInputStream(), Effect.class);
        PrintWriter pw = response.getWriter();
        if (crudService.updateEffects(ceffect)) {
            pw.println(objectMapper.writeValueAsString(crudService.getPlayer(ceffect.getPlayerId()).getEffects()));
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            pw.println("Такого эффекта не существует");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Effect effect = objectMapper.readValue(request.getInputStream(), Effect.class);
        PrintWriter pw = response.getWriter();
        if (crudService.createEffect(effect)) {
            pw.println(objectMapper.writeValueAsString(crudService.getPlayer(effect.getPlayerId()).getEffects()));
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            pw.println("Эффект с таким id уже существует");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        long id = Long.parseLong(req.getParameter("id"));
        if (crudService.deleteEffect(id)){
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
