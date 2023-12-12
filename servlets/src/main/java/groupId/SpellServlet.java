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

@WebServlet("/spell")
public class SpellServlet extends HttpServlet {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final CRUDService crudService = new CRUDService();

    public SpellServlet() {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long playerId = Long.parseLong(request.getParameter("playerId"));
        PrintWriter pw = response.getWriter();
        List<Spell> spells = crudService.getPlayer(playerId).getSpells();
        if (spells != null) {
            pw.println(objectMapper.writeValueAsString(spells));
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            pw.println("У игрока нет спеллов");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Spell spell = objectMapper.readValue(request.getInputStream(), Spell.class);
        PrintWriter pw = response.getWriter();
        if (crudService.updateSpells(spell)) {
            pw.println(objectMapper.writeValueAsString(crudService.getPlayer(spell.getPlayerId())));
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            pw.println("Такого спелла не существует");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Spell spell = objectMapper.readValue(request.getInputStream(), Spell.class);
        PrintWriter pw = response.getWriter();
        crudService.createSpell(spell);
        pw.println(objectMapper.writeValueAsString(crudService.getPlayer(spell.getPlayerId())));
        response.setStatus(HttpServletResponse.SC_OK);

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        long id = Long.parseLong(req.getParameter("id"));
        if (crudService.deleteSpell(id)) {
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
