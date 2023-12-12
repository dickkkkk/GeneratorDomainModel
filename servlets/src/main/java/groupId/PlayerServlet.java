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

@WebServlet("/player")
public class PlayerServlet extends HttpServlet {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final CRUDService crudService = new CRUDService();

    public PlayerServlet() {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long playerId = Long.parseLong(request.getParameter("playerId"));
        PrintWriter pw = response.getWriter();
        Player player = crudService.getPlayer(playerId);
        if (player != null) {
            pw.println(objectMapper.writeValueAsString(player));
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            pw.println("Заданный игрок не найден");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Player player = objectMapper.readValue(request.getInputStream(), Player.class);
        PrintWriter pw = response.getWriter();
        if (crudService.updateNamePlayers(player.getPlayerId(), player.getName())) {
            pw.println(objectMapper.writeValueAsString(crudService.getPlayer(player.getPlayerId())));
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            pw.println("Такого игрока нет в бд");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        Player player = objectMapper.readValue(request.getInputStream(), Player.class);
        PrintWriter pw = resp.getWriter();
        if (crudService.savePlayer(player)) {
            pw.println(objectMapper.writeValueAsString(crudService.getPlayer(player.getPlayerId())));
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            pw.println("Такой playerId уже есть в БД");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        long playerId = Long.parseLong(req.getParameter("playerId"));
        if (crudService.deletePlayer(playerId)) {
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}