package br.com.matheush.apptodolist.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

import br.com.matheush.apptodolist.R;
import br.com.matheush.apptodolist.adapter.TarefaAdapter;
import br.com.matheush.apptodolist.dao.TarefaDao;
import br.com.matheush.apptodolist.model.Tarefa;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ConcluidasActivity extends AppCompatActivity {

    private static final String TAG = "LogX_Concluidas";
    @BindView(R.id.concluidas_rv_concluidas)
    RecyclerView rvConcluidas;

    private TarefaAdapter tarefaAdapter;
    private List<Tarefa> concluidasList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concluidas);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        concluidasList = new LinkedList<>();

        rvConcluidas.setLayoutManager(new LinearLayoutManager(this));
        rvConcluidas.addItemDecoration(new DividerItemDecoration(ConcluidasActivity.this, DividerItemDecoration.VERTICAL));
        rvConcluidas.setItemAnimator(new DefaultItemAnimator());
        rvConcluidas.setHasFixedSize(true);

        tarefaAdapter = new TarefaAdapter(ConcluidasActivity.this, concluidasList, onItemClickListener(), onItemLongClickListener());
        rvConcluidas.setAdapter(tarefaAdapter);


    }

    public void atualizaLista() {
        //RealmResults<Tarefa> tarefas = MyApplication.REALM.where(Tarefa.class).findAllSorted("id", Sort.DESCENDING);
        concluidasList.clear();
        concluidasList.addAll(new TarefaDao().getObjetosFinalizados());
        tarefaAdapter.notifyDataSetChanged();

        Log.d(TAG, "Lista Tarefas: " + concluidasList.toString());
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
                Intent intent = new Intent(ConcluidasActivity.this, DetalheActivity.class);
                intent.putExtra("index", concluidasList.get(position).getId());
                startActivity(intent);
            }
        };
    }

    protected TarefaAdapter.OnItemLongClickListener onItemLongClickListener() {
        return new TarefaAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClicked(final int position) {
                CharSequence opcoes[] = new CharSequence[] {"Visualizar", "Deletar", "Reativar"};

                AlertDialog.Builder builder = new AlertDialog.Builder(ConcluidasActivity.this);
                builder.setTitle("Opções da tarefa");
                builder.setItems(opcoes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(getApplicationContext(), "Clicou " + which, Toast.LENGTH_SHORT).show();
                        Intent intent;
                        int index = concluidasList.get(position).getId();
                        switch (which) {
                            case 0:
                                intent = new Intent(ConcluidasActivity.this, DetalheActivity.class);
                                intent.putExtra("index", index);
                                startActivity(intent);
                                break;
                            case 1:
                                new AlertDialog.Builder(ConcluidasActivity.this)
                                        .setTitle("Aviso!")
                                        .setMessage("Deseja apagar a tarefa " + concluidasList.get(position).getTarefa() + "?")
                                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                Tarefa tarefa = concluidasList.get(position);
                                                new TarefaDao().deleteObjeto(tarefa);
                                                atualizaLista();
                                            }
                                        }).setNegativeButton("Não", null).show();
                                break;
                            case 2:
                                Tarefa tarefa = new Tarefa();
                                tarefa.setId(concluidasList.get(position).getId());
                                tarefa.setTarefa(concluidasList.get(position).getTarefa());
                                tarefa.setHora(concluidasList.get(position).getHora());
                                tarefa.setData(concluidasList.get(position).getData());
                                tarefa.setAtiva(true);
                                tarefa.setHoraConclusao("Não concluída");
                                tarefa.setDataConclusao("Não concluída");

                                new TarefaDao().atualizaObjeto(tarefa);

                                Toast.makeText(getApplicationContext(), "Tarefa reativada com sucesso!", Toast.LENGTH_SHORT).show();

                                atualizaLista();
                                break;
                        }

                    }
                }).show();

                return true;
            }
        };
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
