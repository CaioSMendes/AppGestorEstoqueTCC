package br.com.alura.estoque.database.dao;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import br.com.alura.estoque.model.Produto;

@Dao
abstract public interface ProdutoDAO {

    @Insert
    long salva(Produto produto);

    @Update
    abstract void atualiza(Produto produto);

    @Query("SELECT * FROM Produto")
    List<Produto> buscaTodos();

    @Query("SELECT * FROM Produto WHERE id = :id")
    Produto buscaProduto(long id);

    @Delete
    abstract void remove(Produto produto);
}
