package servlet;

import dao.BookDao;
import db.DataSource;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Book;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/book")
public class BookServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(BookServlet.class.getName());

    private final BookDao bookDao = new BookDao();

    @Override
    public void init() {
        DataSource.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        String id = req.getParameter("id");

        try (Jedis jedis = new Jedis("redis", 6379)) {
            String cached = jedis.get("book:" + id);

            if (cached != null && !cached.isEmpty()) {
                LOGGER.info("Book id=" + id + " served from REDIS");
                String redisJson = cached.replace("\"source\":\"MYSQL\"", "\"source\":\"REDIS\"");
                out.println(redisJson);
                return;
            }

            Book book = bookDao.findById(Integer.parseInt(id));

            if (book != null) {
                String json = "{\"id\":" + book.getId() + ",\"title\":\"" + book.getTitle() + "\",\"source\":\"MYSQL\"}";
                jedis.set("book:" + id, json);
                LOGGER.info("Book id=" + id + " served from MYSQL");
                out.println(json);
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.println("{\"error\":\"Book not found\"}");
            }

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to fetch book with id: " + id, e);
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.println("{\"error\":\"Internal server error\"}");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        String title = req.getParameter("title");

        try {
            bookDao.insert(title);
            out.println("{\"message\":\"Book inserted\"}");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to insert book", e);
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.println("{\"error\":\"Internal server error\"}");
        }
    }
}