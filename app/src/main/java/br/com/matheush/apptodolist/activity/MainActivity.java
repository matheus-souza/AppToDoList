package br.com.matheush.apptodolist.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import br.com.matheush.apptodolist.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fab)
    FloatingActionButton fab;
    private TarefaAdapter tarefaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ADICIONA A ATIVIDADE
            }
        });
    }

    @Override
    protected TarefaAdapter.OnItemClickListener onItemClickListener() {
        return new TarefaAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(View view, int position) {

            }
        };
    }

    protected TarefaAdapter.OnItemLongClickListener onItemLongClickListener() {
        return new TarefaAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClicked(int position) {
                return true;
            }
        };
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_excluir_todos) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
