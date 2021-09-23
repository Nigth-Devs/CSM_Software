/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import dao_alternativo.MateriaJpaAlternativo;
import dao.PensumJpaController;
import dao.ProgramaJpaController;
import dto.Materia;
import dto.Pensum;
import dto.PensumPK;
import dto.Programa;
import dto.Usuario;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import util.Conexion;
import util.MyConnection;

/**
 *
 * @author Dunke
 */
public class AdministrarPensum {

    private String realPathServer;

    public AdministrarPensum(String realPathServer) {
        this.realPathServer = realPathServer;
    }

    public AdministrarPensum() {
    }

    public Pensum registrar(Programa programa, InputStream pensumFile) throws IOException, Exception {
        EntityManagerFactory em = Conexion.getConexion().getBd();
        PensumJpaController pjpa = new PensumJpaController(em);
        
        List<Pensum> pensums = programa.getPensumList();
        
        LectorPensum l = new LectorPensum();
        String path = cargarPensum(pensumFile, programa);
        l.parsePDFDocument(path);
        
        Pensum pensum = new Pensum(new PensumPK(pensums.size()+1, programa.getCodigo()));
        pensum.setPrograma(programa);
        pjpa.create(pensum);
        
        List<Materia> materias = l.getMaterias(pensum.getPensumPK().getCodigo());
        pensum.setMateriaList(materias);
        new MateriaJpaAlternativo(MyConnection.getConnection()).create(pensum);
        
        new File(path).deleteOnExit();
        
        return pensum;
    }

    private String cargarPensum(InputStream pensumeFile, Programa programa) throws IOException {
        File folder = new File(this.realPathServer);
        folder = new File(folder.getParentFile().getParentFile().getAbsolutePath() + "/temp");
        InputStream fileContent = pensumeFile;
        File file = File.createTempFile("pensum-" + programa.getCodigo(), ".pdf", folder);
        Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        return file.getAbsolutePath();
    }

    public Pensum obtenerPensum(int codigo, int programaCodigo) {
        Conexion con = Conexion.getConexion();
        PensumJpaController daoPensum = new PensumJpaController(con.getBd());
        Pensum pensum = daoPensum.findPensum(new PensumPK(codigo, programaCodigo));
        return pensum;
    }

    public Object[][] getMetaInfoPensums(List<Pensum> pensums){
        Object[][] data = new Object[pensums.size()][4];
        int i=0;
        for(Pensum pensum: pensums){
            int creditosMaterias[] = this.creditosMateriasPensum(pensum);
            data[i][0] = pensum.getPensumPK().getProgramaCodigo()+" - "+pensum.getPensumPK().getCodigo();
            data[i][1] = creditosMaterias[1];
            data[i][2] = creditosMaterias[0];
            data[i++][3] = pensum.getPensumPK().getCodigo();
        }
        
        return data;
    }
    
    public int[] creditosMateriasPensum(Pensum pensum) {
        List<dto.Materia> materias = pensum.getMateriaList();
        int materiasXcreditos[] = new int[2];
        int creditos = 0;
        int cantMaterias = 0;
        for (Materia m : materias) {
            cantMaterias++;
            creditos += m.getCreditos();
        }
        materiasXcreditos[0] = cantMaterias;
        materiasXcreditos[1] = creditos;

        return materiasXcreditos;
    }

    public Pensum getLastPensum(Usuario usuario) {
        AdministrarPrograma adminPrograma = new AdministrarPrograma();
        Pensum pensumRes = null;
        Programa programa = adminPrograma.refreshPrograma(usuario.getDocente().getProgramaCodigo());
        for (Pensum pensum : programa.getPensumList()) {
            if (pensumRes != null && pensumRes.getPensumPK().getCodigo() < pensum.getPensumPK().getCodigo()) {
                pensumRes = pensum;
            } else {
                pensumRes = pensum;
            }
        }
        return pensumRes;
    }

    public List<Materia>[] cargarMateriasPorSemestre(Pensum pensum) {
        List<Materia> materiasSemestre[] = new List[10];
        for (Materia m : pensum.getMateriaList()) {
            int semestre = m.getSemestre() - 1;
            if (materiasSemestre[semestre] == null) {
                materiasSemestre[semestre] = new ArrayList<>();
            }
            materiasSemestre[semestre].add(m);
        }
        return materiasSemestre;
    }
}
