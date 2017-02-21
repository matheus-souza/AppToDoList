package br.com.matheush.apptodolist.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.LinkedList;
import java.util.List;

import br.com.matheush.apptodolist.MyApplication;
import br.com.matheush.apptodolist.R;
import br.com.matheush.apptodolist.adapter.TarefaAdapter;
import br.com.matheush.apptodolist.model.Tarefa;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements Validator.ValidationListener {

    @BindView(R.id.fab)
    FloatingActionButton fab;
    @NotEmpty(message = MyApplication.MSG_VAZIO)
    @BindView(R.id.et_nova_tarefa_main)
    EditText etNovaTarefa;
    @BindView(R.id.rv_tarefas_main)
    RecyclerView rvTarefas;

    private TarefaAdapter tarefaAdapter;
    private Validator validator;
    private List<Tarefa> tarefaList;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        validator = new Validator(this);
        validator.setValidationListener(this);

        tarefaList = new LinkedList<>();

        Realm.init(getApplicationContext());
        realm = Realm.getDefaultInstance();

        rvTarefas.setLayoutManager(new LinearLayoutManager(this));
        rvTarefas.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL));
        rvTarefas.setItemAnimator(new DefaultItemAnimator());
        rvTarefas.setHasFixedSize(true);

        tarefaAdapter = new TarefaAdapter(MainActivity.this, tarefaList, onItemClickListener(), onItemLongClickListener());
        rvTarefas.setAdapter(tarefaAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validator.validate();
            }
        });
    }

    public void atualizaLista() {
        RealmResults<Tarefa> tarefas = realm.where(Tarefa.class).findAll();
        tarefaList.clear();
        tarefaList.addAll(tarefas);
        tarefaAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();

        atualizaLista();
    }

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

    @Override
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

    @Override
    public void onValidationSucceeded() {

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
}
