package com.lindseyweberc196.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.lindseyweberc196.Entity.Term;
import java.util.List;

@Dao
public interface TermDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert (Term term);

    @Delete
    void deleteTerm (Term term);

    @Query("DELETE FROM term_table")
    void deleteAllTerms();

    @Query("SELECT * FROM term_table ORDER BY termID ASC")
    LiveData<List<Term>> getAllTerms();

}
