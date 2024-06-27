package servlet;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Element;
import dao.ProjetoDAO;
import dao.RequisitoDAO;
import entidade.Projeto;
import entidade.Requisito;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author felip
 */
public class Relatorio extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Relatorio</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Relatorio at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=relatorio.pdf");

        // Criando um documento PDF com o iText
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            Date data = new Date();
            DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);

            // Adicione as informações do projeto ao PDF
            String projetoIdParam = request.getParameter("projetoId");
            if (projetoIdParam != null && !projetoIdParam.isEmpty()) {
                int projetoId = Integer.parseInt(projetoIdParam);
                Projeto projeto = new ProjetoDAO().consultarId(projetoId);

                // Modifica fonte
                Font nome = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
                Font negrito = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
                Font req = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);

                Paragraph nomeProjeto = new Paragraph(projeto.getNome(), nome);
                nomeProjeto.setAlignment(Element.ALIGN_CENTER);
                document.add(nomeProjeto);

                Paragraph statusParagrafo = new Paragraph();
                statusParagrafo.add(new Chunk("\nStatus: ", negrito));
                statusParagrafo.add(projeto.getStatus());
                document.add(statusParagrafo);

                Paragraph descricaoParagrafo = new Paragraph();
                descricaoParagrafo.add(new Chunk("\nDescrição: ", negrito));
                descricaoParagrafo.add(projeto.getDescricao());
                document.add(descricaoParagrafo);

                Paragraph inicioParagrafo = new Paragraph();
                inicioParagrafo.add(new Chunk("\nData de Início: ", negrito));
                inicioParagrafo.add(projeto.getDataInicio());
                document.add(inicioParagrafo);

                // Adicione informações dos requisitos vinculados ao projeto
                Paragraph reqPara = new Paragraph();
                reqPara.add(new Chunk("\nRequisitos Vinculados:", req));
                document.add(reqPara);
                
                ArrayList<Requisito> requisitos = new RequisitoDAO().consultarPorProjeto(projetoId);
                
                for (Requisito requisito : requisitos) {
                    Paragraph nomeRequisito = new Paragraph();
                    nomeRequisito.add(new Chunk("\nNome do requisito: ", negrito));
                    nomeRequisito.add(requisito.getNome());
                    document.add(nomeRequisito);
                    document.add(new Paragraph("Tipo: " + requisito.getTipo()));
                    document.add(new Paragraph("Descrição: " + requisito.getDescricao()));
                    document.add(new Paragraph("Prioridade: " + requisito.getPrioridade()));
                    document.add(new Paragraph("Complexidade: " + requisito.getComplexidade()));
                    document.add(new Paragraph("Versão: " + requisito.getVersaoAtual()+ "\n\n"));
                }
                
                document.add(new Paragraph("\n\nRelatório gerado " + formatador.format(data) + "."));

            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            document.close();
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
