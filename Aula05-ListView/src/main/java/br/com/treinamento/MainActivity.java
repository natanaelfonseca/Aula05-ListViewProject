package br.com.treinamento;

import android.app.ActionBar;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

public class MainActivity extends ListActivity  {

    private String[] cidades = new String[]{ "Afonso Cláudio","Água Doce do Norte", "Águia Branca",
                                             "Alegre", "Alfredo Chaves", "Alto Rio Novo", "Anchieta",
                                             "Apiacá", "Aracruz", "Atilio Vivacqua", "Baixo Guandu",
                                             "Barra de São Francisco", "Boa Esperança", "Bom Jesus do Norte", "Brejetuba"  };

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mListView = getListView();
        mListView.setChoiceMode( ListView.CHOICE_MODE_MULTIPLE );
        mListView.setTextFilterEnabled( true );

        ArrayAdapter<String> adapter = new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1, cidades );
        this.setListAdapter(adapter);

        handleIntent(getIntent());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.main, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();

        searchView.setSearchableInfo( searchManager.getSearchableInfo(getComponentName()));

        return true;
    }


    @Override
    protected void onNewIntent(Intent intent) {

        handleIntent(intent);

    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {

            String query = intent.getStringExtra(SearchManager.QUERY);

            mListView.setFilterText( query );

        }
    }


}
