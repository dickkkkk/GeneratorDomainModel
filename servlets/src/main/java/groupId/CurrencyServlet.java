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

@WebServlet("/currency")
public class CurrencyServlet extends HttpServlet {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final CRUDService crudService = new CRUDService();

    public CurrencyServlet() {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long playerId = Long.parseLong(request.getParameter("playerId"));
        PrintWriter pw = response.getWriter();
        List<Currency> currencies = crudService.getPlayer(playerId).getCurrencies();
        if (currencies != null) {
            pw.println(objectMapper.writeValueAsString(currencies));
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            pw.println("У игрока нет валюты");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Currency currency  = objectMapper.readValue(request.getInputStream(), Currency.class);
        PrintWriter pw = response.getWriter();
        if (crudService.updateCurrencies(currency)) {
            pw.println(objectMapper.writeValueAsString(crudService.getPlayer(currency.getPlayerId()).getCurrencies()));
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            pw.println("Такой валюты не существует");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Currency currency = objectMapper.readValue(request.getInputStream(), Currency.class);
        PrintWriter pw = response.getWriter();
        boolean flag = crudService.createCurrency(currency);
        if (flag) {
            pw.println(objectMapper.writeValueAsString(crudService.getPlayer(currency.getPlayerId()).getCurrencies()));
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            pw.println("Валюта с таким id уже существует");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        long id = Long.parseLong(req.getParameter("id"));
        if (crudService.deleteCurrency(id)){
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
