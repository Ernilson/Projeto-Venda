/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author t69779848134
 */
public class TableModel extends AbstractTableModel{
    private int colunas, linhas;
    private ResultSet resultset;
    private ResultSetMetaData rsMetaData;
    
    public TableModel(ResultSet resultset) throws SQLException{
        rsMetaData = resultset.getMetaData();
        //select * from ultma coluna;
        this.resultset = resultset;
        resultset.last(); // vai para o  ultmo regeistro;
        linhas = resultset.getRow(); //pega o numero da linha em que se encontra
        
        //aciona alteração de estrutura no JTable
        fireTableStructureChanged();
                
    }

    @Override
    public int getRowCount() {
       return linhas;
    }

    @Override
    public int getColumnCount() {
        colunas = 0;
        try {
            colunas = rsMetaData.getColumnCount();
        } catch (SQLException erro) {
            System.out.println("Erro ao Ler o numero de Colunas:" + erro);
        }
        return colunas;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            resultset.absolute(rowIndex+1);
            return resultset.getObject(columnIndex+1);
        } catch (SQLException erro) {
            System.out.println("Erro"+erro);
        }
        return null;
    }
    
}
