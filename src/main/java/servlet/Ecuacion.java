package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/solucion")
public class Ecuacion extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        // Obtener coeficientes de la ecuación cuadrática
        double a = Double.parseDouble(req.getParameter("a"));
        double b = Double.parseDouble(req.getParameter("b"));
        double c = Double.parseDouble(req.getParameter("c"));

        // Calcular discriminante
        double discriminante = b * b - 4 * a * c;
        String formato1 = "";
        String formato2 = "";

        // Crear salida HTML
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<title>Resultado de la Ecuación Cuadrática</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Resultado de la Ecuación Cuadrática</h1>");

        // Evaluar soluciones según el discriminante
        if (discriminante > 0) {
            double raiz1 = (-b + Math.sqrt(discriminante)) / (2 * a);
            double raiz2 = (-b - Math.sqrt(discriminante)) / (2 * a);
            formato1 = "Las raíces son reales y distintas: raíz 1 = " + raiz1 + ", raíz 2 = " + raiz2;
            formato2 = String.format("Raíces en formato decimal: %.2f y %.2f", raiz1, raiz2);
        } else if (discriminante == 0) {
            double raizUnica = -b / (2 * a);
            formato1 = "La raíz es real y única: raíz = " + raizUnica;
            formato2 = String.format("Raíz en formato decimal: %.2f", raizUnica);
        } else {
            double parteReal = -b / (2 * a);
            double parteImaginaria = Math.sqrt(-discriminante) / (2 * a);
            formato1 = "Las raíces son complejas: raíz 1 = " + parteReal + " + " + parteImaginaria + "i, raíz 2 = " + parteReal + " - " + parteImaginaria + "i";
            formato2 = String.format("Raíces en formato complejo: %.2f + %.2fi y %.2f - %.2fi", parteReal, parteImaginaria, parteReal, parteImaginaria);
        }

        // Imprimir resultados en dos formatos
        out.println("<p>" + formato1 + "</p>");
        out.println("<p>" + formato2 + "</p>");

        // Agregar enlace para regresar
        out.println("<p><a href=\"/EcuacionCuadratica/index.html\">Calcular otra ecuación</a></p>");

        out.println("</body>");
        out.println("</html>");
    }
}
