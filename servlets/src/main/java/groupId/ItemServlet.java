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

@WebServlet("/item")
public class ItemServlet extends HttpServlet {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final CRUDService crudService = new CRUDService();

    public ItemServlet() {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long playerId = Long.parseLong(request.getParameter("playerId"));
        PrintWriter pw = response.getWriter();
        List<Item> items = crudService.getPlayer(playerId).getItems();
        if (items != null) {
            pw.println(objectMapper.writeValueAsString(items));
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            pw.println("Такого предмета не существует");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Item items = objectMapper.readValue(request.getInputStream(), Item.class);
        PrintWriter pw = response.getWriter();
        if (crudService.updateItems(items)) {
            pw.println(objectMapper.writeValueAsString(crudService.getPlayer(items.getPlayerId()).getItems()));
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            pw.println("Такого предмета не существует");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Item item = objectMapper.readValue(request.getInputStream(), Item.class);
        PrintWriter pw = response.getWriter();
        if (crudService.createItem(item)) {
            pw.println(objectMapper.writeValueAsString(crudService.getPlayer(item.getPlayerId()).getItems()));
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            pw.println("Такой предмет уже существует");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        long id = Long.parseLong(req.getParameter("id"));
        if (crudService.deleteItem(id)){
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
