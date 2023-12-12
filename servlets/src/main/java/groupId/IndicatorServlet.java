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

@WebServlet("/indicator")
public class IndicatorServlet extends HttpServlet {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final CRUDService crudService = new CRUDService();

    public IndicatorServlet() {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long playerId = Long.parseLong(request.getParameter("playerId"));
        PrintWriter pw = response.getWriter();
        Indicators indicator = crudService.getPlayer(playerId).getIndicators();
        if (indicator != null) {
            pw.println(objectMapper.writeValueAsString(indicator));
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            pw.println("Заданный индикатор не найден");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Indicators indicators = objectMapper.readValue(request.getInputStream(), Indicators.class);
        PrintWriter pw = response.getWriter();
        if (crudService.updateIndicators(indicators)) {
            pw.println(objectMapper.writeValueAsString(crudService.getPlayer(indicators.getPlayerId()).getIndicators()));
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            pw.println("Такого игрока нет в бд");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Indicators indicators = objectMapper.readValue(request.getInputStream(), Indicators.class);
        PrintWriter pw = response.getWriter();
        if (crudService.createIndicators(indicators)) {
            pw.println(objectMapper.writeValueAsString(crudService.getPlayer(indicators.getPlayerId()).getIndicators()));
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            pw.println("Такой playerId уже есть в БД");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        long playerId = Long.parseLong(req.getParameter("playerId"));
        if (crudService.deleteIndicator(playerId)){
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
