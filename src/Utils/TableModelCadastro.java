/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ernil
 */
public class TableModelCadastro extends AbstractTableModel {

    private int numLinhas;
    private ArrayList<String[]> resultSetCadastro;
    private ResultSetMetaData rsMetaData;
    private static final String[] nomesColunas = {
        "nome, endereço, fone, data_nasc, cpf, status, perfil"
    };
    
    public TableModelCadastro(ResultSet resultset) throws SQLException{
        setResult(resultset);
    }

    public void setResult(ResultSet resultset) throws SQLException {
        resultSetCadastro = new ArrayList<String[]>();
        while (resultset.next()) {
            String[] linha = {resultset.getString("nome"),
                resultset.getString("endereço"),
                resultset.getString("fone"),
                resultset.getString("data_nasc"),
                resultset.getString("cpf"),
                resultset.getString("estatus"),
                resultset.getString("perfil"),};
            resultSetCadastro.add(linha);

        }
        fireTableStructureChanged();
    }

    public void deletarLinha(int linha) {
        resultSetCadastro.remove(linha);
        fireTableRowsDeleted(linha, linha);
    }

    @Override
    public int getRowCount() {
        return resultSetCadastro.size();
    }

    @Override
    public int getColumnCount() {
        return nomesColunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String[] linha = resultSetCadastro.get(rowIndex);
        return linha[columnIndex];
    }

}
