package br.com.alura.estoque.ui.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.math.BigDecimal;

import androidx.appcompat.app.AlertDialog;
import br.com.alura.estoque.R;
import br.com.alura.estoque.model.Produto;

abstract public class FormularioProdutoDialog {

    private final String titulo;
    private final String tituloBotaoPositivo;
    private final ConfirmacaoListener listener;
    private final Context context;
    private static final String TITULO_BOTAO_NEGATIVO = "Cancelar";
    private Produto produto;

    FormularioProdutoDialog(Context context,
                            String titulo,
                            String tituloBotaoPositivo,
                            ConfirmacaoListener listener) {
        this.titulo = titulo;
        this.tituloBotaoPositivo = tituloBotaoPositivo;
        this.listener = listener;
        this.context = context;
    }

    FormularioProdutoDialog(Context context,
                            String titulo,
                            String tituloBotaoPositivo,
                            ConfirmacaoListener listener,
                            Produto produto) {
        this(context, titulo, tituloBotaoPositivo, listener);
        this.produto = produto;
    }

    public void mostra() {
        @SuppressLint("InflateParams") View viewCriada = LayoutInflater.from(context)
                .inflate(R.layout.formulario_produto, null);
        tentaPreencherFormulario(viewCriada);
        new AlertDialog.Builder(context)
                .setTitle(titulo)
                .setView(viewCriada)
                .setPositiveButton(tituloBotaoPositivo, (dialog, which) -> {
                    EditText campoName = getEditText(viewCriada, R.id.formulario_produto_name);
                    EditText campoPrice = getEditText(viewCriada, R.id.formulario_produto_price);
                    EditText campoQuantity = getEditText(viewCriada, R.id.formulario_produto_quantity);
                    EditText campoUnity = getEditText(viewCriada, R.id.formulario_produto_unity);
                    EditText campoDescription = getEditText(viewCriada, R.id.formulario_produto_description);
                    EditText campoCodProdt = getEditText(viewCriada, R.id.formulario_produto_prodCod);
                    EditText campoGtin = getEditText(viewCriada, R.id.formulario_produto_GTIN);
                    EditText campoRfid = getEditText(viewCriada, R.id.formulario_produto_RFID);
                    EditText campoNameCat = getEditText(viewCriada, R.id.formulario_produto_nameCatProd);

                    criaProduto(campoName, campoPrice, campoQuantity, campoUnity, campoDescription, campoCodProdt, campoGtin, campoRfid, campoNameCat);
                })
                .setNegativeButton(TITULO_BOTAO_NEGATIVO, null)
                .show();
    }

    @SuppressLint("SetTextI18n")
    private void tentaPreencherFormulario(View viewCriada) {
        if (produto != null) {
            TextView campoId = viewCriada.findViewById(R.id.formulario_produto_id);
            campoId.setText(String.valueOf(produto.getId()));
            campoId.setVisibility(View.VISIBLE);
            EditText campoNome = getEditText(viewCriada, R.id.formulario_produto_name);//Mudar esse ID
            campoNome.setText(produto.getName());
            EditText campoPreco = getEditText(viewCriada, R.id.formulario_produto_price);//Mudar esse ID
            campoPreco.setText(produto.getPrice().toString());
            EditText campoQuantidade = getEditText(viewCriada, R.id.formulario_produto_quantity);
            campoQuantidade.setText(String.valueOf(produto.getQuantity()));
            EditText campoUnitu = getEditText(viewCriada, R.id.formulario_produto_unity);//Mudar esse ID
            campoUnitu.setText(produto.getUnity());
            EditText campoDescription = getEditText(viewCriada, R.id.formulario_produto_description);//Mudar esse ID
            campoDescription.setText(produto.getDescription());
            EditText campoProdCod = getEditText(viewCriada, R.id.formulario_produto_prodCod);//Mudar esse ID
            campoProdCod.setText(produto.getProductCode());
            EditText campoGTIN = getEditText(viewCriada, R.id.formulario_produto_GTIN);//Mudar esse ID
            campoGTIN.setText(produto.getGtin());
            EditText campoRFID = getEditText(viewCriada, R.id.formulario_produto_RFID);//Mudar esse ID
            campoRFID.setText(produto.getRfid());
            EditText campoCatProd = getEditText(viewCriada, R.id.formulario_produto_nameCatProd);//Mudar esse ID
            campoCatProd.setText(produto.getName_categorie());
        }
    }

    private void criaProduto(EditText campoName, EditText campoPrice, EditText campoQuantity, EditText campoUnity, EditText campoDescription, EditText campoProdtCod, EditText campoGtin, EditText campoRfid, EditText campoNameCat) {
        long id = preencheId();
        String name = campoName.getText().toString();
        BigDecimal price = tentaConverterPreco(campoPrice);
        //String price = campoPrice.getText().toString();//tirar dps aqui
        int quantity = tentaConverterQuantidade(campoQuantity);
        String unity = campoUnity.getText().toString();
        String description = campoDescription.getText().toString();
        String codProdt = campoProdtCod.getText().toString();
        String gtin = campoGtin.getText().toString();
        String rfid = campoRfid.getText().toString();
        String nameCat = campoNameCat.getText().toString();

        Produto produto = new Produto(id, name, description, price, quantity, unity, codProdt, gtin, rfid, nameCat);
        listener.quandoConfirmado(produto);
    }


        private long preencheId() {
        if (produto != null) {
            return produto.getId();
        }
        return 0;
    }

    private BigDecimal tentaConverterPreco(EditText campoPrice) {
        try {
            return new BigDecimal(campoPrice.getText().toString());
        } catch (NumberFormatException ignored) {
            return BigDecimal.ZERO;
        }
    }

    private int tentaConverterQuantidade(EditText campoQuantity) {
        try {
            return Integer.valueOf(
                    campoQuantity.getText().toString());
        } catch (NumberFormatException ignored) {
            return 0;
        }
    }

    private EditText getEditText(View viewCriada, int idTextInputLayout) {
        TextInputLayout textInputLayout = viewCriada.findViewById(idTextInputLayout);
        return textInputLayout.getEditText();
    }

    public interface ConfirmacaoListener {
        void quandoConfirmado(Produto produto);
    }


}
