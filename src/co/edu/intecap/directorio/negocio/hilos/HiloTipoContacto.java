/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.intecap.directorio.negocio.hilos;

import co.edu.intecap.directorio.persistencia.conexion.ConexionBD;
import co.edu.intecap.directorio.persistencia.dao.TipoContactoDAO;
import co.edu.intecap.directorio.persistencia.vo.TipoContacto;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author capasitaciones
 */
public class HiloTipoContacto extends Thread {

    private JComboBox<String> cboTipoContacto;
    private List<TipoContacto> listaTipoContactos;

    public HiloTipoContacto(JComboBox<String> cboTipoContacto, List<TipoContacto> listaContactos) {
        this.listaTipoContactos = listaTipoContactos;
        this.cboTipoContacto = cboTipoContacto;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int seleccionado = cboTipoContacto.getSelectedIndex();
                listaTipoContactos = new TipoContactoDAO(ConexionBD.conectar()).consultar();
                cboTipoContacto.removeAll();
                DefaultComboBoxModel<String> modeloCombo = new DefaultComboBoxModel<>();
                modeloCombo.addElement("Seleccione un tipo de contacto");
                for (TipoContacto tc : listaTipoContactos) {
                    modeloCombo.addElement(tc.getNombre());
                }
                cboTipoContacto.setModel(modeloCombo);
                cboTipoContacto.setSelectedIndex(seleccionado);
                Thread.sleep(5000);
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }

}
