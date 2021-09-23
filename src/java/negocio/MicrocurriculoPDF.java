/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dao.AreaFormacionJpaController;
import dao.TipoMateriaJpaController;
import dto.AreaFormacion;
import dto.ContenidoUnidad;
import dto.Encabezado;
import dto.Materia;
import dto.Microcurriculo;
import dto.PrerrequisitoMateria;
import dto.SeccionMicrocurriculo;
import dto.Tabla;
import dto.TablaSeccion;
import dto.TipoMateria;
import dto.Unidad;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import org.apache.commons.io.IOUtils;
import util.Conexion;

/**
 *
 * @author Dunke
 */
public class MicrocurriculoPDF {

    private BaseFont bf;
    private Microcurriculo m;
    private List<AreaFormacion> areas;
    private List<TipoMateria> ta;
    private Document doc;
    private String path;
    private File file;

    public MicrocurriculoPDF(String path, Microcurriculo m) throws FileNotFoundException, DocumentException, BadElementException, IOException {
        EntityManagerFactory em = Conexion.getConexion().getBd();
        this.m = m;
        this.path = path;
        this.path = new File(this.path).getParentFile().getParentFile().getAbsolutePath();
        this.ta = new TipoMateriaJpaController(em).findTipoMateriaEntities();
        this.areas = new AreaFormacionJpaController(em).findAreaFormacionEntities();
        this.bf = BaseFont.createFont(this.path + "\\fonts\\Roboto-Medium.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        TableHeader th = new TableHeader(this.getTablaEnca());
        this.doc = new Document(PageSize.A4, 36, 36, 20 + th.getTableHeight(), 36);
        this.file = new File(this.path + "\\temp\\MICROCURRICULO_" + m.getMateria().getNombre() + "_" + m.getMateria().getMateriaPK().getCodigo()+ ".pdf");
        PdfWriter.getInstance(this.doc, new FileOutputStream(this.file.getAbsolutePath())).setPageEvent(th);
    }

    public InputStream createPDF() throws DocumentException, BadElementException, IOException {
        this.doc.open();
        this.doc.add(this.getParapgraph("Microcurriculo", 10, Paragraph.ALIGN_CENTER));
        this.createBlank();
        this.doc.add(this.getTablaInfo());
        this.createBlank();
        for (SeccionMicrocurriculo sm : this.m.getSeccionMicrocurriculoList()) {
            this.doc.add(getParapgraph(sm.getSeccionId().getNombre(), 11, Paragraph.ALIGN_CENTER));
            this.createBlank();
            if (sm.getSeccionId().getTipoSeccionId().getId() == 2) {
                this.doc.add(this.getTabla(sm));
            } else {
                this.doc.add(getParapgraph(sm.getContenidoList().get(0).getTexto(), 9, Paragraph.ALIGN_JUSTIFIED));
            }
            this.createBlank();
        }
        this.doc.close();
        InputStream is = new FileInputStream(this.file);
        return is;
    }
    
    private PdfPTable getTabla(SeccionMicrocurriculo sm) throws DocumentException {
        PdfPTable tab = new PdfPTable(sm.getTablaSeccion().getTablaId().getEncabezadoList().size());
        tab.setWidthPercentage(100);
        this.configEnca(tab, sm.getTablaSeccion().getTablaId());
        if (sm.getSeccionId().getId() == 1) {
            tab.setWidths(new int[]{1, 3, 1, 1, 1});
        }
        int i=0;
        for(Unidad unidad: this.m.getUnidadList()){
            if (sm.getSeccionId().getId() == 1) {
                if(i==0)tab.setWidths(new int[]{1, 3, 1, 1, 1});
                tab.addCell(getParapgraph(""+unidad.getNum(), 9, Paragraph.ALIGN_CENTER));
                tab.addCell(getParapgraph(unidad.getNombre(), 9, Paragraph.ALIGN_CENTER));
                tab.addCell(getParapgraph(""+unidad.getHorasPresencial(), 9, Paragraph.ALIGN_CENTER));
                tab.addCell(getParapgraph(""+unidad.getHorasIndependiente(), 9, Paragraph.ALIGN_CENTER));
                tab.addCell(getParapgraph(""+(unidad.getHorasIndependiente()+unidad.getHorasIndependiente()), 9, Paragraph.ALIGN_CENTER));
            }else{
                for(ContenidoUnidad content: unidad.getContenidoUnidadList()){
                    tab.addCell(getParapgraph(""+unidad.getNum(), 9, Paragraph.ALIGN_CENTER));
                    tab.addCell(getParapgraph(content.getContenido(), 9, Paragraph.ALIGN_CENTER));
                    tab.addCell(getParapgraph(content.getTrabajoPresencial(), 9, Paragraph.ALIGN_CENTER));
                    tab.addCell(getParapgraph(content.getTrabajoIndependiente(), 9, Paragraph.ALIGN_CENTER));
                }
            }
        }
        return tab;
    }

    private void configEnca(PdfPTable tab, Tabla tm) {
        int i = 0;
        for (Encabezado et : tm.getEncabezadoList()) {
            if (i++ == 2 && tm.getId() == 1) {
                tab.getDefaultCell().setColspan(2);
                tab.addCell(this.getParapgraph("Dedicacion del estudiante (Horas)", 9, Paragraph.ALIGN_CENTER));
                tab.getDefaultCell().setColspan(1);
                tab.getDefaultCell().setRowspan(2);
                tab.addCell(this.getParapgraph(et.getEncabezado(), 9, Paragraph.ALIGN_CENTER));
                tab.getDefaultCell().setRowspan(1);

                tab.addCell(this.getParapgraph("a)Trabajo Presencial", 9, Paragraph.ALIGN_CENTER));
                tab.addCell(this.getParapgraph("b)Trabajo Independiente", 9, Paragraph.ALIGN_CENTER));
                break;
            } else {
                if (tm.getId() == 1) {
                    tab.getDefaultCell().setRowspan(2);
                }
                tab.addCell(this.getParapgraph(et.getEncabezado(), 9, Paragraph.ALIGN_CENTER));
                tab.getDefaultCell().setRowspan(1);
            }
        }
    }

    public PdfPTable getTablaEnca() throws DocumentException, BadElementException, IOException {
        PdfPTable tab = new PdfPTable(3);
        tab.setWidths(new int[]{1, 4, 1});
        tab.getDefaultCell().setRowspan(3);
        tab.addCell(Image.getInstance(this.path + "\\imgs\\logoufps.png"));
        tab.getDefaultCell().setRowspan(2);
        PdfPCell c = new PdfPCell(this.getParapgraph(
                "UNIVERSIDAD FRANCISCO DE PAULA SANTANDER "
                + "FACULTAD DE " + this.m.getMateria().getPensum().getPrograma().getDepartamentoId().getFacultadId().getNombre().toUpperCase() + " "
                + "PROGRAMA " + this.m.getMateria().getPensum().getPrograma().getNombre().toUpperCase(),
                11, Paragraph.ALIGN_CENTER));
        c.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
        c.setVerticalAlignment(Paragraph.ALIGN_MIDDLE);
        c.setFixedHeight(70);
        tab.addCell(c);
        tab.getDefaultCell().setRowspan(3);
        tab.addCell(Image.getInstance(this.m.getMateria().getPensum().getPrograma().getImagen()));
        tab.getDefaultCell().setRowspan(1);
        PdfPCell f = new PdfPCell(this.getParapgraph("Formato Syllabus", 11, Paragraph.ALIGN_CENTER));
        f.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
        f.setVerticalAlignment(Paragraph.ALIGN_BOTTOM);
        tab.addCell(f);
        return tab;
    }

    public PdfPTable getTablaInfo() throws DocumentException, BadElementException, IOException {
        Materia ma = this.m.getMateria();
        PdfPTable tab = new PdfPTable(5);
        tab.setWidths(new int[]{1, 1, 1, 1, 1});
        tab.addCell(this.getParapgraph("Asignatura", 10, Paragraph.ALIGN_LEFT));
        tab.getDefaultCell().setColspan(4);
        tab.addCell(this.getParapgraph(ma.getNombre(), 10, Paragraph.ALIGN_LEFT));
        tab.getDefaultCell().setColspan(1);
        tab.addCell(this.getParapgraph("Código", 10, Paragraph.ALIGN_LEFT));
        tab.getDefaultCell().setColspan(4);
        tab.addCell(this.getParapgraph("" + ma.getMateriaPK().getCodigo(), 10, Paragraph.ALIGN_LEFT));
        tab.getDefaultCell().setColspan(1);
        tab.addCell(this.getParapgraph("Área de formación:", 10, Paragraph.ALIGN_LEFT));
        for (AreaFormacion a : this.areas) {
            Phrase p = new Phrase(a.getNombre());
            if (this.m.getAreaDeFormacionId().getId().equals(a.getId())) {
                p.add(this.getParapgraph(": X", 10, Paragraph.ALIGN_LEFT));
            }
            tab.addCell(p);
        }
        tab.addCell(this.getParapgraph("Tipo de asignatura:", 10, Paragraph.ALIGN_LEFT));
        tab.getDefaultCell().setColspan(2);
        for (TipoMateria t : this.ta) {
            Phrase p = new Phrase(t.getTipo());
            if (this.m.getAreaDeFormacionId().getId().equals(t.getId())) {
                p.add(this.getParapgraph(": X", 10, Paragraph.ALIGN_LEFT));
            }
            tab.addCell(p);
        }
        tab.getDefaultCell().setColspan(1);
        tab.addCell(this.getParapgraph("Número de Créditos", 10, Paragraph.ALIGN_LEFT));
        tab.getDefaultCell().setColspan(4);
        tab.addCell(this.getParapgraph("" + ma.getCreditos(), 10, Paragraph.ALIGN_LEFT));
        tab.getDefaultCell().setColspan(1);
        tab.addCell(this.getParapgraph("Prerrequisitos", 10, Paragraph.ALIGN_LEFT));
        tab.getDefaultCell().setColspan(4);
        String pr = "";
        for (PrerrequisitoMateria pm : ma.getPrerrequisitoMateriaList()) {
            pr += pm.getMateria1().getMateriaPK().getCodigo()+ " - " + pm.getMateria1().getNombre();
        }
        tab.addCell(this.getParapgraph(pr, 10, Paragraph.ALIGN_LEFT));
        tab.setWidthPercentage(100);
        return tab;
    }

    public Paragraph getParapgraph(String e, int tam, int orien) {
        Paragraph p = new Paragraph(e, new Font(this.bf, tam));
        p.setAlignment(orien);
        return p;
    }

    public void createBlank() throws DocumentException {
        this.doc.add(getParapgraph("\n", 9, Paragraph.ALIGN_CENTER));
    }

    public File getFile() {
        return file;
    }
}
